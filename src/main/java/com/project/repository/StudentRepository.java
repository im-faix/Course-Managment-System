package com.project.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.project.entity.Course;
import com.project.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>
{

	@Query("SELECT e.course FROM Enrolment e WHERE e.student.id = ?1")
	List<Course> getCourseByStudentId(Integer id);

	Page<Student> findAll(Pageable pageable);
	
}
