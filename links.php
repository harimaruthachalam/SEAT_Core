<!DOCTYPE html>
<html lang="en-US">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SEAT - Student Elective Allocation Tool</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/scrolling-nav.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

  </head>
<body id="page-top">

 <?php include 'header.php' ?>

<?php
$sems=array("Jan-May 2018 Round 1","Jan-May 2018 Round 2","July-Nov 2017 Round 1","July-Nov 2017 Round 2","Jan-May 2017 HS-MA allotment","July-Nov 2016");
$arrlength = count($sems);
  echo "<br>";
    echo "<br>";
	  echo "<br>";

for($x = 0; $x < $arrlength; $x++)
{
?>

<a href="statistics.php?sem=<?php echo $sems[$x] ?>"><?php echo $sems[$x] ?></a>
<?php
    echo "<br>";
	echo "<br>";
}
?>

</body>
</html>
