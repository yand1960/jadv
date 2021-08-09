package my.lambdas;

// Пример класса, ожидеюащего предачи в методы экземлпяра
// функционального интерфейса. Интерес не в самом классе,
// а в том, как им пользоваться из клиенсткого приложения

import java.util.ArrayList;
import java.util.List;

public class IntegerCollectionManager1 {

    public List<Integer> transform(List<Integer>data, TransformInteger action) {
        List<Integer> results = new ArrayList<>();
        for(int x: data){
            results.add(action.doTransform(x));
        }
        return results;
    }
}
