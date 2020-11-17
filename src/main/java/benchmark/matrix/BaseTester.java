package benchmark.matrix;

import java.util.Arrays;

/**
 * Simple tester that measures the performance for sequential and parallel process against 2D data.  
 * @author vnik
 */
public class BaseTester {
	private static final int[][] a = new int[][] { {0,1,0}, {1,0,1}, {0,1,1} };
	private static final int[][] b = new int[][] { {1,1,0}, {0,0,0}, {0,1,1} };
	
	public static void main(String... args) {
		int size = 0;
		
		if(args.length == 0) {
			System.out.println("You should provide a size argument");
			return;
		}
		
		try {
			size = Integer.parseInt(args[0]);
		} catch(RuntimeException ex) { }
		
		if(size == 0 || size > 10000) {
			System.out.println("You should provide a size in [1 .. 10000] range");
			return;
		}
		
		benchmark(new Array2DMod2Parallel(size, true), false);
		benchmark(new Array2DMod2Sequential(size, true), false);
	}
	
	private static <T extends Array2DMod2> void benchmark(T runner, boolean log) {
		long before = System.currentTimeMillis();
		
		if(!runner.isInit()) {
			runner.setA(a);
			runner.setB(b);
		}
		
		int[][] prod = runner.multiply();
		
		if(log) {
			log(runner.getA(), "A");
			log(runner.getB(), "B");
			log(prod, "C");				
		}
				
		long after = System.currentTimeMillis();
		System.out.println(runner.getClass().getName() + " : Execution took " + (after-before) + " msecs");		
	}
	
	private static void log(int[][] a, String id) {
		System.out.println("Array: " + id);
		for(int i=0; i < a.length; i++) {
			System.out.println(Arrays.toString(a[i]));
		}
	}
}
