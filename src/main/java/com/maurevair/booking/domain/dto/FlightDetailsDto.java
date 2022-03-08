package com.maurevair.booking.domain.dto;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

@XmlType( propOrder = {"flightDetailsId", "carrier", "flightNumber", "origin","destination","departureTime","arrivalTime","bookingList"})
@XmlRootElement(name = "flight")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class FlightDetailsDto {
    private String flightDetailsId;
    private String carrier;
    private String flightNumber;
    private String origin;
    private String destination;
    private Date departureTime;
    private Date arrivalTime;
    private List<BookingDto> bookingList;

    public String getFlightDetailsId() {
        return flightDetailsId;
    }

    @XmlAttribute(name = "id")
    public void setFlightDetailsId(String flightDetailsId) {
        this.flightDetailsId = flightDetailsId;
    }

    public String getCarrier() {
        return carrier;
    }

    @XmlAttribute(name = "Carrier")
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    @XmlAttribute(name = "FlightNumber")
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    @XmlAttribute(name="Origin")
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    @XmlAttribute(name="Destination")
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    @XmlAttribute(name="DepartureTime")
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }


    @XmlAttribute( name = "ArrivalTime" )
    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<BookingDto> getBookingList() {
        return bookingList;
    }

    @XmlElement(name = "BookingInfo",type = BookingDto.class)
    public void setBookingList(List<BookingDto> bookingList) {
        this.bookingList = bookingList;
    }
}
