/**
 * Created by Luan Hajzeraj on 25.06.2017.
 */

//Wieder eine eigens geschriebene Komponente, die Benutzbar ist, innerhalb des Projekts
import React from "react";

    // A general counter component.
        class Counter extends React.Component {
            constructor(props) {
                super();
                this.state = {
                        counter: 0
                    }
            }

            onClick() {
                this.setState({
                    counter: this.state.counter + 1
                });
            }

            /*
            Wir bauen quasi unseren Counter um das Tag herum. Somit funktioniert der Click-listener
            auf der ganze Zeile des Textes...
             */
            render() {
                return (
                    <div onClick={this.onClick.bind(this)}>
                        {this.props.children}
                        ({this.state.counter})
                    </div>)
            }
        }

    export default Counter;