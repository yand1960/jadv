package my.lambdas;

//Мой собственный функциональный интерфейс

@FunctionalInterface
public interface TransformInteger {
    public Integer doTransform(Integer x);
}
