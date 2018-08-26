package wang.relish.dispatching.algorithm;

import wang.relish.dispatching.bean.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * @author relish
 * @since 2018/08/21
 */
public class SSTF extends Executor {


    public SSTF(List<Task> tasks, int initPos) {
        super(tasks, initPos);
    }

    @Override
    protected void dispatching() {
        int prev = initPos;
        List<Task> visit = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            int min = Integer.MAX_VALUE;
            int shortIndex = 0;
            for (int j = 0; j < taskList.size(); j++) {
                Task task = taskList.get(j);
                if (task == null) continue;
                int curr = Math.abs(task.from - prev);
                if (min > curr) {
                    min = curr;
                    shortIndex = j;
                }
            }
            Task e = taskList.get(shortIndex);
            visit.add(e);
            taskList.set(shortIndex, null);
            prev = e.to;
        }
        taskList.clear();
        taskList.addAll(visit);
    }

    @Override
    protected String algorithmName() {
        return "2)最短寻找楼层时间优先算法（SSTF）:";
    }
}
