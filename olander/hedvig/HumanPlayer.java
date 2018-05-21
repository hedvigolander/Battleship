package olander.hedvig;

import java.util.Scanner;

public class HumanPlayer extends Player{

	public HumanPlayer(String name) {
		super(name);
		
	}	
	
	public String chooseCoord() {
		boolean valid =false;
		boolean placeValid =false;
		String coord ="";
		while(!valid) {
			while(!placeValid) {
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter a coordinate :");
				coord = scanner.nextLine();
				if (validCoord(coord)) {
					if(this.getBoard().placeValid(coord)) {
						valid=true;
						placeValid=true;
					}else {
						System.out.println("Not a valid place");
					}
				}else {
					System.out.println("Not a valid coordinate");
				}
			}
		}
		return coord;
	}
	
	
	public String chooseCoordHit() {
		boolean valid =false;
		boolean placeValid =false;
		String coord ="";
		while(!valid) {
			while(!placeValid) {
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter a coordinate to hit :");
				coord = scanner.nextLine();
				if (validCoord(coord)) {
					if(this.getBoard().placeValid(coord)) {
						valid=true;
						placeValid=true;
					}else {
						System.out.println("Not a valid place");
					}
				}else {
					System.out.println("Not a valid coordinate");
				}
			}
		}
		return coord;
	}
	

	@Override
	public void addCoordinate(Coordinate coord) {
		
		
	}

	@Override
	public void clearListOfHitShips(Ship shipDelete) {
		
		
	}

}
