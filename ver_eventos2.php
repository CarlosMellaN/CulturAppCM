<?php
	include ('conexion.php');
	if($_SERVER["REQUEST_METHOD"]=="POST"){
		include ('conexion.php');
		verEvento();
	}
	
	function verEvento()
	{
		global $con;
		//mysqli_set_charset($con, 'utf8');
		$response = array();
		$IdEvento = $_POST["IdEvento"];

		$query = "SELECT IdEvento, Nombre, Descripcion, FechaInicio, FechaFin, Localizacion, HoraInicio, HoraFin, Estado FROM eventos WHERE IdEvento = '$IdEvento'";
		$result = mysqli_query($con, $query);
		$number_of_rows = mysqli_num_rows($result);

		if($number_of_rows > 0){

			$response ["eventos"]= array();

			while ($row = mysqli_fetch_assoc($result)){

				$evento = array();
				$evento["IdEvento"] = $row["IdEvento"];
				$evento["Nombre"] = $row["Nombre"];
				$evento["Descripcion"]= $row["Descripcion"];
				$evento["FechaInicio"]= $row["FechaInicio"];
				$evento["FechaFin"]= $row["FechaFin"];
				$evento["Localizacion"]= $row["Localizacion"];
				$evento["HoraInicio"]= $row["HoraInicio"];
				$evento["HoraFin"]= $row["HoraFin"];

				array_push($response["eventos"],$evento);

			}
			$response["success"] = true;
			echo json_encode($response);
		}else{
			$response["success"] = false;
			$response["message"] = "No hay eventos";
			echo json_encode($response);
		}
		//mysqli_close($con);

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