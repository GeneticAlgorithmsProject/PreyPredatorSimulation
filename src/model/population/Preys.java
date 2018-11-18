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
		for (int p = 0; p < population.size(); p++) {
			Prey ind = (Prey) population.get(p);
			double dirX = 0., dirY = 0.;
			int size = adversary.getPopulation().size();
			Vector2d dir = new Vector2d();
			for (int i = 0; i < size; ++i) {
				Individual adv = adversary.getPopulation().get(i);
				Vector2d ndiff = Vector2d.normedDiff(adv.getPos(), ind.getPos());
				ndiff.div(size);
				dir.add(ndiff);
			}
			ind.setDirEscape(new Vector2d(dirX, dirY));
		}
	}	
}