package shell;

import GUI.EditorFrame;

import javax.swing.*;

public class entry {
    public static void main(String[] args) {
        EditorFrame mFindow = new EditorFrame("nice");
        try
        {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            System.out.println("载入风格期间发生错误");
            System.exit(-1);
        }
        SwingUtilities.updateComponentTreeUI(mFindow);
        mFindow.setVisible(true);
    }
}
