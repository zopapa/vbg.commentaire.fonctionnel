package com.bootcamp.controllers;

import com.bootcamp.commons.exceptions.DatabaseException;
import com.bootcamp.commons.ws.models.SecteurWs;
import com.bootcamp.commons.ws.models.SecteurWss;
import com.bootcamp.commons.ws.models.Error;
import com.bootcamp.entities.Secteur;
import com.bootcamp.services.SecteurService;
import com.bootcamp.version.ApiVersions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;


@RestController("SecteurController")
@RequestMapping("/Secteur")
@Api(value = "Secteur API", description = "Secteur API")
public class SecteurController {

    @Autowired
    SecteurService secteurService;
    @Autowired
    HttpServletRequest request;

    @RequestMapping(method = RequestMethod.POST, value = "/")
    @ApiVersions({"1.0"})
    @ApiOperation(value = "Create a new Secteur", notes = "Create a new Secteur")
    public ResponseEntity<SecteurWs> create(@RequestBody @Valid Secteur Secteur) {

        SecteurWs SecteurWs = new SecteurWs();
        HttpStatus httpStatus = null;

        try {
            secteurService.create(Secteur);
            SecteurWs.setData(Secteur);
            httpStatus = HttpStatus.OK;
        }catch (SQLException exception){
            String errorMessage = exception.getMessage()==null?exception.getMessage():exception.getCause().getMessage();
            Error error = new Error();
            error.setMessage(errorMessage);
            SecteurWs.setError(error);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<SecteurWs>(SecteurWs, httpStatus);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/")
    @ApiVersions({"1.0"})
    @ApiOperation(value = "Update a new Secteur", notes = "Update a new Secteur")
    public ResponseEntity<SecteurWs> update(@RequestBody @Valid Secteur Secteur) {

        SecteurWs SecteurWs = new SecteurWs();
        HttpStatus httpStatus = null;

        try {
            secteurService.update(Secteur);
            SecteurWs.setData(Secteur);
            httpStatus = HttpStatus.OK;
        }catch (SQLException exception){
            String errorMessage = exception.getMessage()==null?exception.getMessage():exception.getCause().getMessage();
            Error error = new Error();
            error.setMessage(errorMessage);
            SecteurWs.setError(error);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<SecteurWs>(SecteurWs, httpStatus);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ApiVersions({"1.0"})
    @ApiOperation(value = "Delete a Secteur", notes = "Delete a Secteur")
    public ResponseEntity<SecteurWs> delete(@PathVariable(name = "id") int id) {

        SecteurWs SecteurWs = new SecteurWs();
        HttpStatus httpStatus = null;

        try {
            Secteur Secteur = secteurService.delete(id);
            SecteurWs.setData(Secteur);
            httpStatus = HttpStatus.OK;
        }catch (SQLException exception){
            String errorMessage = exception.getMessage()==null?exception.getMessage():exception.getCause().getMessage();
            Error error = new Error();
            error.setMessage(errorMessage);
            SecteurWs.setError(error);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<SecteurWs>(SecteurWs, httpStatus);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ApiVersions({"1.0"})
    @ApiOperation(value = "Read a Secteur", notes = "Read a Secteur")
    public ResponseEntity<SecteurWs> read(@PathVariable(name = "id") int id) {

        SecteurWs SecteurWs = new SecteurWs();
        HttpStatus httpStatus = null;

        try {
            Secteur Secteur = secteurService.read(id);
            SecteurWs.setData(Secteur);
            httpStatus = HttpStatus.OK;
        }catch (SQLException exception){
            String errorMessage = exception.getMessage()==null?exception.getMessage():exception.getCause().getMessage();
            Error error = new Error();
            error.setMessage(errorMessage);
            SecteurWs.setError(error);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<SecteurWs>(SecteurWs, httpStatus);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ApiVersions({"1.0"})
    @ApiOperation(value = "Read a Secteur", notes = "Read a Secteur")
    public ResponseEntity<SecteurWss> read() {

        SecteurWss SecteurWss = new SecteurWss();
        HttpStatus httpStatus = null;

        try {
            List<Secteur> secteurs = secteurService.read(request);
            SecteurWss.setData(secteurs);
            httpStatus = HttpStatus.OK;
        }catch (SQLException | IllegalAccessException | DatabaseException | InvocationTargetException exception){
            String errorMessage = exception.getMessage()==null?exception.getMessage():exception.getCause().getMessage();
            Error error = new Error();
            error.setMessage(errorMessage);
            SecteurWss.setError(error);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<SecteurWss>(SecteurWss, httpStatus);
    }
}
