package com.maurevair.commons;

import com.maurevair.booking.domain.AirportInfo;
import com.maurevair.booking.domain.Booking;
import com.maurevair.booking.domain.FlightDetails;
import com.maurevair.booking.domain.dto.BookingDto;
import com.maurevair.booking.domain.dto.FlightDetailsDto;
import com.maurevair.booking.domain.enums.BookingInfo;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class Mapper {

    public static String enumToStringMapper(BookingInfo bookingInfo) {
        switch (bookingInfo) {
            case FIRST:
                return "First";
            case PREMIUM_ECONOMY:
                return "PremiumEconomy";
            case ECONOMY:
                return "Economy";
            default:
                return null;
        }
    }

    public static BookingInfo stringToEnumMapper(String bookingInfo) {
        switch (bookingInfo) {
            case "First":
                return BookingInfo.FIRST;
            case "PremiumEconomy":
                return BookingInfo.PREMIUM_ECONOMY;
            case "Economy":
                return BookingInfo.ECONOMY;
            default:
                return null;
        }
    }

    public static AirportInfo dtoToEntity(String[] data){
        int i=0;
        AirportInfo airportInfo = new AirportInfo();
        airportInfo.setCountry(data[i]);
        airportInfo.setRegion(data[++i]);
        airportInfo.setCity(data[++i]);
        airportInfo.setAirportCode(data[++i]);
        return airportInfo;
    }

    public static FlightDetails dtoToEntity(FlightDetailsDto flightDetailsDto) {
        FlightDetails flightDetails = new FlightDetails();
        flightDetails.setFlightDetailsId(flightDetailsDto.getFlightDetailsId());
        flightDetails.setCarrier(flightDetailsDto.getCarrier());
        flightDetails.setFlightNumber(flightDetailsDto.getFlightNumber());
        flightDetails.setOrigin(flightDetailsDto.getOrigin());
        flightDetails.setDestination(flightDetailsDto.getDestination());
        flightDetails.setArrivalTime(flightDetailsDto.getArrivalTime().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        flightDetails.setDepartureTime(flightDetailsDto.getDepartureTime().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        flightDetails.setBookingList(Optional.ofNullable(flightDetailsDto.getBookingList())
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .map(bookingDto -> dtoToEntity(bookingDto, flightDetails))
                .collect(Collectors.toList()));
        return flightDetails;
    }

    private static Booking dtoToEntity(BookingDto bookingDto, FlightDetails flightDetails) {
        Booking booking = new Booking();
        booking.setCabinClass(bookingDto.getCabinClass());
        booking.setSeatsAvailable(bookingDto.getSeatsAvailable());
        booking.setFlightDetails(flightDetails);
        return booking;
    }

}
