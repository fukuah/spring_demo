package org.fukua.demo.repository;

import org.fukua.demo.Entity.Section;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends CrudRepository<Section, Long> {
    public Section getById(long id);

    List<Section> getByJobId(long id);
}
