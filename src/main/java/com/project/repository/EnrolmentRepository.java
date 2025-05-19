package com.project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.entity.Enrolment;

public interface EnrolmentRepository extends JpaRepository<Enrolment,Integer>
{

	@Query("select e from Enrolment e where e.student.id=?1")
	List<Enrolment> getEnrolmentByStudentId(Integer id);
	
	@Query("select e from Enrolment e where e.course.id=?1")
	List<Enrolment> getEnrolmentByCourseId(Integer id);

	Page<Enrolment> findAll(Pageable pageable);
	
	
	
}
