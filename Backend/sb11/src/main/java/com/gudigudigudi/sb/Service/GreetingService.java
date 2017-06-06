package com.gudigudigudi.sb.Service;

import com.gudigudigudi.sb.Model.Greeting;

import java.math.BigInteger;
import java.util.Collection;

public interface GreetingService {

    Collection<Greeting> findAll();

    Greeting findOne(BigInteger id);

    Greeting create(Greeting greeting);

    void delete(BigInteger id);

    Greeting update(Greeting greeting);
}
