package utils;

import java.util.Random;

import application.Simulation;

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

	public Vector2d(double R) {
		Random rnd = new Random();
		double a = rnd.nextDouble() * 2 * Math.PI;
		double r = R / 2 * Math.sqrt(rnd.nextDouble());
		x = r * Math.cos(a) + R / 2;
		y = r * Math.sin(a) + R / 2;
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

	public double length() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public void norm() {
		double len = length();
		x /= len;
		y /= len;
	}

	public Vector2d multV(double a) {
		return new Vector2d(x * a, y * a);
	}

	public void fit() {
		if (Simulation.closed) {
			if (x > Simulation.width) {
				x = Simulation.width;
			} else if (x < 0) {
				x = 0;
			}
			if (y > Simulation.height) {
				y = Simulation.height;
			} else if (y < 0) {
				y = 0;
			}
		} else {
			if (x > Simulation.width) {
				x = Simulation.width;
			} else if (x < 0) {
				x = 0;
			}
			if (y > Simulation.height) {
				y = Simulation.height;
			} else if (y < 0) {
				y = 0;
			}
		}
	}

	public static Vector2d add(Vector2d v1, Vector2d v2) {
		return new Vector2d(v1.x + v1.x, v1.y + v2.y);
	}

	public static double distLocal(Vector2d v1, Vector2d v2) {
		return Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2));
	}

	public static double dist(Vector2d v1, Vector2d v2) {
		if (Simulation.closed)
			return distLocal(v1, v2);
		else {
			if(distLocal(v1,v2) > Math.min(Simulation.width, Simulation.height)) {
				
				
			}
			
			
			return distLocal(v1, v2);
		}
	}

	public static double distX(Vector2d v1, Vector2d v2) {
		return v1.x - v2.x;
	}

	public static double distY(Vector2d v1, Vector2d v2) {
		return v1.y - v2.y;
	}

	public static double dirX(Vector2d v1, Vector2d v2) {
		return (v1.x - v2.x) / Math.abs(v1.x - v2.x);
	}

	public static double dirY(Vector2d v1, Vector2d v2) {
		return (v1.y - v2.y) / Math.abs(v1.y - v2.y);
	}

	public static Vector2d diff(Vector2d v1, Vector2d v2) {
		return new Vector2d(v1.x - v2.x, v1.y - v2.y);
	}

	public static Vector2d normedDiff(Vector2d v1, Vector2d v2) {
		return new Vector2d(dirX(v1, v2), dirY(v1, v2));
	}

	public static Vector2d PerpendicularClockwise(Vector2d v) {
		return new Vector2d(v.y, -v.x);
	}

	public static Vector2d PerpendicularCounterClockwise(Vector2d v) {
		return new Vector2d(-v.y, v.x);
	}

}
