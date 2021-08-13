package my.supercalc;

public class SlowCalc {

    ProgressListener listener;

    public void addProgressListener(ProgressListener listener) {
        this.listener = listener;
    }

    public int plus(int x, int y) {
        for(int i=1; i<=100; i++ ) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (listener != null) {
                listener.progress(i);
            }
        }
        return x + y;
    }

}
