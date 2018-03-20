<?php
$servername = "localhost";
$username = "koha_mykoha";
$password = "sL925avEhO6utH1V";
$dbname="koha_mykoha";
$con = new mysqli($servername, $username, $password, $dbname);
if ($con->connect_error){
   echo $con->error;

}

$sql="select * from search ";

$result=$con->query($sql);

$res="*";

while($row=$result->fetch_assoc()){

$res=$res.",".$row["rfid"];
}

echo $res.",";


?>
