package com.file;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class TeacherLogin {

	static JFrame jf;
	JLabel jl_name,jl11,jl_age,jl21,jl_sex,jl31,jl4,jl_course,jl_old_password,jl_new_password1,jl_new_password2,jl_inpassword_err;
	JTextField jt_name,jt_age,jt_sex;
	JPasswordField jp_old_password,jpassword1,jpassword2;
	JPanel jp1,jp2,jp3,jp4,jp_jb;
	static JPanel jp_center,jp_south;
	JPanel jp_course;
	JButton jb,jb_revise,jb_revise_password;
	static JScrollPane scrollPane;
	static JTable table;
	static DefaultTableModel model;
	JMenuBar jmb;
	JMenuItem jmb1find,jmb2find,jmb2revise,jmb1revisePassword;
	JMenu jmb1,jmb2;
	
	public TeacherLogin(){}
	
	public TeacherLogin(final Teacher tea){	
		
		jf = new JFrame(tea.getId()+" ��ʦ�����ã�");
		jf.setSize(1000,700);
		jf.setLocation(800,100);
		jf.setLayout(new BorderLayout(0,0));
		Icon img = new ImageIcon("tu2.jpg");
		JLabel jl = new JLabel(img);
		jf.add(jl,BorderLayout.NORTH);	
		
		jmb = new JMenuBar();
		jf.setJMenuBar(jmb);	
		JMenu jmb1 = new JMenu("������Ϣ");
		jmb.add(jmb1);
		jmb1find = new JMenuItem("�鿴������Ϣ");
		jmb1.add(jmb1find);
		jmb1revisePassword = new JMenuItem("�޸��˻�����");
		jmb1.add(jmb1revisePassword);	
		JMenu jmb2 = new JMenu("ѧ���ɼ�");
		jmb.add(jmb2);
		jmb2find = new JMenuItem("�鿴����");
		jmb2.add(jmb2find);
		jmb2revise = new JMenuItem("�޸ĳɼ�");
		jmb2.add(jmb2revise);
		
		jp_center = new JPanel();
		jp_south = new JPanel();
		jf.setVisible(true);
		
		//�鿴���޸ĸ�����Ϣ
		jmb1find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jp_center.removeAll();
				jp_center.setLayout(new GridLayout(7,2));
				jf.add(jp_center,BorderLayout.CENTER);
				jl_name = new JLabel("����");
				jt_name = new JTextField(20);
				jt_name.setText(tea.getName());
				jp1  =new JPanel();
				jp1.add(jl_name);
				jp1.add(jt_name);
				jl_age = new JLabel("����");
				jt_age = new JTextField(20);
				jt_age.setText(tea.getAge());
				jp2 =new JPanel();
				jp2.add(jl_age);
				jp2.add(jt_age);
				jl_sex = new JLabel("�Ա�");
				jt_sex = new JTextField(20);
				jt_sex.setText(tea.getSex());
				jp3 =new JPanel();
				jp3.add(jl_sex);
				jp3.add(jt_sex);
				if( tea.getTeaching_course() == null ){	
					jl_course = new JLabel("���ڿγ�      "+"��");
					jl_course.setForeground(Color.red);
					jl_course.setFont(new Font("����",Font.ITALIC,16));																	
				}else if(tea.getTeaching_course().equals("��")){
					jl_course = new JLabel("���ڿγ�      "+"��");
					jl_course.setForeground(Color.red);
					jl_course.setFont(new Font("����",Font.ITALIC,16));	
				} else {
					jl_course = new JLabel("���ڿγ�      "+tea.getTeaching_course().getCourseName());	
				}			
				jp_course = new JPanel();
				jp_course.add(jl_course);
				
				jl4 = new JLabel("�޸ĳɹ�");
				jp4 = new JPanel();
				jb = new JButton("�޸���Ϣ");
				jp_jb = new JPanel();
				jp_jb.add(jb);
				
				
				jp_center.add(jp1);
				jp_center.add(jp2);
				jp_center.add(jp3);			
				jp_center.add(jp_course);
				jp_center.add(jp_jb);
				jp_center.add(jp4);
				jf.setVisible(true);
				
				jb.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {	
						System.out.println(tea.toString());
						tea.setName(jt_name.getText());
						tea.setAge(jt_age.getText());
						tea.setSex(jt_sex.getText());
						if(tea.getTeaching_course() == null){
							tea.setTeaching_course(new Course());
						}else{
							tea.setTeaching_course(tea.getTeaching_course());
						}						
						Teacher newtea = tea;
						DB.arrTea.clear();
						DB.arrTea = IOStreamTeacher.readFromFile();
						DB.arrTea.set(Login.searchTeacher(),newtea);
						IOStreamTeacher.writeToFile();	
						jp4.add(jl4);
						jf.setVisible(true);
						
					}										
				});
			}
		});
		//�޸�����
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
						if (String.valueOf(jp_old_password.getPassword()).equals(tea.getPassword())
								&& (!(String.valueOf(jpassword2.getPassword())).equals(""))
								&& ((String.valueOf(jpassword1.getPassword())).equals(String.valueOf(jpassword2.getPassword())))) {
							tea.setPassword(String.valueOf(jpassword2.getPassword()));
							Teacher newstu = tea;
							DB.arrTea.clear();
							DB.arrTea = IOStreamTeacher.readFromFile();
							DB.arrTea.set(Login.searchTeacher(), newstu);
							IOStreamTeacher.writeToFile();
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
		//�鿴����ѧ�����ſγɼ�
		jmb2find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�鿴����ѧ���ɼ�");									
					TeacherLogin.Excel();
					model = (DefaultTableModel) table.getModel();
					model.setColumnIdentifiers(new Object[] { "ѧ��ѧ��","ѧ������","��"+tea.getTeaching_course().getCourseName()+"��  �ɼ�"});
					CourseManage.allStudentOneCourse_mark(tea);
					jf.setVisible(true);							
			}
		});
		//��ʦ��ѧ�����
		jmb2revise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�޸ĳɼ�");
				if (tea.getTeaching_course().courseName == null
						|| tea.getTeaching_course().courseName.equals("null")) {
					JOptionPane.showMessageDialog(null, "�����ý�����ԱΪ�������Ŀ", "",
							JOptionPane.WARNING_MESSAGE);
				} else {
				TeacherLogin.Excel();
			    model = (DefaultTableModel) table.getModel();
			    model.setColumnIdentifiers(new Object[] { "ѧ��ѧ��","ѧ������","��"+tea.getTeaching_course().getCourseName()+"��  �ɼ�"});
			    CourseManage.allStudentOneCourse_mark(tea);			   
			    jb_revise = new JButton("�޸ĳɼ�");
			    jp_south.add(jb_revise);
			    jf.add(jp_south,BorderLayout.SOUTH);
			    jf.setVisible(true);
			    
			    jb_revise.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 CourseManage.teacherReviseCourse_mark(tea);						
					}
				});			    
			}
			}
		});
	}
	//���
	public static void Excel(){
		jp_center.removeAll();
		jp_south.removeAll();
		jp_center.setLayout(new GridLayout());
		scrollPane = new JScrollPane();				
		jp_center.add(scrollPane);
		jf.add(jp_center,BorderLayout.CENTER);
		
		table = new JTable();
        // �����ѡ��ģʽ����Ϊֻ������ѡ�񡢵����������ѡ������ѡ�� 
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(true);
        table.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        table.setRowHeight(30);
        JTableHeader header = table.getTableHeader(); // ��ȡ JTable ��ͷ�Ķ���
        header.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        header.setPreferredSize(new Dimension(header.getWidth(), 35)); // ���ô��������ѡ��С
        scrollPane.setViewportView(table);
	}
	
}

