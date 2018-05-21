package olander.hedvig;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Player {
	private String name;
	private int totScore;
	private int score;
	private ArrayList<Ship> fleet;
	private Board board;
	private int nbCarrier;
	private int nbCruiser ;
	private int nbBattleship ;
	private int nbSubmarine;
	private int nbDestroyer;
	private ArrayList<Coordinate>listOfHit;
	private ArrayList<Coordinate> listOfHitShips;
	private Board hitBoard;

	public Player(String name) {
		this.name = name;
		this.score = 0;
		this.totScore = 0;
		this.fleet = new ArrayList<>();
		this.board = new Board();
		this.hitBoard = new Board();
		this.listOfHit = new ArrayList<>();
		this.listOfHitShips = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotScore() {
		return totScore;
	}

	public void setTotScore(int totScore) {
		this.totScore = this.totScore + totScore;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = this.score + score;
	}

	public void resetScore() {
		this.score = 0;
	}
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	public Board getHitBoard() {
		return hitBoard;
	}

	public void setHitBoard(Board board) {
		this.hitBoard = board;
	}
	
	public ArrayList<Ship> getFleet() {
		return this.fleet;
	}
	
	public boolean alphaNumeric(String string) {
		return string.matches("[a-zA-Z0-9]+");
	}
	
	public boolean validCoord(String coord) {
		if (alphaNumeric(coord) && coord.length()<4 && coord.length()>1) {
			return true;
		}else {
			return false;
		}
	}
	
	public void empty(){
				
		this.board.emptyBoard();
		this.hitBoard.emptyBoard();
		this.resetScore();
		
		if (!this.fleet.isEmpty()){
			this.fleet.clear();
		}
		
		if (!this.listOfHit.isEmpty()){
			this.listOfHit.clear();
		}
		
		if (!this.listOfHitShips.isEmpty()){
			this.listOfHitShips.clear();
		}
		
		this.setNbBattleship(0);
		this.setNbCarrier(0);
		this.setNbCruiser(0);
		this.setNbDestroyer(0);
		this.setNbSubmarine(0);
	}

	public boolean validInsert(String name) {
		boolean insert = false;

		if (name.equals("Destroyer")) {
			if (this.getNbDestroyer() == 0) {
				insert = true;
			}else {
				insert=false;
			}
		} else if (name.equals("Carrier")) {
			if (this.getNbCarrier() == 0) {
				insert = true;
			}else {
				insert=false;
			}
		} else if (name.equals("Battleship")) {
			if (this.getNbBattleship() == 0) {
				insert = true;
			}else {
				insert=false;
			}
		} else if (name.equals("Cruiser")) {
			if (this.getNbCruiser() == 0) {
				insert = true;

			}else {
				insert=false;
			}
		} else if (name.equals("Submarine")) {
			if (this.getNbSubmarine() == 0) {
				insert = true;
			}else {
				insert=false;
			}
		}
		return insert;
	}
	
	public void changeNumber(String name) {
		if (name.equals("Destroyer")) {
			this.setNbDestroyer(1);
		} else if (name.equals("Carrier")) {
			this.setNbCarrier(1);
		} else if (name.equals("Battleship")) {
			this.setNbBattleship(1);
		} else if (name.equals("Cruiser")) {
			this.setNbCruiser(1);
		} else if (name.equals("Submarine")) {
			this.setNbSubmarine(1);
		}
	}
	
	public boolean placeOccupied(Ship ship) {
		boolean occupied =false;
		for (Ship s : this.fleet) {
			int i = 0;
			while (i<s.getSize() && !occupied) {
				int j =0;
				while (j <ship.getSize() && !occupied) {
					if (s.getShipCoords().get(i).getCoordinate().equals(ship.getShipCoords().get(j).getCoordinate())) {
						occupied = true;
					}
					j++;
				}
				i++;
			}
		}
		return occupied;
	}
	public int getNbCarrier() {
		return nbCarrier;
	}

	public void setNbCarrier(int nbCarrier) {
		this.nbCarrier = nbCarrier;
	}

	public int getNbCruiser() {
		return nbCruiser;
	}

	public void setNbCruiser(int nbCruiser) {
		this.nbCruiser = nbCruiser;
	}

	public int getNbBattleship() {
		return nbBattleship;
	}

	public void setNbBattleship(int nbBattleship) {
		this.nbBattleship = nbBattleship;
	}

	public int getNbSubmarine() {
		return nbSubmarine;
	}

	public void setNbSubmarine(int nbSubmarine) {
		this.nbSubmarine = nbSubmarine;
	}

	public int getNbDestroyer() {
		return nbDestroyer;
	}

	public void setNbDestroyer(int nbDestroyer) {
		this.nbDestroyer = nbDestroyer;
	}

	public String chooseShip(){
		String name;

		if(this.getNbCruiser() ==0){
			name="Cruiser";
		}else if(this.getNbSubmarine() ==0){
			name="Submarine";
		}else{
			name="Cruiser";
		}
		return name;
		
	}
	
	public abstract String chooseCoord();
	
	public abstract String chooseCoordHit();

	public abstract void addCoordinate(Coordinate coord);

	public abstract void clearListOfHitShips(Ship shipDelete);

	public void resetTotScore() {
		this.totScore=0;		
	}


	

	
	

}
