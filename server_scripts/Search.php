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

$zipcode = $_GET["zipcode"];
$breed = $_GET["breed"];
$sex = $_GET["sex"];
$age = $_GET["age"];
$size = $_GET["size"];

search($conn, $zipcode, $breed, $sex, $age, $size);

function search($conn, $zipcode, $breed, $sex, $age, $size)
{
    //Check if search criteria value exists in table
    $numZip = array_values(isExistZip($conn, $zipcode));
    $numBreed = array_values(isExistBreed($conn, $breed));
    $numSex = array_values(isExistSex($conn, $sex));
    $numAge = array_values(isExistAge($conn, $age));
    $numSize = array_values(isExistSize($conn, $size));
    echo("<br>");
    
    $nums = array($numZip, $numBreed, $numSex, $numAge, $numSize);
    $maxNum = max($nums);       //Find search criteria with the most matches
    
    //If a search criteria was left blank, fill with criteria with most matches
    if($numZip == null){$numZip = $maxNum;}
    if($numBreed == null){$numBreed = $maxNum;}
    if($numSex == null){$numSex = $maxNum;}
    if($numAge == null){$numAge = $maxNum;}
    if($numSize == null){$numSize = $maxNum;}
    
    //Compute intersection of matched criteria arrays 
    $searchResult = array_intersect($numZip, $numBreed, $numSex, $numAge, $numSize);
    var_dump($searchResult); 
    echo("<br>");
    
    //For debugging
    echo("<br>zipcode: ");
    echo json_encode(array_values($numZip));
    echo("<br>breed: ");
    echo json_encode(array_values($numBreed));
    echo("<br>sex: ");
    echo json_encode(array_values($numSex));
    echo("<br> age: ");
    echo json_encode(array_values($numAge));
    echo("<br>size: ");
    echo json_encode(array_values($numSize));
    echo("<br>");
    
    //Find and return breed, sex, age, size of search matches 
    $query = "SELECT breed, sex, age, size FROM cat_detail WHERE cat_id = ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("i", $searchResult[1]);
    $stmt->execute();
    $stmt->store_result();
    
    $stmt->bind_result($breed, $sex, $age, $size);
    $matches = array();
    while($stmt->fetch()){
        $matches[] = [$breed, $sex, $age, $size];
    }

    echo("<br>");
    echo json_encode($matches);
}

function isExistZip($cnn, $zipcode)
{
	$query = "SELECT cat_id FROM cat_info WHERE zipcode = ?";
	$stmt = $cnn->prepare($query);
	$stmt->bind_param("s", $zipcode);
    $stmt->execute();
    $stmt->store_result();
    $rowcount = $stmt->num_rows;
    //For debugging
    printf("Results matching zipcode %s:    %d <br>", $zipcode, $rowcount);

    $stmt->bind_result($cat_id);
    $matches = array();
    while($stmt->fetch()){
        $matches[] = $cat_id;
    }
    $stmt->free_result();
    
	return $matches;
}

function isExistBreed($cnn, $breed)
{
	$query = "SELECT cat_id FROM cat_detail WHERE breed = ?";
	$stmt = $cnn->prepare($query);
    $stmt->bind_param("s", $breed);
    $stmt->execute();
    $stmt->store_result();
    $rowcount = $stmt->num_rows;
    //For debugging
    printf("Results matching breed %s:    %d <br>", $breed, $rowcount);

    $stmt->bind_result($cat_id);
    $matches = array();
    while($stmt->fetch()){
        $matches[] = $cat_id;
    }
    $stmt->free_result();

	return $matches;
}

function isExistSex($cnn, $sex)
{
	$query = "SELECT cat_id FROM cat_detail WHERE sex = ?";
	$stmt = $cnn->prepare($query);
    $stmt->bind_param("s", $sex);
    $stmt->execute();
    $stmt->store_result();
    $rowcount = $stmt->num_rows;
    //For debugging
    printf("Results matching sex %s:    %d <br>", $sex, $rowcount);

    $stmt->bind_result($cat_id);
    $matches = array();
    while($stmt->fetch()){
        $matches[] = $cat_id;
    }
    $stmt->free_result();

	return $matches;
}

function isExistAge($cnn, $age)
{
	$query = "SELECT cat_id FROM cat_detail WHERE age = ?";
	$stmt = $cnn->prepare($query);
    $stmt->bind_param("i", $age);
    $stmt->execute();
    $stmt->store_result();
    $rowcount = $stmt->num_rows;
    //For debugging
    printf("Results matching age %s:    %d <br>", $age, $rowcount);

    $stmt->bind_result($cat_id);
    $matches = array();
    while($stmt->fetch()){
        $matches[] = $cat_id;
    }
    $stmt->free_result();

	return $matches;
}

function isExistSize($cnn, $size)
{
	$query = "SELECT cat_id FROM cat_detail WHERE size = ?";
	$stmt = $cnn->prepare($query);
    $stmt->bind_param("i", $size);
    $stmt->execute();
    $stmt->store_result();
    $rowcount = $stmt->num_rows;
    //For debugging
    printf("Results matching size %s:    %d <br>", $size, $rowcount);

    $stmt->bind_result($cat_id);
    $matches = array();
    while($stmt->fetch()){
        $matches[] = $cat_id;
    }
    $stmt->free_result();

	return $matches;
}

  mysqli_close($conn);

?>