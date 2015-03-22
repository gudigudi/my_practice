package algs.model.search;

/**
 * 二叉查找处理一个参数类型的有序数组
 *
 * @param参数T必须实现了Comparable接口
 */
public class BinarySearch<T extends Comparable<T>> {
    // 在集合中寻找目标，成功返回真
    public boolean search(T[] collection, T target) {
        // null不存在集合中
        if (target == null) { return false; }

        int low = 0, high = collection.length - 1;
        while (low <= high) {
            int ix = (low + high) / 2;
            int rc = target.compareTo(collection[ix]);

            if (rc < 0) {
                // 目标小于collection[i]
                high = ix - 1;
            } else if (rc > 0){
                // 目标大于collection[i]
                low = ix + 1;
            } else {
                // 找到了
                return true;
            }
        }
        return false;
    }
}
