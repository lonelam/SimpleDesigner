package menubox;

import graph.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearMenu extends JMenuItem {
    Graph G;

    public ClearMenu(Graph g) {
        super();
        G = g;
        setName("Clear");
        setText("Clear");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                G.clear();
            }
        });
    }
}
