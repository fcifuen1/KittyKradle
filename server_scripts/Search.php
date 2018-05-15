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

$zipcode = $_POST['zipcode'];
$breed = $_POST['breed'];
$sex = $_POST['sex'];
// $age = $_GET["age"];
$minAge = $_POST['minAge'];
$maxAge = $_POST['maxAge'];
$size = $_POST['size'];

$result = array();

$sql = "SELECT cat_info.cat_id, cat_info.name, cat_detail.sex, cat_info.picture_url
FROM cat_info INNER JOIN cat_detail ON cat_info.cat_id =cat_detail.cat_id
WHERE (cat_info.zipcode='$zipcode' OR '$zipcode' = 'null')
   AND (cat_detail.breed='$breed' OR '$breed' = 'null')
   AND (cat_detail.sex='$sex' OR '$sex' = 'null')
   AND (cat_detail.age >'$minAge' OR '$minAge' = 'null') 
   AND (cat_detail.age < '$maxAge' OR '$maxAge' = 'null')
   AND (cat_detail.size='$size' OR '$size' = 'null')";
$query = mysqli_query($conn,$sql);

while($row = mysqli_fetch_array($query)){
    array_push($result,array(
        'id'=>$row['cat_id'],
        'name'=>$row['name'],
        'gender'=>$row['sex'],
        'imgUrl'=>$row['picture_url']));
}

echo json_encode($result);
?>