package graph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class UserCaseNode extends Node {
    private EditorBox editorBox;
    String text;
    
	public UserCaseNode(int X0, int Y0, int X1, int Y1) {
		super(X0, Y0, X1, Y1);
	}
	public UserCaseNode(int X0, int Y0, int X1, int Y1, String info) {
		super(X0, Y0, X1, Y1);
		text = info;
		String[] labels={"Name"};
		editorBox = new EditorBox(null,this,"Priorities",labels,text);
		
	}
	
	@Override
	public boolean isPointOn(Point mouseP) {
		// TODO Auto-generated method stub
		if (mouseP.getX() >= getX() && mouseP.getY() >= getY() &&
                mouseP.getX() <= getWidth() + getX() && mouseP.getY() <= getHeight() + getY()) {
            return true;
        }
        return false;
	}

	

	@Override
	public void draw(Graphics2D pic, Rectangle bound) {
		// TODO Auto-generated method stub
		pic.setColor(Color.WHITE);
		pic.fillOval(getX(),getY(),getWidth(),getHeight());
		pic.setColor(Color.BLACK);
		pic.drawOval(getX(),getY(),getWidth(),getHeight());
		
		
		if(editorBox!=null) {
	    	
	    	int h = 0;
	    	text = editorBox.getAllText();
	    	if(!text.equals("")) {
	    		h+=20;
	    		if(h>getHeight()){
	    			setHeight(h+40);
	    		}
	    		String name = text;
	    		int length = pic.getFontMetrics().charsWidth(name.toCharArray(), 0, name.length());
	    		pic.drawString(name, getX()+getWidth()/2-length/2, getY()+getHeight()/2);
	    		
	    		setWidth(length*2+40>150?length*2+40:150);
			}
		}
//		if(editorBox!=null) {
//			String[] linesToDisplay = editorBox.getInfoLinesAt(0);
//	    	
//	    	if(linesToDisplay!=null) {
//	    		String name = editorBox.getAllTextAreas().elementAt(0).getInfo();
//	    		int length = pic.getFontMetrics().charsWidth(name.toCharArray(), 0, name.length());
//
//    			pic.drawString(name, getX()+10, getY()+getHeight()/2+3);
//	    		if(length>getWidth()){
//	    			setWidth(length+30);
//	    		}
//			}
//		}
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

