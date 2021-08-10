package my.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeterClient {

    public static void main(String[] args) {
        SlowMeter meter = new SlowMeter();
        List<int[]> results = new ArrayList<>();
        List<Thread> pool = new ArrayList<>();
        Object sync_flag = new Object();

        for(int i=0; i<10; i++) {
            final int j = i;
            Thread thread = new Thread(
                    () -> {
                        int result = meter.measure(j);
                        synchronized (sync_flag) {
                            // Эта строка кода потоко-небезопаснa
                            results.add(new int[]{j, result});
                        }
                    });
            pool.add(thread);
            thread.start();
        }

        boolean all_completed = false;
        while(!all_completed) {
            all_completed = true;
            for (Thread thread : pool) {
                all_completed = all_completed && !thread.isAlive();
            }
        }

        results.stream()
                .forEach(r -> {
                    System.out.println(r[0] + " " + r[1]);
                });
    }

    public static void main5(String[] args) {
        SlowMeter meter = new SlowMeter();
        List<int[]> results = new ArrayList<>();
        Object sync_flag = new Object();
        for(int i=0; i<10; i++) {
            final int j = i;
            Thread thread = new Thread(
                    () -> {
                        int result = meter.measure(j);
                        synchronized (sync_flag) {
                            // Эта строка кода потоко-небезопаснa
                            results.add(new int[]{j, result});
                        }
                    });
            thread.start();
        }

        //Дешево и сердито
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        results.stream()
                .forEach(r -> {
            System.out.println(r[0] + " " + r[1]);
        });
    }

    public static void main4(String[] args) {
        SlowMeter meter = new SlowMeter();
        for(int i=0; i<10; i++) {
            final int j = i;
            Thread thread = new Thread(
                    () -> {
                        int result = meter.measure(j);
                        System.out.println(result);
                    });
            thread.start();
        }
    }

    public static void main3(String[] args) {
        SlowMeter meter = new SlowMeter();
        for(int i=0; i<10; i++) {
            final int j = i;
            Thread thread = new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            int result = meter.measure(j);
                            System.out.println(result);
                        }
                    }
            );
            thread.start();
        }
    }

    public static void main2(String[] args) {
        int[] meters = {1,3,5,7,9};
        SlowMeter meter = new SlowMeter();
        Arrays.stream(meters).forEach(n -> {
            System.out.println(meter.measure(n));
        });
    }

    public static void main1(String[] args) {
        SlowMeter meter = new SlowMeter();
        for(int i=0; i<10; i++) {
            int result = meter.measure(i);
            System.out.println(result);
        }
    }
}
