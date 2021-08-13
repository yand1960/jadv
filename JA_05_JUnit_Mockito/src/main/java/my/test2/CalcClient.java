package my.test2;

public class CalcClient {

    Calcable calc;

    public CalcClient(Calcable calc) {
        this.calc = calc;
    }

    public void showResults(int x, int y) {
        int result = calc.plus(x, y);
        System.out.println("Result: " + result);
    }
}
