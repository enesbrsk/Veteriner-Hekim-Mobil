<?php

include("ayar.php");
$musid= $_POST["musid"];
$petisim = $_POST["isim"];
$tur = $_POST["tur"];
$cins = $_POST["cins"];
$resim = $_POST["resim"];

class Result
{
    public $text;
    public $tf;
}

$result = new Result();

$isim = rand(0,100000).rand(0,100000).rand(0,100000);

$yol = "resimler/$isim.jpg";
$resimurl = "http://veterineruygulamasi.xyz/veterinerservis/resimler/$isim.jpg";

$add = mysqli_query($baglan,"insert into veteriner_pet_listesi(mus_id,pet_ismi,pet_tur,pet_cins,pet_resim) values('$musid','$petisim','$tur','$cins','$resimurl')");

if($add)
{
    file_put_contents($yol,base64_decode($resim));
    
      $result->text = "Kullanıcıya Pet Eklendi!";
    $result->tf = true;
    echo(json_encode($result));
    
}else
{
    $result->text = "Kullanıcıya Pet Eklenemedi...!";
    $result->tf = false;
        echo(json_encode($result));

}



?>