package shogi2;

import java.util.ArrayList;

public class Kin extends Koma{
	boolean nari=false;
	public Kin() {

	}
	public Kin(int gyo,int retu) {
		setName("金");
		setTurn(Shogi2.teban);
		setZahyo(gyo,retu);
		setIdohani();
	}

	public Kin(int gyo,int retu,int turn) {
		setName("金");
		setTurn(turn);
		setZahyo(gyo,retu);
		setIdohani();
	}
	public Kin(String name,int gyo,int retu) {
		setName(name);
		setTurn(Shogi2.teban);
		setZahyo(gyo,retu);
		setIdohani();
	}

	@Override
	public void setIdohani() {
		ArrayList<ArrayList<Integer>>idohani=new ArrayList<>();
		if(turn==1) {
			if(zahyo.get(0)-1<9&&zahyo.get(0)-1>=0&&zahyo.get(1)>=0&&zahyo.get(1)<9) {
				if(Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)]==null||
						Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)].getTurn()==0) {
					ido=new ArrayList<Integer>();
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
				if(Shogi2.banmen[zahyo.get(0)+1][zahyo.get(1)]==null||
						Shogi2.banmen[zahyo.get(0)+1][zahyo.get(1)].getTurn()==1) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0)+1);
					ido.add(zahyo.get(1));
					idohani.add(ido);

				}
			}
			if(zahyo.get(0)+1<9&&zahyo.get(0)+1>=0&&zahyo.get(1)-1>=0&&zahyo.get(1)-1<9) {
				if(Shogi2.banmen[zahyo.get(0)+1][zahyo.get(1)-1]==null||
						Shogi2.banmen[zahyo.get(0)+1][zahyo.get(1)-1].getTurn()==1) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0)+1);
					ido.add(zahyo.get(1)-1);
					idohani.add(ido);
					}
			}
			if(zahyo.get(0)+1<9&&zahyo.get(0)+1>=0&&zahyo.get(1)+1>=0&&zahyo.get(1)+1<9) {
				if(Shogi2.banmen[zahyo.get(0)+1][zahyo.get(1)+1]==null||
						Shogi2.banmen[zahyo.get(0)+1][zahyo.get(1)+1].getTurn()==1) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0)+1);
					ido.add(zahyo.get(1)+1);
					idohani.add(ido);
				}
			}
			if(zahyo.get(0)<9&&zahyo.get(0)>=0&&zahyo.get(1)-1>=0&&zahyo.get(1)-1<9) {
				if(Shogi2.banmen[zahyo.get(0)][zahyo.get(1)-1]==null||
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)-1].getTurn()==1) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0));
					ido.add(zahyo.get(1)-1);
					idohani.add(ido);
				}
			}
			if(zahyo.get(0)<9&&zahyo.get(0)>=0&&zahyo.get(1)+1>=0&&zahyo.get(1)+1<9) {
				if(Shogi2.banmen[zahyo.get(0)][zahyo.get(1)+1]==null||
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)+1].getTurn()==1) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0));
					ido.add(zahyo.get(1)+1);
					idohani.add(ido);
				}
			}
			if(zahyo.get(0)-1<9&&zahyo.get(0)-1>=0&&zahyo.get(1)>=0&&zahyo.get(1)<9) {
				if(Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)]==null||
						Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)].getTurn()==1) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0)-1);
					ido.add(zahyo.get(1));
					idohani.add(ido);

				}
			}
		}
		this.idohani=idohani;
	}
}
