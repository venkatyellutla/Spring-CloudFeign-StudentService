package com.spring.cloudFeign.studentService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cloudFeign.studentService.feignInterface.BookRestConsumer;
@RestController
@RequestMapping("/student")
public class StudentRestController {
	@Autowired
	private BookRestConsumer bookRestConsumer;
	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;
	 @GetMapping("/data")
     public String getStudentInfo() {
        System.out.println(bookRestConsumer.getClass().getName());  //prints as a proxy class
        return "Accessing from STUDENT-SERVICE ==> " +bookRestConsumer.getBookData();
     }
	 @GetMapping("/allBooks")
     public String getBooksInfo() {
		 CircuitBreaker circuitBreaker=null;
		 circuitBreaker= circuitBreakerFactory.create("default");
		 circuitBreaker.run(()->{
			 return "Accessing from STUDENT-SERVICE ==> " + bookRestConsumer.getAllBooks();
		 });
        return "Accessing from STUDENT-SERVICE ==> " + bookRestConsumer.getAllBooks();
     }

     @GetMapping("/getOneBook/{id}")
     public String getOneBookForStd(@PathVariable Integer id) {
        return "Accessing from STUDENT-SERVICE ==> " + bookRestConsumer.getBookById(id); 
     }

     @GetMapping("/entityData")
     public String printEntityData() {
        ResponseEntity<String> resp = bookRestConsumer.getEntityData();
        return "Accessing from STUDENT-SERVICE ==> " + resp.getBody() +" , status is:" + resp.getStatusCode();
     }
}
