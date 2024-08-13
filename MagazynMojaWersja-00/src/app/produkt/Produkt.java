package app.produkt;

import java.io.Serializable;

public class Produkt implements Serializable{
	private static final long serialVersionUID = 1L;
    private String name;
    private int amount;
    private String locations;
    private String desctiption;
    

    public Produkt(String name, int amount, String locations, String odesctiptionpis) {
        this.name = name;
        this.amount = amount;
        this.locations = locations;
        this.desctiption = desctiption;
    }


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getLocations() {
		return locations;
	}


	public void setLocations(String locations) {
		this.locations = locations;
	}


	public String getDesctiption() {
		return desctiption;
	}


	public void setDesctiption(String desctiption) {
		this.desctiption = desctiption;
	}


	@Override
	public String toString() {
		return "Produkt [name=" + name + ", amount=" + amount + ", locations=" + locations + ", desctiption="
				+ desctiption + "]";
	}

   

}
