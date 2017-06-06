package com.gudigudigudi.sb.Util.Balance;

import java.util.*;

/**
 * @author Jiade Dai
 * WeightRoundRobin: 不同的后端服务器可能机器的配置和当前系统的负载并不相同，
 * 因此它们的抗压能力也不相同。给配置高、负载低的机器配置更高的权重，让其处理更多的请；
 * 而配置低、负载高的机器，给其分配较低的权重，降低其系统负载，加权轮询能很好地处理这一问题，
 * 并将请求顺序且按照权重分配到后端。
 */
public class WeightRoundRobin {
    private static Integer pos;

    public static String getServer() {
        Map<String,Integer> serverMap=new HashMap<String,Integer>();
        serverMap.putAll(IpMap.serverWeightMap);

        Set<String> keySet=serverMap.keySet();
        Iterator<String> iterator=keySet.iterator();

        List<String> serverList=new ArrayList<String>();
        while(iterator.hasNext()){
            String server=iterator.next();
            int weight=serverMap.get(server);
            for(int i=0; i<weight;i++)
                serverList.add(server);
        }

        String server=null;
        synchronized (pos){
            if (pos>keySet.size())
                pos=0;
            server=serverList.get(pos);
            pos++;
        }
        return server;
    }
}
