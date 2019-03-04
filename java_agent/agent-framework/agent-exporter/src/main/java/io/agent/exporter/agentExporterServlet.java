package io.agent.exporter;

import javax.management.ObjectName;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * This servlet simply calls the ExporterMBean via JMX and provides the result.
 */
@WebServlet("/")
public class agentExporterServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String result = (String) ManagementFactory.getPlatformMBeanServer()
                    .getAttribute(new ObjectName("io.agent:type=exporter"), "TextFormat");
            response.getWriter().println(result);
        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().println("Failed to load Exporter MBean. Are you sure the agent is running?");
            e.printStackTrace();
        }
    }
}
