package com.qzz.artchetypeexampletask;

import com.qzz.artchetypeexampletask.entity.request.JobRequest;
import com.qzz.artchetypeexampletask.services.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/job")
@RestController
public class JobController {

    private final JobService jobService;

    @GetMapping("hello")
    public String hellow() {
        return "Hello world!";
    }

    @PostMapping("/addJob")
    public void addJob(@RequestBody JobRequest jobRequest) throws Exception {
         jobService.addJob(jobRequest.getJobClassName(), jobRequest.getJobGroupName(), jobRequest.getCronExpression());
    }

    @PostMapping("/updateJob")
    public void updateJob(@RequestBody JobRequest jobRequest) throws Exception {
        jobService.updateJob(jobRequest.getJobClassName(), jobRequest.getJobGroupName(), jobRequest.getCronExpression());
    }

    @PostMapping("/restartJob")
    public void restartJob(@RequestBody JobRequest jobRequest) throws Exception {
        jobService.restartJob(jobRequest.getJobClassName(), jobRequest.getJobGroupName());
    }

    @PostMapping("/pauseJob")
    public void pauseJob(@RequestBody JobRequest jobRequest) throws Exception {
        jobService.pauseJob(jobRequest.getJobClassName(), jobRequest.getJobGroupName());
    }

    @PostMapping("/deleteJob")
    public void deleteJob(@RequestBody JobRequest jobRequest) throws Exception {
        jobService.deleteJob(jobRequest.getJobClassName(), jobRequest.getJobGroupName());
    }
}
