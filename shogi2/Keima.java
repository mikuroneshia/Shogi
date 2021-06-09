package shogi2;

import java.util.ArrayList;

public class Keima extends Koma{
	public Keima(){

	}
	public Keima(int gyo,int retu) {
		setName("桂");
		setTurn(Shogi2.teban);
		setZahyo(gyo, retu);
		setIdohani();
		setNari(true);
	}
	public Keima(int gyo,int retu,int turn) {
		setName("桂");
		setTurn(turn);
		setZahyo(gyo, retu);
		setIdohani();
		setNari(true);
	}
	@Override
	public void setIdohani() {
		ArrayList<ArrayList<Integer>>idohani=new ArrayList<>();
		if(turn==1) {
			if(zahyo.get(0)-2<9&&zahyo.get(0)-2>=0&&zahyo.get(1)-1>=0&&zahyo.get(1)-1<9) {
				if(Shogi2.banmen[zahyo.get(0)-2][zahyo.get(1)-1]==null||
						Shogi2.banmen[zahyo.get(0)-2][zahyo.get(1)-1].getTurn()==0) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0)-2);
					ido.add(zahyo.get(1)-1);
					idohani.add(ido);
				}
			}
			if(zahyo.get(0)-2<9&&zahyo.get(0)-2>=0&&zahyo.get(1)+1>=0&&zahyo.get(1)+1<9) {
				if(Shogi2.banmen[zahyo.get(0)-2][zahyo.get(1)+1]==null||
						Shogi2.banmen[zahyo.get(0)-2][zahyo.get(1)+1].getTurn()==0) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0)-2);
					ido.add(zahyo.get(1)+1);
					idohani.add(ido);
				}
			}
		}else if(turn==0) {
			if(zahyo.get(0)+2<9&&zahyo.get(0)+2>=1&&zahyo.get(1)-1>=0&&zahyo.get(1)-1<9) {
				if(Shogi2.banmen[zahyo.get(0)+2][zahyo.get(1)-1]==null||
						Shogi2.banmen[zahyo.get(0)+2][zahyo.get(1)-1].getTurn()==1) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0)+2);
					ido.add(zahyo.get(1)-1);
					idohani.add(ido);
				}
			}
			if(zahyo.get(0)+2<9&&zahyo.get(0)+2>=1&&zahyo.get(1)+1>=0&&zahyo.get(1)+1<9) {
				if(Shogi2.banmen[zahyo.get(0)+2][zahyo.get(1)+1]==null||
						Shogi2.banmen[zahyo.get(0)+2][zahyo.get(1)+1].getTurn()==1) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0)+2);
					ido.add(zahyo.get(1)+1);
					idohani.add(ido);
				}
			}
		}
		this.idohani=idohani;
	}
}
