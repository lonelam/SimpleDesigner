package graph;

import java.awt.*;
import java.io.Serializable;

import static java.lang.Math.sqrt;

public abstract class Edge extends Element {
    protected Point s;
    protected Point t;
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

    public void shrink(Graph g){
        double vx = t.x - s.x, vy = t.y - s.y;
        double len = sqrt(vx * vx + vy * vy);
        vx /= len;
        vy /= len;

        double tsx = s.x, tsy = s.y;
        double ttx = t.x, tty = t.y;
        boolean flg = false;
        do {
            flg = false;
            for (Element e : g.getElems()) {
                if (e != this) {
                    if (e.isConflict(new Point((int) tsx, (int) tsy))) {
                        tsx += vx;
                        tsy += vy;
                        flg = true;
                    }
                    if (e.isConflict(new Point((int) ttx, (int) tty))) {
                        ttx -= vx;
                        tty -= vy;
                        flg = true;
                    }
                }
            }
        }while(flg);
        s.setLocation(tsx , tsy );
        t.setLocation(ttx, tty );
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        Edge ret = (Edge)super.clone();
        ret.s = (Point) s.clone();
        ret.t = (Point) t.clone();
        return ret;
    }
}
