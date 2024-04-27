package cn.itcast.dragon.entities;

import java.util.ArrayList;

public class Gift {
private String name ;
private int score;
private int weight;
private static ArrayList<Gift> giftList;
public  Gift(String name,int score,int weight) {
	this.name = name;
	this.score = score;
	this.weight = weight;
}
public static ArrayList<Gift> getList() {
	if(giftList == null){
		giftList = new ArrayList<>();
	}
	return giftList;
}
public void setName(String name) {
	this.name = name;
}
public String getName() {
	return name;
}
public void setScore(int score) {
	this.score = score;
}
public int getScore() {
	return score;
}
public void setWeight(int weight) {
	this.weight = weight;
}
public int getWeight() {
	return weight;
}
}
