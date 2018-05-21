package olander.hedvig;

import java.util.ArrayList;

public class Level2 extends Level1{
	
	private ArrayList<Coordinate>listOfHit; //All the hit you have made
	private ArrayList<Coordinate>listOfHitShips; //All the hits concerning a ship
	
	public Level2(String name) {
		super(name);
		listOfHit = new ArrayList<>();
		listOfHitShips = new ArrayList<>();
	}

	public ArrayList<Coordinate> getListOfHit() {
		return listOfHit;
	}

	public void setListOfHit(ArrayList<Coordinate> listOfHit) {
		this.listOfHit = listOfHit;
	}

	public ArrayList<Coordinate> getListOfHitShips() {
		return listOfHitShips;
	}

	public void setListOfHitShips(ArrayList<Coordinate> listOfHitShips) {
		this.listOfHitShips = listOfHitShips;
	}
	
	@Override
	public void addCoordinate(Coordinate coord) {
		this.listOfHitShips.add(coord);
		
	}
	
	public Coordinate createCoordinate(char c, int n, String string) {
		if(string.equals("up")) {
			if(n!=1) {
				n=n-1;
			}
		}else if(string.equals("down")) {
			if(n!=10) {
				n=n+1;
			}	
		}else if(string.equals("right")) {
			if(c!='J') {
				c=(char) (c+1);
			}	
		}else if(string.equals("left")) {
			if(c!='A') {
				c=(char) (c-1);
			}	
		}
		
		String hit = Character.toString(c) + Integer.toString(n);
		return new Coordinate(hit);
	}
	
	public void empty(){
		super.empty();
		this.listOfHit.clear();
		this.listOfHitShips.clear();
	}
	
	public void clearListOfHitShips(Ship shipDelete) {	
		for (int j=0; j<shipDelete.getShipCoords().size(); j++) {
			for (int i= 0; i<getListOfHitShips().size(); i++) {
				if(getListOfHitShips().get(i).getCoordinate().equals(shipDelete.getShipCoords().get(j).getCoordinate())) {
					getListOfHitShips().remove(i);
				}
			}

		}
	}
	

	public String chooseCoordHit() {
		boolean valid =false;
		boolean placeValid =false;

		String hitCoord = "";
	
		
		while(!valid) {
			while(!placeValid) {
				if(this.getListOfHitShips().isEmpty()) {
					hitCoord = chooseCoord();
					boolean exist = false;
					boolean again = true;
					while(again) {
						for (int i=0; i<this.getListOfHit().size(); i++) {
							if (hitCoord.equals(this.getListOfHit().get(i).getCoordinate())) {
								exist = true;
							}
						}
						if(exist) {
							again= true;
							hitCoord = chooseCoord();
							exist=false;
						}else {
							again = false;
						}
					}
					
				}else {
					int j =0;
					boolean stop =false;
					while (j< this.getListOfHitShips().size() && stop==false) {
						
						
						Coordinate coord = this.getListOfHitShips().get(j);
						char c = coord.getCoordOne();
						int n = coord.getCoordTwo();
						boolean upBool=true;
						boolean downBool=true;
						boolean rightBool=true;
						boolean leftBool=true;
						
						Coordinate up = createCoordinate(c, n, "up");
						Coordinate down = createCoordinate(c, n, "down");
						Coordinate left = createCoordinate(c, n, "left");
						Coordinate right = createCoordinate(c, n, "right");
						
						for (int i=0; i<this.getListOfHit().size(); i++) {
							if (up.getCoordinate().equals(this.getListOfHit().get(i).getCoordinate())) {
								upBool=false;
							}else if(down.getCoordinate().equals(this.getListOfHit().get(i).getCoordinate())) {
								downBool=false;	
							}else if(right.getCoordinate().equals(this.getListOfHit().get(i).getCoordinate())) {
								rightBool=false;
							}else if(left.getCoordinate().equals(this.getListOfHit().get(i).getCoordinate())) {
								leftBool=false;
							}
						}
						
						if(upBool) {
							hitCoord=up.getCoordinate();
							stop=true;
						}else if(downBool) {
							hitCoord = down.getCoordinate();
							stop=true;
						}else if(rightBool) {
							hitCoord =right.getCoordinate();
							stop=true;
						}else if(leftBool) {
							hitCoord =left.getCoordinate();
							stop=true;
						}
						j++;
					}
					if(j==this.getListOfHitShips().size() && stop==false) {
						hitCoord = chooseCoord();
						boolean exist = false;
						boolean again = true;
						while(again) {
							for (int i=0; i<this.getListOfHit().size(); i++) {
								if (hitCoord.equals(this.getListOfHit().get(i).getCoordinate())) {
									exist = true;
								}
							}
							if(exist) {
								again= true;
								hitCoord = chooseCoord();
								exist=false;
							}else {
								again = false;
							}
						}
					}
				}
						
				if (validCoord(hitCoord)) {
					if(this.getBoard().placeValid(hitCoord)) {
						valid=true;
						placeValid=true;
						Coordinate coordHit = new Coordinate(hitCoord);
						this.getListOfHit().add(coordHit);
					}
				}
			}
		}
		return hitCoord;
	}
	
}
