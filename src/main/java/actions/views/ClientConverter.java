package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.Client;

public class ClientConverter {

    public static Client toModel(ClientView cv) {
        return new Client(
                cv.getId(),
                EmployeeConverter.toModel(cv.getEmployee()),
                cv.getName(),
                cv.getDeleteFlag() == null
                ? null
                : cv.getDeleteFlag() == AttributeConst.CLI_DEL_FLAG_TRUE.getIntegerValue()
                        ? JpaConst.CLI_DEL_TRUE
                        : JpaConst.CLI_DEL_FALSE);

    }

    public static ClientView toView(Client c) {

        if (c == null) {
            return null;
        }

        return new ClientView(
                c.getId(),
                EmployeeConverter.toView(c.getEmployee()),
                c.getName(),
                c.getDeleteFlag() == null
                ? null
                : c.getDeleteFlag() == AttributeConst.CLI_DEL_FLAG_TRUE.getIntegerValue()
                        ? JpaConst.CLI_DEL_TRUE
                        : JpaConst.CLI_DEL_FALSE);
}

    public static List<ClientView> toViewList(List<Client> list) {
        List<ClientView> cvs = new ArrayList<>();

        for (Client c : list) {
            cvs.add(toView(c));
        }

        return cvs;
}

    public static void copyViewToModel(Client c, ClientView cv) {
        c.setId(cv.getId());
        c.setEmployee(EmployeeConverter.toModel(cv.getEmployee()));
        c.setName(cv.getName());
        c.setDeleteFlag(cv.getDeleteFlag());

    }
}