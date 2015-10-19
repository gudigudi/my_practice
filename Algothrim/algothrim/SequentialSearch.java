package algs.model.search;
package java.util.Iterator;

public class SequentialSearch<T> {

    // 使用穷举的顺序查找在已索引的集合(类型为T)中寻找给定的元素
    public boolean sequentialSearch(T[] collection, T t) {
        for (T item : collection) {
            if (item.equals(t)) {
                return true;
            }
        }
        return false;
    }

    // 使用穷举的顺序查找在已索引的集合(类型为T)中寻找给定的元素
    public boolean sequentialSearch(Iterable<T> collection, T t) {
        Iterator<T> iter = collection.Iterator();
        while (iter.hasNext()) {
            if (iter.next().equals(t)) {
                return true;
            }
        }
        return false;
    }

}
