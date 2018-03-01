package toolbox;

import drawer.DrawSelf;
import graph.Element;
import graph.Graph;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class SelectionTool extends Tool{
    private Point mouseBuffer;
    private boolean dragging = false;
    public Element Actor = null;
    public SelectionTool(Graph g)
    {
        super(g);
        setToolName("SelectionTool");
    }

    @Override
    public void doubleClicked(MouseEvent e) {
        if (Actor != null)
            Actor.pop();
    }

    @Override
    public void singleClicked(MouseEvent e) {

    }

    @Override
    public void mouseDown(MouseEvent e) {
        Actor = G.focus(e.getPoint());
        dragging = true;
        mouseBuffer = e.getPoint();
    }

    @Override
    public void mouseUp(MouseEvent e) {
        dragging = false;
        mouseBuffer = e.getPoint();
    }

    @Override
    public void mouseDrag(MouseEvent e) {

        if (dragging && Actor != null){
            Actor.move((int) (e.getPoint().getX() - mouseBuffer.getX()),
                    (int) (e.getPoint().getY() - mouseBuffer.getY()));
        }
        mouseBuffer = e.getPoint();
    }

}
