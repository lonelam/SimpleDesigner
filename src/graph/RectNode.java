package graph;

import javax.swing.*;
import java.awt.*;

public class RectNode extends Node{
    private String text = "test";

    public RectNode(int X0, int Y0, int X1, int Y1) {
        super(X0, Y0, X1, Y1);
        for (int i = 0; i < 5; i++) {
            text = text + text;
        }
    }

    @Override
    public void pop() {

    }

    @Override
    public void draw(Graphics2D pic, Rectangle bound) {
        JTextArea label = new JTextArea(text);
        label.setBounds(0, 0, getWidth(), getHeight());
        label.setLineWrap(true);
        pic.translate(getX(), getY());
        label.paint(pic);
        pic.translate(-getX(), -getY());
        pic.drawRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public boolean isPointOn(Point mouseP) {
        if (mouseP.getX() >= getX() && mouseP.getY() >= getY() &&
                mouseP.getX() <= getWidth() + getX() && mouseP.getY() <= getHeight() + getY()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isConflict(Point targetP) {
        if (targetP.getX() >= getX()+3 && targetP.getY() >= getY() +3 &&
                targetP.getX() <= getWidth() + getX() -3 && targetP.getY() <= getHeight() + getY() -3) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isAttached(Point targetP) {
        if (targetP.getX() >= getX()-3 && targetP.getY() >= getY() -3 &&
            targetP.getX() <= getWidth() + getX() +3 && targetP.getY() <= getHeight() + getY() +3) {
        return true;
    }
        return false;
    }
}
