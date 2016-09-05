package com.example.ws.web.api;

import java.math.BigInteger;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.ws.model.Greeting;
import com.example.ws.serevice.GreetingService;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingService;
    

    // informs spring that this method should receive http request
    @RequestMapping(value = "/greetings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Collection<Greeting>> getGreetings() {
	
	Collection<Greeting> greetings= greetingService.findAll();
	return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);
    }

    @RequestMapping(value = "/greetings/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Greeting> getGreeting(@PathVariable("id")
    BigInteger id) {
	Greeting greeting = greetingService.findOne(id);
	
	if (greeting == null) {
	    return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
	}

	return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
    }

    @RequestMapping(
	    value = "/greetings",
	    method = RequestMethod.POST,
	    consumes = MediaType.APPLICATION_JSON,
	    produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Greeting> createGreeting(@RequestBody
    Greeting greeting) {
	Greeting savedGreeting = greetingService.createGreeting(greeting);
	return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.CREATED);

    }

    @RequestMapping(
	    value = "/greetings/{id}",
	    method = RequestMethod.PUT,
	    consumes = MediaType.APPLICATION_JSON,
	    produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Greeting> updateGreeting(@RequestBody
    Greeting greeting) {

	Greeting updateGreeting = greetingService.updateGreeting(greeting);

	if (updateGreeting == null) {
	    return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<Greeting>(updateGreeting, HttpStatus.OK);

    }

    @RequestMapping(
	    value = "/greetings/{id}",
	    method = RequestMethod.DELETE,
	    consumes = MediaType.APPLICATION_JSON,
	    produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Greeting> deleteGreeting(@PathVariable("id")
    BigInteger id, @RequestBody
    Greeting greeting) {

	boolean deleteSuccssess = greetingService.delete(id);
	if (deleteSuccssess) {
	    return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
