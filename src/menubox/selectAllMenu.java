package menubox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import drawer.GraphPanel;
import graph.Element;
import toolbox.SelectionTool;
import toolbox.Tool;

public class selectAllMenu extends JMenuItem {
    GraphPanel GP;
    
    public selectAllMenu(GraphPanel gp){
    	GP = gp;
    	this.setText("Select All");
    	this.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A,  
	               java.awt.Event.CTRL_MASK ));
    	addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Tool currentTool = GP.currentTool;
				if(currentTool.getToolName().equals("SelectionTool")) {
					SelectionTool select = (SelectionTool) currentTool;
					for (Element e1: GP.getG().getElems()){
			            e1.focuson = true;
			            select.ActorBuffers.addElement(e1);
			        }
				}
				GP.repaint();
			}
    		
    	});
    }

}