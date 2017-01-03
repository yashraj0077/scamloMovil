<?php 
    include_once("Trabajador.php");
    $asignacion_id =$_GET['asignacion_id'];
    $solicitud_id = $_GET['solicitud_id'];
    $estado_id = $_GET['estado_id'];
    $equipo_reparado = $_GET['equipo_reparado'];
    $numero_inventario =$_GET['numero_inventario'];
    $observaciones = $_GET['observaciones'];

    $time = time();
    date_default_timezone_set('America/Bogota');    
    $fecha_hora_fin = date('Y-m-d (H:i:s)', $time);

    $trabajador = new Trabajador();
    // echo json_encode($trabajador->iniciarSesion("pablo@hotmail.com","Univalle123456")); 
    echo json_encode($trabajador->registrarTarea($asignacion_id,$solicitud_id,$estado_id,$equipo_reparado,$numero_inventario,$observaciones,$fecha_hora_fin)); 

 ?>