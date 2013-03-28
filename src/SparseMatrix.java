
public class SparseMatrix 
{
	//fields
	private node enter;
	private int rowCount, colCount;
		
	//Constructor: the new matrix should have rowCount rows and colCount columns.
	public SparseMatrix(int rowCount, int colCount)
	{ 
		enter = new node(-1);
		enter.setDown(enter);
		enter.setRight(enter);
		enter.setRow(-1); enter.setColumn(-1);
		this.rowCount = rowCount;
		this.colCount = colCount;
	}
	
	//Return the value at this position in the matrix. Return 0 if there is no node at this position.
	public int getValue(int row, int col)
	{
		node p = enter;					//pointer starts at enter
	
		while(p.getDown() != enter)
		{
			p = p.getDown();			//move pointer down
			if(p.getRow() == row)
			{
				//found row, traverse it
				node header = p;
				while(p.getRight() != header)
				{
					p = p.getRight();			//move pointer right
					if(p.getColumn() == col)
						return p.getValue(); 	//found value
				}
				//not in row ; ;
				return 0;
			}
		}
		//no node, since row ain't there
		return 0;
	}

	//Put value into the matrix. If there is already a node at this position overwrite the value stored there. 
	//If there is no node create one.	
	public void setValue(int value, int row, int col) 
	{

		node p = enter;  				//pointer starts at enter
		node header = null;				//temp var to hold current row/col header
		node newNode = null;			//var for the new node
		int skip = 0;				//variable to skip last 2 whiles
									//if a node was overriden
		
		//1st while loop will bring the iterator to the appropiate row header
		//or it will create a new row header
		while(header == null)		//iterate through rows until row is found
		{
			if(p.getRow() == row)			//found our row! set header
			{
				header = p;
			}
			else if(p.getDown() == enter)	//no row after, so insert new row header
			{
				p.setDown(new node(-1));
				header = p.getDown();
				header.setRight(header);
				header.setDown(enter);
				header.setRow(row); header.setColumn(-1);
			}
			else if(p.getDown().getRow() > row)  //row goes in between two others
			{
				node temp = p.getDown();
				p.setDown(new node(-1));
				header = p.getDown();
				header.setRight(header);
				header.setDown(temp);
				header.setRow(row); header.setColumn(-1);
			}
			else
				p = p.getDown();			//move pointer down
		}
		
		p = header;
		//got row header, traverse right until right col
		//p is header at start if it gets here
		//2nd while loop will traverse the row and input the node
		while(header != null)	
		{
			//System.out.println(header.getRight().getValue());
			if(p.getColumn() == col)
			//node already exists
			{					
				p.setValue(value);		//override previous value
				skip = 1;				//will skip next 2 whiles
				header = null;
			}
			else if(p.getRight() == header)
			//insert at the end of the row
			{
				//System.out.println("2nd if");

				p.setRight(new node(value));
				newNode = p.getRight();
				newNode.setRight(header);
				newNode.setColumn(col); newNode.setRow(row);
				header = null;
			}
			else if(p.getRight().getColumn() > col)
			//node is inserted between two others, adjust their pointers
			{
				//System.out.println("3rd if");

				node temp = p.getRight();
				p.setRight(new node(value));
				newNode = p.getRight();
				newNode.setRight(temp);
				newNode.setColumn(col); newNode.setRow(row);
				header = null;
			}
			else
				p = p.getRight();		//move pointer right
				
		}
		
		p = enter;			//a new node has been placed,	
							//reset p to enter, so we can traverse col headers
							//which are to the right of enter
		
		//precondition for 3rd while loop is that
		//newNode has a value which we found previously and p is reset
		//Still need to find the column header traverse it for the appropiate pointer	
		while(header == null && skip != 1)
		{	
			if(p.getColumn() == col)		//found our column!
			{
				header = p;
			}
			else if(p.getRight() == enter)		//no column after, insert one
			{				
				p.setRight(new node(-1));
				header = p.getRight();
				header.setColumn(col); header.setRow(-1);
				header.setRight(enter);
				header.setDown(header);
			}
			else if(p.getRight().getColumn() > col)	//new col in between existing ones
			{				
				node temp = p.getRight();
				p.setRight(new node(-1));
				header = p.getRight();
				header.setDown(header);
				header.setRight(temp);
				header.setColumn(col); header.setRow(-1);
			}
			else
				p = p.getRight();
		}
		p = header;
		//got the column header!
		//the 4th loop will traverse the column until u find the right row number
		while(header != null && skip != 1)
		{
			if(p.getDown() == header)
			{
				p.setDown(newNode);		//set pointer to our previously created node
				newNode.setDown(header);
				header = null;
				//since we now have pointers to the newNode, we don't need the variable
				newNode = null;
			}
			else if(p.getDown().getRow() > row)
			{
				node temp = p.getDown();
				p.setDown(newNode);
				newNode.setDown(temp);
				header = null;
				//since we now have pointers to the newNode, we don't need the variable
				newNode = null;
			}
			else 
				p = p.getDown();
		}
	}//END SET VALUE METHOD....PHEW
	
	//Display the values from positions represented by nodes. 
	//Displayed values are in "short form": (row, col) : value. See sample above.
	//iterate down row header, using printRowValues for each row
	public void printShort() 
	{
		node p = enter;		//pointer
		while(p.getDown() != enter)
		{
			p = p.getDown();
			printRowValues(p.getRow());
		}
	}

	//Display, in short form, values represented by nodes from one row.
	//(row, col): value
	public void printRowValues(int rowNumber) 
	{
		node p = enter;		//pointer
		node header = null;	//header for row
		
		//1st traverse to down to find row
		//than traverse the row and print values
		while(header == null)
		{
			p = p.getDown();
			if(p.getRow() == rowNumber)
			{
				header = p;
				while(p.getRight() != header)
				{
					p = p.getRight();
					System.out.print("(" + p.getRow() + ", " + p.getColumn() + "):  " + p.getValue() + " ");
				}
				System.out.print("\n");
			}
			else if(p == enter)
			{
				System.out.println("Row doesn't exist :( ");
				break;
			}
		}

	}
	
	//Display, in short form, values represented by nodes from one column.
	public void printColValues(int colNumber) 
	{
		node p = enter;		//pointer
		node header = null;	//header for row
		
		//traverse right to find column
		//than traverse col and print values
		while(header == null)
		{
			p = p.getRight();
			if(p.getColumn() == colNumber)
			{
				header = p;
				while(p.getDown() != header)
				{
					p = p.getDown();
					System.out.println("(" + p.getRow() + ", " + p.getColumn() + "):\t" + p.getValue());
				}
			}
			else if(p == enter)
			{
				System.out.println("Column doesn't exist :( ");
				break;
			}
		}
	}
	
	//Display the entire matrix with a dot for positions not represented by a node. 
	//The display should be arranged in rows and columns with row labels and column heads. 
	//The values will be printed right justified with a field width of 6 (see below). 
	//This method will only be of practical use for matrices with a small number of columns
	public void printLong() 
	{
		//use getValue to grab values of nodes for printing and
		//if getValue gives a 0, print a dot "."
		//double for loops for indexes to check
		
/*		You will display matrix values (from printLong()) using the printf() method inherited from C. 
 * 		To display an integer val right justified in a field width of six use:
 *			System.out.printf("%6d", val)
 *		To display a dot right justified in a field width of six use:
 *			System.out.printf("%6c", '.')
*/
		System.out.println("Welcome to printLong method");
		System.out.printf("%6c", ' ');
		for(int j=0; j<colCount; j++)		//print col headers
			System.out.printf("%6d", j);
		
		for(int i = 0; i < rowCount; i++)	//print values or '.'s
		{
			System.out.println(" ");
			System.out.printf("%6d", i);
			for(int j = 0; j < colCount; j++)
			{
				int value = getValue(i,j);
				if(value == 0)
					System.out.printf("%6c", '.');
				else
					System.out.printf("%6d", value);
			}
		}
		System.out.println("");
	}
	
	//Add matrix this and matrix m returning the sum matrix.
	public SparseMatrix add(SparseMatrix b) 
	{
		//c will represent the resulting matrix
		SparseMatrix c = new SparseMatrix(rowCount, colCount);
		for(int i=0; i<rowCount; i++)
		{
			for(int j=0; j<colCount; j++)
			{
				int output = getValue(i,j) + b.getValue(i, j);
				//if result for node is 0 or blank, don't create a node
				if(output != 0)
					c.setValue(output, i, j);  //construct node in new matrix 
			}
		}
		return c; 
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColCount() {
		return colCount;
	}
	
}
