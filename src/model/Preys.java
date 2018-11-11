package model;

import javafx.scene.layout.Pane;

public class Preys extends Population{

	Population adversary;
	
	public Preys(int count, Pane pane) {
		super(count, pane);
	}
	
	@Override
	public void init() {
		for (int i = 0; i < count; ++i) {
			Individual ind = new Prey(width * rnd.nextDouble(), height * rnd.nextDouble());
			population.add(ind);
			group.getChildren().add(ind.getCircle());
		}
	}
	
	@Override
	public void move() {
		findFood();
		calculatePredatorsField();
		boundaryConditions();
		moveInds();
	}
	
	@Override
	public void update() {
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
