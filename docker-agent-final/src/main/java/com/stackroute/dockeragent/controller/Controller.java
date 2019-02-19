package com.stackroute.dockeragent.controller;

import com.stackroute.dockeragent.domain.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/docker")
public class Controller {

    @GetMapping("/stats")
    public ResponseEntity<?> getMetrics() throws IOException
    {

        List<String> commands = new ArrayList<String>();
        commands.add("/bin/sh");
        commands.add("-c");
        commands.add("docker stats --no-stream"); // command

        // creating the process
        ProcessBuilder pb = new ProcessBuilder(commands);

        // starting the process
        Process process = pb.start();
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String s = null;
        String temp="";

        int f=0;
        List<Metrics> metricsList = new ArrayList<>();

        while ((s = stdInput.readLine()) != null)
        {

            temp =temp+s+"\n";

            if (f>=1)
            {
                Metrics metrics = new Metrics();

                String[] str =s.trim().split("\\s+");

                    metrics.setContainerId(str[0]);
                    metrics.setContainerName(str[1]);
                    metrics.setCpu(str[2]);
                    metrics.setMem(str[6]);
                    metrics.setNetIO(str[7]+str[8]+str[9]);
                    metrics.setBlockIO(str[10]+str[10]+str[12]);
                    metrics.setpId(str[13]);

                metricsList.add(metrics);
            }
            f++;
        }

        for(Metrics model : metricsList) {
            System.out.println(model);
        }
        return new ResponseEntity<List<Metrics>>(metricsList, HttpStatus.OK);
    }

}
