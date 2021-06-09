package shogi;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument.Iterator;
public class ShogiGUI extends JFrame{
	public static int x;
	public static int y;
	public static ArrayList<Integer> sentemotigoma=new ArrayList<>();
	public static ArrayList<Integer> gotemotigoma=new ArrayList<>();
	public static int komaban;
	public static int utukoma;
	public static boolean komamoti;
	public static boolean utikomi;
	public static boolean matta;
	public static int komaX;
	public static int komaY;
	public static ArrayList<ArrayList<Integer>> idoXY=new ArrayList<>();
	public static ArrayList<int[][]>kihu=new ArrayList<>();
	public static ArrayList<ArrayList<Integer>>sentemotigomakihu=new ArrayList<>();
	public static ArrayList<ArrayList<Integer>>gotemotigomakihu=new ArrayList<>();
	Dimension size;
	Image back;
	Graphics b;
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Shogi.komaMap();
		ShogiGUI s=new ShogiGUI();
		s.addMouseListener(s.new MyMouseListener());
		//JLabel label1=new JLabel("歩");
		Shogi.shokibanmen();
		s.shogiWnd();

	}
	public void shogiWnd() {
		JPanel panel=new JPanel();
		panel.setLayout(null);
		JButton button=new JButton("待った");
		button.setBounds(800, 50, 80, 40);
		MyActionListener mal=new MyActionListener();
		button.addActionListener(mal);
		panel.add(button);
		JButton button2=new JButton("投了");
		button2.setBounds(800, 100, 80, 40);
		ToryouListener tr=new ToryouListener();
		button2.addActionListener(tr);
		panel.add(button2);
		Container contentPane = getContentPane();
		contentPane.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(900,600));
		setVisible(true);
	}
	public void paint(Graphics g) {
		size=getSize();
		back=createImage(size.width, size.height);
		b=back.getGraphics();
		super.paint(b);
		b.setColor(Color.white);
		b.fillRect(0, 0, 900, 900);
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				b.setColor(Color.black);
				b.drawRect(50+i*50, 50+j*50, 50, 50);
			}
		}
		b.drawRect(600, 70, 180, 180);
		b.drawRect(600, 350, 180, 180);
		b.drawString("後手   持駒", 650,60);
		b.drawString("先手   持駒", 650, 340);
		komaido();
		idohani(b);
		utikomi(b);
		teban(b);
		if(!matta) {
			banmenhyoji(b);
		}else {
			Shogi.banmen=kihu.get(kihu.size()-1);
			kihu.remove(kihu.size()-1);
			sentemotigoma=sentemotigomakihu.get(sentemotigomakihu.size()-1);
			sentemotigomakihu.remove(sentemotigomakihu.size()-1);
			gotemotigoma=gotemotigomakihu.get(gotemotigomakihu.size()-1);
			gotemotigomakihu.remove(gotemotigomakihu.size()-1);
			matta=false;
			banmenhyoji(b);
		}
		motigoma(b);
		b.drawString("x:"+String.valueOf(x),500,70);
		b.drawString("y:"+String.valueOf(y),500,100);
		if(x>50&&y>50&&x<500&&y<500) {
			b.drawString(String.valueOf(Shogi.banmen[y/50-1][x/50-1]), 500, 130);
		}
		if(sentemotigoma.contains(61)||Shogi.toryo==0) {
			b.drawString("先手の勝ちです！", 170, 280);
		}else if(gotemotigoma.contains(60)||Shogi.toryo==1) {
			b.drawString("後手の勝ちです！", 170, 280);
		}
		g.drawImage(back, 0, 0, this);
	}

	class MyMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			ShogiGUI.x=e.getX();
			ShogiGUI.y=e.getY();
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ

		}

	}
	
	class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			matta=true;
			tebankousin();
			repaint();
		}
		
	}
	
	class ToryouListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			toryo();
			repaint();
		}
		
	}
	
	public static void banmenhyoji(Graphics b) {
		b.setColor(Color.black);
		Font font=new Font(Font.DIALOG_INPUT,Font.PLAIN,30);
		b.setFont(font);
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				int masu=Shogi.banmen[i][j];
				if(masu!=0&&masu%10==1) {
					String koma=Shogi.komaMap.get(masu);
					if(koma.length()==1) {
						b.drawString(koma, 60+j*50, 90+i*50);
					}else if(koma.length()==2) {
						Font font2=new Font(Font.DIALOG_INPUT,Font.PLAIN,20);
						b.setFont(font2);
						b.drawString(koma, 58+j*50, 90+i*50);
						b.setFont(font);
					}
				}else if(masu!=0&&masu%10==0) {
					Graphics2D g2=(Graphics2D)b;
					String koma=Shogi.komaMap.get(masu);
					if(koma.length()==1) {
						g2.setFont(font);
					}else if(koma.length()==2) {
						Font font2=new Font(Font.DIALOG_INPUT,Font.PLAIN,20);
						g2.setFont(font2);
					}
					AffineTransform at=g2.getTransform();
					at.setToRotation(Math.toRadians(180));
					g2.setTransform(at);
					g2.drawString(koma, -(95+j*50), -(70+i*50));
					at.setToRotation(Math.toRadians(0));
					g2.setTransform(at);
					g2.setFont(font);
				}
			}
		}
	}
	public static void banmenhyoji(Graphics b,int[][]kihu) {
		b.setColor(Color.black);
		Font font=new Font(Font.DIALOG_INPUT,Font.PLAIN,30);
		b.setFont(font);
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				int masu=kihu[i][j];
				if(masu!=0&&masu%10==1) {
					String koma=Shogi.komaMap.get(masu);
					if(koma.length()==1) {
						b.drawString(koma, 60+j*50, 90+i*50);
					}else if(koma.length()==2) {
						Font font2=new Font(Font.DIALOG_INPUT,Font.PLAIN,20);
						b.setFont(font2);
						b.drawString(koma, 58+j*50, 90+i*50);
						b.setFont(font);
					}
				}else if(masu!=0&&masu%10==0) {
					Graphics2D g2=(Graphics2D)b;
					String koma=Shogi.komaMap.get(masu);
					if(koma.length()==1) {
						g2.setFont(font);
					}else if(koma.length()==2) {
						Font font2=new Font(Font.DIALOG_INPUT,Font.PLAIN,20);
						g2.setFont(font2);
					}
					AffineTransform at=g2.getTransform();
					at.setToRotation(Math.toRadians(180));
					g2.setTransform(at);
					g2.drawString(koma, -(95+j*50), -(70+i*50));
					at.setToRotation(Math.toRadians(0));
					g2.setTransform(at);
					g2.setFont(font);
				}
			}
		}
	}

	public static void idohani(Graphics b) {
		int suji=x/50*50;
		int dan=y/50*50;
		int koma=0;
		
		if(x>50&&y>50&&x<=500&&y<=500) {
			koma=Shogi.banmen[y/50-1][x/50-1];
		}
		ArrayList<Integer> xy=new ArrayList<>();
		xy.add(suji);
		xy.add(dan);
		if((suji==0||dan==0||suji>500||dan>500||koma==0)&&!idoXY.contains(xy)||
				(komamoti==true&&komaX==suji/50-1&&komaY==dan/50-1)) {
			komamoti=false;
			if(idoXY!=null)idoXY.clear();
		}
			
			
			//歩
		else if(koma==10&&Shogi.teban==0) {
				komamoti=true;
				komaX=suji/50-1;
				komaY=dan/50-1;
				if(idoXY!=null)idoXY.clear();
				if(Shogi.banmen[y/50+1-1][x/50-1]==0||Shogi.banmen[y/50+1-1][x/50-1]%10==1) {
				xy=new ArrayList<>();
				xy.add(suji);
				xy.add(dan+50);
				idoXY.add(xy);
				b.setColor(Color.YELLOW);
				b.fillRect(suji, dan, 50, 50);
				b.setColor(Color.RED);
				b.fillRect(suji,dan+50 , 50, 50);
				b.setColor(Color.black);
				b.drawRect(suji,dan+50 , 50, 50);
			}
		}else if(koma==11&&Shogi.teban==1) {
			if(Shogi.banmen[y/50-1-1][x/50-1]%10!=1) {
				komamoti=true;
				komaX=suji/50-1;
				komaY=dan/50-1;
				if(idoXY!=null)idoXY.clear();
				xy=new ArrayList<>();
				xy.add(suji);
				xy.add(dan-50);
				idoXY.add(xy);
				b.setColor(Color.YELLOW);
				b.fillRect(suji, dan, 50, 50);
				b.setColor(Color.RED);
				b.fillRect(suji,dan-50 , 50, 50);
				b.setColor(Color.black);
				b.drawRect(suji,dan-50 , 50, 50);
			}
			
			
			//香車
		}else if(koma==20&&Shogi.teban==0) {
			komamoti=true;
			komaX=suji/50-1;
			komaY=dan/50-1;
			if(idoXY!=null)idoXY.clear();
			try {
			for(int i=1;i<9;i++) {
				if(Shogi.banmen[y/50+i-1][x/50-1]==0) {
					xy=new ArrayList<>();
					xy.add(suji);
					xy.add(dan+50*i);
					idoXY.add(xy);
					b.setColor(Color.YELLOW);
					b.fillRect(suji, dan, 50, 50);
					b.setColor(Color.RED);
					b.fillRect(suji,dan+50*i , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji,dan+50*i, 50, 50);
				}else if(Shogi.banmen[y/50+i-1][x/50-1]%10==1){
					xy=new ArrayList<>();
					xy.add(suji);
					xy.add(dan+50*i);
					idoXY.add(xy);
					b.setColor(Color.YELLOW);
					b.fillRect(suji, dan, 50, 50);
					b.setColor(Color.RED);
					b.fillRect(suji,dan+50*i , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji,dan+50*i, 50, 50);
					break;
				}else {
					break;
				}
			}
			}catch(ArrayIndexOutOfBoundsException e) {}
		}else if(koma==21&&Shogi.teban==1) {
			komamoti=true;
			komaX=suji/50-1;
			komaY=dan/50-1;
			if(idoXY!=null)idoXY.clear();
			try {
			for(int i=1;i<9;i++) {
				if(Shogi.banmen[y/50-i-1][x/50-1]==0) {
					xy=new ArrayList<>();
					xy.add(suji);
					xy.add(dan-50*i);
					idoXY.add(xy);
					b.setColor(Color.YELLOW);
					b.fillRect(suji, dan, 50, 50);
					b.setColor(Color.RED);
					b.fillRect(suji,dan-50*i , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji,dan-50*i, 50, 50);
				}else if(Shogi.banmen[y/50-i-1][x/50-1]%10==0){
					xy=new ArrayList<>();
					xy.add(suji);
					xy.add(dan-50*i);
					idoXY.add(xy);
					b.setColor(Color.YELLOW);
					b.fillRect(suji, dan, 50, 50);
					b.setColor(Color.RED);
					b.fillRect(suji,dan-50*i , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji,dan-50*i, 50, 50);
					break;
				}else {
					break;
				}
			}
			}catch(ArrayIndexOutOfBoundsException e) {}
			
			
			
			//桂
		}else if(koma==30&&Shogi.teban==0){
			komamoti=true;
			komaX=suji/50-1;
			komaY=dan/50-1;
			if(idoXY!=null)idoXY.clear();
			b.setColor(Color.YELLOW);
			b.fillRect(suji, dan, 50, 50);
			try {
				if(Shogi.banmen[y/50-1+2][x/50-1-1]%10==1||Shogi.banmen[y/50-1+2][x/50-1-1]==0) {
					xy=new ArrayList<>();
					xy.add(suji-50);
					xy.add(dan+100);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji-50,dan+100 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji-50,dan+100 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1+2][x/50-1+1]%10==1||Shogi.banmen[y/50-1+2][x/50-1+1]==0) {
					xy=new ArrayList<>();
					xy.add(suji+50);
					xy.add(dan+100);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji+50,dan+100 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji+50,dan+100 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
		}
		
		else if(koma==31&&Shogi.teban==1){
			komamoti=true;
			komaX=suji/50-1;
			komaY=dan/50-1;
			if(idoXY!=null)idoXY.clear();
			b.setColor(Color.YELLOW);
			b.fillRect(suji, dan, 50, 50);
			try {
				if(Shogi.banmen[y/50-1-2][x/50-1-1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji-50);
					xy.add(dan-100);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji-50,dan-100 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji-50,dan-100 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1-2][x/50-1+1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji+50);
					xy.add(dan-100);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji+50,dan-100 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji+50,dan-100 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			
		}
			
		
		
			//銀
			else if(koma==40&&Shogi.teban==0) {
				komamoti=true;
				komaX=suji/50-1;
				komaY=dan/50-1;
				if(idoXY!=null)idoXY.clear();
				b.setColor(Color.YELLOW);
				b.fillRect(suji, dan, 50, 50);
				try {
					if(Shogi.banmen[y/50-1+1][x/50-1]%10==1||Shogi.banmen[y/50-1+1][x/50-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji);
						xy.add(dan+50);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji,dan+50 , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji,dan+50 , 50, 50);
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
				try {
					if(Shogi.banmen[y/50-1+1][x/50-1-1]%10==1||Shogi.banmen[y/50-1+1][x/50-1-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji-50);
						xy.add(dan+50);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50,dan+50 , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50,dan+50 , 50, 50);
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
				try {
					if(Shogi.banmen[y/50-1+1][x/50-1+1]%10==1||Shogi.banmen[y/50-1+1][x/50-1+1]==0) {
						xy=new ArrayList<>();
						xy.add(suji+50);
						xy.add(dan+50);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50,dan+50 , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50,dan+50 , 50, 50);
					}
				}catch(ArrayIndexOutOfBoundsException e){}
				try {
					if(Shogi.banmen[y/50-1-1][x/50-1-1]%10==1||Shogi.banmen[y/50-1-1][x/50-1-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji-50);
						xy.add(dan-50);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50,dan-50 , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50,dan-50 , 50, 50);
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
				try {
					if(Shogi.banmen[y/50-1-1][x/50-1+1]%10==1||Shogi.banmen[y/50-1-1][x/50-1+1]==0) {
						xy=new ArrayList<>();
						xy.add(suji+50);
						xy.add(dan-50);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50,dan-50 , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50,dan-50 , 50, 50);
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
		}
			
			else if(koma==41&&Shogi.teban==1) {
			komamoti=true;
			komaX=suji/50-1;
			komaY=dan/50-1;
			if(idoXY!=null)idoXY.clear();
			b.setColor(Color.YELLOW);
			b.fillRect(suji, dan, 50, 50);
			try {
				if(Shogi.banmen[y/50-1-1][x/50-1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji);
					xy.add(dan-50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji,dan-50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji,dan-50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1-1][x/50-1-1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji-50);
					xy.add(dan-50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji-50,dan-50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji-50,dan-50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1-1][x/50-1+1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji+50);
					xy.add(dan-50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji+50,dan-50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji+50,dan-50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e){}
			try {
				if(Shogi.banmen[y/50-1+1][x/50-1-1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji-50);
					xy.add(dan+50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji-50,dan+50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji-50,dan+50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1+1][x/50-1+1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji+50);
					xy.add(dan+50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji+50,dan+50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji+50,dan+50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			
			
			
			
			
			//金
		}else if((koma==50||koma==110||koma==120||koma==130||koma==140)&&Shogi.teban==0) {
			komamoti=true;
			komaX=suji/50-1;
			komaY=dan/50-1;
			if(idoXY!=null)idoXY.clear();
			b.setColor(Color.YELLOW);
			b.fillRect(suji, dan, 50, 50);
			try {
				if(Shogi.banmen[y/50-1+1][x/50-1]%10==1||Shogi.banmen[y/50-1+1][x/50-1]==0) {
					xy=new ArrayList<>();
					xy.add(suji);
					xy.add(dan+50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji,dan+50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji,dan+50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1+1][x/50-1-1]%10==1||Shogi.banmen[y/50-1+1][x/50-1-1]==0) {
					xy=new ArrayList<>();
					xy.add(suji-50);
					xy.add(dan+50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji-50,dan+50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji-50,dan+50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1+1][x/50-1+1]%10==1||Shogi.banmen[y/50+1-1][x/50-1+1]==0) {
					xy=new ArrayList<>();
					xy.add(suji+50);
					xy.add(dan+50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji+50,dan+50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji+50,dan+50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1][x/50-1-1]%10==1||Shogi.banmen[y/50-1][x/50-1-1]==0) {
					xy=new ArrayList<>();
					xy.add(suji-50);
					xy.add(dan);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji-50,dan , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji-50,dan , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1][x/50-1+1]%10==1||Shogi.banmen[y/50-1][x/50-1+1]==0) {
					xy=new ArrayList<>();
					xy.add(suji+50);
					xy.add(dan);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji+50,dan , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji+50,dan , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1-1][x/50-1]%10==1||Shogi.banmen[y/50-1-1][x/50-1]==0) {
					xy=new ArrayList<>();
					xy.add(suji);
					xy.add(dan-50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji,dan-50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji,dan-50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
		}
			else if((koma==51||koma==111||koma==121||koma==131||koma==141)&&Shogi.teban==1) {
				komamoti=true;
				komaX=suji/50-1;
				komaY=dan/50-1;
				if(idoXY!=null)idoXY.clear();
				b.setColor(Color.YELLOW);
				b.fillRect(suji, dan, 50, 50);
				try {
					if(Shogi.banmen[y/50-1-1][x/50-1]%10!=1) {
						xy=new ArrayList<>();
						xy.add(suji);
						xy.add(dan-50);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji,dan-50 , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji,dan-50 , 50, 50);
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
				try {
					if(Shogi.banmen[y/50-1-1][x/50-1-1]%10!=1) {
						xy=new ArrayList<>();
						xy.add(suji-50);
						xy.add(dan-50);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50,dan-50 , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50,dan-50 , 50, 50);
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
				try {
					if(Shogi.banmen[y/50-1-1][x/50-1+1]%10!=1) {
						xy=new ArrayList<>();
						xy.add(suji+50);
						xy.add(dan-50);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50,dan-50 , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50,dan-50 , 50, 50);
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
				try {
					if(Shogi.banmen[y/50-1][x/50-1-1]%10!=1) {
						xy=new ArrayList<>();
						xy.add(suji-50);
						xy.add(dan);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50,dan , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50,dan , 50, 50);
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
				try {
					if(Shogi.banmen[y/50-1][x/50-1+1]%10!=1) {
						xy=new ArrayList<>();
						xy.add(suji+50);
						xy.add(dan);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50,dan , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50,dan , 50, 50);
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
				try {
					if(Shogi.banmen[y/50-1+1][x/50-1]%10!=1) {
						xy=new ArrayList<>();
						xy.add(suji);
						xy.add(dan+50);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji,dan+50 , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji,dan+50 , 50, 50);
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
			
				
				
				
			//玉
		}else if(koma==60&&Shogi.teban==0) {
			komamoti=true;
			komaX=suji/50-1;
			komaY=dan/50-1;
			if(idoXY!=null)idoXY.clear();
			b.setColor(Color.YELLOW);
			b.fillRect(suji, dan, 50, 50);
			try {
				if(Shogi.banmen[y/50-1-1][x/50-1]%10==1||Shogi.banmen[y/50-1-1][x/50-1]==0) {
					xy=new ArrayList<>();
					xy.add(suji);
					xy.add(dan-50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji,dan-50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji,dan-50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1-1][x/50-1-1]%10==1||Shogi.banmen[y/50-1-1][x/50-1-1]==0) {
					xy=new ArrayList<>();
					xy.add(suji-50);
					xy.add(dan-50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji-50,dan-50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji-50,dan-50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1-1][x/50-1+1]%10==1||Shogi.banmen[y/50-1-1][x/50-1+1]==0) {
					xy=new ArrayList<>();
					xy.add(suji+50);
					xy.add(dan-50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji+50,dan-50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji+50,dan-50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1][x/50-1-1]%10==1||Shogi.banmen[y/50-1][x/50-1-1]==0) {
					xy=new ArrayList<>();
					xy.add(suji-50);
					xy.add(dan);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji-50,dan , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji-50,dan , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1][x/50-1+1]%10==1||Shogi.banmen[y/50-1][x/50-1+1]==0) {
					xy=new ArrayList<>();
					xy.add(suji+50);
					xy.add(dan);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji+50,dan , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji+50,dan , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1+1][x/50-1-1]%10==1||Shogi.banmen[y/50-1+1][x/50-1-1]==0) {
					xy=new ArrayList<>();
					xy.add(suji-50);
					xy.add(dan+50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji-50,dan+50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji-50,dan+50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1+1][x/50-1+1]%10==1||Shogi.banmen[y/50-1+1][x/50-1+1]==0) {
					xy=new ArrayList<>();
					xy.add(suji+50);
					xy.add(dan+50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji+50,dan+50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji+50,dan+50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1+1][x/50-1]%10==1||Shogi.banmen[y/50-1+1][x/50-1]==0) {
					xy=new ArrayList<>();
					xy.add(suji);
					xy.add(dan+50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji,dan+50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji,dan+50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
		}
		
		else if(koma==61&&Shogi.teban==1) {
			komamoti=true;
			komaX=suji/50-1;
			komaY=dan/50-1;
			if(idoXY!=null)idoXY.clear();
			b.setColor(Color.YELLOW);
			b.fillRect(suji, dan, 50, 50);
			try {
				if(Shogi.banmen[y/50-1-1][x/50-1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji);
					xy.add(dan-50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji,dan-50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji,dan-50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1-1][x/50-1-1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji-50);
					xy.add(dan-50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji-50,dan-50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji-50,dan-50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1-1][x/50-1+1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji+50);
					xy.add(dan-50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji+50,dan-50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji+50,dan-50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1][x/50-1-1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji-50);
					xy.add(dan);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji-50,dan , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji-50,dan , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1][x/50-1+1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji+50);
					xy.add(dan);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji+50,dan , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji+50,dan , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1+1][x/50-1-1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji-50);
					xy.add(dan+50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji-50,dan+50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji-50,dan+50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1+1][x/50-1+1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji+50);
					xy.add(dan+50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji+50,dan+50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji+50,dan+50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1+1][x/50-1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji);
					xy.add(dan+50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji,dan+50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji,dan+50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}


			
			//飛車
		}else if(koma==70&&Shogi.teban==0) {
			komamoti=true;
			komaX=suji/50-1;
			komaY=dan/50-1;
			if(idoXY!=null)idoXY.clear();
			b.setColor(Color.YELLOW);
			b.fillRect(suji, dan, 50, 50);
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50-i-1][x/50-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji);
						xy.add(dan-50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji,dan-50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji,dan-50*i, 50, 50);
					}else if(Shogi.banmen[y/50-i-1][x/50-1]%10==1){
						xy=new ArrayList<>();
						xy.add(suji);
						xy.add(dan-50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji,dan-50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji,dan-50*i, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50+i-1][x/50-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji);
						xy.add(dan+50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji,dan+50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji,dan+50*i, 50, 50);
					}else if(Shogi.banmen[y/50+i-1][x/50-1]%10==1){
						xy=new ArrayList<>();
						xy.add(suji);
						xy.add(dan+50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji,dan+50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji,dan+50*i, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50-1][x/50-i-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji-50*i);
						xy.add(dan);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50*i,dan , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50*i,dan, 50, 50);
					}else if(Shogi.banmen[y/50-1][x/50-1-i]%10==1){
						xy=new ArrayList<>();
						xy.add(suji-50*i);
						xy.add(dan);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50*i,dan , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50*i,dan, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50-1][x/50+i-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji+50*i);
						xy.add(dan);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50*i,dan , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50*i,dan, 50, 50);
					}else if(Shogi.banmen[y/50-1][x/50-1+i]%10==1){
						xy=new ArrayList<>();
						xy.add(suji+50*i);
						xy.add(dan);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50*i,dan , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50*i,dan, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
		}
		else if(koma==71&&Shogi.teban==1) {
			komamoti=true;
			komaX=suji/50-1;
			komaY=dan/50-1;
			if(idoXY!=null)idoXY.clear();
			b.setColor(Color.YELLOW);
			b.fillRect(suji, dan, 50, 50);
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50-i-1][x/50-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji);
						xy.add(dan-50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji,dan-50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji,dan-50*i, 50, 50);
					}else if(Shogi.banmen[y/50-i-1][x/50-1]%10==0){
						xy=new ArrayList<>();
						xy.add(suji);
						xy.add(dan-50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji,dan-50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji,dan-50*i, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50+i-1][x/50-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji);
						xy.add(dan+50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji,dan+50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji,dan+50*i, 50, 50);
					}else if(Shogi.banmen[y/50+i-1][x/50-1]%10==0){
						xy=new ArrayList<>();
						xy.add(suji);
						xy.add(dan+50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji,dan+50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji,dan+50*i, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50-1][x/50-i-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji-50*i);
						xy.add(dan);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50*i,dan , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50*i,dan, 50, 50);
					}else if(Shogi.banmen[y/50-1][x/50-1-i]%10==0){
						xy=new ArrayList<>();
						xy.add(suji-50*i);
						xy.add(dan);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50*i,dan , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50*i,dan, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50-1][x/50-1+i]==0) {
						xy=new ArrayList<>();
						xy.add(suji+50*i);
						xy.add(dan);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50*i,dan , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50*i,dan, 50, 50);
					}else if(Shogi.banmen[y/50-1][x/50-1+i]%10==0){
						xy=new ArrayList<>();
						xy.add(suji+50*i);
						xy.add(dan);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50*i,dan , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50*i,dan, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
		}


		
		
		//角
		else if(koma==80&&Shogi.teban==0) {
			komamoti=true;
			komaX=suji/50-1;
			komaY=dan/50-1;
			if(idoXY!=null)idoXY.clear();
			b.setColor(Color.YELLOW);
			b.fillRect(suji, dan, 50, 50);
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50-i-1][x/50-i-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji-50*i);
						xy.add(dan-50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50*i,dan-50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50*i,dan-50*i, 50, 50);
					}else if(Shogi.banmen[y/50-i-1][x/50-i-1]%10==1){
						xy=new ArrayList<>();
						xy.add(suji-50*i);
						xy.add(dan-50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50*i,dan-50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50*i,dan-50*i, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50-i-1][x/50+i-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji+50*i);
						xy.add(dan-50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50*i,dan-50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50*i,dan-50*i, 50, 50);
					}else if(Shogi.banmen[y/50-i-1][x/50+i-1]%10==1){
						xy=new ArrayList<>();
						xy.add(suji+50*i);
						xy.add(dan-50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50*i,dan-50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50*i,dan-50*i, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50+i-1][x/50+i-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji+50*i);
						xy.add(dan+50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50*i,dan+50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50*i,dan+50*i, 50, 50);
					}else if(Shogi.banmen[y/50+i-1][x/50+i-1]%10==1){
						xy=new ArrayList<>();
						xy.add(suji+50*i);
						xy.add(dan+50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50*i,dan+50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50*i,dan+50*i, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50+i-1][x/50-i-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji-50*i);
						xy.add(dan+50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50*i,dan+50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50*i,dan+50*i, 50, 50);
					}else if(Shogi.banmen[y/50+i-1][x/50-i-1]%10==1){
						xy=new ArrayList<>();
						xy.add(suji-50*i);
						xy.add(dan+50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50*i,dan+50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50*i,dan+50*i, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
		}
			else if(koma==81&&Shogi.teban==1) {
				komamoti=true;
				komaX=suji/50-1;
				komaY=dan/50-1;
				if(idoXY!=null)idoXY.clear();
				b.setColor(Color.YELLOW);
				b.fillRect(suji, dan, 50, 50);
				try {
					for(int i=1;i<9;i++) {
						if(Shogi.banmen[y/50-i-1][x/50-i-1]==0) {
							xy=new ArrayList<>();
							xy.add(suji-50*i);
							xy.add(dan-50*i);
							idoXY.add(xy);
							b.setColor(Color.RED);
							b.fillRect(suji-50*i,dan-50*i , 50, 50);
							b.setColor(Color.black);
							b.drawRect(suji-50*i,dan-50*i, 50, 50);
						}else if(Shogi.banmen[y/50-i-1][x/50-i-1]%10==0){
							xy=new ArrayList<>();
							xy.add(suji-50*i);
							xy.add(dan-50*i);
							idoXY.add(xy);
							b.setColor(Color.RED);
							b.fillRect(suji-50*i,dan-50*i , 50, 50);
							b.setColor(Color.black);
							b.drawRect(suji-50*i,dan-50*i, 50, 50);
							break;
						}else {
							break;
						}
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
				try {
					for(int i=1;i<9;i++) {
						if(Shogi.banmen[y/50-i-1][x/50+i-1]==0) {
							xy=new ArrayList<>();
							xy.add(suji+50*i);
							xy.add(dan-50*i);
							idoXY.add(xy);
							b.setColor(Color.RED);
							b.fillRect(suji+50*i,dan-50*i , 50, 50);
							b.setColor(Color.black);
							b.drawRect(suji+50*i,dan-50*i, 50, 50);
						}else if(Shogi.banmen[y/50-i-1][x/50+i-1]%10==0){
							xy=new ArrayList<>();
							xy.add(suji+50*i);
							xy.add(dan-50*i);
							idoXY.add(xy);
							b.setColor(Color.RED);
							b.fillRect(suji+50*i,dan-50*i , 50, 50);
							b.setColor(Color.black);
							b.drawRect(suji+50*i,dan-50*i, 50, 50);
							break;
						}else {
							break;
						}
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
				try {
					for(int i=1;i<9;i++) {
						if(Shogi.banmen[y/50+i-1][x/50+i-1]==0) {
							xy=new ArrayList<>();
							xy.add(suji+50*i);
							xy.add(dan+50*i);
							idoXY.add(xy);
							b.setColor(Color.RED);
							b.fillRect(suji+50*i,dan+50*i , 50, 50);
							b.setColor(Color.black);
							b.drawRect(suji+50*i,dan+50*i, 50, 50);
						}else if(Shogi.banmen[y/50+i-1][x/50+i-1]%10==0){
							xy=new ArrayList<>();
							xy.add(suji+50*i);
							xy.add(dan+50*i);
							idoXY.add(xy);
							b.setColor(Color.RED);
							b.fillRect(suji+50*i,dan+50*i , 50, 50);
							b.setColor(Color.black);
							b.drawRect(suji+50*i,dan+50*i, 50, 50);
							break;
						}else {
							break;
						}
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
				try {
					for(int i=1;i<9;i++) {
						if(Shogi.banmen[y/50+i-1][x/50-i-1]==0) {
							xy=new ArrayList<>();
							xy.add(suji-50*i);
							xy.add(dan+50*i);
							idoXY.add(xy);
							b.setColor(Color.RED);
							b.fillRect(suji-50*i,dan+50*i , 50, 50);
							b.setColor(Color.black);
							b.drawRect(suji-50*i,dan+50*i, 50, 50);
						}else if(Shogi.banmen[y/50+i-1][x/50-i-1]%10==0){
							xy=new ArrayList<>();
							xy.add(suji-50*i);
							xy.add(dan+50*i);
							idoXY.add(xy);
							b.setColor(Color.RED);
							b.fillRect(suji-50*i,dan+50*i , 50, 50);
							b.setColor(Color.black);
							b.drawRect(suji-50*i,dan+50*i, 50, 50);
							break;
						}else {
							break;
						}
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
		}
			
			
		
		
		//龍
			else if(koma==170&&Shogi.teban==0) {
			komamoti=true;
			komaX=suji/50-1;
			komaY=dan/50-1;
			if(idoXY!=null)idoXY.clear();
			b.setColor(Color.YELLOW);
			b.fillRect(suji, dan, 50, 50);
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50-i-1][x/50-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji);
						xy.add(dan-50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji,dan-50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji,dan-50*i, 50, 50);
					}else if(Shogi.banmen[y/50-i-1][x/50-1]%10==1){
						xy=new ArrayList<>();
						xy.add(suji);
						xy.add(dan-50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji,dan-50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji,dan-50*i, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50+i-1][x/50-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji);
						xy.add(dan+50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji,dan+50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji,dan+50*i, 50, 50);
					}else if(Shogi.banmen[y/50+i-1][x/50-1]%10==1){
						xy=new ArrayList<>();
						xy.add(suji);
						xy.add(dan+50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji,dan+50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji,dan+50*i, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50-1][x/50-i-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji-50*i);
						xy.add(dan);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50*i,dan , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50*i,dan, 50, 50);
					}else if(Shogi.banmen[y/50-1][x/50-1-i]%10==1){
						xy=new ArrayList<>();
						xy.add(suji-50*i);
						xy.add(dan);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50*i,dan , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50*i,dan, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50-1][x/50+i-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji+50*i);
						xy.add(dan);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50*i,dan , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50*i,dan, 50, 50);
					}else if(Shogi.banmen[y/50-1][x/50-1+i]%10==1){
						xy=new ArrayList<>();
						xy.add(suji+50*i);
						xy.add(dan);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50*i,dan , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50*i,dan, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1-1][x/50-1-1]%10==1||Shogi.banmen[y/50-1-1][x/50-1-1]==0) {
					xy=new ArrayList<>();
					xy.add(suji-50);
					xy.add(dan-50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji-50,dan-50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji-50,dan-50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1-1][x/50-1+1]%10==1||Shogi.banmen[y/50-1-1][x/50-1+1]==0) {
					xy=new ArrayList<>();
					xy.add(suji+50);
					xy.add(dan-50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji+50,dan-50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji+50,dan-50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1+1][x/50-1-1]%10==1||Shogi.banmen[y/50-1+1][x/50-1-1]==0) {
					xy=new ArrayList<>();
					xy.add(suji-50);
					xy.add(dan+50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji-50,dan+50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji-50,dan+50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1+1][x/50-1+1]%10==1||Shogi.banmen[y/50-1+1][x/50-1+1]==0) {
					xy=new ArrayList<>();
					xy.add(suji+50);
					xy.add(dan+50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji+50,dan+50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji+50,dan+50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
		}
		
			else if(koma==171&&Shogi.teban==1) {
				komamoti=true;
				komaX=suji/50-1;
				komaY=dan/50-1;
				if(idoXY!=null)idoXY.clear();
				b.setColor(Color.YELLOW);
				b.fillRect(suji, dan, 50, 50);
				try {
					for(int i=1;i<9;i++) {
						if(Shogi.banmen[y/50-i-1][x/50-1]==0) {
							xy=new ArrayList<>();
							xy.add(suji);
							xy.add(dan-50*i);
							idoXY.add(xy);
							b.setColor(Color.RED);
							b.fillRect(suji,dan-50*i , 50, 50);
							b.setColor(Color.black);
							b.drawRect(suji,dan-50*i, 50, 50);
						}else if(Shogi.banmen[y/50-i-1][x/50-1]%10==0){
							xy=new ArrayList<>();
							xy.add(suji);
							xy.add(dan-50*i);
							idoXY.add(xy);
							b.setColor(Color.RED);
							b.fillRect(suji,dan-50*i , 50, 50);
							b.setColor(Color.black);
							b.drawRect(suji,dan-50*i, 50, 50);
							break;
						}else {
							break;
						}
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
				try {
					for(int i=1;i<9;i++) {
						if(Shogi.banmen[y/50+i-1][x/50-1]==0) {
							xy=new ArrayList<>();
							xy.add(suji);
							xy.add(dan+50*i);
							idoXY.add(xy);
							b.setColor(Color.RED);
							b.fillRect(suji,dan+50*i , 50, 50);
							b.setColor(Color.black);
							b.drawRect(suji,dan+50*i, 50, 50);
						}else if(Shogi.banmen[y/50+i-1][x/50-1]%10==0){
							xy=new ArrayList<>();
							xy.add(suji);
							xy.add(dan+50*i);
							idoXY.add(xy);
							b.setColor(Color.RED);
							b.fillRect(suji,dan+50*i , 50, 50);
							b.setColor(Color.black);
							b.drawRect(suji,dan+50*i, 50, 50);
							break;
						}else {
							break;
						}
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
				try {
					for(int i=1;i<9;i++) {
						if(Shogi.banmen[y/50-1][x/50-i-1]==0) {
							xy=new ArrayList<>();
							xy.add(suji-50*i);
							xy.add(dan);
							idoXY.add(xy);
							b.setColor(Color.RED);
							b.fillRect(suji-50*i,dan , 50, 50);
							b.setColor(Color.black);
							b.drawRect(suji-50*i,dan, 50, 50);
						}else if(Shogi.banmen[y/50-1][x/50-1-i]%10==0){
							xy=new ArrayList<>();
							xy.add(suji-50*i);
							xy.add(dan);
							idoXY.add(xy);
							b.setColor(Color.RED);
							b.fillRect(suji-50*i,dan , 50, 50);
							b.setColor(Color.black);
							b.drawRect(suji-50*i,dan, 50, 50);
							break;
						}else {
							break;
						}
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
				try {
					for(int i=1;i<9;i++) {
						if(Shogi.banmen[y/50-1][x/50-1+i]==0) {
							xy=new ArrayList<>();
							xy.add(suji+50*i);
							xy.add(dan);
							idoXY.add(xy);
							b.setColor(Color.RED);
							b.fillRect(suji+50*i,dan , 50, 50);
							b.setColor(Color.black);
							b.drawRect(suji+50*i,dan, 50, 50);
						}else if(Shogi.banmen[y/50-1][x/50-1+i]%10==0){
							xy=new ArrayList<>();
							xy.add(suji+50*i);
							xy.add(dan);
							idoXY.add(xy);
							b.setColor(Color.RED);
							b.fillRect(suji+50*i,dan , 50, 50);
							b.setColor(Color.black);
							b.drawRect(suji+50*i,dan, 50, 50);
							break;
						}else {
							break;
						}
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
				try {
					if(Shogi.banmen[y/50-1-1][x/50-1-1]%10!=1) {
						xy=new ArrayList<>();
						xy.add(suji-50);
						xy.add(dan-50);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50,dan-50 , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50,dan-50 , 50, 50);
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
				try {
					if(Shogi.banmen[y/50-1-1][x/50-1+1]%10!=1) {
						xy=new ArrayList<>();
						xy.add(suji+50);
						xy.add(dan-50);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50,dan-50 , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50,dan-50 , 50, 50);
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
				try {
					if(Shogi.banmen[y/50-1+1][x/50-1-1]%10!=1) {
						xy=new ArrayList<>();
						xy.add(suji-50);
						xy.add(dan+50);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50,dan+50 , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50,dan+50 , 50, 50);
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
				try {
					if(Shogi.banmen[y/50-1+1][x/50-1+1]%10!=1) {
						xy=new ArrayList<>();
						xy.add(suji+50);
						xy.add(dan+50);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50,dan+50 , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50,dan+50 , 50, 50);
					}
				}catch(ArrayIndexOutOfBoundsException e) {}
			}

		
		
		//馬
		else if(koma==180&&Shogi.teban==0) {
			komamoti=true;
			komaX=suji/50-1;
			komaY=dan/50-1;
			if(idoXY!=null)idoXY.clear();
			b.setColor(Color.YELLOW);
			b.fillRect(suji, dan, 50, 50);
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50-i-1][x/50-i-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji-50*i);
						xy.add(dan-50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50*i,dan-50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50*i,dan-50*i, 50, 50);
					}else if(Shogi.banmen[y/50-i-1][x/50-i-1]%10==1){
						xy=new ArrayList<>();
						xy.add(suji-50*i);
						xy.add(dan-50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50*i,dan-50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50*i,dan-50*i, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50-i-1][x/50+i-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji+50*i);
						xy.add(dan-50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50*i,dan-50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50*i,dan-50*i, 50, 50);
					}else if(Shogi.banmen[y/50-i-1][x/50+i-1]%10==1){
						xy=new ArrayList<>();
						xy.add(suji+50*i);
						xy.add(dan-50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50*i,dan-50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50*i,dan-50*i, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50+i-1][x/50+i-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji+50*i);
						xy.add(dan+50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50*i,dan+50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50*i,dan+50*i, 50, 50);
					}else if(Shogi.banmen[y/50+i-1][x/50+i-1]%10==1){
						xy=new ArrayList<>();
						xy.add(suji+50*i);
						xy.add(dan+50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50*i,dan+50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50*i,dan+50*i, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50+i-1][x/50-i-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji-50*i);
						xy.add(dan+50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50*i,dan+50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50*i,dan+50*i, 50, 50);
					}else if(Shogi.banmen[y/50+i-1][x/50-i-1]%10==1){
						xy=new ArrayList<>();
						xy.add(suji-50*i);
						xy.add(dan+50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50*i,dan+50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50*i,dan+50*i, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1-1][x/50-1]%10==1||Shogi.banmen[y/50-1-1][x/50-1]==0) {
					xy=new ArrayList<>();
					xy.add(suji);
					xy.add(dan-50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji,dan-50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji,dan-50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1][x/50-1-1]%10==1||Shogi.banmen[y/50-1][x/50-1-1]==0) {
					xy=new ArrayList<>();
					xy.add(suji-50);
					xy.add(dan);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji-50,dan , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji-50,dan , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1][x/50-1+1]%10==1||Shogi.banmen[y/50-1][x/50-1+1]==0) {
					xy=new ArrayList<>();
					xy.add(suji+50);
					xy.add(dan);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji+50,dan , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji+50,dan , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1+1][x/50-1]%10==1||Shogi.banmen[y/50-1+1][x/50-1]==0) {
					xy=new ArrayList<>();
					xy.add(suji);
					xy.add(dan+50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji,dan+50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji,dan+50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
		}
		
		else if(koma==181&&Shogi.teban==1) {
			komamoti=true;
			komaX=suji/50-1;
			komaY=dan/50-1;
			if(idoXY!=null)idoXY.clear();
			b.setColor(Color.YELLOW);
			b.fillRect(suji, dan, 50, 50);
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50-i-1][x/50-i-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji-50*i);
						xy.add(dan-50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50*i,dan-50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50*i,dan-50*i, 50, 50);
					}else if(Shogi.banmen[y/50-i-1][x/50-i-1]%10==0){
						xy=new ArrayList<>();
						xy.add(suji-50*i);
						xy.add(dan-50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50*i,dan-50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50*i,dan-50*i, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50-i-1][x/50+i-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji+50*i);
						xy.add(dan-50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50*i,dan-50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50*i,dan-50*i, 50, 50);
					}else if(Shogi.banmen[y/50-i-1][x/50+i-1]%10==0){
						xy=new ArrayList<>();
						xy.add(suji+50*i);
						xy.add(dan-50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50*i,dan-50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50*i,dan-50*i, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50+i-1][x/50+i-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji+50*i);
						xy.add(dan+50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50*i,dan+50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50*i,dan+50*i, 50, 50);
					}else if(Shogi.banmen[y/50+i-1][x/50+i-1]%10==0){
						xy=new ArrayList<>();
						xy.add(suji+50*i);
						xy.add(dan+50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji+50*i,dan+50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji+50*i,dan+50*i, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				for(int i=1;i<9;i++) {
					if(Shogi.banmen[y/50+i-1][x/50-i-1]==0) {
						xy=new ArrayList<>();
						xy.add(suji-50*i);
						xy.add(dan+50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50*i,dan+50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50*i,dan+50*i, 50, 50);
					}else if(Shogi.banmen[y/50+i-1][x/50-i-1]%10==0){
						xy=new ArrayList<>();
						xy.add(suji-50*i);
						xy.add(dan+50*i);
						idoXY.add(xy);
						b.setColor(Color.RED);
						b.fillRect(suji-50*i,dan+50*i , 50, 50);
						b.setColor(Color.black);
						b.drawRect(suji-50*i,dan+50*i, 50, 50);
						break;
					}else {
						break;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1-1][x/50-1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji);
					xy.add(dan-50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji,dan-50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji,dan-50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1][x/50-1-1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji-50);
					xy.add(dan);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji-50,dan , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji-50,dan , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1][x/50-1+1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji+50);
					xy.add(dan);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji+50,dan , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji+50,dan , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(Shogi.banmen[y/50-1+1][x/50-1]%10!=1) {
					xy=new ArrayList<>();
					xy.add(suji);
					xy.add(dan+50);
					idoXY.add(xy);
					b.setColor(Color.RED);
					b.fillRect(suji,dan+50 , 50, 50);
					b.setColor(Color.black);
					b.drawRect(suji,dan+50 , 50, 50);
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
		}
	}

	public static void komaido() {
		ArrayList<Integer> clickXY=new ArrayList<>();
		clickXY.add(x/50*50);
		clickXY.add(y/50*50);
		if(komamoti==true&&idoXY.contains(clickXY)) {
			matta=false;
			int[][] banmen=new int[9][9];
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					banmen[i][j]=Shogi.banmen[i][j];
				}
			}
			ArrayList<Integer>sentemotigoma=new ArrayList<>();
			for(int koma:ShogiGUI.sentemotigoma) {
				sentemotigoma.add(koma);
			}
			sentemotigomakihu.add(sentemotigoma);
			ArrayList<Integer>gotemotigoma=new ArrayList<>();
			for(int koma:ShogiGUI.gotemotigoma) {
				gotemotigoma.add(koma);
			}
			gotemotigomakihu.add(gotemotigoma);
			kihu.add(banmen);
			JFrame frame=new JFrame();
			if(clickXY.get(1)/50-1<3&&Shogi.banmen[komaY][komaX]<100&&Shogi.banmen[komaY][komaX]%10==1&&
					Shogi.banmen[komaY][komaX]!=51&&Shogi.banmen[komaY][komaX]!=61) {
				int option=JOptionPane.showConfirmDialog(frame, "成りますか");
				if(option==JOptionPane.YES_OPTION) {
				Shogi.banmen[komaY][komaX]+=100;
				}
			}else if(clickXY.get(1)/50-1>5&&Shogi.banmen[komaY][komaX]<100&&Shogi.banmen[komaY][komaX]%10==0&&
					Shogi.banmen[komaY][komaX]!=50&&Shogi.banmen[komaY][komaX]!=60) {
				int option=JOptionPane.showConfirmDialog(frame, "成りますか");
				if(option==JOptionPane.YES_OPTION) {
				Shogi.banmen[komaY][komaX]+=100;
				}
			}else if(Shogi.banmen[komaY][komaX]%10==1&&komaY<3&&Shogi.banmen[komaY][komaX]<100&&
					Shogi.banmen[komaY][komaX]!=51&&Shogi.banmen[komaY][komaX]!=61) {
				int option=JOptionPane.showConfirmDialog(frame, "成りますか");
				if(option==JOptionPane.YES_OPTION) {
				Shogi.banmen[komaY][komaX]+=100;
				}
			}else if(Shogi.banmen[komaY][komaX]%10==0&&komaY>5&&Shogi.banmen[komaY][komaX]<100&&
					Shogi.banmen[komaY][komaX]!=50&&Shogi.banmen[komaY][komaX]!=60) {
				int option=JOptionPane.showConfirmDialog(frame, "成りますか");
				if(option==JOptionPane.YES_OPTION) {
				Shogi.banmen[komaY][komaX]+=100;
				}
			}
			if(Shogi.banmen[clickXY.get(1)/50-1][clickXY.get(0)/50-1]==0) {
				Shogi.banmen[clickXY.get(1)/50-1][clickXY.get(0)/50-1]=Shogi.banmen[komaY][komaX];
			}else {
				if(Shogi.banmen[clickXY.get(1)/50-1][clickXY.get(0)/50-1]%10==0) {
					ShogiGUI.sentemotigoma.add(Shogi.banmen[clickXY.get(1)/50-1][clickXY.get(0)/50-1]+1);
				}else {
					ShogiGUI.gotemotigoma.add(Shogi.banmen[clickXY.get(1)/50-1][clickXY.get(0)/50-1]-1);
				}
				Shogi.banmen[clickXY.get(1)/50-1][clickXY.get(0)/50-1]=Shogi.banmen[komaY][komaX];
			}
			Shogi.banmen[komaY][komaX]=0;
			komamoti=false;
			idoXY.clear();
			x=0;
			y=0;
			tebankousin();
		}
	}

	public static void motigoma(Graphics b) {
		for(int i=0;i<sentemotigoma.size();i++) {
			if(sentemotigoma.get(i)>100) {
				sentemotigoma.set(i,sentemotigoma.get(i)-100);
			}
		}
		Font font=new Font(Font.DIALOG_INPUT,Font.PLAIN,30);
		int i=0;
		int x=620;
		int y=390;
		for(int koma:sentemotigoma) {
			b.setFont(font);
			b.setColor(Color.BLACK);
			b.drawString(Shogi.komaMap.get(koma), x+30*i, y);
			i++;
			if(i!=0&&i%5==0) {
				i=0;
				y+=40;
			}
		}
		i=0;
		y=110;
		for(int j=0;!gotemotigoma.isEmpty()&&j<gotemotigoma.size();j++) {
			if(gotemotigoma.get(j)>100) {
				gotemotigoma.set(j,gotemotigoma.get(j)-100);
			}
		}
		for(int koma:gotemotigoma) {
			b.setFont(font);
			b.setColor(Color.BLACK);
			b.drawString(Shogi.komaMap.get(koma), x+30*i, y);
			i++;
			if(i!=0&&i%5==0) {
				i=0;
				y+=40;
			}
		}
	}

	public static void utikomi(Graphics b) {
		try {
			if(x>620&&y>360&&x<780&&y<530&&!sentemotigoma.isEmpty()&&Shogi.teban==1) {
				if(komaban==(x-620)/30+(y-350)/40*5&&utikomi==true) {
					utikomi=false;
					utukoma=0;
					komaban=0;
				}else {
					utikomi=true;
					komaban=(x-620)/30+(y-350)/40*5;
					utukoma=sentemotigoma.get(komaban);
					b.setColor(Color.YELLOW);
					b.fillRect((x-20)/30*30+20, y/40*40, 32, 32);
					if(utukoma==11) {
						ArrayList<Integer> nihu=new ArrayList<>();
						for(int i=0;i<9;i++) {
							for(int j=0;j<9;j++) {
								if(Shogi.banmen[i][j]==11) {
									nihu.add(j);
								}
							}
						}
						for(int i=0;i<9;i++) {
							for(int j=0;j<9;j++) {
								if(Shogi.banmen[i][j]==0&&!nihu.contains(j)) {
									b.setColor(Color.red);
									b.fillRect(50+j*50, 50+i*50, 50, 50);
									b.setColor(Color.BLACK);
									b.drawRect(50+j*50, 50+i*50, 50, 50);
								}
							}
						}
					}
					else {
						for(int i=0;i<9;i++) {
							for(int j=0;j<9;j++) {
								if(Shogi.banmen[i][j]==0) {
									b.setColor(Color.red);
									b.fillRect(50+j*50, 50+i*50, 50, 50);
									b.setColor(Color.BLACK);
									b.drawRect(50+j*50, 50+i*50, 50, 50);;
								}
							}
						}
					}
				}
			}else if(x>620&&y>70&&x<780&&y<250&&!gotemotigoma.isEmpty()&&Shogi.teban==0) {
				if(komaban==(x-620)/30+(y-80)/40*5&&utikomi==true) {
					utikomi=false;
					utukoma=0;
					komaban=0;
				}else {
					utikomi=true;
					komaban=(x-620)/30+(y-80)/40*5;
					utukoma=gotemotigoma.get(komaban);
					b.setColor(Color.YELLOW);
					b.fillRect((x-20)/30*30+20, y/40*40, 32, 32);
					if(utukoma==10) {
						ArrayList<Integer> nihu=new ArrayList<>();
						for(int i=0;i<9;i++) {
							for(int j=0;j<9;j++) {
								if(Shogi.banmen[i][j]==10) {
									nihu.add(j);
								}
							}
						}
						for(int i=0;i<9;i++) {
							for(int j=0;j<9;j++) {
								if(Shogi.banmen[i][j]==0&&!nihu.contains(j)) {
									b.setColor(Color.red);
									b.fillRect(50+j*50, 50+i*50, 50, 50);
									b.setColor(Color.BLACK);
									b.drawRect(50+j*50, 50+i*50, 50, 50);
								}
							}
						}
					}
					else{for(int i=0;i<9;i++) {
						for(int j=0;j<9;j++) {
							if(Shogi.banmen[i][j]==0) {
								b.setColor(Color.red);
								b.fillRect(50+j*50, 50+i*50, 50, 50);
								b.setColor(Color.BLACK);
								b.drawRect(50+j*50, 50+i*50, 50, 50);
							}
						}
					}
					}
				}
			}
			else if(utikomi==true&&Shogi.banmen[y/50-1][x/50-1]==0&&utukoma%10==1&&Shogi.teban==1) {
				Shogi.banmen[y/50-1][x/50-1]=utukoma;
				int[][] banmen=new int[9][9];
				for(int i=0;i<9;i++) {
					for(int j=0;j<9;j++) {
						banmen[i][j]=Shogi.banmen[i][j];
					}
				}
				kihu.add(banmen);
				ArrayList<Integer>sentemotigoma=new ArrayList<>();
				for(int koma:ShogiGUI.sentemotigoma) {
					sentemotigoma.add(koma);
				}
				sentemotigomakihu.add(sentemotigoma);
				ArrayList<Integer>gotemotigoma=new ArrayList<>();
				for(int koma:ShogiGUI.gotemotigoma) {
					gotemotigoma.add(koma);
				}
				gotemotigomakihu.add(gotemotigoma);
				ShogiGUI.sentemotigoma.remove(komaban);
				utikomi=false;
				matta=false;
				tebankousin();
			}else if(utikomi==true&&Shogi.banmen[y/50-1][x/50-1]==0&&Shogi.teban==0) {
				Shogi.banmen[y/50-1][x/50-1]=utukoma;
				int[][] banmen=new int[9][9];
				for(int i=0;i<9;i++) {
					for(int j=0;j<9;j++) {
						banmen[i][j]=Shogi.banmen[i][j];
					}
				}
				kihu.add(banmen);
				ArrayList<Integer>sentemotigoma=new ArrayList<>();
				for(int koma:ShogiGUI.sentemotigoma) {
					sentemotigoma.add(koma);
				}
				sentemotigomakihu.add(sentemotigoma);
				ArrayList<Integer>gotemotigoma=new ArrayList<>();
				for(int koma:ShogiGUI.gotemotigoma) {
					gotemotigoma.add(koma);
				}
				gotemotigomakihu.add(gotemotigoma);
				ShogiGUI.gotemotigoma.remove(komaban);
				utikomi=false;
				matta=false;
				tebankousin();
			}
			else {
				utikomi=false;
				utukoma=0;
			}
		}catch(ArrayIndexOutOfBoundsException e) {}
	}
	public static void teban(Graphics b) {
		if(Shogi.teban==1) {
			Font font =new Font(Font.DIALOG_INPUT,Font.PLAIN,20);
			b.setFont(font);
			b.drawString("手番：先手", 780, 60);
		}else if(Shogi.teban==0) {
			Font font =new Font(Font.DIALOG_INPUT,Font.PLAIN,20);
			b.setFont(font);
			b.drawString("手番：後手", 780, 60);
		}
	}
	public static void tebankousin() {
		if(Shogi.teban==1) {
			Shogi.teban=0;
		}else if(Shogi.teban==0) {
			Shogi.teban=1;
		}
	}
	public static void toryo() {
		Shogi.toryo=Shogi.teban;
	}
}
