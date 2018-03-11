package toolbox;

import graph.Graph;
import graph.ImplicitParameterNode;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ImplicitParameterNodeTool extends Tool{
	private Point mouseBuffer, startPoint;

    private ImplicitParameterNode constingBuffer = null;
    private boolean dragging = false;
    public ImplicitParameterNodeTool(Graph g)
    {
    	super(g);
        setToolName("ImplicitParameterNodeTool");
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
         constingBuffer = new ImplicitParameterNode(startPoint.x, startPoint.y, startPoint.x, startPoint.y);
         G.newElem(constingBuffer);
    }
}
