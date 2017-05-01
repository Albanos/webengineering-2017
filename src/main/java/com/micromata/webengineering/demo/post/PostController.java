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

    //Funktioniert irgendwie nicht
    @RequestMapping(value = "/post/add/POST", method = RequestMethod.POST)
    @ResponseBody
    public void addPostWithPOST( String title){ postService.addPost(title);}

    //Rückgabe eines bestimmten Posts aus der Liste
    @RequestMapping("/post/id/{id}")
    public Post getPostByID(@PathVariable int id){
        for (Post p : postService.getPosts()) {
            if(p.getId() == id){
                return p;
            }
        }
        //Gibt es diesen Post nicht, gebe null zurück
        return null;
    }

    //Loeschen eines bestimmten Posts
    @RequestMapping("post/delete/{id}")
    public void deletePostByID(@PathVariable int id){
        for (Post p : postService.getPosts()) {
            if(p.getId() == id){
                postService.getPosts().remove(p);
            }
        }
    }


}
