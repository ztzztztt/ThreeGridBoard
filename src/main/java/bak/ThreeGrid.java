package bak;

import java.util.Vector;

/**
 * @author by zhoutao
 * @description 三格版类
 * @date 2020/10/30 11:13
 */
public class ThreeGrid {

    Vector<Vector<Piece>> threeGridList = new Vector<>(4);
    int count = 0;

    public ThreeGrid(int pos){
        for (int i=0;i<2;i++){
            Vector<Piece> v = new Vector<>(2);
            for(int j=0;j<2;j++){
                if (i * 2 + j == pos){
                    v.add(null);
                } else{
                    Piece piece = new Piece();
                    piece.setColorKey(pos);
                    v.add(piece);
                }
            }
            threeGridList.add(v);
        }
    }

    public Vector<Vector<Piece>> getThreeGridList() {
        return threeGridList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
