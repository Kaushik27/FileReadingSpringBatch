package com.knight.fr.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knight.fr.domain.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, String> {

}
