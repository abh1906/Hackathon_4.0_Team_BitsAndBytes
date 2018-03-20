<?php
$usr=$_GET["p"];
$servername = "localhost";
$username = "koha_mykoha";
$password = "sL925avEhO6utH1V";
$dbname="koha_mykoha";
$con = new mysqli($servername, $username, $password, $dbname);
if ($con->connect_error){
   echo $con->error;

}

$sql="select * from borrowers where userid='".$usr."'";
$result=$con->query($sql);
while($row=$result->fetch_assoc()){
$str=$row["cardnumber"].",".$row["firstname"].",".$row["surname"].",".$row["email"].",".$row["phone"].",".$row["initials"];

}
$sql2="select count(*) from  borrowers b ,issues i where b.borrowernumber=i.borrowernumber and b.userid='".$usr."'";

$result1=$con->query($sql2);
while($row2=$result1->fetch_assoc())

$str=$str.",".$row2["count(*)"];

echo $str;

?> 


