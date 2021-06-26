<?php

include("ayar.php");
$id = $_POST["id"];
$sil = mysqli_query($baglan, " delete from veteriner_kampanyalar where id='$id'");

class Result
{
    public $text;
    public $tf;
    
}

$result = new Result();

if($sil)
{
  $result->text = "Kampanya Başarıyla Silindi..";
$result->tf = true;
echo(json_encode($result));
    
}
else
{
    $result->text = "Kampanya Silinemedi..!";
    $result->tf = false;
    echo(json_encode($result));

}



?>