/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ceciliaprado.cmp.control.model.production.reports;

import br.com.ceciliaprado.cmp.control.dao.DataSource;
import br.com.ceciliaprado.cmp.control.dao.events.TimeClockEventDAO;
import br.com.ceciliaprado.cmp.control.dao.personnel.ManagerDAO;
import br.com.ceciliaprado.cmp.control.dao.personnel.SubordinateDAO;
import br.com.ceciliaprado.cmp.control.model.production.reports.filters.EmployeeRelatedEventsList;
import br.com.ceciliaprado.cmp.exceptions.DAOException;
import br.com.ceciliaprado.cmp.exceptions.ReportException;
import br.com.ceciliaprado.cmp.model.events.AbstractEmployeeRelatedEvent;
import br.com.ceciliaprado.cmp.model.personnel.Manager;
import br.com.ceciliaprado.cmp.model.personnel.Subordinate;
import br.com.ceciliaprado.cmp.util.CalendarFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class AttendanceReportDAOTest {
    
    public static void main(String[] args) throws DAOException, ReportException {
        EntityManager em = DataSource.createEntityManager();
        String subordinateCode = "792001";
        SubordinateDAO subordinateDAO = new SubordinateDAO(em);
        Subordinate subordinate = subordinateDAO.find(subordinateCode);
        Calendar startDate = new GregorianCalendar(2017, Calendar.SEPTEMBER, 1);
        Calendar endDate = new GregorianCalendar();
        TimeClockEventDAO timeClockEventDAO = new TimeClockEventDAO(em);
        EmployeeRelatedEventsList<AbstractEmployeeRelatedEvent> events = timeClockEventDAO.findEmployeeEvents(subordinate, startDate, endDate);
        String managerLogin = "marcos";
        ManagerDAO managerDAO = new ManagerDAO(em);
        Manager manager = managerDAO.find(managerLogin);
        SubordinateEfficiencyReport report = new SubordinateEfficiencyReport(subordinate, events, manager, startDate, endDate);
        System.out.println("\n" + subordinate + "'s daily total duration:");
        TreeMap<Calendar, Double> map = report.getDailyTotalDuration();        
        for (Map.Entry<Calendar, Double> totalDuration : map.entrySet()) {
            System.out.println("\t" + CalendarFormat.formatDate(totalDuration.getKey()) + ": " + totalDuration.getValue());
        }
        em.close();
        DataSource.closeEntityManagerFactory();
    }
    
}
