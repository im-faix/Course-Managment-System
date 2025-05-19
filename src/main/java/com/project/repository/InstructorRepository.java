package com.project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.entity.Instructor;
import com.project.entity.Student;

public interface InstructorRepository extends JpaRepository<Instructor,Integer> 
{

	@Query("select e.student from Enrolment e where e.course.instructor.id=?1")
	List<Student> getStudentByInstructorId(Integer id);
	
	Page<Instructor> findAll(Pageable pageable);
}
