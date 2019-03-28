package cn.com.flaginfo.platform.littleProject.utils;

import cn.com.flaginfo.platform.littleProject.mongo.models.BaseMongoDbModel;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class UtilHelper {

    private final static char UNDERLINE='_';

    private static final PropertiesUtils redisProper = new PropertiesUtils("spring/redis-time-config.properties");


    public static void  main(String[] args){

    }

    /**
     * 连接字符串  z作为redis的key值
     * @param args
     * @return
     */
    public static String contacsString(Object ...args){
        if(args!=null&&args.length>0) {
            int index=0;
            StringBuilder sb=new StringBuilder();
            for (Object p : args) {
                if (null != p&& StringUtils.isNotBlank(String.valueOf(p))) {
                    sb.append(index+":"+String.valueOf(p));
                }
                else {
                    sb.append(index+":");
                }
                index++;
            }
            return sb.toString();
        }
        return null;
    }

    public static String contacsStringObj(Object[] args){
        if(args!=null&&args.length>0) {
            int index=0;
            StringBuilder sb=new StringBuilder();
            for (Object p : args) {
                if (null != p&& StringUtils.isNotBlank(String.valueOf(p))) {
                    sb.append(index+":"+String.valueOf(p));
                }
                else {
                    sb.append(index+":");
                }
                index++;
            }
            return sb.toString();
        }
        return null;
    }



    public static TimeUnit getTimeUtil(String type){
        if(StringUtils.isEmpty(type)) return TimeUnit.SECONDS;
        TimeUnit result=null;
        switch (type){
            case "S":result =TimeUnit.SECONDS;break;
            case "M":result =TimeUnit.MINUTES;break;
            case "H":result=TimeUnit.HOURS;break;
            case "D":result=TimeUnit.DAYS;break;
            default:result=TimeUnit.SECONDS;
        }
        return result;
    }

    /**
     * 将 redis-time-config改成按秒计算的时间
     * @param str
     * @return
     */
    public static int timeUtlToInt(String str){
        int result=0;
        String[] redispropers=redisProper.getPropertiesValues(str);
        int p=Integer.parseInt(redispropers[0]);
        String type=redispropers[1];
        switch (type){
            case "S":result=p;break;
            case "M":result=60*p;break;
            case "H":result=3600*p;break;
            case "D":result=3600*24*p;break;
            default:result=p;break;
        }
        return result;
    }


    //获取当前时间的String值
    public static String getNowStrTime(){
        return sdf.format(new Date());
    }

    private  static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private  static SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");

    private  static SimpleDateFormat sdfYMDHM = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    public static Date parseDateYMD(String str)throws ParseException{
        return sdfYMD.parse(str);
    }

    public static Date parseDateYMDHM(String str)throws ParseException{
        return sdfYMDHM.parse(str);
    }


    public static Boolean checkBlankExist(String... list){
        Boolean flag=false;
        for(String item:list){
            if(StringUtils.isBlank(item)){
                flag=true;
                break;
            }
        }
        return flag;
    }


    public static synchronized String sycnOnjectId(){
        return ObjectId.get().toString();
    }




    public static Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 得到类对象
        Class userCla = (Class) obj.getClass();
        List<Field> list=new ArrayList<>(20);
        /* 得到类中的所有属性集合 */
        List<Field> listField = Arrays.asList(userCla.getDeclaredFields());
        list.addAll(listField);
        if(null!=userCla.getSuperclass()){
            List<Field> tmp=Arrays.asList(userCla.getSuperclass().getDeclaredFields());
            list.addAll(tmp);
        }

        list.forEach(f->{
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = new Object();
            try {
                if(!f.getName().equals(f.getName().toUpperCase())&&null!=val) {
                    val = f.get(obj);
                    // 得到此属性的值
                    map.put(f.getName(), val);// 设置键值
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return map;
    }


    public static String generateRedisKey(Object[] params,String redisTimeKey){
        if(params==null||params.length<1){
            return redisTimeKey;
        }
        Object[] tmpParams=new Object[params.length+1];
        tmpParams[0]=redisTimeKey;
        int i=1;
        for(Object item:params){
            if(item!=null) {
                if (item instanceof BaseMongoDbModel) {
                    tmpParams[i] = md5(item.toString());
                } else {
                    tmpParams[i] = item.toString();
                }
            }
            else {
                tmpParams[i]=null;
            }
            i++;
        }
        return contacsString(tmpParams);
    }

    public static int greateZero(int count){
        return count>0?count:0;
    }


    public static String md5(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("md5");
            md5.update(str.getBytes("UTF-8"));
            byte[] md5Bytes = md5.digest();
            StringBuilder hexValue = new StringBuilder();
            for (byte by : md5Bytes) {
                int val = ((int) by) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }




}
