package my.threads;

public class MeterClient {
    public static void main(String[] args) {
        SlowMeter meter = new SlowMeter();
        for(int i=0; i<10; i++) {
            int result = meter.measure(i);
            System.out.println(result);
        }
    }
}
