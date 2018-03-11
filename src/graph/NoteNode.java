package graph;

import java.awt.*;


public class NoteNode extends Node{
    
	public NoteNode(int X0, int Y0, int X1, int Y1) {
		super(X0, Y0, X1, Y1);
	}
	public NoteNode(int X0, int Y0, int X1, int Y1, String info) {
		super(X0, Y0, X1, Y1);
		text = info;
	}

	@Override
	public void draw(Graphics2D pic, Rectangle bound) {

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

        if (text != null) {
//			text = editorBox.getAllText();
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
        if (focuson) {
            super.highlight(pic);
        }

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
	public void pop() {

        String[] labels = {"Text"};
        EditorBox editorBox = new EditorBox(null, this, "Priorities", labels, text);
        editorBox.setVisible(true);
	}

	@Override
	public void highlight() {

	}

	@Override
	public boolean isConflict(Point targetP) {
        return false;
    }

    public Object cloneElem() {
        NoteNode r = new NoteNode(10, 10, 10 + this.getWidth(), 10 + this.getHeight(), this.text);

        return r;
    }

    public Object getLastVersion() {
        NoteNode r = new NoteNode(10, 10, 10 + this.getWidth(), 10 + this.getHeight(), this.text);

        return r;
    }

    @Override
    public boolean isAttached(Point targetP) {
        return false;
	}
}


