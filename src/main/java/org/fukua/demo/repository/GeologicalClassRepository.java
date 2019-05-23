package org.fukua.demo.repository;

import org.fukua.demo.Entity.GeologicalClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeologicalClassRepository extends JpaRepository<GeologicalClass, Long> {
}
