package graph;

import java.awt.*;

public class ClassNode extends Node{
    
	public ClassNode(int X0, int Y0, int X1, int Y1) {
		super(X0, Y0, X1, Y1);
	}

	@Override
	public boolean isAttached(Point targetP) {
		return false;
	}

	public ClassNode(int X0, int Y0, int X1, int Y1,String info) {
		super(X0, Y0, X1, Y1);
		text = info;
	}

	@Override
	public void draw(Graphics2D pic, Rectangle bound) {
		pic.setColor(Color.WHITE);
		pic.fillRect(getX(),getY(),getWidth(),getHeight());
		pic.setColor(Color.BLACK);
		pic.drawRect(getX(), getY(), getWidth(), getHeight());

        if (focuson) {
            super.highlight(pic);
        }

        int offsetX=2;
		int[] offsetYs = {2,32,62};//basis
        if (text != null) {
            if(text.equals("****"))
				return ;
			String[] seperate = new String[1];
			if(text!="") {
				seperate = text.split("\\*\\*");
			}
            for (int i = 0, len = 3; i < len; i++) {
                String[] linesToDisplay;
				if(i<seperate.length) {
					linesToDisplay = seperate[i].split("\n");
				}else {
					linesToDisplay = null;
				}
				
	 	    	if(linesToDisplay!=null) {
	 	    		if(i==0){
	 	    			offsetYs[i] = 2;
	 	    		}
	 	    		if(i+1<len) {
	 	    			int h=20*(linesToDisplay.length);
	 	    			offsetYs[i+1] = offsetYs[i]+h;
	 	    		}
	 	    	}
			}


            for (int i = 0, len = 3; i < len; i++) {
                String[] linesToDisplay = new String[1];
				if(i<seperate.length) {
					linesToDisplay = seperate[i].split("\n");
				}else {
					linesToDisplay[0] = "";
				}
	 	    	int longest=0;
	 	    	if(linesToDisplay!=null) {
	 	    		int h=20*(linesToDisplay.length);
	 	    		if(i==len-1){
	 	    			setHeight(h+offsetYs[i]+10);
	 	    		}
	 	    		int offset = offsetYs[i];
	 	    		for(int j=0;j<linesToDisplay.length;j++) {
		 	    		pic.drawString(linesToDisplay[j], getX()+offsetX, getY()+(offset+=20)+5);
		 	    		if(linesToDisplay[j].length()>linesToDisplay[longest].length()){
		 	    			longest=j;
		 	    		}
		 	    		int l=pic.getFontMetrics().charsWidth(linesToDisplay[longest].toCharArray(), 0, linesToDisplay[longest].length());
		 	    		if(l>getWidth()){
		 	    			setWidth(l+20);
		 	    		}
		 	    	}
	 	    		pic.drawLine(getX(), getY()+offsetYs[i]+h+10, getX()+getWidth(), getY()+offsetYs[i]+h+10);
	 	    	}
			}
		}
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
        String[] labels = {"Name", "Attributes", "Methods"};
        EditorBox editorBox = new EditorBox(null, this, "Priorities", labels, text);
        editorBox.setVisible(true);
	}

	@Override
	public boolean isConflict(Point targetP) {
		// TODO Auto-generated method stub
		return false;
	}

    public Object cloneElem() {

        ClassNode r = new ClassNode(10, 10, 10 + this.getWidth(), 10 + this.getHeight(), this.text);
        return r;
    }
}
