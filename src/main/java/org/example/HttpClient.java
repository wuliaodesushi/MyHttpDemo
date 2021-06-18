package org.example;

import java.io.IOException;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author cx
 * @create 2021-06-18 19:34
 */
public class HttpClient {
    public static Log log = LogFactory.getLog(HttpClient.class);

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 10; i++) {
            postHttpClient(i+1);


        }
    }
    public static void postHttpClient(int threadID){
        Thread readThread = new Thread(){
            public void run(){
                try {
                    Date date = new Date();
                    long startTime = date.getTime();
                    String startTimeString = String.valueOf(startTime);
                    String responseTimeString=HttpClientUtils.postParameters("http://localhost:8888",startTimeString);
                    long endTime = Long.parseLong(responseTimeString.trim());
                    if((endTime-startTime)<(threadID*1000)){
                        String result="第"+threadID+"个请求在规定的时间响应成功";
                        System.out.println(result);
                        log.info(result);
//                        log.error(result);
//                        log.debug(result);
                    }
                    else{
                        String result="第"+threadID+"个请求响应超时";
                        System.out.println(result);
                        log.info(result);
//                        log.error(result);
//                        log.debug(result);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        readThread.start();
    }
}
