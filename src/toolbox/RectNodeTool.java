package toolbox;

import graph.Graph;
import graph.RectNode;

import java.awt.*;
import java.awt.event.MouseEvent;

public class RectNodeTool extends Tool{
    private Point mouseBuffer, startPoint;
    private RectNode constingBuffer = null;
    private boolean dragging = false;
    public RectNodeTool(Graph g)
    {
        super(g);
        setToolName("RectNodeTool");
    }

    @Override
    public void doubleClicked(MouseEvent e) {

    }

    @Override
    public void singleClicked(MouseEvent e) {

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
        constingBuffer = new RectNode(startPoint.x, startPoint.y, startPoint.x, startPoint.y);
        G.newElem(constingBuffer);
    }
}
