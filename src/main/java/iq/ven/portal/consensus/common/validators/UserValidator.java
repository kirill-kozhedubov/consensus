package iq.ven.portal.consensus.common.validators;

import iq.ven.portal.consensus.controllers.authentication.payload.UserForRegistration;

public class UserValidator {

    public static boolean validateUserDataForRegistration(UserForRegistration user) {
        boolean validationResult = false;
        if (user != null &&
                user.getEmail() != null && user.getEmail().contains("@") &&
                user.getFirstName() != null &&
                user.getLastName() != null &&
                user.getPassword() != null && user.getPassword().length() >= 6) {
            validationResult = true;
        }
        return validationResult;
    }
}
