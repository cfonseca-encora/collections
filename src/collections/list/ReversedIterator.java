package collections.list;

public interface ReversedIterator<T> {
    boolean hasPrevious();
    T previous();
}
