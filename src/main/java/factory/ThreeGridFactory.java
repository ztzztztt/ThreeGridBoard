package factory;

import controller.ThreeGrid;

import java.util.HashMap;

/**
 * @author by zhoutao
 * @description 三格版
 * @date 2020/10/30 20:10
 */
public class ThreeGridFactory {
    static ThreeGridFactory threeGridFactory;
    HashMap<Integer, ThreeGrid> hashMap = new HashMap<>(4);

    public static ThreeGridFactory getThreeGridFactory(){
        if (threeGridFactory == null){
            threeGridFactory = new ThreeGridFactory();
        }
        return threeGridFactory;
    }

    public ThreeGrid getThreeGrid(int pos){
        if (hashMap.get(pos) == null){
            hashMap.put(pos, new ThreeGrid(pos));
        }
        return hashMap.get(pos);
    }
}
