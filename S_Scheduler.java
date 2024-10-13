import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Example to start scheduled virtual thread
 */
public class S_Scheduler {
  public static void main(String[] args) {
    scheduler2();
  }

  // NON CORRETTO
  static void scheduler1() {
    var scheduler = Executors.newScheduledThreadPool(1, Thread.ofVirtual().factory());
    scheduler.scheduleAtFixedRate(() -> task1(), 0, 1, TimeUnit.SECONDS);
    scheduler.scheduleAtFixedRate(() -> task2(), 0, 5, TimeUnit.SECONDS);
    try {
      Thread.sleep(30000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  static ScheduledExecutorService scheduler;
  static ExecutorService donkey;

  static void scheduler2() {
    scheduler = Executors.newSingleThreadScheduledExecutor();
    donkey = Executors.newVirtualThreadPerTaskExecutor();

    schedule(() -> task1(), 0, 1, TimeUnit.SECONDS);
    schedule(() -> task2(), 0, 5, TimeUnit.SECONDS);
  }

  static void schedule(Runnable command, long delay, long period, TimeUnit unit) {
    scheduler.scheduleAtFixedRate(() -> donkey.execute(command), delay, period, unit);
  }

  static void task1() {
    System.out.println("MULE1");
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
    }
  }

  static void task2() {
    System.out.println("MULE2 ---");
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
    }
  }

}
