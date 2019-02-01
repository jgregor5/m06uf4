
package test;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author julian
 */
public class ScheduleTest implements AutoCloseable {
    
    private Timer timer;
    
    public ScheduleTest() {
        this.timer = new Timer();
    }
    
    @Override
    public void close() {
        this.timer.cancel();
    }
            
    private void addTaskEvery5Seconds() {
        this.timer.schedule(new MyTimerTaskOne(), 0, 5000);
    }
    
    private void addTaskIn7Seconds() {
        this.timer.schedule(new MyTimerTaskTwo(), 7000);
    }
        
    private void addTaskAt(int hour, int min, int sec) {        
        this.timer.schedule(new MyTimerTaskThree(), createDate(hour, min, sec));
    }
    
    public static void main(String[] args) {
        
        ScheduleTest test = new ScheduleTest();

        test.addTaskEvery5Seconds();        
        test.addTaskIn7Seconds();
        test.addTaskAt(9, 25, 30);
        
        sleep(60000); // wait 60 seconds before cancelling the timer
        
        test.close();
    }

    // SOME TASKS
    
    public class MyTimerTaskOne extends TimerTask {
        @Override
        public void run() {
            System.out.println("ONE");
        }        
    }

    public class MyTimerTaskTwo extends TimerTask {
        @Override
        public void run() {
            System.out.println("TWO");
        }        
    }

    public class MyTimerTaskThree extends TimerTask {
        @Override
        public void run() {
            System.out.println("THREE");
        }        
    }
    
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
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

}
