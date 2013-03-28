//node class will hold an int value and pointers to the
//nodes to the right and below it
public class node 
{
	//fields
	private int value, row, column;
	private node right;
	private node down;
	
	//constructor
	public node(int n)
	{
		this.value = n;
	}
	//getters and setters
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public node getRight() {
		return right;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public void setRight(node right) {
		this.right = right;
	}
	public node getDown() {
		return down;
	}
	public void setDown(node down) {
		this.down = down;
	}
}
