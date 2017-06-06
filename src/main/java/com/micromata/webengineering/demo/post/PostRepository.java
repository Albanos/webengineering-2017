package com.micromata.webengineering.demo.post;

import com.micromata.webengineering.demo.comment.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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

    //WICHTIG: Dies gibt mir alle Posts, mit jedem Attribut des Posts zurück, also Author, Titel, Kommentare,...
    //@Query("SELECT p from Post p ORDER BY p.createdAt DESC")

    //Diese Query wiederrum gibt mir alle Posts ohne die Kommentare zurück. Ich lege einen neuen Post an und lasse das
    //Kommentare-Feld einfach unbefüllt, indem ich es weglasse...
    //Genau so geht man vor, wenn man nur bestimmte Werte eines Objektes zurück geben möchte
    @Query("SELECT new Post(p.id, p.author, p.title, p.createdAt) from Post p ORDER BY p.createdAt DESC")
    List<Post> findAll();

    //Liefere mir den jeweilligen Post zu einem Kommentar zurück, um diesen bspw loeschen zu koennen, denn:
    //Kommentare hängen an Posts...
    @Query("SELECT p FROM Post p WHERE :comment MEMBER OF p.comments")
    Post findPostForComment(@Param("comment") Comment comment);
}
