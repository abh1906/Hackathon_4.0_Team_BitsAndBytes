<?php
$author=$_GET["p"];
require_once('connection.php');
$book_name="";
$sql="SELECT * from biblio b,biblioitems bb where b.biblionumber=bb.biblionumber and author='".$author."'";
$result=$con->query($sql);
while($row=$result->fetch_assoc()){
$book_name=$book_name.$row["title"];
$book_name=$book_name.",";

echo $book_name;
}
else{


echo "no";
}

?>

