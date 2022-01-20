package com.cts.repository;

<<<<<<< HEAD
import com.cts.entitiy.VehicleInformationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

=======
import com.cts.entities.VehicleInformationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
@Repository
public interface VehicleInformationRepository extends CrudRepository<VehicleInformationEntity, String> {
    Iterable<VehicleInformationEntity> findAllByCity(String city);
}
