
public class OutOfMatrixBounds extends Exception {

	int row , col;
	
	public OutOfMatrixBounds(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public String toString(){
		return "Input is not within the Matrix bounds of (" + row + ", " + col + ")\n" +
				"bounds start at 0 ";
	}
}
