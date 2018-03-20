<?php
$uid=$_GET["p"];
$servername = "localhost";
$username = "koha_mykoha";
$password = "sL925avEhO6utH1V";
$dbname="koha_mykoha";
$con = new mysqli($servername, $username, $password, $dbname);
if ($con->connect_error){
   echo $con->error;

}
$sql="select * from borrowers  where userid='".$uid."'";
$result=$con->query($sql);
if($row=$result->fetch_assoc())
  echo "yes";

else
  echo "no";
?>
