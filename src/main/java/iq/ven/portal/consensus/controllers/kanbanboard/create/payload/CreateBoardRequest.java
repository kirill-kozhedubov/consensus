package iq.ven.portal.consensus.controllers.kanbanboard.create.payload;

public class CreateBoardRequest {

    private String name;

    private String description;

    private String projectId;

    public CreateBoardRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}


