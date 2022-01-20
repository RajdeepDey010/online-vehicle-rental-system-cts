package com.cts.repository;

<<<<<<< HEAD
import com.cts.entitiy.User;
=======
import com.cts.entities.User;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
<<<<<<< HEAD
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

=======
                                                     //entity and pk type
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
    boolean existsByEmail(String email);
}
