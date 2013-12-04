<?php
    mysql_connect("sql3.freesqldatabase.com", "sql323706", "eK8%zV5*");
    mysql_select_db("sql323706");
    $q = mysql_query("SELECT * FROM promos WHERE id='".$_REQUEST['id']."';");
    while($e=mysql_fetch_assoc($q))
        $output[]=$e;
    print(json_encode($output));
    mysql_close();
?>