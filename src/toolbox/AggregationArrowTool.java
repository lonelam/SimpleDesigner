package toolbox;

import graph.AggregationArrow;
import graph.CompositionArrow;
import graph.Graph;

import java.awt.*;
import java.awt.event.MouseEvent;

public class AggregationArrowTool extends Tool{
    private Point mouseBuffer, startPoint;
    private boolean dragging = false;
    private AggregationArrow arrowBuffer = null;
    public AggregationArrowTool(Graph G) {
        super(G);
        setToolName("AggregationArrowTool");
    }

    @Override
    public void doubleClicked(MouseEvent e) {

    }

    @Override
    public void singleClicked(MouseEvent e) {

    }

    @Override
    public void mouseDown(MouseEvent e) {
        dragging = true;
        startPoint = e.getPoint();
        arrowBuffer = new AggregationArrow(startPoint.x, startPoint.y, startPoint.x, startPoint.y);
        G.newElem(arrowBuffer);
    }

    @Override
    public void mouseUp(MouseEvent e) {
        dragging = false;
        arrowBuffer.pop();
        arrowBuffer.shrink(G);
        arrowBuffer = null;
    }

    @Override
    public void mouseDrag(MouseEvent e) {
        if (dragging)
        {
            mouseBuffer = e.getPoint();
            arrowBuffer.setT(mouseBuffer);
//            G.getActor().update(startPoint.x, startPoint.y, mouseBuffer.x, mouseBuffer.y);
        }
    }
}
