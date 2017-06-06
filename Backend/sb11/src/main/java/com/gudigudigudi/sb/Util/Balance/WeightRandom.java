package com.gudigudigudi.sb.Util.Balance;

import java.util.*;
import java.util.Random;

/**
 * @author Jiade Dai
 */
public class WeightRandom {
    public static String getServer(){
        Map<String,Integer> serverMap=new HashMap<String,Integer>();
        serverMap.putAll(IpMap.serverWeightMap);

        Set<String> keySet=serverMap.keySet();
        Iterator<String> iterator=keySet.iterator();

        List<String> serverList=new ArrayList<String>();
        while(iterator.hasNext()){
            String server=iterator.next();
            int weight=serverMap.get(server);
            for(int i=0;i<weight;i++)
                serverList.add(server);
        }

        Random random=new Random();
        int randomPos=random.nextInt(serverList.size());

        return serverList.get(randomPos);
    }
}
