package com.micromata.webengineering.demo.post;

import com.micromata.webengineering.demo.user.User;
import com.micromata.webengineering.demo.user.UserRepository;
import com.micromata.webengineering.demo.user.UserService;
import org.apache.tomcat.jni.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Handle all CRUD operations for posts.
 */
@Service
public class PostService {
    private static final Logger LOG = LoggerFactory.getLogger(PostService.class);


    @Autowired
    private PostRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    /**
     * Retrieve the list of all posts.
     *
     * @return post list
     */
    public Iterable<Post> getPosts() {
        User currentUser =  userService.getCurrentUser();
        LOG.info("Current User {}"+currentUser);


        return repository.findAll();
    }


    /**
     * Add a new post.
     *
     * @param post the post title.
     */
    public void addPost(Post post) {
        LOG.info("Adding post. user={}, title={}", userService.getCurrentUser().getEmail(), post.getTitle());

        //Wir suchen den User (im moment ja nur einer vorhanden) und adden ihn als Author zum Post. Erst danach wird
        //persistiert
        User author = userRepository.findByEmail("l.hajzeraj@gmail.com");
        post.setAuthor(author);

        repository.save(post);
    }

    public Post getPost(Long id){
        LOG.info("Retrieving post. user={}, id={}", userService.getCurrentUser().getEmail(), id);
        /*
        for(Post p : posts){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
        */


        return repository.findOne(id);
    }

    public void deletePost(Long id){
        //Wir schauen, ob der aktuell eingeloggte User berechtigt ist, einen Post zu loeschen
        Post post = repository.findOne(id);

        //Ist der aktuell eingeloggte User nicht der Author des Posts: Exception werfen!
        //Den aktuellen user erhalten wir über das JWT-Token
        if(!post.getAuthor().equals(userService.getCurrentUser())){
            LOG.info("Deleting post not allowed. user={}, id={}", userService.getCurrentUser().getEmail(), id);
            throw new IllegalStateException("User not allowed to delete post");
        }
        LOG.info("Deleting post. user={}, id={}", userService.getCurrentUser().getEmail(), id);

        //Im positiven Fall (akt User ist Author): Erlaube den delete
        repository.delete(id);
    }
}
