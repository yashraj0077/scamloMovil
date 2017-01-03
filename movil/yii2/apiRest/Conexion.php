<?php
class Conexion{

	var $url = "localhost";
	var $conexion;
	var $usuario = "root";
	var $contrasenia = "mysql";
	var $bd = "scamlo";

	function Conexion(){
		$this->conexion = mysql_connect($this->url,$this->usuario,$this->contrasenia);
	    mysql_select_db($this->bd ,$this->conexion);

	}

	function ejecutar($sql){
		$result = mysql_query($sql,$this->conexion);
		return $result;

	}

	function getArray($res){
	    return mysql_fetch_array($res);
	}

}

?>
