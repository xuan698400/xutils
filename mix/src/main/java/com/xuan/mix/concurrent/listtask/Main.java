package com.xuan.mix.concurrent.listtask;

import java.util.ArrayList;
import java.util.List;

import com.xuan.mix.concurrent.listtask.callback.AbstractSingleSizeListTaskCallable;
import com.xuan.mix.concurrent.listtask.core.ListTaskException;
import com.xuan.mix.concurrent.listtask.executor.ListTaskExecutor;

/**
 * @author xuan
 * @since 2021/8/16
 */
public class Main {

    private static ListTaskExecutor<OriginClass, TargetClass> listTaskExecutor;

    static {
        listTaskExecutor = ListTaskExecutorFactory.getExecutor();
    }

    public static void main(String[] args) {

        List<OriginClass> originList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            OriginClass oc = new OriginClass();
            oc.setName(i + "");
            originList.add(oc);
        }

        try {
            long start = System.currentTimeMillis();
            List<TargetClass> targetList = listTaskExecutor.execute(originList,
                new AbstractSingleSizeListTaskCallable<OriginClass, TargetClass>() {
                    @Override
                    protected TargetClass call(OriginClass originClass) {
                        sleep();
                        TargetClass tc = new TargetClass();
                        tc.setName(originClass.getName() + "_加工");
                        return tc;
                    }
                });
            System.out.println(targetList);
            System.out.println("耗时：" + (System.currentTimeMillis() - start));
        } catch (ListTaskException e) {
            e.printStackTrace();
        }
    }

    public static class OriginClass {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class TargetClass {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "TargetClass{" +
                "name='" + name + '\'' +
                '}';
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
