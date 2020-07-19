package com.qzz.artchetypeexampletask;

import com.qzz.artchetypeexampletask.entity.request.JobAddRequest;
import com.qzz.artchetypeexampletask.services.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/job")
@RestController
public class HelloWorldController {

    private final JobService jobService;

    @GetMapping("hello")
    public String hellow() {
        return "Hello world!";
    }

    @PostMapping("/addJob")
    public void addJob(@RequestBody JobAddRequest jobAddRequest) throws Exception {
         jobService.addJob(jobAddRequest.getJobClassName(), jobAddRequest.getJobGroupName(), jobAddRequest.getCronExpression());
    }
}
