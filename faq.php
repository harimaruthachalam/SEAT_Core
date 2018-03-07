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

</head>

<body id="page-top">

	<?php include 'header.php' ?>

	<section id="faq" class="bg-light">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 mx-auto">
					<h2>FAQ</h2>
					<div id="accordion" role="tablist">
						<div class="card">
							<div class="card-header" role="tab" id="headingOne">
								<h5 class="mb-0">
									<a class="collapsed" data-toggle="collapse"
										data-parent="#accordion" href="#collapseOne"
										aria-expanded="false" aria-controls="collapseOne"> For
										Faculty </a>
								</h5>
							</div>

							<div id="collapseOne" class="collapse" role="tabpanel"
								aria-labelledby="headingOne">
								<div class="card-block">
									<p class="text-primary">
										<a class="collapsed" data-toggle="collapse"
											data-parent="#accordion" href="#collapseOne1"
											aria-expanded="false" aria-controls="collapseOne1">How
											are course details made available to SEAT?</a>
									</p>
									<div id="collapseOne1" class="collapse" role="tabpanel"
										aria-labelledby="headingOne">
										SEAT gets course details from workflow. Departments send these
										details (course capacities, ranking criteria, etc) to workflow
										through the slotbook every semester.<br />
										<br />
									</div>
									<p class="text-primary"><a class="collapsed" data-toggle="collapse"
											data-parent="#accordion" href="#collapseOne2"
											aria-expanded="false" aria-controls="collapseOne2">What course details are relevant to
										SEAT?</a></p>
										<div id="collapseOne2" class="collapse" role="tabpanel"
										aria-labelledby="headingOne">
									For the purposes of SEAT, a course has two types of
									information:
									<ol>
										<li><u>Permanent Information:</u> This includes the
											course description, references, prerequisites etc. All this
											information is present in the Course Master. Modifying this
											information is done via approval through BAC and Senate.</li>
										<li><u>Information pertaining to the particular
												offering of the course:</u> This includes the following : is
											this course available for SEAT, course capacity for SEAT,
											Outside Capacity for SEAT, ranking criteria. These parameters
											are specific to the offering of the course and can change
											across offerings. All this information is uploaded to
											slotbook during slot-wise listing.</li>
									</ol>
									Both the pieces of information are crucial for SEAT. It is
									possible that for some courses the Permanent Information is not
									up to date. We encourage departments to get this information in
									place for the benefit of the students. However since changing
									Permanent information requires approvals, workflow has enabled
									a short-cut to updating course description and prerequisites
									for the current offering of the course. It should be noted that
									prerequisites entered using this shortcut will not be enforced,
									and are only for the students’ information.<br />
									<br />
									</div>
									<p class="text-primary"><a class="collapsed" data-toggle="collapse"
											data-parent="#accordion" href="#collapseOne3"
											aria-expanded="false" aria-controls="collapseOne3">What are the course details that
										need to be provided for SEAT for each offering?</a></p>
										<div id="collapseOne3" class="collapse" role="tabpanel"
										aria-labelledby="headingOne">
									During slotbook update, departments provide the following
									details for each course.
									<ol>
										<li>Is this course available for 2015 batch and later
											students as an elective course? (In case you choose to tick
											this, then please ensure that the prerequisites for the
											course are correctly entered in course master.)</li>
										<li>Total Capacity (how many students should SEAT allot
											to the course?)</li>
										<li>Outside Capacity (how many students from outside
											parent dept. should SEAT allot to the course?)</li>
										<li>Ranking Criteria (what ranking criteria should be
											used if there are more students interested that the available
											capacity?</li>
									</ol>
									<br />
									<br />
									</div>
									<p class="text-primary"><a class="collapsed" data-toggle="collapse"
											data-parent="#accordion" href="#collapseOne4"
											aria-expanded="false" aria-controls="collapseOne4">What is the difference between
										Total Capacity (TOT CAP) and Outside Capacity (OUT CAP)?</a></p>
										<div id="collapseOne4" class="collapse" role="tabpanel"
										aria-labelledby="headingOne">
									The Total Capacity denotes the maximum number of students that
									SEAT will allot to the course. Note that only 2015 batch and
									later BTech / DD students fall under the purview of SEAT -- we
									call such students as under the purview of SEAT students. A
									course may also be credited by BTech/ DD students from 2014 and
									earlier batches and postgraduate students (MTech, MS, PhD). We
									call such students as outside the purview of SEAT students.
									Note that outside the purview of SEAT students are not
									accounted for in Total Capacity. Therefore, the Total Capacity
									should NOT be confused with Class Size (which is used for room
									allocation etc).<br /> The Outside Capacity denotes the number
									of external (outside department) students that SEAT can
									allocate to a course. For example, suppose a course CS2800 from
									the CSE department is available for outside department students
									and can accommodate 10 students from outside CSE. In this case
									the Outside Capacity for CS2800 is 10. Again, these 10 students
									are only from 2015 and later batches.<br /> Note that the Total
									Capacity includes Outside Capacity and Within Department
									Capacity, but does not include students who fall outside SEAT
									purview (BTech/ DD 2014 and earlier and All MTech, MS, and
									PhD).<br />
									<br /> <img src="images/totCap.png" width="700px"
										height="400px" />
										</div>
									<p class="text-primary"><a class="collapsed" data-toggle="collapse"
											data-parent="#accordion" href="#collapseOne5"
											aria-expanded="false" aria-controls="collapseOne5">How do I split my Class Size
										between students under purview of SEAT and outside purview of
										SEAT?</a></p>
										<div id="collapseOne5" class="collapse" role="tabpanel"
										aria-labelledby="headingOne">
									For this, we do not have a clear answer. We request departments
									to take a call based on historical data. Going forward, as a
									larger set of students fall under the purview of SEAT, we
									expect that the estimation will become easier.<br />
									<br />
									</div>
									<p class="text-primary"><a class="collapsed" data-toggle="collapse" data-parent="#accordion"
href="#collapseOne6" aria-expanded="false" aria-controls="collapseOne6">How do faculty members communicate
										extra details for this offering of the course?</a></p>

<div id="collapseOne6" class="collapse" role="tabpanel" aria-labelledby="headingOne">
									Once the slotbook is approved by the TTC Chair, individual
									faculty members can provide data pertaining to courses that
									they will be offering in the upcoming semester. They can fill
									these details by logging into workflow and navigating to
									Academics → Course Details; a sample screenshot is provided
									below. In this screen, individual course instructors can
									provide the following inputs. <img src="images/courseDet.png"
										width="700px" height="300px" /> The instructor can provide a
									brief description of the course (Proposed content) and the
									background knowledge expected (Prerequisite). Note that this
									information on background knowledge is only for students to
									read manually. <em>At this stage SEAT cannot use this
										information to validate whether students have the necessary
										prerequisites.</em> <br />
									<br />
									</div>
									<p class="text-primary"><a class="collapsed" data-toggle="collapse" data-parent="#accordion"
href="#collapseOne7" aria-expanded="false" aria-controls="collapseOne7">What about COT for courses?</a></p>
<div id="collapseOne7" class="collapse" role="tabpanel" aria-labelledby="headingOne">
									With this new system in place, COT will change its form --
									instead of students going around with paper forms, the COT is
									specified by the teacher indirectly. At the time of filling the
									slot-book, course instructors will provide course capacity and
									ranking criteria. Thus, instructors can control the number of
									students allocated to the course as well as the ranking
									criteria. Departments are requested to update the course master
									with appropriate prerequisites and course equivalences for all
									their courses through appropriate channels (via BAC, Senate
									etc). SEAT has essentially automated the manual task of
									instructors inspecting the student’s eligibility for the course
									and the task of selecting students from the pool of interested
									students.<br />
									<br />
									</div>
									<p class="text-primary"><a class="collapsed" data-toggle="collapse" data-parent="#accordion"
href="#collapseOne8" aria-expanded="false" aria-controls="collapseOne8">What are different ranking
										criteria?</a></p>
									<div id="collapseOne8" class="collapse" role="tabpanel" aria-labelledby="headingOne">
									Please see Question in Student FAQ (Reference to Page-5) for a
									detailed description of various ranking criteria. We expect the
									departments to provide us with ranking criteria for courses
									offered in each semester. The default is set to Stratified
									Random.<br />
									<br />
									</div>
									<p class="text-primary"><a class="collapsed" data-toggle="collapse" data-parent="#accordion"
href="#collapseOne9" aria-expanded="false" aria-controls="collapseOne9">How is ranking criteria different
										from pre-requisites?</a></p>
										<div id="collapseOne9" class="collapse" role="tabpanel" aria-labelledby="headingOne">
									Please see Question in Student FAQ<br />
									<br />
									</div>
								</div>
							</div>
						</div>
						<div class="card">
							<div class="card-header" role="tab" id="headingTwo">
								<h5 class="mb-0">
									<a class="collapsed" data-toggle="collapse"
										data-parent="#accordion" href="#collapseTwo"
										aria-expanded="false" aria-controls="collapseTwo"> For
										Students </a>
								</h5>
							</div>
							<div id="collapseTwo" class="collapse" role="tabpanel"
								aria-labelledby="headingTwo">
								<div class="card-block">
									<p class="text-primary"><a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo1"
aria-expanded="false" aria-controls="collapseTwo1">What are inputs to SEAT?</a></p>
<div id="collapseTwo1" class="collapse" role="tabpanel" aria-labelledby="headingTwo">
									SEAT uses workflow to get its input from students. Every
									student who wishes to credit electives in a particular
									semester, should submit the following:
									<ol>
										<li>The maximum number of credits (including both
											elective and core credits, default is 60) he/she wants to
											register for in the particular semester, and</li>
										<li>An exhaustive list of courses in an order of
											preference.</li>
										<li>A colour for each course</li>
									</ol>
									<br />
									<br />
									</div>
									<p class="text-primary"><a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo2"
aria-expanded="false" aria-controls="collapseTwo2">How do I know what courses I am
										eligible to take?</a></p>

<div id="collapseTwo2" class="collapse" role="tabpanel" aria-labelledby="headingTwo">
									Workflow shows you all the courses that are being offered
									across different departments that are available as electives.
									It is highly recommended that you ensure that you have the
									background knowledge (prerequisites) to credit the course. You
									can do this by checking the course details on the academic
									website, visiting the course home page and/or talking to the
									faculty offering the course.<br />
									<br />
									</div>
									<p class="text-primary"><a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo3"
aria-expanded="false" aria-controls="collapseTwo3">How do I form my preference list
										when I have several types of electives to credits like
										Humanities, Mathematics and others?</a></p>
								<div id="collapseTwo3" class="collapse" role="tabpanel" aria-labelledby="headingTwo">
									Once you have ensured that you meet the prerequisites for a set
									of courses, create a single list containing all the courses
									that you wish to give a preference for. Your preference list
									can contain multiple courses in the same slot. The SEAT
									software will ensure that you do not have any slot conflicts.
									Next, you have to colour code the courses in your preference
									list.<br />
									<br />
									</div>
									<p class="text-primary"><a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo4"
aria-expanded="false" aria-controls="collapseTwo4">What is a colour for a course in
										the student’s preference list?</a></p>
								<div id="collapseTwo4" class="collapse" role="tabpanel" aria-labelledby="headingTwo">
									Students may have a subset of courses in their preference list
									from which they wish to pick only one. In that case, the
									student should give all the courses in this subset a single
									colour. There is no restriction on the number of courses that
									can be grouped under one colour, but it should be noted that
									every course can get at most 1 colour.<br /> For example, a
									student may have several courses from the Maths department in
									his preference list, but wants to be allotted at most one of
									them. He should colour all those courses in the same colour.<br />
									If you do not wish to give a course any colour, choose the “no
									colour” option . Among the courses that are with the “no colour
									option”, a student can get allotted multiple courses, subject
									to their credit limit.<br /> It should be noted that there is
									no need to colour courses in the same slot with a single
									colour. The SEAT software takes care of slot conflicts.<br />
									<br />
									</div>
									<p class="text-primary"><a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo5"
aria-expanded="false" aria-controls="collapseTwo5">What about COT (Consent of Teacher)
										for courses?</a></p>

<div id="collapseTwo5" class="collapse" role="tabpanel" aria-labelledby="headingTwo">
									There is no more paper COT for students starting from 2015-16
									batch. COT has been replaced with course prerequisites and
									course capacities declared for SEAT.<br />
									<br />
									</div>
									<p class="text-primary"><a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo6"
aria-expanded="false" aria-controls="collapseTwo6">What are the different ranking
										criteria for courses to rank students?</a></p>
										<div id="collapseTwo6" class="collapse" role="tabpanel" aria-labelledby="headingTwo">
									Every course has a ranking criteria associated with it. Types
									of ranking criteria currently available in SEAT are:
									<ol>
										<li><b>Stratified Random:</b> The course with stratified
											random as their ranking criteria will prioritize all students
											who gave this course as a top-choice over any student who
											gave it as a second choice, and so on. Amongst all students
											who gave the course as a top choice, a random order of
											ranking is used.</li>
										<li><b>Random:</b> The course with random as the ranking
											criteria will order all students interested in the course in
											a random order.</li>
										<li><b>CGPA:</b> The course with CGPA as their ranking
											criteria will compare students based on their CGPA till last
											semester. Students with higher CGPA will be given more
											preference over students with lower CGPA.</li>
									</ol>
									Consider the following example: say there are 5 students S1,
									S2, S3, S4, S5 who have listed course C in their preference
									list according to the table below:
									<table class="blueTable">
										<thead>
											<tr>
												<th></th>
												<th>S1</th>
												<th>S2</th>
												<th>S3</th>
												<th>S4</th>
												<th>S5</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Position of C in student preference order</td>
												<td>1</td>
												<td>1</td>
												<td>1</td>
												<td>2</td>
												<td>2</td>
											</tr>
											<tr>
												<td>CGPA of student</td>
												<td>7</td>
												<td>9.2</td>
												<td>8.5</td>
												<td>8.5</td>
												<td>6</td>
											</tr>
										</tbody>
									</table>
									Under <b>stratified random​</b> criterion, the first three
									students S1, S2, and S3 will be randomly permuted amongst
									themselves followed by a random permutation of the last two
									(i.e., S4 and S5). For example, S2, S1, S3, S5, S4 is a
									possible stratified random ordering, but S2, S3, S4, S1, S5 is
									not.<br /> Under <b>random</b> ​criterion, one of the 5!
									permutations of the five students will be chosen uniformly at
									random.<br /> Under <b>CGPA</b>​ criterion, one of (S2, S3, S4,
									S1, S5) and (S2, S4, S3, S1, S5) will be chosen uniformly at
									random. (In other words, ties are broken randomly.) It should
									be noted that the ranking order is an expression of instructors
									preference over students interested in the course. Since
									student preferences are also taken into consideration by SEAT,
									the top k students ranked by the course (where k is the
									capacity of the course) may not be allocated to the course. <br />
									<br />
									</div>
									<p class="text-primary"><a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo7"
aria-expanded="false" aria-controls="collapseTwo7">How is ranking criteria different
										from pre-requisites?</a></p>
									<div id="collapseTwo7" class="collapse" role="tabpanel" aria-labelledby="headingTwo">
									Pre-requisites are used to decide if a student is eligible for
									a given course. Ranking criteria on the other hand, like ‘CGPA’
									or ‘Random’ as discussed above, are used to rank the eligible
									students for allotment in the presence of limitations such as
									course capacities.<br />
									<br />
									</div>
									<p class="text-primary"><a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo8"
aria-expanded="false" aria-controls="collapseTwo8">What are some of the things
										students should keep in mind when filling preferences?</a></p>
									<div id="collapseTwo8" class="collapse" role="tabpanel" aria-labelledby="headingTwo">
									A student will provide his maximum credit limit (including core
									course credits). This maximum credit limit cannot exceed 60.
									The student will not be allotted more credits than the maximum
									credit limit, but it is possible that his credit limit will not
									be met if the student does not provide a long enough preference
									list or makes a mistake (like colouring all his courses with
									the same colour). In this case, the student is advised to apply
									for courses in the 2nd round. Hence a student should make his
									preference list sufficiently long.<br /> A student must
									responsibly choose the colours for courses in their preference
									list. If he or she makes a mistake like putting all courses in
									a single colour, or putting all HS courses in different
									colours, it will not be considered as an error, since it is a
									valid input.<br /> “No colour” option indicates that courses
									under that group are uncoloured, ie, any number of courses can
									be allotted from the set of courses with “no colour”.<br />
									<br />
									</div>
									<p class="text-primary"><a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo9"
aria-expanded="false" aria-controls="collapseTwo9">What happens to students who do not
										get allocated enough electives or are dissatisfied with some
										of their allotted courses?</a></p>
										<div id="collapseTwo9" class="collapse" role="tabpanel" aria-labelledby="headingTwo">
									The best way to avoid dissatisfaction is to do a thorough
									homework of the available courses and provide a well thought
									out and sufficiently long ordering of courses.<br /> If the
									student is not satisfied with the electives allocated to
									him/her, one can opt for dropping/replacing the course(s). The
									drop option is similar to the traditional drop, the student
									simply deregisters for the allocated elective and accepts that
									he/she does not want any other elective. The replace option is
									a 2-step process -- the first step is to drop the course. In
									the next step, the student can provide a fresh list of
									preferences, however only on the courses which have vacancies
									after the first allotment. Note that student is not guaranteed
									to get allotted to a course in the replace and might remain
									unallotted.<br />
									<br />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Footer -->
	<br><br><br><br><br><br><br><br><br>
	<footer class="py-1 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; SEAT |
				IITM 2018</p>
		</div>
		<!-- /.container -->
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Plugin JavaScript -->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom JavaScript for this theme -->
	<script src="js/scrolling-nav.js"></script>

</body>

</html>
