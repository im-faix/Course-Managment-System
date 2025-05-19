package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.dao.CourseDao;
import com.project.dao.EnrolmentDao;
import com.project.dao.StudentDao;
import com.project.dto.ResponseStructure;
import com.project.entity.Course;
import com.project.entity.Enrolment;
import com.project.entity.Student;
import com.project.exception.IdNotFoundException;

@Service
public class EnrolmentService 
{
		
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private EnrolmentDao enrolmentDao;
	
	public ResponseEntity<ResponseStructure<Enrolment>> enrollStudent(Enrolment enrolment)
	{
		Optional<Course> course = courseDao.getCourseById(enrolment.getCourse().getId());
		Optional<Student> student = studentDao.getStudentById(enrolment.getStudent().getId());
		ResponseStructure<Enrolment> structure = new ResponseStructure<Enrolment>();
		if(course.isPresent() && student.isPresent())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("1 Record Added ");
			enrolment.setCourse(course.get());
			enrolment.setStudent(student.get());
			structure.setData(enrolmentDao.enrollStudent(enrolment));
			return new ResponseEntity<ResponseStructure<Enrolment>>(structure,HttpStatus.OK);
		}
		else
		{
			structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			structure.setMessage("Bad Request");
			return new ResponseEntity<ResponseStructure<Enrolment>>(structure,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	public ResponseEntity<ResponseStructure<List<Enrolment>>> getAllEnrolment()
	{
		List<Enrolment> ls = enrolmentDao.getAllEnrolment();
		ResponseStructure<List<Enrolment>> structure = new ResponseStructure<List<Enrolment>>();
		if(!ls.isEmpty())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Fetched");
			structure.setData(ls);
			return new ResponseEntity<ResponseStructure<List<Enrolment>>>(structure,HttpStatus.OK);
		}
		else
		{
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("No Records found");
			return new ResponseEntity<ResponseStructure<List<Enrolment>>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<Enrolment>> getEnrolmentById(Integer id)
	{
		Optional<Enrolment> ot = enrolmentDao.getEnrolmentById(id);
		ResponseStructure<Enrolment> structure = new ResponseStructure<Enrolment>();
		if(ot.isPresent())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("1 Record Found");
			structure.setData(ot.get());
			
			return new ResponseEntity<ResponseStructure<Enrolment>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new IdNotFoundException();
		}
	}
	
	
	public ResponseEntity<ResponseStructure<String>> deleteEnrolment(Integer id)
	{
		Optional<Enrolment> ot = enrolmentDao.getEnrolmentById(id);
		ResponseStructure<String> structure = new ResponseStructure<String>();
		if(ot.isPresent())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("1 Record Deleted");
			structure.setData("Deleted");
			enrolmentDao.deleteEnrolment(id);
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Enrolment>> updateEnrolment(Enrolment enrolment)
	{
		ResponseStructure<Enrolment> structure = new ResponseStructure<Enrolment>();
		if (enrolment.getId() == null) 
		{
	        structure.setStatusCode(HttpStatus.CREATED.value());
	        structure.setMessage("New Record Created");
	        structure.setData(enrolmentDao.enrollStudent(enrolment)); 
	        return new ResponseEntity<>(structure, HttpStatus.CREATED);
	    }

	    // Fetch Enrolment by ID
	    Optional<Enrolment> op = enrolmentDao.getEnrolmentById(enrolment.getId());

	    
	    Optional<Course> ot = courseDao.getCourseById(enrolment.getCourse().getId());
	    Optional<Student> os = studentDao.getStudentById(enrolment.getStudent().getId());
	    
	    if (op.isPresent()) 
	    {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Updated");
	        if(ot.isPresent() && os.isPresent())
	        {
	        	structure.setData(enrolmentDao.enrollStudent(op.get())); 
		        return new ResponseEntity<ResponseStructure<Enrolment>>(structure, HttpStatus.OK);
	        }
	        else
	        {
	        	throw new IdNotFoundException();
	        }  
	    } 
	    else
	    {
	    	throw new IdNotFoundException();
	    }
	}
	
	public ResponseEntity<ResponseStructure<List<Enrolment>>> getEnrolmentByStudentId(Integer id)
	{
		List<Enrolment> ls  = enrolmentDao.getEnrolmentByStudentId(id);
		ResponseStructure<List<Enrolment>> structure = new ResponseStructure<List<Enrolment>>();
		if(!ls.isEmpty())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Fetched");
			structure.setData(ls);
			return new ResponseEntity<ResponseStructure<List<Enrolment>>>(structure,HttpStatus.OK);
		}
		else
		{
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Fetched");
			return new ResponseEntity<ResponseStructure<List<Enrolment>>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Enrolment>>> getEnrolmentByCourseId(Integer id)
	{
		List<Enrolment> ls  = enrolmentDao.getEnrolmentByCourseId(id);
		ResponseStructure<List<Enrolment>> structure = new ResponseStructure<List<Enrolment>>();
		if(!ls.isEmpty())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Fetched");
			structure.setData(ls);
			return new ResponseEntity<ResponseStructure<List<Enrolment>>>(structure,HttpStatus.OK);
		}
		else
		{
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Fetched");
			return new ResponseEntity<ResponseStructure<List<Enrolment>>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<Page<Enrolment>>> getEnrolmentByPaginationAndSorting(int pageNumber, int pageSize, String field) {
	    
//	    Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending());
	    Page<Enrolment> page = enrolmentDao.getEnrolmentByPaginationAndSorting(pageNumber, pageSize, field);
	    
	    ResponseStructure<Page<Enrolment>> structure = new ResponseStructure<>();
	    
	    if (!page.isEmpty()) {
	        structure.setStatusCode(HttpStatus.FOUND.value());
	        structure.setMessage("Fetched all the records");
	        structure.setData(page);
	        return new ResponseEntity<>(structure, HttpStatus.FOUND);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("No records found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}

}

