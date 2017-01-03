<?php 
    include_once("Conexion.php");
    include_once("Security.php");
    include_once("Tarea.php");

    class Trabajador 
    {    	
    	var $trabajador_asignado;    	
    	var $username;
    	var $password_hash; 
    	var $tareas;  	
    	
    	function Trabajador(){}

    	function iniciarSesion($correo,$contrasenia){

			$sql ="SELECT asignacion_solicitud.fecha_hora_inicio as fecha_inicio_tarea,solicitud.description as tarea, estado.nombre as estado,e.nombre as estado_asignacion, servicio.nombre_servicio as servicio_a_prestar, user.nombre_completo as trabajador_asignado,edificio.nombre_edificio edificio,solicitud.numero_piso as piso,espacio.nombre as espacio, espacio.codigo as numero, u.nombre_completo as persona_que_envio_solicitud, dependencia.nombre_dependencia as dependencia,user.email as correo,user.password_hash,asignacion_solicitud.asignacion_id,solicitud.id as solicitud_id,asignacion_solicitud.equipo_reparado,asignacion_solicitud.numero_inventario,asignacion_solicitud.observaciones,asignacion_solicitud.estado_id

				FROM asignacion_solicitud
				INNER JOIN user 
				ON asignacion_solicitud.usuario_id=user.id
				INNER JOIN solicitud 
				ON asignacion_solicitud.solicitud_id=solicitud.id
				INNER JOIN espacio
				ON solicitud.espacio_id=espacio.espacio_id
				INNER JOIN servicio
				ON solicitud.servicio_id=servicio.id
				INNER JOIN dependencia
				ON solicitud.dependencia_id=dependencia.id
				INNER JOIN edificio
				ON espacio.edificio_id=edificio.edificio_id
				INNER JOIN estado
				ON solicitud.estado_id=estado.id
				INNER JOIN estado e
				ON asignacion_solicitud.estado_id=e.id
				INNER JOIN user u
				ON solicitud.user_id=u.id
				ORDER BY  asignacion_solicitud.fecha_hora_inicio";

				$conexion = new Conexion();
				$res = $conexion->ejecutar($sql);
                
                $tarea = null;
                $tareas = array();
                $trabajador = null;
                $security = new Security();	 
			 	
				while($row = $conexion->getArray($res)){

                    if ($correo==$row[12] && $security->validatePassword($contrasenia,$row[13])) {                    
						$tarea = new  Tarea();
						$trabajador = new Trabajador();

						$tarea->fecha_inicio_tarea = $row[0];
						$tarea->tarea = $row[1];
						$tarea->estado = $row[2];
						$tarea->estado_asignacion = $row[3];
						$tarea->servicio_a_prestar = $row[4];
						$trabajador->trabajador_asignado = $row[5];
						$tarea->edificio = $row[6];
						$tarea->piso = $row[7];
						$tarea->espacio = $row[8];
						$tarea->numero = $row[9];
						$tarea->persona_que_envio_solicitud = $row[10];
						$tarea->dependencia = $row[11];	
						$trabajador->username = $row[12];
						$trabajador->password_hash = $contrasenia;
						$tarea->asignacion_id = $row[14];
						$tarea->solicitud_id = $row[15];
						$tarea->equipo_reparado = $row[16];
						$tarea->numero_inventario = $row[17];
						$tarea->observaciones = $row[18];
						$tarea->estado_id = $row[19];

						
						//$trabajador->password = "Por seguridad no se Puede mostrar la contrasenia";
						array_push($tareas, $tarea);
					}
				}
                if (!is_null($trabajador)) {
                	 $trabajador->tareas = $tareas;
                }
               
				return $trabajador;			
		}

		function registrarTarea($asignacion_id,$solicitud_id,$estado_id,$equipo_reparado,$numero_inventaria,$observaciones,$fecha_hora_fin){
             
			$sql1 ="UPDATE  asignacion_solicitud SET estado_id=$estado_id,equipo_reparado='$equipo_reparado',numero_inventario=$numero_inventaria,observaciones='$observaciones',fecha_hora_fin='$fecha_hora_fin'  WHERE asignacion_id=$asignacion_id";
			echo $sql1."<br>";

			$sql2 ="UPDATE  solicitud SET estado_id=$estado_id WHERE id=$solicitud_id";

				$conexion = new Conexion();
				$res = $conexion->ejecutar($sql1);
				$res +=$conexion->ejecutar($sql2);
			 	
				if($res==2){
					return 1;				
				}else{
                     return 0;
				}				
        }
    }       

 ?>