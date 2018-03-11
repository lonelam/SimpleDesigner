package menubox;

import graph.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SaveMenu extends JMenuItem {
    Graph G;

    public SaveMenu(Graph g) {
        super();
        G = g;
        setName("Save");
        setText("Save...");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser jc = new JFileChooser();
                int ret = jc.showSaveDialog(getParent());
                if (ret == jc.APPROVE_OPTION) {
                    File selectedFile = jc.getSelectedFile();
                    G.save(selectedFile);
                }
            }
        });
    }
}
