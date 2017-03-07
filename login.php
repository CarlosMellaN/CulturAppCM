<?php
	include ('conexion.php');
	// $nombre de la variable y en los [] va el nombre de la entrada en html
	$nombreusuario = $_POST["nombreusuario"];
	$contrasena = $_POST["contrasena"];

	//tuve que poner todos los parametros para que el bind_result funcionara, deberia funcionar
	//solo con nombreususario y contrasena
	$statement = mysqli_prepare($con, "SELECT email, nombre, nombreusuario, contrasena, genero
			FROM usuarios
			WHERE nombreusuario = ?
			AND contrasena = ?");
	
	mysqli_stmt_bind_param($statement, "ss", $nombreusuario, $contrasena);
	mysqli_stmt_execute($statement); 

	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement, $email, $nombre, $nombreusuario, $contrasena, $genero);

	$response = array();
	$response["success"] = false;


	while(mysqli_stmt_fetch($statement)){
		$response["success"] = true;
		$response["nombreusuario"] = $nombreusuario;
		$response["contrasena"] = $contrasena;
		$response["email"] = $email;
		$response["nombre"] = $nombre;
		$response["genero"] = $genero;
	}
	echo json_encode($response);
?>
