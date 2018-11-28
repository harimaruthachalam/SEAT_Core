
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--
-- Table structure for table `tbl_batch_specific_recommended_elective`
--

DROP TABLE IF EXISTS `tbl_batch_specific_recommended_elective`;
CREATE TABLE IF NOT EXISTS `tbl_batch_specific_recommended_elective` (
  `batch` varchar(4) NOT NULL,
  `recommended_elective_type` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_course_list`
--

DROP TABLE IF EXISTS `tbl_course_list`;
CREATE TABLE IF NOT EXISTS `tbl_course_list` (
  `course_id` varchar(15) NOT NULL,
  `dept_code` varchar(3) NOT NULL,
  `max_students` int(6) NOT NULL,
  `max_outside_dept` int(6) NOT NULL,
  `ranking_criteria_id` int(1) NOT NULL,
  `credits` int(2) NOT NULL,
  `slot_id` varchar(2) NOT NULL,
  `additional_slot` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `dept_code` (`dept_code`),
  KEY `ranking_criteria_id` (`ranking_criteria_id`),
  KEY `slot_id` (`slot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_course_preference`
--

DROP TABLE IF EXISTS `tbl_course_preference`;
CREATE TABLE IF NOT EXISTS `tbl_course_preference` (
  `roll_number` varchar(15) NOT NULL,
  `course_id` varchar(15) NOT NULL,
  `inside_or_outside` varchar(10) NOT NULL,
  `preference_number` int(10) NOT NULL,
  PRIMARY KEY (`roll_number`,`course_id`,`inside_or_outside`),
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_department`
--

DROP TABLE IF EXISTS `tbl_department`;
CREATE TABLE IF NOT EXISTS `tbl_department` (
  `dept_code` varchar(3) NOT NULL,
  `dept_name` varchar(50) NOT NULL,
  PRIMARY KEY (`dept_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_department`
--

INSERT INTO `tbl_department` (`dept_code`, `dept_name`) VALUES
('AE', 'AE'),
('AM', 'AM'),
('BT', 'BT'),
('CE', 'CE'),
('CH', 'CH'),
('CS', 'CS'),
('CY', 'CY'),
('ED', 'ED'),
('EE', 'EE'),
('HS', 'HS'),
('MA', 'MA'),
('ME', 'ME'),
('MM', 'MM'),
('MS', 'MS'),
('OE', 'OE'),
('PH', 'PH');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_exchange_unstable_pairs`
--

DROP TABLE IF EXISTS `tbl_exchange_unstable_pairs`;
CREATE TABLE IF NOT EXISTS `tbl_exchange_unstable_pairs` (
  `student1_roll_no` varchar(15) NOT NULL,
  `course1_id` varchar(15) NOT NULL,
  `inside_or_outside_course1` varchar(10) NOT NULL,
  `student2_roll_no` varchar(15) NOT NULL,
  `course2_id` varchar(15) NOT NULL,
  `inside_or_outside_course2` varchar(10) NOT NULL,
  KEY `student1_roll_no` (`student1_roll_no`),
  KEY `student2_roll_no` (`student2_roll_no`),
  KEY `course1_id` (`course1_id`),
  KEY `course2_id` (`course2_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_high_priority_students`
--

DROP TABLE IF EXISTS `tbl_high_priority_students`;
CREATE TABLE IF NOT EXISTS `tbl_high_priority_students` (
  `course_id` varchar(15) NOT NULL,
  `batch` varchar(4) NOT NULL,
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_inside_department_spec`
--

DROP TABLE IF EXISTS `tbl_inside_department_spec`;
CREATE TABLE IF NOT EXISTS `tbl_inside_department_spec` (
  `course_id` varchar(15) NOT NULL,
  `batch` varchar(4) NOT NULL,
  `order_number` int(5) NOT NULL,
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_max_credit_limits`
--

DROP TABLE IF EXISTS `tbl_max_credit_limits`;
CREATE TABLE IF NOT EXISTS `tbl_max_credit_limits` (
  `batch` varchar(4) NOT NULL,
  `credit_limit` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_output`
--

DROP TABLE IF EXISTS `tbl_output`;
CREATE TABLE IF NOT EXISTS `tbl_output` (
  `student_roll_no` varchar(15) NOT NULL,
  `course_id` varchar(15) NOT NULL,
  KEY `student_roll_no` (`student_roll_no`),
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_ranking_criteria`
--

DROP TABLE IF EXISTS `tbl_ranking_criteria`;
CREATE TABLE IF NOT EXISTS `tbl_ranking_criteria` (
  `ranking_criteria_id` int(1) NOT NULL,
  `ranking_criteria_type` varchar(20) NOT NULL,
  PRIMARY KEY (`ranking_criteria_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_ranking_criteria`
--

INSERT INTO `tbl_ranking_criteria` (`ranking_criteria_id`, `ranking_criteria_type`) VALUES
(1, 'CG'),
(2, 'SI'),
(3, 'ST');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_slot`
--

DROP TABLE IF EXISTS `tbl_slot`;
CREATE TABLE IF NOT EXISTS `tbl_slot` (
  `slot_id` varchar(2) NOT NULL,
  `lecture_1` varchar(50) NOT NULL,
  `lecture_2` varchar(50) DEFAULT NULL,
  `lecture_3` varchar(50) DEFAULT NULL,
  `lecture_4` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`slot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_student_list`
--

DROP TABLE IF EXISTS `tbl_student_list`;
CREATE TABLE IF NOT EXISTS `tbl_student_list` (
  `roll_number` varchar(15) NOT NULL,
  `cgpa` float NOT NULL,
  `max_credits` int(11) NOT NULL DEFAULT '60',
  PRIMARY KEY (`roll_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_student_preference_list`
--

DROP TABLE IF EXISTS `tbl_student_preference_list`;
CREATE TABLE IF NOT EXISTS `tbl_student_preference_list` (
  `roll_number` varchar(15) NOT NULL,
  `course_id` varchar(15) NOT NULL,
  `colour_code` int(5) DEFAULT NULL,
  `preference_number` int(3) DEFAULT NULL,
  `course_type` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`roll_number`,`course_id`),
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_course_list`
--
ALTER TABLE `tbl_course_list`
  ADD CONSTRAINT `tbl_course_list_ibfk_1` FOREIGN KEY (`dept_code`) REFERENCES `tbl_department` (`dept_code`),
  ADD CONSTRAINT `tbl_course_list_ibfk_2` FOREIGN KEY (`ranking_criteria_id`) REFERENCES `tbl_ranking_criteria` (`ranking_criteria_id`),
  ADD CONSTRAINT `tbl_course_list_ibfk_3` FOREIGN KEY (`slot_id`) REFERENCES `tbl_slot` (`slot_id`);

--
-- Constraints for table `tbl_course_preference`
--
ALTER TABLE `tbl_course_preference`
  ADD CONSTRAINT `tbl_course_preference_ibfk_1` FOREIGN KEY (`roll_number`) REFERENCES `tbl_student_list` (`roll_number`),
  ADD CONSTRAINT `tbl_course_preference_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `tbl_course_list` (`course_id`);

--
-- Constraints for table `tbl_exchange_unstable_pairs`
--
ALTER TABLE `tbl_exchange_unstable_pairs`
  ADD CONSTRAINT `tbl_exchange_unstable_pairs_ibfk_1` FOREIGN KEY (`student1_roll_no`) REFERENCES `tbl_student_list` (`roll_number`),
  ADD CONSTRAINT `tbl_exchange_unstable_pairs_ibfk_2` FOREIGN KEY (`student2_roll_no`) REFERENCES `tbl_student_list` (`roll_number`),
  ADD CONSTRAINT `tbl_exchange_unstable_pairs_ibfk_3` FOREIGN KEY (`course1_id`) REFERENCES `tbl_course_list` (`course_id`),
  ADD CONSTRAINT `tbl_exchange_unstable_pairs_ibfk_4` FOREIGN KEY (`course2_id`) REFERENCES `tbl_course_list` (`course_id`);

--
-- Constraints for table `tbl_high_priority_students`
--
ALTER TABLE `tbl_high_priority_students`
  ADD CONSTRAINT `tbl_high_priority_students_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `tbl_course_list` (`course_id`);

--
-- Constraints for table `tbl_inside_department_spec`
--
ALTER TABLE `tbl_inside_department_spec`
  ADD CONSTRAINT `tbl_inside_department_spec_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `tbl_course_list` (`course_id`);

--
-- Constraints for table `tbl_output`
--
ALTER TABLE `tbl_output`
  ADD CONSTRAINT `tbl_output_ibfk_1` FOREIGN KEY (`student_roll_no`) REFERENCES `tbl_student_list` (`roll_number`),
  ADD CONSTRAINT `tbl_output_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `tbl_course_list` (`course_id`);

--
-- Constraints for table `tbl_student_preference_list`
--
ALTER TABLE `tbl_student_preference_list`
  ADD CONSTRAINT `tbl_student_preference_list_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `tbl_course_list` (`course_id`),
  ADD CONSTRAINT `tbl_student_preference_list_ibfk_2` FOREIGN KEY (`roll_number`) REFERENCES `tbl_student_list` (`roll_number`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
