/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmp.dao.events;

import cmp.model.events.LogEvent;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class LogEventDAO extends AbstractEmployeeRelatedEventDAO<LogEvent> {

    public LogEventDAO(EntityManager em) {
        super(em, LogEvent.class);
    }
    
}
