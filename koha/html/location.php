
<?php
$string=$_GET["p"];
$servername = "localhost";
$username = "koha_mykoha";
$password = "sL925avEhO6utH1V";
$dbname="koha_mykoha";
$con=new mysqli($servername, $username, $password, $dbname);
if ($con->connect_error)
   echo $con->error;


$array=explode(",",$string);
echo $string;

$sql2="select loc from ascii_to_loc where sno='".$array[1]."'";
$result=$con->query($sql2);
$loc="";
if($row=$result->fetch_assoc()){
$loc=$row["loc"];

}
$sql="update  rfid set location='".$loc."' where rid='".$array[0]."'";

$con->query($sql);



?>
