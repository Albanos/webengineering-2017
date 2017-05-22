package com.micromata.webengineering.demo.post;

import com.micromata.webengineering.demo.util.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * HTTP endpoint for a post-related HTTP requests.
 */
@RestController
@RequestMapping("/post")
public class PostController {
    public static class PostCreated{
        public String url;
    }

    @Autowired
    private AddressService addressService;

    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Post> getPostList() {
        return postService.getPosts();
    }


    /*
    @RequestMapping(value = "/post/add")
    public void addPost(@RequestParam("title") String title) {

        postService.addPost(new Post(title));

        PostCreated postCreated = new PostCreated();
        //postCreated.url= adressService.getServerURL() + "/api/post" + post.getId();
    }
    */


    //Wir müssen den Inhalt über ein Tool übergeben, aber: der Inhalt muss im JSON-Format sein!!!!
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> addPost(@RequestBody Post post) {
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
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePostByID(@PathVariable Long id){
        postService.deletePost(id);
    }


}
