package drawer;

import graph.Graph;
import toolbox.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Date;
import java.util.TimerTask;

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
     * 浠庤繖閲屽線涓嬮兘鏄鐞嗛紶鏍囦簨浠剁殑閮ㄥ垎
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
         * 璋冪敤宸ュ叿瀹炵幇锛屽垵姝ョ‘瀹氶渶瑕侀�夋嫨宸ュ叿銆佺敾绗斿伐鍏枫�侀噴鏀惧厓绱犲伐鍏蜂笁澶х被
         *
         * @param e
         */
    	boolean flag;//用来判断是否已经执行双击事件
    	int clickNum;//用来判断是否该执行双击事件
    	
    	
    	GraphPanelMouseListener(){
    		flag=false;//用来判断是否已经执行双击事件
        	clickNum=0;//用来判断是否该执行双击事件
    	}
    	
    	public void mouseClicked(MouseEvent e) {
	    	final MouseEvent me=e;//事件源
	
	    	this.flag=false;//每次点击鼠标初始化双击事件执行标志为false
	
	    	if (this.clickNum == 1) {//当clickNum==1时执行双击事件
	    	    this.mouseDoubleClicked(me);//执行双击事件
	    	    this.clickNum=0;//初始化双击事件执行标志为0
	    	    this.flag=true;//双击事件已执行,事件标志为true
	    	    return;
	    	}
	
	    	//定义定时器
	    	java.util.Timer timer=new java.util.Timer();
	
	    	//定时器开始执行,延时0.2秒后确定是否执行单击事件
	    	timer.schedule(new TimerTask() {
	    	    private int n=0;//记录定时器执行次数
	    	    public void run() {
	    	        if(flag){//如果双击事件已经执行,那么直接取消单击执行
	    	            n=0;
	    	            clickNum=0;
	    	            this.cancel();
	    	            return;
	    	        }
	    	        if (n == 1) {//定时器等待0.2秒后,双击事件仍未发生,执行单击事件
	    	            mouseSingleClicked(me);//执行单击事件
	    	            flag = true;
	    	            clickNum=0;
	    	            n=0;
	    	            this.cancel();
	    	            return;
	    	        }
	    	        clickNum++;
	    	        n++;
	    	  }
	    	},new Date(),500);
    	}

    	/**
    	* 鼠标单击事件
    	* @param e 事件源参数
    	*/
    	public void mouseSingleClicked(MouseEvent e){
    		currentTool.singleClicked(e);
    		repaint();
    	}

    	/**
    	* 鼠标双击事件
    	* @param e 事件源参数
    	*/
    	public void mouseDoubleClicked(MouseEvent e){
    		currentTool.doubleClicked(e);
    		repaint();
    	}
    	
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            currentTool.mouseDown(e);
            repaint();
        }

        /**
         * 璋冪敤宸ュ叿瀹炵幇锛屽垵姝ョ‘瀹氶渶瑕侀�夋嫨宸ュ叿銆佺敾绗斿伐鍏枫�侀噴鏀惧厓绱犲伐鍏蜂笁澶х被
         * 杩欓噷閬囧埌鐨勯棶棰樻槸鍦ㄩ紶鏍囪繃绋嬩腑鍙兘杩橀渶瑕佺敾鍥撅紝鑰冭檻浼犲弬瑙ｅ喅
         *
         * @param e
         */

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            currentTool.mouseUp(e);
            repaint();
        }
    }
    
    
//    private class GraphPanelMouseListener extends MouseAdapter {
//        /**
//         * 璋冪敤宸ュ叿瀹炵幇锛屽垵姝ョ‘瀹氶渶瑕侀�夋嫨宸ュ叿銆佺敾绗斿伐鍏枫�侀噴鏀惧厓绱犲伐鍏蜂笁澶х被
//         *
//         * @param e
//         */
//        @Override
//        public void mousePressed(MouseEvent e) {
//            super.mousePressed(e);
//            currentTool.mouseDown(e);
//            repaint();
//        }
//
//        /**
//         * 璋冪敤宸ュ叿瀹炵幇锛屽垵姝ョ‘瀹氶渶瑕侀�夋嫨宸ュ叿銆佺敾绗斿伐鍏枫�侀噴鏀惧厓绱犲伐鍏蜂笁澶х被
//         * 杩欓噷閬囧埌鐨勯棶棰樻槸鍦ㄩ紶鏍囪繃绋嬩腑鍙兘杩橀渶瑕佺敾鍥撅紝鑰冭檻浼犲弬瑙ｅ喅
//         *
//         * @param e
//         */
//
//        @Override
//        public void mouseReleased(MouseEvent e) {
//            super.mouseReleased(e);
//            currentTool.mouseUp(e);
//            repaint();
//        }
//
//
//        /**
//         * 缁樺浘锛岄紶鏍囧け鍘荤劍鐐圭殑鎯呭喌闇�瑕佸己鍒跺彇娑堢粯鍥俱��
//         * @param e
//         */
//
//        /**
//         * 璋冪敤宸ュ叿瀹炵幇锛屽垵姝ョ‘瀹氶渶瑕侀�夋嫨宸ュ叿銆佺敾绗斿伐鍏枫�侀噴鏀惧厓绱犲伐鍏蜂笁澶х被
//         * 瀵逛簬鍙屽嚮缁熶竴璋冪敤閫夋嫨宸ュ叿锛屽叾浠栦氦缁欏伐鍏疯嚜宸卞疄鐜般��
//         *
//         * @param e
//         */
//        @Override
//        public void mouseClicked(MouseEvent e) {
//            super.mouseClicked(e);
//            currentTool.singleClicked(e);
//            if (e.getClickCount() == 2) {
//                currentTool.doubleClicked(e);
//            }
//            repaint();
//        }
//    }
}
