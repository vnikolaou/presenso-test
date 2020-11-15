package benchmark.matrix;

import java.util.Arrays;

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
			System.out.println(Arrays.deepToString(runner.getA()));
			System.out.println(Arrays.deepToString(runner.getB()));
			System.out.println(Arrays.deepToString(prod));	
		}
				
		long after = System.currentTimeMillis();
		System.out.println(runner.getClass().getName() + " : Execution took " + (after-before) + " msecs");		
	}
}