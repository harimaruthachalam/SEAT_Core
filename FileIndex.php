<html lang="en">
<head>
    <title>File upload progress bar with percentage using form jquery example</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" >
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script> 
    <script src="http://malsup.github.com/jquery.form.js"></script> 
	<script src="script.js"></script> 
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid text-center">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Graph Matching Algorithms</a>
			</div>
		</div>		
	</nav>
	<div class="container">
	<form enctype="multipart/form-data" action="fileUploader.php" method="POST">
	Select an Algorithm:
	<select id="algorithm" name="compute" onchange="optionChange(this)">
		  <option selected="selected" value="0">--Select--</option>
		  <option value="s">Stable matching</option>
		  <option value="p">Maximum cardinality popular matching</option>
		  <option value="m">Maximum cardinality matching</option>
		  <option value="h">Matching in a given HRLQ instance</option>
		</select>
	<br/>
	<p>Who is proposing the algorithm?</p>
    <input type="radio" name="proposing" value="A" checked="checked">Resident proposing algorithm</input>
    <input type="radio" name="proposing" value="B">Hospital proposing algorithm</input>
    <br/>
	</div><br/>
	
	

	</div><br/>
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
	</div>
</body>
</html>