package shogi2;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShogiGUI2 extends JFrame{
	public static int x;
	public static int y;
	public static boolean komamoti;
	public static boolean utikomi;
	public static String utukoma;
	public static int komaX;
	public static int komaY;
	public static ArrayList<ArrayList<Integer>> idoXY=new ArrayList<>();
	public static Font font =new Font(Font.DIALOG_INPUT,Font.PLAIN,30);
	Dimension size;
	Image back;
	Graphics b;
	public static void main(String[] args) {
		mainShogi();
	}

	public static void mainShogi() {
		ShogiGUI2 s=new ShogiGUI2();
		Shogi2.shokibanmen();
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(Shogi2.banmen[i][j]!=null) {
					Shogi2.banmen[i][j].setIdohani();
				}
			}
		}
		s.shogiWnd();
	}

	public void shogiWnd() {
		JPanel panel=new JPanel();
		panel.setLayout(null);
		JButton button=new JButton("待った");
		button.setBounds(800, 50, 80, 40);
		//MyActionListener mal=new MyActionListener();
		//button.addActionListener(mal);
		panel.add(button);
		JButton button2=new JButton("投了");
		button2.setBounds(800, 100, 80, 40);
		//ToryouListener tr=new ToryouListener();
		//button2.addActionListener(tr);
		panel.add(button2);
		MyMouseListener ml=new MyMouseListener();
		panel.addMouseListener(ml);
		Container contentPane = getContentPane();
		contentPane.add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(900,600));
		setVisible(true);
	}
	public void paint(Graphics g) {
		size=getSize();
		back=createImage(size.width, size.height);
		b=back.getGraphics();
		super.paint(b);
		b.setColor(Color.white);
		b.fillRect(0, 0, 900, 600);
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				b.setColor(Color.black);
				b.drawRect(50+i*50, 75+j*50, 50, 50);
			}
		}
		b.drawRect(600, 70, 180, 180);
		b.drawRect(600, 350, 180, 180);
		b.setFont(font);
		cancel();
		ido();
		idohani(b);
		utikomi(b);
		banmenhyoji(b);
		motigomahyoji(b);
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(Shogi2.banmen[i][j]!=null) {
					Shogi2.banmen[i][j].setIdohani();
				}
			}
		}
		b.setColor(Color.black);
		b.drawString("後手  持駒", 590,60);
		b.drawString("先手  持駒", 590, 340);
		b.drawString("x:"+String.valueOf(x),500,70);
		b.drawString("y:"+String.valueOf(y),500,100);
		if(x>0&&y>0&&x<450&&y<450) {
			if(Shogi2.banmen[y/50][x/50]!=null) {
			b.drawString(Shogi2.banmen[y/50][x/50].getName(), 500, 130);
			}
		}
		g.drawImage(back, 0, 0, this);
	}

	class MyMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			ShogiGUI2.x=e.getX()-45;
			ShogiGUI2.y=e.getY()-47;
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

	public static void banmenhyoji(Graphics b) {
		b.setColor(Color.black);
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(Shogi2.banmen[i][j]!=null) {
					Koma koma=Shogi2.banmen[i][j];
					if(koma.getTurn()==1) {
						if(koma.getName().length()==1) {
							b.setFont(font);
							b.drawString(koma.getName(),60+j*50,115+i*50);
						}else if(koma.getName().length()==2) {
							Font font2=new Font(Font.DIALOG_INPUT,Font.PLAIN,20);
							b.setFont(font2);
							b.drawString(koma.getName(),55+j*50,115+i*50);
						}
					}else if(koma.getTurn()==0) {
						Graphics2D g2=(Graphics2D)b;
						AffineTransform at=g2.getTransform();
						at.setToRotation(Math.toRadians(180));
						g2.setTransform(at);
						if(koma.getName().length()==1) {
							g2.setFont(font);
							g2.drawString(koma.getName(), -(90+j*50), -(90+i*50));
						}else if(koma.getName().length()==2) {
							Font font2=new Font(Font.DIALOG_INPUT,Font.PLAIN,20);
							g2.setFont(font2);
							g2.drawString(koma.getName(), -(95+j*50), -(90+i*50));
						}
						at.setToRotation(Math.toRadians(0));
						g2.setTransform(at);
					}
				}
			}
		}
	}

	public static void motigomahyoji(Graphics b) {
		int i=0;
		int x=620;
		int y=390;
		for(String koma:Shogi2.sentemotigoma) {
			b.setFont(font);
			b.setColor(Color.BLACK);
			b.drawString(koma, x+i*30, y);
			i++;
			if(i!=0&&i%5==0) {
				i=0;
				y+=40;
			}
		}
		i=0;
		y=110;
		for(String koma:Shogi2.gotemotigoma) {
			b.setFont(font);
			b.setColor(Color.BLACK);
			b.drawString(koma, x+30*i, y);
			i++;
			if(i!=0&&i%5==0) {
				i=0;
				y+=40;
			}
		}
	}

	public static void idohani(Graphics b){
		try {
			if(Shogi2.banmen[y/50][x/50]!=null&&Shogi2.banmen[y/50][x/50].getTurn()==Shogi2.teban) {
				komamoti=true;
				komaY=y/50;
				komaX=x/50;
				Koma koma=Shogi2.banmen[y/50][x/50];
				if(koma.getIdohani().isEmpty()) {
					koma.setIdohani();
				}
				ArrayList<ArrayList<Integer>>idohani=koma.getIdohani();
				for(ArrayList<Integer> masu:idohani) {
					b.setColor(Color.red);
					b.fillRect(50+masu.get(1)*50, 75+masu.get(0)*50, 50, 50);
					b.setColor(Color.BLACK);
					b.drawRect(50+masu.get(1)*50, 75+masu.get(0)*50, 50, 50);
				}
			}
		}catch(ArrayIndexOutOfBoundsException e) {

		}
	}
	public static void ido() {
		ArrayList<Integer>clickGyoretu=new ArrayList<>();
		clickGyoretu.add(y/50);
		clickGyoretu.add(x/50);
		if(Shogi2.banmen[komaY][komaX]!=null) {
			Koma koma=Shogi2.banmen[komaY][komaX];
			if(komamoti==true&&koma.getIdohani().contains(clickGyoretu)) {
				JFrame frame=new JFrame();
				koma.ido(y/50,x/50,frame);
				Shogi2.tebankousin();
				komamoti=false;
				Shogi2.kihu();
			}
		}
	}

	public static void utikomi(Graphics b) {
		if(x>550&&y>275&&x<730&&y<455&&!Shogi2.sentemotigoma.isEmpty()&&Shogi2.teban==1&&utikomi==false) {
			utukoma=Shogi2.sentemotigoma.get((x-580)/30+(y-315)/40*5);
			utikomi=true;
			b.setColor(Color.yellow);
			b.fillRect(620+(x-580)/30*30, 360+(y-315)/40*40, 30, 30);
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					if(Shogi2.banmen[i][j]==null) {
						b.setColor(Color.red);
						b.fillRect(50+j*50,75+i*50,50,50);
						b.setColor(Color.black);
						b.drawRect(50+j*50, 75+i*50, 50, 50);
					}
				}
			}
		}else if(x>550&&y>-5&&x<730&&y<175&&!Shogi2.gotemotigoma.isEmpty()&&Shogi2.teban==0&&utikomi==false) {
			utukoma=Shogi2.gotemotigoma.get((x-580)/30+(y-35)/40*5);
			utikomi=true;
			b.setColor(Color.yellow);
			b.fillRect(620+(x-580)/30*30, 80+(y-35)/40*40, 30, 30);
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					if(Shogi2.banmen[i][j]==null) {
						b.setColor(Color.red);
						b.fillRect(50+j*50,75+i*50,50,50);
						b.setColor(Color.black);
						b.drawRect(50+j*50, 75+i*50, 50, 50);
					}
				}
			}
		}else if(utikomi==true) {
			if(Shogi2.banmen[y/50][x/50]==null) {
				Koma.utikomi(utukoma, y/50, x/50);
				utikomi=false;
				Shogi2.tebankousin();
				Shogi2.kihu();
			}
		}

	}

	public static void cancel() {
		ArrayList<Integer> clickGyoretu=new ArrayList<>();
		clickGyoretu.add(y/50);
		clickGyoretu.add(x/50);
		if(x>450||y>450||x<0||y<0||Shogi2.banmen[y/50][x/50]==null&&Shogi2.banmen[komaY][komaX]!=null&&
				!Shogi2.banmen[komaY][komaX].getIdohani().contains(clickGyoretu)) {
			komamoti=false;
			utikomi=false;
		}
	}
}
