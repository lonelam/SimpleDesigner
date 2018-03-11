package GUI;

import drawer.GraphPanel;
import graph.Graph;
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
        Graph g = actingPanel.getG();
        toolList.add(new SelectionTool(g));
        toolList.add(new RectNodeTool(g));
        toolList.add(new Eraser(g));
        toolList.add(new UserNodeTool(g));
        toolList.add(new UserCaseNodeTool(g));
        toolList.add(new NoteNodeTool(g));
        toolList.add(new ClassNodeTool(g));
        toolList.add(new InterfaceNodeTool(g));
        toolList.add(new PackageNodeTool(g));
        toolList.add(new ImplicitParameterNodeTool(g));
        toolList.add(new ActivationBarNodeTool(g));
        toolList.add(new SimpleArrowTool(g));
        toolList.add(new InheritanceArrowTool(g));
        toolList.add(new GeneralizationArrowTool(g));
        toolList.add(new CompositionArrowTool(g));
        toolList.add(new AggregationArrowTool(g));
        toolList.add(new DependenciesArrowTool(g));
        toolList.add(new MultiPlicityArrowTool(g));
//        toolList.add(new SaveTool(g));
//        toolList.add(new LoadTool(g));
//        toolList.add(new OutputTool(g));
        toolList.add(new AutoEdgeTool(g));
        toolList.add(new DuplicateTool(g));

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
