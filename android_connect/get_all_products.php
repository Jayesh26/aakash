<?php

 
/*
 * All database connection variables
 */
 
define('DB_USER', "root"); // db user
define('DB_PASSWORD', ""); // db password (mention your db password here)
define('DB_DATABASE', "android"); // database name
define('DB_SERVER', "localhost"); // db server

 
 
        // Connecting to mysql database
   $con = mysql_connect(DB_SERVER, DB_USER, DB_PASSWORD) or die(mysql_error());
 
        // Selecing database
    $db = mysql_select_db(DB_DATABASE) or die(mysql_error()) or die(mysql_error());
 











 
/*
 * Following code will list all the products
 */
 
// array for JSON response
$response = array();
 

if (isset($_GET['name'])) {
 
    $name = $_GET['name'];
    $result = mysql_query("SELECT *FROM products WHERE name = '".$_GET['name']."'");
   
//$result = mysql_query("SELECT *FROM products") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["products"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $product = array();
        $product["pid"] = $row["pid"];
        $product["name"] = $row["name"];
        $product["price"] = $row["price"];
        $product["created_at"] = $row["created_at"];
        $product["updated_at"] = $row["updated_at"];
 
        // push single product into final response array
        array_push($response["products"], $product);
   }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No products found";
 
    // echo no users JSON
    echo json_encode($response);
}else {
	$response["success"] = 0;
    	$response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>
