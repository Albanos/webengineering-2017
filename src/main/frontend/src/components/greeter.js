/**
 * Created by Luan Hajzeraj on 25.06.2017.
 */
import React from "react";

//Nutzung der Komponente Counter in der Komponente greeter
import Counter from "./counter";

//Wir nutzen Ecmascript6: Wir schreiben uns eine eigene Komponente, welche wir exportieren und woanders
//als solches einfach benutzen können

/* Wir nutzen functional Components (siehe direkt darunter), denn: Unser Greeter besitzt keinen state, weshalb
wir dies "einfacher" durch eine "anonyme" Funktion (siehe unten) lösen können. Ginge aber auch mit class...
Wir würden ja quasi immer nur die selbe NAchricht zurück geben, deshalb keinen state
class Greeter extends React.Component {
        render() {
            //Wenn message leer ist (aufruf von greeter-Tag ohne message attr.) setze als message unknown
            const message = !this.props.message ? 'unknown' : this.props.message;

            //Wir geben auch den Counter mit zurück/rufen ihn als JSX mit auf
            return <h1>Hello, {message}! (<Counter />)</h1>
        }
    }
*/

/*
Das selbe wie die obige Klasse, nur als functional component. Besser, da kein state vorliegt. Quasi der selbe Code.
Wir haben eine einfache Komponente, die immer nur die selbe NAchricht zurück gibt, also ohne state.
 */
const Greeter = ({message}) => {
    const procMessage = !message ? 'unknown' : message;
    //return <h1>Hello (functional component), {message}! (<Counter />)</h1>

    /*
    Quasi Schachtelung von Tags: Wir bauen den Counter um das h1 herum, somit funktioniert der Counter-Listn
    in der gesamte Zeile des angezeigten Textes
     */
    return (
        <Counter>
            <h1>Hello, {procMessage}!</h1>
        </Counter>);
};


//Exportiere diesen Code, zur späteren Nutzung/Möglichkeit des Imports
export default Greeter;