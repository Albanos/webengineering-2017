package com.micromata.webengineering.demo.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/post/add/POST", method = RequestMethod.POST)
    public void addPostWithPOST(@RequestParam("title") String title){ postService.addPost(title);}
}
