package com.bootcamp.services;

import com.bootcamp.commons.constants.DatabaseConstants;
import com.bootcamp.commons.exceptions.DatabaseException;
import com.bootcamp.commons.models.Criteria;
import com.bootcamp.commons.models.Criterias;
import com.bootcamp.commons.ws.models.CommentaireUWs;
import com.bootcamp.commons.ws.utils.RequestParser;
import com.bootcamp.crud.CommentaireCRUD;
import com.bootcamp.entities.Commentaire;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by darextossa on 11/27/17.
 */
@Component
public class CommentaireService implements DatabaseConstants {

    public int create(CommentaireUWs commentaireUWs) throws SQLException {
        Commentaire commentaire = new Commentaire();
        commentaire.setId(commentaireUWs.getId());
        commentaire.setContenu(commentaireUWs.getContenu());
        commentaire.setEntityType(commentaireUWs.getEntityType());
        commentaire.setPseudo(commentaireUWs.getPseudo());
        commentaire.setUserId(commentaireUWs.getUserId());
        commentaire.setUserMail(commentaireUWs.getUserMail());
        commentaire.setDateCreation(commentaireUWs.getDateCreation());
        commentaire.setDateMiseAJour(commentaireUWs.getDateMiseAJour());
        
        CommentaireCRUD.create(commentaire);
        return commentaire.getId();
    }

    public void update(CommentaireUWs commentaireUWs) throws SQLException {
        Commentaire commentaire = new Commentaire();
        commentaire.setId(commentaireUWs.getId());
        commentaire.setEntityId(commentaireUWs.getEntityId());
        commentaire.setContenu(commentaireUWs.getContenu());
        commentaire.setEntityType(commentaireUWs.getEntityType());
        commentaire.setPseudo(commentaireUWs.getPseudo());
        commentaire.setDateCreation(commentaireUWs.getDateCreation());
        commentaire.setDateMiseAJour(commentaireUWs.getDateMiseAJour());
        
        CommentaireCRUD.update(commentaire);
    }

    public Commentaire delete(int id) throws SQLException {
        Commentaire commentaire = read(id);
        CommentaireCRUD.delete(commentaire);

        return commentaire;
    }

    public Commentaire read(int id) throws SQLException {
        Criterias criterias = new Criterias();
        criterias.addCriteria(new Criteria("id", "=", id));
        List<Commentaire> commentaires = CommentaireCRUD.read(criterias);

        return commentaires.get(0);
    }

    public List<Commentaire> read(HttpServletRequest request) throws SQLException, IllegalAccessException, DatabaseException, InvocationTargetException {
        Criterias criterias = RequestParser.getCriterias(request);
        List<String> fields = RequestParser.getFields(request);
        List<Commentaire> commentaires = null;
        if (criterias == null && fields == null) {
            commentaires = CommentaireCRUD.read();
        } else if (criterias != null && fields == null) {
            commentaires = CommentaireCRUD.read(criterias);
        } else if (criterias == null && fields != null) {
            commentaires = CommentaireCRUD.read(fields);
        } else {
            commentaires = CommentaireCRUD.read(criterias, fields);
        }

        return commentaires;
    }
}
