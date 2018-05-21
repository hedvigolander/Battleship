package olander.hedvig;
import java.util.ArrayList;

public class Ship {
	private String name;
	private String startCoord;
	private String endCoord;
	private ArrayList<Coordinate> listCoord;

	public Ship(String startCoord, String endCoord) { 
		this.startCoord=startCoord;
		this.endCoord=endCoord;

		Coordinate sCoord = new Coordinate(startCoord);
		Coordinate eCoord = new Coordinate(endCoord);

		listCoord=new ArrayList<>();
			
		if (sCoord.getCoordOne() == (eCoord.getCoordOne())) {
			for (int i=sCoord.getCoordTwo(); i<=eCoord.getCoordTwo(); i++) {
				String newString = Character.toString(sCoord.getCoordOne()) + i;
				Coordinate newCoord = new Coordinate(newString);
				listCoord.add(newCoord);
			}
		}else if(sCoord.getCoordTwo() == (eCoord.getCoordTwo())) {
			for(char i=sCoord.getCoordOne(); i<=eCoord.getCoordOne(); i++) {
				String newString = (i) + Integer.toString(sCoord.getCoordTwo());
				Coordinate newCoord = new Coordinate(newString);
				listCoord.add(newCoord);
			}
		}
	} 
	
	public String getStartCoord() {
		return startCoord;
	}
	
	public String getEndCoord() {
		return endCoord;
	}
	
	public ArrayList<Coordinate> getShipCoords() {
		return listCoord;
	}
	
	public int getSize() {
		return listCoord.size();
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isHit(String Missilecoord) {
		boolean hit=false;
		for (int i=0; i<listCoord.size(); i++) {
			if (listCoord.get(i).getCoordinate().equals(Missilecoord)){
				hit=true;
				listCoord.get(i).setHit();
			}	
		}
		return hit;
	}
	
	public boolean isDestroyed() {
		int counter=0;
		for (int i=0; i<listCoord.size(); i++) {
			if (listCoord.get(i).getHit()==true) {
				counter++;
			}
		}
		return counter == this.getSize();	
	}
	
}
