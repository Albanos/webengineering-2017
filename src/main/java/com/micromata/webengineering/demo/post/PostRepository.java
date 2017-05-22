package com.micromata.webengineering.demo.post;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Luan Hajzeraj on 22.05.2017.
 */

//Wir geben dem CrudRepo die eine Entinität an, also Post, sowie den Primärschlüssel
    //Alle Funktionen zum persistieren dieser Daten werden schon direkt angeboten
public interface PostRepository extends CrudRepository<Post, Long> {
    //Empty now
}
