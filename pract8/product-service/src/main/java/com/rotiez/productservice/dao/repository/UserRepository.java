package com.rotiez.productservice.dao.repository;

import com.rotiez.productservice.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
