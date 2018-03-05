package com.file;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class StudentLogin {
	
	JFrame jf;
	JLabel jl_name, jl11, jl_age, jl21, jl_sex, jl31, jl_ReviseOK,jl_old_password,jl_new_password1,jl_new_password2,jl_inpassword_err;
	JTextField jt_name, jt_age, jt_sex;
	JPasswordField jp_old_password,jpassword1,jpassword2;
	JPanel jp1, jp2, jp3, jp4, jp_jb, jp_img,jp_scroll,jp_center;
	JButton jb,jb_revise_password;
	static JTable table;
	JScrollPane scrollPane;
	static DefaultTableModel model;
	 JMenuBar jmb;
	 JMenuItem jmb1find,jmb2find,jmb1revisePassword;
	
	public StudentLogin(final Student stu) {

		jf = new JFrame("��ӭ " + stu.getId() + " ͬѧ����ã�");
		jf.setSize(1000, 700);
		jf.setLocation(800, 100);
		jf.setLayout(new BorderLayout(0,0));
		Icon img = new ImageIcon("tu2.jpg");
		jf.add(new JLabel(img),BorderLayout.NORTH);
		
		jmb = new JMenuBar();
		jf.setJMenuBar(jmb);	
		JMenu jmb1 = new JMenu("������Ϣ");
		jmb.add(jmb1);
		jmb1find = new JMenuItem("�鿴������Ϣ");
		jmb1.add(jmb1find);	
		jmb1revisePassword = new JMenuItem("�޸��˻�����");
		jmb1.add(jmb1revisePassword);	
		JMenu jmb2 = new JMenu("�ҵĳɼ�");
		jmb.add(jmb2);
		jmb2find = new JMenuItem("�鿴����");
		jmb2.add(jmb2find);
		
		jp_center = new JPanel();//���ĵ����
		jf.setVisible(true);

		jmb1find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jp_center.removeAll();
				jp_center.setLayout(new GridLayout(7,2));
				jf.add(jp_center,BorderLayout.CENTER);
				
				jl_name = new JLabel("����");
				jt_name = new JTextField(20);
				jt_name.setText(stu.getName());
				jp1 = new JPanel();
				jp1.add(jl_name);
				jp1.add(jt_name);
				jl_age = new JLabel("����");
				jt_age = new JTextField(20);
				jt_age.setText(stu.getAge());
				jp2 = new JPanel();
				jp2.add(jl_age);
				jp2.add(jt_age);
				jl_sex = new JLabel("�Ա�");
				jt_sex = new JTextField(20);
				jt_sex.setText(stu.getSex());
				jp3 = new JPanel();
				jp3.add(jl_sex);
				jp3.add(jt_sex);
				jl_ReviseOK = new JLabel("�޸ĳɹ�");
				jp4 = new JPanel();
				jb = new JButton("�޸���Ϣ");
				jp_jb = new JPanel();
				jp_jb.add(jb);

				jp_center.add(jp1);
				jp_center.add(jp2);
				jp_center.add(jp3);
				jp_center.add(jp_jb);
				jp_center.add(jp4);
				jf.setVisible(true);
				
				jb.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						stu.setName(jt_name.getText());
						stu.setAge(jt_age.getText());
						stu.setSex(jt_sex.getText());
						Student newstu = stu;
						DB.arrStu.clear();
						DB.arrStu = IOStreamStudent.readFromFile();
						DB.arrStu.set(Login.searchStudent(), newstu);
						IOStreamStudent.writeToFile();
						jp4.add(jl_ReviseOK);
						jf.setVisible(true);
					}
				});
			}
		});
		jmb1revisePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jp_center.removeAll();
				jp_center.setLayout(new GridLayout(6,2));
				jf.add(jp_center, BorderLayout.CENTER);
				jl_old_password = new JLabel("ԭ����");
				jp_old_password = new JPasswordField(20);
				jl_new_password1 = new JLabel("������");
				jpassword1 = new JPasswordField(20);
				jl_new_password2 = new JLabel("�ٴ�����������");
				jpassword2 = new JPasswordField(20);
				jb_revise_password = new JButton("����");
				jl_inpassword_err = new JLabel("���������������������");
				jl_inpassword_err.setForeground(Color.red);
				jl_inpassword_err.setFont(new Font("����",Font.ITALIC,16));
				JPanel jp1 = new JPanel();
				JPanel jp2 = new JPanel();
				JPanel jp3 = new JPanel();
				final JPanel jp4 = new JPanel();
				final JPanel jp5 = new JPanel();
				JPanel jp_jb = new JPanel();
				jp1.add(jl_old_password);
				jp1.add(jp_old_password);
				jp_center.add(jp1);
				jp2.add(jl_new_password1);
				jp2.add(jpassword1);
				jp_center.add(jp2);
				jp3.add(jl_new_password2);
				jp3.add(jpassword2);
				jp_center.add(jp3);
				jp_center.add(jp4);
				jp_jb.add(jb_revise_password);
				jp_center.add(jp_jb);
				jp_center.add(jp5);
				jf.setVisible(true);
				
					
				jb_revise_password.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (String.valueOf(jp_old_password.getPassword()).equals(stu.getPassword())
								&& (!(String.valueOf(jpassword2.getPassword())).equals(""))
								&& ((String.valueOf(jpassword1.getPassword())).equals(String.valueOf(jpassword2.getPassword())))) {
							stu.setPassword(String.valueOf(jpassword2.getPassword()));
							Student newstu = stu;
							DB.arrStu.clear();
							DB.arrStu = IOStreamStudent.readFromFile();
							DB.arrStu.set(Login.searchStudent(), newstu);
							IOStreamStudent.writeToFile();
							jp5.removeAll();
							jp5.add(new JLabel("�޸�����ɹ�"));
							jf.setVisible(true);
						} else {
							jp4.removeAll();
							jp4.add(jl_inpassword_err);
							jf.setVisible(true);
						}
					}
				});		
			}
		});
		
		jmb2find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�鿴���гɼ�");
				jp_center.removeAll();
				jp_center.setLayout(new GridLayout());
				scrollPane = new JScrollPane();
				jp_center.add(scrollPane);
				jf.add(jp_center, BorderLayout.CENTER);

				table = new JTable();
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setFont(new Font("΢���ź�", Font.PLAIN, 14));
				table.setRowHeight(30);
				JTableHeader header = table.getTableHeader(); // ��ȡ JTable ��ͷ�Ķ���
				header.setFont(new Font("΢���ź�", Font.PLAIN, 16));
				header.setPreferredSize(new Dimension(header.getWidth(), 35)); // ���ô��������ѡ��С
				scrollPane.setViewportView(table);
				model = (DefaultTableModel) table.getModel();
				model.setColumnIdentifiers(new Object[] { "�γ̱��", "�γ���", "�ɼ�","�ο���ʦ" });
				CourseManage.findOneStudentAllCourseMark(stu);
				jf.setVisible(true);

			}
		});
	}

}
