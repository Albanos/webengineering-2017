package com.micromata.webengineering.demo.comment;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Luan Hajzeraj on 06.06.2017.
 */

//Brauchen wir, weil wir einen neuen Kommentar zu einem Post hinzufügen wollen, oder auch Kommentare loeschen wollen
public interface CommentRepository extends CrudRepository<Comment, Long> {
    //Empty now
}
