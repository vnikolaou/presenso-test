package benchmark.matrix;

/**
 * Base interface for concrete implementations for operations over 2D arrays.
 * @author vnik
 */
public interface Array2DMod2 {
	public int[][] add();
	public int[][] multiply();
	
	public void setA(int[][] _a);
	public void setB(int[][] _b);
	public int[][] getA();
	public int[][] getB();	
	public boolean isInit();	
}
