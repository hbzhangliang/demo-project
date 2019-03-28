package cn.com.flaginfo.platform.littleProject.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UtilHelper {

    private final static char UNDERLINE='_';

    private static final PropertiesUtils redisProper = new PropertiesUtils("spring/redis-time-config.properties");


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
                    sb.append(index+String.valueOf(p));
                }
                else {
                    sb.append(index+"_");
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

    private  static SimpleDateFormat sdfYM = new SimpleDateFormat("yyyy-MM");

    private  static SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");

    private  static SimpleDateFormat sdfYMDHM = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    public static String getDateStrYM(Date date){
        if(null==date){
            return null;
        }
        return sdfYM.format(date);
    }


    public static String getDateStrYMDHM(Date date){
        if(null==date){
            return null;
        }
        return sdfYMDHM.format(date);
    }


    public static Date parseDateYMD(String str)throws ParseException{
        return sdfYMD.parse(str);
    }

    public static Date parseDateYMDHM(String str)throws ParseException{
        return sdfYMDHM.parse(str);
    }

    /**
     * 将驼峰转为下横线
     * 比如createBy  ---> create_by
     * @param param
     * @return
     */
    public static String camelToUnderline(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     * @param param
     * @return
     */
    public static String underlineToCamel(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (c==UNDERLINE){
                if (++i<len){
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }



    public static String strToLikeStr(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("%").append(str).append("%");
        return sb.toString();
    }

    /**
     *   [2,3,4]  ---> 2,3,4
     * @param ids
     * @return
     */
    public static String getStrIds(List<Long> ids){
        if(null==ids||ids.isEmpty()){
            return null;
        }
        StringBuilder sb=new StringBuilder();
        for(Long id:ids){
            sb.append(id+",");
        }
        return sb.toString().substring(0,sb.length()-1);
    }


    public static void main(String[] args){
        List<Long> list=new ArrayList<>(4);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        list.add(5L);
        System.out.println(getStrIds(list));

    }


}
