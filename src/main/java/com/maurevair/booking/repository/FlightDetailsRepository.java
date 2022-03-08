package com.maurevair.booking.repository;

import com.maurevair.booking.domain.FlightDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightDetailsRepository extends CrudRepository<FlightDetails, String> {
    List<FlightDetails> findByOriginAndDestination(String origin, String destination);
}
