package ic.doc;

import java.util.concurrent.Callable;


public class TimedTask implements Callable<Long> {

  private Runnable task;

  public TimedTask(Runnable task) {
    this.task = task;
  }

  @Override
  public Long call() {
    long startTime = System.currentTimeMillis();
    try {
      task.run();
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
      throw e;
    }
    long endTime = System.currentTimeMillis();
    return endTime - startTime;
  }
}
