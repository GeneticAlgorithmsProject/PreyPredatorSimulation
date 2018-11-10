package model;

import model.Gene;

import java.util.Random;

import javafx.scene.shape.Circle;
import model.Vector2d;

public class Individual extends Gene {
	private double x, y;
	private double size;
	private double dirX, dirY;

	private Vector2d<Double> dirE;
	private Vector2d<Double> dirF;
	
	private double speed;

	private int age;
	private double health, maxHealth;
	private double addHealth = 20;
	
	private Circle circle;
	
	public Individual() {
		Random rnd = new Random();
		x = 0;
		y = 0;
		size = 10 + rnd.nextInt(10);
		age = 0;
		health = 100;
		maxHealth = health;
		dirX = 0;
		dirY = 0;
		dirE = new Vector2d<>(0.,0.);
		dirF = new Vector2d<>(0.,0.);
		speed = 1. / size;
		circle = new Circle(x,y,size);
	}

	public Individual(double x, double y) {
		Random rnd = new Random();
		this.x = x;
		this.y = y;
		size = 10 + rnd.nextInt(10);
		age = 0;
		health = 100;
		maxHealth = health;
		dirX = 0;
		dirY = 0;
		speed = 1./ size;
		circle = new Circle(x,y,size);
	}
	
	public void move() {
//		dirX *= noiseCoefficients.get(0);
//		dirY *=  noiseCoefficients.get(1);
		x += dirX * speed;
		y += dirY * speed;
	}

	public double distance(double x, double y) {
		return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}

	public double getAddHealth() {
		return addHealth;
	}

	public void setAddHealth(double addHealth) {
		this.addHealth = addHealth;
	}

	public double getDirX() {
		return dirX;
	}

	public void setDirX(double dirX) {
		this.dirX = dirX;
	}

	public double getDirY() {
		return dirY;
	}

	public void setDirY(double dirY) {
		this.dirY = dirY;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}
}
