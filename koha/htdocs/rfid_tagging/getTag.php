
<?php

$servername = "localhost";
$username = "koha_mykoha";
$password = "sL925avEhO6utH1V";
$dbname="koha_mykoha";
$con = new mysqli($servername, $username, $password, $dbname);
if ($con->connect_error){
   echo $con->error;

}

$sql ="select rfid from get_tag";
$result=$con->query($sql);
if($row=$result->fetch_assoc())

echo $row["rfid"];


echo "anand";


?>

