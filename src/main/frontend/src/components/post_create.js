/**
 * Created by Luan Hajzeraj on 26.06.2017.
 */

/*
Funktionalität zum hinzufügen von Posts, an das Backend
 */
import React from "react";
import axios from "axios";

    // See https://facebook.github.io/react/docs/forms.html for documentation about forms.
    class PostCreate extends React.Component {

        /*
        Mit dem Konstruktor setzen wir unseren aktuellen Status, wir legen also ganz zu Beginn den Titel fest.
        Diesen Zustand nutzen wir dann anschließend beim Übertragen. Zusätzlich schreiben wir einen onChange-List.
        der Änderungen in diesem Textfeld erkennt und dies dann updatet
         */
        constructor(props) {
            super(props);
            this.state = {
                title: 'default title'
            };


            // This is easier than adding it in the JSX part and you don't create a new function every time your component
            // is rendered.
            //
            // See https://medium.freecodecamp.com/react-binding-patterns-5-approaches-for-handling-this-92c651b5af56
            //
            // For general remarks regarding this in JavaScript see, e.g.
            // - http://developer.telerik.com/featured/seven-javascript-quirks-i-wish-id-known-about/
            // - http://ryanmorr.com/understanding-scope-and-context-in-javascript/
            //
            // (This comment will be deleted in some of the future commits.)
            this.handleTitleChange = this.handleTitleChange.bind(this);
            this.handleSubmit = this.handleSubmit.bind(this);

        }


        /*
        Der Change-List., der den Zustand immer updatet, sobald sich etwas in dem Textfeld ändert
         */
        handleTitleChange(event) {
            // Remember setState behaviour:
            //
            //     "You may optionally pass an object as the first argument to setState() instead of a function: [...]
            //     This performs a shallow merge of stateChange into the new state [...]."
            //
            // See https://facebook.github.io/react/docs/react-component.html#setstate
            this.setState({title: event.target.value});
        }



        /*
        Für das abschicken an das Backend zuständig. Im Grunde nur ein axios-Befehl
         */
        handleSubmit(event) {
            event.preventDefault();

            axios.post('/api/post',
                {
                    title: this.state.title
                });
        }



        /*
        Attribut des <form>-Tags: Wenn Enter gedrück wird, führe handleSubmit aus
         */
        render() {
            return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        title
                        <input type="text" name="title" value={this.state.value} onChange={this.handleTitleChange}/>
                    </label>
                    <input type="submit" value="Submit"/>
                </form>
            </div>
            );
        }
    }

export default PostCreate;
