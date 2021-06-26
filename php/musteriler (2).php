<?php  


include 'ayar.php'; 
$result = mysqli_query($baglan,"SELECT * FROM veteriner_kullaniciler where durum = '1' ");

?> 



<!DOCTYPE html> 

<html lang="tr"> 

<head> 

    <title>VeterinerUygulaması</title> 
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head> 

<body> 

    <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand " href="#">MSE Veteriner</a>
    </div>
    <ul class="nav navbar-nav pd-1">
      <li class="active "><a href="adminanasayfa.php">Ana Sayfa</a></li>
      <li class="active"><a href="webadminkampanya.php">Kampanyalar</a></li>
      <li class="active"><a href="webkampanya.php">Kampanya Paylaş</a></li>
      <li class="active"><a href="adminsorular.php">Sorular</a></li>
       <li class="active"><a href="musteriler.php">Müşteriler</a></li>
   
    </ul>
    <ul class="nav navbar-nav navbar-right">
     
      <li><a href="logout.php"><span class="glyphicon glyphicon-log-in"></span> Çıkış</a></li>
    </ul>
  </div>
</nav>
    <div class="container" id="adminrandevu_div">
  <h2>Müşteriler</h2>
  </br>
<div class="container">
         
  <table class="table table-dark table-hover">
    <thead>
      <tr>
        <th>Id</th>
        <th>Kullanıcı Adı</th>
        <th>Mail Adres</th>
        <th>Telefon</th>
        <th>Sil</th>
    
      </tr>
    <?php
	$i=0;
	while($row = mysqli_fetch_array($result)) {
	?>
	<tr class="<?php if(isset($classname)) echo $classname;?>">
	<td><?php echo $row["id"]; ?></td>
	<td><?php echo $row["kadi"]; ?></td>
		<td><?php echo $row["mailAdres"]; ?></td>
				<td><?php echo $row["telefon"]; ?></td>



	<td><a href="webmusterisil.php?id=<?php echo $row["id"]; ?>">Sil</a></td>

	</tr>
	<?php
	$i++;
	}
	?>
  </table>
</div>
</div>





</body> 

</html>