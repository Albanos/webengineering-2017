package com.micromata.webengineering.demo.post;

import com.micromata.webengineering.demo.user.UserService;
import com.micromata.webengineering.demo.util.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * HTTP endpoint for a post-related HTTP requests.
 */
@RestController
public class PostController {

    //Kapselung der URL, wegen besserem Stil
    public static class PostCreated{
        public String url;
    }

    @Autowired
    private AddressService addressService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @RequestMapping(value= "/api/post", method = RequestMethod.GET)
    public Iterable<Post> getPostList() {
        return postService.getPosts();
    }


    //Wir müssen den Inhalt über ein Tool übergeben, aber: der Inhalt muss im JSON-Format sein!!!!
    @RequestMapping(value= "/api/post", method = RequestMethod.POST)
    public ResponseEntity<Object> addPost(@RequestBody Post post) {

        //Wenn aktueller Nutzer ncht authorisiert ist, kann er keinen neuen Post anlegen!
        if (userService.isAnonymous()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        //Validate Title: Wenn ein Titel eingegeben wird, der einen längeren Titel als 1024 Zeichen hat, sende ein Bad-Request zurück
        //da nicht gewollt...
        if(post.getTitle() != null && post.getTitle().length() > 1024){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        postService.addPost(post);

        PostCreated postCreated = new PostCreated();
        postCreated.url = addressService.getServerURL() + "api/post/" + post.getId();

        //das return sorgt fuer die Rueckgabe der generierten URL
        return ResponseEntity.ok(postCreated);
    }


    //Rückgabe eines bestimmten Posts aus der Liste
    @RequestMapping("/{id}")
    public Post getPostByID(@PathVariable Long id){
        return postService.getPost(id);
    }

    //Loeschen eines bestimmten Posts
    @RequestMapping(value = "/api/post/{id}", method = RequestMethod.DELETE)
    public void deletePostByID(@PathVariable Long id){
        postService.deletePost(id);
    }

    @RequestMapping(value = "/api/post/{id}", method = RequestMethod.GET)
    public Post getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }


}
