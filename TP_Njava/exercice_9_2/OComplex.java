package exercice_9_2;


public class OComplex {
	
	private int x;
	private int y;
	
	public OComplex(int newX, int newY) {
		x = newX;
		y = newY;
	}

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public static OComplex add(OComplex z1, OComplex z2) {
		return new OComplex(z1.x + z2.x, z1.y + z2.y);
	}
	
	public OComplex add( OComplex z) {
		return new OComplex(this.x + z.x, z.y + this.y);
	}
	
	public static OComplex mul(OComplex z1, OComplex z2) {
		// z1 * z2 = (ac - bd) + (ad + bc)i
		int x= z1.x * z2.x - z1.y * z2.y ;
		int y= z1.x * z2.y + z1.y * z2.x;
		return new OComplex(x, y);
	}
	
	public OComplex mul(OComplex z) {
		// z1 * z2 = (ac - bd) + (ad + bc)i
		int newX = this.x * z.x - this.y * z.y ;
		int newY = this.x * z.y + this.y * z.x;
		return new OComplex(newX, newY);
	}
	
	public static String toString(OComplex z) {
		String str;
		if (z.y == 0) {
			str ="" + z.x;
		} else if (z.x == 0 && z.y == 1) {
			str = "i";
		} else if (z.x == 0) {
			str = z.y + ".i";
		} else if (z.y == 1) {
			str = z.x + " + i" ;
		} else{
			str = z.x + " + " + z.y + ".i";
		}
		return str;
	}
	
	public String toString() {
		String str;
		if (this.y == 0) {
			str ="" + this.x;
		} else if (this.x == 0 && this.y == 1) {
			str = "i";
		} else if (this.x == 0) {
			str = this.y + ".i";
		} else if (this.y == 1) {
			str = this.x + " + i" ;
		} else{
			str = this.x + " + " + this.y + ".i";
		}
		return str;
	}

}
