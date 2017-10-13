<?php
require_once('Config_Zersey.php');

$link=mysqli_connect(HOST,USER,PASSWORD,Database);


$google_id=$_GET['google_id'];
$name=$_GET['name'];
$email=$_GET['email'];

$response=array();
$check=false;
$value=0;

$qr="select * from login where google_id='$google_id'";


$result= mysqli_query($link,$qr);

//$result=Blogic::execute_query($qr);

if(mysqli_affected_rows($link))
{
    if($result)
    {
        $response["success"]=1;
        $response["id"]=$row["id"];
        } 
    }
else
{
       $qr1="insert into login values('$google_id','$name','$email')";
     //  $result1=Blogic::execute_query($qr1);
      $result1= mysqli_query($link,$qr1);
    }
         $result3= mysqli_query($link,$qr);
      //  $result3=Blogic::execute_query($qr);   
        if($result3)
        {
    while($row=mysqli_fetch_array($result3))
    {
        $response["success"]=1;
        $response["id"]=$row["id"];
    
    }
    
    
    }
    else
    {
        $response["success"]=0;
        $response["message"]="Not Inserted";
    } 


 echo json_encode($response);
?>