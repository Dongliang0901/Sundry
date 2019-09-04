import java.util.Arrays;

/*
 * The University of Melbourne
 * School of Computing and Information Systems
 * COMP90041 Programming and Software Development
 * Assignment 3
 * Student Name: Dongliang Liang
 * Student ID: 962661
 * 
 * NimGame contains play game method used in the Nim game
 * 
 */

public class NimGame{
			
	public int theReststoneNum;

	private int upperBound;

	private NimPlayer player1;

	private NimPlayer player2;
	
	private NimPlayer chesser;
	
	private int numMove;
	
	
	
	
	
	public NimGame(){
		
	}
	
	public NimGame(int theReststoneNum, NimPlayer player1, NimPlayer player2){
		this.player1=player1;
		this.player2=player2;
		this.theReststoneNum=theReststoneNum;
		this.upperBound=2;
	}
	
	public NimGame(int theReststoneNum, int upperBound, NimPlayer player1, NimPlayer player2){
		this.player1=player1;
		this.player2=player2;
		this.theReststoneNum=theReststoneNum;
		this.upperBound=upperBound;
	}

	//the playing process
	public NimPlayer PlayGame1(NimGame game){
		System.out.println();
		System.out.println("Initial stone count: " + game.theReststoneNum);	
		System.out.println("Maximum stone removal: " + game.upperBound);	
		System.out.println("Player 1: "+game.player1.getGivenname()+" "+game.player1.getFamilyname());				    
		System.out.println("Player 2: "+game.player2.getGivenname()+" "+game.player2.getFamilyname());	
		System.out.println();
		chesser=game.player1;
		while(game.theReststoneNum > 0){
			chesser.printStar(game.theReststoneNum);
			numMove=chesser.RemoveStone(chesser.getGivenname(),game.theReststoneNum,game.upperBound);
			if(numMove!=0){
				game.theReststoneNum=game.theReststoneNum-numMove;
				System.out.println();
				chesser = changePlayer(chesser,game.player1, game.player2);
				if (game.theReststoneNum<=0){
					System.out.println("Game Over");
					System.out.println(chesser.getGivenname() + " " + chesser.getFamilyname() + " wins!");
					System.out.println();
				}
			}
		}
			return chesser;	
	}
	
	//switch the player play the game
	private NimPlayer changePlayer(NimPlayer chesser,NimPlayer player1,NimPlayer player2) {
		if(player1.getGivenname().equals(chesser.getGivenname())){
			return player2;
		}else{
			return player1;
		}
	}
	
	public NimPlayer PlayGame2(NimGame game){
		System.out.println();
		String numMove = "";
		boolean[] stonenum=new boolean[game.theReststoneNum];
		Arrays.fill(stonenum, true);
		int count=game.theReststoneNum;
		System.out.println("Initial stone count: " + game.theReststoneNum);	
		System.out.print("Stones display:");
		printStar(count,stonenum);
		System.out.println("Player 1: "+game.player1.getGivenname()+" "+game.player1.getFamilyname());				    
		System.out.println("Player 2: "+game.player2.getGivenname()+" "+game.player2.getFamilyname());	
		System.out.println();
		chesser=game.player1;
		while(game.theReststoneNum > 0){
			System.out.print(game.theReststoneNum+" stones left:");
			printStar(count,stonenum);
			System.out.println(chesser.getGivenname()+"'s turn - which to remove?");
			numMove=chesser.advancedMove(stonenum,numMove);
			if(numMove!=""){
				String[] s=numMove.split(" ");
				if(Integer.parseInt(s[1])==1){
					game.theReststoneNum=game.theReststoneNum-1;
				}else{
					game.theReststoneNum=game.theReststoneNum-2;
				}
				move(s,stonenum);
				System.out.println();
				if (game.theReststoneNum<=0){
					System.out.println("Game Over");
					System.out.println(chesser.getGivenname() + " " + chesser.getFamilyname() + " wins!");
					System.out.println();
				}
				chesser = changePlayer(chesser,game.player1, game.player2);
			}
		}
		chesser = changePlayer(chesser,game.player1, game.player2);	
		return chesser;	
	}
	
	public void printStar(int theReststoneNum, boolean[] stonenum) {
		for (int i = 0; i < theReststoneNum; i++) {
			if (stonenum[i]==true) {
				System.out.print(" <"+(i+1)+",*>");
			}else{
				System.out.print(" <"+(i+1)+",x>");
			}
		}
		System.out.println();
	}
	
	public void move(String[] s, boolean[] stonenum){
		int num1=Integer.parseInt(s[0]);
		int num2=Integer.parseInt(s[1]);
		if(num2==2){
			stonenum[num1-1]=false;
			stonenum[num1]=false;
		}else{
			stonenum[num1-1]=false;
		}
		
		
		
	}
}
