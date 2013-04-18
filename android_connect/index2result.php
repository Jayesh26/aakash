<?php

$MIS = $_POST['mis'];
$Bookid = $_POST['bookid'];
//$Copys = $_POST['copys'];
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
		}
		DELETE FROM `android`.`issue` WHERE `issue`.`mis` = 141103003 LIMIT 1

	*/
	$row = mysql_fetch_array(mysql_query("select copys from issue where mis = '$MIS' and bookid = '$Bookid' "));
	$copies = $row[0];
	$query = "delete from issue where copys = $copies";
	$r = mysql_query($query);
		if (!$r)
		{
			echo "NO or Entry duplicated";
        	}
        	else
        	{
			echo "Successfully Return";
        	
        		$row = mysql_fetch_array(mysql_query("select copys from products where bookid = '$Bookid' "));
			$copys = $row['copys'];
        		
		      	$query1 = "update products set copys = $copys + 1 where bookid = '$Bookid'";
        		mysql_query($query1);
        		$row = mysql_fetch_array(mysql_query("select nob from studentd where mis = '$MIS'"));
			$nob = $row['nob'];
			mysql_query("update studentd set nob = $nob - 1 where mis = '$MIS'");
	
        	}
			

?>
