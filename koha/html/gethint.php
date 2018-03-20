<?php
$rfid=$_GET["p"];

require_once('connection.php');


$sql="insert into get_tag values('".$rfid."')";

$con->query($sql);


?>
