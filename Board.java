import java.util.Random;

public class Board 
{
	private int[][] board;
	private int freeBoxes, score;
	Random rand;
	
	public Board()
	{
		board = new int[4][4];
	}
	//initialize the board with 0's and 2 random boxes
	public void initializeBoard() 
	{
		freeBoxes = 16;
		score = 0;
		rand = new Random();
		for(int i = 0; i < 4; i++) 
		{
			for(int j = 0; j < 4; j++) 
				board[i][j] = 0;
		}
		spawnNumber();
		spawnNumber();
	}
	
	public void spawnNumber()
	{
		//fills an empty box with a 2 or a 4 (10% chance to spawn a 4)
		if(freeBoxes != 0)
		{
			int location = rand.nextInt(freeBoxes);
			int count = 0;
			int num = rand.nextInt(10);
			if(num == 0) {
				num = 4;
			}else {
				num = 2;
			}
			
			for(int i = 0; i < 4; i++) 
			{
				for(int j = 0; j < 4; j++)
				{
					if(board[i][j] == 0) {
						if(count == location)
							board[i][j] = num;
						count++;
					}
				}
			}
			
			freeBoxes--;
		}
	}
		
	public void printBoard() {
		int numSpaces;
		/*  4x4 board format:
		 *  +------+------+------+------+
		 *	|      |      |      |      | 
		 *	+------+------+------+------+
		 *	|      |      |      |      | 
		 *	+------+------+------+------+
		 *	|      |      |      |      | 
		 *	+------+------+------+------+
		 *	|      |      |      |      | 
		 *	+------+------+------+------+
		 */
		for(int i = 0; i < 5; i++) 
		{
			System.out.println("+------+------+------+------+");
			if(i < 4)
			{
				for(int j = 0; j < 4; j++)
				{
					numSpaces = 4 - String.valueOf(board[i][j]).length();
					System.out.print("| ");
					for(int k = 0; k < numSpaces; k++)
						System.out.print(" ");
					if(board[i][j] == 0)
					{
						System.out.print("  ");
					}else{
						System.out.print(board[i][j] + " ");
					}
				}
				System.out.print("|\n");
			}
		}
		System.out.println("score: " + score);
	}//end printBoard()
	
	public void moveUp() {
		boolean changed = false;
		for(int j = 0; j < 4; j++) {
			int oldScore = score;
			for(int i = 0; i < 4; i++) {
				for(int k = i; k > 0; k--) {
					if(board[k][j] != 0) {
						if(board[k-1][j] == 0) {
							board[k-1][j] = board[k][j];
							board[k][j] = 0;
							changed = true;
						}else if(board[k-1][j] == board [k][j] && oldScore == score) {
							score += board[k][j];
							board[k-1][j] += board[k][j];
							board[k][j] = 0;
							changed = true;
							freeBoxes++;
						}
					}
				}
			}
		}
		if(changed) {
			spawnNumber();
		}
	}//end moveUp()
	
	public void moveLeft() {
		boolean changed = false;
		for(int i = 0; i < 4; i++) {
			int oldScore = score;
			for(int j = 0; j < 4; j++) {
				for(int k = j; k > 0; k--) {
					if(board[i][k] != 0) {
						if(board[i][k-1] == 0) {
							board[i][k-1] = board[i][k];
							board[i][k] = 0;
							changed = true;
						}else if(board[i][k-1] == board [i][k] && oldScore == score) {
							score += board[i][k];
							board[i][k-1] += board[i][k];
							board[i][k] = 0;
							changed = true;
							freeBoxes++;
						}
					}
				}
			}
		}
		if(changed) {
			spawnNumber();
		}
	}//end moveLeft();
	
	public void moveDown() {
		boolean changed = false;
		for(int j = 0; j < 4; j++) {
			int oldScore = score;
			for(int i = 2; i >= 0; i--) {
				for(int k = i; k <= 2; k++) {
					if(board[k][j] != 0) {
						if(board[k+1][j] == 0) {
							board[k+1][j] = board[k][j];
							board[k][j] = 0;
							changed = true;
						}else if(board[k+1][j] == board [k][j] && oldScore == score) {
							score += board[k][j];
							board[k+1][j] += board[k][j];
							board[k][j] = 0;
							changed = true;
							freeBoxes++;
						}
					}
				}
			}
		}
		if(changed) {
			spawnNumber();
		}
	}//end moveDown()
	
	public void moveRight() {
		boolean changed = false;
		for(int i = 0; i < 4; i++) {
			int oldScore = score;
			for(int j = 2; j >= 0; j--) {
				for(int k = j; k <= 2; k++) {
					if(board[i][k] != 0) {
						if(board[i][k+1] == 0) {
							board[i][k+1] = board[i][k];
							board[i][k] = 0;
							changed = true;
						}else if(board[i][k+1] == board [i][k] && oldScore == score) {
							score += board[i][k];
							board[i][k+1] += board[i][k];
							board[i][k] = 0;
							changed = true;
							freeBoxes++;
						}
					}
				}
			}
		}
		if(changed) {
			spawnNumber();
		}
	}//end moveRight();
	
	public void endScreen() {
		boolean won = false;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(board[i][j]>=2048)
					won = true;
			}
		}
		if(won == false) {
			System.out.println("          GAME OVER");
			System.out.println("    Better luck next time!");
			System.out.println("       Final score: " + score);
		}else {
			System.out.println("  CONGRATULATIONS! YOU WON!");
			System.out.println("     Final score: " + score);
			
		}
	}//end endScreen
}//end class Board
