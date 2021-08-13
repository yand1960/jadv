package my.supercalc;

@FunctionalInterface
public interface ProgressListener {
    public void progress(int percent);
}
