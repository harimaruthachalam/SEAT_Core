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
	<style type="text/css">
		svg > g > g:last-child { pointer-events: none }
		
		
	</style>
  </head>

<body id="page-top">
<?php include 'header.php' ?>


     <section id="intro" class="bg-light">
       <div class="container">
         <div class="row">
           <div class="col-lg-8 mx-auto">
             <h3><?php echo getTitle(); ?></h3>


<table border="0" cellspacing="60">
<tr>
<td><div id="piechart"></div></td>
<td>
<table align="right" border="0">
<?php
$sems=array("Jan-May 2018 Round 1","Jan-May 2018 Round 2","July-Nov 2017 Round 1","July-Nov 2017 Round 2","Jan-May 2017","July-Nov 2016");
$arrlength = count($sems);
  echo "<br>";
    echo "<br>";
	  echo "<br>";

for($x = 0; $x < $arrlength; $x++)
{
if($sems[$x]===$_GET["sem"])
{
	
 echo "<tr><td>$sems[$x]</td></tr>";
}
else
{
?>
<tr>
<td><a href="statistics.php?sem=<?php echo $sems[$x] ?>"><?php echo $sems[$x] ?></a></td>
</tr>
<?php
}
}
?>
</table>
</td>
</tr>
</table>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">
// Load google charts
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

// Draw the chart and set the chart values
function drawChart() {
  var data = google.visualization.arrayToDataTable([
  ['Branch', 'Count'],
  ['AE',<?php echo findCount($_GET["cname"],$_GET["sem"],"AE") ?>],


  ['BE',<?php echo findCount($_GET["cname"],$_GET["sem"],"BE") ?>],


  ['BS',<?php echo findCount($_GET["cname"],$_GET["sem"],"BS") ?>],


  ['CE',<?php echo findCount($_GET["cname"],$_GET["sem"],"CE") ?>],


  ['CH',<?php echo findCount($_GET["cname"],$_GET["sem"],"CH") ?>],


  ['CS',<?php echo findCount($_GET["cname"],$_GET["sem"],"CS") ?>],




  ['EE',<?php echo findCount($_GET["cname"],$_GET["sem"],"EE") ?>],


  ['EP',<?php echo findCount($_GET["cname"],$_GET["sem"],"EP") ?>],




  ['MM',<?php echo findCount($_GET["cname"],$_GET["sem"],"MM") ?>],


  ['NA',<?php echo findCount($_GET["cname"],$_GET["sem"],"NA") ?>],


  ['PH',<?php echo findCount($_GET["cname"],$_GET["sem"],"PH") ?>]

]);

  function selectHandler() {
      var selectedItem = chart.getSelection()[0];
      if (selectedItem) {
        var topping = data.getValue(selectedItem.row, 0);

        if(topping=="AE")
        alert('<?php echo getDetails($_GET["cname"],$_GET["sem"],"AE") ?>');
	    if(topping=="BE")
        alert('<?php echo getDetails($_GET["cname"],$_GET["sem"],"BE") ?>');
	   if(topping=="BS")
        alert('<?php echo getDetails($_GET["cname"],$_GET["sem"],"BS") ?>');
	if(topping=="CE")
        alert('<?php echo getDetails($_GET["cname"],$_GET["sem"],"CE") ?>');
	if(topping=="CH")
        alert('<?php echo getDetails($_GET["cname"],$_GET["sem"],"CH") ?>');
	if(topping=="CS")
        alert('<?php echo getDetails($_GET["cname"],$_GET["sem"],"CS") ?>');
	if(topping=="EE")
        alert('<?php echo getDetails($_GET["cname"],$_GET["sem"],"EE") ?>');
	if(topping=="EP")
        alert('<?php echo getDetails($_GET["cname"],$_GET["sem"],"EP") ?>');
	if(topping=="MM")
        alert('<?php echo getDetails($_GET["cname"],$_GET["sem"],"MM") ?>');
	if(topping=="NA")
        alert('<?php echo getDetails($_GET["cname"],$_GET["sem"],"NA") ?>');
	if(topping=="PH")
        alert('<?php echo getDetails($_GET["cname"],$_GET["sem"],"PH") ?>');



        }
    }

  // Optional; add a title and set the width and height of the chart
  var options = {'title':null, 'width':550, 'height':500, fontSize:16};

  // Display the chart inside the <div> element with id="piechart"
  var chart = new google.visualization.PieChart(document.getElementById('piechart'));



    google.visualization.events.addListener(chart, 'select', selectHandler);
  chart.draw(data, options);
}
</script>

<?php
function findCount($cname,$sem,$branch)
{

$studentrollnumber=[];
$courseid=[];
$i=0;
$c2=0;
$row = 0;
if (($handle = fopen("$sem.csv", "r")) !== FALSE) {
    while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {
        $num = count($data);

        $row++;
        if($row===1)
        continue;
        $studentrollnumber[$c2]=$data[0];
		$courseid[$c2++]=$data[1];

    }
    fclose($handle);
}


$count=0;
for($i=0;$i<$c2;$i++)
{
	if(strcmp($cname,'others')===0)
	{
	  if(strpos($courseid[$i],'HS')===0 or strpos($courseid[$i],'MA')===0)
	  continue;
	  else
	  {
		  if(strpos($studentrollnumber[$i],$branch)===0)
			  $count++;
	  }

	}
	else
	{
	   if(strpos($courseid[$i],$cname)===0 and strpos($studentrollnumber[$i],$branch)===0)
         $count++;
	}

}


return $count;
}

function getTitle()
{
	return $_GET["sem"]." for ".$_GET["cname"];
}






function getDetails($cname,$sem,$branch)
{

$studentrollnumber=[];
$courseid=[];
$i=0;
$c1=1;
$c2=0;
$row = 0;
if (($handle = fopen("$sem.csv", "r")) !== FALSE) {
    while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {
        $num = count($data);

        $row++;
        if($row===1)
        continue;
        $studentrollnumber[$c2]=$data[0];
		$courseid[$c2++]=$data[1];

    }
    fclose($handle);
}
$count=0;
$count15=0;
$count16=0;
$count17=0;
for($i=0;$i<$c2;$i++)
{
	if(strcmp($cname,'others')===0)
	{
	  if(strpos($courseid[$i],'HS')===0 or strpos($courseid[$i],'MA')===0)
	  continue;
	  else
	  {
		  if(strpos($studentrollnumber[$i],$branch)===0)
			  $count++;
		  if(strpos($studentrollnumber[$i],$branch."15")===0)
			  $count15++;
		  if(strpos($studentrollnumber[$i],$branch."16")===0)
			  $count16++;
		  if(strpos($studentrollnumber[$i],$branch."17")===0)
			  $count17++;
	  }

	}
	else
	{
	   if(strpos($courseid[$i],$cname)===0 and strpos($studentrollnumber[$i],$branch)===0)
         $count++;
       if(strpos($courseid[$i],$cname)===0 and strpos($studentrollnumber[$i],$branch."15")===0)
         $count15++;
	   if(strpos($courseid[$i],$cname)===0 and strpos($studentrollnumber[$i],$branch."16")===0)
         $count16++;
	 if(strpos($courseid[$i],$cname)===0 and strpos($studentrollnumber[$i],$branch."17")===0)
         $count17++;
	}

}
$ret="";
if($count!==0)
	$ret=$ret."Total count of $branch is $count\\n";
if($count15!==0)
	$ret=$ret."2015 batch count is $count15\\n";
if($count16!==0)
	$ret=$ret."2016 batch count is $count16\\n";
if($count17!==0)
	$ret=$ret."2017 batch count is $count17\\n";


return $ret;
}

?>


			</div>
        </div>
      </div>
    </section>

<?php include 'footer.php' ?>


</body>
</html>
