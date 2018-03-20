
<?php
require_once('connection.php');

$sql ="select rfid from get_user_tag";
$result=$con->query($sql);
if($row=$result->fetch_assoc())

echo $row["rfid"];


?>


