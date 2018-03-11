package graph;

import java.awt.*;

public class UserNode extends Node{

    public UserNode(int X0, int Y0, int X1, int Y1) {
        super(X0, Y0, X1 + 40, Y1 + 90);
    }

    public UserNode(int X0, int Y0, int X1, int Y1, String info) {
        super(X0, Y0, X1 + 40, Y1 + 90);
        text = info;
    }

    @Override
    public void draw(Graphics2D pic, Rectangle bound) {
        pic.drawOval(getX(), getY(), 40, 40);
        pic.drawLine(getX()+20,getY()+40,getX()+20,getY()+60);
        pic.drawLine(getX()-5, getY()+50, getX()+45, getY()+50);
        pic.drawLine(getX()+20, getY()+60, getX()+10, getY()+90);
        pic.drawLine(getX()+20, getY()+60, getX()+35, getY()+90);
        this.setHeight(90);
        this.setWidth(40);

        if (text != null) {
            if(!text.equals("")) {
                int length = pic.getFontMetrics().charsWidth(text.toCharArray(), 0, text.length());

                if(length>getWidth()){
                    int t = getX()+length-getWidth()/2;
                    pic.drawString(text, getX()+getWidth()/2-length/2, getY()+110);
                }else{
                    pic.drawString(text, getX(), getY()+110);
                }
            }
        }
        if (focuson) {
            super.highlight(pic);
        }
    }

    @Override
    public boolean isPointOn(Point mouseP) {
        if (mouseP.getX() >= getX() && mouseP.getY() >= getY() &&
                mouseP.getX() <= 25 + getX() && mouseP.getY() <= 90 + getY()) {
            return true;
        }
        return false;

    }

    @Override
    public boolean isConflict(Point targetP) {
        return false;
    }

    @Override
    public void pop() {
        String[] labels = {"Name"};
        EditorBox editorBox = new EditorBox(null, this, "Priorities", labels, text);
        editorBox.setVisible(true);
    }

    @Override
    public Object cloneElem() {
        UserNode r = new UserNode(10, 10, 10 + this.getWidth(), 10 + this.getHeight(), this.text);

        return r;
    }

    @Override
    public boolean isAttached(Point targetP) {
        return false;
    }

}


//package graph;
//
//import java.awt.Graphics2D;
//import java.awt.Point;
//import java.awt.Rectangle;
//
//public class UserNode extends Node{
//    private EditorBox editorBox;
//    String text;
//    
//	public UserNode(int X0, int Y0, int X1, int Y1) {
//		super(X0, Y0, X1+25, Y1+90);
//	}
//	
//	public UserNode(int X0, int Y0, int X1, int Y1,String info) {
//		super(X0, Y0, X1+25, Y1+90);
//		text = info;
//		String[] labels={"Name"};
//		editorBox = new EditorBox(null,this,"Priorities",labels,text);
//	}
//	
//	@Override
//	public void draw(Graphics2D pic, Rectangle bound) {
//		// TODO Auto-generated method stub
//		pic.drawOval(getX(), getY(), 40, 40);
//		pic.drawLine(getX()+20,getY()+40,getX()+20,getY()+60);
//		pic.drawLine(getX()-5, getY()+50, getX()+45, getY()+50);
//		pic.drawLine(getX()+20, getY()+60, getX()+10, getY()+90);
//		pic.drawLine(getX()+20, getY()+60, getX()+35, getY()+90);
//		
//		if(editorBox!=null) {
//			text = editorBox.getAllText();
//	    	if(!text.equals("")) {
//	    		int length = pic.getFontMetrics().charsWidth(text.toCharArray(), 0, text.length());
//	    		
//	    		if(length>getWidth()){
//	    			int t = getX()+length-getWidth()/2;
//	    			pic.drawString(text, getX()+getWidth()/2-length/2, getY()+110);
//	    		}else{
//	    			pic.drawString(text, getX(), getY()+110);
//	    		}
//			}
//		}
//	}
//
//	@Override
//	public boolean isPointOn(Point mouseP) {
//		// TODO Auto-generated method stub
//		if (mouseP.getX() >= getX() && mouseP.getY() >= getY() &&
//                mouseP.getX() <= 25 + getX() && mouseP.getY() <= 90 + getY()) {
//            return true;
//        }
//        return false;
//		
//	}
//
//	@Override
//	public boolean isConflict(Point targetP) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void pop() {
//		// TODO Auto-generated method stub
//		if(editorBox == null){
//			String[] labels={"Name"};
//			editorBox = new EditorBox(null,this,"Priorities",labels);
//		}
//		editorBox.setVisible(true);
//	}
//
//}
//
