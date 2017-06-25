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

            render() {
                return <span onClick={this.onClick.bind(this)}>{this.state.counter}</span>
            }
        }

    export default Counter;