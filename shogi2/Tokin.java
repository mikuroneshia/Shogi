package shogi2;

import java.util.ArrayList;

public class Tokin extends Koma{
	String name="と";
	boolean nari=false;
	public Tokin() {

	}
	public Tokin(int gyo,int retu) {
		setName("と");
		setTurn(Shogi2.teban);
		setZahyo(gyo,retu);
		setIdohani();
	}
	@Override
	public void setName(String name) {
		this.name=name;
	}

	@Override
	public void setTurn(int turn) {
		this.turn=turn;
	}

	@Override
	public void setZahyo(int gyo, int retu) {
		zahyo.add(gyo);
		zahyo.add(retu);
	}

	@Override
	public void setIdohani() {
		if(turn==1) {
			if(zahyo.get(0)-1<9&&zahyo.get(0)-1>=0&&zahyo.get(1)>=0&&zahyo.get(1)<9) {
				if(Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)]==null||
						Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)].getTurn()==0) {
					ido.add(zahyo.get(0)-1);
					ido.add(zahyo.get(1));
					idohani.add(ido);
				}
			}
			if(zahyo.get(0)-1<9&&zahyo.get(0)-1>=0&&zahyo.get(1)-1>=0&&zahyo.get(1)-1<9) {
				if(Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)-1]==null||
						Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)-1].getTurn()==0) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0)-1);
					ido.add(zahyo.get(1)-1);
					idohani.add(ido);
					}
			}
			if(zahyo.get(0)-1<9&&zahyo.get(0)-1>=0&&zahyo.get(1)+1>=0&&zahyo.get(1)+1<9) {
				if(Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)+1]==null||
						Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)+1].getTurn()==0) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0)-1);
					ido.add(zahyo.get(1)+1);
					idohani.add(ido);
				}
			}
			if(zahyo.get(0)<9&&zahyo.get(0)>=0&&zahyo.get(1)-1>=0&&zahyo.get(1)-1<9) {
				if(Shogi2.banmen[zahyo.get(0)][zahyo.get(1)-1]==null||
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)-1].getTurn()==0) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0));
					ido.add(zahyo.get(1)-1);
					idohani.add(ido);
				}
			}
			if(zahyo.get(0)<9&&zahyo.get(0)>=0&&zahyo.get(1)+1>=0&&zahyo.get(1)+1<9) {
				if(Shogi2.banmen[zahyo.get(0)][zahyo.get(1)+1]==null||
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)+1].getTurn()==0) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0));
					ido.add(zahyo.get(1)+1);
					idohani.add(ido);
				}
			}
			if(zahyo.get(0)+1<9&&zahyo.get(0)+1>=0&&zahyo.get(1)>=0&&zahyo.get(1)<9) {
				if(Shogi2.banmen[zahyo.get(0)+1][zahyo.get(1)]==null||
						Shogi2.banmen[zahyo.get(0)+1][zahyo.get(1)].getTurn()==0) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0)+1);
					ido.add(zahyo.get(1));
					idohani.add(ido);

				}
			}
		}else if(turn==0) {
			if(zahyo.get(0)+1<9&&zahyo.get(0)+1>=1&&zahyo.get(1)>=0&&zahyo.get(1)<9) {
				if(Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)]==null||
						Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)].getTurn()==1) {
					ido.add(zahyo.get(0)+1);
					ido.add(zahyo.get(1));
					idohani.add(ido);

				}
			}
		}
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getTurn() {
		return turn;
	}

	@Override
	public ArrayList<ArrayList<Integer>> getIdohani() {
		return idohani;
	}
}
