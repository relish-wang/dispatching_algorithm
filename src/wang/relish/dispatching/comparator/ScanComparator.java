package wang.relish.dispatching.comparator;

import wang.relish.dispatching.bean.Task;
import wang.relish.dispatching.common.Direct;

import java.util.Comparator;

/**
 * @author relish
 * @since 2018/08/22
 */
public class ScanComparator implements Comparator<Task> {
    private final int initPos;
    private final Direct direct;

    public ScanComparator(int initPos, Direct direct) {
        this.initPos = initPos;
        this.direct = direct;
    }

    @Override
    public int compare(Task o1, Task o2) {
        int i = ifInitDirectIsUp(o1, o2);
        if (direct == Direct.UP) {
            return i;
        } else {
            return -i;
        }
    }

    public int ifInitDirectIsUp(Task o1, Task o2) {
        int i = biggerOrSmaller(o1, o2);
        Direct d1 = o1.getDirect();
        Direct d2 = o2.getDirect();
        if (d1 == d2) {
            if (d1 == Direct.UP) {
                if (o1.from > initPos) {
                    return i;
                } else {
                    return 1;
                }
            } else {
                if (o1.from > initPos) {
                    return -i;
                } else {
                    return 1;
                }
            }
        } else {
            if (d1 == Direct.UP) {
                if (o1.from > initPos) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                if (o1.from > initPos) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }


    public int biggerOrSmaller(Task o1, Task o2) {
        if (o1.from > o2.from) {
            return 1;
        } else if (o1.from < o2.from) {
            return -1;
        } else {
            return 0;
        }
    }
}
