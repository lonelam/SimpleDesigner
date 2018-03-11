package graph;

import java.awt.*;

public class PackageNode extends Node {
    
	public PackageNode(int X0, int Y0, int X1, int Y1) {
		super(X0, Y0, X1, Y1);
	}
	public PackageNode(int X0, int Y0, int X1, int Y1, String info) {
		super(X0, Y0, X1, Y1);
		text = info;
	}

	@Override
	public void draw(Graphics2D pic, Rectangle bound) {
		pic.setColor(Color.WHITE);
		pic.fillRect(getX()+1,getY(),getWidth()/2,40);
		pic.setColor(Color.BLACK);
		pic.drawRect(getX(), getY(), getWidth()/2, 40);
		pic.setColor(Color.WHITE);
		pic.fillRect(getX(),getY()+40,getWidth(),getHeight()-40);
		pic.setColor(Color.BLACK);
		pic.drawRect(getX(), getY()+40, getWidth(),getHeight()-40);


        if (text != null) {
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

        String[] labels = {"Name", "Contents"};
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
        PackageNode r = new PackageNode(10, 10, 10 + this.getWidth(), 10 + this.getHeight(), this.text);

        return r;
    }

    @Override
    public boolean isAttached(Point targetP) {
        return false;
    }

}

