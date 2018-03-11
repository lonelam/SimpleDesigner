package graph;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

import static java.lang.Math.*;


public class SimpleLine extends Edge {
    private String textline = "";

    SimpleLine(Point s, Point t) {
        super(s, t);
    }

    public SimpleLine(int X0, int Y0, int X1, int Y1) {
        super(new Point(X0, Y0), new Point(X1, Y1));
    }

    @Override
    public void move(int dx, int dy) {
        s.x += dx;
        s.y += dy;
        t.x += dx;
        t.y += dy;
    }

    @Override
    public void draw(Graphics2D pic, Rectangle bound) {
        double vx = t.x - s.x, vy = t.y - s.y;
        double len = sqrt(vx * vx + vy * vy);
        vx /= len;
        vy /= len;
        double wx = vy, wy = -vx;
        double ox = t.x - vx * 7.5, oy = t.y - vy * 7.5;
        double cx = ox + wx * 5.0, cy = oy + wy * 5.0;
        double dx = ox - wx * 5.0, dy = oy - wy * 5.0;
        int[] xs = new int[]{(int) cx, (int) dx, t.x};
        int[] ys = new int[]{(int) cy, (int) dy, t.y};
        AffineTransform orig = pic.getTransform();

        pic.rotate(atan2(vy, vx), (s.x + t.x) / 2, (s.y + t.y) / 2);
        pic.drawString(textline, (s.x + t.x) / 2, (s.y + t.y) / 2);
        pic.setTransform(orig);
        pic.drawLine(s.x, s.y, t.x, t.y);
    }

    @Override
    public boolean isPointOn(Point mouseP) {
        /*
        先要求矩形范围内
         */
        if (mouseP.getX() >= min(s.x, t.x)
                && mouseP.getX() <= max(s.x, t.x)
                && mouseP.getY() >= min(s.y, t.y)
                && mouseP.getY() <= max(s.y, t.y)
                ) {
            /*
            然后在线附近
            搞不定矩形的判定方式，这里大概会形成一个椭圆形的区域
             */
            if (abs((mouseP.getX() - s.x) * (t.y - s.y) - (mouseP.getY() - s.y) * (t.x - s.x)) < 1000.0) {

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isConflict(Point targetP) {
        return false;
    }

    public void setTextline(String textline) {
        this.textline = textline;
    }


    @Override
    public void pop() {
        textline = JOptionPane.showInputDialog("Type");
        if (!textline.equals("")) {
            textline = "<<" + textline + ">>";
        }
    }

    @Override
    public void highlight() {

    }

    @Override
    public Object cloneElem() {
        return null;
    }
}
