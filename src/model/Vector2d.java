package model;

public class Vector2d {

	public double x,y;
	
	public Vector2d() {
		this.x = 0.;
		this.y = 0.;
	}
	
	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void Add(Vector2d v) {
		x += v.x;
		y += v.y;
	}
	
	public Vector2d AddV(Vector2d v) {
		return new Vector2d(x+v.x,y+v.y);
	}
	
	public void Mult(double a) {
		x *= a;
		y *= a;
	}
	
	public Vector2d MultV(double a) {
		return new Vector2d(x*a,y*a);
	}
	
	public void Fit(double x1, double y1, double x2, double y2) {
		if (x > x2) {
			x = x2;
		} else if (x < x1) {
			x = x1;
		}
		if (y > y2) {
			y = y2;
		} else if (y < y1) {
			y = y1;
		}
	}
	
	static Vector2d Add(Vector2d v1, Vector2d v2) {
		return new Vector2d(v1.x+v1.x,v1.y+v2.y);
	}
	
}
