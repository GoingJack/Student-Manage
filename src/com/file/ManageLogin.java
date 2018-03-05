package com.file;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ManageLogin {
	
	static JFrame jf;
	JLabel jl_name, jl11, jl21,jl31, jl4,jl_password;
	JPasswordField jpassword;
	JTextField jt_name;
	JPanel jp1, jp2, jp4, jp_jb, jp_img,jp_scroll;
	static JPanel jp_center,jp_south;
	JButton jb,jb_revise_teacher;
	static JTable table;
	static JScrollPane scrollPane;
	static DefaultTableModel model;
	 JMenuBar jmb;
	 JMenuItem jmb1findStudentMessage,jmb2findTeacherMessage,jmb1reviseStudentMessage,jmb2revise,jmb0message,jmb1deleteStudent,jmb2deleteTeacher;
	
	public ManageLogin(final Manage man) {

		jf = new JFrame("��ӭ " + man.getId() + " �������Ա����ã�");
		jf.setSize(1000, 700);
		jf.setLocation(800, 100);
		jf.setLayout(new BorderLayout(0,0));
		Icon img = new ImageIcon("tu2.jpg");
		jf.add(new JLabel(img),BorderLayout.NORTH);
		
		jmb = new JMenuBar();
		jf.setJMenuBar(jmb);	
		JMenu jmb0 = new JMenu("�ҵ���Ϣ");
		jmb.add(jmb0);
		jmb0message = new JMenuItem("�鿴���޸�");
		jmb0.add(jmb0message);		
		JMenu jmb1 = new JMenu("ѧ������");
		jmb.add(jmb1);
		jmb1findStudentMessage = new JMenuItem("�鿴ѧ����Ϣ");
		jmb1.add(jmb1findStudentMessage);	
		jmb1reviseStudentMessage = new JMenuItem("�޸�ѧ����Ϣ");
		jmb1.add(jmb1reviseStudentMessage);
		jmb1deleteStudent = new JMenuItem("ɾ��ѧ����Ϣ");
		jmb1.add(jmb1deleteStudent);
		
		JMenu jmb2 = new JMenu("��ʦ����");
		jmb.add(jmb2);
		jmb2findTeacherMessage = new JMenuItem("�鿴��ʦ��Ϣ");
		jmb2.add(jmb2findTeacherMessage);
		jmb2revise = new JMenuItem("�޸���ʦ��Ϣ");
		jmb2.add(jmb2revise);
		jmb2deleteTeacher = new JMenuItem("ɾ����ʦ��Ϣ");
		jmb2.add(jmb2deleteTeacher);
		
		jp_center = new JPanel();//���ĵ����
		jp_south = new JPanel();//�·������
		jf.setVisible(true);
		//�鿴���޸��Լ���Ϣ
		jmb0message.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jp_center.removeAll();
				jp_center.setLayout(new GridLayout(7,2));
				jf.add(jp_center,BorderLayout.CENTER);

				jl_name = new JLabel("����");
				jt_name = new JTextField(20);
				jt_name.setText(man.getName());
				jp1 = new JPanel();
				jp1.add(jl_name);
				jp1.add(jt_name);
				
				jl_password = new JLabel("����");
				jpassword = new JPasswordField(20);
				jpassword.setText(man.getPassword());
				jp2 = new JPanel();
				jp2.add(jl_password);
				jp2.add(jpassword);
				
				jl4 = new JLabel("�޸ĳɹ�");
				jp4 = new JPanel();
				jb = new JButton("�޸���Ϣ");
				jp_jb = new JPanel();
				jp_jb.add(jb);

				jp_center.add(jp1);
				jp_center.add(jp2);
				jp_center.add(jp_jb);
				jp_center.add(jp4);
				jf.setVisible(true);
				
				jb.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						man.setName(jt_name.getText());
						man.setPassword(String.valueOf(jpassword.getPassword()));
						Manage newman = man;
						DB.arrMan.clear();
						DB.arrMan = IOStreamManage.readFromFile();
						DB.arrMan.set(Login.searchManage(), newman);
						IOStreamManage.writeToFile();
						jp4.add(jl4);
						jf.setVisible(true);
					}
				});
			}
		});
		//�鿴ѧ����Ϣ
		jmb1findStudentMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�鿴����ѧ����Ϣ");
				ManageLogin.Excel();
				model = (DefaultTableModel) table.getModel();
				model.setColumnIdentifiers(new Object[] { "�û���", "����", "����","�Ա�" });
				CourseManage.findAllStudentMessage();
				jp_center.add(scrollPane);
				jf.setVisible(true);

			}
		});
		//�޸�ѧ����Ϣ
		jmb1reviseStudentMessage.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				CourseManage.jmb1reviseStudentMessage();				
			}
		});
		//ɾ��ѧ��
		jmb1deleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseManage.deleteStudent();			
			}
		});
		
		
		//�鿴��ʦ��Ϣ
		jmb2findTeacherMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�鿴������ʦ��Ϣ");
				ManageLogin.Excel();
				model = (DefaultTableModel) table.getModel();
				model.setColumnIdentifiers(new Object[] { "�û���", "����", "����", "�Ա�", "�γ̱��" ,"���ڿγ�" });
				CourseManage.findAllTeacherMessage();
				jp_center.add(scrollPane);
				jf.setVisible(true);

			}
		});
		//�޸���ʦ��Ϣ
		jmb2revise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageLogin.Excel();
				model = (DefaultTableModel) table.getModel();
				model.setColumnIdentifiers(new Object[] { "�û���", "����", "����","�Ա�", "�γ̱��", "���ڿγ�" });
				CourseManage.findAllTeacherMessage();			
				jb_revise_teacher = new JButton("�޸�");
			    jp_south.add(jb_revise_teacher);
			    jf.add(jp_south,BorderLayout.SOUTH);
			    jf.setVisible(true);
			    
			    jb_revise_teacher.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					CourseManage.teacherReviseJudge();						
					}
				});			    
			}
		});
		//ɾ����ʦ
		jmb2deleteTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseManage.deleteTeacher();			
			}
		});			
	}
	
	//���ģ��
	public static void Excel(){
		jp_center.removeAll();
		jp_south.removeAll();
		jp_center.setLayout(new GridLayout());
		scrollPane = new JScrollPane();
		jp_center.add(scrollPane);
		jf.add(jp_center, BorderLayout.CENTER);
		jf.setVisible(true);
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		table.setRowHeight(30);
		JTableHeader header = table.getTableHeader(); // ��ȡ JTable ��ͷ�Ķ���
		header.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		header.setPreferredSize(new Dimension(header.getWidth(), 35)); // ���ô��������ѡ��С
		scrollPane.setViewportView(table);
	}

}
