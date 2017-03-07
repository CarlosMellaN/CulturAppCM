<?php

//MySQLi coneccion
//Datos de la base en localhost
$servername = "localhost";
$username = "Carlos";
$password = "qwe123";
$dbname = "culturappcm";

$con = new mysqli($servername, $username, $password, $dbname);

if ($con->connect_error) {
	
    die("Fallo la conexión con la base de datos: " . $con->connect_error);
}

?>