package shogi;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JFrame;
public class ShogiGUI2 extends JFrame{
	public static int x;
	public static int y;
	public static boolean komamoti;
	public static int komaX;
	public static int komaY;
	public static ArrayList<ArrayList<Integer>> idoXY=new ArrayList<>();
	Dimension size;
	Image back;
	Graphics b;
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Shogi.komaMap();
		ShogiGUI2 s=new ShogiGUI2();
		s.addMouseListener(s.new MyMouseListener());
		//JLabel label1=new JLabel("歩");
		Shogi.shokibanmen();
		s.shogiWnd();

	}
	public void shogiWnd() {
		//JFrame frame=new JFrame("shogi");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(900,900));
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
			b.drawRect(250+i*50, 150+j*50, 50, 50);
			}
		}
		komaido();
		idohani(b);
		banmenhyoji(b);
		b.drawString("x:"+String.valueOf(x),700,70);
		b.drawString("y:"+String.valueOf(y),700,100);
		if(x>50&&y>50&&x<500&&y<500) {
			b.drawString(String.valueOf(Shogi.banmen[y/50-1][x/50-1]), 500, 130);
		}
		g.drawImage(back, 0, 0, this);
	}

	class MyMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			ShogiGUI.x=e.getX()-200;
			ShogiGUI.y=e.getY()-100;
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
		Font font=new Font(Font.DIALOG_INPUT,Font.PLAIN,30);
		b.setFont(font);
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				int masu=Shogi.banmen[i][j];
				if(masu!=0&&masu%10==1) {
					b.drawString(Shogi.komaMap.get(masu), 260+j*50, 190+i*50);
				}else if(masu!=0&&masu%10==0) {
					Graphics2D g2=(Graphics2D)b;
					g2.setFont(font);
					AffineTransform at=g2.getTransform();
					at.setToRotation(Math.toRadians(180));
					g2.setTransform(at);
					g2.drawString(Shogi.komaMap.get(masu), -(290+j*50), -(160+i*50));
					at.setToRotation(Math.toRadians(0));
					g2.setTransform(at);
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
		if((suji==0||dan==0||suji>500||dan>500||koma==0)&&!idoXY.contains(xy)) {
			komamoti=false;
			if(idoXY!=null)idoXY.clear();
		}else if(koma==11) {
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
		}else if(koma==21) {
			komamoti=true;
			komaX=suji/50-1;
			komaY=dan/50-1;
			if(idoXY!=null)idoXY.clear();
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
		}
	}
	public static void komaido() {
		ArrayList<Integer> cXY=new ArrayList<>();
		cXY.add(x/50*50);
		cXY.add(y/50*50);
		if(komamoti==true&&idoXY.contains(cXY)) {
			Shogi.banmen[cXY.get(1)/50-1][cXY.get(0)/50-1]=Shogi.banmen[komaY][komaX];
			Shogi.banmen[komaY][komaX]=0;
			
		}
	}
}
