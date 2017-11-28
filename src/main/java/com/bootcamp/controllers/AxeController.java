package com.bootcamp.controllers;

import com.bootcamp.commons.exceptions.DatabaseException;
import com.bootcamp.commons.ws.models.Error;
import com.bootcamp.commons.ws.models.AxeWs;
import com.bootcamp.commons.ws.models.AxeWss;
import com.bootcamp.entities.Axe;
import com.bootcamp.services.AxeService;
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


@RestController("AxeController")
@RequestMapping("/axe")
@Api(value = "Axe API", description = "Axe API")
public class AxeController {

    @Autowired
    AxeService axeService;
    @Autowired
    HttpServletRequest request;

    @RequestMapping(method = RequestMethod.POST, value = "/")
    @ApiVersions({"1.0"})
    @ApiOperation(value = "Create a new axe", notes = "Create a new axe")
    public ResponseEntity<AxeWs> create(@RequestBody @Valid Axe axe) {

        AxeWs axeWs = new AxeWs();
        HttpStatus httpStatus = null;

        try {
            axeService.create(axe);
            axeWs.setData(axe);
            httpStatus = HttpStatus.OK;
        }catch (SQLException exception){
            String errorMessage = exception.getMessage()==null?exception.getMessage():exception.getCause().getMessage();
            Error error = new Error();
            error.setMessage(errorMessage);
            axeWs.setError(error);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<AxeWs>(axeWs, httpStatus);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/")
    @ApiVersions({"1.0"})
    @ApiOperation(value = "Update a new axe", notes = "Update a new axe")
    public ResponseEntity<AxeWs> update(@RequestBody @Valid Axe axe) {

        AxeWs axeWs = new AxeWs();
        HttpStatus httpStatus = null;

        try {
            axeService.update(axe);
            axeWs.setData(axe);
            httpStatus = HttpStatus.OK;
        }catch (SQLException exception){
            String errorMessage = exception.getMessage()==null?exception.getMessage():exception.getCause().getMessage();
            Error error = new Error();
            error.setMessage(errorMessage);
            axeWs.setError(error);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<AxeWs>(axeWs, httpStatus);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ApiVersions({"1.0"})
    @ApiOperation(value = "Delete a axe", notes = "Delete a axe")
    public ResponseEntity<AxeWs> delete(@PathVariable(name = "id") int id) {

        AxeWs axeWs = new AxeWs();
        HttpStatus httpStatus = null;

        try {
            Axe axe = axeService.delete(id);
            axeWs.setData(axe);
            httpStatus = HttpStatus.OK;
        }catch (SQLException exception){
            String errorMessage = exception.getMessage()==null?exception.getMessage():exception.getCause().getMessage();
            Error error = new Error();
            error.setMessage(errorMessage);
            axeWs.setError(error);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<AxeWs>(axeWs, httpStatus);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ApiVersions({"1.0"})
    @ApiOperation(value = "Read a axe", notes = "Read a axe")
    public ResponseEntity<AxeWs> read(@PathVariable(name = "id") int id) {

        AxeWs axeWs = new AxeWs();
        HttpStatus httpStatus = null;

        try {
            Axe axe = axeService.read(id);
            axeWs.setData(axe);
            httpStatus = HttpStatus.OK;
        }catch (SQLException exception){
            String errorMessage = exception.getMessage()==null?exception.getMessage():exception.getCause().getMessage();
            Error error = new Error();
            error.setMessage(errorMessage);
            axeWs.setError(error);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<AxeWs>(axeWs, httpStatus);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ApiVersions({"1.0"})
    @ApiOperation(value = "Read a axe", notes = "Read a axe")
    public ResponseEntity<AxeWss> read() {

        AxeWss axeWss = new AxeWss();
        HttpStatus httpStatus = null;

        try {
            List<Axe> axes = axeService.read(request);
            axeWss.setData(axes);
            httpStatus = HttpStatus.OK;
        }catch (SQLException | IllegalAccessException | DatabaseException | InvocationTargetException exception){
            String errorMessage = exception.getMessage()==null?exception.getMessage():exception.getCause().getMessage();
            Error error = new Error();
            error.setMessage(errorMessage);
            axeWss.setError(error);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<AxeWss>(axeWss, httpStatus);
    }
}
