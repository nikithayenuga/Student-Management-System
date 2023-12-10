package models;

public class University {
    private int universityId;
    private String universityName;
    private String location;

    public University(int universityId, String universityName, String location) {
        this.universityId = universityId;
        this.universityName = universityName;
        this.location = location;
    }

    public int getuniversityId() {
        return universityId;
    }

    public void setuniversityId(int universityId) {
        this.universityId = universityId;
    }

    public String getuniversityName() {
        return universityName;
    }

    public void setuniversityName(String universityName) {
        this.universityName = universityName;
    }
    public String getlocation() {
        return location;
    }

    public void setlocation(String address) {
        this.location = address;
    }
    
    @Override
    public String toString() {
        return universityName + " - " + location;
    }
}