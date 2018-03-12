package menubox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import drawer.GraphPanel;
import graph.Graph;
import toolbox.SelectionTool;
import toolbox.Tool;

public class copyMenu extends JMenuItem {
    GraphPanel GP;
    
    public copyMenu(GraphPanel gp){
    	GP = gp;
    	this.setText("Copy");
    	this.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C,  
	               java.awt.Event.CTRL_MASK ));
    	addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Tool currentTool = GP.currentTool;
				if(currentTool.getToolName().equals("SelectionTool")) {
					SelectionTool select = (SelectionTool) currentTool;
					GP.copyBuffers = select.getActorBuffers();
					GP.repaint();
				}
				
			}
    		
    	});
    }

}
