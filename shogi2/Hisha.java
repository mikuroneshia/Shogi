package shogi2;

import java.util.ArrayList;

public class Hisha extends Koma{
	public Hisha() {

	}
	public Hisha(int gyo,int retu) {
		setName("飛");
		setTurn(Shogi2.teban);
		setZahyo(gyo,retu);
		setIdohani();
		setNari(true);
	}
	public Hisha(int gyo,int retu,int turn) {
		setName("飛");
		setTurn(turn);
		setZahyo(gyo,retu);
		setIdohani();
		setNari(true);
	}
	@Override
	public void setIdohani() {
		ArrayList<ArrayList<Integer>>idohani=new ArrayList<>();
			for(int i=1;i<9;i++) {
				if(zahyo.get(0)-i<0||(Shogi2.banmen[zahyo.get(0)-i][zahyo.get(1)]!=null&&
						Shogi2.banmen[zahyo.get(0)-i][zahyo.get(1)].getTurn()==turn)) {
					break;
				}else if(Shogi2.banmen[zahyo.get(0)-i][zahyo.get(1)]!=null&&
						Shogi2.banmen[zahyo.get(0)-i][zahyo.get(1)].getTurn()!=turn) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0)-i);
					ido.add(zahyo.get(1));
					idohani.add(ido);
					break;
				}else {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0)-i);
					ido.add(zahyo.get(1));
					idohani.add(ido);
				}
			}
			for(int i=1;i<9;i++) {
				if(zahyo.get(0)+i>=9||(Shogi2.banmen[zahyo.get(0)+i][zahyo.get(1)]!=null&&
						Shogi2.banmen[zahyo.get(0)+i][zahyo.get(1)].getTurn()==turn)) {
					break;
				}else if(Shogi2.banmen[zahyo.get(0)+i][zahyo.get(1)]!=null&&
						Shogi2.banmen[zahyo.get(0)+i][zahyo.get(1)].getTurn()!=turn) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0)+i);
					ido.add(zahyo.get(1));
					idohani.add(ido);
					break;
				}else {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0)+i);
					ido.add(zahyo.get(1));
					idohani.add(ido);
				}
			}
			for(int i=1;i<9;i++) {
				if(zahyo.get(1)+i>=9||(Shogi2.banmen[zahyo.get(0)][zahyo.get(1)+i]!=null&&
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)+i].getTurn()==turn)) {
					break;
				}else if(Shogi2.banmen[zahyo.get(0)][zahyo.get(1)+i]!=null&&
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)+i].getTurn()!=turn) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0));
					ido.add(zahyo.get(1)+i);
					idohani.add(ido);
					break;
				}else {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0));
					ido.add(zahyo.get(1)+i);
					idohani.add(ido);
				}
			}
			for(int i=1;i<9;i++) {
				if(zahyo.get(1)-i<0||(Shogi2.banmen[zahyo.get(0)][zahyo.get(1)-i]!=null&&
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)-i].getTurn()==turn)) {
					break;
				}else if(Shogi2.banmen[zahyo.get(0)][zahyo.get(1)-i]!=null&&
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)-i].getTurn()!=turn) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0));
					ido.add(zahyo.get(1)-i);
					idohani.add(ido);
					break;
				}else {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0));
					ido.add(zahyo.get(1)-i);
					idohani.add(ido);
				}
			}
		this.idohani=idohani;
	}
}
