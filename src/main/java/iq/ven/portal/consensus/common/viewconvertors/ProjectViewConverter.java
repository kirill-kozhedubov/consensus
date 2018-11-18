package iq.ven.portal.consensus.common.viewconvertors;

import iq.ven.portal.consensus.database.issue.model.Issue;
import iq.ven.portal.consensus.database.project.model.Project;
import iq.ven.portal.consensus.database.user.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectViewConverter {

    public static Map<String, Object> convertProject(Project project, boolean isLightweight) {

        String name = project.getName();
        String abbr = project.getAbbreviation();
        String description = project.getDescription();

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

        return projectMap;
    }


    public static List<Map<String, Object>> convertProjects(List<Project> projects, boolean isLightweight) {
        List<Map<String, Object>> projectsList = new ArrayList<>();

        for (Project project : projects) {
            Map<String, Object> issueMap = convertProject(project, isLightweight);
            projectsList.add(issueMap);
        }

        return projectsList;
    }

}
