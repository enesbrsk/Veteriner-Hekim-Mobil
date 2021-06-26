<?php 

include("ayar.php");

$mailA = $_POST["mailadres"];
$sifre = $_POST["sifre"];

$control = mysqli_query($baglan,"select * from veteriner_kullaniciler where mailAdres = '$mailA' and parola='$sifre'");

$count = mysqli_num_rows($control);


class UserLogin
{
    public $id;
    public $username;
    public $mailadres;
    public $parola;
    public $tf;
    public $text;
}

$user = new UserLogin();


if($count>0){


    $parse = mysqli_fetch_assoc($control);
    $durum = $parse["durum"];
    $id = $parse["id"];
    $username = $parse["kadi"];
    $parola = $parse["parola"];
    $mailadres = $parse["mailAdres"];
      
    if($durum==0)
    {
        
    $user->tf=true;
    $user->text="Sisteme Giris basarili..";
    $user->id=$id;
    $user->parola=$parola;
    $user->username = $username;
    $user->mailadres = $mailadres;
    echo(json_encode($user));
	}
else{
         $user->tf=false;
    $user->text="Sisteme Giriş başarısız";
    $user->id=null;
    $user->parola=null;
    $user->username = null;
    $user->mailadres = null;
    echo(json_encode($user));
    }
  
    }
    else{
    $user->tf=false;
    $user->text="Sistemde girmiş olduğunuz kayıtlara göre kullanici bulunmamaktadir..";
    $user->id=null;
    $user->parola=null;
    $user->username = null;
    $user->mailadres = null;
    
    echo(json_encode($user));
}

?>