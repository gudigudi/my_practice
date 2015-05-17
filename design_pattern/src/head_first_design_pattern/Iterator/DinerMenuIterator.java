package head_first_design_pattern.Iterator;

import java.util.Iterator;

/**
 * Created by gudi on 5/17/15.
 */
public class DinerMenuIterator implements Iterator {

    MenuItem[] list;
    int position = 0;

    public DinerMenuIterator(MenuItem[] list) {
        this.list = list;
    }

    public Object next() {
        return new Object();
    }

    public boolean hasNext() {
        return true;
    }

    public void remove() {
        if (position <= 0) {
            throw new IllegalStateException(
                    "You can't remove an item until you've done at least one next()");
        }
        if (list[position-1] != null) {
            for (int i = position - 1; i < (list.length-1); i++) {
                list[i] = list[i+1];
            }
            list[list.length-1] = null;
        }
    }
}
