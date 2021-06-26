<?php
include("ayar.php");
$id = $_POST["id"];
$onayla = mysqli_query($baglan,"delete from veteriner_takipasi where id ='$id' ");

class Result
{
    public $text;
    public $tf;
}

$result = new Result();
$result ->text= "Aşı İptal Edildi..!";
$result -> tf = true;

echo(json_encode($result));


?>