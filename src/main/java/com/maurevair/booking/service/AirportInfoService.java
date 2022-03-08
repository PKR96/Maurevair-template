package com.maurevair.booking.service;

import com.maurevair.booking.domain.AirportInfo;
import com.maurevair.booking.repository.AirportInfoRepository;
import com.maurevair.commons.Mapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AirportInfoService {

    private final AirportInfoRepository airportInfoRepository;

    @Autowired
    public AirportInfoService(AirportInfoRepository airportInfoRepository){
        this.airportInfoRepository=airportInfoRepository;
    }

    public void saveAirportInfo(List<String[]> airportInfos){
        if(CollectionUtils.isNotEmpty(airportInfos)){
            airportInfos.stream()
                    .map(Mapper::dtoToEntity)
                    .forEach(airportInfoRepository::save);
        }
    }

    public List<AirportInfo> getAllInfo(){
        List<AirportInfo> airportInfos = new ArrayList<>();
        airportInfoRepository.findAll()
                .iterator()
                .forEachRemaining(airportInfos::add);
        return airportInfos;
    }

    public AirportInfo getAirport(long airportId){
        return airportInfoRepository.findById(airportId).orElse(new AirportInfo());
    }
}
