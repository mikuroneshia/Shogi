package shogi2;

import java.util.ArrayList;

public class Hu extends Koma {
	public Hu() {

	}
	public Hu(int gyo,int retu) {
		setName("歩");
		setTurn(Shogi2.teban);
		setZahyo(gyo, retu);
		setIdohani();
		setNari(true);
	}
	public Hu(int gyo,int retu,int turn) {
		setName("歩");
		setTurn(turn);
		setZahyo(gyo, retu);
		setIdohani();
		setNari(true);
	}
	@Override
	public void setIdohani() {
		ArrayList<ArrayList<Integer>>idohani=new ArrayList<>();
		if(turn==1) {
			if(zahyo.get(0)-1<8&&zahyo.get(0)-1>=0&&zahyo.get(1)>=0&&zahyo.get(1)<9) {
				if(Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)]==null||
						Shogi2.banmen[zahyo.get(0)-1][zahyo.get(1)].getTurn()==0) {
					ido=new ArrayList<>();
					ido.add(zahyo.get(0)-1);
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
		}
		this.idohani=idohani;
	}
	/*public void ido(int gyo,int retu,JFrame frame) {
			if(Shogi2.banmen[gyo][retu]==null) {
				if(gyo<3&&getNari()&&getTurn()==1) {
					int option=new JOptionPane().showConfirmDialog(frame,"成りますか");
					if(option==JOptionPane.YES_OPTION) {
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
						Kin tokin=new Kin("と",gyo,retu);
						Shogi2.banmen[gyo][retu]=tokin;
					}else {
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
						Hu hu=new Hu(gyo,retu);
						Shogi2.banmen[gyo][retu]=hu;
					}
				}else if(gyo>5&&getNari()&&getTurn()==0) {
					int option=new JOptionPane().showConfirmDialog(frame,"成りますか");
					if(option==JOptionPane.YES_OPTION) {
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
						Kin tokin=new Kin("と",gyo,retu);
						Shogi2.banmen[gyo][retu]=tokin;
					}else {
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
						Hu hu=new Hu(gyo,retu);
						Shogi2.banmen[gyo][retu]=hu;
					}
				}
				else {
					Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
					Hu hu=new Hu(gyo,retu);
					Shogi2.banmen[gyo][retu]=hu;

				}
			}else if(Shogi2.banmen[gyo][retu].getTurn()!=turn) {
				if(Shogi2.banmen[gyo][retu].getTurn()==0) {
					Shogi2.banmen[gyo][retu].addMotigoma();
					if(gyo<3&&getNari()) {
						int option=new JOptionPane().showConfirmDialog(frame,"成りますか");
						if(option==JOptionPane.YES_OPTION) {
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
						Kin tokin=new Kin("と",gyo,retu);
						Shogi2.banmen[gyo][retu]=tokin;
						}else {
							Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
							Hu hu=new Hu(gyo,retu);
							Shogi2.banmen[gyo][retu]=hu;
						}
					}else {
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
						Hu hu=new Hu(gyo,retu);
						Shogi2.banmen[gyo][retu]=hu;
					}
				}else if(Shogi2.banmen[gyo][retu].getTurn()==1) {
					Shogi2.banmen[gyo][retu].addMotigoma();
					if(gyo>5&&getNari()) {
						int option=new JOptionPane().showConfirmDialog(frame,"成りますか");
						if(option==JOptionPane.YES_OPTION) {
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
						Kin tokin=new Kin("と",gyo,retu);
						Shogi2.banmen[gyo][retu]=tokin;
						}else {
							Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
							Hu hu=new Hu(gyo,retu);
							Shogi2.banmen[gyo][retu]=hu;
						}
					}else {
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
						Hu hu=new Hu(gyo,retu);
						Shogi2.banmen[gyo][retu]=hu;
					}
				}
			}
	}*/
}
