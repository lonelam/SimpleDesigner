package drawer;

import java.awt.*;

public interface DrawSelf {

    /**
     * @param pic 所有元素都通过这个图形对象来绘图
     * @param bound 整个绘图框应该有边界，超出部分不完全绘制，为了画图方便可能会往外画一部分，不影响显示
     */
    void draw(Graphics2D pic, Rectangle bound);

    /**
     * @param mouseP 鼠标位置
     * @return 返回是否在该元素之上
     */
    boolean isPointOn(Point mouseP);

    /**
     * @param targetP 目标位置
     * @return 返回在目标位置画图是否会引起冲突，用于自动寻路
     */
    boolean isConflict(Point targetP);
}
