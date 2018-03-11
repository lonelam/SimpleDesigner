package toolbox;

import drawer.DrawSelf;
import graph.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class SelectionTool extends Tool{
    private Point mouseBuffer;
    private boolean dragging = false;
    public Element Actor = null;
    public SelectionTool(Graph g)
    {
        super(g);
        setToolName("SelectionTool");
    }

    @Override
    public void doubleClicked(MouseEvent e) {
        if (Actor != null)
            Actor.pop();
    }

    @Override
    public void singleClicked(MouseEvent e) {

    }

    @Override
    public void mouseDown(MouseEvent e) {
        Actor = G.focus(e.getPoint());
        dragging = true;
        mouseBuffer = e.getPoint();
    }

    @Override
    public void mouseUp(MouseEvent e) {
        dragging = false;
        mouseBuffer = e.getPoint();
    }

    @Override
    public void mouseDrag(MouseEvent e) {

        if (dragging && Actor != null){
            Actor.move((int) (e.getPoint().getX() - mouseBuffer.getX()),
                    (int) (e.getPoint().getY() - mouseBuffer.getY()));
            
            if(Actor instanceof ImplicitParameterNode) {
                Vector<ActivationBarNode> abns = ((ImplicitParameterNode) Actor).getActivationBarNodes();
                for(ActivationBarNode a:abns) {
                    a.update(((Node) Actor).getX()+((Node) Actor).getWidth()/2-15,((Node) Actor).getY()+a.getInnerY()
                            ,((Node) Actor).getX()+((Node) Actor).getWidth()/2-15, ((Node) Actor).getY()+a.getInnerY());
                }
            }
            if (Actor instanceof Node) {
                for (Element ee : getG().getElems()) {
                    if (ee instanceof Edge) {
                        if (Actor.isPointOn(((Edge) ee).getS())) {
                            ((Edge) ee).getS().x +=
                                    (int) e.getPoint().getX() - mouseBuffer.getX();
                            ((Edge) ee).getS().y +=
                                    (int) e.getPoint().getY() - mouseBuffer.getY();
                        }
                        if (Actor.isPointOn(((Edge) ee).getT())) {
                            ((Edge) ee).getT().x +=
                                    (int) e.getPoint().getX() - mouseBuffer.getX();
                            ((Edge) ee).getT().y +=
                                    (int) e.getPoint().getY() - mouseBuffer.getY();
                        }
                    }
                }
                for (Element ee: getG().getElems()){
                    if (ee instanceof Edge)
                        ((Edge) ee).shrink(G);
                }
            }
        }
        mouseBuffer = e.getPoint();
    }

}
