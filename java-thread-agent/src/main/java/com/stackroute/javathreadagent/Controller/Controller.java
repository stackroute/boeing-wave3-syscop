package com.stackroute.javathreadagent.Controller;


import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
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
@RequestMapping("/java")
public class Controller {

    private String stat=null;
    @GetMapping("/stats")
    public ResponseEntity getMetrics() throws IOException {
        String vmId = null;
        List<String> commands1 = new ArrayList<>();

        List<VirtualMachineDescriptor> vmList = VirtualMachine.list();

        for(VirtualMachineDescriptor vm : vmList){
            if(vm.displayName().contains("muzix")){
                vmId = vm.id();
                break;
            }
//            System.out.println("name: " + vm.displayName() + " id :" + vm.id());
        }


        commands1.add("/bin/sh");
        commands1.add("-c");
        commands1.add("jstack "+vmId); // command to get list of docker container IDs.

        ProcessBuilder pb1 = new ProcessBuilder(commands1);
        Process process1 = pb1.start();
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process1.getInputStream()));

        String tmp = new String();
        while ((stat = stdInput.readLine()) != null) {
            if(stat.contains("runnable")){
                tmp += "\n"+stat;
            }
        }
        stat = "";

        String[] tmpdata = tmp.split("\n");
        for(int i=0; i<tmpdata.length; i++){

            if(tmpdata[i].contains("cpu=")){
                stat += "\""+tmpdata[i].split("\"")[1]+"\",";
                stat += tmpdata[i].substring(tmpdata[i].indexOf("elapsed=")).split(" ")[0]+",";
                stat += tmpdata[i].substring(tmpdata[i].indexOf("cpu=")).split(" ")[0]+"\n";
            }
        }
        System.out.println(stat);
        return new ResponseEntity(stat, HttpStatus.OK);

    }
}
