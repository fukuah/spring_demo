package org.fukua.demo.repository;

import org.fukua.demo.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Long> {
    Job getByUserId(Long id);
}
