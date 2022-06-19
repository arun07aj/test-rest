package com.aj.testrest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	Map<Integer, String> data = new HashMap<>();
	
	// sample get
	@RequestMapping("/test")
	public ResponseEntity<?> test()
	{
		data.put(1, "Arun");
		data.put(2, "Alak");
		data.put(3, "Jon");
		return ResponseEntity.status(HttpStatus.OK).body(data.toString());
	}
	
	// sample get taking id and body values
	@GetMapping("/test/{id}")
	public ResponseEntity<?> getDetails(@PathVariable("id") Integer id)
	{
		Optional<?> name = Optional.ofNullable(data.get(id));
		
		if (!name.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body("data found:- id: " + id + " and name: " + name.get());
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no data found for given id");
	}
	
	// sample post
	@PostMapping("/test/{id}")
	public ResponseEntity<?> addDetails(@PathVariable("id") Integer id, @RequestBody String name)
	{
		if(!data.containsKey(id))
		{
			data.put(id, name);
			return ResponseEntity.status(HttpStatus.OK).body("data added:- id: " + id + " and name: " + name);
		}
			
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("data already exists");
	}
	
	@DeleteMapping("/test/{id}")
	public ResponseEntity<?> deleteDetails(@PathVariable("id") Integer id)
	{
		if(data.containsKey(id))
		{
			data.remove(id);
			return ResponseEntity.status(HttpStatus.OK).body("data removed");
		}
			
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("data not found");
	}
}
