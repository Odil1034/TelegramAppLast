package uz.pdp.backend.service;

import uz.pdp.backend.models.BaseModel;

import java.util.List;

public interface BaseService<M extends BaseModel> {

    // CRUD  => Create, read, update, delete
    boolean
    create(M m);
    M get(String ID);
    List<M> getList();
    void update(M newM);
    boolean delete(String ID);

}
