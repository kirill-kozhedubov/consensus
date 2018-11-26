package iq.ven.portal.consensus.controllers.issues.create.payload;

public class ProjectAvailableForIssue {

    private String name;
    private Long id;
    private String abbreviation;
    private String description;
    private String managerName;

    public ProjectAvailableForIssue(String name, Long id, String abbreviation, String description, String managerName) {
        this.name = name;
        this.id = id;
        this.abbreviation = abbreviation;
        this.description = description;
        this.managerName = managerName;
    }

    public ProjectAvailableForIssue() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}
