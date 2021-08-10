package my.threads;

public class SlowMeter {
    public int measure(int n) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int result = n * 10;
        return result;
    }
}
