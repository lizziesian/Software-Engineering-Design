package ic.doc;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DelayExamples {

  public static void main(String[] args) throws Exception {

    ExecutorService executorService = Executors.newFixedThreadPool(8);

    /*
    Using 1 thread the delays take 24084ms to complete
    Using 2 threads the delays take 12061ms to complete
    Using 4 thread the delays take 6039ms to complete
    Using 8 threads the delays take 4039ms to complete
    */

    List<Delay> delays = List.of(
        Delay.of(2, SECONDS),
        Delay.of(2, SECONDS),
        Delay.of(2, SECONDS),
        Delay.of(2, SECONDS),
        Delay.of(4, SECONDS),
        Delay.of(4, SECONDS),
        Delay.of(4, SECONDS),
        Delay.of(4, SECONDS)
    );

    final long startTime = System.currentTimeMillis();
    long processingTime = 0;

    for (Delay delay : delays) {
      TimedTask timedTask = new TimedTask(delay);
      Future<Long> future = executorService.submit(timedTask);
      processingTime += future.get();
    }

    executorService.shutdown();

    executorService.awaitTermination(25, SECONDS);

    long endTime = System.currentTimeMillis();

    long elapsedTime = endTime - startTime;
    System.out.printf("Total elapsed time: %dms%n", elapsedTime);
    System.out.printf("Total processing time: %dms%n", processingTime);
  }
}
