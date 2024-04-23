package uz.pdp.backend.models;

import java.util.UUID;

public abstract class BaseModel {

    private String ID;
    private boolean delete;

    public BaseModel() {
        this.ID = UUID.randomUUID().toString();
        this.delete = false;
    }

    public String getID() {
        return ID;
    }

    public boolean getDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
