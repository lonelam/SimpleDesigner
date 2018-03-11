package graph;

import drawer.DrawSelf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.Queue;

public class Graph implements DrawSelf{
    private Vector<Element> elems = new Vector<>();
    private Vector<AutoEdge> fictions = new Vector<>();
    private Rectangle boundBuffer = new Rectangle(0, 0, 1024, 768);


    public Vector<Element> newBuffer = new Vector<Element>();
    public Vector<Element> changeBuffer = new Vector<Element>();
    public Vector<Element> pointBuffer = new Vector<Element>();
    public Vector<Element> deleteBuffer = new Vector<Element>();

    public Vector<Vector<Element>> bufferOrder = new Vector<Vector<Element>>();
    
    
    public Graph() {
    }

    /**
     * @param mouseP 鼠标位置
     * @return 当前鼠标所在位置对应的物件，如果没有，则返回null
     */
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

    /**
     * @param S 起始点集
     * @param T 结束点集
     * @return 返回一条路径
     * BFS + A* 找一条最优不冲突路径，以一列像素点的向量的形式返回，如果找不到路径，则返回null
     */
    public Vector<Point> getPath(Vector<Point> S, Vector<Point> T){
        final int[] dx = new int[]{0, 0, 1, -1};
        final int[] dy = new int[]{-1, 1, 0, 0};
        Map<Point, Point> pre = new HashMap<>();
        Queue<Point> cur = new ArrayDeque<>(S);
        while (!cur.isEmpty()){
            Point C = cur.remove();
            if (T.contains(C)){
                Vector<Point> ret = new Vector<>();
                ret.add(C);
                while (!S.contains(C)){
                    C = pre.get(C);
                    ret.add(C);
                }
                return ret;
            }
            for (int dir = 0; dir < 4; dir++){
                Point tmp = new Point((int)C.getX() + dx[dir], (int)C.getY() + dy[dir]);
                if (isPointOn(tmp)){
                    for (Element e: elems){
                        if (!e.isConflict(tmp) && !pre.containsKey(tmp)){
                            pre.put(tmp, C);
                            cur.add(tmp);
                        }
                    }
                }
            }
        }
        /*
        找不到路径
         */
        return null;
    }

    /**
     * @param pic   所有元素都通过这个图形对象来绘图
     * @param bound 整个绘图框应该有边界，超出部分不完全绘制，为了画图方便可能会往外画一部分，不影响显示
     */
    @Override
    public void draw(Graphics2D pic, Rectangle bound) {
        for (Element e: elems){
            e.draw(pic, bound);
        }
        boundBuffer = bound;
    }

    /**
     * @param mouseP 鼠标位置
     * @return 鼠标是否在该对象的像上
     */
    @Override
    public boolean isPointOn(Point mouseP) {
        if (mouseP.getX() >= boundBuffer.getX() && mouseP.getY() >= boundBuffer.getY()
                && mouseP.getX() <= boundBuffer.getMaxX() && mouseP.getY() <= boundBuffer.getMaxY()) {
            return true;
        }
        return false;
    }



    @Override
    public boolean isConflict(Point targetP) {
        return false;
    }

    public void clear(){
        elems.clear();
        fictions.clear();
    }

    public void newElem(Element elem) {
        Element copy;
        if (elem instanceof Node) {
            copy = (Element) elem.cloneElem();
        } else {
            try {
                copy = (Element) elem.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return;
            }
        }
        this.newBuffer.addElement(copy);
        this.bufferOrder.addElement(newBuffer);
        elems.add(elem);
    }

    public void delElem(Element focuser) {
        this.deleteBuffer.addElement(focuser);
        this.bufferOrder.addElement(deleteBuffer);

        if (focuser instanceof ImplicitParameterNode) {
            for (Element ab : ((ImplicitParameterNode) focuser).ActivationBarNodes) {
                delElem(ab);
            }
        }
        elems.remove(focuser);
        fictions.remove(focuser);
    }

    public Rectangle getBoundBuffer() {
        return boundBuffer;
    }

    public Vector<Element> getElems() {
        return elems;
    }

    public Vector<Element> getOutputElems() {
        Vector<Element> output = new Vector<Element>();
        for (Element e : elems) {

        }
        return output;
    }

    public void setElems(Vector<Element> elems) {
        this.elems = elems;
    }

    public Vector<AutoEdge> getFictions() {
        return fictions;
    }

    public void setFictions(Vector<AutoEdge> fictions) {
        this.fictions = fictions;
    }
    public void save(File fhandle){
        FileOutputStream fos;
        ObjectOutputStream out;
        final JFileChooser fc = new JFileChooser();
        try{
            fos = new FileOutputStream(fhandle, false);
            out = new ObjectOutputStream(fos);
            for (Element ee : getElems()){
                try{
                    out.writeObject(ee);
                }
                catch (NotSerializableException e){
                    System.out.println("Serialize failed, skip.");
                }
            }
            out.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void load(File fhandle){
        FileInputStream fos;
        ObjectInputStream in;
        clear();
        try{
            fos = new FileInputStream(fhandle);
            in = new ObjectInputStream(fos);
            while(fos.available() > 0){
                newElem((Element) in.readObject());
            }
            in.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public void output(File fhandle){
        BufferedImage bi = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_ARGB);
        Graphics2D outG = bi.createGraphics();
        draw(outG, new Rectangle(1024, 768));
        try {
            ImageIO.write(bi, "PNG", fhandle);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void setBoundBuffer(Rectangle boundBuffer) {
        this.boundBuffer = boundBuffer;
    }

    public void undo() {
        Vector<Element> currentBuffer = bufferOrder.remove(bufferOrder.size() - 1);
        Element e = currentBuffer.lastElement();
        currentBuffer.remove(e);
        if (currentBuffer == newBuffer) {
            elems.remove(elems.size() - 1);
        }

        if (currentBuffer == deleteBuffer) {
            elems.addElement(e);
        }

        if (currentBuffer == changeBuffer) {
            elems.add(e);
            Element old = pointBuffer.remove(pointBuffer.size() - 1);
            elems.remove(old);
        }
    }

}
