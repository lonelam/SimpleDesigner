package graph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;


public class NoteNode extends Node{
    private EditorBox editorBox;
    String text;
    
	public NoteNode(int X0, int Y0, int X1, int Y1) {
		super(X0, Y0, X1, Y1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isAttached(Point targetP) {
		return false;
	}

	public NoteNode(int X0, int Y0, int X1, int Y1, String info) {
		super(X0, Y0, X1, Y1);
		text = info;
		String[] labels={"Text"};
		editorBox = new EditorBox(null,this,"Priorities",labels,text);
	}

	@Override
	public void draw(Graphics2D pic, Rectangle bound) {
		// TODO Auto-generated method stub
		
		int[] xPoints = {getX(),getX(),getX()+getWidth(),getX()+getWidth(),getX()+getWidth()-20,getX()+getWidth()-20};
		int[] yPoints = {getY(),getY()+getHeight(),getY()+getHeight(),getY()+20,getY()+20,getY()};
		pic.setColor(new Color(230,230,153));
		pic.fillPolygon(xPoints, yPoints, xPoints.length);
		pic.setColor(Color.BLACK);
		pic.drawPolygon(xPoints, yPoints, xPoints.length);
		
		int[] triangleX = {getX()+getWidth(),getX()+getWidth()-19,getX()+getWidth()-19};
		int[] triangleY = {getY()+20,getY()+20-1,getY()-1};
		pic.setColor(Color.WHITE);
		pic.fillPolygon(triangleX,triangleY,triangleX.length);
		pic.setColor(Color.BLACK);
		pic.drawLine(getX()+getWidth()-20, getY(), getX()+getWidth(), getY()+20);
		
		
		
		int offsetX=2,offsetY=1;
		
		if(editorBox!=null) {
			text = editorBox.getAllText();
	    	if(!text.equals("")) {
	    		String[] linesToDisplay = text.split("\n");
	    		int longest=0;
	 	    	if(linesToDisplay!=null) {
	 	    		int h=20*(linesToDisplay.length);
	 	    		setHeight(h+40>150?h+40:150);
	 	    		for(int j=0;j<linesToDisplay.length;j++) {
		 	    		pic.drawString(linesToDisplay[j], getX()+offsetX, getY()+(offsetY+=20));
		 	    		if(linesToDisplay[j].length()>linesToDisplay[longest].length()){
		 	    			longest=j;
		 	    		}
		 	    		int l=pic.getFontMetrics().charsWidth(linesToDisplay[longest].toCharArray(), 0, linesToDisplay[longest].length());
		 	    		if(l>getWidth()){
		 	    			setWidth(l+40);
		 	    		}
		 	    	}
	 	    	}
	    	}
		}
//		if(editorBox!=null) {
//			
//			for(int i=0,len=editorBox.getTextAreaCount();i<len;i++) {
//				String[] linesToDisplay = editorBox.getInfoLinesAt(i);
//	 	    	int longest=0;
//	 	    	if(linesToDisplay!=null) {
//	 	    		int h=20*(linesToDisplay.length);
//	 	    		if(h>getHeight()){
//	 	    			setHeight(h+40);
//	 	    		}
//	 	    		for(int j=0;j<linesToDisplay.length;j++) {
//		 	    		pic.drawString(linesToDisplay[j], getX()+offsetX, getY()+(offsetY+=20));
//		 	    		if(linesToDisplay[j].length()>linesToDisplay[longest].length()){
//		 	    			longest=j;
//		 	    		}
//		 	    		int l=pic.getFontMetrics().charsWidth(linesToDisplay[longest].toCharArray(), 0, linesToDisplay[longest].length());
//		 	    		if(l>getWidth()){
//		 	    			setWidth(l+40);
//		 	    		}
//		 	    	}
//	 	    	}
//			}
//		}
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
	public void pop() {
		// TODO Auto-generated method stub
		if(editorBox == null){
			String[] labels={"Text"};
			editorBox = new EditorBox(null,this,"Priorities",labels);

		}
			
		editorBox.setVisible(true);
	}

	@Override
	public boolean isConflict(Point targetP) {
		// TODO Auto-generated method stub
		return false;
	}

}
