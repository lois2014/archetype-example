package com.qzz.artchetypeexampletask;

import com.qzz.artchetypeexampletask.entity.request.JobAddRequest;
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
    public void addJob(@RequestBody JobAddRequest jobAddRequest) throws Exception {
         jobService.addJob(jobAddRequest.getJobClassName(), jobAddRequest.getJobGroupName(), jobAddRequest.getCronExpression());
    }

    @PostMapping("/updateJob")
    public void updateJob(@RequestBody JobAddRequest jobAddRequest) throws Exception {
        jobService.updateJob(jobAddRequest.getJobClassName(), jobAddRequest.getJobGroupName(), jobAddRequest.getCronExpression());
    }

    @PostMapping("/restartJob")
    public void restartJob(@RequestBody JobAddRequest jobAddRequest) throws Exception {
        jobService.restartJob(jobAddRequest.getJobClassName(), jobAddRequest.getJobGroupName());
    }

    @PostMapping("/pauseJob")
    public void pauseJob(@RequestBody JobAddRequest jobAddRequest) throws Exception {
        jobService.pauseJob(jobAddRequest.getJobClassName(), jobAddRequest.getJobGroupName());
    }

    @PostMapping("/deleteJob")
    public void deleteJob(@RequestBody JobAddRequest jobAddRequest) throws Exception {
        jobService.deleteJob(jobAddRequest.getJobClassName(), jobAddRequest.getJobGroupName());
    }
}
