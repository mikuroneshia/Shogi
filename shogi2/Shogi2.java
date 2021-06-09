package shogi2;

import java.util.ArrayList;

public class Shogi2 {
	public static Koma [][]banmen=new Koma[9][9];
	public static ArrayList<String>sentemotigoma=new ArrayList<>();
	public static ArrayList<String>gotemotigoma=new ArrayList<>();
	public static ArrayList<Koma[][]> kihu=new ArrayList<>();
	public static ArrayList<ArrayList<String>>smotigomakihu=new ArrayList<>();
	public static ArrayList<ArrayList<String>>gmotigomakihu=new ArrayList<>();
	public static int teban=1;
	public Shogi2() {
		shokibanmen();
	}
	public static void shokibanmen() {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				banmen[i][j]=null;
			}
		}
		for(int j=0;j<9;j++) {
			Hu hu1=new Hu(6,j,1);
			banmen[6][j]=hu1;
			Hu hu0=new Hu(2,j,0);
			banmen[2][j]=hu0;
		}
		Kyosha  kyo1=new Kyosha(8,8,1);
		banmen[8][8]=kyo1;
		Kyosha  kyo2=new Kyosha(8,0,1);
		banmen[8][0]=kyo2;
		Kyosha  kyo3=new Kyosha(0,8,0);
		banmen[0][8]=kyo3;
		Kyosha  kyo4=new Kyosha(0,0,0);
		banmen[0][0]=kyo4;

		Keima  kei1=new Keima(8,7,1);
		banmen[8][7]=kei1;
		Keima  kei2=new Keima(8,1,1);
		banmen[8][1]=kei2;
		Keima  kei3=new Keima(0,7,0);
		banmen[0][7]=kei3;
		Keima  kei4=new Keima(0,1,0);
		banmen[0][1]=kei4;

		Gin gin1=new Gin(8,2,1);
		banmen[8][2]=gin1;
		Gin gin2=new Gin(8,6,1);
		banmen[8][6]=gin2;
		Gin gin3=new Gin(0,2,0);
		banmen[0][2]=gin3;
		Gin gin4=new Gin(0,6,0);
		banmen[0][6]=gin4;

		Kin kin1=new Kin(8,3,1);
		banmen[8][3]=kin1;
		Kin kin2=new Kin(8,5,1);
		banmen[8][5]=kin2;
		Kin kin3=new Kin(0,3,0);
		banmen[0][3]=kin3;
		Kin kin4=new Kin(0,5,0);
		banmen[0][5]=kin4;

		Hisha hi1=new Hisha(7,7,1);
		banmen[7][7]=hi1;
		Hisha hi0=new Hisha(1,1,0);
		banmen[1][1]=hi0;

		Kaku kaku1=new Kaku(7,1,1);
		banmen[7][1]=kaku1;
		Kaku kaku2=new Kaku(1,7,0);
		banmen[1][7]=kaku2;

		Gyoku ou1=new Gyoku(8,4,1);
		banmen[8][4]=ou1;
		Gyoku ou2=new Gyoku(0,4,0);
		banmen[0][4]=ou2;

	}
	public static void tebankousin() {
		if(teban==1) {
			teban=0;
		}else {
			teban=1;
		}
	}
	public static void kihu() {
		kihu.add(banmen);
		smotigomakihu.add(sentemotigoma);
		gmotigomakihu.add(gotemotigoma);
	}
	public static void main(String[] args) {

	}

}
