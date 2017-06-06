package com.gudigudigudi.sb.Util.Balance;

import java.util.HashMap;

/**
 * @author Jiade Dai
 * IpMap: 使用ip权重来设置负载均衡
 */
public class IpMap {
    // 待路由的Ip列表，Key代表Ip，Value代表该Ip的权重
    public static HashMap<String,Integer> serverWeightMap=new HashMap<String,Integer>();

    static {
        serverWeightMap.put("192.168.1.100",1);
        serverWeightMap.put("192.168.1.101",2);
        serverWeightMap.put("192.168.1.102",3);
        serverWeightMap.put("192.168.1.103",4);
    }
}
