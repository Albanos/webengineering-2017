/**
 * Created by Luan Hajzeraj on 17.06.2017.
 */
//Wir verweise auf index.html: Wir nehmen in dem Element "root" die Ersetzung mit dem h1 vor
//Der erste Parameter ist ein JSX-Wert, aus dem dann JS gemacht wird
/*
ReactDOM.render(
    <h1>Hi, ich werde mit React gezeigt</h1>,
    document.getElementById('root'));
*/

import React from "react";
import ReactDOM from "react-dom";
import Greeter from "./components/greeter";



//Dies lagern wir nun in eine eigene Componente aus, die wir importieren und nutzen!! (siehe oben!!!)

//Wir entwerfen einen eigenen Tag (also eine Wiederverwendbare Komponente)
/*import React from "react";
import ReactDOM from "react-dom";

class Greeter extends React.Component {
    render() {
        return <h1>Hello, {this.props.message}!</h1>
    }
}
*/
//MERKE: Sollte mal etwas nicht laufen, schaue in der Konsole des Browsers nach!!

//Wollte man Greeter in einem Aufruf häufiger verwenden, müsste man dies in ein div-Tag packen.
//Normal:
/*
 ReactDOM.render(
 <Greeter message="Du"/>,
 document.getElementById('root')
 );
Und mit mehreren Aufrufen der Komponente Greeter, siehe unten
 */

//Hierfür existiert eine eigene Kompnente, welche wir quasi ausführen. Diese Komponente (In EcmaScript6 geschr.)
//MERKE: Der letzter Greeter-Tag wird einfach von greeter.js über unknown gehandelt:Ist message null, dann unknown
ReactDOM.render(
    <div>
        <Greeter message="Du"/>
        <Greeter message="anderer"/>
        <Greeter/>
    </div>,
    document.getElementById('root')
);