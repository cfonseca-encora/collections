package collections.list;

public interface List {
    void add(String data);
    void insert(int index, String data);
    String getAt(int index);
    void setAt(int index, String data);
    void remove(int index);
    void removeAll();
    Iterator iterator();
    int size();
}
