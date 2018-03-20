<?php

require_once('connection.php');


$rfid=$_GET["p"];


$sql="select rid from rfid r,items it ,issues i where i.itemnumber=it.itemnumber and it.barcode=r.barcode and rid='".$rfid."'";



$result=$con->query($sql);
if($row=$result->fetch_assoc())
{
	echo "#".$row["rid"];
}
else{
	echo "#"."no";
}
?>

