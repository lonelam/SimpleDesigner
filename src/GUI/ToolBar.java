package GUI;

import drawer.GraphPanel;
import graph.ClearTool;
import toolbox.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ToolBar extends JPanel{
    public ButtonGroup bGroup = new ButtonGroup();
    private GraphPanel actingPanel = null;
    private Vector<Tool> toolList = new Vector<>();

    /**
     * @param graphPanel 鏋勯�犳椂鍗虫寚瀹氫緷闄勭殑Graph
     */
    public ToolBar(GraphPanel graphPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        actingPanel = graphPanel;
        //宸ュ叿鍒楄〃
        toolList.add(new SelectionTool(actingPanel.getG()));
        toolList.add(new RectNodeTool(actingPanel.getG()));
        toolList.add(new Eraser(actingPanel.getG()));
        toolList.add(new UserNodeTool(actingPanel.getG()));
        toolList.add(new UserCaseNodeTool(actingPanel.getG()));
        toolList.add(new NoteNodeTool(actingPanel.getG()));
        toolList.add(new ClassNodeTool(actingPanel.getG()));
        toolList.add(new InterfaceNodeTool(actingPanel.getG()));
        toolList.add(new PackageNodeTool(actingPanel.getG()));
        toolList.add(new ImplicitParameterNodeTool(actingPanel.getG()));
        toolList.add(new ActivationBarNodeTool(actingPanel.getG()));
//        toolList.add(new SaveTool(actingPanel.getG()));
//        toolList.add(new ClearTool(actingPanel.getG()));
//        toolList.add(new LoadTool(actingPanel.getG()));
//        toolList.add(new OutputTool(actingPanel.getG()));
        toolList.add(new AutoEdgeTool(actingPanel.getG()));
        toolList.add(new DuplicateTool(actingPanel.getG()));
//        toolList.add(new ArrowDrawTool());
        for (int i = 0; i < toolList.size(); i++)
        {
            JRadioButton button = new ToolButton(toolList.get(i).getToolName(), i);
            bGroup.add(button);
            add(button);
            if (i == 0)
            {
                button.doClick();
            }
        }

    }

    /**
     * 娣诲姞涓�涓猅ool瀵瑰簲鐨勬寜閽�
     */
    private class ToolButton extends JRadioButton {
        ToolButton(String Text, int index) {
            super(Text);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    actingPanel.setCurrentTool(toolList.get(index));
                }
            });
        }
    }

    public GraphPanel getActingPanel() {
        return actingPanel;
    }

    public void setActingPanel(GraphPanel actingPanel) {
        this.actingPanel = actingPanel;
    }
}
