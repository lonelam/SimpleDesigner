package graph;

import drawer.DrawSelf;

import java.awt.*;
import java.util.Vector;

public class Graph implements DrawSelf{
    private Vector<Element> elems = new Vector<>();
    private Vector<AutoEdge> fictions = new Vector<>();
    private Rectangle boundBuffer = new Rectangle(0, 0);

    public Graph() {
        /*
        默认初始图，测试用，后期删除
         */
        elems.add(new RectNode(100, 50, 500, 430));
//        elems.add(new SimpleArrow(110, 60, 510, 440));
    }

    @Override
    public void draw(Graphics2D pic, Rectangle bound) {
        for (Element e: elems){
            e.draw(pic, bound);
        }
        boundBuffer = bound;
    }

    @Override
    public boolean isPointOn(Point mouseP) {
        if (mouseP.getX() >= boundBuffer.getX() && mouseP.getY() >= boundBuffer.getY()
                && mouseP.getX() <= boundBuffer.getMaxX() && mouseP.getY() <= boundBuffer.getMaxY()) {
            return true;
        }
        return false;
    }

    public Element focus(Point mouseP) {
        for (Element e: elems){
            if (e.isPointOn(mouseP)){
                return e;
            }
        }
        for (AutoEdge e: fictions){
            if (e.isPointOn(mouseP)){
                return e;
            }
        }
        return null;
    }

    @Override
    public boolean isConflict(Point targetP) {
        return false;
    }

    public void newElem(Element elem) {
        elems.add(elem);
    }

    public Rectangle getBoundBuffer() {
        return boundBuffer;
    }

    public void setBoundBuffer(Rectangle boundBuffer) {
        this.boundBuffer = boundBuffer;
    }
}
