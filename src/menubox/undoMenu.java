package menubox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import drawer.GraphPanel;
import toolbox.SelectionTool;
import toolbox.Tool;

public class undoMenu extends JMenuItem {
    GraphPanel GP;
    
    public undoMenu(GraphPanel gp){
    	GP = gp;
    	this.setText("Undo");
    	this.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z,  
	               java.awt.Event.CTRL_MASK ));
    	addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				GP.getG().undo();
				GP.repaint();
			}
    		
    	});
    }

}
