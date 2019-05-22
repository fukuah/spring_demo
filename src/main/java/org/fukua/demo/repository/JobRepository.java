package org.fukua.demo.repository;

import org.fukua.demo.Entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Section, Long> {

}
