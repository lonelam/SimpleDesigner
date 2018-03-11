package graph;

import java.awt.*;

public class UserCaseNode extends Node implements Cloneable {
    
	public UserCaseNode(int X0, int Y0, int X1, int Y1) {
		super(X0, Y0, X1, Y1);
	}
	public UserCaseNode(int X0, int Y0, int X1, int Y1, String info) {
		super(X0, Y0, X1, Y1);
		text = info;
		
	}
	
	@Override
	public boolean isPointOn(Point mouseP) {
		if (mouseP.getX() >= getX() && mouseP.getY() >= getY() &&
                mouseP.getX() <= getWidth() + getX() && mouseP.getY() <= getHeight() + getY()) {
            return true;
        }
        return false;
	}

	

	@Override
	public void draw(Graphics2D pic, Rectangle bound) {
		pic.setColor(Color.WHITE);
		pic.fillOval(getX(),getY(),getWidth(),getHeight());
		pic.setColor(Color.BLACK);
		pic.drawOval(getX(),getY(),getWidth(),getHeight());


        if (text != null) {
            int h = 0;
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
        if (focuson) {
            super.highlight(pic);
        }

	}



	@Override
	public boolean isConflict(Point targetP) {
		if (targetP.getX() >= getX()+3 && targetP.getY() >= getY() +3 &&
				targetP.getX() <= getWidth() + getX() -3 && targetP.getY() <= getHeight() + getY() -3) {
			return true;
		}
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
        UserCaseNode r = new UserCaseNode(10, 10, 10 + this.getWidth(), 10 + this.getHeight(), this.text);

        return r;
    }

    @Override
    public boolean isAttached(Point targetP) {
        return false;
    }
}

