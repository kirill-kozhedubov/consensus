package iq.ven.portal.consensus.database.project.model;

import iq.ven.portal.consensus.database.Base;
import iq.ven.portal.consensus.database.issue.model.Issue;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
@PrimaryKeyJoinColumn(name="id")
public class Project extends Base {

    private String abbreviation;

    private Issue baseIssue;

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
