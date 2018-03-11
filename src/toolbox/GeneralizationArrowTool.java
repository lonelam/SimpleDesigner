package toolbox;

import graph.GeneralizationArrow;
import graph.Graph;
import graph.InheritanceArrow;

import java.awt.*;
import java.awt.event.MouseEvent;

public class GeneralizationArrowTool extends Tool{
    private Point mouseBuffer, startPoint;
    private boolean dragging = false;
    private GeneralizationArrow arrowBuffer = null;
    public GeneralizationArrowTool(Graph G) {
        super(G);
        setToolName("GeneralizationArrowTool");
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
        arrowBuffer = new GeneralizationArrow(startPoint.x, startPoint.y, startPoint.x, startPoint.y);
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
