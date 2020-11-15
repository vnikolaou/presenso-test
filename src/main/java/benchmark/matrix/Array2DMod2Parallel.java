package benchmark.matrix;

/**
 * Handles the partially parallel process of multiplying two 2D arrays of dynamic size. 
 * @author vnik
 */
import java.util.Arrays;

import org.javatuples.Pair;

import com.google.common.collect.Streams;

public class Array2DMod2Parallel extends Array2DMod2Abstract implements Array2DMod2 {

	public Array2DMod2Parallel(int size, boolean init) {
		super(size, init);
	}
	
	@Override
	public int[][] multiply() {
		int[][] tb = this.transformB(); // transform "b" array, ie. columns become rows
		
		int[][] r = new int[this.size][this.size];
		
		Streams.mapWithIndex(Arrays.stream(this.a).parallel(), 
				(row, index) -> new Pair<Long, int[]>(index, row)).forEach(rowa -> {

					/* NOTE: here we mix parallel with sequential process. The parallel 
					 	applied only on streaming the rows from array "a" but preserving the index of every row (so as it can be used in the end) with the help of guava's mapWithIndex() method. 
					 	For repeatedly accessing the columns of "tb" array (the transformed "b" ie. with columns to rows) and every row from "a"
					 	using the sequential way gives a huge boost in comparison with the fully parallel process over streams.
					 	The reason is that preserving indexes for every stream element is a very costly function and introduces big overhead as a whole.
					 	However further enhancements might be applied with a help of the JMH and by executing benchmarks over different combinations of partial solutions.
					 */
					for(int j=0; j < this.size; j++) { // iterate through rows in array "b"
						int[] rowtb = tb[j]; // get a "tb" row

						int i = rowa.getValue0().intValue();

						for(int k=0; k < this.size; k++) { // iterate through "a" row's values
							int oval = rowa.getValue1()[k]; // take once the value of "a" row at k position
							if(oval == 0) continue; 
							if(oval == rowtb[k]) { // if the current "a" value and "tb" value matches (both are 1)
								r[i][j] = 1;
								break;
							}	
						}	
					}
				});
		
		return r;
	}	

	/*
	@Override
	public int[][] multiply() {
		int[][] tb = this.transformB(); // transform "b" array, ie. columns become rows
		
		int[][] r = new int[this.size][this.size];
		
		Streams.mapWithIndex(Arrays.stream(this.a).parallel(), 
				(row, index) -> new Pair<Long, int[]>(index, row)).forEach(rowa -> {
			Streams.mapWithIndex(Arrays.stream(tb).parallel(), 
					(row, index) -> new Pair<Long, int[]>(index, row)).forEach(rowtb -> {
						Streams.mapWithIndex(Arrays.stream(rowa.getValue1()).parallel(), 
								(row, index) -> new Pair<Long, Integer>(index, row)).forEach( oval -> {
							int k = oval.getValue0().intValue();
							int val = oval.getValue1();
							if(val == 0) return; 
							int tbval = rowtb.getValue1()[k];
							if(val == tbval) { // if the current "a" value and "tb" value matches (both are 1)
								int i = rowa.getValue0().intValue();
								int j = rowtb.getValue0().intValue();
								r[i][j] = 1;
							}					
						});
			});
		});
		
		return r;
	}*/
	
	@Override
	public int[][] add() {
		throw new UnsupportedOperationException("Add operation is not implemented yet");
	}
}
