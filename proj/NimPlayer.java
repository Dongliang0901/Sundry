/*
 * The University of Melbourne
 * School of Computing and Information Systems
 * COMP90041 Programming and Software Development
 * Assignment 3
 * Student Name: Dongliang Liang
 * Student ID: 962661
 * 
 * Class of Nim, including the information about player.
 * 
 */

public abstract class NimPlayer {
	
		private String username;

		private String givenname;

		private String familyname;

		private int NumOfGame;

		private int NumOfWon;
		
		private Double rank;
		
		private NimGame ng;
		
		public NimPlayer(){
			
		}
		
		public NimPlayer(String username, String givenname,String familyname){
			this.username=username;
			this.givenname=givenname;
			this.familyname=familyname;
			this.NumOfGame=0;
			this.NumOfWon=0;
			this.rank=0.0;
		}
		
		public NimPlayer(String username, String givenname, String familyname,int NumOfGame,int NumOfWon){
			this.username=username;
			this.givenname=givenname;
			this.familyname=familyname;
			this.NumOfGame=NumOfGame;
			this.NumOfWon=NumOfWon;
			this.rank = (double) Math.round(((double) NumOfWon/(double) NumOfGame * 100));
		}

		public Double getRank() {
			return rank;
		}
		
		public void setRank(Double rank){
			this.rank=rank;
		}
		
		public int getNumOfGame() {
			return NumOfGame;
		}

		public void setNumOfGame(int numOfGame) {
			this.NumOfGame = numOfGame;
		}

		public int getNumOfWon() {
			return NumOfWon;
		}

		public void setNumOfWon(int numOfWon) {
			this.NumOfWon = numOfWon;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getGivenname() {
			return givenname;
		}

		public void setGivenname(String givenname) {
			this.givenname = givenname;
		}

		public String getFamilyname() {
			return familyname;
		}

		public void setFamilyname(String familyname) {
			this.familyname = familyname;
		}
		
		public void printStar(int theReststoneNum) {
			System.out.printf("%d stones left:",theReststoneNum);
			for(int i=0;i<theReststoneNum;i++){
				System.out.print(" *");
			}
			System.out.println();
		}
		
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

		public String advancedMove(boolean[] available, String lastMove){
			return null;	
		}
		
}
