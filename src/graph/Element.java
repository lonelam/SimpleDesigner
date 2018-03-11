package graph;

import drawer.DrawSelf;
import drawer.Movable;

import java.awt.*;
import java.util.Vector;

public abstract class Element implements DrawSelf, Movable, Cloneable {
    /**
     * 元素首次建立和选中双击后会调用这个函数
     */
    public boolean focuson = false;
    public String text = "";

    public abstract void pop();
    public Vector<Point> getPin(){
        return new Vector<>();
    }

    public abstract void highlight();

    public abstract Object cloneElem();
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
