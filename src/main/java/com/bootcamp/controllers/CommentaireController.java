package com.bootcamp.controllers;

import com.bootcamp.commons.ws.models.CommentaireUWs;
import com.bootcamp.services.CommentaireService;
import com.bootcamp.version.ApiVersions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController("CommentaireController")
@RequestMapping("/commentaire")
@Api(value = "Commentaire API", description = "Commentaire API")
public class CommentaireController {

    @Autowired
    CommentaireService commentaireService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping(method = RequestMethod.POST, value = "/")
    @ApiVersions({"1.0"})
    @ApiOperation(value = "Create a new commentaire", notes = "Create a new commentaire")
    public ResponseEntity<CommentaireUWs> create(@RequestBody @Valid CommentaireUWs commentaireUWs) {

        HttpStatus httpStatus = null;

        int id;
        try {
            id = commentaireService.create(commentaireUWs);
            commentaireUWs.setId(id);
            httpStatus = HttpStatus.OK;
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<CommentaireUWs>(commentaireUWs, httpStatus);
    }
//
//
//    @RequestMapping(method = RequestMethod.PUT, value = "/")
//    @ApiVersions({"1.0"})
//    @ApiOperation(value = "Update a new commentaire", notes = "Update a new commentaire")
//    public ResponseEntity<CommentaireWs> update(@RequestBody @Valid Commentaire commentaire) {
//
//        CommentaireWs commentaireWs = new CommentaireWs();
//        HttpStatus httpStatus = null;
//
//        try {
//            commentaireService.update(commentaire);
//            commentaireWs.setData(commentaire);
//            httpStatus = HttpStatus.OK;
//        }catch (SQLException exception){
//            String errorMessage = exception.getMessage()==null?exception.getMessage():exception.getCause().getMessage();
//            Error error = new Error();
//            error.setMessage(errorMessage);
//            commentaireWs.setError(error);
//            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//
//        return new ResponseEntity<CommentaireWs>(commentaireWs, httpStatus);
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
//    @ApiVersions({"1.0"})
//    @ApiOperation(value = "Delete a commentaire", notes = "Delete a commentaire")
//    public ResponseEntity<CommentaireWs> delete(@PathVariable(name = "id") int id) {
//
//        CommentaireWs commentaireWs = new CommentaireWs();
//        HttpStatus httpStatus = null;
//
//        try {
//            Commentaire commentaire = commentaireService.delete(id);
//            commentaireWs.setData(commentaire);
//            httpStatus = HttpStatus.OK;
//        }catch (SQLException exception){
//            String errorMessage = exception.getMessage()==null?exception.getMessage():exception.getCause().getMessage();
//            Error error = new Error();
//            error.setMessage(errorMessage);
//            commentaireWs.setError(error);
//            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//
//        return new ResponseEntity<CommentaireWs>(commentaireWs, httpStatus);
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ApiVersions({"1.0"})
    @ApiOperation(value = "Read a comments", notes = "Read a comments")
    public ResponseEntity<CommentaireUWs> read(@PathVariable(name = "id") int id) {

        CommentaireUWs commentaireUWs = new CommentaireUWs();
        HttpStatus httpStatus = null;

        try {
            commentaireUWs = commentaireService.read(id);
            httpStatus = HttpStatus.OK;
        }catch (SQLException ex){
            Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<CommentaireUWs>(commentaireUWs, httpStatus);
    }
    
//    @RequestMapping(method = RequestMethod.GET, value = "/")
//    @ApiVersions({"1.0"})
//    @ApiOperation(value = "Read a commentaire", notes = "Read a commentaire")
//    public ResponseEntity<List<Commentaire>> read() throws InvocationTargetException, SQLException, DatabaseException, IllegalAccessException {
//        List<Commentaire> commentaires = commentaireService.read(request);
//        return new ResponseEntity<List<Commentaire>>(commentaires, HttpStatus.OK);
//    }

}
