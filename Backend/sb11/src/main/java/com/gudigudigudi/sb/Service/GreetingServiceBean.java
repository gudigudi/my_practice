package com.gudigudigudi.sb.Service;

import com.gudigudigudi.sb.Model.Greeting;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class GreetingServiceBean implements GreetingService{

    private static BigInteger nextId;
    private static Map<BigInteger ,Greeting> greetingMap;

    static {
        Greeting g1=new Greeting();
        g1.setText("Hello World!");
        save(g1);

        Greeting g2=new Greeting();
        g2.setText("Hello Gudi!");
        save(g2);
    }

    private static Greeting save(Greeting greeting){
        if(greetingMap==null){
            greetingMap=new HashMap<BigInteger,Greeting>();
            nextId=BigInteger.ONE;
        }
        // If Update...
        if (greeting.getId()!=null){
            Greeting oldGreeting=greetingMap.get(greeting.getId());
            if (oldGreeting==null){
                return null;
            }
            greetingMap.remove(greeting.getId());
            greetingMap.put(greeting.getId(),greeting);
            return greeting;
        }
        // If Create...
        greeting.setId(nextId);
        nextId =nextId.add(BigInteger.ONE);
        greetingMap.put(greeting.getId(),greeting);
        return greeting;
    }

    private static boolean remove(BigInteger id){
        Greeting deletedGreeting=greetingMap.remove(id);
        if (deletedGreeting==null){
            return false;
        }
        return true;
    }

    @Override
    public Collection<Greeting> findAll() {
        Collection<Greeting> greetings=greetingMap.values();
        return greetings;
    }

    @Override
    public Greeting findOne(BigInteger id) {
        Greeting greeting=greetingMap.get(id);
        return greeting;
    }

    @Override
    public Greeting create(Greeting greeting) {
        Greeting savedGreeting = save(greeting);
        return savedGreeting;
    }

    @Override
    public void delete(BigInteger id) {
        remove(id);
    }

    @Override
    public Greeting update(Greeting greeting) {
        Greeting updatedGreeting=save(greeting);
        return updatedGreeting;
    }
}
