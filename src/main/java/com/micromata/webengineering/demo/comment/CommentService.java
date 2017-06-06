package com.micromata.webengineering.demo.comment;

import com.micromata.webengineering.demo.post.PostService;
import com.micromata.webengineering.demo.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Luan Hajzeraj on 06.06.2017.
 */

@Service
public class CommentService {
    private static final Logger LOG = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    private CommentRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    //Im Grunde wie bei den Posts: Suche den Kommentar und schaue, ob der Author der currentUser ist
    //Wenn ja: erlaube das loeschen. Wenn nicht, werfe Exception
    public void deleteComment(Long id) {
        // Validate that user is allowed to delete comment.
        Comment comment = repository.findOne(id);
        if (!comment.getAuthor().equals(userService.getCurrentUser())) {
            LOG.info("Deleting comment not allowed. user={}, id={}", userService.getCurrentUser().getEmail(), id);
            throw new IllegalStateException("User not allowed to delete comment");
        }

        //Da jedoch Kommentare an Posts hängen, müssen wir den Kommentar über die Posts löschen
        //(=Resultat unserer gewählten Architektur --> Kommentare hängen an Posts und nicht umgekehrt)
        LOG.info("Deleting comment. user={}, id={}", userService.getCurrentUser().getEmail(), id);
        postService.removeComment(comment);
    }


    public void update(Long id, Comment updateComment) {
        // Validate that user is allowed to edit comment.
        Comment comment = repository.findOne(id);
        if (!comment.getAuthor().equals(userService.getCurrentUser())) {
            LOG.info("Updating comment not allowed. user={}, id={}", userService.getCurrentUser().getEmail(), id);
            throw new IllegalStateException("User not allowed to update comment");
        }
        LOG.info("Updating comment. user={}, id={}", userService.getCurrentUser().getEmail(), id);

        //Setze den neuen Text beim Kommentar
        comment.setText(updateComment.getText());

        //Speiechere die aktualisierte Liste ab
        repository.save(comment);
    }


    /**
     * Add Comment to an existing Post
     * @param postId id of an Post
     * @param text text of the comment
     * @return id of the corresponding comment
     */

    /*
    Notation sorgt dafür, dass erst persistiert wird, wenn die Methode vollständig durchgelaufen ist. Damit wird
    verhindert, dass Kommentare ohne Posts entstehen können (wenn beim Kommentar-erstellen der Server abschmiert)

    JEDOCH: Würde man es überall nutzen, hätte man starke Performance-Probleme und ausserdem werden Daten zwischen-
    gespeichert. Macht man das zu oft, kann dieser Zwischenspeicher auch schnell volllaufen und Anwendung schmiert ab
    */
    @Transactional
    public Long addComment(Long postId, String text) {
        //Machen wir so, weil wir sonst beim anlegen eines neuen Kommentars "null" in der URL des Kommentares stehen haben

        // Persist comment.
        Comment comment = new Comment();
        comment.setText(text);
        comment.setAuthor(userService.getCurrentUser());
        repository.save(comment);

        /*
        Einfach eingebauter Test-Fehler!
        Diesen versuchen wir gleich zu handeln, denn ohne Handling hätten wir unter Umständen einen Kommentar,
        der zu keinem Post gehört.
        */
        if (true) {
            throw new IllegalStateException("Something went wrong");
        }

        // Append technically to post.
        postService.addComment(postId, comment);

        return comment.getId();
    }


    /**
      * Return a single comment.
      *
      * @param id comment id
      * @return a comment
      */
    public Comment getComment(Long id) {
        LOG.info("Retrieving comment. user={}, id={}", userService.getCurrentUser().getEmail(), id);
        return repository.findOne(id);
    }
}
