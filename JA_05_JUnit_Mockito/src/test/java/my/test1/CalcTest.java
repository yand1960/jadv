package my.test1;

import my.test1.Calc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CalcTest {

    @Test
    public void testPlus() {
        Calc calc = new Calc();
//        System.out.println(calc.plus(1,2));
        int result = calc.plus(1,2);
        Assertions.assertEquals(3,result);
        result = calc.plus(123,124);
        Assertions.assertEquals(247,result);
    }

    @Test
    public void testMinus() {
        Calc calc = new Calc();
//        System.out.println(calc.plus(1,2));
        int result = calc.minus(1,2);
        Assertions.assertEquals(-1,result);
        result = calc.minus(123,124);
        Assertions.assertEquals(-1,result);
    }

}
