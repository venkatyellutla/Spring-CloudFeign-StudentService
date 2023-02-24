package com.spring.cloudFeign.studentService.feignInterface;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.cloudFeign.studentService.bean.Book;

@FeignClient(name = "BOOK-SERVICE")
public interface BookRestConsumer {
	@GetMapping("/book/data")
	public String getBookData();

	@GetMapping("/book/{bookId}")
	public Book getBookById(@PathVariable Integer bookId);

	@GetMapping("/book/getall")
	public List<Book> getAllBooks();

	@GetMapping("/book/entity")
	public ResponseEntity<String> getEntityData();
}
