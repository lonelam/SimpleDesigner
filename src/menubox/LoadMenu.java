package menubox;

import graph.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LoadMenu extends JMenuItem {
    Graph G;

    public LoadMenu(Graph g) {
        super();
        G = g;
        setName("Load");
        setText("Load...");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser jc = new JFileChooser();
                int ret = jc.showOpenDialog(getParent());
                if (ret == jc.APPROVE_OPTION) {
                    File selectedFile = jc.getSelectedFile();
                    G.load(selectedFile);
                }
            }
        });
    }
}
