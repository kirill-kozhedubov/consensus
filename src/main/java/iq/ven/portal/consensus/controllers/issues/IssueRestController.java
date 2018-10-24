package iq.ven.portal.consensus.controllers.issues;

import iq.ven.portal.consensus.controllers.AbstractController;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class IssueRestController extends AbstractController {

    @RequestMapping(path = "/zulul", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> zulul(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        Map<String, Object> result = new HashMap<>();
        result.put("yayx", "yaix");
        return result;
    }

}
