<?php
// Initialize the session
session_start();
error_reporting(0);
 
$msg = "";
 
// If upload button is clicked ...
if (isset($_POST['upload'])) {
 
    $filename = $_FILES["uploadfile"]["name"];
    $tempname = $_FILES["uploadfile"]["tmp_name"];
    $folder = "./uploads/" . $filename;
 
    $db = mysqli_connect("localhost", "root", "", "file_data");
    
   
    // Now let's move the uploaded file into the folder: uploads
    if (move_uploaded_file($tempname, $folder)) {
        echo "<br><h3 style='text-align:center; color:green;'>  File uploaded successfully!</h3>";
    } else {
        echo "<br><h3 style='color:red';>  Failed to upload file!</h3>";
    }
           
            if (isset($_POST['upload'])){
                $username = $_POST['username'];
            
                // Normal Password
                $pass = $_POST['password'];
            
                // Securing password using password_hash
                $secure_pass = password_hash($pass, PASSWORD_BCRYPT);
            }
            

    // Get all the submitted data from the form
   // $sql = "INSERT INTO user_data (filename) VALUES ('$filename')";
   $sql = "INSERT INTO mydata (filename, username, private_key) VALUES('$filename', '$username', '$secure_pass')";
 
    // Execute query
    mysqli_query($db, $sql);

    
}
// Check if the user is logged in, if not then redirect him to login page
if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    header("location: login.php");
    exit;
}
?>

 

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body{ font: 14px sans-serif; text-align: center; }
    </style>
    <link rel="stylesheet" type="text/css" href="style.css" />
    <style>
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            
            #content{
                width: 50%;
                justify-content: center;
                align-items: center;
                margin: 20px auto;
                border: 1px solid #cbcbcb;
            }
            form{
                width: 50%;
                margin: 20px auto;
            }
            
            /* #display-file{
                width: 100%;
                justify-content: center;
                padding: 5px;
                margin: 15px;
            } */
            img{
                margin: 5px;
                width: 350px;
                height: 250px;
            }
    </style>
</head>
<body>
    <h1 class="my-5">Hi, <b><?php echo htmlspecialchars($_SESSION["username"]); ?></b>. Welcome to our site.</h1>
    <div id="content">
        <form method="POST" action="" enctype="multipart/form-data">
            <div class="form-group form-outline mb-4">
                <input class="form-control" type="file" name="uploadfile" value="" />
            </div>
            <div class="form-group form-outline mb-4">
                <label for="username">Username</label>
                <input type="text" name="username" required><br><br>
                
                <label for="password">Enter 16-bit Key</label>
                <input type="password" name="password" required><br><br> 
            </div>
            



            <div class="form-group form-outline mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit" name="upload">UPLOAD</button>
            </div>
        </form>
    </div>
    <p>
        <a href="reset-password.php" class="btn btn-warning">Reset Your Password</a>
        <a href="logout.php" class="btn btn-danger ml-3">Sign Out of Your Account</a>
    </p>
</body>
</html>