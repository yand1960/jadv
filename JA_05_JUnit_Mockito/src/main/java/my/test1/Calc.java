package my.test1;

import my.test2.Calcable;

public class Calc implements Calcable {

    @Override
    public int plus(int x, int y) {
        return x + y;
    }

    @Override
    public int minus(int x, int y) {
        return x + y;
    }
}
