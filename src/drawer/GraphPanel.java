package drawer;

import graph.Graph;
import toolbox.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GraphPanel extends JPanel{
    private boolean isGridHide = false;
    private Graph G = new Graph();
    private Grid grid = new Grid(10);
    private Tool currentTool = null;

    public GraphPanel() {
        addMouseListener(new GraphPanelMouseListener());
        addMouseMotionListener(new GraphPanelMouseMotionListener());
        setBackground(Color.WHITE);
    }

    public Graph getG() {
        return G;
    }

    public void setG(Graph g) {
        G = g;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Rectangle bounds = getBounds();
        if (!isGridHide) grid.draw(g2, bounds);
        G.draw(g2, bounds);
    }

    public void setCurrentTool(Tool tool)
    {
        currentTool = tool;
    }


    public boolean isGridHide() {
        return isGridHide;
    }

    public void setGridHide(boolean gridHide) {
        isGridHide = gridHide;
    }

    /**
     * 从这里往下都是处理鼠标事件的部分
     */
    private class GraphPanelMouseMotionListener extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            super.mouseMoved(e);
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            currentTool.mouseDrag(e);
            repaint();
        }
    }

    private class GraphPanelMouseListener extends MouseAdapter {
        /**
         * 调用工具实现，初步确定需要选择工具、画笔工具、释放元素工具三大类
         *
         * @param e
         */
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            currentTool.mouseDown(e);
            repaint();
        }

        /**
         * 调用工具实现，初步确定需要选择工具、画笔工具、释放元素工具三大类
         * 这里遇到的问题是在鼠标过程中可能还需要画图，考虑传参解决
         *
         * @param e
         */

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            currentTool.mouseUp(e);
            repaint();
        }


        /**
         * 绘图，鼠标失去焦点的情况需要强制取消绘图。
         * @param e
         */

        /**
         * 调用工具实现，初步确定需要选择工具、画笔工具、释放元素工具三大类
         * 对于双击统一调用选择工具，其他交给工具自己实现。
         *
         * @param e
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            currentTool.singleClicked(e);
            if (e.getClickCount() == 2) {
                currentTool.doubleClicked(e);
            }
            repaint();
        }
    }
}
