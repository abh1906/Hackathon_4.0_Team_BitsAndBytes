<?php
$user_id=$_GET["p"];
$servername = "localhost";
$username = "koha_mykoha";
$password = "sL925avEhO6utH1V";
$dbname="koha_mykoha";
$con = new mysqli($servername, $username, $password, $dbname);
if ($con->connect_error){
   echo $con->error;
}

$sql="SELECT * from issues i,borrowers b,biblio bb,items it where i.borrowernumber=b.borrowernumber and i.itemnumber=it.itemnumber and it.biblionumber=bb.biblionumber and userid='".$user_id."'";
$result=$con->query($sql);
$res="";
while($row=$result->fetch_assoc()){

$res=$res.",".$row["title"].","."Author :".",".$row["author"].","."Iss Date :".",".$row["issuedate"].","."Due Date :".",".$row["date_due"];

}if($res=="")
echo "0";
echo $res;
?>
