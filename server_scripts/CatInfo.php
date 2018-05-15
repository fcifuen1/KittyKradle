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

$id = $_POST['catId'];
$result = array();

$sql = "SELECT likes,bio,zipcode
FROM cat_info
WHERE cat_id='$id'";
$query = mysqli_query($conn,$sql);

while($row = mysqli_fetch_array($query)){
    array_push($result,array(
        'likes'=>$row['likes'],
        'bio'=>$row['bio'],
        'zipcode'=>$row['zipcode']));
}

echo json_encode($result);
?>