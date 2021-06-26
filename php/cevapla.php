<?php
include("ayar.php");

$musid=$_POST["musid"];
$soruid=$_POST["soruid"];
$text =$_POST["text"];

$ekle = mysqli_query($baglan, "insert into veteriner_cevaplar (mus_id,soru_id,cevap) values ('$musid','$soruid','$text')");

class Result
{
    public $text;
    public $tf;
}
$res = new Result();
if($ekle)
{
    $guncelle = mysqli_query($baglan,"update veteriner_sorular set durum = '1' where id = '$soruid'");
    $res ->text = "Soru Cevaplandı";
    $res ->tf = true;
    echo(json_encode($res));
}
else
{
     $res ->text = "Soru Cevaplanamadi..!";
    $res ->tf = false;
    echo(json_encode($res));
}



?>