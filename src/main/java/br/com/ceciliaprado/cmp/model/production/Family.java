/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ceciliaprado.cmp.model.production;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author adrianohrl
 */
@Entity
public class Family implements Serializable {
    
    @Id
    private String name;

    public Family() {
    }

    public Family(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Family && equals((Family) obj);
    }
    
    public boolean equals(Family family) {
        return family != null && name.equals(family.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
