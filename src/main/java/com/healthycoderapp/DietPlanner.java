package com.healthycoderapp;

public class DietPlanner {

	private int proteinPercentage;
	private int fatPercentage;
	private int carbohydratePercentage;
	
	public DietPlanner(int proteinPercentage, int fatPercentage, int carbohydratePercentage) {
		super();
		if (proteinPercentage + fatPercentage + carbohydratePercentage != 100) {
			throw new RuntimeException("protein, fat and carbohydrate percentages must add up to 100!");
		}
			
		this.proteinPercentage = proteinPercentage;
		this.fatPercentage = fatPercentage;
		this.carbohydratePercentage = carbohydratePercentage;
	}
	
	public DietPlan calculateDiet(Coder coder) {
		int calories = this.calculateBMR(coder);
		int protein = this.calculateProtein(calories);
		int fat = this.calculateFat(calories);
		int carbohydrate = this.calculateCarbohydrate(calories);
		
		return new DietPlan(calories, protein, fat, carbohydrate);
	}
	
	private int calculateProtein(int bmr) {
		return (int) Math.round(bmr * proteinPercentage / 400.0);
	}
	private int calculateFat(int bmr) {
		return (int) Math.round(bmr * fatPercentage / 900.0);
	}
	private int calculateCarbohydrate(int bmr) {
		return (int) Math.round(bmr * carbohydratePercentage / 400.0);
	}
	
	private int calculateBMR(Coder coder) {		
		if (coder.getGender() == Gender.MALE) {
			return (int) Math.round(
					(66.5 + 13.8 * coder.getWeight() 
					+ 5.0 * coder.getHeight() * 100 
					- 6.8 * coder.getAge()) * 1.2
			);
		}
		
		return (int) Math.round(
				(655.1 + 9.6 * coder.getWeight() 
				+ 1.9 * coder.getHeight() * 100 
				- 4.7 * coder.getAge()) * 1.2 
		);
	}
}