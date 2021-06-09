package shogi2;

import java.util.ArrayList;

public class Gyoku extends Koma{
	public Gyoku() {

	}
	public Gyoku(int gyo,int retu) {
		setName("玉");
		setTurn(Shogi2.teban);
		setZahyo(gyo, retu);
		setIdohani();
		setNari(false);
	}
	public Gyoku(int gyo,int retu,int turn) {
		setName("玉");
		setTurn(turn);
		setZahyo(gyo, retu);
		setIdohani();
		setNari(false);
	}
	@Override
	public void setIdohani() {
		ArrayList<ArrayList<Integer>>idohani=new ArrayList<>();
		if(zahyo.get(0)-1<9&&zahyo.get(0)-1>=0&&zahyo.get(1)>=0&&zahyo.get(1)<9) {
			if(Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)]==null||
					Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)].getTurn()!=turn) {
				ido.add(zahyo.get(0)-1);
				ido.add(zahyo.get(1));
				idohani.add(ido);
			}
		}
		if(zahyo.get(0)-1<9&&zahyo.get(0)-1>=0&&zahyo.get(1)-1>=0&&zahyo.get(1)-1<9) {
			if(Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)-1]==null||
					Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)-1].getTurn()!=turn) {
				ido=new ArrayList<>();
				ido.add(zahyo.get(0)-1);
				ido.add(zahyo.get(1)-1);
				idohani.add(ido);
				}
		}
		if(zahyo.get(0)-1<9&&zahyo.get(0)-1>=0&&zahyo.get(1)+1>=0&&zahyo.get(1)+1<9) {
			if(Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)+1]==null||
					Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)+1].getTurn()!=turn) {
				ido=new ArrayList<>();
				ido.add(zahyo.get(0)-1);
				ido.add(zahyo.get(1)+1);
				idohani.add(ido);
			}
		}
		if(zahyo.get(0)<9&&zahyo.get(0)>=0&&zahyo.get(1)-1>=0&&zahyo.get(1)-1<9) {
			if(Shogi2.banmen[zahyo.get(0)][zahyo.get(1)-1]==null||
					Shogi2.banmen[zahyo.get(0)][zahyo.get(1)-1].getTurn()!=turn) {
				ido=new ArrayList<>();
				ido.add(zahyo.get(0));
				ido.add(zahyo.get(1)-1);
				idohani.add(ido);
			}
		}
		if(zahyo.get(0)<9&&zahyo.get(0)>=0&&zahyo.get(1)+1>=0&&zahyo.get(1)+1<9) {
			if(Shogi2.banmen[zahyo.get(0)][zahyo.get(1)+1]==null||
					Shogi2.banmen[zahyo.get(0)][zahyo.get(1)+1].getTurn()!=turn) {
				ido=new ArrayList<>();
				ido.add(zahyo.get(0));
				ido.add(zahyo.get(1)+1);
				idohani.add(ido);
			}
		}
		if(zahyo.get(0)+1<9&&zahyo.get(0)+1>=0&&zahyo.get(1)>=0&&zahyo.get(1)<9) {
			if(Shogi2.banmen[zahyo.get(0)+1][zahyo.get(1)]==null||
					Shogi2.banmen[zahyo.get(0)+1][zahyo.get(1)].getTurn()!=turn) {
				ido=new ArrayList<>();
				ido.add(zahyo.get(0)+1);
				ido.add(zahyo.get(1));
				idohani.add(ido);

			}
		}
		if(zahyo.get(0)+1<9&&zahyo.get(0)+1>=0&&zahyo.get(1)+1>=0&&zahyo.get(1)+1<9) {
			if(Shogi2.banmen[zahyo.get(0)+1][zahyo.get(1)+1]==null||
					Shogi2.banmen[zahyo.get(0)+1][zahyo.get(1)+1].getTurn()!=turn) {
				ido=new ArrayList<>();
				ido.add(zahyo.get(0)+1);
				ido.add(zahyo.get(1)+1);
				idohani.add(ido);

			}
		}
		if(zahyo.get(0)+1<9&&zahyo.get(0)+1>=0&&zahyo.get(1)-1>=0&&zahyo.get(1)-1<9) {
			if(Shogi2.banmen[zahyo.get(0)+1][zahyo.get(1)-1]==null||
					Shogi2.banmen[zahyo.get(0)+1][zahyo.get(1)-1].getTurn()!=turn) {
				ido=new ArrayList<>();
				ido.add(zahyo.get(0)+1);
				ido.add(zahyo.get(1)-1);
				idohani.add(ido);

			}
		}
		this.idohani=idohani;
	}
}
