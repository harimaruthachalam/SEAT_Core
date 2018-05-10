-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3307
-- Generation Time: May 10, 2018 at 01:43 AM
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
CREATE DATABASE IF NOT EXISTS `db_seat` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `db_seat`;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_batch_specific_recommended_elective`
--

CREATE TABLE `tbl_batch_specific_recommended_elective` (
  `batch` varchar(4) CHARACTER SET utf8 NOT NULL,
  `recommended_elective_type` varchar(2) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_course_list`
--

CREATE TABLE `tbl_course_list` (
  `course_id` varchar(10) CHARACTER SET utf8 NOT NULL,
  `dept_code` varchar(3) CHARACTER SET utf8 NOT NULL,
  `max_students` int(6) NOT NULL,
  `max_outside_dept` int(6) NOT NULL,
  `ranking_criteria_id` int(1) NOT NULL,
  `credits` int(2) NOT NULL,
  `slot_id` varchar(2) CHARACTER SET utf8 NOT NULL,
  `additional_slot` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_course_preference`
--

CREATE TABLE `tbl_course_preference` (
  `roll_number` varchar(15) NOT NULL,
  `course_id` varchar(10) NOT NULL,
  `inside_or_outside` varchar(10) NOT NULL,
  `preference_number` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_course_type`
--

CREATE TABLE `tbl_course_type` (
  `course_type_id` varchar(1) NOT NULL,
  `type_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_department`
--

CREATE TABLE `tbl_department` (
  `dept_code` varchar(3) NOT NULL,
  `dept_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_exchange_unstable_pairs`
--

CREATE TABLE `tbl_exchange_unstable_pairs` (
  `student1_roll_no` varchar(15) CHARACTER SET utf8 NOT NULL,
  `course1_id` varchar(10) CHARACTER SET utf8 NOT NULL,
  `student2_roll_no` varchar(15) CHARACTER SET utf8 NOT NULL,
  `course2_id` varchar(10) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_high_priority_students`
--

CREATE TABLE `tbl_high_priority_students` (
  `course_id` varchar(10) CHARACTER SET utf8 NOT NULL,
  `batch` varchar(4) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_inside_department_spec`
--

CREATE TABLE `tbl_inside_department_spec` (
  `course_id` varchar(10) CHARACTER SET utf8 NOT NULL,
  `batch` varchar(4) CHARACTER SET utf8 NOT NULL,
  `order_number` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_max_credit_limits`
--

CREATE TABLE `tbl_max_credit_limits` (
  `batch` varchar(4) CHARACTER SET utf8 NOT NULL,
  `credit_limit` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_output`
--

CREATE TABLE `tbl_output` (
  `student_roll_no` varchar(15) CHARACTER SET utf8 NOT NULL,
  `course_id` varchar(10) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_ranking_criteria`
--

CREATE TABLE `tbl_ranking_criteria` (
  `ranking_criteria_id` int(1) NOT NULL,
  `ranking_criteria_type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_slot`
--

CREATE TABLE `tbl_slot` (
  `slot_id` varchar(2) CHARACTER SET utf8 NOT NULL,
  `lecture_1` varchar(50) CHARACTER SET utf8 NOT NULL,
  `lecture_2` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `lecture_3` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `lecture_4` varchar(50) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_student_list`
--

CREATE TABLE `tbl_student_list` (
  `roll_number` varchar(15) CHARACTER SET utf8 NOT NULL,
  `cgpa` float NOT NULL,
  `max_credits` int(11) NOT NULL DEFAULT '60',
  `is_honour` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_student_preference_list`
--

CREATE TABLE `tbl_student_preference_list` (
  `roll_number` varchar(15) CHARACTER SET utf8 NOT NULL,
  `course_id` varchar(10) CHARACTER SET utf8 NOT NULL,
  `colour_code` int(5) NOT NULL,
  `preference` varchar(15) CHARACTER SET utf8 NOT NULL,
  `preference_number` int(3) DEFAULT NULL,
  `course_type_id` varchar(1) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_course_list`
--
ALTER TABLE `tbl_course_list`
  ADD PRIMARY KEY (`course_id`),
  ADD KEY `dept_code` (`dept_code`),
  ADD KEY `ranking_criteria_id` (`ranking_criteria_id`),
  ADD KEY `slot_id` (`slot_id`);

--
-- Indexes for table `tbl_course_preference`
--
ALTER TABLE `tbl_course_preference`
  ADD PRIMARY KEY (`roll_number`,`course_id`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `tbl_course_type`
--
ALTER TABLE `tbl_course_type`
  ADD PRIMARY KEY (`course_type_id`);

--
-- Indexes for table `tbl_department`
--
ALTER TABLE `tbl_department`
  ADD PRIMARY KEY (`dept_code`);

--
-- Indexes for table `tbl_exchange_unstable_pairs`
--
ALTER TABLE `tbl_exchange_unstable_pairs`
  ADD KEY `student1_roll_no` (`student1_roll_no`),
  ADD KEY `student2_roll_no` (`student2_roll_no`),
  ADD KEY `course1_id` (`course1_id`),
  ADD KEY `course2_id` (`course2_id`);

--
-- Indexes for table `tbl_high_priority_students`
--
ALTER TABLE `tbl_high_priority_students`
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `tbl_inside_department_spec`
--
ALTER TABLE `tbl_inside_department_spec`
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `tbl_output`
--
ALTER TABLE `tbl_output`
  ADD KEY `student_roll_no` (`student_roll_no`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `tbl_ranking_criteria`
--
ALTER TABLE `tbl_ranking_criteria`
  ADD PRIMARY KEY (`ranking_criteria_id`);

--
-- Indexes for table `tbl_slot`
--
ALTER TABLE `tbl_slot`
  ADD PRIMARY KEY (`slot_id`);

--
-- Indexes for table `tbl_student_list`
--
ALTER TABLE `tbl_student_list`
  ADD PRIMARY KEY (`roll_number`);

--
-- Indexes for table `tbl_student_preference_list`
--
ALTER TABLE `tbl_student_preference_list`
  ADD PRIMARY KEY (`roll_number`,`course_id`),
  ADD KEY `course_id` (`course_id`),
  ADD KEY `course_type_id` (`course_type_id`);

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
  ADD CONSTRAINT `tbl_student_preference_list_ibfk_2` FOREIGN KEY (`roll_number`) REFERENCES `tbl_student_list` (`roll_number`),
  ADD CONSTRAINT `tbl_student_preference_list_ibfk_3` FOREIGN KEY (`course_type_id`) REFERENCES `tbl_course_type` (`course_type_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
