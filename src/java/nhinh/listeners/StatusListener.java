/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.listeners;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author PC
 */
public class StatusListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        List<String> listStatus = new ArrayList<>();
        listStatus.add("Active");
        listStatus.add("Inactive");
        context.setAttribute("STATUS_LIST", listStatus);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
