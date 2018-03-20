<?php
$book_name=$_GET["p"];
$servername = "localhost";
$username = "koha_mykoha";
$password = "sL925avEhO6utH1V";
$dbname="koha_mykoha";
$con = new mysqli($servername, $username, $password, $dbname);
if ($con->connect_error){
   echo $con->error;

}


$sql="select * from  items it,biblio bb,rfid r where r.barcode=it.barcode and bb.biblionumber=it.biblionumber and title='".$book_name."'";

$result=$con->query($sql);
$rf="";
while($row=$result->fetch_assoc())
{

$rf=$row["rid"];
$sql2="insert into search values('".$rf."')";

$con->query($sql2);

}


?>
