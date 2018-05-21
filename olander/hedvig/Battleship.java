package olander.hedvig;

import java.util.Scanner;

public class Battleship {
	
	public Battleship() {
		
	}
	
	public void startGame() {
		boolean begin =false;
		Player player1 = null;
		Player player2 = null;
		
		while(!begin) {
			
			System.out.println("Do you want to play Human vs Human (1), Human vs Level0 (2), Human vs Level1 (3), Human vs Level2 (4), Level0 vs Level1 (5), Level0 vs Level2 (6) or Level1 vs Level2 (7) ?");
			Scanner scanner= new Scanner(System.in);
			int nb = Integer.parseInt(scanner.nextLine());
			if (nb==1) {
				System.out.println("Enter your name player 1: ");
				Scanner sc = new Scanner(System.in);
				String name = sc.nextLine();
				player1 = new HumanPlayer(name);
			
				System.out.println("Enter your name player 2: ");
				Scanner sc2 = new Scanner(System.in);
				String name2 = sc2.nextLine();
				player2 = new HumanPlayer(name2);
				begin =true;
			}else if(nb==2) {
				System.out.println("Enter your name player 1: ");
				Scanner sc = new Scanner(System.in);
				String name = sc.nextLine();
				player1 = new HumanPlayer(name);
				
				player2 = new Level0("Level0");
				begin =true;
			}else if(nb==3) {
				System.out.println("Enter your name player 1: ");
				Scanner sc = new Scanner(System.in);
				String name = sc.nextLine();
				player1 = new HumanPlayer(name);
				
				player2 = new Level1("Level1");
				begin =true;
			}else if(nb==4) {
				System.out.println("Enter your name player 1: ");
				Scanner sc = new Scanner(System.in);
				String name = sc.nextLine();
				player1 = new HumanPlayer(name);
				
				player2 = new Level2("Level2");
				begin =true;
			}else if(nb==5) {
				
				player1 = new Level0("Level0");
				player2 = new Level1("Level1");
				begin =true;
			}else if(nb==6) {
				
				player1 = new Level0("Level0");
				player2 = new Level2("Level2");
				begin =true;
			}else if(nb==7) {
				
				player1 = new Level1("Level1");
				player2 = new Level2("Level2");
				begin =true;
			
			}else {
				System.out.println("Choose a number between 1 and 7");
			}
		}	
		
		boolean again=true;
		
		while (again) {
			System.out.println("Time to create your fleets");
			Player player;
			int j=0;
			while (j<2){
				if (j==0) {
					System.out.println("Player 1 " + player1.getName());
					player = player1;
				}else{
					System.out.println("Player 2 " + player2.getName());
					player = player2;
				}
				String start = "";
				String end ="";
				int counterShip =0;
				
				while (counterShip <5) {
					boolean added =false; 
					while (!added) { //as long as it's not added
						boolean diagonal = true; 
						while (diagonal) {//as long as it's not diagonal
							start = player.chooseCoord();
							end = player.chooseCoord();
							diagonal = player.getBoard().isDiagonal(start, end);
							if (diagonal) {
								System.out.println("You can put a ship on the diagonal");
							}
						}
	
						Ship ship = new Ship(start, end);
						
						System.out.println("You have created a boat with size " + ship.getSize());
						
						if(ship.getSize()==3)	{
							
							String shipName = player.chooseShip();
							ship.setName(shipName);
							
						}else if (ship.getSize()==2) {
							ship.setName("Destroyer");
						}else if (ship.getSize()==4) {
							ship.setName("Battleship");
						}else if (ship.getSize()==5) {
							ship.setName("Carrier");
						}
						
						if (ship.getSize()>5) {
							System.out.println("To big size of the ship");
						}else if(ship.getSize()<2) {
							System.out.println("To small size of the ship");
						}else {
							boolean validNumber= player.validInsert(ship.getName());
							boolean placeOccupied = player.placeOccupied(ship);
							
							if (validNumber && !placeOccupied) {
								player.getFleet().add(ship);
								player.changeNumber(ship.getName());
								System.out.println("You added the ship to your fleet");
								added=true;
								counterShip+=1;
								
								for (int i = 0; i < ship.getSize(); i++) {
									int l = 0;
									char letter =(ship.getShipCoords().get(i).getCoordOne());
									if (letter=='A') {
										l =0;
									}else if (letter=='B') {
										l =1;
									}else if (letter=='C') {
										l =2;
									}else if (letter=='D') {
										l =3;
									}else if (letter=='E') {
										l =4;
									}else if (letter=='F') {
										l =5;
									}else if (letter=='G') {
										l =6;
									}else if (letter=='H') {
										l =7;
									}else if (letter=='I') {
										l =8;
									}else if (letter=='J') {
										l =9;
									}
									
									int number =(ship.getShipCoords().get(i).getCoordTwo()-1);
									player.getBoard().setBoard(l, number, "S");
			
								}
								System.out.println("Your battleship after adding the ship");
								System.out.println(player.getBoard().toString());
								
							}else if(!validNumber) {
								System.out.println("The size of the ship already exist in you fleet");
							}else if(placeOccupied) {
								System.out.println("The place is occupied by another ship");
							}
						}
					}
				}
				j++;
			}
			
			int counter =0;
			Player playingPlayer;
			Player waitingPlayer;
			
			while ((player1.getFleet().size() != 0) && (player2.getFleet().size() != 0)) {
				
				if (counter % 2==0) {
					playingPlayer=player1;
					waitingPlayer=player2;
				}else {
					playingPlayer=player2;
					waitingPlayer=player1;				
				}
				
				System.out.println("Player " + playingPlayer.getName() + " to hit");
				System.out.println("Your battleship: ");
				System.out.println(playingPlayer.getBoard().toString());
				System.out.println("");
				System.out.println("The battleship of the opponent to hit: ");
				System.out.println(playingPlayer.getHitBoard().toString());
				
				
				boolean hit = false;
				boolean destroyed =false;
				Ship shipDelete = null;
				
				String coordHit = playingPlayer.chooseCoordHit();
				System.out.println("Missilcoordinate = " + coordHit);
				Coordinate cHit = new Coordinate(coordHit);
				int l = 0;
				
				if (cHit.getCoordOne()=='A') {
					l =0;
				}else if (cHit.getCoordOne()=='B') {
					l =1;
				}else if (cHit.getCoordOne()=='C') {
					l =2;
				}else if (cHit.getCoordOne()=='D') {
					l =3;
				}else if (cHit.getCoordOne()=='E') {
					l =4;
				}else if (cHit.getCoordOne()=='F') {
					l =5;
				}else if (cHit.getCoordOne()=='G') {
					l =6;
				}else if (cHit.getCoordOne()=='H') {
					l =7;
				}else if (cHit.getCoordOne()=='I') {
					l =8;
				}else if (cHit.getCoordOne()=='J') {
					l =9;
				}
				
				int number = cHit.getCoordTwo()-1;
				
				
				for (Ship ship : waitingPlayer.getFleet()) {
					if(ship.isHit(coordHit)) {
						hit=true;
						playingPlayer.getHitBoard().setBoard(l, number, "X"); //when you hit a ship
						waitingPlayer.getBoard().setBoard(l, number, "X");
						Coordinate coord = new Coordinate(coordHit);
						playingPlayer.addCoordinate(coord);
						
						if(ship.isDestroyed()) {
							destroyed = true;
							shipDelete = ship;
						}
					}
				}
				
				if (hit) {
					System.out.println("You hit a ship");	
				}else {
					System.out.println("You hit the water");
					playingPlayer.getHitBoard().setBoard(l, number, "O"); //when you hit water
					waitingPlayer.getBoard().setBoard(l, number, "O");
				}
				
				if(destroyed) {
					playingPlayer.clearListOfHitShips(shipDelete);
					waitingPlayer.getFleet().remove(shipDelete);
					playingPlayer.setScore(1);
	
					System.out.println("You destroyed a ship");
					System.out.println("You have "+ waitingPlayer.getFleet().size() +" ships left to hit");
					System.out.println("Your score is " + playingPlayer.getScore());
				}
				
				counter=counter +1;
			}
			
			
			//END OF GAME
			System.out.println("----End of game----");
			
			if (player1.getScore()>player2.getScore()) {
				System.out.println("Winner is " + player1.getName());
				
			} else {
				System.out.println("Winner is " + player2.getName());
				
			}
			
			//Play again
			boolean right=true;
			while(right){
				System.out.println("You want to play again? Y/N");
				Scanner scanner = new Scanner(System.in);
				String answer=scanner.nextLine();
				if(answer.equals("Y")){
					again=true;
					right=false;
				
					player1.empty();
					player2.empty();
					
					Player t = player1;
					player1 = player2;
					player2 = t;
					
					
				}else if(answer.equals("N")){
					again=false;
					right=false;
				}else{
					System.out.println("Answer Y or N");
				}
			}

		}

		
	}
	
	public static void main(String args[]) {
		Battleship game = new Battleship();

		game.startGame();
	}
	
}
