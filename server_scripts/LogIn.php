<?php
	if(isset($_POST['username']) && isset($_POST['password'])){
		$username = $_POST['username'];
		$password = $_POST['password'];

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
		function checkUser($username,$password,$conn){
			$sql = "SELECT * FROM user_info WHERE username = ? AND password = ?";
			$query = $conn->prepare($sql);
			$query->bind_param("ss",$username,$password);
			$query->execute();
			$query->store_result();
			if($query->num_rows == 1){
				$query->close();
				return true;
			}else{
				$query->close();
				return false;
			}
		}
		//==========================================================================
		//Action
		//==========================================================================

		$success = checkUser($username,$password,$conn);
		if($success){
			echo "success";
		}else{
			echo "false";
		}

		$conn->close();
	}
	else{
		echo "false";
	}
?>