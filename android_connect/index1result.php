<?php

$MIS = $_POST['mis'];
$Bookid = $_POST['bookid'];
//$Date = $_POST['date'];

//echo $MIS . "<br>";
//echo $Bookid. "<br>";
//echo $Date. "<br>";
	$con = mysql_connect("localhost","root","");

		if (!$con)
		{
			die('Could not connect: ' . mysql_error());
		}
		mysql_select_db("android");

	/*	$result = mysql_query("SELECT * FROM products ");
		while($row = mysql_fetch_array($result))
		{
  			
			echo $row['pid'];
			echo $row['name'];
			echo $row['price'];
			echo $row['description'];
			 echo "<br>";
		}d
	*/
	$query = "select nob from studentd where mis = '$MIS'";
	$result = mysql_query($query);
	$row = mysql_fetch_array($result);

	$nob =  $row['nob'];
	if($nob < 2)
	{	

		$query = "INSERT INTO issue (mis, bookid, date)VALUES('$MIS','$Bookid',CURDATE())";
		$r = mysql_query($query);
		if (!$r)
		{
			echo "NO or Entry duplicated";
        	}
        	else
        	{
		//	mysql_query("insert into studentd(nob) values ('$copy') where mis = '$MIS'");

        		echo "Successfully issued";
        		

        	}
        	
        	$row = mysql_fetch_array(mysql_query("select copys from products where bookid = '$Bookid'"));
		$copies = $row['copys'];
		//echo $copies;
        	$query1 = "update products set copys = $copies - 1 where bookid = '$Bookid'";
        	mysql_query($query1);
        	$row = mysql_fetch_array(mysql_query("select nob from studentd where mis = '$MIS'"));
		$nob = $row['nob'];
		
		mysql_query("update studentd set nob = $nob + 1 where mis = '$MIS'");
	}
	else
	{
		echo "2 books are already issued.";
	}			

?>
