package graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class EditorBox extends JDialog implements Cloneable {
    //	Node belongsTo;
    Element belongsTo;
	Vector<infoInput> allTextAreas;
	final int numberOfTextArea;
    JPanel input;//��Ϣ��������
    String[] lables;

    public EditorBox(JFrame owner, Element parent, String title, String[] lables, String known) {
        this(owner, parent, title, lables);
        if (known != null) {

            this.init(known);
        }

    }

    public EditorBox(JFrame owner,Element parent,String title,String[] lables){
		super(owner,title,true);
        this.lables = lables;
        numberOfTextArea = lables.length;
		belongsTo = parent;
		allTextAreas = new Vector<infoInput>();
		this.setLayout(new BorderLayout(20,10));
		//�����Ŀ�϶
		JPanel spaceTop = new JPanel();
		spaceTop.setSize(100,20);
		this.add(spaceTop,BorderLayout.NORTH);
		//��Ϣ��������
        input = new JPanel();
        input.setLayout(new BoxLayout(input,BoxLayout.Y_AXIS));
		add(input,BorderLayout.CENTER);

        for(int i=0,length=lables.length;i<length;i++) {
            addInputArea(lables[i]);
        }

        JButton validate = new JButton("ȷ��");
		JPanel buttonArea = new JPanel();
		buttonArea.setLayout(new BorderLayout());
		buttonArea.setSize(100,40);
		add(buttonArea,BorderLayout.SOUTH);
		validate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);

                belongsTo.text = getAllText();
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

	//��õ�index���ı���������ʾ��Ϣ
//	public String[] getInfoLinesAt(int index) {
//		return allTextAreas.elementAt(index).getDisplayInfoLines();
//	}
	
//	public String getInfoAt(int index) {
//		return allTextAreas.elementAt(index).getInfo();
//	}

    void addInputArea(String label) {


        infoInput tmp = new infoInput(label);

        allTextAreas.addElement(tmp);
        input.add(tmp);
        input.add(Box.createVerticalStrut(10));
    }

    String[] getLabels() {
        return lables;
    }

    //2018.03.08
	//��ȡÿ���������ַ����ļ��� ��**Ϊ�ֽ�
	String getAllText() {
		String r = "";
		for(int i=0,len=allTextAreas.size();i<len;i++) {
			r+=allTextAreas.elementAt(i).getInfo();
			if(i!=len-1) {
				r+="**";
				//�ָ���
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

    @Override
    public Object clone() {
        EditorBox r = new EditorBox(null, belongsTo, this.getTitle(), this.getLabels(), this.getAllText());
        return r;
    }

    class infoInput extends JPanel{
		JTextArea jta;//���������ı���
		JPanel labelArea;//�����label
		String labelName;//label
		String info;//�������Ϣ
		
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
		
		String getInfo() {
			return jta.getText();
		}
		
	}	
}
