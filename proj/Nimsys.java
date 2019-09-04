/*
 * The University of Melbourne
 * School of Computing and Information Systems
 * COMP90041 Programming and Software Development
 * Assignment 3
 * Student Name: Dongliang Liang
 * Student ID: 962661
 * 
 * Nim is a two players game, begins with a number of objects. 
 * Each player takes turns removing stones from the table.
 * On each turn, a player must remove at least one stone. 
 * In addition, there is an upper bound on the number of 
 * stones that can be removed in a single turn. 
 * The game ends when there are no more stones remaining. 
 * The player who removes the last stone, loses.
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Nimsys {
		private static int index=0;

		private static NimPlayer players[]=new NimPlayer[100];
		
		private NimGame ng=new NimGame();
		
		public static Scanner input=new Scanner(System.in);
		
		private static String[] choice=new String[100];
		
		private String choice1;
		
		//the order control
		public static void main(String[] args) throws Exception {
			Nimsys ns=new Nimsys();
			System.out.println("Welcome to Nim");
			System.out.println();
			File file = new File("player.dat");
			if (file.exists()) {
				try {
					@SuppressWarnings("resource")
					Scanner filereader = new Scanner(file);
					while (filereader.hasNextLine()) {
						String playercontent = filereader.nextLine();
						String[] content = new String[5];
						content = playercontent.split(" ");
						index++;
						if (content[1].equals("NimPlayer")) {
							ns.addplayer(players, playercontent);
						} else {
							ns.addaiplayer(players, playercontent);
						}
					}
				} catch (FileNotFoundException e) {
				}
			}
			
			while (true) {
				try{
					System.out.print("$");
					choice = input.nextLine().split(" ");
					ns.setChoice1(choice[0]);
					switch (ns.getChoice1()) {
						case "addplayer":
							ns.addplayer();
							break;
						case "addaiplayer":
							ns.addaiplayer();
							break;
						case "removeplayer":
							ns.removeplayer();
							break;
						case "editplayer":
							ns.editplayer();
							break;
						case "resetstats":
							ns.reset();
							break;
						case "displayplayer":
							ns.display();
							break;
						case "rankings":
							ns.ranking();
							break;
						case "startgame":
							players = ns.startgame(players, choice[1]);
							break;
						case "startadvancedgame":
							players = ns.startadvancegame(players, choice[1]);
							break;
						case "exit":
							try {
								FileOutputStream file1=new FileOutputStream("player.dat");
								PrintWriter outputStream = new PrintWriter(file1);
								for (int i = 0; i < index; i++) {
									if (players[i] != null) {
										outputStream.println(players[i].getClass().toString() + " " + players[i].getUsername() + " "
												+ players[i].getFamilyname() + " " + players[i].getGivenname() + " "
												+ players[i].getNumOfGame() + " " + players[i].getNumOfWon());
									}
								}
								outputStream.close();
							} catch (FileNotFoundException e) {
								System.out.println("File not Found");
							}
							System.out.println();
							System.exit(0);
							break;
						default:
							throw new Exception("'"+ns.getChoice1()+"' is not a valid command."); 
					}	
				}catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println();
				}
			}
//			System.out.println();
		}
		
		private String getChoice1(){
			return choice1;
		}

		private void setChoice1(String choice){
			this.choice1 = choice;
		}
		
		//add human player from the file
		public NimPlayer[] addplayer(NimPlayer[] players, String content) {
			String[] details = new String[10];
			details = content.split(" ");
			int NumOfGame = Integer.parseInt(details[5]);
			int NumOfWon = Integer.parseInt(details[6]);
	        
			NimHumanPlayer player = new NimHumanPlayer(details[2].toString(), details[4].toString(), details[3].toString(),NumOfGame, NumOfWon);
			players[index-1] = player;
			return players;
		}
		
		//add ai player from the file
		public NimPlayer[] addaiplayer(NimPlayer[] players, String content) {
			String[] details = new String[10];
			details = content.split(" ");
			int NumOfGame = Integer.parseInt(details[5]);
			int NumOfWon = Integer.parseInt(details[6]);
			NimAIPlayer aiplayer = new NimAIPlayer(details[2].toString(), details[4].toString(), details[3].toString(),
					NumOfGame, NumOfWon);
			players[index - 1] = aiplayer;
			return players;
		}
		
		//add player process
		private void addplayer() throws Exception{
			boolean choice;
			String input[]=new String[5];
			input=Nimsys.choice[1].split(",");
			if(input.length==3){
				choice=findexists(input[0],index);
				if(!choice){
					players[index]=new NimHumanPlayer(input[0],input[2],input[1]);
					index++;
				}else{
					System.out.println("The player already exists.");
				}
			}else{
				throw new Exception("Incorrect number of arguments supplied to command.");
			}			
			System.out.println();
		}
		
		//add ai player
		private void addaiplayer() throws Exception{
			boolean choice;
			String input[]=new String[5];
			input=Nimsys.choice[1].split(",");
			if(input.length==3){
				choice=findexists(input[0],index);
				if(!choice){
					players[index]=new NimAIPlayer(input[0],input[2],input[1]);
					index++;
				}else{
					System.out.println("The player already exists.");
				}
			}else{
				throw new Exception("Incorrect number of arguments supplied to command.");
			}
			System.out.println();
		}
		
		//find whether the player is in the array
		private boolean findexists(String string,int index){
			boolean choice=false;
			if(index==0){
				return choice;
			}else{
				for (int i = 0; i <index+1; i++) {
					if (players[i]!=null){
						if (string.equals(players[i].getUsername())){
							choice=true;
							break;
						}
					}else{
						return choice;
					}
				}
			}
			return choice;
		}
		
		//edit player process
		private void editplayer(){
			boolean choice;
			String input[]=new String[5];
			input=Nimsys.choice[1].split(",");
			choice=findexists(input[0],index);
			if(!choice){
				System.out.println("The player does not exist.");
			}else{
				for(int i=0; i<index; i++){
		        	if (players[i].getUsername()!=""){
		        		if(players[i].getUsername().equals(input[0])){
		        			players[i].setGivenname(input[2]);
		        			players[i].setFamilyname(input[1]);;   		
		        		}
		        	}
		        }
			}
			System.out.println();
		}		
		
		//remove player process
		private void removeplayer(){
			boolean choice;
			String input;
			if(Nimsys.choice.length>1){
				choice=findexists(Nimsys.choice[1],index);
				if(choice){
					players=RemovePlayer(players,index,Nimsys.choice[1].toString());
					index--;
				}else{
					System.out.println("The player does not exist.");
				}
			}else{
				System.out.println("Are you sure you want to remove all players? (y/n)");
				input=Nimsys.input.nextLine();
				if(input.equals("y")){
					for (int i = 0; i < 100; i++) {
						index=0;
					}
					}
			}
			System.out.println();
		}	
		
		//the specific remove process
		private NimPlayer[] RemovePlayer(NimPlayer[] players,int count, String name){
			Boolean found = false;
	        for (int i = 0; i<count; i++){
	        	if (players[i] != null){
	        		if ((players[i].getUsername().equals(name))||found){
	        			found = true;
	        			if (players[i+1]!= null){
	        				players[i].setUsername(players[i+1].getUsername());
	        				players[i].setFamilyname(players[i+1].getFamilyname());
	        				players[i].setGivenname(players[i+1].getGivenname());
	        				players[i].setNumOfGame(players[i+1].getNumOfGame());
	        				players[i].setNumOfWon(players[i+1].getNumOfWon());
	        				players[i].setRank(players[i+1].getRank());
	        			}else{
	        				players[i].setUsername("");
	        				players[i].setGivenname("");
	        				players[i].setFamilyname("");
	        				players[i].setNumOfGame(0);
	        				players[i].setNumOfWon(0);
	        				players[i].setRank(0.0);
	        			}
	        		}    
	        	}
	        }
			return players;
		}
		
		//reset player process
		private void reset(){
			boolean choice;
			String input;
			if(Nimsys.choice.length==1){
				System.out.println("Are you sure you want to reset all player statistics? (y/n)");
				input=Nimsys.input.nextLine();
				if(input.equals("y")){
					for (int j = 0; j < index+1; j++) {
						players[j].setNumOfGame(0);
						players[j].setNumOfWon(0);
						players[j].setRank(0.0);
					}
				}
			}else{
				choice=findexists(Nimsys.choice[1],index);
				if(!choice){
					System.out.println("The player does not exist.");
				}else{
					for(int i=0; i <index; i++){
						if(players[i].getUsername().equals(Nimsys.choice[1])){
							players[i].setNumOfGame(0); 
							players[i].setNumOfWon(0);							
							players[i].setRank(0.0);				
						}
					} 
				}
			}
			System.out.println();
		}
	
		//display player process
		private void display(){
			if(Nimsys.choice.length==1){
				Sort(players,index);
				for (int i = 0; i <index; i++) {
					if(players[i].getUsername()!=""){
						System.out.println(players[i].getUsername()+","
												+players[i].getGivenname()+","
												+players[i].getFamilyname()+","
												+players[i].getNumOfGame()+" games,"
												+players[i].getNumOfWon()+" wins");
					}
				}
			}else{
				int s=searchplayer(Nimsys.choice[1], index);
				if(s==-1){
					System.out.println("The player does not exist.");
				}else{
					for (int i = 0; i <index; i++) {
						if(players[i].getUsername()!=""){
							if(players[i].getUsername().equals(Nimsys.choice[1])){
								System.out.println(players[i].getUsername()+","
										+players[i].getGivenname()+","
										+players[i].getFamilyname()+","
										+players[i].getNumOfGame()+" games,"
										+players[i].getNumOfWon()+" wins");
							}
						}
					}
				}
			}
			System.out.println();
		}
		
		//search whether the player exists
		private int searchplayer(String name,int index){
			for(int i=0; i<index; i++){
				if(name.compareTo(players[i].getUsername())==0){
					return i;
				}
			}
			return -1;
		}
		
		//sort the payer in alphabetic order
		private void Sort(NimPlayer[] players,int index){
			for (int i=0; i<index-1; i++){
				for (int j=1; j<index-i; j++){	
					if (players[j].getUsername().compareTo(players[j-1].getUsername())<0){
						NimPlayer Temp=players[j-1];
						players[j-1]=players[j];
						players[j]=Temp;
					}
				}
			}
		}

		//rank the player result
		private void ranking(){
			String percent="";
			players=RankingSort(players,index);
			if(((Nimsys.choice.length==1)||(Nimsys.choice[1].equals("desc")))){
				for (int i=0; i<index; i++){
					if(players[i].getUsername()!=null){
					Integer rankTemp=(int) Math.round(players[i].getRank());
					percent=rankTemp.toString()+"%";
					System.out.printf("%-5.5s| %02d games | %s %s%n", percent,players[i].getNumOfGame(), 
													players[i].getGivenname(),players[i].getFamilyname());
					}
				}
			}else{
				for (int i=index-1; i>-1; i--){
					Integer rankTemp =(int)Math.round(players[i].getRank());
					percent=rankTemp.toString()+"%";
					System.out.printf("%-5.5s| %02d games | %s %s%n" ,percent,players[i].getNumOfGame(), 
														players[i].getGivenname(),players[i].getFamilyname());
					
				}
			}
			System.out.println();
		}
		
		//sort the player depend on the result
		private NimPlayer[] RankingSort(NimPlayer[] players,int index){
			for (int i=0; i<index-1; i++){
				for (int j=1; j<index-i; j++){
					if(players[j]!=null){
					if(players[j-1].getRank().compareTo(players[j].getRank())<0){
						NimPlayer Temp=players[j-1];
						players[j-1]=players[j];
						players[j]=Temp;
					}
					if(players[j-1].getRank().compareTo(players[j].getRank())==0){
						if (players[j].getUsername().compareTo(players[j-1].getUsername())<0){
							NimPlayer Temp=players[j-1];
							players[j-1]=players[j];
							players[j]=Temp;
						}
					}
				}
				}
			}
			return players;
		}
		
		// start the game process
		private NimPlayer[] startgame(NimPlayer[] players,String string){
			String input[]=string.split(",");
			if(!(findexists(input[2],index)&&findexists(input[3],index))){
				System.out.println("One of the players does not exist.");
				System.out.println();
			}else{
				NimPlayer player1=searchPlayer(players, input[2]);
				NimPlayer player2=searchPlayer(players, input[3]);
				players=NumPlay(players, 100, player1);
				players=NumPlay(players, 100, player2);
				NimGame game=new NimGame(Integer.parseInt(input[0]),Integer.parseInt(input[1]),player1,player2);
				NimPlayer winner=ng.PlayGame1(game);
				players=NumWon(players, winner);
			}
			return players;
		}
		
		//find the player exists or not and return the username
		private NimPlayer searchPlayer(NimPlayer[] players,String player){
			boolean choice=false;
			int i=0;
			while(!choice){
				if (players[i].getUsername().equals(player)){
					choice=true;
				}
				i++;
			}
			return players[i-1];
		}
		
		//record the number of game the player played
		private NimPlayer[] NumPlay(NimPlayer[] players,int count, NimPlayer player){
			boolean choice=false;
			int i=0;
			while(!choice){
				if(players[i].getUsername().equals(player.getUsername())){
					players[i].setNumOfGame(player.getNumOfGame()+1);
					players[i].setRank((double)Math.round(((double)player.getNumOfWon()/(double)player.getNumOfGame()*100)));
					choice=true;
				}
				i++;
			}
			return players;
		}
		
		//record the number of win the player had
		private NimPlayer[] NumWon(NimPlayer[] players,NimPlayer player){
			boolean choice=false;
			for (int i = 0; i < players.length; i++) {
				if(!choice){
					if((players[i].getUsername()!="")&&(players[i].getUsername().equals(player.getUsername()))){
						players[i].setNumOfWon(player.getNumOfWon()+1);
						players[i].setRank((double)Math.round(((double)player.getNumOfWon()/(double)player.getNumOfGame()*100)));
						choice=true;
					}
				}
			}
			return players;
		}

		private NimPlayer[] startadvancegame(NimPlayer[] players,String string){
			String input[]=string.split(",");
			if(!(findexists(input[1],index)&&findexists(input[2],index))){
				System.out.println("One of the players does not exist.");
				System.out.println();
			}else{
				NimPlayer player1=searchPlayer(players, input[1]);
				NimPlayer player2=searchPlayer(players, input[2]);
				players=NumPlay(players, 100, player1);
				players=NumPlay(players, 100, player2);
				NimGame game=new NimGame(Integer.parseInt(input[0]),player1,player2);
				NimPlayer winner=ng.PlayGame2(game);
				players=NumWon(players, winner);
			}
			
			return players;
		}

		

}
