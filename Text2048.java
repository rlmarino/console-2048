//a text based 2048 game in java
import java.io.IOException;
import java.util.Scanner;

public class Text2048 {	
	
	public static void main(String[] args) throws IOException {
		Board board = new Board();
		Scanner scan = new Scanner(System.in);
		board.initializeBoard();
		board.printBoard();
		
		String command = " ";
		while(!command.equals("q")) {
			System.out.print("W to move up, A to move left, S to move down, D to move right, Q to quit ");
			command = scan.nextLine();
			command = command.toLowerCase();
			switch(command) {
			
			case "w":
				board.moveUp();
				board.printBoard();
				break;
				
			case "a":
				board.moveLeft();
				board.printBoard();
				break;
			
			case "s":
				board.moveDown();
				board.printBoard();
				break;
				
			case "d":
				board.moveRight();
				board.printBoard();
				break;

			case "q":
				board.printBoard();
				board.endScreen();
				scan.close();
				break;
				
			default:
				System.out.println("Please enter a valid input.");
				break;
			}
		}
	}
}
