package nl.han.ica.datastructures;

public class HANStack<T> implements IHANStack<T> {

    private IHANLinkedList<T> stack;

    public HANStack() {
        stack = new HANLinkedList<T>();
    }


    @Override
    public void push(T value) {
        stack.addFirst(value);
    }

    @Override
    public T pop() {
        if(stack.getFirst() == null){
            return null;
        }else{
            T value = stack.getFirst();
            stack.removeFirst();
            return value;
        }
    }

    @Override
    public T peek() {
        return stack.getFirst();
    }
}
