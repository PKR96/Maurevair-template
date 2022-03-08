package com.maurevair.booking.repository;

import com.maurevair.booking.domain.AirportInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportInfoRepository extends CrudRepository<AirportInfo,Long> {
}
