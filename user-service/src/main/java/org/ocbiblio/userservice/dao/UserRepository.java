package org.ocbiblio.userservice.dao;

import org.ocbiblio.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource
public interface UserRepository extends JpaRepository <User, Long> {

    User findUserByPseudoAndPassword (String pseudo, String password);
}
