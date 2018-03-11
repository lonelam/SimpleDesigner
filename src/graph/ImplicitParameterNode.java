package graph;

import java.awt.*;
import java.util.Vector;


public class ImplicitParameterNode extends Node{
	Vector<ActivationBarNode> ActivationBarNodes;
    
	public ImplicitParameterNode(int X0, int Y0, int X1, int Y1) {
		super(X0, Y0, X1, Y1);
		ActivationBarNodes = new Vector<ActivationBarNode>();
	}

    public ImplicitParameterNode(int X0, int Y0, int X1, int Y1, String info) {
		super(X0, Y0, X1, Y1);
		text = info;
		
		ActivationBarNodes = new Vector<ActivationBarNode>();
	}

	@Override
	public void draw(Graphics2D pic, Rectangle bound) {
		// TODO Auto-generated method stub
		pic.setColor(Color.WHITE);
		pic.fillRect(getX(),getY(),getWidth(),getHeight());
		pic.setColor(Color.BLACK);
		pic.drawRect(getX(), getY(), getWidth(),getHeight());

        if (text != null) {

            int h = 0;
//	    	text = editorBox.getAllText();
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

        if (focuson) {
            super.highlight(pic);
        }


        Stroke dash = new BasicStroke(1f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND,2.5F,new float[] {15,10},0f);
		pic.setStroke(dash);
        pic.drawLine(getX() + getWidth() / 2, getY() + getHeight(), getX() + getWidth() / 2, getY() + getHeight() + (200 + ActivationBarNodes.size() * 100));
        pic.setStroke(new BasicStroke());

    }

	@Override
	public boolean isPointOn(Point mouseP) {
		if (mouseP.getX() >= getX() && mouseP.getY() >= getY() &&
                mouseP.getX() <= getWidth() + getX() && mouseP.getY() <= getHeight() + getY() + (200 + ActivationBarNodes.size() * 100)) {
            if(mouseP.getX()>=getX()+getWidth()/2-15&&mouseP.getX()<=getX()+getWidth()/2+15
                    && mouseP.getY() >= getY() + getHeight() && mouseP.getY() <= getY() + getHeight() + (200 + ActivationBarNodes.size() * 100)) {
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

        String[] labels = {"Name"};
        EditorBox editorBox = new EditorBox(null, this, "Priorities", labels, text);
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

    @Override
    public Object cloneElem() {
        ImplicitParameterNode r = new ImplicitParameterNode(10, 10, 10 + this.getWidth(), 10 + this.getHeight(), this.text);

        return r;
    }

    @Override
    public boolean isAttached(Point targetP) {
        // TODO Auto-generated method stub
        return false;
    }
}

