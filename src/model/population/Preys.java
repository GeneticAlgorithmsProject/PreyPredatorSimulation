package model.population;

import java.util.Random;

import application.Simulation;
import model.individual.Individual;
import model.individual.Prey;
import utils.Vector2d;

public class Preys extends Population {

	Population adversary;

	public Preys(int count) {
		super(count);
		name = "Prey";
	}

	public void initPositions() {
		Random rnd = new Random();
		for (Individual ind : population)
			ind.setPos(new Vector2d(Simulation.width * (1 - rnd.nextDouble() / 8),
					Simulation.height * (1 - rnd.nextDouble() / 8)));
	}

	@Override
	public void init() {
		for (int i = 0; i < count; ++i)
			population.add(new Prey(Math.min(Simulation.width, Simulation.height)));
	}

	@Override
	public void move(double dt) {
//		findPrey();
//		calculatePredatorsField();
//		boundaryConditions();
//		moveInds(dt);
	}

	@Override
	public void updateSpecial(double dt) {
		eat();
		death();
	}

	public void setAdversary(Population adversary) {
		this.adversary = adversary;
	}

	private void calculatePredatorsField() {
//		for (int p = 0; p < population.size(); p++) {
//			Prey ind = (Prey) population.get(p);
//			int size = adversary.getPopulation().size();
//			Vector2d dir = new Vector2d();
//			for (int i = 0; i < size; ++i) {
//				Individual adv = adversary.getPopulation().get(i);
//				if (Vector2d.dist(adv.getPos(), ind.getPos()) < ind.getGene().getSight()) {
//					dir.add(Vector2d.normedDiff(ind.getPos(), adv.getPos()));
//				}
//			}
//			ind.setDirEscape(dir);
//		}
	}
}
