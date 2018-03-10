package toolbox;

import java.awt.Point;
import java.awt.event.MouseEvent;

import graph.Graph;
import graph.ClassNode;

public class ClassNodeTool extends Tool{
	private Point mouseBuffer, startPoint;

    private ClassNode constingBuffer = null;
    private boolean dragging = false;
    public ClassNodeTool(Graph g)
    {
    	super(g);
        setToolName("ClassNodeTool");
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
         constingBuffer = new ClassNode(startPoint.x, startPoint.y, startPoint.x, startPoint.y);
         G.newElem(constingBuffer);
    }
}
