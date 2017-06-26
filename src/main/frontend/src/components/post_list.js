/**
 * Created by Luan Hajzeraj on 26.06.2017.
 */

/*
Unsere Komponente, die alle Posts auf dem Frontend listen soll
 */

import React from "react";
import axios from "axios";

class PostList extends React.Component {
    constructor(props) {
        super();
        this.state = {
            posts: []
        }
    }

    // This function is called before render() to initialize its state.
    componentWillMount() {
        axios.get('/api/post')
            .then(({data}) => {
                this.setState({
                    posts: data
                })
            });
    }

    /*
     Für das löschen von Posts zuständig
     */
    deletePost(id) {
        console.log("Deleting post " + id);

        // ES6 string interpolation (https://developer.mozilla.org/de/docs/Web/JavaScript/Reference/template_strings)
        // No error handling for now, e.g. if the user is not authenticated.
        axios.delete(`/api/post/${id}`);
    }




    /*
    Hier geben wir als MAP quasi iterativ alle Elemente von post zurück, dabei aber eben nur den Titel. Jeder Post
    braucht eine eindeutige ID (SAGT EIN WARNING; WENN WIR ES OHNE AUSFÜHREN WÜRDEN). Diese ID besitzen wir: nämlich
    die Post-ID. Die definieren wir noch zusätzlich.

     <span onClick={() =>this.deletePost(post.id)}>DELETE</span>: Das "() =>" macht aus dem Aufruf this.del ....
     eine anonyme Methode. Erst, wenn der span wirklich geklickt wird, wir die Methode ausgeführt. Lassen wir dies weg
     wird quasi bei jedem rendern "geklickt", was wir natürlich nicht wollen... Der Aufruf als anonyme Methode
     verhindert dies
     */
    renderPosts() {
        return this.state.posts.map((post => {
            return (
            <li key={post.id}>
                {post.id} {post.title} <span onClick={() =>this.deletePost(post.id)}>DELETE</span>
            </li>
            );
        }));
    }




    /*
    Wir definieren HTML-technisch eine unsorted-list und rufen dabei immer wieder renderPosts() auf (siehe oben)
     */
    render() {
        return (
            <div>
                <h1>Posts</h1>
                <ul>
                    {this.renderPosts()}
                </ul>
            </div>
        );
    }
}


export default PostList;