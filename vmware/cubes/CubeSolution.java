/**
 * @author ShengMin Zhang
 * @notes 
 * My coding style is different when coding for an actual application. 
 * For example, I would put a class inside a package, handle exceptions, proper encapsulation and etc. 
 * Here as well as in any other algorithm contests, I prefer simplicity and less typing. :P
 *
 * @revison 1.0
 * - Input size is small enough that I can try all possibilities
 * 
 * Estimated time: 
 * less than 1s because each cube has at most 2*3*4 = 24 possible arrangments, there are 4 cubes, so 4 * 24 = 96 combinations
 * there are 4! ways to stack those 4 cubes for any given combination, so 4! * 96 = 2304 possibilities to consider, which is much smaller than 10^7, so should be able to complete in <1s
 * 
 * Actual time: less than 2s probably because of printing, duplicate checking, copying, and initialization of enum set
 *
 */

import java.io.*;
import java.util.*;

enum Side {
	FRONT,
	LEFT,
	BACK,
	RIGHT,
	TOP,
	BOTTOM;
}

enum Color {
	B,
	R,
	G,
	Y;
}

class Cube {
	final Color[] colors;
	final Color[] originalColors;
	final int id;
	
	/**
	 * FRONT, LEFT, BACK, RIGHT, TOP, BOTTOM in that order
	 */
	Cube(int id, Color[] colors) {
		this.id = id;
		this.originalColors = colors;
		this.colors = new Color[colors.length];
		reset();
	}
	
	/**
	 * reset the colors to original colors
	 */
	Cube reset() {
		for(int i = colors.length - 1; i >= 0; i--){
			colors[i] = originalColors[i];
		}	
		return this;
	}
	
	void swap(Side a, Side b){
		Color colorA = colors[a.ordinal()];
		Color colorB = colors[b.ordinal()];
		colors[a.ordinal()] = colorB;
		colors[b.ordinal()] = colorA;
	}
	
	/**
	 * Rotate the cube around Y-axis clockwise
	 */
	Cube rotateLeft(){
		Color first = colors[0];
		for(int i = 0; i < 3; i++) {
			colors[i] = colors[i+1];
		}
		colors[3] = first;
		return this;
	}
	
	/**
	 * Turn the cube upside down
	 */
	Cube flip(){
		swap(Side.TOP, Side.BOTTOM);
		swap(Side.FRONT, Side.BACK);
		return this;
	}
	
	/**
	 * Rotate the original cube so that the side is the top side
	 */
	Cube setTop(Side side) {
		switch(side) {
			case LEFT:
			case RIGHT:
				// will do flip, so no need to seperate those two cases
				swap(Side.TOP, Side.LEFT);
				swap(Side.LEFT, Side.BOTTOM);
				swap(Side.BOTTOM, Side.RIGHT);
				break;
			case FRONT:
			case BACK:
				swap(Side.TOP, Side.FRONT);
				swap(Side.FRONT, Side.BOTTOM);
				swap(Side.BOTTOM, Side.BACK);
				break;
		}
		return this;
	}
	
	void print(PrintWriter pw){
		pw.printf("Cube %d :", id);
		for(int i = 0; i < colors.length; i++){
			for(int j = 0; j < 7; j++) pw.print(' ');
			pw.print(colors[i]);
		}
		pw.println();
	}
}

public class CubeSolution {
	static final Side[] TOPS = new Side[]{ Side.TOP, Side.LEFT, Side.FRONT };
	PrintWriter pw;

	Cube[] originalCubes = new Cube[] {
		new Cube(1, new Color[]{ Color.R, Color.G, Color.B, Color.Y, Color.B, Color.Y }),
		new Cube(2, new Color[]{ Color.R, Color.G, Color.G, Color.Y, Color.B, Color.B }),
		new Cube(3, new Color[]{ Color.Y, Color.R, Color.B, Color.G, Color.Y, Color.R }),
		new Cube(4, new Color[]{ Color.Y, Color.B, Color.G, Color.R, Color.R, Color.R })
	};
	
	Cube[] cubes = new Cube[originalCubes.length];
	int[] indices = new int[originalCubes.length];
	boolean[] usedIndex = new boolean[originalCubes.length];
	
	void print() {
		pw.println("--------------------------------------------------------------------------------");
		for(Cube cube: cubes){
			cube.print(pw);
		}
		pw.println("--------------------------------------------------------------------------------");
		pw.println();
	}
	
	boolean check() {
		for(int j = 0; j < 4; j++) {
			boolean[] seen = new boolean[4];
			for(int i = cubes.length - 1; i >= 0; i--) {
				Color color = cubes[i].colors[j];
				if(seen[color.ordinal()]) {
					return false;
				} else {
					seen[color.ordinal()] = true;
				}
			}
		}
		
		return true;
	}
	
	void permute(int level) {
		if(level == cubes.length) {
			rotateCubes(0);
			return;
		}
		
		for(int i = 0; i < cubes.length; i++) {
			if(usedIndex[i]) {
				continue;
			}
			
			indices[level] = i;
			usedIndex[i] = true;
			permute(level+1);
			usedIndex[i] = false;
		}
	}
	
	void rotateCubes(int level) {
		if(level == originalCubes.length) {
			for(int i = 0; i < indices.length; i++){
				cubes[i] = originalCubes[indices[i]];
			}
			if(check()) {
				print();
			}
			return;
		}
			
		Cube cube = originalCubes[level];
		for(int i = 0; i < TOPS.length; i++) {
			cube.reset();
			cube.setTop(TOPS[i]);
			for(int flip = 0; flip < 2; flip++) {
				if(flip != 0) cube.flip();
				for(int j = 0; j < 4; j++) {
					cube.rotateLeft();
					rotateCubes(level+1);
				}
			}
		}
	}
	
	void run() throws Exception {
		// initial set up
		pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		permute(0);
		pw.close();
	}

	public static void main(String[] args) throws Exception {
		new CubeSolution().run();
	}
}
