package org.fukua.demo.service;

import org.fukua.demo.Entity.Job;
import org.fukua.demo.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private UserService userService;

     public Job createJob(String username){
         Job job = new Job();
         job.setUser(userService.getUserByLogin(username));
         return job;
     }

}
