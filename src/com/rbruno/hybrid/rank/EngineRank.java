package com.rbruno.hybrid.rank;

public class EngineRank {

	private String name;
	private int tear;
	private int item;
	private int amount;

	public EngineRank(String name, int tear, int item, int amount) {
		this.name = name;
		this.tear = tear;
		this.item = item;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTear() {
		return tear;
	}

	public void setTear(int tear) {
		this.tear = tear;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}
}
