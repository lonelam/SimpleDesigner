package graph;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.*;

public class EditorBox extends JDialog {
	Element belongsTo;
	Vector<infoInput> allTextAreas;
	final int numberOfTextArea;

	public EditorBox(JFrame owner,Node parent,String title,String[] lables,String known) {
		this(owner, parent, title, lables);
		this.init(known);
	}

	public EditorBox(JFrame owner,Element parent,String title,String[] lables){
		super(owner,title,true);
		numberOfTextArea = lables.length;
		belongsTo = parent;
		allTextAreas = new Vector<infoInput>();
		this.setLayout(new BorderLayout(20,10));
		//顶部的空隙
		JPanel spaceTop = new JPanel();
		spaceTop.setSize(100,20);
		this.add(spaceTop,BorderLayout.NORTH);
		//信息输入区域
		JPanel input = new JPanel();
		input.setLayout(new BoxLayout(input,BoxLayout.Y_AXIS));
		add(input,BorderLayout.CENTER);

		for(int i=0,length=lables.length;i<length;i++) {

			infoInput tmp = new infoInput(lables[i]);

			allTextAreas.addElement(tmp);
			input.add(tmp);
			input.add(Box.createVerticalStrut(10));
		}

		JButton validate = new JButton("确认");
		JPanel buttonArea = new JPanel();
		buttonArea.setLayout(new BorderLayout());
		buttonArea.setSize(100,40);
		add(buttonArea,BorderLayout.SOUTH);
		validate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);

				//按下确认键后，将每一个对话框中的字符串整理成可以显示的样子
//				generateDisplayInfos();

				//元素立即更新信息
//				belongsTo.update();
			}
		});
		buttonArea.add(validate,BorderLayout.EAST);
		JPanel space = new JPanel();
		space.setSize(70,40);
		buttonArea.add(space,BorderLayout.WEST);

		pack();

	}


	public EditorBox(JFrame owner,Node parent,String title,String[] lables){
		super(owner,title,true);
		numberOfTextArea = lables.length;
		belongsTo = parent;
		allTextAreas = new Vector<infoInput>();
		this.setLayout(new BorderLayout(20,10));
		//顶部的空隙
		JPanel spaceTop = new JPanel();
		spaceTop.setSize(100,20);
		this.add(spaceTop,BorderLayout.NORTH);
		//信息输入区域
		JPanel input = new JPanel();
		input.setLayout(new BoxLayout(input,BoxLayout.Y_AXIS));
		add(input,BorderLayout.CENTER);
		
		for(int i=0,length=lables.length;i<length;i++) {
			infoInput tmp = new infoInput(lables[i]);

			allTextAreas.addElement(tmp);
			input.add(tmp);
			input.add(Box.createVerticalStrut(10));
		}
		
		JButton validate = new JButton("确认");
		JPanel buttonArea = new JPanel();
		buttonArea.setLayout(new BorderLayout());
		buttonArea.setSize(100,40);
		add(buttonArea,BorderLayout.SOUTH);
		validate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
				
				//按下确认键后，将每一个对话框中的字符串整理成可以显示的样子
//				generateDisplayInfos();
				
				//元素立即更新信息
//				belongsTo.update();
			}
		});
		buttonArea.add(validate,BorderLayout.EAST);
		JPanel space = new JPanel();
		space.setSize(70,40);
		buttonArea.add(space,BorderLayout.WEST);
		
		pack();
		
	}

	public int getTextAreaCount() {
		return numberOfTextArea;
	}

	//获得第index个文本输入框的显示信息
//	public String[] getInfoLinesAt(int index) {
//		return allTextAreas.elementAt(index).getDisplayInfoLines();
//	}
	
//	public String getInfoAt(int index) {
//		return allTextAreas.elementAt(index).getInfo();
//	}

	
//	void generateDisplayInfos() {
//		for(int i=0,len=allTextAreas.size();i<len;i++) {
//			allTextAreas.elementAt(i).retrieveText();
//		}
//	}
//	
//	Vector<infoInput>  getAllTextAreas(){
//		return allTextAreas;
//	}
	
	//2018.03.08
	//获取每个输入框的字符串的集合 以**为分界
	String getAllText() {
		String r = "";
		for(int i=0,len=allTextAreas.size();i<len;i++) {
			r+=allTextAreas.elementAt(i).getInfo();
			if(i!=len-1) {
				r+="**";
				//分隔符
			}
		}
		return r;
	}
	
	void init(String fromText) {
		int n = allTextAreas.size();
		String[] seperate = fromText.split("\\*\\*");
		for(int i=0;i<n;i++) {
			if(i<seperate.length) {
				allTextAreas.elementAt(i).jta.setText(seperate[i]);
			}
			
		}
	}
	
	class infoInput extends JPanel{
		JTextArea jta;//输入区域文本框
		JPanel labelArea;//输入框label
		String labelName;//label
		String info;//输入框信息
		
		infoInput(String LabelName){
			super();
			info = new String();
			this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
			labelArea = new JPanel();
			labelArea.setPreferredSize(new Dimension(100,100));
			labelArea.setMinimumSize(new Dimension(100,100));
			labelArea.setLayout(new FlowLayout(FlowLayout.RIGHT));
			labelName = LabelName;
			
			JLabel label = new JLabel(LabelName);
			labelArea.add(label);
			this.add(labelArea);
			this.add(Box.createHorizontalStrut(10));
			
			JScrollPane pane = new JScrollPane();
			
			jta = new JTextArea(8,30);
			jta.setBorder(BorderFactory.createTitledBorder(""));
			jta.setLineWrap(true);
			pane.add(jta);
			pane.setViewportView(jta);
			this.add(pane);
			this.add(Box.createHorizontalStrut(10));
			
		}
		
		
//		void retrieveText() {
//			info = jta.getText();
//			infos = info.split("\n");
//		}
//		
//		String[] getDisplayInfoLines() {
//			return infos;
//		}
		String getInfo() {
			return jta.getText();
		}
		
	}	
}