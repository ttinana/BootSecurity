/**
 * 
 */
package com.example.ws.serevice;

import java.math.BigInteger;
import java.util.Collection;

import com.example.ws.model.Greeting;

/**
 * @author tijana.pavicic
 *
 */
public interface GreetingService {
    Collection<Greeting> findAll();

    Greeting findOne(BigInteger id);

    Greeting createGreeting(Greeting greet);

    Greeting updateGreeting(Greeting greet);

    boolean delete(BigInteger id);

}
