package utils;

/**
 * @author by zhoutao
 * @description 坐标
 * @date 2020/10/30 20:46
 */
public class Position {
    private int positionX;
    private int positionY;

    public Position(){}

    public Position(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void reset(){
        this.positionX = 0;
        this.positionY = 0;
    }
}
