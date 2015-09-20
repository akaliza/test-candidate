package com.test.candidate.service.dto;

import com.test.candidate.persistence.entity.Candidate;

/**
 * Created by florakalisa on 9/20/15.
 */
public class CandidateDTO {

    private int id;
    private String name;
    private boolean enabled;

    public CandidateDTO() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
