package net.java.springbootrestapi.controller;
import net.java.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    //HTTP GET request

@GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                1,
                 "Bhawna",
    "Bassarmalani");
        //return  new ResponseEntity<>(student,HttpStatus.OK);
    return ResponseEntity.ok().header("custom-header" , "bhawna").body(student);

    }
@GetMapping("students")
    public ResponseEntity<List<Student>> getStudents(){
    List<Student> students = new ArrayList<>();
    students.add(new Student(2,"Shreya","Jain"));
    students.add(new Student(3,"anisha" , "palan"));
    return ResponseEntity.ok(students);
    }

    // Springboot Rest API with a Path Variable
    // {id} - URI Template variable
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int id,
                                       @PathVariable("first-name")String firstName,
                                       @PathVariable("last-name") String lastName){
    Student student = new Student(id,firstName,lastName);
        return ResponseEntity.ok(student);
        }
        // Spring Boot REST API with RequestParam
// http://localhost:8080/students/query?id=1
    @GetMapping("students/query")
    public Student studentRequestVariable(@RequestParam int ind){
    return new Student(1, "bhawna" , "bassarmalani");

    }
    // Spring Boot REST API that handles HTTP PostRequest
    // @PostMapping and @RequestBody annotation
    @PostMapping("students/create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
    System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
    @PutMapping("students/{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student , @PathVariable int id){
        //System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("students/{id}/delete")
    public ResponseEntity<String>deleteStudent(@PathVariable int id){
    System.out.println(id);
    return ResponseEntity.ok("deleted successfully");
    }


}


