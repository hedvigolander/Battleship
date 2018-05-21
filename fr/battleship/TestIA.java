package fr.battleship;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import olander.hedvig.Coordinate;
import olander.hedvig.Level0;
import olander.hedvig.Level1;
import olander.hedvig.Level2;
import olander.hedvig.Player;
import olander.hedvig.Ship;

public class TestIA {
	
	public TestIA() {
		
	}
		
	public void startGame() {
	
		Player player1 = null;
		Player player2 = null;
		int player1Type1 =0;
		int player1Type0 =0;
		int player1Type2=0;
		int player2Type1=0;
		int player2Type0=0;
		int player2Type2=0;
		
		int chooseType = 0;
		while(chooseType<3) {
			if(chooseType==0){
				player1 = new Level0("AI1");
				player2 = new Level1("AI2");
			
			}else if(chooseType==1) {
			
				player1 = new Level0("AI1");
				player2 = new Level2("AI2");
			
			}else if(chooseType==2) {
				
				player1 = new Level1("AI1");
				player2 = new Level2("AI2");

			}
			
			for (int k=0; k<100; k++){
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
						player1.setTotScore(1);
						
					} else {
						System.out.println("Winner is " + player2.getName());
						player2.setTotScore(1);
					}
					player1.empty();
					player2.empty();
					
					Player t = player1;
					player1 = player2;
					player2 = t;		
	
				
			}
			if(chooseType==0){
				player1Type0=player1.getTotScore() ;
				player2Type0=player2.getTotScore();
			
			}else if(chooseType==1) {
			
				player1Type1=player1.getTotScore();
				player2Type1=player2.getTotScore();
			
			}else if(chooseType==2) {
				
				player1Type2=player1.getTotScore();
				player2Type2=player2.getTotScore();

			}
			player1.resetTotScore();
			player2.resetTotScore();
			
			chooseType++;
			
		}

		int totPlayer1 = player1Type0 + player1Type1+ player1Type2;
		int totPlayer2 = player2Type0 + player2Type1+ player2Type2;
		
		System.out.println("Player "+ player1.getName() + " has total point of " + totPlayer1);
		System.out.println("Player "+ player2.getName() + " has total point of " + totPlayer2);	
		writeFile(player1.getName(),totPlayer1, player1Type0, player1Type1, player1Type2, player2.getName(), totPlayer2, player2Type0, player2Type1, player2Type2);	
	}

	private void writeFile(String name, int totScore, int p1T0, int p1T1, int p1T2, String name2, int totScore2, int p2T0, int p2T1, int p2T2) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("ai_proof.csv"));
		} catch (FileNotFoundException e) {
			System.out.println("No file");
			e.printStackTrace();
		}
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("; ");
        sb.append(totScore);
        sb.append("; ");    
        sb.append(name2);
        sb.append("; ");
        sb.append(totScore2);
        sb.append("\n");
        sb.append("AI Level Beginner; ");
        sb.append(p1T0);
        sb.append("; AI Level Medium; ");
        sb.append(p2T0);
        sb.append("\n");
        sb.append("AI Level Beginner; ");
        sb.append(p1T1);
        sb.append("; AI Level Hard; ");
        sb.append(p2T1);
        sb.append("\n");
        sb.append("AI Level Medium; ");
        sb.append(p1T2);
        sb.append("; AI Level Hard; ");
        sb.append(p2T2);
        sb.append("\n"); 
        

        pw.write(sb.toString());
        pw.close();
        System.out.println("The result is written in the file ai_proof.csv");
		
	}

		public static void main(String args[]) {
			TestIA game = new TestIA();
			game.startGame();
			
			
		}
}
