package iq.ven.portal.consensus.controllers.project;

import iq.ven.portal.consensus.controllers.AbstractController;
import iq.ven.portal.consensus.controllers.project.payload.CreateProjectRequest;
import iq.ven.portal.consensus.database.project.model.Project;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/projects")
public class ProjectCreationResourceController extends AbstractController {


    @ResponseBody
    @RequestMapping(value = {"/create-project-request"}, method = RequestMethod.POST)
    public Map<String, Object> createProject(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                             @Valid CreateProjectRequest createProjectRequest) {
        Map<String, Object> result = new HashMap<>();

        Project project = new Project();
        project.setName(createProjectRequest.getName());
        project.setAbbreviation(createProjectRequest.getAbbreviation().toUpperCase());
        project.setDescription(createProjectRequest.getDescription());
        // project.setManager(userDataService.findUserById(projectUser.getUserData().getUserId())); //TODO use in final version
        project.setManager(userDataService.findUsersForAssigneeChange("user").get(0)); //TODO remove
        project.setBoards(Collections.emptyList());
        project.setIssues(Collections.emptyList());


        Project savedProject = projectDataService.saveProject(project);
        Project savedProjectFromDB = projectDataService.findProjectByAbbreviation(project.getAbbreviation());

        if (savedProjectFromDB != null && !StringUtils.isEmpty(savedProjectFromDB.getAbbreviation())) {
            String redirectUrl = "/project/" + savedProjectFromDB.getAbbreviation();
            result.put("redirectURL", redirectUrl);
            result.put("success", true);
        } else {
            result.put("error", true);
            result.put("errorMessage", "Project isn't created, probably Abbreviation already exists.");
        }


        return result;
    }
}
