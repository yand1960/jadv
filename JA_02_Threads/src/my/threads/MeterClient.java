package my.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class MeterClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Использование Executors и Futures (см. также класс SlowMeter)
        SlowMeter meter = new SlowMeter();
        List<Future<Integer>> results = new ArrayList<>();

        // Асинхронные запуски на клиенте получаются
        // весьма изящными при использовании Futures:
        for(int i=0; i<10; i++) {
            results.add(meter.measureAync(i));
        }

        //Выводим результаты
        for (Future<Integer> future: results){
            System.out.println(future.get());
        }

        // Без этого executor не отключится
        // и приложение работу не закончит:
        //meter.shutdown();
    }

    public static void main9(String[] args) {
        SlowMeter meter = new SlowMeter();
        // Потоко-безопасная коллекция:
        ConcurrentHashMap<Integer, Integer>  results
                                = new ConcurrentHashMap<>();
        List<Thread> pool = new ArrayList<>();

        for(int i=0; i<10; i++) {
            final int j = i;
            Thread thread = new Thread(
                    () -> {
                        int result = meter.measure(j);
                        // Эта строка кода потоко-безопаснa
                        results.put(j, result);
                    });
            pool.add(thread);
            thread.start();
        }

        //Ждем окончания работы всех потоков
        pool.forEach( thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //Вводим результаты:
        results.entrySet().stream()
                .forEach(entry -> {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                });
    }

    public static void main8(String[] args) {
        SlowMeter meter = new SlowMeter();
        List<int[]> results = new ArrayList<>();
        List<Thread> pool = new ArrayList<>();
        //Такой локер более гибок, чем блок syncronized:
        ReentrantLock locker = new ReentrantLock();

        for(int i=0; i<10; i++) {
            final int j = i;
            Thread thread = new Thread(
                    () -> {
                        int result = meter.measure(j);
                        locker.lock();
                        // Эта строка кода потоко-небезопаснa
                        results.add(new int[]{j, result});
                        locker.unlock();
                    });
            pool.add(thread);
            thread.start();
        }
        //Ждем окончания работы всех потоков (оптимальный вариант)
        pool.forEach( thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //Выводим результаты
        results.stream()
                .forEach(r -> {
                    System.out.println(r[0] + " " + r[1]);
                });
    }


    public static void main7(String[] args) {
        SlowMeter meter = new SlowMeter();
        List<Integer[]> results = new ArrayList<>();
        // Держать под контролем созданные потоки
        // - нужное и обычное дело:
        List<Thread> pool = new ArrayList<>();
        Object sync_flag = new Object();

        for(int i=0; i<10; i++) {
            final int j = i;
            Thread thread = new Thread(
                    () -> {
                        int result = meter.measure(j);
                        synchronized (sync_flag) {
                            // Эта строка кода потоко-небезопаснa
                            results.add(new Integer[]{j, result});
                        }
                    });
            pool.add(thread);
            thread.start();
        }

        //Ждем окончания работы всех потоков (оптимальный вариант)
        pool.forEach( thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //Выводим результаты:
        results.stream()
                .sorted((r1, r2) -> r1[0].compareTo(r2[0]) )
                .forEach(r -> {
                    System.out.println(r[0] + " " + r[1]);
                });
    }

    public static void main6(String[] args) {
        SlowMeter meter = new SlowMeter();
        List<int[]> results = new ArrayList<>();
        // Держать под контролем созданные потоки
        // - нужное и обычное дело:
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

        //Ждем окончания работы всех потоков (уже не дешево и не сердито)
        boolean all_completed = false;
        while(!all_completed) {
            all_completed = true;
            for (Thread thread : pool) {
                all_completed = all_completed && !thread.isAlive();
            }
        }

        //Выводим результаты:
        results.stream()
                .forEach(r -> {
                    System.out.println(r[0] + " " + r[1]);
                });
    }

    public static void main5(String[] args) {
        SlowMeter meter = new SlowMeter();
        List<Integer[]> results = new ArrayList<>();
        Object sync_flag = new Object();
        for(int i=0; i<10; i++) {
            final int j = i;
            Thread thread = new Thread(
                    () -> {
                        int result = meter.measure(j);
                        synchronized (sync_flag) {
                            // Эта строка кода потоко-небезопаснa
                            results.add(new Integer[]{j, result});
                        }
                    });
            thread.start();
        }

        //Ждем окончания работы всех потоков:
        //Дешево и сердито
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Теперь (потенциально) можно отсортировать и вывести:
        results.stream()
                .sorted((r1, r2) -> r1[0].compareTo(r2[0]) )
                .forEach(r -> {
            System.out.println(r[0] + " " + r[1]);
        });
    }

    public static void main4(String[] args) {
        //Минимальный асинхронный код (в стиле лямбда)
        // Главный недостаток - несортированный вывод
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
        //Минимальный асинхронный код (традиционный стиль)
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
        // Синхронный код через лямбда (просто спорт)
        int[] meters = {1,3,5,7,9};
        SlowMeter meter = new SlowMeter();
        Arrays.stream(meters).forEach(n -> {
            System.out.println(meter.measure(n));
        });
    }

    public static void main1(String[] args) {
        // синхронный код хорош, пока нет задержек у SlowMeter
        SlowMeter meter = new SlowMeter();
        for(int i=0; i<10; i++) {
            int result = meter.measure(i);
            System.out.println(result);
        }
    }
}
