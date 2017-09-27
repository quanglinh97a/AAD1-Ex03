package com.quanglinhit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Driver;

public class ListStudent extends JFrame {

	DefaultTableModel dtmStudent;
	JTable tblStudent;
	JButton btnAdd;
	public static Connection conn;
	public static Statement state = null;
	
	public ListStudent(String title) {
		super(title);
		addControls();
		addEvents();
		connectMySQL();
		displayDetail();
	}

	private void displayDetail() {
		try {
			state = conn.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM hocvien");
			dtmStudent.setRowCount(0);
			while (rs.next()) {
				Vector<Object> vec = new Vector<>();
				vec.add(rs.getString(1));
				vec.add(rs.getString(2));
				vec.add(rs.getDate(3));
				vec.add(rs.getInt(4));
				vec.add(rs.getString(5));
				dtmStudent.addRow(vec);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void connectMySQL() {
		
		try {
			String strConn = "jdbc:mysql://localhost/db_hocvien?useUnicode=true&characterEncoding=utf-8";
			Properties pro = new Properties();
			pro.put("user", "root");
			pro.put("password", "");
			Driver driver = new Driver();
			conn = driver.connect(strConn, pro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addEvents() {
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormStudent ui = new FormStudent("Thông tin chi tiết sinh viên");
				ui.setVisible(true);
				if (FormStudent.result > 0) {
					displayDetail();
				}
			}
		});
	}

	private void addControls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnNorth = new JPanel();
		JLabel lblTitle = new JLabel("Chương trình quản lý sinh viên");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setForeground(Color.BLUE);
		pnNorth.add(lblTitle);
		con.add(pnNorth, BorderLayout.NORTH);

		dtmStudent = new DefaultTableModel();
		dtmStudent.addColumn("Mã học viên");
		dtmStudent.addColumn("Tên học viên");
		dtmStudent.addColumn("Ngày sinh");
		dtmStudent.addColumn("Số điện thoại");
		dtmStudent.addColumn("Địa chỉ");
		tblStudent = new JTable(dtmStudent);

		JScrollPane scTable = new JScrollPane(tblStudent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		con.add(scTable, BorderLayout.CENTER);

		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout(FlowLayout.LEFT));
		btnAdd = new JButton("Thêm mới");
		pnButton.add(btnAdd);
		con.add(pnButton, BorderLayout.SOUTH);

		this.setSize(700, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
