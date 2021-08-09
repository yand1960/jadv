package my.lambdas;

import java.util.ArrayList;
import java.util.List;

// "Классический" класс, который принимает данные в качестве аргументов
public class IntegerCollectionManager {

    public List<Integer> squares(List<Integer> data) {
        List<Integer> results = new ArrayList<Integer>();
        for(int x: data){
            results.add(x * x);
        }
        return results;
    }
}
