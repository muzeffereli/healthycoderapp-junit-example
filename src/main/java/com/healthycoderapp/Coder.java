package com.healthycoderapp;

public class Coder {
	
	private double height;
	private double weight;
	private int age;
	private Gender gender;
		
	public Coder(double height, double weight) {
		super();
		this.height = height;
		this.weight = weight;
	}
	
	public Coder(double height, double weight, int age, Gender gender) {
		super();
		this.height = height;
		this.weight = weight;
		this.age = age;
		this.gender = gender;
	}
	
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}	
}
