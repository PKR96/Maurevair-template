package com.maurevair.booking.domain.dto;

import javax.xml.bind.annotation.*;

@XmlType(propOrder = {"cabinClass", "seatsAvailable"})
@XmlRootElement(name = "BookingInfo")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BookingDto {
    private String cabinClass;
    private int seatsAvailable;

    public String getCabinClass() {
        return cabinClass;
    }

    @XmlAttribute(name = "CabinClass")
    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    @XmlAttribute(name = "SeatsAvailable")
    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }
}
