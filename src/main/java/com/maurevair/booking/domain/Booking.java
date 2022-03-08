package com.maurevair.booking.domain;

import com.maurevair.booking.domain.enums.BookingInfo;
import com.maurevair.commons.Mapper;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "BOOKING")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "BOOKING_ID", unique = true, nullable = false)
    private long bookingId;
    @Column(name = "CABIN_CLASS")
    @Enumerated(value = EnumType.STRING)
    private BookingInfo cabinClass;
    @Column(name = "SEATS_AVAILAIBLE")
    private int seatsAvailable;
    @ManyToOne
    private FlightDetails flightDetails;

    public void setCabinClass(String cabinClass) {
        this.cabinClass = Mapper.stringToEnumMapper(cabinClass);
    }

    public String getCabinClass() {
        return Mapper.enumToStringMapper(this.cabinClass);
    }
}
