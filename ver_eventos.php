<?php
	include ('conexion.php');
	if($_SERVER["REQUEST_METHOD"]=="GET"){
		include ('conexion.php');
		verEvento();
	}
	
	function verEvento()
	{
		global $con;

		$query = "SELECT IdEvento, Nombre, Descripcion, FechaInicio, FechaFin, Localizacion, HoraInicio, HoraFin, Estado FROM eventos";
		$result = mysqli_query($con, $query);
		$number_of_rows = mysqli_num_rows($result);

		$response = array();
		//$response["success"] = true;

		if($number_of_rows > 0){
			while ($row = mysqli_fetch_assoc($result)){
				$response[] = $row;
			}
		}
		header('Content-Type: application/json');
		//con ese eventos se identifica
		echo json_encode(array("eventos"=>$response));

		mysqli_close($con);

	}
	/*
            IdEVento
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
