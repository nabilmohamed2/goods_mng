package com.projects.management_sys.repository;

import com.projects.management_sys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {

    //Instead of returning null when a Users entity is not found,
    //Optional is returned, which provides a more expressive and safer way
    //to deal with the potential absence of a value.
    Optional<User> findByUsername (String username );

    //findByUsername follows the convention of findBy<PropertyName>,
    // where PropertyName refers to a field in the User entity (assumed to be a username field).
    // Spring Data JPA automatically translates this method into a query like:
    //
    //SELECT u FROM User u WHERE u.username = ?1

    //By doing this we don't need to write separate methods inside service package.
}
