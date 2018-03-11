package menubox;

import graph.Graph;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OutputMenu extends JMenuItem {
    Graph G;

    public OutputMenu(Graph g) {
        super();
        G = g;
        setName("Output");
        setText("Output...");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser jc = new JFileChooser();
                jc.setFileFilter(new FileNameExtensionFilter("PNG images", "png"));
                int ret = jc.showSaveDialog(getParent());
                if (ret == jc.APPROVE_OPTION) {
                    if (!jc.getSelectedFile().getName().toLowerCase().endsWith(".png")) {
                        jc.setSelectedFile(new File(jc.getSelectedFile().toString() + ".png"));
                    }
                    File selectedFile = jc.getSelectedFile();
                    G.output(selectedFile);
                }
            }
        });
    }
}
