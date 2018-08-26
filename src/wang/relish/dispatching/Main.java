package wang.relish.dispatching;

import wang.relish.dispatching.algorithm.Executor;
import wang.relish.dispatching.algorithm.FCFS;
import wang.relish.dispatching.algorithm.SCAN;
import wang.relish.dispatching.algorithm.SSTF;
import wang.relish.dispatching.bean.Task;
import wang.relish.dispatching.common.Direct;
import wang.relish.dispatching.common.Type;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 简便起见:
 * 0 电梯默认初始状态在1层
 * 1 所有楼层都是正整数
 * 2 每层调度所需时间为1s
 *
 * @author relish
 * @since 2018/08/21
 */
public class Main {
    /**
     * 电梯初始位置
     */
    private static final int INIT_POS = 1;
    /**
     * 最底层
     */
    private static final int MIN_FLOOR = 1;
    /**
     * 最高层
     */
    private static final int MAX_FLOOR = 20;
    /**
     * 电梯初始前进方向
     */
    private static final Direct INIT_DIRECT = Direct.UP;
    /**
     * 请求队列
     */
    private static final List<Task> TASKS = Collections.unmodifiableList(new LinkedList<Task>() {
        {
            add(new Task("乘客1", 4, 7));
            add(new Task("乘客2", 8, 10));
            add(new Task("乘客3", 7, 8));
            add(new Task("乘客4", 9, 2));
            add(new Task("乘客5", 10, 7));
            add(new Task("乘客6", 3, 5));

        }
    });

    public static class ExecutorFactory {
        public static Executor create(Type type) {
            switch (type) {
                case FCFS:
                    return new FCFS(TASKS, INIT_POS);
                case SSTF:
                    return new SSTF(TASKS, INIT_POS);
                case SCAN:
                    return new SCAN(TASKS, INIT_POS, INIT_DIRECT, MIN_FLOOR, MAX_FLOOR);
                default:
                    throw new IllegalArgumentException("不存在这样的调度的算法");
            }
        }
    }

    public static void main(String[] args) {
        Executor fcfs = ExecutorFactory.create(Type.FCFS);
        fcfs.exec();

        System.out.println("-----------------------------------------------");

        Executor sstf = ExecutorFactory.create(Type.SSTF);
        sstf.exec();

        System.out.println("-----------------------------------------------");


        Executor scan = ExecutorFactory.create(Type.SCAN);
        scan.exec();
    }
}
