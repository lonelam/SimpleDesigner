package toolbox;

import graph.Graph;
import graph.InterfaceNode;

import java.awt.*;
import java.awt.event.MouseEvent;

public class InterfaceNodeTool extends Tool{
	private Point mouseBuffer, startPoint;

    private InterfaceNode constingBuffer = null;
    private boolean dragging = false;
    public InterfaceNodeTool(Graph g)
    {
    	super(g);
        setToolName("InterfaceNodeTool");
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
         constingBuffer = new InterfaceNode(startPoint.x, startPoint.y, startPoint.x, startPoint.y);
         G.newElem(constingBuffer);
    }
}
