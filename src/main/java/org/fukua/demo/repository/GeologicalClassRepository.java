package org.fukua.demo.repository;

import org.fukua.demo.Entity.GeologicalClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeologicalClassRepository extends JpaRepository<GeologicalClass, Long> {
    List<GeologicalClass> findSectionIdByName(String name);
    List<GeologicalClass> findSectionIdByCode(String code);
}
