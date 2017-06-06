package com.gudigudigudi.sb.Util.Balance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Jiade Dai
 * Hash: 根据获取客户端的IP地址，通过hash函数得到的一个数值，用该数值对服务器列表的大小进行取模运算，
 * 得到的结果就是客户端要访问服务器的序号。
 * 优点: 保证了相同客户端IP地址将会被hash到同一台后端服务器，直到后端服务器列表变更，可以在服务消费者与服务提供者之间建立有状态的session会话.
 * 缺点在于：除非集群中服务器的非常稳定，基本不会上下线，否则一旦有服务器上线、下线，那么通过源地址哈希算法路由到的服务器是服务器上线、下线前路由到的服务器的概率非常低，如果是session则取不到session，如果是缓存则可能引发"雪崩"。
 * --->一致性Hash算法
 */
public class Hash {
    public static String getServer(){
        Map<String,Integer> serverMap=new HashMap<String,Integer>();
        serverMap.putAll(IpMap.serverWeightMap);

        Set<String> keySet=serverMap.keySet();
        ArrayList<String> keyList=new ArrayList<String>();
        keyList.addAll(keySet);

        // 在Web应用中可通过HttpServlet的getRemoteIp方法获取.
        String remoteIp = "127.0.0.1";
        int hashCode=remoteIp.hashCode();
        int serverListSize=keyList.size();
        int serverPos=hashCode % serverListSize;

        return keyList.get(serverPos);
    }
}
