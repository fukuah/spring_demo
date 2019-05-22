package org.fukua.demo.controller;

import org.fukua.demo.Entity.Job;
import org.fukua.demo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("job")
public class JobController {
    @Autowired
    private JobService jobService;

    @RequestMapping(value="{username}", method={RequestMethod.POST})
    public Job loadXlsFile(String username){
        Job job = new Job();

        return jobService.createJob(job);
    }
}
