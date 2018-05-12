<?php
	if(isset($_POST['username']) && isset($_POST['password']) && isset($_POST['email'])){
		$username = $_POST['username'];
		$password = $_POST['password'];
		$email = $_POST['email'];

		//Other fields that has to be set to null for the code to work
		$phone_num = NULL;
		$fn = NULL;
		$ln = NULL;
		$background_check = NULL;
		$pet_insurance = NULL;
		$picture_url = NULL;

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
		function doesUserExist($username,$email,$conn){
			$sql = "SELECT username, email FROM user_info WHERE username = ? OR email = ?";
			$query = $conn->prepare($sql);
			$query->bind_param("ss",$username,$email);
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
		function createNewUser($conn,$username, $password, $email){
			$sql = "INSERT INTO user_info (phone_num,username,password,email,first_name,last_name,background_check,pet_insurance,picture_url) VALUES (?,?,?,?,?,?,?,?,?)";
			$query = $conn->prepare($sql);
			//$query->bind_param("sssss", $user, $pass, $email, $latitude, $longitude);
			//Error Checking
			if (!$query)
			{
				echo "false";
			}
			if (!$query->bind_param("ssssssiis", $phone_num,$username, $password, $email, $fn, $ln,$background_check,$pet_insurance,$picture_url))
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
			$exist = doesUserExist($username,$email,$conn);
			if($exist){
				echo "false";
			}else{
				createNewUser($conn,$username, $password, $email);
				echo "success";
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