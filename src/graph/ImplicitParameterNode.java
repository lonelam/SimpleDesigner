package graph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.Vector;
import java.awt.BasicStroke;


public class ImplicitParameterNode extends Node{
	String text;
	Vector<ActivationBarNode> ActivationBarNodes;
    
	private EditorBox editorBox;
	
	public ImplicitParameterNode(int X0, int Y0, int X1, int Y1) {
		super(X0, Y0, X1, Y1);
		ActivationBarNodes = new Vector<ActivationBarNode>();
	}
	
	public ImplicitParameterNode(int X0, int Y0, int X1, int Y1, String info) {
		super(X0, Y0, X1, Y1);
		text = info;
		String[] labels={"Name"};
		editorBox = new EditorBox(null,this,"Priorities",labels,text);
		
		ActivationBarNodes = new Vector<ActivationBarNode>();
	}

	@Override
	public void draw(Graphics2D pic, Rectangle bound) {
		// TODO Auto-generated method stub
		pic.setColor(Color.WHITE);
		pic.fillRect(getX(),getY(),getWidth(),getHeight());
		pic.setColor(Color.BLACK);
		pic.drawRect(getX(), getY(), getWidth(),getHeight());
		
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
	    		if(length>getWidth()/2){
	    			setWidth(length*2+40);
	    			for(ActivationBarNode a : ActivationBarNodes) {
	    				a.setX(getX()+getWidth()/2-a.getWidth()/2);
	    			}
	    		}
			}
		}
		
//		if(editorBox!=null) {
//			String[] linesToDisplay = editorBox.getInfoLinesAt(0);
//	    	int longest=0;
//	    	int h = 0;
//	    	text = editorBox.getAllText();
////	    	System.out.println(text);
//	    	if(linesToDisplay!=null) {
//	    		h+=20;
//	    		if(h>getHeight()){
//	    			setHeight(h+40);
//	    		}
//	    		String name = text;
////	    		String name = editorBox.getAllTextAreas().elementAt(0).getInfo();
//	    		int length = pic.getFontMetrics().charsWidth(name.toCharArray(), 0, name.length());
//	    		pic.drawString(name, getX()+getWidth()/2-length/2, getY()+getHeight()/2);
//	    		if(length>getWidth()/2){
//	    			setWidth(length*2+40);
//	    			for(ActivationBarNode a : ActivationBarNodes) {
//	    				a.setX(getX()+getWidth()/2-a.getWidth()/2);
//	    			}
//	    		}
//			}
//		}
		
		
		Stroke dash = new BasicStroke(1f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND,2.5F,new float[] {15,10},0f);
		pic.setStroke(dash);
		pic.drawLine(getX()+getWidth()/2, getY()+getHeight(), getX()+getWidth()/2, getY()+getHeight()+(40+ActivationBarNodes.size()*100));
		pic.setStroke(new BasicStroke());
	}

	@Override
	public boolean isPointOn(Point mouseP) {
		if (mouseP.getX() >= getX() && mouseP.getY() >= getY() &&
                mouseP.getX() <= getWidth() + getX() && mouseP.getY() <= getHeight() + getY() + (40+ActivationBarNodes.size()*100)) {
			if(mouseP.getX()>=getX()+getWidth()/2-15&&mouseP.getX()<=getX()+getWidth()/2+15
					&&mouseP.getY()>=getY()+getHeight()&&mouseP.getY()<=getY()+getHeight()+(40+ActivationBarNodes.size()*100)) {
				return false;
			}else {
				return true;
			}
        }
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
	
	public void addBarNode(ActivationBarNode a) {
		ActivationBarNodes.addElement(a);
	}
	
	public Vector<ActivationBarNode> getActivationBarNodes(){
		return ActivationBarNodes;
	}

	@Override
	public boolean isConflict(Point targetP) {
		// TODO Auto-generated method stub
		return false;
	}

}
