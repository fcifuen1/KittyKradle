<?php
	if(isset($_POST['username']) && isset($_POST['password']) && isset($_POST['email'])){
		$username = $_POST['username'];
		$password = $_POST['password'];
		$email = $_POST['email'];

		//Database Connection variable
		$dbname = "kittykradle";
		$server = "localhost";
		$dbuser = "root";
		$dbpass = "";

		//Database connecting
		$conn = mysqli_connect($server, $dbuser, $dbpass, $dbname);

		//Check for connection status
		if(!$conn){
			die ("Connection Failed" . mysql_connect_error());
		} 
		//==========================================================================
		//Functions
		//==========================================================================
		function isValidEmail($email){
			$valid = filter_var($email, FILTER_VALIDATE_EMAIL);
			return $valid;
		}
		function doesUserExist($user,$email,$conn){
			$sql = "SELECT userName, email FROM friend WHERE userName = ? OR email = ?";
			$query = $conn->prepare($sql);
			$query->bind_param("ss",$user,$email);
			$query->execute();
			$query->store_result();
			if($query->num_rows > 0){
				$query->close();
				return true;
			}else{
				$query->close();
				return false;
			}
		}
		function createNewUser($conn,$user, $pass, $email, $latitude, $longitude){
			$sql = "INSERT INTO friend (userName,password,email,latitude,longitude) VALUES (?, ?, ?, ?, ?)";
			$query = $conn->prepare($sql);
			//$query->bind_param("sssss", $user, $pass, $email, $latitude, $longitude);
			//Error Checking
			if (!$query)
			{
				echo "false";
			}
			if (!$query->bind_param("sssss", $user, $pass, $email, $latitude, $longitude))
			{
				echo "false";
			}
			if (!$query->execute())
			{
				echo "false";
			}
			$query->close();
		}
		//==========================================================================
		//Action
		//==========================================================================
		$validEmail = isValidEmail($email);
		if($validEmail){
			$exist = doesUserExist($user,$email,$conn);
			if($exist){
				echo "false";
			}else{
				createNewUser($conn,$user, $pass, $email, $latitude, $longitude);
				echo "true";
			}
		}else{
			echo "false";
		}

		$conn->close();
	}
	else{
		echo "false";
	}
?>