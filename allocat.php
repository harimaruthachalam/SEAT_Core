<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SEAT - Student Elective Allocation Tool</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/scrolling-nav.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="script.js"></script>
</head>

<body id="page-top">

	<?php include 'header.php' ?>

	<section id="faq" class="bg-light">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 mx-auto">
					<h2>Graph Matching Algorithms</h2>

					<form enctype="multipart/form-data" action="allocat.php" method="POST" class="form-horizontal">
					Select an Algorithm:
					<select id="algorithm" name="compute" onchange="optionChange(this)">
							<option selected="selected" value="0">--Select--</option>
							<option value="s">Stable matching</option>
							<option value="p">Maximum cardinality popular matching</option>
							<option value="m">Maximum cardinality matching </option>
							<option value="h">Matching in a given HRLQ instance</option>
						</select>
					<br/>
					<p>Who is proposing the algorithm?</p>
						<input type="radio" name="proposing" value="A" checked="checked">Resident proposing algorithm</input>
						<input type="radio" name="proposing" value="B">Hospital proposing algorithm</input>
						<br/>
					<div class="container text-center">
						<div style="border: 1px solid #a1a1a1;text-align: center;width: 500px;padding:30px;margin:0px auto">
								<div class="preview"></div>
								 <div class="progress" style="display:none">
									<div class="progress-bar" role="progressbar" aria-valuenow="0"
									aria-valuemin="0" aria-valuemax="100" style="width:0%">
										0%
									</div>
								</div>
								<input type="file" name="uploaded_file" class="form-control" /><br/>
								<button class="btn btn-primary upload-image">Upload File</button>
							</form>
						</div>

						<?php
						  if(!empty($_FILES['uploaded_file']))
						  {
								if ($_POST['compute'] == '0')
								{
									echo "There was an error processing your request, please try again!";
								}
								else {

						    $path = "temp/";
						    system('rm '.$path.'*');
						    $path = $path . basename( $_FILES['uploaded_file']['name']);
						    if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $path)) {
									$file = "temp/output.txt";
						      // $command = 'algo/GraphMatching/build/graphmatching -B -s -i ';
									if ($_POST['compute']=='0')
									throw new Exception("Error Processing Request", 1);

						      $command = 'algo/GraphMatching/build/graphmatching -'.$_POST['proposing'].' -'.$_POST['compute'].' -i ';
						      $command = $command.$path;
						      $command = $command.' -o ';
						      $command = $command.$file;
						      shell_exec($command);
									echo "<a href='download.php?name=".$file."'>Click Here to View the Output</a> <br />";
						      $fh = fopen('temp/output.txt','r');
						      while ($line = fgets($fh)) {
						        echo($line);
						        echo "<br/>";
						      }
						      fclose($fh);
						    } else{
						        echo "There was an error uploading the file, please try again!";
						    }
						  }
						}
						?>
				</div>
			</div>
		</div>
	</section>

	<!-- Footer -->
	<?php include 'footer.php' ?>

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Plugin JavaScript -->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom JavaScript for this theme -->
	<script src="js/scrolling-nav.js"></script>

</body>

</html>
