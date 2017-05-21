package com.micromata.webengineering.demo.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpMethod.POST;

/**
 * HTTP endpoint for a post-related HTTP requests.
 */
@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping("/post")
    public List<Post> getPostList() {
        return postService.getPosts();
    }


    @RequestMapping(value = "/post/add")
    public void addPost(@RequestParam("title") String title) {
        postService.addPost(title);
    }


    /* --> Muss noch geklärt werden!!!
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void addPost(@RequestBody Post post) {
        postService.addPost(post);
    }
    */

    //Rückgabe eines bestimmten Posts aus der Liste
    @RequestMapping("/post/{id}")
    public Post getPostByID(@PathVariable Long id){
        return postService.getPost(id);
    }

    //Loeschen eines bestimmten Posts
    @RequestMapping("post/delete/{id}")
    public void deletePostByID(@PathVariable Long id){
        postService.deletePost(id);
    }


}
