package com.cts.repository;

import com.cts.entitiy.VehicleInformationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface VehicleInformationRepository extends CrudRepository<VehicleInformationEntity, String> {
    Iterable<VehicleInformationEntity> findAllByCity(String city);
}
