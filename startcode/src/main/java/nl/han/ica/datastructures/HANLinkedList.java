package nl.han.ica.datastructures;

public class HANLinkedList <T> implements IHANLinkedList{

    private HANLLItem<T> first;

    @Override
    public void addFirst(Object value) {
        HANLLItem<T> newFirst = new HANLLItem<T>((T) value);
        newFirst.setNext(first);
        first = newFirst;
    }

    @Override
    public void clear() {
        first = null;
    }

    @Override
    public void insert(int index, Object value) {
        HANLLItem<T> newItem = new HANLLItem<T>((T) value);
        HANLLItem<T> itemBefore = getItem(index - 1, first);
        newItem.setNext(itemBefore.getNext());
        itemBefore.setNext(newItem);
    }

    @Override
    public void delete(int pos) {
        HANLLItem<T> itemBefore = getItem(pos - 1, first);
        itemBefore.setNext(itemBefore.getNext().getNext());
    }

    @Override
    public Object get(int pos) {
        return getItem(pos, first).getValue();
    }

    @Override
    public void removeFirst() {
        first = first.getNext();
    }

    @Override
    public Object getFirst() {
        if(first == null){
            return null;
        }else{
            return first.getValue();
        }
    }

    @Override
    public int getSize() {
        int size = 0;
        HANLLItem<T> current = first;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }

    private HANLLItem<T> getItem(int posDiff, HANLLItem<T> current) {
        if(posDiff == 0){
            return current;
        }
        return getItem(posDiff - 1, current.getNext());
    }
}
