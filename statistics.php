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

<h1>My Web Page</h1>

<div id="piechart"></div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">
// Load google charts
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

// Draw the chart and set the chart values
function drawChart() {
  var data = google.visualization.arrayToDataTable([
  ['Course', 'Count'],
  ['HS',<?php echo findCount("HS",$_GET["sem"]) ?>],
  ['MA',<?php echo findCount("MA",$_GET["sem"]) ?>],
  ['OTHER COURSES',<?php echo findCount("others",$_GET["sem"]) ?>],

]);

  function selectHandler() {
      var selectedItem = chart.getSelection()[0];
	  a1="course.php?cname=HS&sem="+"<?php echo $_GET["sem"] ?>";
	  a2="course.php?cname=MA&sem="+"<?php echo $_GET["sem"] ?>";
	  a3="course.php?cname=others&sem="+"<?php echo $_GET["sem"] ?>";
      if (selectedItem) {
        var topping = data.getValue(selectedItem.row, 0);
      if(topping=='HS')
    	  window.location.assign(a1);
    	  else if(topping=='MA')
    		  window.location.assign(a2);
    		  else
    			  window.location.assign(a3);

      }
    }

  // Optional; add a title and set the width and height of the chart
  var options = {'title':"<?php echo $_GET["sem"] ?>", 'width':650, 'height':400};

  // Display the chart inside the <div> element with id="piechart"
  var chart = new google.visualization.PieChart(document.getElementById('piechart'));



    google.visualization.events.addListener(chart, 'select', selectHandler);
  chart.draw(data, options);
}
</script>


<?php
function findCount($cname,$sem)
{

$studentrollnumber=[];
$courseid=[];
$i=0;

$c2=0;

$row = 0;
if (($handle = fopen("$sem.csv", "r")) !== FALSE)
{
    while (($data = fgetcsv($handle, 1000, ",")) !== FALSE)
	{
        $num = count($data);
        $row++;
        if($row===1)
        continue;
        $studentrollnumber[$c2]=$data[0];
		$courseid[$c2]=$data[1];
		$c2=$c2+1;

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
	  $count++;
	}
	else
	{
	   if(strpos($courseid[$i],$cname)===0)
         $count++;
	}

}

return $count;
}


?>
<a href="links.php">Click here to view other semesters(or rounds) statistics</a>

</body>
</html>
