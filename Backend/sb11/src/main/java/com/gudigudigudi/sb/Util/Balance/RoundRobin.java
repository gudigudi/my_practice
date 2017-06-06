package com.gudigudigudi.sb.Util.Balance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Jiade Dai
 * RoundRobin: 每一次把来自用户的请求轮流分配给内部中的服务器，从1开始，知道N(内部服务器个数)，然后重新开始循环。
 * 无需记录当前所有连接的状态，所以是一种无状态调度。
 */
public class RoundRobin {
    private static Integer pos = 0;

    public static String getServer(){
        // 重建server的Map
        Map<String,Integer> serverMap=new HashMap<String,Integer>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 获取Ip地址List
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>();
        keyList.addAll(keySet);

        String server;
        synchronized (pos){
            if(pos>keySet.size()) pos=0;
            server=keyList.get(pos);
            pos++;
        }

        return server;
    }
}
