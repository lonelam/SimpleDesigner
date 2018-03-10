package graph;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class UserNode extends Node{
    private EditorBox editorBox;
    String text;
    
	public UserNode(int X0, int Y0, int X1, int Y1) {
		super(X0, Y0, X1+25, Y1+90);
	}
	
	public UserNode(int X0, int Y0, int X1, int Y1,String info) {
		super(X0, Y0, X1+25, Y1+90);
		text = info;
		String[] labels={"Name"};
		editorBox = new EditorBox(null,this,"Priorities",labels,text);
	}
	
	@Override
	public void draw(Graphics2D pic, Rectangle bound) {
		// TODO Auto-generated method stub
		pic.drawOval(getX(), getY(), 40, 40);
		pic.drawLine(getX()+20,getY()+40,getX()+20,getY()+60);
		pic.drawLine(getX()-5, getY()+50, getX()+45, getY()+50);
		pic.drawLine(getX()+20, getY()+60, getX()+10, getY()+90);
		pic.drawLine(getX()+20, getY()+60, getX()+35, getY()+90);
		
		if(editorBox!=null) {
			text = editorBox.getAllText();
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
	}

	@Override
	public boolean isPointOn(Point mouseP) {
		// TODO Auto-generated method stub
		if (mouseP.getX() >= getX() && mouseP.getY() >= getY() &&
                mouseP.getX() <= 25 + getX() && mouseP.getY() <= 90 + getY()) {
            return true;
        }
        return false;
		
	}

	@Override
	public boolean isConflict(Point targetP) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub
		if(editorBox == null){
			String[] labels={"Name"};
			editorBox = new EditorBox(null,this,"Priorities",labels);
		}
		editorBox.setVisible(true);
	}

}

