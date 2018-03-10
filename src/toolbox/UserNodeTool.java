package toolbox;

import java.awt.Point;
import java.awt.event.MouseEvent;

import graph.Graph;
import graph.Node;
import graph.RectNode;
import graph.UserNode;

public class UserNodeTool extends Tool{

	private Point mouseBuffer, startPoint;

    private UserNode constingBuffer = null;
    private boolean dragging = false;
    public UserNodeTool(Graph g)
    {
    	super(g);
        setToolName("UserNodeTool");
    }

    @Override
    public void doubleClicked(MouseEvent e) {
    	
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
	    constingBuffer = new UserNode(startPoint.x, startPoint.y, startPoint.x, startPoint.y);
	    G.newElem(constingBuffer);
    }

}