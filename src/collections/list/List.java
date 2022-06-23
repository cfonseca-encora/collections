package collections.list;

public interface List<T> {
    void add(T data);
    void insert(int index, T data);
    T getAt(int index);
    void setAt(int index, T data);
    void remove(int index);
    void removeAll();
    Iterator<T> iterator();
    int size();
}
