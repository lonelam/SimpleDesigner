package toolbox;


import graph.Graph;
import graph.UserCaseNode;

import java.awt.*;
import java.awt.event.MouseEvent;

public class UserCaseNodeTool extends Tool{
	private Point mouseBuffer, startPoint;

    private UserCaseNode constingBuffer = null;
    private boolean dragging = false;
    public UserCaseNodeTool(Graph g)
    {
    	super(g);
        setToolName("UserCaseNodeTool");
    }

    @Override
    public void doubleClicked(MouseEvent e) {
    	//no reaction
    }

    @Override
    public void singleClicked(MouseEvent e) {
    	//no reaction
    }

    @Override
    public void mouseDrag(MouseEvent e) {
    	if (dragging && constingBuffer != null){
            mouseBuffer = e.getPoint();
            constingBuffer.update(startPoint.x, startPoint.y, mouseBuffer.x, mouseBuffer.y);
        }
    }

    @Override
    public void mouseUp(MouseEvent e) {
        dragging = false;
        constingBuffer = null;
    }

    @Override
    public void mouseDown(MouseEvent e) {
    	 dragging = true;
         mouseBuffer = startPoint = e.getPoint();
         constingBuffer = new UserCaseNode(startPoint.x, startPoint.y, startPoint.x, startPoint.y);
         G.newElem(constingBuffer);
    }
}