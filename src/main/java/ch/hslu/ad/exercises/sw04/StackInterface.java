package ch.hslu.ad.exercises.sw04;

public interface StackInterface<T> {

    void push(T element);

    T pop();

    int maxSize();

    int size();
}
