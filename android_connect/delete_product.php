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
 * Following code will delete a product from table
 * A product is identified by product id (pid)
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['pid'])) {
    $pid = $_POST['pid'];
 
    // include db connect class
//    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
  //  $db = new DB_CONNECT();
 
    // mysql update row with matched pid
    $result = mysql_query("DELETE FROM products WHERE pid = $pid");
 
    // check if row deleted or not
    if (mysql_affected_rows() > 0) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Product successfully deleted";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No product found";
 
        // echo no users JSON
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
