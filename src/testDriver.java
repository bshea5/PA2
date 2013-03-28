import java.util.*;

public class testDriver {

	public static void main(String[] args) {
		
		//construct matrices
		
		int rowCount = 10; int colCount = 8;
		SparseMatrix a = new SparseMatrix(rowCount, colCount);
		SparseMatrix b = new SparseMatrix(rowCount, colCount);
		SparseMatrix c = new SparseMatrix(rowCount, colCount);
		
//SAMPLE MENU		
//		   Set value:  v      Show row:     r
//		   Get value:  g      Show column:  c
//		   Show short: s      Add matrices: a
//		   Show long:  l      Quit q:       q
		
		char menu = 'z'; SparseMatrix matrix;
		while(menu != 'q')
		{
			System.out.println("====MENU====");
			System.out.print("Set value:\tv\t"); System.out.println("Show row:\tr");
			System.out.print("Get value:\tg\t"); System.out.println("Show column:\tc");
			System.out.print("Show short:\ts\t"); System.out.println("Add matrices:\ta");
			System.out.print("Show long:\tl\t"); System.out.println("Quit q:\t\tq");
			
			Scanner sc = new Scanner(System.in);
			matrix = null;
			
			try{
			
			InvalidSelection inval = new InvalidSelection();
			OutOfMatrixBounds outOfBounds = new OutOfMatrixBounds(rowCount, colCount);
	
			menu = (sc.next()).charAt(0);
			
			//Note: getMatrix is a function to determine which matrix
			//		the user wishes to manipulate
			
			if(menu == 'v')  	//set value option
			{
				matrix = getMatrix(a,b,c);
				//get values and location from user
				System.out.print("Row Number: ");
				int row = sc.nextInt();
				System.out.print("\nColumn Number: ");
				int col = sc.nextInt();
				
				if( row >= rowCount || col >= colCount)
					throw(outOfBounds);
				
				System.out.print("\nInt Value: ");
				int val = sc.nextInt();
				System.out.println("");
				matrix.setValue(val, row, col);
			}
			else if(menu == 'r')		//show values in given row
			{
				matrix = getMatrix(a,b,c);
				System.out.println("Row Number: ");
				int row = sc.nextInt();
				
				if(row >= rowCount)
					throw(outOfBounds);
				
				System.out.println("");
				matrix.printRowValues(row);
			}
			else if(menu == 'l')		//Show long option
			{
				matrix = getMatrix(a,b,c);
				matrix.printLong();
			}
			else if(menu == 'g')		//get value at a given index
			{
				matrix = getMatrix(a,b,c);
				System.out.print("Row Number: ");
				int row = sc.nextInt();
				System.out.print("\nColumn Number: ");
				int col = sc.nextInt();
				
				if( row >= rowCount || col >= colCount)
					throw(outOfBounds);
				
				System.out.println("Searching...");
				System.out.println("Int Value:\t " + matrix.getValue(row, col));
			}
			else if(menu == 'c')		//show values in given column
			{
				matrix = getMatrix(a,b,c);
				System.out.print("Column Number: ");
				int col = sc.nextInt();
				
				if(col >= colCount)
					throw(outOfBounds);
				
				System.out.println("");
				matrix.printColValues(col);
			}
			else if(menu == 's')		//show short form
			{
				matrix = getMatrix(a,b,c);
				matrix.printShort();
			}
			else if(menu == 'a')		//add two matrices and printLong
			{
				System.out.println("Select two matrices to add.");
				SparseMatrix matrixOne = getMatrix(a,b,c);
				SparseMatrix matrixTwo = getMatrix(a,b,c);
				//check to see if addition is possible
				if(matrixOne.getRowCount() == matrixTwo.getRowCount() &&
						matrixOne.getColCount() == matrixTwo.getColCount())
				{
					//add'em up!
					matrix = matrixOne.add(matrixTwo);	//result
					System.out.println("Resulting Matrix: ");
					matrix.printLong();					//print
					
					c = matrix;  //saves resulting matrix to matrix c
								//overrides old matrix c
				}
				else
					System.out.println("Invalid Addition");
			}
			else if(menu == 'q')
				System.out.println("Goodbye!");
			else
			{
				//System.out.println("Invalid Selection :( ");
				throw(inval);
			}
			
			}
			catch(InvalidSelection e) {
				System.out.println(e);
			}
			catch(OutOfMatrixBounds e) {
				System.out.println(e);
			}
			catch(InputMismatchException e) {
				System.out.println("Integer Input Please");
			}
		}
		
	}
	
	//method to figure out which matrix the user wishes to use
	public static SparseMatrix getMatrix(SparseMatrix a, SparseMatrix b, SparseMatrix c)
	{
		SparseMatrix matrix = null;
		char select = 'z';
		Scanner sc = new Scanner(System.in);
		
		//try{
		
		while(matrix == null)
		{
			System.out.println("Select Matrix(a,b,c)");
			
			try{
				
			select = (sc.next()).charAt(0);
			
			if(select == 'a')
				matrix = a;
			else if(select == 'b')
				matrix = b;
			else if(select == 'c')
				matrix = c;
			else
			{
				InvalidSelection inval = new InvalidSelection();
				throw(inval);
				//System.out.println("invalid matrix");
			}
			
			}
			catch(InvalidSelection e) {
				System.out.println(e);
			}
		}
		
		return matrix;

	}

}
