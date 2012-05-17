public class Player {

	public static int player_x = 1;
	public static int player_y = 1;
	public static int old_x;
	public static int old_y;

	public static void goUp(int x, int y) {
		player_x = x;
		player_y = y - 1;
		old_x = x;
		old_y = y;
	}

	public static void goDown(int x, int y) {
		player_x = x;
		player_y = y + 1;
		old_x = x;
		old_y = y;
	}

	public static void goLeft(int x, int y) {
		player_x = x - 1;
		player_y = y;
		old_x = x;
		old_y = y;
	}

	public static void goRight(int x, int y) {
		player_x = x + 1;
		player_y = y;
		old_x = x;
		old_y = y;
	}

	public static void playerToField() {
		Field.fieldNumbers[old_x][old_y] = 0;
		Field.fieldNumbers[player_x][player_y] = 3;
	}

}
