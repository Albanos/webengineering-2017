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

import axios from "axios";

import PostList from "./components/post_list";

import PostCreate from "./components/post_create";





/*
Globale Definition des Headers: Dieser Header wird bei jedem POST-Request mitgesendet. Im Header muss ja immer, für das
absetzen von Posts, ein JWT-Token mitgegeben werden. Dieses definieren wir hiermit global und geben es bei jedem
POST-Request mit. Damit schaffen wir quasi eine globale authorisierung!!! (später erfolgt das login separat)
 */
axios.defaults.headers.common['Authorization'] = 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdWFuIiwianRpIjoiMSJ9.OOfxIrCaXhZhAGLTAmEWneNDiY--N15g5CxI7b8n7WI2HtGmEQwNYAz05z69bldDwRmfBqJ8eWXwkcvQbRXTIw';






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







/*
Wir testen axios: Wir schicken einen GET-Request an Michels seite. Bekommen wir eine Antwort, geben wir diese
einfach auf der Konsole (WEB-Konsole des Browsers) aus.
Wir können uns im Konsolen-LOG über "data.data" auch nur den HTML-Inhalt zurückgeben lassen.

axios.get('http://www.mlesniak.com') //Die Pfeil-variante stammt von der neueren JavaScript-Version 6
    .then(({data}) =>   //{data} heisst: Ich bekomme irgendein Objekt und greife dann darüber auf data zu. Quasi data.data
        console.log(data)
    );
*/





/*
Wir gehen nun nach dem Test einen Schritt weiter und geben, wie oben, die Liste aller Posts in der Web-Konsole aus.
Dies ermöglicht uns somit bereits die erste Kommunikation zwischen Back- & Frontend (Siehe Web-Konsole)

    axios.get('/api/post')
        .then((data) => {
        console.log(data);
});
*/




/*
WICHTIGE BEMERKUNG ZU AXIOS!!!!
Axios läuft asynchron ab, weshalb nicht immer alles direkt und sofort umgesetzt wird. Synchronität werden wir später
noch kennen lernen, so läuft es aber zunächst asynchron!!!
 */
/*
Liefere mir nun jeden Post einzeln, auf der Konsole: Greife auf .data vom ankommenden Objekt zu. Iteriere dann
über den Inhalt des Objektes und gebe mir alles separat auf der Konsole aus
 */
axios.get('/api/post')
.then(({data}) => {
    for (var post of data) {
        //console.log(post);
    }
});



/*
Nun legen wir einen neuen Post an: dafür benötigen wir axios.post(), aber eben zusätzlich auch ein gültiges JWT-Token,
denn nun authorisierte/eingeloggte User können Posts anlegen..
Dafür geben wir dem POST-Befehl von Axios noch eine Konfiguration mit, die ein gültiges JWT-Token enthählt.

DANK DES GLOBAL DEFINIERTEN HEADERS: das JWT-Token ist oben global definiert und wird immer mitgesendet. Macht also
diesen Aufruf leichter

    axios.post('/api/post',
    {
    // POST data
        title: 'with-global-auth // axios-' + new Date().getTime()
    });
*/


/*
Nun sollen alle Posts auf dem Frontend angezeigt werden. Wir nutzen die angelegte PostList-Komponente aus post_list.js

<hr/>: Erzeugt einfach nur eine horizontale Trennline

<PostCreate/>: Bindet unser post_create.js ein

 */
ReactDOM.render(
        <div>
            <PostList />
            <hr/>
            <PostCreate/>
        </div>,
        document.getElementById('root'));




/*
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
*/