package com.android.loushi.loushi.util;

import com.android.loushi.loushi.jsonbean.UserMessageJson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by binpeiluo on 2016/7/31 0031.
 */
public class DateUtils {


    /*
    * 获取输入时间   如   22:37
    * */
    public static final String calulateTime(String date) {
        String[] temp = date.replace("-","/").split("T");
        String[] strs=temp[1].split(":");
        return strs[0]+":"+strs[1];
    }

    /*
    * 通过输入当前时间判断距离时间
    * */
    public static final String calulateDate(String date) {
        String[] temp = date.replace("-","/").split("T");
        String result=temp[0];
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
//        Date begin = new Date(date.replace("T"," "));
        Date begin= null;
        try {
            begin = dfs.parse(date.replace("T"," "));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date end=new Date(System.currentTimeMillis());
        Calendar cBegin=Calendar.getInstance();
        Calendar cEnd=Calendar.getInstance();
        cBegin.setTime(begin);
        cEnd.setTime(end);
        int beginDay=cBegin.get(Calendar.DAY_OF_YEAR);
        int beginMonth=cBegin.get(Calendar.MONTH);
        int beginYear=cBegin.get(Calendar.YEAR);
        int endDay=cEnd.get(Calendar.DAY_OF_YEAR);
        int endMonth=cEnd.get(Calendar.MONTH);
        int endYear=cEnd.get(Calendar.YEAR);

        if(endDay-beginDay==0)
            result="今天";
        else if(endDay-beginDay==1)
            result="昨天";
        else if(endDay-beginDay==2)
            result="前天";
        else if((endYear-beginYear==1)
                &&(endMonth-beginMonth==-11)
                &&(cEnd.get(Calendar.DAY_OF_MONTH)-cBegin.get(Calendar.DAY_OF_MONTH)==-30))
            result="昨天";
        return result;

    }



    /**
     *  解析我的消息list  按照日期被分为新评论与旧评论
     * @param myMessageList
     * @return  返回新评论的条数
     * @throws ParseException
     */
    public static final int parseMessage(List<UserMessageJson.BodyBean> myMessageList) throws ParseException{
        int result=0;
        String passTimeStr=null;
        SimpleDateFormat dfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate=new Date();
        Calendar nowCalendar=Calendar.getInstance();
        Calendar passCalendar=Calendar.getInstance();
        int nowDay;
        int passDay;
        nowDay=nowCalendar.get(Calendar.DAY_OF_YEAR);
        nowCalendar.setTime(nowDate);
        for (UserMessageJson.BodyBean message:myMessageList) {
            passTimeStr=message.getCDate().replace("T"," ");
            passCalendar.setTime(dfs.parse(passTimeStr));
            passDay=passCalendar.get(Calendar.DAY_OF_YEAR);
            if(nowDay==passDay){
                result+=1;
            }
        }
        return result;
    }

}
