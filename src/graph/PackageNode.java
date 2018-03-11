package graph;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class PackageNode extends Node {
    private EditorBox editorBox;
    String text;
    
	public PackageNode(int X0, int Y0, int X1, int Y1) {
		super(X0, Y0, X1, Y1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isAttached(Point targetP) {
		return false;
	}

	public PackageNode(int X0, int Y0, int X1, int Y1, String info) {
		super(X0, Y0, X1, Y1);
		text = info;
		String[] labels={"Name","Contents"};
		editorBox = new EditorBox(null,this,"Priorities",labels,text);
	}

	@Override
	public void draw(Graphics2D pic, Rectangle bound) {
		// TODO Auto-generated method stub
		
		pic.setColor(Color.WHITE);
		pic.fillRect(getX()+1,getY(),getWidth()/2,40);
		pic.setColor(Color.BLACK);
		pic.drawRect(getX(), getY(), getWidth()/2, 40);
		pic.setColor(Color.WHITE);
		pic.fillRect(getX(),getY()+40,getWidth(),getHeight()-40);
		pic.setColor(Color.BLACK);
		pic.drawRect(getX(), getY()+40, getWidth(),getHeight()-40);
		
		
		if(editorBox!=null) {
			text = editorBox.getAllText();
			if(text.equals("**"))
				return;
			
			String[] linesToDisplay = text.split("\\*\\*");
 	    	int longest=0;
 	    	int h = 0;
 	    	if(linesToDisplay!=null) {
 	    		h+=20;
 	    		setHeight(h+40>150?h+40:150);
 	    		
 	    		String name = linesToDisplay[0];
 	    		pic.drawString(name, getX()+2, getY()+20);
 	    		
 	    		int l=pic.getFontMetrics().charsWidth(name.toCharArray(), 0, linesToDisplay[longest].length());
 	    		if(l>getWidth()/2){
 	    			setWidth(l*2+40);
 	    		}
 	    		
			}
 	    	if(linesToDisplay.length==1) {
 	    		return;
 	    	}
 	    	linesToDisplay = linesToDisplay[1].split("\n");
 	    	if(linesToDisplay!=null) {
 	    		
 	    		h+=20*(linesToDisplay.length)+10;
 	    		setHeight(h+40>150?h+40:150);
 	    		
// 	    		pic.drawLine(getX(), getY()+getHeight()/4+offsetY, getX()+getWidth(), getY()+getHeight()/4+offsetY);
 	    		int offsetY=50;
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
// 	    		h+=20;
// 	    		if(h>getHeight()){
// 	    			setHeight(h+40);
// 	    		}
// 	    		String name = editorBox.getAllTextAreas().elementAt(0).getInfo();
// 	    		pic.drawString(name, getX()+2, getY()+20);
// 	    		
// 	    		int l=pic.getFontMetrics().charsWidth(name.toCharArray(), 0, linesToDisplay[longest].length());
// 	    		if(l>getWidth()/2){
// 	    			setWidth(l*2+40);
// 	    		}
// 	    		
//			}
// 	    	linesToDisplay = editorBox.getInfoLinesAt(1);
// 	    	if(linesToDisplay!=null) {
// 	    		
// 	    		h+=20*(linesToDisplay.length)+10;
// 	    		if(h>getHeight()) {
// 	    			setHeight(h+40);
// 	    		}
//// 	    		pic.drawLine(getX(), getY()+getHeight()/4+offsetY, getX()+getWidth(), getY()+getHeight()/4+offsetY);
// 	    		int offsetY=50;
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
			String[] labels={"Name","Contents"};
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