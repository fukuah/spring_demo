package org.fukua.demo.repository;

import org.fukua.demo.Entity.User;
import org.fukua.demo.Entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
}
