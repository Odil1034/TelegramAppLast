package uz.pdp.backend.models.group;

import uz.pdp.backend.models.BaseModel;

public class Group extends BaseModel {

    private String name;
    private String authorID;
    private String massageID;
    private int countOfUser;

    public Group(String name, String authorID, String massageID, int countOfUser) {
        this.name = name;
        this.authorID = authorID;
        this.massageID = massageID;
        this.countOfUser = countOfUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getMassageID() {
        return massageID;
    }

    public void setMassageID(String massageID) {
        this.massageID = massageID;
    }

    public int getCountOfUser() {
        return countOfUser;
    }

    public void setCountOfUser(int countOfUser) {
        this.countOfUser = countOfUser;
    }
}
