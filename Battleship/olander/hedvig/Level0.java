package olander.hedvig;

import java.util.Random;

public class Level0 extends Player {

	public Level0(String name) {
		super(name);
		
	}
	
	public String chooseCoord() {
		boolean valid =false;
		boolean placeValid =false;
		String coord ="";
		while(!valid) {
			
			while(!placeValid) {
				Random rand = new Random(); 
				int value = rand.nextInt(10)+1;
				Random r = new Random();
				char c = (char) (r.nextInt(10) + 'A');
				coord = Character.toString(c)+Integer.toString(value);
				
				if (validCoord(coord)) {
					if(this.getBoard().placeValid(coord)) {
						valid=true;
						placeValid=true;
					}
				}
			}
		}
		return coord;
		
	}
	
	public void empty(){
		super.empty();
	}
	
	public String chooseCoordHit() {
		boolean valid =false;
		boolean placeValid =false;
		String coord ="";
		while(!valid) {
			while(!placeValid) {
				Random rand = new Random(); 
				int value = rand.nextInt(10)+1;
				Random r = new Random();
				char c = (char) (r.nextInt(10) + 'A');
				coord = Character.toString(c)+Integer.toString(value);
				
				if (validCoord(coord)) {
					if(this.getBoard().placeValid(coord)) {
						valid=true;
						placeValid=true;
					}
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
