package toolbox;

import java.awt.Point;
import java.awt.event.MouseEvent;

import graph.Graph;
import graph.ImplicitParameterNode;
import graph.Node;
import graph.ActivationBarNode;
import graph.Element;

public class ActivationBarNodeTool extends Tool{
	private Point mouseBuffer, startPoint;

    private ActivationBarNode constingBuffer = null;
    private boolean dragging = false;
    public ActivationBarNodeTool(Graph g){
    	super(g);
        setToolName("ActivationBarNodeTool");
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
         Element act = ((Node) getG().focus(e.getPoint()));
         if (act instanceof ImplicitParameterNode) {
             mouseBuffer = startPoint = e.getPoint();
             ActivationBarNode tmp = new ActivationBarNode(((Node) act).getX()+((Node) act).getWidth()/2-15
            		 , startPoint.y, ((Node) act).getX()+((Node) act).getWidth()/2-15, startPoint.y);
             G.newElem(tmp);
             ((ImplicitParameterNode) act).addBarNode(tmp);
             tmp.setInnerY(startPoint.y-((Node) act).getY());
         }
    }
}
