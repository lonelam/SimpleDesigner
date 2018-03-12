package menubox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import drawer.GraphPanel;
import graph.Element;
import toolbox.SelectionTool;
import toolbox.Tool;

public class deleteMenu extends JMenuItem {
    GraphPanel GP;
    
    public deleteMenu(GraphPanel gp){
    	GP = gp;
    	this.setText("Delete");
    	this.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D,  
	               java.awt.Event.CTRL_MASK ));
    	addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Tool currentTool = GP.currentTool;
				if(currentTool.getToolName().equals("SelectionTool")) {
					SelectionTool select = (SelectionTool) currentTool;
					for(Element ac : select.ActorBuffers) {
						GP.getG().delElem(ac);
					}
					select.ActorBuffers.clear();
				}
				GP.repaint();
			}
    		
    	});
    }

}
