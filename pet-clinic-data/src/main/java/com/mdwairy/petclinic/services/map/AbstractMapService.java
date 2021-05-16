package com.mdwairy.petclinic.services.map;

import com.mdwairy.petclinic.model.BaseEntity;
import com.mdwairy.petclinic.services.CrudService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> implements CrudService<T, ID> {

    protected Map<ID, T> map = new HashMap<>();


    @Override
    public T findById(ID id) {
        return map.get(id);
    }

    @Override
    public T Save(ID id, T type) {
        return map.put(id, type);
    }

    @Override
    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    @Override
    public void delete(T type) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(type));
    }

    @Override
    public void deleteById(ID id) {
        map.remove(id);
    }
}
