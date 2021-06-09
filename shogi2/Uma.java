package shogi2;

import java.util.ArrayList;

public class Uma extends Koma{
	public Uma() {

	}
	public Uma(int gyo,int retu) {
		setName("馬");
		setTurn(Shogi2.teban);
		setZahyo(gyo, retu);
		setIdohani();
		setNari(false);
	}
	@Override
	public void setIdohani() {
		ArrayList<ArrayList<Integer>>idohani=new ArrayList<>();
		for(int i=1;i<9;i++) {
			if(zahyo.get(0)-i<0||zahyo.get(1)-i<0||(Shogi2.banmen[zahyo.get(0)-i][zahyo.get(1)-i]!=null&&
					Shogi2.banmen[zahyo.get(0)-i][zahyo.get(1)-i].getTurn()==turn)) {
				break;
			}else if(Shogi2.banmen[zahyo.get(0)-i][zahyo.get(1)-i]!=null&&
					Shogi2.banmen[zahyo.get(0)-i][zahyo.get(1)-i].getTurn()!=turn) {
				ido=new ArrayList<>();
				ido.add(zahyo.get(0)-i);
				ido.add(zahyo.get(1)-i);
				idohani.add(ido);
				break;
			}else {
				ido=new ArrayList<>();
				ido.add(zahyo.get(0)-i);
				ido.add(zahyo.get(1)-i);
				idohani.add(ido);
			}
		}
		for(int i=1;i<9;i++) {
			if(zahyo.get(0)+i>=9||zahyo.get(1)+i>=9||(Shogi2.banmen[zahyo.get(0)+i][zahyo.get(1)+i]!=null&&
					Shogi2.banmen[zahyo.get(0)+i][zahyo.get(1)+i].getTurn()==turn)) {
				break;
			}else if(Shogi2.banmen[zahyo.get(0)+i][zahyo.get(1)+i]!=null&&
					Shogi2.banmen[zahyo.get(0)+i][zahyo.get(1)+i].getTurn()!=turn) {
				ido=new ArrayList<>();
				ido.add(zahyo.get(0)+i);
				ido.add(zahyo.get(1)+i);
				idohani.add(ido);
				break;
			}else {
				ido=new ArrayList<>();
				ido.add(zahyo.get(0)+i);
				ido.add(zahyo.get(1)+i);
				idohani.add(ido);
			}
		}
		for(int i=1;i<9;i++) {
			if(zahyo.get(1)+i>=9||zahyo.get(0)-i<0||(Shogi2.banmen[zahyo.get(0)-i][zahyo.get(1)+i]!=null&&
					Shogi2.banmen[zahyo.get(0)-i][zahyo.get(1)+i].getTurn()==turn)) {
				break;
			}else if(Shogi2.banmen[zahyo.get(0)-i][zahyo.get(1)+i]!=null&&
					Shogi2.banmen[zahyo.get(0)-i][zahyo.get(1)+i].getTurn()!=turn) {
				ido=new ArrayList<>();
				ido.add(zahyo.get(0)-i);
				ido.add(zahyo.get(1)+i);
				idohani.add(ido);
				break;
			}else {
				ido=new ArrayList<>();
				ido.add(zahyo.get(0)-i);
				ido.add(zahyo.get(1)+i);
				idohani.add(ido);
			}
		}
		for(int i=1;i<9;i++) {
			if(zahyo.get(1)-i<0||zahyo.get(0)+i>=9||(Shogi2.banmen[zahyo.get(0)+i][zahyo.get(1)-i]!=null&&
					Shogi2.banmen[zahyo.get(0)+i][zahyo.get(1)-i].getTurn()==turn)) {
				break;
			}else if(Shogi2.banmen[zahyo.get(0)+i][zahyo.get(1)-i]!=null&&
					Shogi2.banmen[zahyo.get(0)+i][zahyo.get(1)-i].getTurn()!=turn) {
				ido=new ArrayList<>();
				ido.add(zahyo.get(0)+i);
				ido.add(zahyo.get(1)-i);
				idohani.add(ido);
				break;
			}else {
				ido=new ArrayList<>();
				ido.add(zahyo.get(0)+i);
				ido.add(zahyo.get(1)-i);
				idohani.add(ido);
			}
		}
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
