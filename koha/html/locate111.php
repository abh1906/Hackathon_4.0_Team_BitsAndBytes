<?php
$book_name=$_GET["p"];
$servername="localhost";

$dbname="koha_mykoha";

$username="koha_mykoha";

$password="sL925avEhO6utH1V";

$con=new mysqli($servername,$username,$password,$dbname);

if($con->connect_error)
 echo $con->error;


$sql="select * from items it,biblio b,rfid r where it.biblionumber=b.biblionumber and it.barcode=r.barcode and b.title='".$book_name."'";

$result=$con->query($sql);

$res="";
while($row=$result->fetch_assoc())

{

$res=$res.",". $row["location"].",".$row["rid"];

}

echo $res;

?>
