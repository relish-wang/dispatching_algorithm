package wang.relish.dispatching.bean;

import wang.relish.dispatching.common.Direct;

/**
 * 一次任务请求
 *
 * @author relish
 * @since 2018/08/21
 */
public class Task {

    public String name;
    /**
     * 请求所在楼层
     */
    public int from;
    /**
     * 请求去往楼层
     */
    public int to;

    public Task(String name, int from, int to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    public Direct getDirect() {
        return from - to > 0 ? Direct.DOWN : Direct.UP;
    }


    @Override
    public String toString() {
        return String.format("%s %d->%d", name, from, to);
    }
}
