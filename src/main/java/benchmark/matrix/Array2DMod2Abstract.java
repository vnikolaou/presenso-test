package benchmark.matrix;

import java.util.Random;

public abstract class Array2DMod2Abstract {
	protected int size;
	protected boolean init;
	protected int[][] a;
	protected int[][] b;
	
	protected Array2DMod2Abstract(int _size, boolean _init) {
		if(_init) { // if it is true, create random arrays
			this.a = makeRandomArray(_size);
			this.b = makeRandomArray(_size);
		}
		
		this.size = _size;
		this.init = _init;
	}
	
	private int[][] makeRandomArray(int size) {
		Random rnd = new Random();
		
		int[][] ar = new int[size][size];
		for(int i=0; i < size; i++) {
			for(int j=0; j < size; j++) {
				ar[i][j] = (int)rnd.nextInt(2);
			}
		}
		return ar;
	}
	
	protected int[][] transformB() {
		int[][] tb = new int[this.size][this.size];
		
		for(int i=0; i < this.size; i++) {
			for(int j=0; j < this.size; j++) {
				tb[i][j] = this.b[j][i];
			}			
		}
		
		return tb;
	}

	public int[][] getA() {
		return a;
	}

	public void setA(int[][] _a) {
		if(this.init) {
			throw new UnsupportedOperationException("Setter cannot be used.");
		}
		this.a = _a;
	}

	public int[][] getB() {
		return b;
	}

	public void setB(int[][] _b) {
		if(this.init) {
			throw new UnsupportedOperationException("Setter cannot be used.");
		}		
		this.b = _b;
	}

	public boolean isInit() {
		return this.init;
	}
	
	
}
