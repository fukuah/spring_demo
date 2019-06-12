package org.fukua.demo.repository;

import org.fukua.demo.Entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    Section getById(long id);

    List<Section> getByJobId(long id);

    @Query(value = "select * from section where id IN (select section_id from geological_class where name=?)", nativeQuery = true)
    List<Section> getByGeologicalClassName(String name);

    @Query(value = "select * from section where id IN (select section_id from geological_class where code=?)", nativeQuery = true)
    List<Section> getByGeologicalClassCode(String code);
}
