package GUI;

import drawer.GraphPanel;
import toolbox.Eraser;
import toolbox.RectNodeTool;
import toolbox.SelectionTool;
import toolbox.Tool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ToolBar extends JPanel{
    public ButtonGroup bGroup = new ButtonGroup();
    private GraphPanel actingPanel = null;
    private Vector<Tool> toolList = new Vector<>();

    /**
     * @param graphPanel 构造时即指定依附的Graph
     */
    public ToolBar(GraphPanel graphPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        actingPanel = graphPanel;
        //工具列表
        toolList.add(new SelectionTool(actingPanel.getG()));
        toolList.add(new RectNodeTool(actingPanel.getG()));
        toolList.add(new Eraser(actingPanel.getG()));
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
     * 添加一个Tool对应的按钮
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
