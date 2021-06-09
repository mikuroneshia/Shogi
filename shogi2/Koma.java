package shogi2;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
public abstract class Koma{
	String name;
	ArrayList<Integer> ido=new ArrayList<>();
	int turn;
	ArrayList<Integer> zahyo=new ArrayList<>();
	ArrayList<ArrayList<Integer>> idohani=new ArrayList<>();
	public boolean nari;
	public void setName(String name) {
		this.name=name;
	}
	public void setTurn(int turn) {
		this.turn=turn;
	}
	public void setZahyo(int gyo,int retu) {
		zahyo.add(gyo);
		zahyo.add(retu);
	}
	public void setNari(boolean nari) {
		this.nari=nari;
	}
	public abstract void setIdohani();
	public String getName() {
		return name;
	}
	public int getTurn() {
		return turn;
	}
	public ArrayList<Integer> getZahyo(){
		return zahyo;
	}
	public  ArrayList<ArrayList<Integer>> getIdohani(){
		return idohani;
	}
	public void ido(int gyo,int retu,JFrame frame) {
		Koma koma=null;
		Koma nari=null;
		if(getName()=="歩") {
			koma=new Hu(gyo,retu);
			nari=new Kin("と",gyo,retu);
		}else if(getName()=="香") {
			koma=new Kyosha(gyo,retu);
			nari=new Kin("成香",gyo,retu);
		}else if(getName()=="桂") {
			koma=new Keima(gyo,retu);
			nari=new Kin("成桂",gyo,retu);
		}else if(getName()=="銀") {
			koma=new Gin(gyo,retu);
			nari=new Kin("成銀",gyo,retu);
		}else if(getName()=="金") {
			koma=new Kin(gyo,retu);
		}else if(getName()=="玉") {
			koma=new Gyoku(gyo,retu);
		}else if(getName()=="飛") {
			koma=new Hisha(gyo,retu);
			nari=new Ryu(gyo,retu);
		}else if(getName()=="角") {
			koma=new Kaku(gyo,retu);
			nari=new Uma(gyo,retu);
		}else if(getName()=="と") {
			koma=new Kin("と",gyo,retu);
		}else if(getName()=="成香") {
			koma=new Kin("成香",gyo,retu);
		}else if(getName()=="成桂") {
			koma=new Kin("成桂",gyo,retu);
		}else if(getName()=="成銀") {
			koma=new Kin("成銀",gyo,retu);
		}else if(getName()=="龍") {
			koma=new Ryu(gyo,retu);
		}else if(getName()=="馬") {
			koma=new Uma(gyo,retu);
		}
		if(getNari()) {
			if(Shogi2.banmen[gyo][retu]==null) {
				if(gyo<3&&getNari()&&getTurn()==1) {
					int option=new JOptionPane().showConfirmDialog(frame,"成りますか");
					if(option==JOptionPane.YES_OPTION) {
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
						Shogi2.banmen[gyo][retu]=nari;
					}else {
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
						Shogi2.banmen[gyo][retu]=koma;
					}
				}else if(gyo>5&&getNari()&&getTurn()==0) {
					int option=new JOptionPane().showConfirmDialog(frame,"成りますか");
					if(option==JOptionPane.YES_OPTION) {
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
						Shogi2.banmen[gyo][retu]=nari;
					}else {
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
						Shogi2.banmen[gyo][retu]=koma;
					}
				}
				else {
					Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
					Shogi2.banmen[gyo][retu]=koma;

				}
			}else if(Shogi2.banmen[gyo][retu].getTurn()!=turn) {
				if(Shogi2.banmen[gyo][retu].getTurn()==0) {
					Shogi2.banmen[gyo][retu].addMotigoma();
					if(gyo<3&&getNari()) {
						int option=new JOptionPane().showConfirmDialog(frame,"成りますか");
						if(option==JOptionPane.YES_OPTION) {
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
						Shogi2.banmen[gyo][retu]=nari;
						}else {
							Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
							Shogi2.banmen[gyo][retu]=koma;
						}
					}else {
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
						Shogi2.banmen[gyo][retu]=koma;
					}
				}else if(Shogi2.banmen[gyo][retu].getTurn()==1) {
					Shogi2.banmen[gyo][retu].addMotigoma();
					if(gyo>5&&getNari()) {
						int option=new JOptionPane().showConfirmDialog(frame,"成りますか");
						if(option==JOptionPane.YES_OPTION) {
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
						Shogi2.banmen[gyo][retu]=nari;
						}else {
							Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
							Shogi2.banmen[gyo][retu]=koma;
						}
					}else {
						Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
						Shogi2.banmen[gyo][retu]=koma;
					}
				}
			}

		}else {
			if(Shogi2.banmen[gyo][retu]==null) {
				Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
				Shogi2.banmen[gyo][retu]=koma;
			}else if(Shogi2.banmen[gyo][retu].getTurn()!=this.turn) {
				if(Shogi2.banmen[gyo][retu].getTurn()==0) {
					Shogi2.banmen[gyo][retu].addMotigoma();
					Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
					Shogi2.banmen[gyo][retu]=koma;
				}else if(Shogi2.banmen[gyo][retu].getTurn()==1) {
					Shogi2.banmen[gyo][retu].addMotigoma();
					Shogi2.banmen[zahyo.get(0)][zahyo.get(1)]=null;
					Shogi2.banmen[gyo][retu]=koma;
				}
			}
		}
	}

	public static void utikomi(String koma,int gyo,int retu) {
		Koma utukoma=null;
		if(koma=="歩") {
			utukoma=new Hu(gyo,retu);
		}else if(koma=="香") {
			utukoma=new Kyosha(gyo,retu);
		}else if(koma=="桂") {
			utukoma=new Keima(gyo,retu);
		}else if(koma=="銀") {
			utukoma=new Gin(gyo,retu);
		}else if(koma=="金") {
			utukoma=new Kin(gyo,retu);
		}else if(koma=="角") {
			utukoma=new Kaku(gyo,retu);
		}else if(koma=="飛") {
			utukoma=new Hisha(gyo,retu);
		}
		Shogi2.banmen[gyo][retu]=utukoma;
		if(Shogi2.teban==1) {
			Shogi2.sentemotigoma.remove(koma);
		}else {
			Shogi2.gotemotigoma.remove(koma);
		}
	}

	public boolean getNari() {
		return nari;
	}

	public void addMotigoma() {
		String koma=getName();
		if(koma=="歩"||koma=="と") {
			if(Shogi2.teban==1) {
				Shogi2.sentemotigoma.add("歩");
			}else {
				Shogi2.gotemotigoma.add("歩");
			}

		}else if(koma=="香"||koma=="成香") {
			if(Shogi2.teban==1) {
				Shogi2.sentemotigoma.add("香");
			}else {
				Shogi2.gotemotigoma.add("香");
			}

		}else if(koma=="桂"||koma=="成桂") {
			if(Shogi2.teban==1) {
				Shogi2.sentemotigoma.add("桂");
			}else {
				Shogi2.gotemotigoma.add("桂");
			}

		}else if(koma=="銀"||koma=="成銀") {
			if(Shogi2.teban==1) {
				Shogi2.sentemotigoma.add("銀");
			}else {
				Shogi2.gotemotigoma.add("銀");
			}

		}else if(koma=="金") {
			if(Shogi2.teban==1) {
				Shogi2.sentemotigoma.add("金");
			}else {
				Shogi2.gotemotigoma.add("金");
			}

		}else if(koma=="飛"||koma=="龍") {
			if(Shogi2.teban==1) {
				Shogi2.sentemotigoma.add("飛");
			}else {
				Shogi2.gotemotigoma.add("飛");
			}

		}else if(koma=="角"||koma=="馬") {
			if(Shogi2.teban==1) {
				Shogi2.sentemotigoma.add("角");
			}else {
				Shogi2.gotemotigoma.add("角");
			}
		}
	}
}
