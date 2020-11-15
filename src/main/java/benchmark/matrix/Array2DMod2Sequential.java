package benchmark.matrix;

/**
 * Handles the sequential process of multiplying two 2D arrays of dynamic size. 
 * @author vnik
 */
public class Array2DMod2Sequential extends Array2DMod2Abstract implements Array2DMod2 {

	public Array2DMod2Sequential(int size, boolean init) {
		super(size, init);
	}
	
	@Override
	public int[][] multiply() {
		int[][] tb = this.transformB(); // transform "b" array, ie. columns become rows
		
		int[][] r = new int[this.size][this.size];
		
		for(int i=0; i < this.size; i++) { // iterate through rows in array "a"
			int[] rowa = this.a[i]; // get an "a" row
			
			for(int j=0; j < this.size; j++) { // iterate through rows in array "b"
				int[] rowtb = tb[j]; // get a "tb" row

				for(int k=0; k < this.size; k++) { // iterate through "a" row's values
					int oval = rowa[k]; // take once the value of "a" row at k position
					if(oval == 0) continue; 
					if(oval == rowtb[k]) { // if the current "a" value and "tb" value matches (both are 1)
						r[i][j] = 1;
						break;
					}	
				}	
			}
		}
		
		return r;
	}

	@Override
	public int[][] add() {
		throw new UnsupportedOperationException("Add operation is not implemented yet");
	}
}
