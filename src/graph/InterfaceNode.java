package graph;

import java.awt.*;

public class InterfaceNode extends Node {
    private EditorBox editorBox;
    String text;
    
	public InterfaceNode(int X0, int Y0, int X1, int Y1) {
		super(X0, Y0, X1, Y1);
	}

    public InterfaceNode(int X0, int Y0, int X1, int Y1,String info) {
		super(X0, Y0, X1, Y1);
		text = info;
	}

	@Override
	public void draw(Graphics2D pic, Rectangle bound) {
		pic.setColor(Color.WHITE);
		pic.fillRect(getX(),getY(),getWidth(),getHeight());
		pic.setColor(Color.BLACK);
		pic.drawRect(getX(), getY(), getWidth(), getHeight());
		String interfaceString = "<<interface>>";
 		int length=pic.getFontMetrics().charsWidth(interfaceString.toCharArray(), 0, interfaceString.length());
		pic.drawString(interfaceString, getX()+getWidth()/2-length/2, getY()+20);

        if (focuson) {
            super.highlight(pic);
        }
        int offsetY=20;

        if (text != null) {

//			text = editorBox.getAllText();
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
        String[] labels = {"Name", "Methods"};
        EditorBox editorBox = new EditorBox(null, this, "Priorities", labels, text);
        editorBox.setVisible(true);
	}

	@Override
	public boolean isConflict(Point targetP) {
		if (targetP.getX() >= getX()+3 && targetP.getY() >= getY() +3 &&
				targetP.getX() <= getWidth() + getX() -3 && targetP.getY() <= getHeight() + getY() -3) {
			return true;
		}
		return false;
	}

    public Object cloneElem() {
        InterfaceNode r = new InterfaceNode(10, 10, 10 + this.getWidth(), 10 + this.getHeight(), this.text);

        return r;
    }

    @Override
    public boolean isAttached(Point targetP) {
        return false;
    }
}