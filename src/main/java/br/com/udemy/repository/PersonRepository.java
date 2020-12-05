package br.com.udemy.repository;

import br.com.udemy.data.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Modifying
    @Query("UPDATE Person p set p.enabled = 0 WHERE p.id = :id")
    void disablePerson(@Param("id") Long id);
}
