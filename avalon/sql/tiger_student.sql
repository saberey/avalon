--学生表
CREATE TABLE IF NOT EXISTS `tiger`.`student` (
  `id` INT NULL,
  `student_no` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL,
  `sex` VARCHAR(45) NULL,
  `remark` VARCHAR(45) NULL,
  PRIMARY KEY (`student_no`))
ENGINE = InnoDB;

--课程表
CREATE TABLE IF NOT EXISTS `tiger`.`course` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `remark` VARCHAR(45) NULL,
  `course_no` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`course_no`))
ENGINE = InnoDB;


--学生课程成绩表
CREATE TABLE IF NOT EXISTS `tiger`.`coursegrade` (
  `id` INT NOT NULL,
  `student_no` VARCHAR(45) NULL,
  `course_no` VARCHAR(45) NULL,
  `score` VARCHAR(45) NULL,
  `remark` VARCHAR(45) NULL,
  `student_student_no` VARCHAR(45) NOT NULL,
  `course_course_no` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `student_student_no`, `course_course_no`),
  INDEX `fk_coursegrade_student_idx` (`student_student_no` ASC),
  INDEX `fk_coursegrade_course1_idx` (`course_course_no` ASC),
  CONSTRAINT `fk_coursegrade_student`
    FOREIGN KEY (`student_student_no`)
    REFERENCES `tiger`.`student` (`student_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_coursegrade_course1`
    FOREIGN KEY (`course_course_no`)
    REFERENCES `tiger`.`course` (`course_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;