package models;

public class Scholarship {
    private int scholarshipId;
    private String scholarshipName;
    private String description;
    private double amount;
    
    public Scholarship(int scholarshipId, String scholarshipName, String description, double amount) {
        this.scholarshipId = scholarshipId;
        this.scholarshipName = scholarshipName;
        this.description = description;
        this.amount = amount;
    }
    
	public int getScholarshipId() {
		return scholarshipId;
	}
	public void setScholarshipId(int scholarshipId) {
		this.scholarshipId = scholarshipId;
	}
	public String getScholarshipName() {
		return scholarshipName;
	}
	public void setScholarshipName(String scholarshipName) {
		this.scholarshipName = scholarshipName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
    @Override
    public String toString() {
        return scholarshipName + " - " + amount; // Customize as needed
    }
}
