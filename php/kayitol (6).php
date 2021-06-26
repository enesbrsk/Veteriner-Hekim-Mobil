<?php 

include("ayar.php");

$mailAdres = $_POST["mailAdres"];
$kadi = $_POST["kadi"];
$parola = $_POST["pass"];
$dogrulamaKodu = rand(0,10000).rand(0,10000);
$durum = 1;

$control = mysqli_query($baglan,"select * from veteriner_kullaniciler where mailAdres = '$mailAdres' or kadi ='$kadi'");
$count = mysqli_num_rows($control);


class User{
	public $text ;
	public $tf;
	
}
$user = new User();



if($count >0)
{
	$user ->text = "Girmiş olduğunuz bilgilere ait kullanıcı bulunmaktadır. Lütfen bilgilerinizi değiştiriniz..!";
	$user->tf=false;
	echo(json_encode($user));
}
else
{	
$addUser = mysqli_query($baglan,"insert into veteriner_kullaniciler(kadi,mailAdres,parola,dogrulamaKodu,durum) 
values ('$kadi','$mailAdres','$parola','$dogrulamaKodu','$durum')");
	
	$git = "www.veterineruygulamasi.xyz/veterinerservis/aktifet.php?mail=".$mailAdres."&dogrulamakodu=".$dogrulamaKodu."";
	
	$to = $mailAdres;
	$subject = "Kullanici Hesabi Aktiflestirme";
	$text = "Merhaba Sayin  ".$kadi. "\n Sisteme giris yapabilmeniz icin onayiniz gerekmektedir.
	<a href='".$git."' > Onayla </a>";
	
	$from = "From: info@kocveteriner.com";
	$from .="MIME-Version: 1.0\r\n";
	$from .="Content-Type : text/html; charset=UFT-8\r\n";
	mail($to,$subject,$text,$from);
	

	
		$user ->text = "Hesabınız kaydedildi, lütfen mail adresinizden doğrulama işlemi gerçekleştiriniz..!";
	$user->tf=true;
	echo(json_encode($user));
}



?>