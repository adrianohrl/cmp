/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmp.model.events;

import cmp.util.CalendarFormat;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author adrianohrl
 */
@MappedSuperclass
public abstract class AbstractEvent implements Comparable<AbstractEvent>, Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long code;
    private String observation = "";
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Calendar eventDate = new GregorianCalendar();

    public AbstractEvent() {
    }

    public AbstractEvent(Calendar eventDate, String observation) {
        this.eventDate = eventDate;
        this.observation = observation;
    }

    @Override
    public int compareTo(AbstractEvent event) {
        return eventDate.compareTo(event.eventDate);
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof AbstractEvent && equals((AbstractEvent) obj);
    }
    
    public boolean equals(AbstractEvent event) {
        return event != null && eventDate.equals(event.eventDate);
    }
    
    @Override
    public String toString() {
        return "[" + CalendarFormat.format(eventDate) + "]: ";
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Calendar getEventDate() {
        return eventDate;
    }

    public void setEventDate(Calendar eventDate) {
        this.eventDate = eventDate;
    }
    
}
