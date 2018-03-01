package GUI;

import drawer.GraphPanel;

import javax.swing.*;
import java.awt.*;

public class EditorFrame extends JFrame{
    private GraphPanel graphPanel;
    private ToolBar toolBar;
    public EditorFrame (String title){
        setTitle(title);
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        graphPanel = new GraphPanel();
        toolBar = new ToolBar(graphPanel);

        add(toolBar, BorderLayout.EAST);
        add(graphPanel, BorderLayout.CENTER);
    }

    public GraphPanel getGraphPanel() {
        return graphPanel;
    }

    public void setGraphPanel(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
    }
}
