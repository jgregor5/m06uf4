package test;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author julian
 */
public class ScheduleTest {
    
    private static int count = 0;
    private static Timer timer;
    
    public static void main(String[] args) {

        timer = new Timer();
        timer.schedule(new MyTimerTask("message 1"), createDate(17, 39, 0));
        timer.schedule(new MyTimerTask("message 2"), createDate(17, 39, 10));
        timer.schedule(new MyTimerTask("message 3"), createDate(17, 39, 20));
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
        }
    }

    public static void writeMessage(String message) {
        System.out.println(new Date() + ": " + message);
        if (--count == 0) {
            System.out.println("done!");
            timer.cancel();
        }
    }
    
    private static Date createDate(int hour, int mins, int secs) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, mins);
        calendar.set(Calendar.SECOND, secs);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    
    public static class MyTimerTask extends TimerTask {

        private final String message;

        public MyTimerTask(String message) {
            ScheduleTest.count ++;
            this.message = message;
        }
        
        @Override
        public void run() {
            writeMessage(message);
        }        
    }
}
