package com.bootcamp.services;

import com.bootcamp.commons.constants.DatabaseConstants;
import com.bootcamp.commons.exceptions.DatabaseException;
import com.bootcamp.commons.models.Criteria;
import com.bootcamp.commons.models.Criterias;
import com.bootcamp.commons.ws.models.PilierUWs;
import com.bootcamp.commons.ws.utils.RequestParser;
import com.bootcamp.crud.PilierCRUD;
import com.bootcamp.entities.Pilier;
import com.bootcamp.repositories.PilierRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by darextossa on 11/27/17.
 */

@Component
public class PilierService implements DatabaseConstants{

    PilierCRUD pilierCRUD;

    @PostConstruct
    public void init(){
        pilierCRUD = new PilierCRUD();
    }

    public int  create(PilierUWs pilierUWs) throws SQLException {
         Pilier pilier = new Pilier();
         pilier.setDescription(pilierUWs.getDescription());
         pilier.setNom(pilierUWs.getNom());
         pilier.setDateCreation(System.currentTimeMillis());
         pilier.setDateMiseAJour(System.currentTimeMillis());
         pilierCRUD.create(pilier);

         return pilier.getId();
    }

    public void update(Pilier pilier) throws SQLException {
        pilierCRUD.update(pilier);
    }

    public Pilier delete(int id) throws SQLException {
        Pilier pilier = read(id);
        pilierCRUD.delete(pilier);

        return pilier;
    }

    public Pilier read(int id) throws SQLException {
        Criterias criterias = new Criterias();
        criterias.addCriteria(new Criteria("id", "=", id));
        List<Pilier> piliers = pilierCRUD.read(criterias);

        return piliers.get(0);
    }


    public List<Pilier> read(HttpServletRequest request) throws SQLException, IllegalAccessException, DatabaseException, InvocationTargetException {
        Criterias criterias = RequestParser.getCriterias(request);
        List<String> fields = RequestParser.getFields(request);
        List<Pilier> piliers = null;
        if(criterias == null && fields == null)
           piliers =  pilierCRUD.read();
        else if(criterias!= null && fields==null)
            piliers = pilierCRUD.read(criterias);
        else if(criterias== null && fields!=null)
            piliers = pilierCRUD.read(fields);
        else
            piliers = pilierCRUD.read(criterias, fields);

        return piliers;
    }


//    private List<PilierUWs> convertPilerToPilierUWS(List<Pilier> piliers){
//        List<PilierUWs> pilierUWss = new ArrayList<>();
//        for(Pilier pilier: piliers){
//           PilierUWs pilierUWs = new PilierUWs();
//        }
//    }

}
