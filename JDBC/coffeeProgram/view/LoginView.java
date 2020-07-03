package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import dao.MemberDao;

public class LoginView extends JFrame implements ActionListener {
	JTextField tx1;
	JPasswordField pf;
	JButton btn1, btn2;
	JLabel lb1,lb2,lb3;
	JPanel jp = new JPanel();
	
	public LoginView() {

		super("로그인");
		setLayout(null);
		jp.setBounds(0, 0, 300, 280);
		jp.setBackground(Color.white);
		
		lb1 = new JLabel("로그인");
		lb1.setBounds(122, 30, 50, 30);
		add(lb1);
		
		lb2 = new JLabel("ID");
		lb2.setBounds(80, 75, 60, 25);
		lb3 = new JLabel("PW");
		lb3.setBounds(80, 120, 60, 25);
		add(lb2);
		add(lb3);
		
		
		tx1 = new JTextField("ID");
		tx1.setBounds(130, 75, 100, 25);
		tx1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Object obj = e.getSource();
				if(obj == tx1) {
					tx1.setText("");
				}
			}
		});
		pf = new JPasswordField("11111");
		pf.setBounds(130, 120, 100, 25);
		pf.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Object obj = e.getSource();
				if(obj == pf) {
					pf.setText("");
				}
			}
		});
		add(tx1);
		add(pf);
		
		btn1 = new JButton("log-in");
		btn1.setBounds(40, 165, 90, 30);
		btn1.addActionListener(this);
		btn2 = new JButton("회원가입");
		btn2.setBounds(145, 165, 90, 30);
		btn2.addActionListener(this);
		add(btn1);
		add(btn2);
		
		JRootPane  rootPane  = getRootPane();
        rootPane.setDefaultButton(btn1);  
         
		add(jp);
		setBounds(700, 300, 300, 280);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		MemberDao dao = MemberDao.getInstance();
		if(obj == btn1) {
			String id = tx1.getText().trim();
			String pw = pf.getText().trim();
			
			boolean b = dao.login(id, pw);
			
			if(b == true) {
				
				MemberDao.getInstance().setLoginId(id);
				JOptionPane.showMessageDialog(null, id + " 님 환영합니다.");
				dispose();
				new OrderView();
			}
			else {
				JOptionPane.showMessageDialog(null, "ID,PW가 일치 하지 않습니다.");
				tx1.setText("");
				pf.setText("");
			}
		}
		else if(obj == btn2) {
			dispose();
			new NewMember();
		}
	}

}
