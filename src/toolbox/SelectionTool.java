package toolbox;

import graph.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class SelectionTool extends Tool{
    private Point mouseBuffer;
    private boolean dragging = false;
    public Element Actor = null;
    public Vector<Element> ActorBuffers = new Vector<Element>();

    public Element ActorBuffer = null;
    public boolean multiChoice = false;
    
    
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

    boolean firstDrag;
    boolean firstMulti = true;
    @Override
    public void mouseDown(MouseEvent e) {
        Actor = G.focus(e.getPoint());
        dragging = true;
        mouseBuffer = e.getPoint();
        firstDrag = true;

        if (Actor != null) {
            if (!this.getMultiChoice()) {
                //��ѡ
                cleanActorBuffers();
            }
            if (firstMulti) {
                cleanActorBuffers();
                firstMulti = false;
            }
            Actor.focuson = true;
            if (!ActorBuffers.contains(Actor))
                ActorBuffers.addElement(Actor);
        } else {
            cleanActorBuffers();
            firstMulti = true;
        }
    }

    @Override
    public void mouseUp(MouseEvent e) {
        dragging = false;
        mouseBuffer = e.getPoint();
        G.pointBuffer.addElement(Actor);
    }

    @Override
    public void mouseDrag(MouseEvent e) {

        if (dragging && Actor != null){
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
                        if (((Node) Actor).isAttached(((Edge) ee).getS())) {
                            ((Edge) ee).getS().x +=
                                    (int) e.getPoint().getX() - mouseBuffer.getX();
                            ((Edge) ee).getS().y +=
                                    (int) e.getPoint().getY() - mouseBuffer.getY();
                        }
                        if (((Node) Actor).isAttached(((Edge) ee).getT())) {
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
            Actor.move((int) (e.getPoint().getX() - mouseBuffer.getX()),
                    (int) (e.getPoint().getY() - mouseBuffer.getY()));
        }
        mouseBuffer = e.getPoint();
    }

    public Element getActor() {
        return Actor;
    }

    public void setMultiChoice(boolean b) {
        multiChoice = b;
    }

    public boolean getMultiChoice() {
        return multiChoice;
    }

    public void cleanActorBuffers() {
        for (Element i : ActorBuffers) {
            i.focuson = false;
        }
        ActorBuffers.clear();
    }

    public Vector<Element> getActorBuffers() {
        return ActorBuffers;
    }

}


//package toolbox;
//
//import drawer.DrawSelf;
//import graph.ActivationBarNode;
//import graph.Element;
//import graph.Graph;
//import graph.ImplicitParameterNode;
//import graph.Node;
//
//import java.awt.*;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.util.Vector;
//
//public class SelectionTool extends Tool{
//    private Point mouseBuffer;
//    private boolean dragging = false;
//    public Element Actor = null;
//    public SelectionTool(Graph g)
//    {
//        super(g);
//        setToolName("SelectionTool");
//    }
//
//    @Override
//    public void doubleClicked(MouseEvent e) {
//        if (Actor != null)
//            Actor.pop();
//    }
//
//    @Override
//    public void singleClicked(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseDown(MouseEvent e) {
//        Actor = G.focus(e.getPoint());
//        dragging = true;
//        mouseBuffer = e.getPoint();
//    }
//
//    @Override
//    public void mouseUp(MouseEvent e) {
//        dragging = false;
//        mouseBuffer = e.getPoint();
//    }
//
//    @Override
//    public void mouseDrag(MouseEvent e) {
//
//        if (dragging && Actor != null){
//            Actor.move((int) (e.getPoint().getX() - mouseBuffer.getX()),
//                    (int) (e.getPoint().getY() - mouseBuffer.getY()));
//            
//            if(Actor instanceof ImplicitParameterNode) {
//                Vector<ActivationBarNode> abns = ((ImplicitParameterNode) Actor).getActivationBarNodes();
//                for(ActivationBarNode a:abns) {
//                    a.update(((Node) Actor).getX()+((Node) Actor).getWidth()/2-15,((Node) Actor).getY()+a.getInnerY()
//                            ,((Node) Actor).getX()+((Node) Actor).getWidth()/2-15, ((Node) Actor).getY()+a.getInnerY());
//                }
//            }
//        }
//        mouseBuffer = e.getPoint();
//    }
//
//}
