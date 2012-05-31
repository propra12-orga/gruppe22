public class Player {

	int x;
	int y;
	int bCnt;
	int rad;
	int xo;
	int yo;
	int num;
	String ctrl;

	public Player() {
		this.x = 1;
		this.y = 1;
		
		this.xo = x;
		this.yo = y;
		this.bCnt = 2;
		this.rad = 1;
		this.ctrl ="";
		this.num = 1;
	}
	
	public static void getStartPos2(Player player){
		player.x = 19;
		player.y = 15;
		player.num = 2;
	}
	
}
