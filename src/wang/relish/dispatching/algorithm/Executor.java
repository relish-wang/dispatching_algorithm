package wang.relish.dispatching.algorithm;

import wang.relish.dispatching.bean.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * @author relish
 * @since 2018/08/21
 */
public abstract class Executor {
    protected final List<Task> taskList;
    protected final int initPos;

    public Executor(List<Task> tasks, int initPos) {
        this.taskList = new ArrayList<>(tasks);
        this.initPos = initPos;
    }

    /**
     * 进行调度(排序)
     */
    protected abstract void dispatching();

    /**
     * 调度算法名称
     *
     * @return eg. FCFS,SSTF
     */
    protected abstract String algorithmName();

    public void exec() {
        System.out.println(algorithmName());
        System.out.format("电梯当前位于第%s层, 对如下乘客进行服务:\n", initPos);
        for (Task task : taskList) {
            System.out.format("%d->%d ", task.from, task.to);
        }
        System.out.println("\n请求次序     服务乘客    电梯移动楼层数");

        dispatching();

        int totalTime = 0;
        int prev = initPos;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            int abs = Math.abs(task.from - prev);
            totalTime += abs;
            int d = Math.abs(task.from - task.to);
            totalTime += d;
            prev = task.to;
            System.out.format("   %d     %s:%2d->%2d       %2d\n", i, task.name, task.from, task.to, abs + d);
        }
        System.out.println("总移动距离: " + totalTime);
        double ave = totalTime / 1.0 / taskList.size();
        System.out.format("平均每次服务的距离: %.1f\n", ave);
    }
}
