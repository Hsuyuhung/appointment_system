CREATE TABLE  IF NOT EXISTS `appointment_info` (
  `auto_id` int NOT NULL AUTO_INCREMENT,
  `patient_name` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `birthday` date NOT NULL,
  `hospital_name` varchar(45) NOT NULL,
  `doctor_department` varchar(45) NOT NULL,
  `doctor_name` varchar(45) NOT NULL,
  `appointment_date` date NOT NULL,
  `appointment_week` varchar(45) NOT NULL,
  `appointment_time` time NOT NULL,
  `now_time` datetime NOT NULL,
  `number` int NOT NULL,
  PRIMARY KEY (`auto_id`)
  );
  
  CREATE TABLE  IF NOT EXISTS `doctor_info` (
  `doctor_id` varchar(45) NOT NULL,
  `doctor_name` varchar(45) NOT NULL,
  `doctor_department` varchar(45) NOT NULL,
  `hospital_id` varchar(45) NOT NULL,
  `appointment_time` varchar(45) NOT NULL,
  `week` varchar(45) NOT NULL,
  `auto_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`auto_id`)
   );
<<<<<<< HEAD
=======
  PRIMARY KEY (`doctor_id`)
  );
>>>>>>> branch 'hospital' of https://github.com/Hsuyuhung/appointment_system.git
=======
>>>>>>> refs/heads/hospital
  
   CREATE TABLE  IF NOT EXISTS `hospital_info` (
  `hospital_id` varchar(45) NOT NULL,
  `hospital_name` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `district` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `department` varchar(45) NOT NULL,
  PRIMARY KEY (`hospital_id`)
  );
  
   CREATE TABLE  IF NOT EXISTS `patient_info` (
  `id` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `birthday` date NOT NULL,
  `gender` varchar(10) NOT NULL,
  `e-mail` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
  );