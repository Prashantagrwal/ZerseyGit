<?php
require_once('Config_Zersey.php');
class Blogic
{  
    function get_connection ()
  {
    $link=mysql_connect(HOST,USER,PASSWORD);
  if($link)
{   echo $link;
    return $link;
}
else
{
    return false;
}

}

function get_database()
{
    if(mysql_select_db(Database))
    {return true;}
    else{return false;}
}
   static function execute_query($qr)
   { 
    
    self::get_connection();
      self::get_database();
   $num=mysql_query($qr);
   return $num;
   }
   
}
  
?>