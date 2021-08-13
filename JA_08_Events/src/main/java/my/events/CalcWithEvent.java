package my.events;

import java.util.ArrayList;
import java.util.List;

public class CalcWithEvent {

//    private JackpotListener jackpotListener;
//    public void addJackpotListener(JackpotListener jackpotListener) {
//        this.jackpotListener = jackpotListener;
//    }

    private List<JackpotListener> jackpotListeners = new ArrayList<>();
    public void addJackpotListener(JackpotListener jackpotListener) {
        jackpotListeners.add(jackpotListener);
    }

    protected void onJackpot() {
        jackpotListeners.forEach(listener -> {
            listener.jackPot(1000000.0);
        });
    }

    public int plus(int x, int y) {
        int result = x + y;
//        if (result == 777) {
//            if (jackpotListener != null)
//                jackpotListener.jackPot(1000000.0);
//        }
        if(result == 777) {
            onJackpot();
        }
        return result;
    }

    public int minus(int x, int y) {
        int result = x - y;
        return result;
    }
}
