package iq.ven.portal.consensus.controllers;

import iq.ven.portal.consensus.common.beans.ProjectUser;
import iq.ven.portal.consensus.common.beans.UserState;
import iq.ven.portal.consensus.common.util.helpers.TemplatesHelper;
import iq.ven.portal.consensus.services.data.BoardsDataService;
import iq.ven.portal.consensus.services.data.IssueDataService;
import iq.ven.portal.consensus.services.data.ProjectDataService;
import iq.ven.portal.consensus.services.data.UserDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.net.ConnectException;

public class AbstractController {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractController.class);

    @Autowired
    protected ProjectUser projectUser;

    @Autowired
    protected UserState userState;

    @Autowired
    protected UserDataService userDataService;

    @Autowired
    protected IssueDataService issueDataService;

    @Autowired
    protected ProjectDataService projectDataService;

    @Autowired
    protected BoardsDataService boardsDataService;


    @ExceptionHandler
    public String handleException(HttpServletRequest request,
                                  HttpServletResponse response,
                                  Writer writer,
                                  Exception exception) throws IOException {
        logger.error("Default annotation error handler", exception);

        String message = null;
        if (exception instanceof Exception) {
            message = exception.getCause().getMessage();
            response.setStatus(HttpStatus.BAD_GATEWAY.value());
        } else {
            if (isCausedBy(exception, ConnectException.class)) {
                message = "Service Temporarily Unavailable";
                response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
            } else {
                message = "Something went wrong. Try again later.";
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        }
        if (message != null) {
            writer.append(message);
        }
        logger.error("AbstractController." + exception.getClass(), exception);
        return message;
    }

    private static boolean isCausedBy(Throwable exception, Class<? extends Throwable> cause_class) {
        if (exception != null && exception.getClass().equals(cause_class)) {
            return true;
        } else if (exception != null) {
            return isCausedBy(exception.getCause(), cause_class);
        }
        return false;
    }

    private static ModelAndView redirectToPageNotFound() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:error/not-found");
        modelAndView.addObject(TemplatesHelper.PAGE_TITLE, "Page not found");

        return modelAndView;
    }


}
