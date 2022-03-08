package com.maurevair.booking.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "flights")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class FlightDetailsList {

    private List<FlightDetailsDto> flightDetailsDtoList;

    @XmlElement(name = "flight")
    public void setFlightDetailsDtoList(List<FlightDetailsDto> flightDetailsDtoList){
        this.flightDetailsDtoList=flightDetailsDtoList;
    }

    public List<FlightDetailsDto> getFlightDetailsDtoList() {
        return flightDetailsDtoList;
    }
}
