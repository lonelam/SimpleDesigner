package graph;

import drawer.DrawSelf;
import drawer.Movable;

import java.awt.*;
import java.io.Serializable;
import java.util.Vector;

public abstract class Element implements DrawSelf, Movable, Serializable, Cloneable{
    /**
     * 元素首次建立和选中双击后会调用这个函数
     */
    public abstract void pop();
    public Vector<Point> getPin(){
        return new Vector<>();
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
