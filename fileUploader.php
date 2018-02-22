<!DOCTYPE html>
<html>
<head>
  <title>Upload your files</title>
</head>
<body>
  <form enctype="multipart/form-data" action="fileUploader.php" method="POST">
    <p>Upload your file</p>
    <input type="file" name="uploaded_file"></input><br />
    <input type="submit" value="Upload"></input>
  </form>
</body>
</html>
<?PHP
  if(!empty($_FILES['uploaded_file']))
  {
    $path = "temp/";
    system('rm '.$path.'*');
    $path = $path . basename( $_FILES['uploaded_file']['name']);
    if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $path)) {
      $command = '../../../../Desktop/GraphMatching/build/graphmatching -B -s -i ';
      $command = $command.$path;
      $command = $command.' -o ';
      $command = $command.'temp/output.txt';
      shell_exec($command);
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
?>
