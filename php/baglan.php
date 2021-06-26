<?php
try {
    $db = new PDO("mysql:host=92.204.220.76; dbname=veteriner; charest=utf8", 'z6ve02ga96hz', '159357Admin.');
      //echo 'veritabanı bağlantısı başarılı';
} catch (Exception $e) {
      echo $e ->getMessage();
} 





?>