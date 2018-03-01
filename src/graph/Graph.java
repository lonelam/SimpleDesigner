package graph;

import drawer.DrawSelf;

import java.awt.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Graph implements DrawSelf{
    private Vector<Element> elems = new Vector<>();
    private Vector<AutoEdge> fictions = new Vector<>();
    private Rectangle boundBuffer = new Rectangle(0, 0, 1024, 768);
    /*
    测试自动路径
     */
    Vector<Point> vv = null;

    public Graph() {
        /*
        默认初始图，测试用，后期删除
         */
        elems.add(new RectNode(100, 50, 200, 250));
        elems.add(new RectNode(500, 600, 300, 400));
        //测试自动路径
        Vector<Point> ts = new Vector<>();
        ts.add(new Point(1, 2));
        Vector<Point> tt = new Vector<>();
        tt.add(new Point(400, 400));
        vv = getPath(ts, tt);
//        elems.add(new SimpleArrow(110, 60, 510, 440));
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
        /*
        测试自动路径
         */

        if (vv != null) {
            int []vx = new int[vv.size()];
            int []vy = new int[vv.size()];
            for (int i  = 0; i < vv.size(); i++){
                vx[i] = (int)vv.get(i).getX();
                vy[i] = (int)vv.get(i).getY();
            }
            pic.drawPolyline((int [])vx, (int [])vy, vv.size());
        }
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

    public void newElem(Element elem) {
        elems.add(elem);
    }

    public void delElem(Element focuser) {
        elems.remove(focuser);
        fictions.remove(focuser);
    }

    public Rectangle getBoundBuffer() {
        return boundBuffer;
    }

    public void setBoundBuffer(Rectangle boundBuffer) {
        this.boundBuffer = boundBuffer;
    }

}
