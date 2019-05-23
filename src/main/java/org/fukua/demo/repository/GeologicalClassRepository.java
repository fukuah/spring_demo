package org.fukua.demo.repository;

import org.fukua.demo.Entity.GeologicalClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeologicalClassRepository extends CrudRepository<GeologicalClass, Long> {
}
