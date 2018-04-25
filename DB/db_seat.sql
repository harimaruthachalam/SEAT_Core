-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3307
-- Generation Time: Apr 24, 2018 at 11:12 PM
-- Server version: 5.7.21
-- PHP Version: 7.1.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_seat`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_courselist`
--

CREATE TABLE `tbl_courselist` (
  `coursenumber` varchar(15) NOT NULL,
  `dept_code` varchar(3) NOT NULL,
  `max_students` int(6) NOT NULL,
  `max_outside_dept` int(6) NOT NULL,
  `ranking_criteria` int(2) NOT NULL,
  `onwards` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_coursepreferencelist`
--

CREATE TABLE `tbl_coursepreferencelist` (
  `coursenumber` varchar(30) NOT NULL,
  `onwards` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_coursepreferencelist`
--

INSERT INTO `tbl_coursepreferencelist` (`coursenumber`, `onwards`) VALUES
('EE1000$inside', NULL),
('EE1000$outside', 'CS13B004,CS13B002,CS13B001,CS13B003,CS13B000'),
('EE1001$inside', NULL),
('EE1001$outside', 'CS13B001,CS13B000'),
('EE1002$inside', NULL),
('EE1002$outside', 'CS13B004,CS13B000,CS13B003,CS13B002'),
('EE1003$inside', NULL),
('EE1003$outside', 'CS13B000,CS13B004,CS13B001,CS13B002,CS13B003');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_course_preference`
--

CREATE TABLE `tbl_course_preference` (
  `id` bigint(20) NOT NULL,
  `course_id` varchar(10) NOT NULL,
  `inside_or_outside` varchar(10) NOT NULL,
  `preferences` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Todo: Foreign key mapping; preferences as other table';

-- --------------------------------------------------------

--
-- Table structure for table `tbl_high_priority_students`
--

CREATE TABLE `tbl_high_priority_students` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Todo: Add missing columns';

-- --------------------------------------------------------

--
-- Table structure for table `tbl_inside_department_spec`
--

CREATE TABLE `tbl_inside_department_spec` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_max_credit_limit`
--

CREATE TABLE `tbl_max_credit_limit` (
  `dept_year` varchar(15) NOT NULL,
  `max_credit_limit` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_output`
--

CREATE TABLE `tbl_output` (
  `id` bigint(20) NOT NULL,
  `student_roll` varchar(10) NOT NULL,
  `course_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Todo: Foreign key mapping';

-- --------------------------------------------------------

--
-- Table structure for table `tbl_per_course_alloted_students`
--

CREATE TABLE `tbl_per_course_alloted_students` (
  `id` bigint(20) NOT NULL,
  `course_id` varchar(10) NOT NULL,
  `inside_or_outside` varchar(10) NOT NULL,
  `total_capacity` int(11) NOT NULL,
  `allotted_capacity` int(11) NOT NULL,
  `students_allotted` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Todo: Foreign key mapping; students_allotted as other table';

-- --------------------------------------------------------

--
-- Table structure for table `tbl_per_student_allotted_courses`
--

CREATE TABLE `tbl_per_student_allotted_courses` (
  `id` bigint(20) NOT NULL,
  `student_roll` varchar(10) NOT NULL,
  `elective_allotted` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_slot_configuration`
--

CREATE TABLE `tbl_slot_configuration` (
  `slot_name` varchar(2) NOT NULL,
  `onwards` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_student_list`
--

CREATE TABLE `tbl_student_list` (
  `roll_number` varchar(15) NOT NULL,
  `cgpa` float NOT NULL,
  `max_credits` int(11) NOT NULL DEFAULT '60'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_student_list`
--

INSERT INTO `tbl_student_list` (`roll_number`, `cgpa`, `max_credits`) VALUES
('CS13B000', 8.31, 29),
('CS13B001', 8.63, 34),
('CS13B002', 5.12, 31),
('CS13B003', 7.72, 33),
('CS13B004', 9.63, 32);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_student_preference_list`
--

CREATE TABLE `tbl_student_preference_list` (
  `roll_number` varchar(15) NOT NULL,
  `course_number` varchar(15) NOT NULL,
  `colour_code` int(5) NOT NULL,
  `preference` varchar(15) NOT NULL,
  `preference_number` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_courselist`
--
ALTER TABLE `tbl_courselist`
  ADD PRIMARY KEY (`coursenumber`);

--
-- Indexes for table `tbl_coursepreferencelist`
--
ALTER TABLE `tbl_coursepreferencelist`
  ADD PRIMARY KEY (`coursenumber`);

--
-- Indexes for table `tbl_course_preference`
--
ALTER TABLE `tbl_course_preference`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_high_priority_students`
--
ALTER TABLE `tbl_high_priority_students`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_inside_department_spec`
--
ALTER TABLE `tbl_inside_department_spec`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_max_credit_limit`
--
ALTER TABLE `tbl_max_credit_limit`
  ADD PRIMARY KEY (`dept_year`);

--
-- Indexes for table `tbl_output`
--
ALTER TABLE `tbl_output`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_per_course_alloted_students`
--
ALTER TABLE `tbl_per_course_alloted_students`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_per_student_allotted_courses`
--
ALTER TABLE `tbl_per_student_allotted_courses`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_slot_configuration`
--
ALTER TABLE `tbl_slot_configuration`
  ADD PRIMARY KEY (`slot_name`);

--
-- Indexes for table `tbl_student_list`
--
ALTER TABLE `tbl_student_list`
  ADD PRIMARY KEY (`roll_number`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_course_preference`
--
ALTER TABLE `tbl_course_preference`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_high_priority_students`
--
ALTER TABLE `tbl_high_priority_students`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_inside_department_spec`
--
ALTER TABLE `tbl_inside_department_spec`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_output`
--
ALTER TABLE `tbl_output`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_per_course_alloted_students`
--
ALTER TABLE `tbl_per_course_alloted_students`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_per_student_allotted_courses`
--
ALTER TABLE `tbl_per_student_allotted_courses`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
