package model.population;

import javafx.scene.layout.Pane;
import model.individual.Individual;
import model.individual.Prey;
import utils.Vector2d;

public class Preys extends Population{

	Population adversary;
	
	public Preys(int count, Pane pane) {
		super(count, pane);
		name = "Prey";
	}
	
	@Override
	public void init() {
		for (int i = 0; i < count; ++i)
			population.add(new Prey(Math.min(width, height)));
	}
	
	@Override
	public void move(double dt) {
		findPrey();
		calculatePredatorsField();
		boundaryConditions();
		moveInds(dt);
	}
	
	@Override
	public void updateSpecial(double dt) {
		eat();
		death();
	}
	
	@Override
	public void setAdversary(Population adversary) {
		this.adversary = adversary;
	}
	
	private void calculatePredatorsField() {
		for (Individual ind : population) {
			int size = adversary.getPopulation().size();
			Vector2d dir = new Vector2d();
			for (int i = 0; i < size; ++i) {
				Individual adv = adversary.getPopulation().get(i);
				if(Vector2d.dist(adv.getPos(), ind.getPos()) < ind.getGene().getSight()) {
					dir.add(Vector2d.diff(ind.getPos(),adv.getPos()));			
				}
			}
			Prey pr = (Prey)ind;
			pr.setDirEscape(dir);
		}
	}	
}
