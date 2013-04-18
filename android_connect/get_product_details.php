<?php header('content-type: application/json; charset=utf-8');

/*
 * Following code will get single product details
 * A product is identified by product id (pid)
 */

define('DB_USER', "root"); // db user
define('DB_PASSWORD', ""); // db password (mention your db password here)
define('DB_DATABASE', "android"); // database name
define('DB_SERVER', "localhost"); // db server

 
 
        // Connecting to mysql database
   $con = mysql_connect(DB_SERVER, DB_USER, DB_PASSWORD) or die(mysql_error());
 
        // Selecing database
   $db = mysql_select_db(DB_DATABASE) or die(mysql_error()) or die(mysql_error());



// array for JSON response
$response = array();


// include db connect class
//require_once __DIR__ . '/db_connect.php';

// connecting to db
//$db = new DB_CONNECT();


// check for post data
if (isset($_POST['name'])) {
 //  $name = $_POST['name'];
   echo $_POST['name'];
    // get a product from products table
   $result = mysql_query("SELECT *FROM products WHERE name = '".$_POST['name']."'");
   // $result = mysql_query("SELECT *FROM products WHERE name = $name");
    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

   /*         $result = mysql_fetch_array($result);

            $product = array();
            $product["pid"] = $result["pid"];
            $product["name"] = $result["name"];
            $product["price"] = $result["price"];
            $product["description"] = $result["description"];
            $product["created_at"] = $result["created_at"];
            $product["updated_at"] = $result["updated_at"];
         */   // success
            $response["success"] = 1;
	    echo $response["success"];
            // user node
          //  $response["product"] = array();

           // array_push($response["product"], $product);

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
