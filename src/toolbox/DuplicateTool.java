package toolbox;

import graph.Element;
import graph.Graph;

import java.awt.event.MouseEvent;

public class DuplicateTool extends Tool{
    public DuplicateTool(Graph G) {
        super(G);
        setToolName("Duplicate");
    }

    @Override
    public void doubleClicked(MouseEvent e) {

        Element tar = G.focus(e.getPoint());
        try {
            G.newElem((Element)tar.clone());
        } catch (CloneNotSupportedException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void singleClicked(MouseEvent e) {

    }

    @Override
    public void mouseDown(MouseEvent e) {

    }

    @Override
    public void mouseUp(MouseEvent e) {

    }

    @Override
    public void mouseDrag(MouseEvent e) {

    }
}
