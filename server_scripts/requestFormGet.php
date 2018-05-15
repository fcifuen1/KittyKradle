<?php

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

$username = $_GET["username"];

$result = array();


$sql = "SELECT user_info.phone_num, user_info.first_name, user_info.last_name, user_info.background_check, user_info.pet_insurance
FROM user_info
WHERE (user_info.username='$username')";
$query = mysqli_query($conn,$sql);

while($row = mysqli_fetch_array($query)){
    array_push($result,array(
        'phone_num'=>$row['phone_num'],
        'first_name'=>$row['first_name'],
        'last_name'=>$row['last_name'],
        'background_check'=>$row['background_check'],
        'pet_insurance'=>$row['pet_insurance']));
}

echo json_encode(array($result));
?>