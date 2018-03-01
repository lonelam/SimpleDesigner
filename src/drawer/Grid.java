package drawer;

import java.awt.*;
import java.awt.geom.Line2D;

public class Grid implements DrawSelf{
    private static final double GRID_SIZE = 10;
    private static final Color GRID_COLOR = new Color(220, 220, 220);
    private int interval;

    private Grid() {
    }

    /**
     * @param interval 网格间距
     */
    public Grid(int interval) {
        this.interval = interval;
    }

    /**
     * 绘制图形网格
     *
     * @param pic 绘制框
     * @param bound 边界
     */
    public void draw(Graphics2D pic, Rectangle bound) {
        Color oldColor = pic.getColor();
        pic.setColor(GRID_COLOR);
        Stroke oldStroke = pic.getStroke();
        for (double x = interval / 2; x < bound.getWidth(); x += interval) {
            pic.draw(new Line2D.Double(x, 0, x, bound.getHeight()));
        }
        for (double y = interval / 2; y < bound.getHeight(); y += interval) {
            pic.draw(new Line2D.Double(0, y, bound.getWidth(), y));
        }
        pic.setColor(oldColor);
        pic.setStroke(oldStroke);
    }

    @Override
    public boolean isPointOn(Point mouseP) {
        return false;
    }

    @Override
    public boolean isConflict(Point targetP) {
        return false;
    }
}
