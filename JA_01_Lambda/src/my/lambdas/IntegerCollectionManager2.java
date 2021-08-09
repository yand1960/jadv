package my.lambdas;

// Пример класса, ожидаюащего передачи в методы экземпляра
// стандартного функционального интрефейса

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class IntegerCollectionManager2 {

    public List<Integer> transform(List<Integer>data,
                                   Function<Integer,Integer> action) {
        List<Integer> results = new ArrayList<Integer>();
        for(int x: data){
            results.add(action.apply(x));
        }
        return results;
    }

    public void doIt(List<Integer>data, Consumer<Integer> action) {
        for(int x: data){
            action.accept(x);
        }
    }

    public List<Integer> filter(List<Integer> data, Predicate<Integer> predicate) {
        List<Integer> results = new ArrayList<Integer>();
        for(int x: data){
           if (predicate.test(x)) {
               results.add(x);
           }
        }
        return results;
    }
}
