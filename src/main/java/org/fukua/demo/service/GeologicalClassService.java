package org.fukua.demo.service;

import org.fukua.demo.repository.GeologicalClassRepository;
import org.fukua.demo.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeologicalClassService {
    @Autowired
    private GeologicalClassRepository geologicalClassRepositoryRepository;
}
