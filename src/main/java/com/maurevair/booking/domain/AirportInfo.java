package com.maurevair.booking.domain;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity //indicates that class is an entity that is mapped to a database table
@Table(name = "AIRPORT_INFO") //indicates table name in the database
public class AirportInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "AIRPORT_INFO_ID", unique = true, nullable = false)
    private long airportInfoId;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "REGION")
    private String region;
    @Column(name = "CITY")
    private String city;
    @Column(name = "AIRPORT_CODE")
    private String airportCode;
}
