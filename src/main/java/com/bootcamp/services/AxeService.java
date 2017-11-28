package com.bootcamp.services;

import com.bootcamp.commons.constants.DatabaseConstants;
import com.bootcamp.commons.exceptions.DatabaseException;
import com.bootcamp.commons.models.Criteria;
import com.bootcamp.commons.models.Criterias;
import com.bootcamp.commons.ws.utils.RequestParser;
import com.bootcamp.crud.AxeCRUD;
import com.bootcamp.entities.Axe;
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
public class AxeService implements DatabaseConstants{

    AxeCRUD axeCRUD;

    @PostConstruct
    public void init(){
        axeCRUD = new AxeCRUD();
    }

    public void create(Axe axe) throws SQLException {
         axeCRUD.create(axe);
    }

    public void update(Axe axe) throws SQLException {
        axeCRUD.update(axe);
    }

    public Axe delete(int id) throws SQLException {
        Axe axe = read(id);
        axeCRUD.delete(axe);

        return axe;
    }

    public Axe read(int id) throws SQLException {
        Criterias criterias = new Criterias();
        criterias.addCriteria(new Criteria("id", "=", id));
        List<Axe> axes = axeCRUD.read(criterias);

        return axes.get(0);
    }


    public List<Axe> read(HttpServletRequest request) throws SQLException, IllegalAccessException, DatabaseException, InvocationTargetException {
        Criterias criterias = RequestParser.getCriterias(request);
        List<String> fields = RequestParser.getFields(request);
        List<Axe> axes = null;
        if(criterias == null && fields == null)
           axes =  axeCRUD.read();
        else if(criterias!= null && fields==null)
            axes = axeCRUD.read(criterias);
        else if(criterias== null && fields!=null)
            axes = axeCRUD.read(fields);
        else
            axes = axeCRUD.read(criterias, fields);

        return axes;
    }

}
