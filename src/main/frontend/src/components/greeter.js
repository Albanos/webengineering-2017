/**
 * Created by Luan Hajzeraj on 25.06.2017.
 */
import React from "react";

//Nutzung der Komponente Counter in der Komponente greeter
import Counter from "./counter";

//Wir nutzen Ecmascript6: Wir schreiben uns eine eigene Komponente, welche wir exportieren und woanders
//als solches einfach benutzen können
    class Greeter extends React.Component {
        render() {
            //Wenn message leer ist (aufruf von greeter-Tag ohne message attr.) setze als message unknown
            const message = !this.props.message ? 'unknown' : this.props.message;

            //Wir geben auch den Counter mit zurück/rufen ihn als JSX mit auf
            return <h1>Hello, {message}! (<Counter />)</h1>
        }
    }

//Exportiere diesen Code, zur späteren Nutzung/Möglichkeit des Imports
export default Greeter;