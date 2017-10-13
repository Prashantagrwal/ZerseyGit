<?php

require_once('Config_Zersey.php');

$link=mysqli_connect(HOST,USER,PASSWORD,Database);


$check=$_GET['check'];




if($check=='1'){

$google_id=$_GET['google_id'];
$cate=$_GET['category'];
$title=$_GET['title'];
$desp=$_GET['description'];
$event_date=$_GET['event_date'];
$event_time=$_GET['event_time'];
$image_url=$_GET['image_url'];
$video_url=$_GET['video_url'];
$event_id=$_GET['event_id'];

$qr1="insert into event_detail values('$google_id','$cate','$title','$desp','$event_date','$event_time','$image_url','$video_url','$event_id')";

$result= mysqli_query($link,$qr1);

//$result=Blogic::execute_query($qr);

if(mysqli_affected_rows($link))
{
        $response["success"]=1;
        $response["message"]="event added successfully";
    }
}
else if($check=='2'){
   $qr1="select * from event_detail"; 
    $result= mysqli_query($link,$qr1);

if(mysqli_affected_rows($link))
{ 
    $response["success"]=1;
    $response['event']=array();
   
   while($row=mysqli_fetch_array($result))
    {
        
        $response1["google_id"]=$row[0];
        $response1["category"]=$row[1];
        $response1["title"]=$row[2];
        $response1["description"]=$row[3];
        $response1["event_date"]=$row[4];
        $response1["event_time"]=$row[5];
        $response1["image_url"]=$row[6];
        $response1["video_url"]=$row[7];
        $response1["event_id"]=$row[8];
        array_push($response['event'],$response1);
        }
    
}}
else if($check=='3'){
    $google_id=$_GET['google_id'];
    $qr1="select * from event_detail where google_id='$google_id'"; 
    $result= mysqli_query($link,$qr1);

if(mysqli_affected_rows($link))
{   $response["success"]=1;
    $response['event']=array();
    while($row=mysqli_fetch_array($result))
    {   
        
        $response1["google_id"]=$row[0];
        $response1["category"]=$row[1];
        $response1["title"]=$row[2];
        $response1["description"]=$row[3];
        $response1["event_date"]=$row[4];
        $response1["event_time"]=$row[5];
        $response1["image_url"]=$row[6];
        $response1["video_url"]=$row[7];
        $response1["event_id"]=$row[8];
        array_push($response['event'],$response1);
        }
    
}}
else if($check=='4')
{
    $image_url=$_GET['image_url'];
    $event_id=$_GET['event_id'];
    $update ="update event_detail set image_url='$image_url' where event_id='$event_id'";
    $link=mysqli_query($link,$update);
}
else if($check=='5')
{
    $video_url=$_GET['video_url'];
    $event_id=$_GET['event_id'];
    $update ="update event_detail set video_url='$video_url' where event_id='$event_id'";
    $link=mysqli_query($link,$update);
}
    if($check!=4 && $check!=5)
       { echo json_encode($response);}

?>