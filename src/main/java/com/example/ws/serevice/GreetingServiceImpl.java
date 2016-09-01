/**
 * 
 */
package com.example.ws.serevice;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.ws.model.Greeting;

/**
 * @author tijana.pavicic
 *
 */
@Service
public class GreetingServiceImpl implements GreetingService {
    private static BigInteger		     nextId;
    private static Map<BigInteger, Greeting> greetingMap;

    /* (non-Javadoc)
     * @see com.example.ws.serevice.GreetingService#findAll()
     */
    @Override
    public Collection<Greeting> findAll() {
	Collection<Greeting> greetings = greetingMap.values();
	return greetings;
    }

    /* (non-Javadoc)
     * @see com.example.ws.serevice.GreetingService#findOne(java.math.BigInteger)
     */
    @Override
    public Greeting findOne(BigInteger id) {
	Greeting greeting = greetingMap.get(id);
	return greeting;
    }

    /* (non-Javadoc)
     * @see com.example.ws.serevice.GreetingService#createGreeting(com.example.ws.model.Greeting)
     */
    @Override
    public Greeting createGreeting(Greeting greet) {
	Greeting savedGreeting = save(greet);
	return savedGreeting;
    }

    /* (non-Javadoc)
     * @see com.example.ws.serevice.GreetingService#updateGreeting(com.example.ws.model.Greeting)
     */
    @Override
    public Greeting updateGreeting(Greeting greet) {
	Greeting updateGreeting = save(greet);
	return updateGreeting;
    }

    /* (non-Javadoc)
     * @see com.example.ws.serevice.GreetingService#delete(java.math.BigInteger)
     */
    @Override
    public boolean delete(BigInteger id) {
	boolean deleteSuccssess = remove(id);
	return deleteSuccssess;

    }
    
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

    private static boolean remove(BigInteger id) {
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

}
