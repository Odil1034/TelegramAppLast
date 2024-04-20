package uz.pdp.backend.models.channel;

import uz.pdp.backend.models.BaseModel;

public class Channel extends BaseModel {

    private String name;
    private String authorID;
    private String description;


    public Channel(String name, String authorID, String description) {
        this.name = name;
        this.authorID = authorID;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

}
