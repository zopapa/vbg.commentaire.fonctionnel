package com.bootcamp.services;

import com.bootcamp.commons.constants.DatabaseConstants;
import com.bootcamp.commons.exceptions.DatabaseException;
import com.bootcamp.commons.models.Criteria;
import com.bootcamp.commons.models.Criterias;
import com.bootcamp.commons.ws.utils.RequestParser;
import com.bootcamp.crud.SecteurCRUD;
import com.bootcamp.entities.Secteur;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by darextossa on 11/27/17.
 */

@Component
public class SecteurService implements DatabaseConstants{

    SecteurCRUD secteurCRUD;

    @PostConstruct
    public void init(){
        secteurCRUD = new SecteurCRUD();
    }

    public void create(Secteur secteur) throws SQLException {
         secteurCRUD.create(secteur);
    }

    public void update(Secteur secteur) throws SQLException {
        secteurCRUD.update(secteur);
    }

    public Secteur delete(int id) throws SQLException {
        Secteur secteur = read(id);
        secteurCRUD.delete(secteur);

        return secteur;
    }

    public Secteur read(int id) throws SQLException {
        Criterias criterias = new Criterias();
        criterias.addCriteria(new Criteria("id", "=", id));
        List<Secteur> secteurs = secteurCRUD.read(criterias);

        return secteurs.get(0);
    }



    public List<Secteur> read(HttpServletRequest request) throws SQLException, IllegalAccessException, DatabaseException, InvocationTargetException {
        Criterias criterias = RequestParser.getCriterias(request);
        List<String> fields = RequestParser.getFields(request);
        List<Secteur> axes = null;
        if(criterias == null && fields == null)
           axes =  secteurCRUD.read();
        else if(criterias!= null && fields==null)
            axes = secteurCRUD.read(criterias);
        else if(criterias== null && fields!=null)
            axes = secteurCRUD.read(fields);
        else
            axes = secteurCRUD.read(criterias, fields);

        return axes;
    }

}
