package localSearch;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * @author Cradle Lee
 * @describe This is the local search GUI
 */
public class SearchGUI {
	Frame frm; 
	TextField tfaddr,tfgoal;
	Button btGo;
	TextArea txtarea;
	public SearchGUI(){
		//�������Ͳ��� ���趨
		frm = new Frame("local Search");
		FlowLayout fl = new FlowLayout();
		frm.setLayout(fl);
		frm.setBounds(300, 200, 600, 400);
		//�Բ�����������趨
		tfgoal = new TextField(60);
		tfaddr = new TextField(50);
		btGo = new Button("��һ��");
		txtarea = new TextArea();
		//�������ӵ�������
		frm.add(tfgoal);
		frm.add(tfaddr);
		frm.add(btGo);
		frm.add(txtarea);
		//����ɼ�
		frm.setVisible(true);
		//����Ӧ�������ע�������
		addListener();
		
		tfgoal.setText("�ڴ�����Ŀ���ļ�����");
		tfaddr.setText("������Ҫһ����ַ");
		txtarea.setText("�����������ļ��ľ���·��");
	}
	
	public void addListener(){
		
		frm.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent cls){
				System.exit(0);
			}
		});
		
		btGo.addActionListener(
			new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent clck){
					
					String addr = tfaddr.getText();
					String goal = tfgoal.getText();
					File f = new File(addr);//�����������Ĳ����ļ�·��������ô��
					
					search(f,goal,txtarea);
					//System.out.println(txtarea.getText());
//					������Ϊ�˴���û���ѵ������
					if("�����������ļ��ľ���·��".equals(txtarea.getText())){
						txtarea.setText("��ѽ��������˼��û���ѵ�");
					}
				}
				
			}	
		);
		
		
	}

//	���õݹ���ɵ�Ŀ��������f������·����װ���ļ�����goal��Ŀ��
//	����ҵ������txtarea�����������·������
	public void search(File f, String goal, TextArea txtarea){
		File[] fl;
		if(f.isDirectory()){
			fl = f.listFiles();
			for(File in:fl){
				search(in,goal,txtarea);
			}
		}
		else
			if(f.toString().contains(goal)){
				txtarea.append(f.toString());
				txtarea.append("\r\n");
			}
	} 
	
}
