import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;

public class Main {
    public static void main(String[] args){

        List<VirtualMachineDescriptor> vmList = VirtualMachine.list();

        int port = Integer.valueOf(args[1]);

        for(VirtualMachineDescriptor vm : vmList){
            System.out.println("name: " + vm.displayName() + " id :" + vm.id());
            try{
                VirtualMachine vm0 = VirtualMachine.attach(vm.id());
                // load agent, agnet class agentmain will be invoked.
                vm0.loadAgent(args[0], "port="+ Integer.toString(port++));
                System.out.println("Load agent done.");
                vm0.detach();
            }catch(Exception e) {
                System.out.println("exception : " + e.getMessage());
            }
        }

    }
}