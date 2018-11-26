package iq.ven.portal.consensus.services.data;

import iq.ven.portal.consensus.controllers.issues.create.payload.ProjectAvailableForIssue;
import iq.ven.portal.consensus.database.project.model.Project;

import java.util.List;

public interface ProjectDataService {

    Project saveProject(Project project);

    Project findProjectByAbbreviation(String abbreviation);

    Project findProjectById(Long projectId);

    List<Project> findAllProjects();

    List<ProjectAvailableForIssue> getProjectsAvailableForIssue();
}
