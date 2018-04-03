/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ceciliaprado.cmp.control.dao.production;

import br.com.ceciliaprado.cmp.control.dao.DAO;
import br.com.ceciliaprado.cmp.model.production.Variant;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class VariantDAO extends DAO<Variant, String> {

    public VariantDAO(EntityManager em) {
        super(em, Variant.class);
    }

    @Override
    public boolean isRegistered(Variant entity) {
        return super.find(entity.getName()) != null;
    }
    
}
