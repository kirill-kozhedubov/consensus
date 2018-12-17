package iq.ven.portal.consensus.common.viewconvertors;

import iq.ven.portal.consensus.database.issue.model.Issue;
import iq.ven.portal.consensus.database.project.model.Project;
import iq.ven.portal.consensus.database.user.model.User;

import java.util.*;

public class ProjectViewConverter {

    public static Map<String, Object> convertProject(Project project, boolean isLightweight) {
        Long projectId = project.getId();
        String name = project.getName();
        String abbr = project.getAbbreviation();
        String description = project.getDescription();
        Date createdDate = project.getCreatedDate();
        Date updatedDate = project.getUpdatedDate();

        User manager = project.getManager();
        String managerName = manager.getFullNameWithUsername();

        Map<String, Object> projectMap = new HashMap<>();

        if (!isLightweight) {
            List<Issue> issues = project.getIssues();
            List<Map<String, Object>> issuesConverted = IssueViewConverter.convertIssues(issues, true);
            projectMap.put("issues", issuesConverted);
        }

        projectMap.put("name", name);
        projectMap.put("description", description);
        projectMap.put("abbr", abbr);
        projectMap.put("manager", managerName);
        projectMap.put("id", projectId);
        projectMap.put("createdDate", createdDate);
        projectMap.put("updatedDate", updatedDate);
        return projectMap;
    }


    public static List<Map<String, Object>> convertProjects(List<Project> projects, boolean isLightweight) {
        List<Map<String, Object>> projectsList = new ArrayList<>();
        for (Project project : projects) {
            Map<String, Object> projectMap = convertProject(project, isLightweight);
            projectsList.add(projectMap);
        }
        return projectsList;
    }

}
