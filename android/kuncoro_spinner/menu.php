<?php 
	/* ===== www.dedykuncoro.com ===== */
	include "koneksi.php";
	
	$query = mysql_query("SELECT * FROM menu ORDER BY id ASC");
	
	$json = array();	
	
	while($row = mysql_fetch_assoc($query)){
		$json[] = $row;
	}
	
	echo json_encode($json);
	
	mysql_close($con);
	

	/*=================== KALAU PAKAI MYSQLI YANG ATAS SEMUA DI REMARK, TERUS YANG INI RI UNREMARK ========*/
	// include_once "koneksi.php";

	// $query = mysqli_query($con, "SELECT * FROM menu ORDER BY id ASC");
	
	// $json = array();	
	
	// while($row = mysqli_fetch_assoc($query)){
	// 	$json[] = $row;
	// }
	
	// echo json_encode($json);
	
	// mysqli_close($con);
?>