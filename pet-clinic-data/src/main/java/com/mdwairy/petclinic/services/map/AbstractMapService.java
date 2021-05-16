package com.mdwairy.petclinic.services.map;

import com.mdwairy.petclinic.model.BaseEntity;
import com.mdwairy.petclinic.services.CrudService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected Map<ID, T> map = new HashMap<>();

    public T findById(ID id) {
        return map.get(id);
    }

    public T save(ID id, T type) {
        return map.put(id, type);
    }

    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    public void delete(T type) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(type));
    }

    public void deleteById(ID id) {
        map.remove(id);
    }

}
