package view;

import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.MemberDao;

public class OrderView extends JFrame implements ActionListener {

	JLabel lb, lb1, lb2, lb3, lb4;
	JButton menu, order;
	JPanel toppan, menupan, botpan, midpan, sizepan, shotpan, syruppan;
	JTextField tf;
	JComboBox cb;
	JRadioButton[] rb1, rb2;
	Checkbox cb1, cb2;
	

	public OrderView() {
		super("주문");
		setLayout(null);

		toppan = new JPanel();
		toppan.setLayout(new GridLayout(1, 2));
		toppan.setBounds(200, 50, 200, 60);
		lb1 = new JLabel("커피 한잔");
		lb1.setBounds(60, 40, 100, 40);
		toppan.add(lb1);

		menu = new JButton("MENU");
		menu.setBounds(400, 40, 100, 40);
		menu.addActionListener(this);
		toppan.add(menu);

		String[] coffee = { "커피를 선택", "헤이즐럿 카라멜 모카", "카라멜 마끼아또", "화이트 초콜렛 모카", "카라멜 모카", "카페모카", "카라멜 라떼", "카페라떼",
				"카푸치노", "아메리카노", "오늘의 커피" };
		cb = new JComboBox(coffee);
		cb.setBounds(70, 100, 430, 30);
		cb.addActionListener(this);
		
		menupan = new JPanel();
		menupan.setLayout(new GridLayout(1, 1));
		menupan.setBounds(100, 120, 400, 30);
		menupan.add(cb);

		midpan = new JPanel();
		midpan.setLayout(new GridLayout(1, 3));
		midpan.setBounds(80, 170, 500, 250);

		sizepan = new JPanel();
		sizepan.setLayout(new GridLayout(5, 1));
		lb = new JLabel("SIZE");
		sizepan.add(lb);
		String sz[] = { "Short", "Tall", "Grande" };
		rb1 = new JRadioButton[3];
		ButtonGroup btng = new ButtonGroup();
		for (int i = 0; i < rb1.length; i++) {
			rb1[i] = new JRadioButton(sz[i]);
			btng.add(rb1[i]);
			sizepan.add(rb1[i]);
		}

		rb1[0].setSelected(true);
		rb1[1].setSelected(false);
		rb1[2].setSelected(false);

		midpan.add(sizepan);

		syruppan = new JPanel();
		syruppan.setLayout(new GridLayout(5, 1));
		lb4 = new JLabel("Syrup");
		syruppan.add(lb4);
		String sz2[] = { "바닐라", "카라멜", "헤이즐럿" };
		rb2 = new JRadioButton[3];
		ButtonGroup btng2 = new ButtonGroup();
		for (int i = 0; i < rb2.length; i++) {
			rb2[i] = new JRadioButton(sz[i]);
			btng.add(rb2[i]);
			syruppan.add(rb2[i]);
		}
		rb2[0].setSelected(false);
		rb2[1].setSelected(false);
		rb2[2].setSelected(false);

		midpan.add(syruppan);

		shotpan = new JPanel();
		shotpan.setLayout(new GridLayout(5, 1));
		lb2 = new JLabel("The Other");
		shotpan.add(lb2);
		cb1 = new Checkbox("샷 추가", false);
		cb2 = new Checkbox("휘핑크림", false);
		

		shotpan.add(cb1);
		shotpan.add(cb2);

		midpan.add(shotpan);

		botpan = new JPanel();
		botpan.setLayout(new GridLayout(1, 3));
		botpan.setBounds(200, 400, 200, 20);

		tf = new JTextField();
		tf.setBounds(20, 100, 80, 20);
		botpan.add(tf);

		lb3 = new JLabel("잔");
		lb3.setBounds(100, 100, 40, 20);
		botpan.add(lb3);

		order = new JButton("주문");
		order.setBounds(300, 100, 80, 50);
		botpan.add(order);

		add(toppan);
		add(menupan);
		add(botpan);
		add(midpan);

		setBounds(600, 200, 600, 500);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();

		if (obj == menu) {
			new CoffeeMenu();
		} else if (obj == order) {
			String id = MemberDao.getInstance().getLoginId();
			String cofee = (String)cb.getSelectedItem();
			String c_size;
			if(rb1[0].isSelected()) {
				c_size = "Short";
			}
			else if(rb1[1].isSelected()) {
				c_size = "Tall";
			}
			else if(rb1[2].isSelected()) {
				c_size = "Grande";
			}
			String syrup;
			if(rb2[0].isSelected()) {
				syrup = "바닐라";
			}
			else if(rb2[1].isSelected()) {
				syrup = "카라멜";
			}
			else if(rb2[2].isSelected()) {
				syrup = "헤이즐럿";
			}
			else {
				syrup = "";
			}
			String other;
			if(obj == cb1) {
				other = "샷 추가";
			}
			else if(obj == cb2) {
				other = "휘핑크림";
			}
			else {
				other = "";
			}
		}
	}

}
