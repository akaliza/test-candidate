package com.test.candidate.persistence.repository;

import com.test.candidate.persistence.entity.Candidate;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by oleg on 09/08/15.
 */
//TODO create CandidateRepository interface
public interface CandidateRepository extends CrudRepository<Candidate, Integer> {


}
