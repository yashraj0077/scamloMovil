<?php 
    include_once("Trabajador.php");
    $username =$_GET['username'];
    $pass = $_GET['password'];
    $trabajador = new Trabajador();
    // echo json_encode($trabajador->iniciarSesion("pablo@hotmail.com","Univalle123456")); 
    echo json_encode($trabajador->iniciarSesion($username,$pass));  

 ?>