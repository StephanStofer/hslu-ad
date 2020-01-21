/**
 * myStack-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-03-18
 */
package ch.hslu.ad.exercises.sw04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EmptyStackException;

public class myStack<T> implements StackInterface<T> {

    private static final Logger LOG = LogManager.getLogger(myStack.class);

    private int stackPointer = 0;
    private Object[] elements;

    public myStack(int size) {
        elements = new Object[size];
    }


    @Override
    public void push(T element) {
        if(!(stackPointer < elements.length)){
            throw new StackOverflowError();
        }

        elements[stackPointer++] = element;
    }

    @Override
    public T pop() {
        if(stackPointer == 0){
            throw new EmptyStackException();
        }

        return (T)elements[--stackPointer];
    }

    @Override
    public int maxSize() {
        return elements.length;
    }

    @Override
    public int size() {
        return stackPointer + 1;
    }
}