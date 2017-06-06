package com.micromata.webengineering.demo.comment;

import com.micromata.webengineering.demo.post.Post;
import com.micromata.webengineering.demo.user.UserService;
import com.micromata.webengineering.demo.util.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Luan Hajzeraj on 06.06.2017.
 */

//AGIERT NUR ALS DUMMY CONTROLLER, EIGENTLICHE FUNKTIONALITÄT SCHON UMGESETZT!!
@RestController
public class CommentController {
    private static class CommentCreated {
        public String url;
    }

    private static class NewComment {
        public Long postid;
        public String text;
    }

    @Autowired
    private AddressService addressService;



    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/api/comment", method = RequestMethod.POST)
    public ResponseEntity<CommentCreated> addComment(@RequestBody NewComment newComment) {

        //Ist aktueller User Anonym, kann der keine Kommentare an Posts hängen!
        if (userService.isAnonymous()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Long id = commentService.addComment(newComment.postid, newComment.text);
        CommentCreated commentCreated = new CommentCreated();
        commentCreated.url = addressService.getServerURL() + "/api/comment/" + id;
        return ResponseEntity.ok(commentCreated);
    }

    @RequestMapping(value = "/api/comment/{id}", method = RequestMethod.POST)
    public void editComment(@PathVariable Long id, @RequestBody Comment comment) {
        commentService.update(id, comment);
    }

    @RequestMapping(value = "/api/comment/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);

    }
}