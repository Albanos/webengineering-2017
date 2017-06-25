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


//Wir entwerfen einen eigenen Tag (also eine Wiederverwendbare Komponente)
var Greeter = React.createClass({
    render : function(){
        return <h3>Hello {this.props.message}, ich komme von der Function</h3>
    }
});

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
ReactDOM.render(
    <div>
        <Greeter message="Du"/>
        <Greeter message="anderer"/>
    </div>,
    document.getElementById('root')
);