package model;

public class Vector2d {

	public double x, y;

	public Vector2d() {
		this.x = 0.;
		this.y = 0.;
	}

	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void add(Vector2d v) {
		x += v.x;
		y += v.y;
	}

	public Vector2d addV(Vector2d v) {
		return new Vector2d(x + v.x, y + v.y);
	}

	public void mult(double a) {
		x *= a;
		y *= a;
	}
	
	public void div(double a) {
		x /= a;
		y /= a;
	}

	public Vector2d multV(double a) {
		return new Vector2d(x * a, y * a);
	}

	public void fit(double x1, double y1, double x2, double y2) {
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

	static public Vector2d add(Vector2d v1, Vector2d v2) {
		return new Vector2d(v1.x + v1.x, v1.y + v2.y);
	}

	static public double dist(Vector2d v1, Vector2d v2) {
		return Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2));
	}

	static public double dirX(Vector2d v1, Vector2d v2) {
		return (v1.x - v2.x) / Math.abs(v1.x - v2.x);
	}

	static public double dirY(Vector2d v1, Vector2d v2) {
		return (v1.y - v2.y) / Math.abs(v1.y - v2.y);
	}
	
	static public Vector2d normedDiff(Vector2d v1, Vector2d v2) {
		return new Vector2d(dirX(v1,v2), dirY(v1,v2));
	}
}
