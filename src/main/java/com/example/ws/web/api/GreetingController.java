package com.example.ws.web.api;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ws.model.Greeting;

@RestController
public class GreetingController {
    private static BigInteger		     nextId;
    private static Map<BigInteger, Greeting> greetingMap;

    private static Greeting save(Greeting greeting) {
	if (greetingMap == null) {
	    greetingMap = new HashMap<BigInteger, Greeting>();
	    nextId = BigInteger.ONE;
	}
	// If Update...
	if (greeting.getId() != null) {
	    Greeting oldGreeting = greetingMap.get(greeting.getId());
	    if (oldGreeting == null) {
		return null;
	    }
	    greetingMap.remove(greeting.getId());
	    greetingMap.put(greeting.getId(), greeting);
	    return greeting;
	}
	// If Create...
	greeting.setId(nextId);
	nextId = nextId.add(BigInteger.ONE);
	greetingMap.put(greeting.getId(), greeting);

	return greeting;

    }

    private static boolean delete(BigInteger id) {
	Greeting greet = greetingMap.remove(id);
	if (greet == null) {
	    return false;
	}
	return true;

    }

    static {
	Greeting g1 = new Greeting();
	g1.setText("HelloWorld");
	save(g1);

	Greeting g2 = new Greeting();
	g2.setText("ZdravoSvete");
	save(g2);
    }

    // informs spring that this method should receive http request
    @RequestMapping(value = "/*", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Collection<Greeting>> getGreetings() {

	Collection<Greeting> greetings = greetingMap.values();

	return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);
    }

    @RequestMapping(value = "/greetings/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Greeting> getGreeting(@PathVariable("id")
    BigInteger id) {
	Greeting greeting = greetingMap.get(id);
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

	Greeting savedGreeting = save(greeting);

	return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.CREATED);

    }

    @RequestMapping(
	    value = "/greetings/{id}",
	    method = RequestMethod.PUT,
	    consumes = MediaType.APPLICATION_JSON,
	    produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Greeting> updateGreeting(@RequestBody
    Greeting greeting) {

	Greeting updateGreeting = save(greeting);
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
	
	boolean deleteSuccssess = delete(id);
	if (deleteSuccssess){
	    return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
	}
	 return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
