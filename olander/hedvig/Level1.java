package olander.hedvig;

import java.util.ArrayList;

public class Level1 extends Level0{
	
	private ArrayList<Coordinate>listOfHit; //not to hit the same coordinate twice

	public Level1(String name) {
		super(name);
		this.listOfHit = new ArrayList<>();

	}

	public ArrayList<Coordinate> getListOfHit() {
		return listOfHit;
	}

	public void setListOfHit(ArrayList<Coordinate> listOfHit) {
		this.listOfHit = listOfHit;
	}
	
	@Override
	public void addCoordinate(Coordinate coord) {
		this.listOfHit.add(coord);
		
	}
	public void empty(){
		super.empty();
		this.listOfHit.clear();
	}

	public String chooseCoordHit() {
		boolean valid =false;
		boolean placeValid =false;

		String hitCoord = "";

		while(!valid) {
			while(!placeValid) {
				boolean again = false;
				hitCoord = chooseCoord();					
				if (this.getListOfHit().isEmpty()) {
						again = false;		
				}else {
					for (int i=0; i<this.getListOfHit().size(); i++) {
						if (hitCoord.equals(this.getListOfHit().get(i).getCoordinate())) {
							again = true;
						}
					}
				}
					
				if(!again) {
					if (validCoord(hitCoord)) {
						if(this.getBoard().placeValid(hitCoord)) {
							valid=true;
							placeValid=true;
							Coordinate coord = new Coordinate(hitCoord);
							this.getListOfHit().add(coord);
						}
					}
				}

					
			}
		}
		return hitCoord;
	}
}
