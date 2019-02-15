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

//    @Autowired
//    private Metrics metrics;

//    @Autowired
//    public Controller(Metrics metrics) {
//        this.metrics = metrics;
//    }

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
//        System.out.println(stdInput.readLine());

        String s = null;
        String temp="";

        int f=0;
        List<Metrics> metricsList = new ArrayList<>();

//        System.out.println("here is the string array");
        while ((s = stdInput.readLine()) != null)
        {

            temp =temp+s+"\n";

            if (f>=1)
            {
                Metrics metrics = new Metrics();

                String[] str =s.trim().split("\\s+");
//                System.out.println("String[] : " + str);
//                System.out.println("length ==="+str.length);

                  //  System.out.println(str[i]);
                    metrics.setContainerId(str[0]);
                    metrics.setContainerName(str[1]);
                    metrics.setCpu(str[2]);
                    metrics.setMem(str[6]);
                    metrics.setNetIO(str[7]+str[8]+str[9]);
                    metrics.setBlockIO(str[10]+str[10]+str[12]);
                    metrics.setpId(str[13]);
                //System.out.println("Metrics : " + metrics.toString());
                metricsList.add(metrics);
            }
            f++;

            //System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjj");
        }
//        System.out.println(metricsList.toString());
        for(Metrics model : metricsList) {
            System.out.println(model);
        }
        return new ResponseEntity<List<Metrics>>(metricsList, HttpStatus.OK);
    }

}
//        01ba60de2082
//        elegant_mclaren
//        5.04%
//        1.985GiB
//        /
//        15.54GiB
//        12.77%
//        807MB
//        /
//        809MB
//        9.49MB
//        /
//        1.32GB
//        307