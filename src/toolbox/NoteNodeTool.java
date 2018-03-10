package toolbox;

import java.awt.Point;
import java.awt.event.MouseEvent;

import graph.Graph;
import graph.NoteNode;

public class NoteNodeTool extends Tool{
	private Point mouseBuffer, startPoint;

    private NoteNode constingBuffer = null;
    private boolean dragging = false;
    public NoteNodeTool(Graph g)
    {
    	super(g);
        setToolName("NoteNodeTool");
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
         constingBuffer = new NoteNode(startPoint.x, startPoint.y, startPoint.x, startPoint.y);
         G.newElem(constingBuffer);
    }
}
