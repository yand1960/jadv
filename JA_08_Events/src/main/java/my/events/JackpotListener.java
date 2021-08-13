package my.events;

@FunctionalInterface
public interface JackpotListener {
    void jackPot(Double amount);
}
