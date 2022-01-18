package com.cts.repository;

import com.cts.entities.VehicleInformationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleInformationRepository extends CrudRepository<VehicleInformationEntity, String> {
    Iterable<VehicleInformationEntity> findAllByCity(String city);
}
