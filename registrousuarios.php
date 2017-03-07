<?php
	include ('conexion.php');

	$email = $_POST["email"];
	$nombre = $_POST["nombre"];
	$nombreusuario = $_POST["nombreusuario"];
	$contrasena = $_POST["contrasena"];
	$genero = $_POST["genero"];

	/*$statement = mysqli_prepare($con, "INSERT INTO usuarios ( email, nombre, nombreusuario, contrasena, genero)
		VALUES ('$_POST[email]','$_POST[nombre]','$_POST[nombreusuario]','$_POST[contrasena]','$_POST[genero]')");*/
	$statement = mysqli_prepare($con, "INSERT INTO usuarios ( email, nombre, nombreusuario, contrasena, genero)
		VALUES (?, ?, ?, ?, ?)");
	//las sisss son la cantidad de varaibles que lee ( s = string, i = int)
	mysqli_stmt_bind_param($statement, "sssss", $email, $nombre, $nombreusuario, $contrasena, $genero);
	mysqli_stmt_execute($statement);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);
?>