<!DOCTYPE html>
<html lang="en">

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

        <section id="contact" class="bg-light">
          <div class="container">
            <div class="row">
              <div class="col-lg-8 mx-auto">
                <?php
                // POST method part
                  if(isset($_POST['contactName']))
                  {
                    try{
                      // Currently, we are writing to a file. Later modify the code to enable the php engine to send a email.
                      $fp = fopen( "temp/feedback.txt", "a" );
                      date_default_timezone_set('Asia/Kolkata');
                      $timestamp = date("Y-m-d H:i:s");
                      fputs( $fp, $_POST['contactName']."\tEncryption(".$_POST['contactEmail'].")\t".$_POST['contactFeedback']."\t".$timestamp."\n" );
                      fclose( $fp );
                    }
                    catch(Exception $e)
                    {
                      echo "Error Writing to the file!";
                      exit(-1);
                    }
                  echo "<h2>Feedback sent successfully!</h2>";
                  }
                ?>
<form method="post" action="contact.php">
            <h3>Send us feedback</h3>
            <table border="0">
              <tr><td>Name</td><td>&nbsp;&nbsp;&nbsp;</td><td><input type="text" name="contactName" placeholder="Enter your Name" /></td></tr>
              <tr><td></td><td></td><td></td></tr>
              <tr><td>Email Address</td><td>&nbsp;&nbsp;&nbsp;</td><td><input type="email" name="contactEmail" placeholder="Enter your Email Address" /></td></tr>
              <tr><td></td><td></td><td></td></tr>
              <tr><td>Feedback</td><td>&nbsp;&nbsp;&nbsp;</td><td><textarea name="contactFeedback" placeholder="Enter your Feedback"></textarea></td></tr>
              <tr><td></td><td></td><td></td></tr>
              <tr><td></td><td><input type="submit" value="Send" />&nbsp;&nbsp;&nbsp;<input type="reset" value="Clear" /></td><td></td></tr>
            </table>
<br/>
    <p>The feedback will sent to <strong><em>seat [AT] wmail [DOT] iitm [DOT] ac [DOT] in</em></strong>
</p>

</form>
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
