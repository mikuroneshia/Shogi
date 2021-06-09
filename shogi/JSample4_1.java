package shogi;

	import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

	class JSample4_1 extends JFrame{
	  public static void main(String args[]){
	    JSample4_1 frame = new JSample4_1("MyTitle");
	    frame.setVisible(true);
	  }

	  JSample4_1(String title){
	    setTitle(title);
	    setBounds(100, 100, 600, 400);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JButton button1 = new JButton("OK");
	    JButton button2 = new JButton("Cancel");
	    button2.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
	    JButton button3 = new JButton("ヘルプ");
	    button3.setFont(new Font("メイリオ", Font.PLAIN, 32));

	    JPanel p = new JPanel();
	    p.add(button1);
	    p.add(button2);
	    p.add(button3);

	    Container contentPane = getContentPane();
	    contentPane.add(p, BorderLayout.CENTER);
	  }
	}

