package iq.ven.portal.consensus.services.data.impl;

import iq.ven.portal.consensus.common.util.utils.UserUtils;
import iq.ven.portal.consensus.controllers.issues.create.payload.ProjectAvailableForIssue;
import iq.ven.portal.consensus.controllers.project.payload.CreateProjectRequest;
import iq.ven.portal.consensus.database.project.model.Project;
import iq.ven.portal.consensus.database.project.repository.ProjectRepository;
import iq.ven.portal.consensus.services.data.ProjectDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("projectDataService")
public class ProjectDataServiceImpl implements ProjectDataService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project findProjectByAbbreviation(String abbreviation) {
        Project project = projectRepository.findByAbbreviation(abbreviation);
        return project;
    }

    @Override
    public Project findProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId);
        return project;
    }

    @Override
    public List<Project> findAllProjects() {
        List<Project> projects = projectRepository.findAll();

        return projects;
    }

    @Override
    public List<ProjectAvailableForIssue> getProjectsAvailableForIssue() {
        List<Project> projects = findAllProjects();
        List<ProjectAvailableForIssue> projectsAvailableForIssue = new ArrayList<>();
        for (Project project : projects) {
            ProjectAvailableForIssue proj = new ProjectAvailableForIssue();

            proj.setAbbreviation(project.getAbbreviation());
            proj.setDescription(project.getDescription());
            proj.setId(project.getId());
            proj.setManagerName(UserUtils.convertUserFullNameWithUsername(project.getManager()));
            proj.setName(project.getName());

            projectsAvailableForIssue.add(proj);
        }


        return projectsAvailableForIssue;
    }

    public Project saveProject(CreateProjectRequest projectRequest) {
        Project project = new Project();
        return projectRepository.save(project);
    }

    public Project loadProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project loadProjectByAbbreviation(String abbreviation) {
        return projectRepository.findByAbbreviation(abbreviation);
    }

}
