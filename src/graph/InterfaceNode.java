package graph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class InterfaceNode extends Node {
    private EditorBox editorBox;
    String text;
    
	public InterfaceNode(int X0, int Y0, int X1, int Y1) {
		super(X0, Y0, X1, Y1);
		// TODO Auto-generated constructor stub
	}
	
	public InterfaceNode(int X0, int Y0, int X1, int Y1,String info) {
		super(X0, Y0, X1, Y1);
		// TODO Auto-generated constructor stub
		text = info;
		String[] labels={"Name","Methods"};
		editorBox = new EditorBox(null,this,"Priorities",labels,text);
		
	}

	@Override
	public void draw(Graphics2D pic, Rectangle bound) {
		// TODO Auto-generated method stub
		
		pic.setColor(Color.WHITE);
		pic.fillRect(getX(),getY(),getWidth(),getHeight());
		pic.setColor(Color.BLACK);
		pic.drawRect(getX(), getY(), getWidth(), getHeight());
		String interfaceString = "<<interface>>";
 		int length=pic.getFontMetrics().charsWidth(interfaceString.toCharArray(), 0, interfaceString.length());
		pic.drawString(interfaceString, getX()+getWidth()/2-length/2, getY()+20);
		
		
		int offsetY=20;
		
		if(editorBox!=null) {
			
			text = editorBox.getAllText();
			if(text.equals("**"))
				return;
			
			String[] everyMessage = text.split("\\*\\*");
			String[] linesToDisplay = everyMessage[0].split("\n");
			int longest=0;
 	    	int h = 0;
 	    	if(linesToDisplay!=null) {
 	    		h+=20*(linesToDisplay.length);
 	    		setHeight(h+40);
 	    		
 	    		
 	    		for(int j=0;j<linesToDisplay.length;j++) {
 	    			int le = pic.getFontMetrics().charsWidth(linesToDisplay[j].toCharArray(), 0, linesToDisplay[j].length());
	 	    		pic.drawString(linesToDisplay[j], getX()+getWidth()/2-le/2, getY()+(offsetY+=20));
	 	    		if(linesToDisplay[j].length()>linesToDisplay[longest].length()){
	 	    			longest=j;
	 	    		}
	 	    	}
 	    		int l=pic.getFontMetrics().charsWidth(linesToDisplay[longest].toCharArray(), 0, linesToDisplay[longest].length());
 	    		if(l>getWidth()){
 	    			setWidth(l+40);
 	    		}
 	    		
			}
 	    	if(everyMessage.length==1)
 	    		return;
 	    	linesToDisplay = everyMessage[1].split("\n");
 	    	if(linesToDisplay!=null) {
 	    		h+=20*(linesToDisplay.length);
 	    		setHeight(h+40);
 	    		
 	    		offsetY+=10;
 	    		pic.drawLine(getX(), getY()+offsetY, getX()+getWidth(), getY()+offsetY);
 	    		for(int j=0;j<linesToDisplay.length;j++) {
	 	    		pic.drawString(linesToDisplay[j], getX()+2, getY()+(offsetY+=20));
	 	    		if(linesToDisplay[j].length()>linesToDisplay[longest].length()){
	 	    			longest=j;
	 	    		}
	 	    	}
 	    		int l=pic.getFontMetrics().charsWidth(linesToDisplay[longest].toCharArray(), 0, linesToDisplay[longest].length());
 	    		if(l>getWidth()){
 	    			setWidth(l+60);
 	    		}
 	    	}
 	    	
		}
//		if(editorBox!=null) {
//			
//			String[] linesToDisplay = editorBox.getInfoLinesAt(0);
// 	    	int longest=0;
// 	    	int h = 0;
// 	    	if(linesToDisplay!=null) {
// 	    		h+=20*(linesToDisplay.length);
// 	    		if(h>getHeight()){
// 	    			setHeight(h+40);
// 	    		}
// 	    		
// 	    		for(int j=0;j<linesToDisplay.length;j++) {
// 	    			int le = pic.getFontMetrics().charsWidth(linesToDisplay[j].toCharArray(), 0, linesToDisplay[j].length());
//	 	    		pic.drawString(linesToDisplay[j], getX()+getWidth()/2-le/2, getY()+(offsetY+=20));
//	 	    		if(linesToDisplay[j].length()>linesToDisplay[longest].length()){
//	 	    			longest=j;
//	 	    		}
//	 	    	}
// 	    		int l=pic.getFontMetrics().charsWidth(linesToDisplay[longest].toCharArray(), 0, linesToDisplay[longest].length());
// 	    		if(l>getWidth()){
// 	    			setWidth(l+40);
// 	    		}
// 	    		
//			}
// 	    	linesToDisplay = editorBox.getInfoLinesAt(1);
// 	    	if(linesToDisplay!=null) {
// 	    		h+=20*(linesToDisplay.length);
// 	    		if(h>getHeight()) {
// 	    			setHeight(h+40);
// 	    		}
// 	    		offsetY+=10;
// 	    		pic.drawLine(getX(), getY()+offsetY, getX()+getWidth(), getY()+offsetY);
// 	    		for(int j=0;j<linesToDisplay.length;j++) {
//	 	    		pic.drawString(linesToDisplay[j], getX()+2, getY()+(offsetY+=20));
//	 	    		if(linesToDisplay[j].length()>linesToDisplay[longest].length()){
//	 	    			longest=j;
//	 	    		}
//	 	    	}
// 	    		int l=pic.getFontMetrics().charsWidth(linesToDisplay[longest].toCharArray(), 0, linesToDisplay[longest].length());
// 	    		if(l>getWidth()){
// 	    			setWidth(l+60);
// 	    		}
// 	    	}
// 	    	
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
			String[] labels={"Name","Methods"};
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