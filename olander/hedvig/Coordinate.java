package olander.hedvig;



public class Coordinate {
	
	private char coordOne;
	private int coordTwo;
	private boolean hit;
	
	public Coordinate(String coord) {
		if (coord.length()<3) {
			this.coordOne = coord.charAt(0);
			this.coordTwo = Character.getNumericValue(coord.charAt(1));
		}else if (coord.length()==3) {
			this.coordOne = coord.charAt(0);
			String pTwo = Character.toString(coord.charAt(1)); 
			String pExtra = Character.toString(coord.charAt(2));
			this.coordTwo = Integer.parseInt(pTwo + pExtra);
		}
		this. hit = false;
	}

	public char getCoordOne() {
		return coordOne;
	}

	public void setCoordOne(char coordOne) {
		this.coordOne = coordOne;
	}

	public int getCoordTwo() {
		return coordTwo;
	}

	public void setCoordTwo(int coordTwo) {
		this.coordTwo = coordTwo;
	}

	public boolean getHit() {
		return hit;
	}

	public void setHit() {
		this.hit=true;
	}
	
	public String getCoordinate() {
		return Character.toString(this.getCoordOne()) + Integer.toString(this.getCoordTwo());
	}
	
}
