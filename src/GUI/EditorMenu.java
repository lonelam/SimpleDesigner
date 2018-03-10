package GUI;

import graph.Graph;
import menubox.LoadMenu;
import menubox.OutputMenu;
import menubox.SaveMenu;

import javax.swing.*;
import java.awt.*;

public class EditorMenu extends JMenuBar{
    private JMenu jm;
    private Graph G;
    EditorMenu(Graph g){
        G = g;
        jm = new JMenu("File");
        add(jm);
        jm.add(new SaveMenu(G));
        jm.add(new LoadMenu(G));
        jm.add(new OutputMenu(G));
    }

    public Graph getG() {
        return G;
    }
}
