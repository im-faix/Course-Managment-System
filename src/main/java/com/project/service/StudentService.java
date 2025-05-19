package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.dao.StudentDao;
import com.project.dto.ResponseStructure;
import com.project.entity.Course;
import com.project.entity.Student;

@Service
public class StudentService 
{
	@Autowired
	private StudentDao studentDao;
	
	public ResponseEntity<ResponseStructure<Student>> addStudent(Student student)
	{
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Successfully Added");
		structure.setData(studentDao.addStudent(student));
		
		return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> getAllStudents()
	{
		List<Student> ls = studentDao.getAllStudent();
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
		if(!ls.isEmpty())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Fetching all the Records from the DataBase");
			structure.setData(ls);
			return new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.OK);
		}
		else
		{
			structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			structure.setMessage("No Records Present in DataBase");
			return new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.BAD_REQUEST);
			
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> getStudentById(Integer id)
	{
		Optional<Student> op = studentDao.getStudentById(id);
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		if(op.isPresent())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Fetched");
			structure.setData(op.get());
			
			return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		}
		else
		{
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("No Record Found");
			
			return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Student>> updateStudent(Student student) {
	    ResponseStructure<Student> structure = new ResponseStructure<>();

	    // If student ID is null, create a new record
	    if (student.getId() == null) {
	        structure.setStatusCode(HttpStatus.CREATED.value());
	        structure.setMessage("New Record Created");
	        structure.setData(studentDao.addStudent(student)); // Assuming addStudent handles new records
	        return new ResponseEntity<>(structure, HttpStatus.CREATED);
	    }

	    // Fetch student by ID
	    Optional<Student> op = studentDao.getStudentById(student.getId());

	    if (op.isPresent()) {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Updated");
	        structure.setData(studentDao.addStudent(student)); 
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value()); 
	        structure.setMessage("Student ID not found, cannot update");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}

	public ResponseEntity<ResponseStructure<String>> deleteStudent(Integer id)
	{
		Optional<Student> ot = studentDao.getStudentById(id);
		ResponseStructure<String> structure = new ResponseStructure<String>();
		if(ot.isPresent())
		{
				structure.setStatusCode(HttpStatus.OK.value());
		        structure.setMessage("Deleted");
		        studentDao.deleteStudent(ot.get());
		        structure.setData("1 Record Deleted");
		      return new ResponseEntity<>(structure, HttpStatus.OK);     
		}
		else
		{
			  structure.setStatusCode(HttpStatus.NOT_FOUND.value()); 
		        structure.setMessage("Student ID not found, cannot deleted");
		        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
		}
		
	}
	
	public ResponseEntity<ResponseStructure<List<Course>>> getCourseByStudentId(Integer id)
	{
		List<Course> ls = studentDao.getCourseBtStudentId(id);
		ResponseStructure<List<Course>> structure = new ResponseStructure<List<Course>>();
		if(!ls.isEmpty())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Fetched");
			structure.setData(ls);
			return new ResponseEntity<ResponseStructure<List<Course>>>(structure,HttpStatus.OK);
		}
		else
		{
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("No Record Found");
			return new ResponseEntity<ResponseStructure<List<Course>>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	
		public ResponseEntity<ResponseStructure<Page<Student>>> getStudentByPaginationAndSorting(int pageNumber, int pageSize, String field) {
	    
//	    Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending());
	    Page<Student> page = studentDao.getStudentByPaginationAndSorting(pageNumber, pageSize, field);
	    
	    ResponseStructure<Page<Student>> structure = new ResponseStructure<>();
	    
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
