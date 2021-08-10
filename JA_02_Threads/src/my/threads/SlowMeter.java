package my.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SlowMeter {

    //Executorы снимают с девелопера много забот
    // о контроле над запущенными задачами:
    private ExecutorService executor
            = Executors.newFixedThreadPool(20);

    //Симуляция медленного измерения
    public int measure(int n) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int result = n * 10;
        return result;
    }

    //"Асинхронная обертка" над медленным синхронным методом
    //Future в Яве - родственник Task в С# и Promise в JS
    public Future<Integer> measureAync(int n) {
        return executor.submit(() -> {
            return measure(n);
        });
    }

    //В Яве exеcutorы не заканивают работу вместе с основным потоком
    public void shutdown(){
        executor.shutdown();
    }
}
