package olander.hedvig;
public class Board {
	private int rows =10;
	private int cols =10;
	private String [][] board;

	public Board() {
		this.board = new String[rows][cols];	
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				this.board[i][j] = "-️";
			}
		}
	}
	
	
	//- water
	//X hit/ship
	//S ship when adding ships to battleship
	
	public String[][] getBoard() {
		return board;
	}
	
	public void setBoard(int a, int b, String type) {
		if (board[b][a] != "X") {
			board[b][a] = type;
		}
	}

	//check if the coordinates are diagonal
	public boolean isDiagonal(String startCoord, String endCoord) {
		if (startCoord.charAt(0)==(endCoord.charAt(0))) {
			return false;
		}else if (startCoord.charAt(1)==(endCoord.charAt(1))) {
			return false;
		}else {
			return true;
		}
	}
	
	public void emptyBoard() {	
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				this.board[i][j] = "-️";
			}
		}
	}
	
	//check if the coordinate is valid
	public boolean placeValid(String coord){
		boolean coordOk = false;
		String pOne;
		String pTwo;

		pOne = Character.toString(coord.charAt(0)); 
		pTwo = Character.toString(coord.charAt(1)); 

		if (coord.length() == 3) {
			String pExtra;
			pExtra = Character.toString(coord.charAt(2));
			pTwo = pTwo + pExtra;
		}
		boolean okOne = false;
		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		String e = "E";
		String f = "F";
		String g = "G";
		String h = "H";
		String i = "I";
		String j = "J";
		
		if (pOne.equals(a) || pOne.equals(b)||pOne.equals(c) ||pOne.equals(d) ||pOne.equals(e) ||pOne.equals(f) ||pOne.equals(g) ||pOne.equals(h) ||pOne.equals(i) ||pOne.equals(j)){
			okOne=true;
		}
		int value = Integer.parseInt(pTwo);
		boolean okTwo=false;
		if (value >0 && value <=10 ) {
			okTwo=true;
		}
		if (okOne && okTwo) {
			coordOk = true;
		}
		return coordOk;
	}	
   
	public String toString() {
		StringBuilder sbResult = new StringBuilder();
		sbResult.append(" 	A	B	C	D	E	F	G	H	I	J");
		sbResult.append("\n");
	    for(int i = 0; i < board.length;i++) {
	    	sbResult.append(i+1);
	    	sbResult.append("\t");
	        for(int j = 0; j < board[i].length;j++){
	             sbResult.append(board[i][j]);
	             sbResult.append("\t");

	        }
	        sbResult.append("\n");
	    }

	    return sbResult.toString();
	}
}
