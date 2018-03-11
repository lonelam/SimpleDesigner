
package graph;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivationBarNode extends Node{
	private String text;
    private int innerY;
    
	public ActivationBarNode(int X0, int Y0, int X1, int Y1) {
		super(X0, Y0, X1+30, Y1+80);
	}

	@Override
	public boolean isAttached(Point targetP) {
		return false;
	}

	public ActivationBarNode(int X0, int Y0, int X1, int Y1, String info) {
		super(X0, Y0, X1+30, Y1+80);
		text = info;
	}

	@Override
	public void draw(Graphics2D pic, Rectangle bound) {
		// TODO Auto-generated method stub
		if(text!=null) {
			pic.setColor(Color.WHITE);
			pic.fillRect(getX(),getY(),30,90);
			pic.setColor(Color.BLACK);
			pic.drawLine(getX(), getY()+80, getX(), getY()+75);
			pic.drawLine(getX(), getY()+70, getX(), getY());			
			pic.drawLine(getX()+30, getY(), getX(), getY());
			pic.drawLine(getX()+30, getY()+70, getX()+30, getY());
			pic.drawLine(getX()+30, getY()+80, getX()+30, getY()+75);
		}else {
			pic.setColor(Color.WHITE);
			pic.fillRect(getX(),getY(),30,70);
			pic.setColor(Color.BLACK);
			pic.drawRect(getX(), getY(),30,70);
		}
	}

	@Override
	public boolean isPointOn(Point mouseP) {
		// TODO Auto-generated method stub
		if (mouseP.getX() >= getX() && mouseP.getY() >= getY() &&
                mouseP.getX() <= getWidth() + getX() && mouseP.getY() <= getHeight() + getY()){
            return true;
        }
        return false;
	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub
//		
//		if(text == null){
        JDialog editorBox = new JDialog();
        editorBox.setTitle("Properties");
        editorBox.setModal(true);
        editorBox.setLayout(new BorderLayout(20, 10));
        JPanel p = new JPanel();
        p.setSize(40, 30);
        JRadioButton radio1 = new JRadioButton("Open");
        p.add(radio1, BorderLayout.NORTH);
        radio1.setSelected(text == null ? false : true);

        JButton validate = new JButton("ȷ��");
        validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                editorBox.setVisible(false);
                if (radio1.isSelected()) {
                    text = "1";
                } else {
                    text = null;
                }
            }
        });
        editorBox.add(validate, BorderLayout.SOUTH);
        editorBox.add(p);
        editorBox.pack();
        editorBox.setVisible(true);
//		}


    }

    public void setInnerY(int offset) {
        innerY = offset;
    }

    public int getInnerY() {
        return innerY;
    }

    @Override
    public boolean isConflict(Point targetP) {
        // TODO Auto-generated method stub
        return false;
    }

    public Object cloneElem() {
        return null;
    }
}
