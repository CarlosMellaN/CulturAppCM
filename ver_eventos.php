<?php
	include ('conexion.php');
	if($_SERVER["REQUEST_METHOD"]=="POST"){
		include ('conexion.php');
		verEvento();
	}
	
	function verEvento()
	{
		global $con;

		$query = "SELECT Nombre, Descripcion, FechaInicio, FechaFin, Localizacion, HoraInicio, HoraFin, Estado FROM eventos";
		$result = mysqli_query($con, $query);
		$number_of_rows = mysqli_num_rows($result);

		$response = array();
		$response["success"] = true;

		if($number_of_rows > 0){
			while ($row = mysqli_fetch_assoc($result)){
				$response[] = $row;
			}
		}
		header('Content-Type: application/json');
		echo json_encode(array("eventos"=>$response));

		//echo json_encode($response);

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