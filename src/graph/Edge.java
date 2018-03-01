package graph;

import java.awt.*;

public abstract class Edge extends Element{
    private Point s, t;
    private Edge(){

    }

    /**
     * @param s 箭头起始点
     * @param t 箭头结束点
     */
    public Edge(Point s, Point t){
        this.s = s;
        this.t = t;
    }

    public Point getS() {
        return s;
    }

    public void setS(Point s) {
        this.s = s;
    }

    public Point getT() {
        return t;
    }

    public void setT(Point t) {
        this.t = t;
    }
}
