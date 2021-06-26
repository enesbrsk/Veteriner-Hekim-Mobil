<?php 
include 'header.php';
include 'baglan.php';
include 'islem.php';
?>

<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <link rel="stylesheet" href="style.css">
    <title>Veteriner</title>
</head>
<body>

    <div class="randevu_div" id="randevual_div">
        <h3>Randevu Al</h3>

        <form action="islem.php" method="post">
    <input type="date" name="randevu_tarih" value="<?php echo date('Y-m-d'); ?>" />
    
 <select name="saat">
     

  <option value="8:00">8:00 </option>
  <option value="8:30">8:30</option>
  <option value="9:00">9:00</option>
  <option value="9:30">9:30</option>
  <option value="10:00">10:00 </option>
  <option value="10:30">10:30 </option>
  <option value="11:00">11:00 </option>
  <option value="11:30">11:30 </option>
  <option value="12:00">12:00</option>
  <option value="12:30">12:30 </option>
  <option value="13:00">13:00 </option>
  <option value="13:30">13:30 </option>
  <option value="14:00">14:00 </option>
  <option value="14:30">14:30 </option>
  <option value="15:00">15:00 </option>
  <option value="15:30">15:30 </option>
  <option value="16:00">16:00 </option>
  <option value="16:30">16:30 </option>
  <option value="17:00">17:00 </option>
 
</select> 

<select name="doktor" class="doktor">
<option value="doktor">Doktor Seçin</option>
<option value="Merve Ozcan">Merve Özcan</option>
<option value="Enes Birisik">Enes Birişik</option>
<option value="Sena Cesme">Sena Çeşme</option>

</select>


<input type="hidden" name="id" value="<?php echo $kullanicicek['id']; ?>">
<button name="randevu_kaydet">Randevu Kaydet</button>
</form>
</div>
<div class="randevu_liste" id="randevu_liste">
    <h3>Randevularım</h3>
     <table>
         <tr>
             <th>Tarih</th>
             <th>Saat</th>
             <th>Hekim</th>
         </tr>
            <?php
            $randevu_sor=$db->prepare("SELECT * FROM veteriner_randevu 
            INNER JOIN veteriner_kullaniciler ON veteriner_randevu.mus_id=veteriner_kullaniciler.id WHERE mailAdres=:mailAdres");
            $randevu_sor -> execute([
                'mailAdres'=> $_SESSION['userkullanici_mail'] 
                ] );
            while ($randevu_cek=$randevu_sor->fetch(PDO::FETCH_ASSOC)){ ?>
            <tr>
                
                <td><?php echo $randevu_cek['randevu_tarih'];?></td>
                <td><?php echo $randevu_cek['randevu_saat'];?></td>
                <td><?php echo $randevu_cek['hekim']; ?></td>
                
            
            </tr>
            <?php } ?>
        </table>
</div>
</body>
</html>