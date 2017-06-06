package com.gudigudigudi.sb.Util.Balance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Jiade Dai
 * Random: 负载的随机算法，根据后端服务器的列表大小值来随机选取其中的一台服务器进行访问。
 */
public class Random {

    public static String getServer()
    {
        Map<String,Integer> serverMap = new HashMap<String,Integer>();
        serverMap.putAll(IpMap.serverWeightMap);

        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList=new ArrayList<String>();
        keyList.addAll(keySet);

        java.util.Random random = new java.util.Random();
        int randomPos=random.nextInt(keyList.size());

        return keyList.get(randomPos);
    }
}
