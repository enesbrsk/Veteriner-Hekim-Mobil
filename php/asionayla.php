<?php
include("ayar.php");
$id = $_POST["id"];
$onayla = mysqli_query($baglan,"update veteriner_takipasi set asi_durum = 1 where id ='$id' ");

class Result
{
    public $text;
    public $tf;
}

$result = new Result();
$result ->text= "Aşı Yapılmıştır..!";
$result -> tf = true;

echo(json_encode($result));


?>