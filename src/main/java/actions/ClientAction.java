package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.ClientView;
import actions.views.EmployeeView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.ClientService;
import services.EmployeeService;

public class ClientAction extends ActionBase{

    private ClientService service;
    private EmployeeService emservice;

    @Override
    public void process() throws ServletException, IOException {

        service = new ClientService();

        emservice = new EmployeeService();


        invoke();

        service.close();

        emservice.close();
    }

    public void index() throws ServletException, IOException {

            int page = getPage();
            List<ClientView> clients = service.getAllPerPage(page);

            long clientCount = service.countAll();

            putRequestScope(AttributeConst.CLIENTS, clients);
            putRequestScope(AttributeConst.CLI_COUNT, clientCount);
            putRequestScope(AttributeConst.PAGE, page);
            putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);

            String flush = getSessionScope(AttributeConst.FLUSH);
            if (flush != null) {
                putRequestScope(AttributeConst.FLUSH, flush);
                removeSessionScope(AttributeConst.FLUSH);
            }

        forward(ForwardConst.FW_CLI_INDEX);

        }

    public void entryNew() throws ServletException, IOException {

            putRequestScope(AttributeConst.TOKEN, getTokenId());
            putRequestScope(AttributeConst.CLIENT, new ClientView());

            forward(ForwardConst.FW_CLI_NEW);

        }

    public void create() throws ServletException, IOException{

        EmployeeView ev = (EmployeeView) (emservice.findOne(getRequestParam(AttributeConst.EMP_CODE)));

            ClientView cv = new ClientView(
                    null,
                    ev,
                    getRequestParam(AttributeConst.CLI_NAME),
                    AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

            List<String> errors = service.create(cv);

            if(errors.size() > 0){

                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.CLIENT, cv);
                putRequestScope(AttributeConst.ERR, errors);

                forward(ForwardConst.FW_CLI_NEW);

            } else {

                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                redirect(ForwardConst.ACT_CLI, ForwardConst.CMD_INDEX);

            }
        }


    public void show() throws ServletException, IOException{

            ClientView cv = service.findOne(toNumber(getRequestParam(AttributeConst.CLI_ID)));

            if (cv == null || cv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()){

                forward(ForwardConst.FW_ERR_UNKNOWN);
                return;

        }

        putRequestScope(AttributeConst.CLIENT, cv);

        forward(ForwardConst.FW_CLI_SHOW);

    }

    public void edit() throws ServletException, IOException {

            ClientView cv = service.findOne(toNumber(getRequestParam(AttributeConst.CLI_ID)));

            if (cv == null || cv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()){

                forward(ForwardConst.FW_ERR_UNKNOWN);
                return;

        }

        putRequestScope(AttributeConst.TOKEN, getTokenId());
        putRequestScope(AttributeConst.CLIENT, cv);

        forward(ForwardConst.FW_CLI_EDIT);

    }

    public void update() throws ServletException, IOException {

        if (checkToken()) {

            EmployeeView ev = (EmployeeView) (emservice.findOne(getRequestParam(AttributeConst.EMP_CODE)));

            ClientView cv = new ClientView(
                    toNumber(getRequestParam(AttributeConst.CLI_ID)),
                    ev,
                    getRequestParam(AttributeConst.CLI_NAME),
                    AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

            List<String> errors = service.update(cv);

            if (errors.size() > 0) {

                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.CLIENT, cv);
                putRequestScope(AttributeConst.ERR, errors);

                forward(ForwardConst.FW_CLI_EDIT);
            } else {

                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                redirect(ForwardConst.ACT_CLI, ForwardConst.CMD_INDEX);

            }
        }
    }

    public void destroy() throws ServletException, IOException {

        if (checkToken()) {

            service.destroy(toNumber(getRequestParam(AttributeConst.CLI_ID)));

            putSessionScope(AttributeConst.FLUSH, MessageConst.I_DELETED.getMessage());

            redirect(ForwardConst.ACT_CLI, ForwardConst.CMD_INDEX);
        }
    }
}
