package org.fukua.demo.service;

import org.fukua.demo.Entity.Job;
import org.fukua.demo.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private UserService userService;

     public Job createJob(){
         Job job = new Job();

         Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         String username = ((UserDetails)principal).getUsername();
         // TODO improve, remove additional query to get user id
         job.setUser(userService.getUserByLogin(username));
         return jobRepository.save(job);
     }

     public void save(Job job){
         jobRepository.save(job);
     }

}
