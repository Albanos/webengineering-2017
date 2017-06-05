package com.micromata.webengineering.demo.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Luan Hajzeraj on 26.05.2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    //Wir rufen die folgende Methode über die Query auf. Nach ausführen der Query wird die darunterliegende
    //Methode aufgerufen, welche uns die Mail des Users zurückliefert.
    @Query("SELECT u FROM User_ u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    //Führt eben auch die Query aus und gibt ergebnis über die untere Methode zurück
    @Query("SELECT u from User_ u WHERE u.email = :email AND u.password = :password")
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    /*
    @Query("SELECT u from User_ u WHERE u.email = :email AND u.password = :password")
    User login(@Param("email") String email, @Param("password") String password);
    */
}
