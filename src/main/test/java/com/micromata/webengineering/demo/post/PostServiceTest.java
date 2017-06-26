package com.micromata.webengineering.demo.post;

import com.micromata.webengineering.demo.post.Post;
import com.micromata.webengineering.demo.post.PostService;
import com.micromata.webengineering.demo.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.UUID;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Luan Hajzeraj on 11.06.2017.
 */

/*
MERKE: Die unteren beiden Annotationen @RunWith und @SpringBootTest sind nötig, um Tests in diesem Kontext
richtig benutzen zu können!! Wir müssen also quasi dem Test mitteilen, dass wir Spring benutzen, um Dinge
@Autowired richtig aufzulösen und nutzen zu können
Benutze den Spring-Runner, also nicht den Standard JUnit-Runner, bspw zuständig für dependency injection

ALSO: Für Unit-Tests benötigen wir diese beiden Annotations!!!
*/

//Nutze den Spring-Runner und NICHT den Standard JUnit-Runner. Sorgt bspw für Dependency-injection von Spring
@RunWith(SpringRunner.class)

//Ist dafür zuständig, dass bspw die Dinge aus application.properties geladen werden
@SpringBootTest
public class PostServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;


    //Wenn wir einen neuen Post anlegen wollen, ist dafür ein gesetzer User nötig. Diesen setzen wir über diese Methode,
    //mit der @Before-Annotation von JUnit. Führe also vor allen Tests diese Methode aus...
    @Before
    public void setup(){
        userService.setCurrentUser(1L, "luan");
    }

    @Test
    public void notNull(){
        assertNotNull("Läuft...", postService);
    }

    @Test
    @Transactional
    public void testPostAdd(){
        LOG.info("Number of posts: {}", countPosts());
        Post post = new Post();
        post.setTitle("TestTitle");
        post.setAuthor(userService.getCurrentUser());

        //assertNotNull(post.getId());
        postService.addPost(post);
        assertNotNull(post.getId());
    }

    /**
     * Test that a post is actually persisted.
     * <p>
     * NOTE: Usually we would actually test business logic which is implemented in the service. In our case there is
     * de facto no business logic hence we simply test a few persistence features, but keep in mind what I said about
     * testing core technologies in the lecture.
     */

    //MERKE: @Transactional nutzen wir hier, weil die Posts, die geadded werden nach dem Test wieder rückgängig gemacht werden
    //Damit haben wir zu jeder Zeit des ausführens einen frischen, sauberen Zustand
    @Test
    @Transactional
    public void testPostPersisted() {
        LOG.info("Number of posts: {}", countPosts());
        String uuid = UUID.randomUUID().toString();

        Post post = new Post();
        post.setTitle(uuid);
        //post.setAuthor(userService.getCurrentUser());

        assertNull(post.getId());
        postService.addPost(post);

        assertNotNull(post.getId());
        Long id = post.getId();
        Post storedPost = postService.getPost(id);
        assertEquals("Post correctly stored", storedPost.getTitle(), uuid);
    }


    private int countPosts() {
        int count = 0;
        Iterator<Post> it = postService.getPosts().iterator();
        while (it.hasNext()) {
            it.next();
            count++;
        }

        return count;
    }
}
