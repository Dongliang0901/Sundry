/*
 * The University of Melbourne
 * School of Computing and Information Systems
 * COMP90041 Programming and Software Development
 * Assignment 3
 * Student Name: Dongliang Liang
 * Student ID: 962661
 * 
 * NimAIPlayer contains AI player method
 * 
 */

public class NimAIPlayer extends NimPlayer implements Testable{
	
	public NimAIPlayer() {
		
	}

	public NimAIPlayer(String username, String givenname,String familyname) {
		super(username, givenname, familyname);
	}

	public NimAIPlayer(String username, String givenname, String familyname,int NumOfGame,int NumOfWon) {
		super(username, givenname, familyname,NumOfGame, NumOfWon);
	}

	@Override
	public int RemoveStone(String player, int total, int max) {
		System.out.println(player+"'s turn - remove how many?");
		int numMove=(total-1)%(max+1);
		return numMove;
	}
	
	@Override
	public String advancedMove(boolean[] available, String lastMove) {
		String move=null;
		if(lastMove==""){
			if(available.length%2==1)
				move=(available.length/2+1)+" 1";
			else{
				move=(available.length/2)+" 2";
			}
		}else{
			int num1=Integer.parseInt(lastMove.split(" ")[0]);
			int num2=Integer.parseInt(lastMove.split(" ")[1]);
			if(available[available.length/2]==true){
				if(available.length%2==1){
					move=(available.length/2+1)+" 1";
				}else{
					move=(available.length/2)+" 2";
				}
			}else{
				if(num2==1){
					move=(available.length-num1+1)+" 1";
				}else{
					move=(available.length-num1)+" 2";
				}
			}
		}
		return move;
	}
}