-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 11, 2018 at 10:46 PM
-- Server version: 5.7.21-0ubuntu0.16.04.1
-- PHP Version: 7.0.28-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
-- Table structure for table `tbl_aggregate_statistics`
--

CREATE TABLE `tbl_aggregate_statistics` (
  `effective_rank_mean` double NOT NULL,
  `effective_rank_sd` double NOT NULL,
  `effective_rank_lowest10` double NOT NULL,
  `credit_satisfaction_ratio_mean` double NOT NULL,
  `credit_satisfaction_ratio_sd` double NOT NULL,
  `credit_satisfaction_ratio_lowest10` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `onwards` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
-- Table structure for table `tbl_exchange_unstable_pairs`
--

CREATE TABLE `tbl_exchange_unstable_pairs` (
  `student1_roll_no` varchar(15) NOT NULL,
  `student1_alloted_course` varchar(20) NOT NULL,
  `student2_roll_no` varchar(15) NOT NULL,
  `student2_alloted_course` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
-- Table structure for table `tbl_per_student_statistics`
--

CREATE TABLE `tbl_per_student_statistics` (
  `student_roll_no` varchar(15) NOT NULL,
  `effective_average_rank` double NOT NULL,
  `credit_satisfaction_ratio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_preferences_with_zero_capacity`
--

CREATE TABLE `tbl_preferences_with_zero_capacity` (
  `student_roll_number` varchar(10) NOT NULL,
  `preference_with_zero_capacity` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_reasons_not_alloting_preferences`
--

CREATE TABLE `tbl_reasons_not_alloting_preferences` (
  `student_roll_no` varchar(15) NOT NULL,
  `course_number` varchar(20) NOT NULL,
  `reason` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_rejections`
--

CREATE TABLE `tbl_rejections` (
  `course_id` varchar(15) NOT NULL,
  `no_of_rejections` int(11) NOT NULL,
  `no_of_first_preference_rejections` int(11) NOT NULL,
  `course_capacity` int(11) NOT NULL,
  `no_of_rejections_per_capacity` double NOT NULL,
  `no_of_first_preference_rejections_per_capacity` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
