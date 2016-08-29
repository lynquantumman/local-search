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
		//对容器和布局 的设定
		frm = new Frame("local Search");
		FlowLayout fl = new FlowLayout();
		frm.setLayout(fl);
		frm.setBounds(300, 200, 600, 400);
		//对布局中组件的设定
		tfgoal = new TextField(60);
		tfaddr = new TextField(50);
		btGo = new Button("搜一下");
		txtarea = new TextArea();
		//将组件添加到容器中
		frm.add(tfgoal);
		frm.add(tfaddr);
		frm.add(btGo);
		frm.add(txtarea);
		//让其可见
		frm.setVisible(true);
		//在相应的组件上注册监听器
		addListener();
		
		tfgoal.setText("在此输入目标文件名称");
		tfaddr.setText("这里需要一个地址");
		txtarea.setText("这里是生成文件的绝对路径");
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
					File f = new File(addr);//这里如果输入的不是文件路径，会怎么样
					
					search(f,goal,txtarea);
					//System.out.println(txtarea.getText());
//					下面是为了处理没有搜到的情况
					if("这里是生成文件的绝对路径".equals(txtarea.getText())){
						txtarea.setText("哎呀，不好意思，没有搜到");
					}
				}
				
			}	
		);
		
		
	}

//	利用递归完成的目标搜索，f欲搜索路径封装的文件对象，goal是目标
//	如果找到则会在txtarea区域给出绝对路径名称
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
