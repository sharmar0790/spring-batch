package spring.batch.boot.example.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoBatchBootControler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @RequestMapping("/testDemo")
    public BatchStatus runTheDemo() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        final Map<String, JobParameter> paramMap = new HashMap<>();
        paramMap.put("time",new JobParameter(System.nanoTime()));
        final JobParameters jobParameters = new JobParameters(paramMap);
        JobExecution jobExecution = jobLauncher.run(job,jobParameters);
        System.out.println(jobExecution.getExitStatus());
        return jobExecution.getStatus();
    }
}
