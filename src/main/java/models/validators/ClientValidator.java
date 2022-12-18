package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.ClientView;
import constants.MessageConst;

public class ClientValidator {
    public static List<String> validate(ClientView cv) {
        List<String> errors = new ArrayList<String>();

        String nameError = validateName(cv.getName());
        if (!nameError.equals("")) {
            errors.add(nameError);
        }

        return errors;
    }

    private static String validateName(String name) {

        if (name == null || name.equals("")) {
            return MessageConst.E_NONAME.getMessage();
        }

        return "";
    }
}

