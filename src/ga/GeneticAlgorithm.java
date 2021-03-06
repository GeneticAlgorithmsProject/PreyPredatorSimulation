package ga;

import java.util.Collections;
import java.util.Random;

import application.Simulation;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import model.individual.Individual;
import model.population.Population;

public class GeneticAlgorithm {

	private int numberOfBest;
	private double selectivePressure;
	private double mutationRate;

	private Random rnd;

	private List<Double> fitness;

	public GeneticAlgorithm(int numberOfBest, double selectivePressure, double mutationRate) {
		this.numberOfBest = numberOfBest;
		this.selectivePressure = selectivePressure;
		this.mutationRate = mutationRate;
		rnd = new Random();
	}

	public void createNewGeneration(Population population) {
		setFitness(population);
		createChildren(population, selectParents(population));
		mutate(population);
	}

	private void setFitness(Population population) {
		int size = population.getPopulation().size();
		fitness = new LinkedList<>();
		for (int i = 0; i < size; ++i) {
			fitness.add(i, population.getPopulation().get(i).getAge() * selectivePressure);
		}
	}

	private void mutate(Population population) {

	}

	private void createChildren(Population population, List<Individual> parents) {
		List<Individual> newGeneration = new LinkedList<>();
		while (newGeneration.size() != population.getCount()) {
			Individual ind = new Individual(Math.min(Simulation.width, Simulation.height));
			Gene gene = new Gene();

			int parent1index = rnd.nextInt(parents.size()-1);
			int parent2index = rnd.nextInt(parents.size()-1);
			while (parent1index == parent2index)
				parent1index = rnd.nextInt(parents.size());

			Gene newGene = crossover(parents.get(parent1index).getGene(),parents.get(parent2index).getGene());
			ind.setGene(newGene);
			newGeneration.add(ind);
		}
		population.setPopulation(newGeneration);
	}

	private Gene crossover(Gene gene1, Gene gene2) {
		Gene gene = new Gene();
		if(gene1.getGene().size() != gene2.getGene().size()) {
			System.err.println("Wrong gene sizes");
			return gene;
		}
		int size = gene1.getGene().size();
		for(int g = 0; g < size; g++) {
			double alpha = rnd.nextDouble();
			double min = Math.min(gene1.getGene().get(Gene.keys[g]), gene2.getGene().get(Gene.keys[g]));
			double max = Math.max(gene1.getGene().get(Gene.keys[g]), gene2.getGene().get(Gene.keys[g]));
			double range = max - min;
			double newValue = min - alpha + rnd.nextDouble()*(range + alpha);
			gene.getGene().put(Gene.keys[g], newValue);
		}
		return gene;
	}

	private List<Individual> selectParents(Population population) {
		List<Individual> parents = new LinkedList<>();
		for (Individual ind : elitism(population))
			parents.add(ind);
		for (Individual ind : rouletteSelect(population, population.getPopulation().size() / 2 - numberOfBest))
			parents.add(ind);
//		for(Individual ind : parents)
//			System.out.println(population.getName() + " " + ind.getAge());
		return parents;
	}

	private int roulette() {
		double max = Collections.max(fitness);
		double pick = this.rnd.nextDouble() * max;
		double current = 0.f;
		for (int i = 0; i < fitness.size(); ++i) {
			current += fitness.get(i);
			if (current > pick)
				return i;
		}
		return -1;
	}

	private List<Individual> rouletteSelect(Population population, int size) {
		List<Individual> selected = new LinkedList<>();
		for (int i = 0; i < size; ++i) {
			int index = roulette();
			if (index < 0) {
				System.err.println("Shieeet");
				continue;
			}
			selected.add(population.getPopulation().get(index));
		}
		return selected;
	}

	private List<Individual> elitism(Population population) {
		List<Individual> best = new LinkedList<>();
		for (int b = 0; b < numberOfBest; ++b) {
			double currentFitness = 0;
			int currentIndex = 0;
			for (int f = 0; f < fitness.size(); f++) {
				if (fitness.get(f) > currentFitness && !best.contains(population.getPopulation().get(f))) {
					currentFitness = fitness.get(f);
					currentIndex = f;
				}
			}
			best.add(population.getPopulation().get(currentIndex));
		}
//		for (Individual ind : best)
//			System.out.println(population.getName() + " " + ind.getAge());
		return best;
	}

	public void sortPopulation(LinkedList<Individual> population) {
		LinkedList<Individual> sortedPopulation = population;
		Collections.sort(sortedPopulation, new Comparator<Individual>() {
			@Override
			public int compare(final Individual lhs, Individual rhs) {
				return lhs.getAge() < rhs.getAge() ? -1 : rhs.getAge() == lhs.getAge() ? 0 : 1;
			}
		});
	}

}
