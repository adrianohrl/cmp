/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.cmp.reports;

import cmp.exceptions.CMPException;
import cmp.model.events.Casualty;
import cmp.model.events.TimeClockEvent;
import cmp.model.personal.Sector;
import cmp.model.personal.Subordinate;
import cmp.model.personal.Supervisor;
import cmp.model.production.Model;
import cmp.model.production.Phase;
import cmp.model.production.PhaseProductionOrder;
import cmp.model.production.ProductionOrder;
import cmp.model.production.ProductionStates;
import cmp.production.control.EntryEventsBuilder;
import cmp.production.reports.EmployeeEventsPeriodBuilder;
import cmp.production.reports.EventsPeriodBuilder;
import cmp.production.reports.filters.EmployeeRelatedEventsList;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author adrianohrl
 */
public class EventsPeriodBuilderTest {
    
    public static void main(String[] args) throws CMPException {
        Supervisor supervisor = new Supervisor("ahrl", "12345", "sup1", "Adriano Henrique Rossette Leite");
        Sector sector = new Sector("costura", supervisor);
        Subordinate subordinate1 = new Subordinate("sub1", "Subordinate 1");
        Subordinate subordinate2 = new Subordinate("sub2", "Subordinate 2");
        Subordinate subordinate3 = new Subordinate("sub3", "Subordinate 3");
        Subordinate subordinate4 = new Subordinate("sub4", "Subordinate 4");
        ArrayList<Subordinate> subordinates = new ArrayList<>();
        subordinates.add(subordinate1);
        subordinates.add(subordinate2);
        subordinates.add(subordinate3);
        subordinates.add(subordinate4);
        supervisor.setSubordinates(subordinates);
        
        Model model = new Model("ref1", "Reference 1");
        Phase phase1 = new Phase("phase 1", 10);
        Phase phase2 = new Phase("phase 2", 7.5);
        Phase phase3 = new Phase("phase 3", 5);
        ArrayList<Phase> phases = new ArrayList<>();
        phases.add(phase1);
        phases.add(phase2);
        phases.add(phase3);
        model.setPhases(phases);
        
        ProductionOrder productionOrder = new ProductionOrder("production of ref1", model);
        
        PhaseProductionOrder phaseProductionOrder1 = new PhaseProductionOrder(phase1, productionOrder, 15);
        PhaseProductionOrder phaseProductionOrder2 = new PhaseProductionOrder(phase2, productionOrder, 15);
        PhaseProductionOrder phaseProductionOrder3 = new PhaseProductionOrder(phase3, productionOrder, 15);
        
        Casualty casualty1 = new Casualty("Falta de suprimento");
        Casualty casualty2 = new Casualty("Falta de energia elétrica");
        
        EntryEventsBuilder entryEventsBuilder = new EntryEventsBuilder(sector, supervisor);
        entryEventsBuilder.buildEntryEvent(phaseProductionOrder1, subordinate1, new GregorianCalendar(2017, 4, 3, 7, 5), "");
        entryEventsBuilder.buildEntryEvent(phaseProductionOrder1, subordinate1, ProductionStates.PAUSED, 5, new GregorianCalendar(2017, 4, 3, 9, 20), "", casualty2);
        entryEventsBuilder.buildEntryEvent(phaseProductionOrder1, subordinate1, ProductionStates.RESTARTED, new GregorianCalendar(2017, 4, 3, 13, 45), "");
        entryEventsBuilder.buildEntryEvent(phaseProductionOrder1, subordinate1, ProductionStates.RETURNED, 3, new GregorianCalendar(2017, 4, 3, 15, 0), "", casualty1);
        entryEventsBuilder.buildEntryEvent(phaseProductionOrder3, subordinate2, new GregorianCalendar(2017, 4, 3, 7, 3), "");
        entryEventsBuilder.buildEntryEvent(phaseProductionOrder3, subordinate2, ProductionStates.PAUSED, 7, new GregorianCalendar(2017, 4, 3, 9, 20), "", casualty2);
        entryEventsBuilder.buildEntryEvent(phaseProductionOrder3, subordinate3, ProductionStates.RESTARTED, new GregorianCalendar(2017, 4, 3, 9, 45), "");
        entryEventsBuilder.buildEntryEvent(phaseProductionOrder2, subordinate2, new GregorianCalendar(2017, 4, 3, 9, 45), "");
        entryEventsBuilder.buildEntryEvent(phaseProductionOrder2, subordinate2, ProductionStates.FINISHED, new GregorianCalendar(2017, 4, 3, 10, 45), "");
        entryEventsBuilder.buildEntryEvent(phaseProductionOrder3, subordinate3, ProductionStates.FINISHED, new GregorianCalendar(2017, 4, 3, 13, 45), "");
        
        ArrayList<TimeClockEvent> timeClockEvents = new ArrayList<>();
        timeClockEvents.add(new TimeClockEvent(subordinate1, true, new GregorianCalendar(2017, 4, 3, 7, 1), ""));
        timeClockEvents.add(new TimeClockEvent(subordinate2, true, new GregorianCalendar(2017, 4, 3, 7, 0), ""));
        timeClockEvents.add(new TimeClockEvent(subordinate3, true, new GregorianCalendar(2017, 4, 3, 8, 30), ""));
        timeClockEvents.add(new TimeClockEvent(subordinate1, false, new GregorianCalendar(2017, 4, 3, 11, 2), ""));
        timeClockEvents.add(new TimeClockEvent(subordinate2, false, new GregorianCalendar(2017, 4, 3, 11, 5), ""));
        timeClockEvents.add(new TimeClockEvent(subordinate3, false, new GregorianCalendar(2017, 4, 3, 11, 15), ""));
        timeClockEvents.add(new TimeClockEvent(subordinate1, true, new GregorianCalendar(2017, 4, 3, 12, 45), ""));
        timeClockEvents.add(new TimeClockEvent(subordinate2, true, new GregorianCalendar(2017, 4, 3, 13, 0), ""));
        timeClockEvents.add(new TimeClockEvent(subordinate3, true, new GregorianCalendar(2017, 4, 3, 13, 0), ""));
        timeClockEvents.add(new TimeClockEvent(subordinate1, false, new GregorianCalendar(2017, 4, 3, 17, 42), ""));
        timeClockEvents.add(new TimeClockEvent(subordinate2, false, new GregorianCalendar(2017, 4, 3, 17, 50), ""));
        timeClockEvents.add(new TimeClockEvent(subordinate3, false, new GregorianCalendar(2017, 4, 3, 18, 15), ""));
        
        EmployeeRelatedEventsList events = new EmployeeRelatedEventsList();
        events.addAll(entryEventsBuilder.getEntryEvents());
        events.addAll(timeClockEvents);
        
        EventsPeriodBuilder builder = new EventsPeriodBuilder(events);
        for (EmployeeEventsPeriodBuilder b : builder) {
            System.out.println("Employee: " + b.getEmployee());
            for (Phase phase : b.getPhases()) {
                System.out.println("\tPhase: " + phase);
                System.out.println("\t\tEffective Duration: " + b.getEffectiveDuration(phase) + " [min]");
                System.out.println("\t\tExpected Duration: " + b.getExpectedDuration(phase) + " [min]");
                System.out.println("\t\tProduced Quantity: " + b.getProducedQuantity(phase) + " [un]");
                System.out.println("\t\tReturned Quantity: " + b.getReturnedQuantity(phase) + " [un]");
                System.out.println("\t\tEffective Efficiency: " + (b.getEffectiveEfficiency(phase) * 100) + " %");
            }
            System.out.println("\n\t-------------------------------------------------------------\n");
            System.out.println("\tTotals:");
            System.out.println("\t\tEffective Duration: " + b.getTotalEffectiveDuration() + " [min]");
            System.out.println("\t\tExpected Duration: " + b.getTotalExpectedDuration() + " [min]");
            System.out.println("\t\tFree Duration: " + b.getTotalFreeDuration() + " [min]");
            System.out.println("\t\tTotal Duration: " + b.getTotalDuration() + " [min]");
            System.out.println("\t\tProduced Quantity: " + b.getTotalProducedQuantity() + " [un]");
            System.out.println("\t\tReturned Quantity: " + b.getTotalReturnedQuantity() + " [un]");
            System.out.println("\t\tEffective Efficiency: " + (b.getTotalEffectiveEfficiency() * 100) + " %");
            System.out.println("\t\tTotal Efficiency: " + (b.getTotalEfficiency() * 100) + " %");
            System.out.println("\n=====================================================================\n");
        }
    }
    
}
