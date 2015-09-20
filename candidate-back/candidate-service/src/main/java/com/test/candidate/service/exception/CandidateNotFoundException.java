package com.test.candidate.service.exception;

/**
 * Created by florakalisa on 9/20/15.
 */
public class CandidateNotFoundException extends Exception {

    public CandidateNotFoundException(String msg) {
        super(msg);
    }
    public CandidateNotFoundException(Throwable e) {
        super(e);
    }
}
