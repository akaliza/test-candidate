package com.test.candidate.service;

import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.persistence.repository.CandidateRepository;
import com.test.candidate.service.dto.CandidateDTO;
import com.test.candidate.service.exception.CandidateNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 12/08/15.
 */
@Component
//TODO
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;


    public List<CandidateDTO> getAll(){
        List<CandidateDTO> list = new ArrayList<CandidateDTO>();
        for(Candidate candidate :candidateRepository.findAll()){
            CandidateDTO candidateDTO= new CandidateDTO ();
            candidateDTO.setEnabled(candidate.isEnabled());
            candidateDTO.setName(candidate.getName());
            candidateDTO.setId(candidate.getId());
            list.add(candidateDTO);
        }
       return list;
    }


    public void update(int id, CandidateDTO candidateDTO) throws CandidateNotFoundException {

        Candidate c = candidateRepository.findOne(id);
        if(c==null){
            throw new CandidateNotFoundException("user is not found");
        }

        c.setName(candidateDTO.getName());
        c.setEnabled(candidateDTO.isEnabled());
        candidateRepository.save(c);
    }

    public CandidateDTO add(CandidateDTO candidateDTO) {
        Candidate candidate = new Candidate ();
        candidate.setName(candidateDTO.getName());
        candidate.setEnabled(candidateDTO.isEnabled());
        candidate = candidateRepository.save(candidate);
        candidateDTO.setId(candidate.getId());
        return candidateDTO;
    }

    public void delete(int id) throws CandidateNotFoundException {

        Candidate c = candidateRepository.findOne(id);
        if(c==null){
            throw new CandidateNotFoundException("user is not found");
        }
        candidateRepository.delete(id);
    }




}
