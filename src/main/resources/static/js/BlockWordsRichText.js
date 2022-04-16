/*
Lo que he pensado es hacer una "LIBRERIA" con el String <script> y que lo modifique por caracteres ***** si lo encuentra
de esa manera se puede evitar.
No he encontrado nada sobre como securizarlo
y he pensado en esta manera

BASICAMENTE ES COMO CREAR UNA LISTA BLANCA


<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<h1>Input Nombre</h1> (Malas Palabras para probar nalgas, trasero y basura)
<br>
  <input type="text" placeholder="Nombre" id="nombretxt"> <br>  <br>
<button id="boton-guardar">Guardar</button><br>
<div>
  <label type="text" id="nombre"></label>
</div>





 */
var malasPalabras = ['script'];

const checkMalasPalabras = (palabra) => {
    var rgx = new RegExp(malasPalabras.join("|")+"|" + "/gi");
    return (rgx.test(palabra));
}

$('#boton-guardar').click(() => {

    var nombre = $("#nombretxt").val().toLowerCase();

    if(checkMalasPalabras(nombre)){
        window.alert("Mala palabra encontrada: " + nombre);
    }

});

//Esto es en php, que no tengo ni idea de como usarlo

$('#boton-guardar').click(() => {

    var nombre = $("#nombretxt").val().toLowerCase();

    if(checkMalasPalabras(nombre) == true){
        window.alert("Mala palabra encontrada: " + nombre);
    }

});