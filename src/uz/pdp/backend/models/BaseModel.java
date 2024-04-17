package uz.pdp.backend.models;

import java.util.UUID;

public abstract class BaseModel {

    private String ID;
    private boolean isDelete;

    public BaseModel() {
        this.ID = UUID.randomUUID().toString();
        this.isDelete = false;
    }

    public String getID() {
        return ID;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean delete) {
        isDelete = delete;
    }
}
