package toolbox;

import graph.Graph;

import java.awt.event.MouseEvent;

public class Eraser extends Tool {
    public Eraser(Graph g)
    {
        super(g);
        setToolName("Eraser");
    }
    @Override
    public void doubleClicked(MouseEvent e) {

        G.delElem(G.focus(e.getPoint()));
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
