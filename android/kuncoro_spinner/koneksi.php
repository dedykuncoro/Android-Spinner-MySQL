<?php
	/* ===== www.dedykuncoro.com ===== */
	$server		= "localhost"; //sesuaikan dengan nama server
	$user		= "root"; //sesuaikan username
	$password	= "enter"; //sesuaikan password
	$database	= "kuncoro_spinner"; //sesuaikan target databese
	
	$con = mysql_connect($server, $user, $password) or die ("Koneksi gagal!");
	mysql_select_db($database) or die ("Database belum siap!");

	/* ====== UNTUK MENGGUNAKAN MYSQLI DI UNREMARK YANG INI, YANG MYSQL_CONNECT DI REMARK ======= */
	// $con = mysqli_connect($server, $user, $password, $database);
	// if (mysqli_connect_errno()) {
	// 	echo "Gagal terhubung MySQL: " . mysqli_connect_error();
	// }
?>