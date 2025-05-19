package com.project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.entity.Course;

public interface CourseRepository extends JpaRepository<Course,Integer> 
{
	@Query("select c from Course c where c.instructor.id=?1")
	List<Course> getCourseByInstructorId(Integer id);
	
	Page<Course> findAll(Pageable pageable);
}
