<?php
 
/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();

 
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
 




 
// check for required fields
if (isset($_GET['bookname'])) {
 
    $name = $_GET['bookname'];
 
    $result = mysql_query("SELECT *FROM products WHERE bookname = '".$_GET['bookname']."'");
      
    if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["products"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $product = array();
        $product["bookid"] = $row["bookid"];
        $product["bookname"] = $row["bookname"];
        $product["copys"] = $row["copys"];
      
 	$product["description"] = $row["description"];
        // push single product into final response array
        array_push($response["products"], $product);
   }
    
        $response["success"] = 1;
        $response["message"] = "Product successfully created.";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
 
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>
