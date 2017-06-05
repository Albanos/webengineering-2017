package com.micromata.webengineering.demo.post;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Luan Hajzeraj on 22.05.2017.
 */

//Wir geben dem CrudRepo die eine Entinität an, also Post, sowie den Primärschlüssel
    //Alle Funktionen zum persistieren dieser Daten werden schon direkt angeboten
public interface PostRepository extends CrudRepository<Post, Long> {
    //Es gab hier Probleme mit der Query! Sie wird nicht als Falsch angezeigt, da ein editor ausgeschaltet wurde. Irgendwas
    //stimmt damit aber nicht...

    //ALLG.: Wenn man die Annotation @Query verwendet, wird die Query vor dem Methodenaufruf ausgeführt. Hier würde sie uns
    //also alle Posts zurückgeben, aber nach dem Datum sortiert
    @Query("SELECT p from Post p ORDER BY p.createdAt DESC")
    //@Query("SELECT new Post(p.id, p.author, p.title, p.createdAt) from Post p ORDER BY p.createdAt DESC")
    List<Post> findAll();
}
