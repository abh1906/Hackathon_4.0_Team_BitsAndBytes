<?php


$servername = "localhost";
$username = "koha_mykoha";
$password = "sL925avEhO6utH1V";
$dbname="koha_mykoha";
$con = new mysqli($servername, $username, $password, $dbname);
if ($con->connect_error){
   echo $con->error;

}

$title="";
$sql="SELECT * from biblio b,biblioitems bb where b.biblionumber=bb.biblionumber";
$result=$con->query($sql);
while($row=$result->fetch_assoc())
  {     $title=$title.",".$row["title"];
  
  
}
echo $title;

?>


