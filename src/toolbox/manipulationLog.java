package toolbox;

import java.util.Vector;

import graph.Element;

public class manipulationLog {
	Vector<manipulationItem> log = new Vector<manipulationItem>();
	// 新建，删除，改信息，拖动
	public void addManipulation(manipulationItem m) {
		log.addElement(m);
	}
	public manipulationItem getRecentChange() {
		manipulationItem r = log.lastElement();
		log.remove(r);
		return r;
	}
	
	private class manipulationItem{
		Element e;
		String recentText;
		int lastX,lastY;
		
		
		public manipulationItem(Element ele,String Text,int x,int y){
			e = ele;
			recentText = Text;
			lastX = x;
			lastY = y;
		}
		public manipulationItem(Element ele,String Text){
			e = ele;
			recentText = Text;
		}
		public manipulationItem(Element ele){
			e = ele;
		}
	}
	
}
