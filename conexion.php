<?php

//MySQLi coneccion
//Datos de la base en localhost
$servername = "localhost";
$username = "user";
$password = "pass";
$dbname = "culturappcm";

$con = new mysqli($servername, $username, $password, $dbname);

if ($con->connect_error) {
	
    die("Fallo la conexión con la base de datos: " . $con->connect_error);
}

?>
