
    /***appointment_info***/
 CREATE TABLE  IF NOT EXISTS `appointment_info` (
  `auto_id` int NOT NULL AUTO_INCREMENT,
  `patient_id` varchar(45) NOT NULL,
  `doctor_id` varchar(45) NOT NULL,
  `appointment_time` varchar(45) NOT NULL,
  `week` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `appointment_date` datetime DEFAULT NULL,
  `hospital_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`auto_id`)
);
  
    /***doctor_info***/
  CREATE TABLE  IF NOT EXISTS `doctor_info` (
  `auto_id` int NOT NULL AUTO_INCREMENT,
  `doctor_id` varchar(45) NOT NULL,
  `doctor_name` varchar(45) NOT NULL,
  `doctor_department` varchar(45) NOT NULL,
  `hospital_id` varchar(45) NOT NULL,
  `appointment_time` varchar(45) NOT NULL,
  `week` varchar(45) NOT NULL,
  PRIMARY KEY (`auto_id`)
)
   
  /***hospital_info***/
   CREATE TABLE  IF NOT EXISTS `hospital_info`  (
  `hospital_id` varchar(45) NOT NULL,
  `hospital_name` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `district` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `department` varchar(45) NOT NULL,
  PRIMARY KEY (`hospital_id`,`department`)
) ;
  /***patient_info***/
 CREATE TABLE  IF NOT EXISTS `patient_info` (
  `patient_id` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `patient_name` varchar(45) DEFAULT NULL,
  `birthday` date NOT NULL,
  `gender` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`patient_id`)
) ;

