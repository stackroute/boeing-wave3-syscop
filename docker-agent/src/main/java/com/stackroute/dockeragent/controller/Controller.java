package com.stackroute.dockeragent.controller;

import com.stackroute.dockeragent.domain.ContainerDetails;
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
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/docker")
public class Controller {

    @GetMapping("/stats")
    public ResponseEntity<?> getMetrics() throws IOException
    {
        String s1 = null;
        String s = null;
        String s2 =null;
        String temp="";
        String temp2 = "";
        List<String> commands1 = new ArrayList<>();

        commands1.add("/bin/sh");
        commands1.add("-c");
        commands1.add("docker ps --quiet"); // command to get list of docker container IDs.

        ProcessBuilder pb1 = new ProcessBuilder(commands1);
        Process process1 = pb1.start();
        BufferedReader stdInput1 = new BufferedReader(new InputStreamReader(process1.getInputStream()));

        List<ContainerDetails> containerDetailsList1 = new ArrayList<>();

        while((s1 = stdInput1.readLine()) != null){
            ContainerDetails containerDetails1 = new ContainerDetails();
            containerDetails1.setContainerId(s1);
            containerDetailsList1.add(containerDetails1);
        }

        for(ContainerDetails newmodel : containerDetailsList1) {
            System.out.println(newmodel.getContainerId());
            List<String> commands2 = new ArrayList<String>();

            commands2.add("/bin/sh");
            commands2.add("-c");
            commands2.add("docker inspect --format='{{range $p, $conf := .Config.ExposedPorts}} {{$p}} {{end}}' containerid "+newmodel.getContainerId());    //command to get port number of a particular container ID.

            ProcessBuilder pb2 = new ProcessBuilder(commands2);

            // starting the process
            Process process2 = pb2.start();
            BufferedReader stdInput2 = new BufferedReader(new InputStreamReader(process2.getInputStream()));


            while ((s2 = stdInput2.readLine()) != null) {
                String[] str2 =s2.trim().split("/");
                newmodel.setPort(str2[0]);
            }
            System.out.println(stdInput2);

            System.out.println(newmodel.getContainerId() + newmodel.getContainerName() + newmodel.getPort());
        }

        /********************************************************************************************88*/

        List<String> commands = new ArrayList<String>();
        commands.add("/bin/sh");
        commands.add("-c");
        commands.add("docker stats --no-stream"); // command to get performance metrics details of docker container

        // creating the process
        ProcessBuilder pb = new ProcessBuilder(commands);

        // starting the process
        Process process = pb.start();
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));


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

                Iterator iterator = containerDetailsList1.iterator();
                while (iterator.hasNext()){
                    System.out.println("Inside Iterator");
                    ContainerDetails newContainerDetails = (ContainerDetails) iterator.next();
                    if(str[0].equals(newContainerDetails.getContainerId())){
                        System.out.println(newContainerDetails.getPort() + "########");
                        metrics.setPort(newContainerDetails.getPort());
                    }

                }
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
