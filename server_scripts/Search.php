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

//For debugging
printf("user searched zipcode: %s <br>", $zipcode);
printf("user searched breed: %s <br>", $breed);
printf("user searched sex: %s <br>", $sex);
printf("user searched age: %s <br>", $age);
printf("user searched size: %s <br>", $size);
echo("<br>");

search($conn, $zipcode, $breed, $sex, $age, $size);

function search($conn, $zipcode, $breed, $sex, $age, $size)
{
    //Check if search criteria value exists in table
    $numZip = array_values(isExistZip($conn, $zipcode));
    $numBreed = array_values(isExistBreed($conn, $breed));
    $numSex = array_values(isExistSex($conn, $sex));
    $numAge = array_values(isExistAge($conn, $age));
    $numSize = array_values(isExistSize($conn, $size));
    
    $nums = array($numZip, $numBreed, $numSex, $numAge, $numSize);
    //Find search criteria with the most matches
    $maxNum = max($nums);       
    
    //If a search criteria was left blank, it's results are replaced by the highest match count criteria's results
    if($numZip == null){$numZip = $maxNum;}
    if($numBreed == null){$numBreed = $maxNum;}
    if($numSex == null){$numSex = $maxNum;}
    if($numAge == null){$numAge = $maxNum;}
    if($numSize == null){$numSize = $maxNum;}
    
    //Compute intersection of matched criteria arrays 
    $searchResult = array_intersect($numZip, $numBreed, $numSex, $numAge, $numSize);
    
    //For debugging
    echo("<br>cat_ids matching zipcode: ");
    echo json_encode(array_values($numZip));
    echo("<br>cat_ids matching breed: ");
    echo json_encode(array_values($numBreed));
    echo("<br>cat_ids matching sex: ");
    echo json_encode(array_values($numSex));
    echo("<br>cat_ids matching age: ");
    echo json_encode(array_values($numAge));
    echo("<br>cat_ids matching size: ");
    echo json_encode(array_values($numSize));
    echo("<br>");
    echo("NOTE: any criteria that didn't return any matches is replaced by the highest match count criteria's results");
    echo("<br>");

    $matches1 = array();

     //Loop through cat_ids retrieved from array intersection
    foreach($searchResult as $value) {
        //Retrieve sex of cat_id matches 
        $query1 = "SELECT sex FROM cat_detail WHERE cat_id = ?";
        $stmt1 = $conn->prepare($query1);
        $stmt1->bind_param("i", $value);  
        $stmt1->execute();
        $stmt1->store_result();
    
        $stmt1->bind_result($sex);
        $matches = array();
        while($stmt1->fetch()){
            //Push matching sex criteria to first array
            $matches = array($value, $sex);
        }

        //Retrieve name and picture_url of cat_id matches
        $query2 = "SELECT name, picture_url FROM cat_info WHERE cat_id = ?";
        $stmt2 = $conn->prepare($query2);
        $stmt2->bind_param("i", $value);  
        $stmt2->execute();
        $stmt2->store_result();
    
        $stmt2->bind_result($name, $picture_url);
        while($stmt2->fetch()){
            //Push matching name and picture_url criteria to second array
            array_push($matches, $name, $picture_url);
        }
        //Combine cat_detail and cat_info query results arrays
        array_push($matches1, $matches);

    }
    echo("<br>");
    //Return json representation of matches array
    echo json_encode($matches1);
}

//Returns array of cat_ids with matching zipcode
function isExistZip($cnn, $zipcode)
{
	$query = "SELECT cat_id FROM cat_info WHERE zipcode = ?";
	$stmt = $cnn->prepare($query);
	$stmt->bind_param("s", $zipcode);
    $stmt->execute();
    $stmt->store_result();
    $rowcount = $stmt->num_rows;
    //For debugging
    printf("number of cats in zipcode %s:    %d <br>", $zipcode, $rowcount);

    $stmt->bind_result($cat_id);
    $matches = array();
    while($stmt->fetch()){
        $matches[] = $cat_id;
    }
    $stmt->free_result();
    
	return $matches;
}

//Returns array of cat_ids with matching breed
function isExistBreed($cnn, $breed)
{
	$query = "SELECT cat_id FROM cat_detail WHERE breed = ?";
	$stmt = $cnn->prepare($query);
    $stmt->bind_param("s", $breed);
    $stmt->execute();
    $stmt->store_result();
    $rowcount = $stmt->num_rows;
    //For debugging
    printf("number of cats with breed %s:    %d <br>", $breed, $rowcount);

    $stmt->bind_result($cat_id);
    $matches = array();
    while($stmt->fetch()){
        $matches[] = $cat_id;
    }
    $stmt->free_result();

	return $matches;
}

//Returns array of cat_ids with matching sex
function isExistSex($cnn, $sex)
{
	$query = "SELECT cat_id FROM cat_detail WHERE sex = ?";
	$stmt = $cnn->prepare($query);
    $stmt->bind_param("s", $sex);
    $stmt->execute();
    $stmt->store_result();
    $rowcount = $stmt->num_rows;
    //For debugging
    printf("number of cats with sex %s:    %d <br>", $sex, $rowcount);

    $stmt->bind_result($cat_id);
    $matches = array();
    while($stmt->fetch()){
        $matches[] = $cat_id;
    }
    $stmt->free_result();

	return $matches;
}

//Returns array of cat_ids with matching age
function isExistAge($cnn, $age)
{
	$query = "SELECT cat_id FROM cat_detail WHERE age = ?";
	$stmt = $cnn->prepare($query);
    $stmt->bind_param("i", $age);
    $stmt->execute();
    $stmt->store_result();
    $rowcount = $stmt->num_rows;
    //For debugging
    printf("number of cats with age %s:    %d <br>", $age, $rowcount);

    $stmt->bind_result($cat_id);
    $matches = array();
    while($stmt->fetch()){
        $matches[] = $cat_id;
    }
    $stmt->free_result();

	return $matches;
}

//Returns array of cat_ids with matching size
function isExistSize($cnn, $size)
{
	$query = "SELECT cat_id FROM cat_detail WHERE size = ?";
	$stmt = $cnn->prepare($query);
    $stmt->bind_param("i", $size);
    $stmt->execute();
    $stmt->store_result();
    $rowcount = $stmt->num_rows;
    //For debugging
    printf("number of cats of size %s:    %d <br>", $size, $rowcount);

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