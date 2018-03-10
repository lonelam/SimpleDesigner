package graph;

import toolbox.Tool;

import java.awt.event.MouseEvent;

public class ClearTool extends Tool{

    public ClearTool(Graph G) {
        super(G);
        setToolName("ClearTool");
    }

    @Override
    public void doubleClicked(MouseEvent e) {
        getG().clear();
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
