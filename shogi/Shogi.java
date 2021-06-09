package shogi;

import java.util.HashMap;

public class Shogi {
	public static int[][]banmen=new int[9][9];
	public static int teban=1;
	public static int toryo=2;
	static HashMap<Integer,String> komaMap=new HashMap<>();
	public static void main(String[] args) {
		
	}
	public static void shokibanmen() {
		for(int suji=0;suji<9;suji++) {
			banmen[2][suji]=10;
			banmen[6][suji]=11;
		}
		//香車
		banmen[0][0]=20;
		banmen[0][8]=20;
		banmen[8][0]=21;
		banmen[8][8]=21;
		//桂馬
		banmen[0][1]=30;
		banmen[0][7]=30;
		banmen[8][1]=31;
		banmen[8][7]=31;
		//銀
		banmen[0][2]=40;
		banmen[0][6]=40;
		banmen[8][2]=41;
		banmen[8][6]=41;
		//金
		banmen[0][3]=50;
		banmen[0][5]=50;
		banmen[8][3]=51;
		banmen[8][5]=51;
		//玉
		banmen[0][4]=60;
		banmen[8][4]=61;
		//飛車
		banmen[1][1]=70;
		banmen[7][7]=71;
		//角
		banmen[1][7]=80;
		banmen[7][1]=81;

	}
	public static void banmenhyoji() {
		System.out.print("　");
		for(int suji=0;suji<9;suji++) {
			System.out.print(suji+1+" ");
		}
		System.out.println();
		for(int dan=0;dan<9;dan++) {
			if(dan==0) {
				System.out.print("一");
			}else if(dan==1) {
				System.out.print("二");
			}else if(dan==2) {
				System.out.print("三");
			}else if(dan==3) {
				System.out.print("四");
			}else if(dan==4) {
				System.out.print("五");
			}else if(dan==5) {
				System.out.print("六");
			}else if(dan==6) {
				System.out.print("七");
			}else if(dan==7) {
				System.out.print("八");
			}else if(dan==8) {
				System.out.print("九");
			}
			for(int suji=0;suji<9;suji++) {
				if(dan<3) {
					if(banmen[dan][suji]==0) {
						System.out.print("□");
					}else if(banmen[dan][suji]==1) {
						System.out.print("Ｐ");
					}else if(banmen[dan][suji]==2) {
						System.out.print("Ⅼ");
					}else if(banmen[dan][suji]==3) {
						System.out.print("Ｎ");
					}else if(banmen[dan][suji]==4) {
						System.out.print("Ｓ");
					}else if(banmen[dan][suji]==5) {
						System.out.print("Ｇ");
					}else if(banmen[dan][suji]==6) {
						System.out.print("Ｋ");
					}else if(banmen[dan][suji]==7) {
						System.out.print("Ｒ");
					}else if(banmen[dan][suji]==8) {
						System.out.print("Ｂ");
					}
				}else {
				if(banmen[dan][suji]==0) {
					System.out.print("□");
				}else if(banmen[dan][suji]==1) {
					System.out.print("歩");
				}else if(banmen[dan][suji]==2) {
					System.out.print("香");
				}else if(banmen[dan][suji]==3) {
					System.out.print("桂");
				}else if(banmen[dan][suji]==4) {
					System.out.print("銀");
				}else if(banmen[dan][suji]==5) {
					System.out.print("金");
				}else if(banmen[dan][suji]==6) {
					System.out.print("玉");
				}else if(banmen[dan][suji]==7) {
					System.out.print("飛");
				}else if(banmen[dan][suji]==8) {
					System.out.print("角");
				}
				}
			}
			System.out.println("");
		}
	}
	public static void komaMap() {
		komaMap.put(11, "歩");
		komaMap.put(21, "香");
		komaMap.put(31, "桂");
		komaMap.put(41, "銀");
		komaMap.put(51, "金");
		komaMap.put(61, "王");
		komaMap.put(71, "飛");
		komaMap.put(81, "角");
		komaMap.put(10, "歩");
		komaMap.put(20, "香");
		komaMap.put(30, "桂");
		komaMap.put(40, "銀");
		komaMap.put(50, "金");
		komaMap.put(60, "王");
		komaMap.put(70, "飛");
		komaMap.put(80, "角");
		komaMap.put(111, "と");
		komaMap.put(121, "成香");
		komaMap.put(131, "成桂");
		komaMap.put(141, "成銀");
		komaMap.put(171, "龍");
		komaMap.put(181, "馬");
		komaMap.put(110, "と");
		komaMap.put(120, "成香");
		komaMap.put(130, "成桂");
		komaMap.put(140, "成銀");
		komaMap.put(170, "龍");
		komaMap.put(180, "馬");
		
	}
}
