package com.maurevair.booking.domain;

import com.maurevair.commons.Constants;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "FLIGHT_DETAILS")
public class FlightDetails {
    @Id
    @Column(name = "FLIGHT_DETAILS_ID", unique = true, nullable = false)
    private String flightDetailsId = Constants.AIRPORT_INFO + UUID.randomUUID().toString();
    @Column(name = "CARRIER")
    private String carrier;
    @Column(name = "FLIGHT_NUMBER")
    private String flightNumber;
    @Column(name = "ORIGIN")
    private String origin;
    @Column(name = "DESTINATION")
    private String destination;
    @Column(name = "DEPARTURE_TIME")
    private LocalDateTime departureTime;
    @Column(name = "ARRIVAL_TIME")
    private LocalDateTime arrivalTime;
    @OneToMany(mappedBy = "flightDetails",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Booking> bookingList;
}
