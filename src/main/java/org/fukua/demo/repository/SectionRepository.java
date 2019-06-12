package org.fukua.demo.repository;

import org.fukua.demo.Entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    Section getById(long id);

    List<Section> getByJobId(long id);
}
