package model;

import model.Individual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GeneticAlgorithm {

	private int numberOfBest;
	private double selectivePressure;
	private double mutationRate;

	private Random rnd;

	private Map<Integer, Double> fitness;

	public GeneticAlgorithm(int numberOfBest, double selectivePressure, double mutationRate) {
		this.numberOfBest = numberOfBest;
		this.selectivePressure = selectivePressure;
		this.mutationRate = mutationRate;
		rnd = new Random();
	}

	public void createNewGeneration(HashMap<Integer, Individual> population) {
		this.setFitness(population);
		HashMap<Integer, Individual> parents = this.selectParents(population);
		HashMap<Integer, Individual> newGeneration = this.newGeneration(parents, population.size());
	}

	private void setFitness(HashMap<Integer, Individual> population) {
		int size = population.size();
		fitness = new HashMap<>();
		System.out.println("Fitness:");
		for (int i = 0; i < size; ++i) {
			fitness.put(i, population.get(i).getHealth() * selectivePressure);
			System.out.println(fitness.get(i));
		}
	}

	private HashMap<Integer, Individual> newGeneration(HashMap<Integer, Individual> parents, int size) {
		HashMap<Integer, Individual> newGeneration = new HashMap<>();
//		int pSize = parents.size();
//		int ch = 0;
//		while (newGeneration.size() != size) {
//			List<List<Double>> geneI1 = parents.get(this.rnd.nextInt(pSize)).getGene();
//			List<List<Double>> geneI2 = parents.get(this.rnd.nextInt(pSize)).getGene();
//			if (geneI1.size() != geneI2.size()) {
//				System.err.printf("Number of gene components doesn't match: g1.size=%d, g2.size=%d\n",geneI1.size(), geneI2.size());
//				break;
//			}
//			int count = geneI1.size();
//			List<List<Double>> geneCh1 = new ArrayList<>();
//			List<List<Double>> geneCh2 = new ArrayList<>();
//			for (int i = 0; i < count; ++i) {
//				if (geneI1.get(i).size() == 0 || geneI2.get(i).size() == 0
//						|| geneI2.get(i).size() != geneI1.get(i).size()) {
//					System.err.printf("Gene size error, g1.size=%d, g2.size=%d\n", geneI1.get(i).size(),
//							geneI2.get(i).size());
//					break;
//				}
//				int cP = rnd.nextInt(geneI1.get(i).size());
//				List<Double> genePart11 = geneI1.get(i).subList(0, cP);
//				List<Double> genePart12 = geneI2.get(i).subList(0, cP);
//				List<Double> genePart21 = geneI1.get(i).subList(cP, geneI1.get(i).size() - 1);
//				List<Double> genePart22 = geneI2.get(i).subList(cP, geneI2.get(i).size() - 1);
//
//				List<Double> crossGene1 = new ArrayList<>();
//				List<Double> crossGene2 = new ArrayList<>();
//				crossGene1.addAll(genePart11);
//				crossGene1.addAll(genePart22);
//				crossGene2.addAll(genePart12);
//				crossGene2.addAll(genePart21);
//				geneCh1.add(i,crossGene1);
//				geneCh2.add(i, crossGene2);
//			}
//			Individual child1 = new Individual();
//			child1.setGene(geneCh1);
//			newGeneration.put(ch, child1);
//			ch++;
//
//			Individual child2 = new Individual();
//			child2.setGene(geneCh2);
//			newGeneration.put(ch, child2);
//			ch++;
//		}
//		Set<Entry<Integer, Individual>> entrySet = newGeneration.entrySet();
//		for (Entry<Integer, Individual> entry : entrySet)
//			System.out.println(entry.getKey() + " : " + entry.getValue().getHealth());
//
		return newGeneration;
	}

	private void mutate(HashMap<Integer, Individual> population) {
		Set<Entry<Integer, Individual>> entrySet = population.entrySet();
		for (Entry<Integer, Individual> entry : entrySet) {
//			entry.getValue().getNoiseCoefficients();
		}

	}

	private HashMap<Integer, Individual> selectParents(HashMap<Integer, Individual> population) {
		HashMap<Integer, Individual> parents = new HashMap<>();
		HashMap<Integer, Individual> best = this.elitism(population);
		int size = population.size() / 2 - this.numberOfBest;
		HashMap<Integer, Individual> roulette = this.rouletteSelect(population, size);

		int index = 0;
		Set<Entry<Integer, Individual>> entrySet = roulette.entrySet();
		for (Entry<Integer, Individual> entry : entrySet) {
			parents.put(index, entry.getValue());
			index++;
		}

		entrySet = best.entrySet();
		for (Entry<Integer, Individual> entry : entrySet) {
			parents.put(index, entry.getValue());
			index++;
		}
		System.out.println("Parents:");
		entrySet = parents.entrySet();
		for (Entry<Integer, Individual> entry : entrySet)
			System.out.println(entry.getKey() + " : " + entry.getValue().getHealth());
		return parents;
	}

	private int roulette() {
		double max = 0.f;
		for (Double f : fitness.values()) {
			max += f;
		}
		double pick = this.rnd.nextDouble() * max;
		double current = 0.f;
		for (int i = 0; i < fitness.size(); ++i) {
			current += fitness.get(i);
			if (current > pick)
				return i;
		}
		return -1;
	}

	private HashMap<Integer, Individual> rouletteSelect(HashMap<Integer, Individual> population, int size) {
		HashMap<Integer, Individual> selected = new HashMap<>();
		for (int i = 0; i < size; ++i) {
			int key = roulette();
			if (key < 0) {
				System.out.println("Oops");
				break;
			}
			selected.put(key, population.get(key));
		}
		return selected;
	}

	private HashMap<Integer, Individual> elitism(HashMap<Integer, Individual> population) {
		HashMap<Integer, Individual> best = new HashMap<>();
		for (int b = 0; b < this.numberOfBest; ++b) {
			double currentFittest = 0;
			int currentKey = 0;
			Set<Entry<Integer, Double>> entrySet = this.fitness.entrySet();
			for (Entry<Integer, Double> entry : entrySet) {
				if (entry.getValue() > currentFittest && best.get(entry.getKey()) == null) {
					currentFittest = entry.getValue();
					currentKey = entry.getKey();
				}
			}
			best.put(currentKey, population.get(currentKey));
		}
		System.out.println("Best");
		Set<Entry<Integer, Individual>> entrySet = best.entrySet();
		for (Entry<Integer, Individual> entry : entrySet)
			System.out.println(entry.getKey() + " : " + entry.getValue().getHealth());
		return best;
	}

	public void sortPopulation(ArrayList<Individual> population) {
		ArrayList<Individual> sortedPopulation = population;
		Collections.sort(sortedPopulation, new Comparator<Individual>() {
			@Override
			public int compare(final Individual lhs, Individual rhs) {
				// TODO return 1 if rhs should be before lhs
				// return -1 if lhs should be before rhs
				// return 0 otherwise
				return lhs.getHealth() < rhs.getHealth() ? -1 : rhs.getHealth() == lhs.getHealth() ? 0 : 1;

			}
		});

	}

}
