<?php
	
	if($_SERVER["REQUEST_METHOD"]=="POST"){
		include ('conexion.php');
		crearEvento();
	}

	function crearEvento()
	{
		global $con;
		$Nombre = $_POST["Nombre"];
		$Descripcion = $_POST["Descripcion"];
		$FechaInicio = $_POST["FechaInicio"];
		$FechaFin = $_POST["FechaFin"];
		$Localizacion = $_POST["Localizacion"];
		$HoraInicio = $_POST["HoraInicio"];
		$HoraFin = $_POST["HoraFin"];
		$Estado = $_POST["Estado"];

		$query = "INSERT INTO eventos (Nombre, Descripcion, FechaInicio, FechaFin, Localizacion, HoraInicio, HoraFin, Estado) 
		VALUES ('$Nombre', '$Descripcion', '$FechaInicio', '$FechaFin', '$Localizacion', '$HoraInicio', '$HoraFin', '$Estado')";

		$response = array();
		$response["success"] = true;
		echo json_encode($response);

		mysqli_query($con, $query);
		mysqli_close($con);
	}

/*
Id_EVento
Nombre
Descripcion
FechaInicio
FechaFin
Localizacion
HoraInicio
HoraFin
Estado
*/
?>

