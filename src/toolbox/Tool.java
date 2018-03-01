package toolbox;

import graph.Graph;

import java.awt.event.MouseEvent;

public abstract class Tool {
    private String ToolName;

    protected Graph G;

    public Tool(Graph G){
        setG(G);
    }

    public abstract void doubleClicked(MouseEvent e);

    public abstract void singleClicked(MouseEvent e);

    public abstract void mouseDown(MouseEvent e);

    public abstract void mouseUp(MouseEvent e);

    public abstract void mouseDrag(MouseEvent e);

    public String getToolName() {
        return ToolName;
    }

    public void setToolName(String toolName) {
        ToolName = toolName;
    }

    public Graph getG() {
        return G;
    }

    public void setG(Graph g) {
        G = g;
    }
}
