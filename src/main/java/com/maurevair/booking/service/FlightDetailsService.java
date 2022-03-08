package com.maurevair.booking.service;

import com.maurevair.booking.domain.Booking;
import com.maurevair.booking.domain.FlightDetails;
import com.maurevair.booking.domain.dto.FlightDetailsList;
import com.maurevair.booking.repository.BookingRepository;
import com.maurevair.booking.repository.FlightDetailsRepository;
import com.maurevair.commons.Mapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FlightDetailsService {
    private final FlightDetailsRepository flightDetailsRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public FlightDetailsService(FlightDetailsRepository flightDetailsRepository, BookingRepository bookingRepository) {
        this.flightDetailsRepository = flightDetailsRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public void saveFlightDetails(URL url, Map<String, String> map) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(FlightDetailsList.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        FlightDetailsList flightDetailsList = (FlightDetailsList) unmarshaller.unmarshal(url);
        if (flightDetailsList.getFlightDetailsDtoList() != null && flightDetailsList.getFlightDetailsDtoList().size() > 0) {
            List<FlightDetails> flightDetailsList1 = flightDetailsList.getFlightDetailsDtoList().stream()
                    .map(Mapper::dtoToEntity)
                    .collect(Collectors.toList());
            flightDetailsList1.forEach(flightDetailsRepository::save);
        }
    }

    public List<FlightDetails> getFlightDetailsForOriginAndDestination(String origin, String destination) {
        return flightDetailsRepository.findByOriginAndDestination(origin, destination);
    }

    public String reserveFlight(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        String bookingId = request.getParameter("rowid");
        if (StringUtils.isNotBlank(bookingId)) {
            bookingRepository.findById(Long.parseLong(bookingId))
                    .ifPresent(booking -> {
                        if (booking.getSeatsAvailable() == 0)
                            sb.append("No Seats Available!");
                        else {
                            booking.setSeatsAvailable(booking.getSeatsAvailable() - 1);
                            bookingRepository.save(booking);
                            sb.append("Booking has been made for flight:" + booking.getFlightDetails().getFlightNumber());
                        }
                    });
        }
        return sb.toString();
    }

    public String displayData(List<FlightDetails> data) {
        StringBuilder sb = new StringBuilder();
        if (data != null && data.size() > 0) {
            sb.append("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "  <body>\n" +
                    "    <form action=\"/api/v1/info/reserveSeats\" method=\"post\">\n" +
                    "      <table border=\"1\">\n" +
                    "          <tr>\n" +
                    "              <th></th>\n" +
                    "              <th>Flight Number</th>\n" +
                    "              <th>Cabin class</th>\n" +
                    "              <th>Seats Available</th>\n" +
                    "          </tr>\n");
            data.forEach(flightDetails -> {
                for (Booking booking : flightDetails.getBookingList()) {
                    sb.append("<tr>\n")
                            .append("<td><input type=\"radio\" value=\"").append(booking.getBookingId()).append("\"").append("name=\"rowid\"></td>\n")
                            .append("<td>" + flightDetails.getFlightNumber() + "</td>\n")
                            .append("<td>" + booking.getCabinClass() + "</td>\n")
                            .append("<td>" + booking.getSeatsAvailable() + "</td>\n")
                            .append("</tr>");
                }
            });
            sb.append("</table>\n" +
                    "      <br />\n" +
                    "      <input type=\"submit\" value=\"Select\">\n" +
                    "    </form>\n" +
                    "  </body>\n" +
                    "</html>");
        }
        return sb.toString();
    }

}
