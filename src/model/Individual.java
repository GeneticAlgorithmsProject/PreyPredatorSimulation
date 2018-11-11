package model;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Vector2d;

public class Individual {
	protected Vector2d pos;
	protected double size;

	protected int age;
	protected double health, maxHealth;
	protected double addHealth = 20;
	protected double speed;

	protected Vector2d dirFood;

	protected Random rnd = new Random();

	protected Circle circle;
	protected Color color;

	public Individual() {
		size = 10 + rnd.nextInt(10);
		age = 0;
		health = 100;
		maxHealth = health;
		pos = new Vector2d();
		circle = new Circle(pos.x, pos.y, size);
		color = new Color(0, 1, 0, 1);
		dirFood = new Vector2d();
	}

	public Individual(double x, double y) {
		Random rnd = new Random();
		size = 10 + rnd.nextInt(10);
		age = 0;
		health = 100;
		maxHealth = health;
		pos = new Vector2d(x, y);
		circle = new Circle(pos.x, pos.y, size);
		color = new Color(0, 1, 0, 1);
		dirFood = new Vector2d();

	}

	public void move() {

	}

	public double distance(double x, double y) {
		return Math.sqrt(Math.pow(pos.x - x, 2) + Math.pow(pos.y - y, 2));
	}

	public Vector2d getPos() {
		return pos;
	}

	public void setPos(Vector2d pos) {
		this.pos = pos;
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

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Vector2d getDirFood() {
		return dirFood;
	}

	public void setDirFood(Vector2d dirFood) {
		this.dirFood = dirFood;
	}
}
