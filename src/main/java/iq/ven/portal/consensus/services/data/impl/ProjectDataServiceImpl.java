package iq.ven.portal.consensus.services.data.impl;

import iq.ven.portal.consensus.controllers.project.payload.ProjectCreationRequest;
import iq.ven.portal.consensus.database.project.model.Project;
import iq.ven.portal.consensus.database.project.repository.ProjectRepository;
import iq.ven.portal.consensus.services.data.ProjectDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("projectDataService")
public class ProjectDataServiceImpl implements ProjectDataService{

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project getProjectByAbbreviation(String abbreviation) {
        Project project = projectRepository.findByAbbreviation(abbreviation);
        return project;
    }

    @Override
    public Project getProjectById(long projectId) {
        Project project = projectRepository.findById(projectId);
        return project;
    }

    public Project saveProject(ProjectCreationRequest projectRequest) {
        Project project = new Project();
        return projectRepository.save(project);
    }

    public Project loadProjectById(long id) {
        return projectRepository.findById(id);
    }

    public Project loadProjectNyAbbreviation(String abbreviation) {
        return projectRepository.findByAbbreviation(abbreviation);
    }

}
