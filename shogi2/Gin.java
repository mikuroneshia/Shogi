package shogi2;

import java.util.ArrayList;

public class Gin extends Koma{
	public Gin() {

	}
	public Gin(int gyo,int retu) {
		setName("銀");
		setTurn(Shogi2.teban);
		setZahyo(gyo, retu);
		setIdohani();
		setNari(true);
	}
	public Gin(int gyo,int retu,int turn) {
		setName("銀");
		setTurn(turn);
		setZahyo(gyo, retu);
		setIdohani();
		setNari(true);
	}
	@Override
	public void setIdohani() {
		ArrayList<ArrayList<Integer>>idohani=new ArrayList<>();
		if(turn==1) {
			if(zahyo.get(0)-1<9&&zahyo.get(0)-1>=0&&zahyo.get(1)>=0&&zahyo.get(1)<9) {
				if(Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)]==null||
						Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)].getTurn()!=turn) {
					ido=new ArrayList<Integer>();
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
		}else if(turn==0) {
			if(zahyo.get(0)+1<9&&zahyo.get(0)+1>=0&&zahyo.get(1)>=0&&zahyo.get(1)<9) {
				if(Shogi2.banmen[zahyo.get(0)+1][zahyo.get(1)]==null||
						Shogi2.banmen[zahyo.get(0)+1][zahyo.get(1)].getTurn()!=turn) {
					ido=new ArrayList<Integer>();
					ido.add(zahyo.get(0)+1);
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
		}
		this.idohani=idohani;

	}

	//@Override
	/*public void ido(int gyo, int retu, JFrame frame) {
		if(Shogi2.banmen[gyo][retu]==null) {
			if(gyo<3&&getNari()&&getTurn()==1) {
				int option=new JOptionPane().showConfirmDialog(frame,"成りますか");
				if(option==JOptionPane.YES_OPTION) {
					Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
					Kin tokin=new Kin("成銀",gyo,retu);
					Shogi2.banmen[gyo][retu]=tokin;
				}else {
					Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
					Gin gin=new Gin(gyo,retu);
					Shogi2.banmen[gyo][retu]=gin;
				}
			}else if(gyo>5&&getNari()&&getTurn()==0) {
				int option=new JOptionPane().showConfirmDialog(frame,"成りますか");
				if(option==JOptionPane.YES_OPTION) {
					Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
					Kin tokin=new Kin("成銀",gyo,retu);
					Shogi2.banmen[gyo][retu]=tokin;
				}else {
					Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
					Gin gin=new Gin(gyo,retu);
					Shogi2.banmen[gyo][retu]=gin;
				}
			}
			else {
				Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
				Gin gin=new Gin(gyo,retu);
				Shogi2.banmen[gyo][retu]=gin;

			}
		}else if(Shogi2.banmen[gyo][retu].getTurn()!=turn) {
			if(Shogi2.banmen[gyo][retu].getTurn()==0) {
				Shogi2.banmen[gyo][retu].addMotigoma();
				if(gyo<3&&getNari()) {
					int option=new JOptionPane().showConfirmDialog(frame,"成りますか");
					if(option==JOptionPane.YES_OPTION) {
					Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
					Kin tokin=new Kin("成銀",gyo,retu);
					Shogi2.banmen[gyo][retu]=tokin;
					}else {
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
						Gin gin=new Gin(gyo,retu);
						Shogi2.banmen[gyo][retu]=gin;
					}
				}else {
					Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
					Gin gin=new Gin(gyo,retu);
					Shogi2.banmen[gyo][retu]=gin;
				}
			}else if(Shogi2.banmen[gyo][retu].getTurn()==1) {
				Shogi2.banmen[gyo][retu].addMotigoma();
				if(gyo>5&&getNari()) {
					int option=new JOptionPane().showConfirmDialog(frame,"成りますか");
					if(option==JOptionPane.YES_OPTION) {
					Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
					Kin tokin=new Kin("成銀",gyo,retu);
					Shogi2.banmen[gyo][retu]=tokin;
					}else {
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
						Gin gin=new Gin(gyo,retu);
						Shogi2.banmen[gyo][retu]=gin;
					}
				}else {
					Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
					Gin gin=new Gin(gyo,retu);
					Shogi2.banmen[gyo][retu]=gin;
				}
			}
		}
	}*/

}
