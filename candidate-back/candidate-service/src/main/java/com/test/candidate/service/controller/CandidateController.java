package com.test.candidate.service.controller;

import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.service.CandidateService;
import com.test.candidate.service.dto.CandidateDTO;
import com.test.candidate.service.exception.CandidateNotFoundException;
import com.test.candidate.service.exception.InvalidRequestException;
import com.test.candidate.service.resource.error.ErrorInfo;
import com.test.candidate.service.resource.error.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by oleg on 12/08/15.
 */
@RestController
@RequestMapping("/candidate")
//TODO
class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @RequestMapping(method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List<CandidateDTO>>  getAll() {
        List<CandidateDTO> list = candidateService.getAll();
        return new ResponseEntity<List<CandidateDTO>>(list, HttpStatus.OK);
    }


    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<HttpStatus>  update(@PathVariable int id, @RequestBody CandidateDTO candidateDTO) {

        ResponseEntity<HttpStatus> response = null;
        try {
            if(candidateDTO.getName()==null) {
                FieldError[] tab = getFieldError(candidateDTO.getName(), "name", "PUT resource", "NotNull");
                //throw new InvalidRequestException("InvalidRequestException", "PUT resource", tab);
            }
            candidateService.update(id, candidateDTO);
            response = new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (CandidateNotFoundException e) {
            response = new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            response = new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<HttpStatus>  delete(@PathVariable int id) {

        ResponseEntity<HttpStatus> response = null;
        try {
            candidateService.delete(id);
            response = new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (CandidateNotFoundException e) {
            response = new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            response = new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return response;
    }

    @RequestMapping(method=RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<CandidateDTO> add(@RequestBody CandidateDTO candidateDTO) throws InvalidRequestException {
        if(candidateDTO.getName()==null) {
            FieldError[] tabFieldError = getFieldError(candidateDTO.getName(), "name", "POST resource", "NotNull");
            throw new InvalidRequestException("InvalidRequestException", "POST resource", tabFieldError);
        }
        CandidateDTO c = candidateService.add(candidateDTO);
        return new ResponseEntity<CandidateDTO>(c, HttpStatus.OK);


    }

    private FieldError[] getFieldError(String value, String name, String resource, String code) {
        FieldError fieldError = new FieldError();
        fieldError.setField(name);
        fieldError.setResource(resource);
        fieldError.setCode(code);
        fieldError.setRejectedValue(value);
        FieldError[] tab = new FieldError[1];
        tab[0] = fieldError;
        return tab;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRequestException.class)
    @ResponseBody ErrorInfo handleBadRequest(InvalidRequestException ex) {

        List<FieldError> list = new ArrayList<FieldError>();
        FieldError[] fiels = ex.getField();
        for(int i = 0; i< fiels.length; i++){
            FieldError fieldError = new  FieldError();
            fieldError.setResource(fiels[i].getResource());
            fieldError.setField(fiels[i].getField());
            fieldError.setRejectedValue(fiels[i].getRejectedValue());
            fieldError.setCode(fiels[i].getCode());
            list.add(fieldError);

        }

        return new ErrorInfo(ex.getType(), ex.getResource(), list);
    }



}