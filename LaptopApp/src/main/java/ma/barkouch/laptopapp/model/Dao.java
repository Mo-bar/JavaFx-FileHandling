package ma.barkouch.laptopapp.model;

import java.util.List;

public interface Dao<objectType,idType> {
    void insert(objectType object);
    void update(objectType object);

    void deleteById(idType id);

    List<objectType> findAll();
    objectType findById(idType id);

    boolean exist(objectType object);
}
