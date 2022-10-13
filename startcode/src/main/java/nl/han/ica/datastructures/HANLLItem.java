package nl.han.ica.datastructures;

public class HANLLItem<T> {
    T value;
    HANLLItem<T> next;

    public HANLLItem(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public HANLLItem<T> getNext() {
        return next;
    }

    public void setNext(HANLLItem<T> next) {
        this.next = next;
    }
}
