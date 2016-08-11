package com.hx.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.danga.MemCached.MemCachedClient;
import com.hx.test.KeysBean;

public class MemcachedUtils {
    private static MemCachedClient cachedClient;  
    static {  
        if (cachedClient == null)  
            cachedClient = new MemCachedClient("memcachedPool");  
    }  
  
    private MemcachedUtils() {}  
  
    /** 
     * 向缓存添加新的键值对。如果键已经存在，则之前的值将被替换。 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     * @return 
     */  
    public static boolean set(String key, Object value) {  
        return setExp(key, value, null);  
    }  
  
    /** 
     * 向缓存添加新的键值对。如果键已经存在，则之前的值将被替换。 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     * @param expire 
     *            过期时间 New Date(1000*10)：十秒后过期 
     * @return 
     */  
    public static boolean set(String key, Object value, Date expire) {  
        return setExp(key, value, expire);  
    }  
  
    /** 
     * 向缓存添加新的键值对。如果键已经存在，则之前的值将被替换。 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     * @param expire 
     *            过期时间 New Date(1000*10)：十秒后过期 
     * @return 
     */  
    private static boolean setExp(String key, Object value, Date expire) {  
        boolean flag = false;  
        try {  
            flag = cachedClient.set(key, value, expire);  
        } catch (Exception e) {  
            // 记录Memcached日志  
            MemcachedLog.writeLog("Memcached set方法报错，key值：" + key + "\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * 仅当缓存中不存在键时，add 命令才会向缓存中添加一个键值对。 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     * @return 
     */  
    public static boolean add(String key, Object value) {  
        return addExp(key, value, null);  
    }  
  
    /** 
     * 仅当缓存中不存在键时，add 命令才会向缓存中添加一个键值对。 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     * @param expire 
     *            过期时间 New Date(1000*10)：十秒后过期 
     * @return 
     */  
    public static boolean add(String key, Object value, Date expire) {  
        return addExp(key, value, expire);  
    }  
  
    /** 
     * 仅当缓存中不存在键时，add 命令才会向缓存中添加一个键值对。 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     * @param expire 
     *            过期时间 New Date(1000*10)：十秒后过期 
     * @return 
     */  
    private static boolean addExp(String key, Object value, Date expire) {  
        boolean flag = false;  
        try {  
            flag = cachedClient.add(key, value, expire);  
        } catch (Exception e) {  
            // 记录Memcached日志  
            MemcachedLog.writeLog("Memcached add方法报错，key值：" + key + "\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * 仅当键已经存在时，replace 命令才会替换缓存中的键。 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     * @return 
     */  
    public static boolean replace(String key, Object value) {  
        return replaceExp(key, value, null);  
    }  
  
    /** 
     * 仅当键已经存在时，replace 命令才会替换缓存中的键。 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     * @param expire 
     *            过期时间 New Date(1000*10)：十秒后过期 
     * @return 
     */  
    public static boolean replace(String key, Object value, Date expire) {  
        return replaceExp(key, value, expire);  
    }  
  
    /** 
     * 仅当键已经存在时，replace 命令才会替换缓存中的键。 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     * @param expire 
     *            过期时间 New Date(1000*10)：十秒后过期 
     * @return 
     */  
    private static boolean replaceExp(String key, Object value, Date expire) {  
        boolean flag = false;  
        try {  
            flag = cachedClient.replace(key, value, expire);  
        } catch (Exception e) {  
            MemcachedLog.writeLog("Memcached replace方法报错，key值：" + key + "\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * get 命令用于检索与之前添加的键值对相关的值。 
     *  
     * @param key 
     *            键 
     * @return 
     */  
    public static Object get(String key) {  
        Object obj = null;  
        try {  
            obj = cachedClient.get(key);  
        } catch (Exception e) {  
            MemcachedLog.writeLog("Memcached get方法报错，key值：" + key + "\r\n" + exceptionWrite(e));  
        }  
        return obj;  
    }  
  
    /** 
     * 删除 memcached 中的任何现有值。 
     *  
     * @param key 
     *            键 
     * @return 
     */  
    public static boolean delete(String key) {  
        return deleteExp(key, null);  
    }  
  
    /** 
     * 删除 memcached 中的任何现有值。 
     *  
     * @param key 
     *            键 
     * @param expire 
     *            过期时间 New Date(1000*10)：十秒后过期 
     * @return 
     */  
    public static boolean delete(String key, Date expire) {  
        return deleteExp(key, expire);  
    }  
  
    /** 
     * 删除 memcached 中的任何现有值。 
     *  
     * @param key 
     *            键 
     * @param expire 
     *            过期时间 New Date(1000*10)：十秒后过期 
     * @return 
     */  
    @SuppressWarnings("deprecation")
	private static boolean deleteExp(String key, Date expire) {  
        boolean flag = false;  
        try {  
            flag = cachedClient.delete(key, expire);  
        } catch (Exception e) {  
            MemcachedLog.writeLog("Memcached delete方法报错，key值：" + key + "\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * 清理缓存中的所有键/值对 
     *  
     * @return 
     */  
    public static boolean flashAll() {  
        boolean flag = false;  
        try {  
            flag = cachedClient.flushAll();  
        } catch (Exception e) {  
            MemcachedLog.writeLog("Memcached flashAll方法报错\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * 返回异常栈信息，String类型 
     *  
     * @param e 
     * @return 
     */  
    private static String exceptionWrite(Exception e) {  
        StringWriter sw = new StringWriter();  
        PrintWriter pw = new PrintWriter(sw);  
        e.printStackTrace(pw);  
        pw.flush();  
        return sw.toString();  
    }  
  
    /**
     * 获取memcached中所有的key,没什么用
     */
	public static Map<String, Map<String, String>> getAllKeys_unuser(){
    	try{
    		return cachedClient.statsItems();
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
	
	
	/**
	 * 获取memcached中所有的key
	 */
    
	public static List<String> getAllKeys(){
		try{
			List<String> list = new ArrayList<String>();  
			Map<String, Map<String, String>> items = cachedClient.statsItems();  
			for (Iterator<String> itemIt = items.keySet().iterator(); itemIt.hasNext();) {  
			    String itemKey = itemIt.next();  
			    Map<String, String> maps = items.get(itemKey);  
			    for (Iterator<String> mapsIt = maps.keySet().iterator(); mapsIt.hasNext();) {  
			        String mapsKey = mapsIt.next();  
			           String mapsValue = maps.get(mapsKey);  
			           if (mapsKey.endsWith("number")) {  //memcached key 类型  item_str:integer:number_str  
			            String[] arr = mapsKey.split(":");  
			               int slabNumber = Integer.valueOf(arr[1].trim());  
			               int limit = Integer.valueOf(mapsValue.trim());  
			               Map<String, Map<String, String>> dumpMaps = cachedClient.statsCacheDump(slabNumber, limit);  
			               for (Iterator<String> dumpIt = dumpMaps.keySet().iterator(); dumpIt.hasNext();) {  
			                   String dumpKey = dumpIt.next();  
			                   Map<String, String> allMap = dumpMaps.get(dumpKey);  
			                   for (Iterator<String> allIt = allMap.keySet().iterator(); allIt.hasNext();) {  
			                       String allKey = allIt.next();  
			                       list.add(allKey.trim());  
			                   }  
			               }  
			           }  
			    }  
			}  
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
    
    /** 
     *  
     * @ClassName: MemcachedLog 
     * @Description: Memcached日志记录 
     * @author yinjw 
     * @date 2014-6-18 下午5:01:37 
     *  
     */  
    private static class MemcachedLog {  
        private final static String MEMCACHED_LOG = "D:\\memcached.log";  
        private final static String LINUX_MEMCACHED_LOG = "/usr/local/logs/memcached.log";  
        private static FileWriter fileWriter;  
        private static BufferedWriter logWrite;  
        // 获取PID，可以找到对应的JVM进程  
        private final static RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();  
        private final static String PID = runtime.getName();  
  
        /** 
         * 初始化写入流 
         */  
        static {  
            try {  
                String osName = System.getProperty("os.name");  
                if (osName.indexOf("Windows") == -1) {  
                    fileWriter = new FileWriter(MEMCACHED_LOG, true);  
                } else {  
                    fileWriter = new FileWriter(LINUX_MEMCACHED_LOG, true);  
                }  
                logWrite = new BufferedWriter(fileWriter);  
            } catch (IOException e) {  
                closeLogStream();  
            }  
        }  
  
        /** 
         * 写入日志信息 
         *  
         * @param content 
         *            日志内容 
         */  
        public static void writeLog(String content) {  
            try {  
                logWrite.write("[" + PID + "] " + "- [" + new SimpleDateFormat("yyyy年-MM月-dd日 hh时:mm分:ss秒").format(new Date().getTime()) + "]\r\n"  
                        + content);  
                logWrite.newLine();  
                logWrite.flush();  
            } catch (IOException e) {  
            }  
        }  
  
        /** 
         * 关闭流 
         */  
        private static void closeLogStream() {  
            try {  
                fileWriter.close();  
                logWrite.close();  
            } catch (IOException e) {  
            }  
        }  
    }
    
    
    
    
    /**
     * 网上找的例子，获取所有的key，看看 正确不正确
     */
    public static Map<String,KeysBean> getKeysForMap() throws UnsupportedEncodingException{  
        Map<String,KeysBean> keylist=new HashMap<String,KeysBean>();  
        //遍历statsItems 获取items:2:number=14  
        MemCachedClient mcc = new MemCachedClient("memcachedPool");  
        Map<String,Map<String,String>> statsItems=mcc.statsItems();  
        Map<String,String> statsItems_sub=null;  
        String statsItems_sub_key=null;  
        int items_number=0;  
        String server=null;  
        //根据items:2:number=14，调用statsCacheDump，获取每个item中的key  
        Map<String,Map<String,String>> statsCacheDump=null;  
        Map<String,String> statsCacheDump_sub=null;  
        String statsCacheDumpsub_key=null;  
        String statsCacheDumpsub_key_value=null;  
           
        for (Iterator iterator=statsItems.keySet().iterator();iterator.hasNext();) {  
            server=(String) iterator.next();  
            statsItems_sub=statsItems.get(server);  
            //System.out.println(server+"==="+statsItems_sub);  
            for (Iterator iterator_item=statsItems_sub.keySet().iterator();iterator_item.hasNext();) {  
                statsItems_sub_key=(String) iterator_item.next();                 
                //System.out.println(statsItems_sub_key+":=:"+bb);  
                //items:2:number=14  
                if (statsItems_sub_key.toUpperCase().startsWith("items:".toUpperCase()) && statsItems_sub_key.toUpperCase().endsWith(":number".toUpperCase())){  
                    items_number=Integer.parseInt(statsItems_sub.get(statsItems_sub_key).trim());  
                    //System.out.println(statsItems_sub_key+":=:"+items_number);  
                    statsCacheDump=mcc.statsCacheDump(new String[]{server},Integer.parseInt(statsItems_sub_key.split(":")[1].trim()), items_number);  
                       
                    for (Iterator statsCacheDump_iterator=statsCacheDump.keySet().iterator();statsCacheDump_iterator.hasNext();) {  
                        statsCacheDump_sub=statsCacheDump.get(statsCacheDump_iterator.next());  
                        //System.out.println(statsCacheDump_sub);  
                        for (Iterator iterator_keys=statsCacheDump_sub.keySet().iterator();iterator_keys.hasNext();) {  
                            statsCacheDumpsub_key=(String) iterator_keys.next();  
                            statsCacheDumpsub_key_value=statsCacheDump_sub.get(statsCacheDumpsub_key);                            
                            //System.out.println(statsCacheDumpsub_key);//key是中文被编码了,是客户端在set之前编码的，服务端中文key存的是密文  
                            //System.out.println(statsCacheDumpsub_key_value);  
                               
                            keylist.put(URLDecoder.decode(statsCacheDumpsub_key, "UTF-8"), new KeysBean(server,Long.parseLong(statsCacheDumpsub_key_value.substring(1, statsCacheDumpsub_key_value.indexOf("b;")-1).trim()),Long.parseLong(statsCacheDumpsub_key_value.substring(statsCacheDumpsub_key_value.indexOf("b;")+2,statsCacheDumpsub_key_value.indexOf("s]")-1).trim())));  
                        }  
                    }  
                }  
   
            }  
        }  
        return keylist;  
    }  
    
    
    
    //另一个获取memecached中所有key的方法
    /** 
	* 获取服务器上面所有的key 
	* @return 
	* @author liu787427876@126.com 
	* @date 2013-12-4 
	*/  
	public static List<String> getAllKeys(MemCachedClient memCachedClient) {  
		List<String> list = new ArrayList<String>();  
		Map<String, Map<String, String>> items = memCachedClient.statsItems();  
		for (Iterator<String> itemIt = items.keySet().iterator(); itemIt.hasNext();) {  
		    String itemKey = itemIt.next();  
		    Map<String, String> maps = items.get(itemKey);  
		    for (Iterator<String> mapsIt = maps.keySet().iterator(); mapsIt.hasNext();) {  
		        String mapsKey = mapsIt.next();  
		           String mapsValue = maps.get(mapsKey);  
		           if (mapsKey.endsWith("number")) {  //memcached key 类型  item_str:integer:number_str  
		            String[] arr = mapsKey.split(":");  
		               int slabNumber = Integer.valueOf(arr[1].trim());  
		               int limit = Integer.valueOf(mapsValue.trim());  
		               Map<String, Map<String, String>> dumpMaps = memCachedClient.statsCacheDump(slabNumber, limit);  
		               for (Iterator<String> dumpIt = dumpMaps.keySet().iterator(); dumpIt.hasNext();) {  
		                   String dumpKey = dumpIt.next();  
		                   Map<String, String> allMap = dumpMaps.get(dumpKey);  
		                   for (Iterator<String> allIt = allMap.keySet().iterator(); allIt.hasNext();) {  
		                       String allKey = allIt.next();  
		                       list.add(allKey.trim());  
		
		                   }  
		               }  
		           }  
		    }  
		}  
		return list;  
	}   
	
	
	
	
	//自己写的用来测试的东西
	public static Object testMem(String key,String value) {  
        Object obj = null;  
        try { 
        	System.out.println(cachedClient.addOrDecr(key));
        	System.out.println(cachedClient.getCounter(key));
        	System.out.println(cachedClient.sync(key));
        }catch(Exception e){
        	e.printStackTrace();
        }
        return obj;
	}
	
	
	
}
