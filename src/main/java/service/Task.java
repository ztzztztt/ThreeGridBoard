package service;

import java.lang.reflect.Field;
import java.util.TimerTask;

/**
 * @author by zhoutao
 * @description 延时任务类
 * @date 2020/11/3 17:03
 */
public abstract class Task extends TimerTask {

    private static long currentTimeMillis = 0;
    /*
     * 利用反射设置其延时的值
     */
    private void setDeclaredField(Class<?> clazz, Object obj, String name, Long value) {
        System.currentTimeMillis();
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            Object object = currentTimeMillis + value;
            field.set(obj, object);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * 动态修改其延时值
     * @param delay 延时的值
     */
    public void dynamicDelay(double delay) {
        setDeclaredField(TimerTask.class, this, "nextExecutionTime", (long)(delay * 2000.0));
    }


    public static void setCurrentTimeMillis(long currentTime){
        currentTimeMillis = currentTime;
    }
}
