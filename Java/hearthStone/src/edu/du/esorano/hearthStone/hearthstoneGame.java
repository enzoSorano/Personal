package edu.du.esorano.hearthStone;

public class hearthstoneGame {
	//16 copies of each minon
	private int totalOne = 16 * 17;
	//15 copies of each
	private int totalTwo = 15 * 24;
	//13 copies of each
	private int totalThree = 13 * 30;
	//11 copies of each
	private int totalFour = 11 * 26;
	//9 copies of each
	private int totalFive = 9 * 24;
	//7 copies of each 
	private int totalSix = 7 * 16;
	//gets the total minions per tier
	private int[] minions = new int[6];
	public hearthstoneGame() {
		minions[0] = totalOne;
		minions[1] = totalTwo;
		minions[2] = totalThree;
		minions[3] = totalFour;
		minions[4] = totalFive;
		minions[5] = totalSix;
	}
	/*
	 * tier is thje tier the minion is
	 * 
	 */
	
	public int getMinionsPerTier(int tier) {
		int total = 0;
		//get how many minions are in the pool at that time
		for(int i = 0; i <= tier; i++) {
			total+= minions[i];
			i++;
		}
		
		return total;
	}
	public double oddsOfGettingAMinion(int tier) {
		double odds = 0;
		//get the total amount of minions at the tier
		int total = 0;
		for(int i = 0; i <= tier; i++) {
			total+= minions[i];
			i++;
		}
		//divide the number of copies of that minion at the tier
		double numberOfCopies = 0;
		if(tier == 0) {
			numberOfCopies = 16.0;
		}else
		if(tier == 1) {
			numberOfCopies = 15.0;
		}else
		if(tier == 2) {
			numberOfCopies = 13.0;
		}else
		if(tier == 3) {
			numberOfCopies = 11.0;
		}else
		if(tier == 4) {
			numberOfCopies = 9.0;
		}
		if(tier == 5) {
			numberOfCopies = 7.0;
		}else
		odds = numberOfCopies/total;
		odds = odds*100;
		return odds;
	}
	public static void main(String args[]) {
		
		hearthstoneGame h = new hearthstoneGame();
		
		for(int i=0; i <= 5; i++) {
		System.out.println("the ods of getting a minion that is tier" + i + " is "+  h.oddsOfGettingAMinion(i));
		}
	}
}
