/*
 * The University of Melbourne
 * School of Computing and Information Systems
 * COMP90041 Programming and Software Development
 * Assignment 3
 * Student Name: Dongliang Liang
 * Student ID: 962661
 * 
 * NimHumanPlayer contains Human player method
 * 
 */

public class NimHumanPlayer extends NimPlayer{
	
	public NimHumanPlayer(String username, String givenname,String familyname) {
		super(username, givenname, familyname);
	}

	public NimHumanPlayer(String username, String givenname, String familyname,int NumOfGame,int NumOfWon) {
		super(username, givenname, familyname,NumOfGame, NumOfWon);
	}

	@Override
	public int RemoveStone(String player, int total, int max){
		System.out.println(player+"'s turn - remove how many?");
		Integer numMove=Integer.parseInt(Nimsys.input.nextLine());
		try {
			if(numMove<0||numMove>max||numMove>total){
				if(numMove<0||numMove>max){
					throw new Exception("Invalid move. You must remove between 1 and "+max+" stones.");
				}else{
					throw new Exception("Invalid move. You must remove between 1 and "+total+" stones.");
				}
			}else{
				return numMove;
			}
		} catch (Exception e) {
			System.out.println();
			System.out.println(e.getMessage());
			System.out.println();
			return 0;
		}
	}
	
	public String advancedMove(boolean[] available, String lastMove) {
		String input=Nimsys.input.nextLine();
		String[] move=input.split(" ");
		try {
			int num1=Integer.parseInt(move[0]);
			int num2=Integer.parseInt(move[1]);
			if(num2>2||num2<1||num1>12){
				throw new Exception("Invalid move.");
			}else{
				if(num2==2){
					if(available[num1-1]==false||available[num1]==false){
						throw new Exception("Invalid move.");
					}else{
						return input;
					}
				}else if(num2==1 && available[num1-1]==false){
						throw new Exception("Invalid move.");
				}else{
					return input;
				}
			}
		} catch (Exception e) {
			System.out.println();
			System.out.println(e.getMessage());
			System.out.println();
			return "";
		}	
	}
}
