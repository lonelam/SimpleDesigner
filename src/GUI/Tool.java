package GUI;

import java.awt.event.MouseEvent;

public abstract class Tool {
    private String ToolName;
    public abstract void doubleClicked(MouseEvent e, Graph G);

    public abstract void singleClicked(MouseEvent e, Graph G);

    public abstract void mouseDown(MouseEvent e, Graph G);

    public abstract void mouseUp(MouseEvent e, Graph G);

    public abstract void mouseDrag(MouseEvent e, Graph G);

    public String getToolName() {
        return ToolName;
    }

    public void setToolName(String toolName) {
        ToolName = toolName;
    }
}
