package com.mdwairy.petclinic.services.map;

import com.mdwairy.petclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    public T findById(ID id) {
        return map.get(id);
    }

    public T save(T object) {
        if (object != null) {
            if (object.getId() == null) {
                object.setId(getNextId());
            }
            map.put(object.getId(), object);
        }
        else {
            throw new RuntimeException("Object cannot be null");
        }
        return map.get(object.getId());
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

    private Long getNextId() {
        if (!map.isEmpty()) {
            return Collections.max(map.keySet()) + 1;
        }
        return 1L;
    }

}
