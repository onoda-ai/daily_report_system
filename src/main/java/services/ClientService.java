package services;

import java.util.List;

import actions.views.ClientConverter;
import actions.views.ClientView;
import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import constants.JpaConst;
import models.Client;
import models.validators.ClientValidator;

public class ClientService extends ServiceBase {


    public List<ClientView> getAllPerPage(int page) {

        List<Client> clients = em.createNamedQuery(JpaConst.Q_CLI_GET_ALL, Client.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ClientConverter.toViewList(clients);
    }

    public List<ClientView> getAllMine(EmployeeView employee) {

        List<Client> clients = em.createNamedQuery(JpaConst.Q_CLI_GET_ALL_MINE, Client.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .getResultList();
        return ClientConverter.toViewList(clients);
    }

    public long countAll() {
        long clients_count = (long) em.createNamedQuery(JpaConst.Q_CLI_COUNT, Long.class)
                .getSingleResult();
        return clients_count;
    }

    public ClientView findOne(int id) {
        return ClientConverter.toView(findOneInternal(id));
    }

    public List<String> create(ClientView cv) {
        List<String> errors = ClientValidator.validate(cv);
        if (errors.size() == 0) {
            createInternal(cv);
        }

        return errors;
    }

    public List<String> update(ClientView cv) {

        List<String> errors = ClientValidator.validate(cv);

        if (errors.size() == 0) {

            updateInternal(cv);
        }

        return errors;
    }

    public void destroy(Integer id) {

        //idを条件に登録済みの従業員情報を取得する
        ClientView savedCli = findOne(id);


        //論理削除フラグをたてる
        savedCli.setDeleteFlag(JpaConst.CLI_DEL_TRUE);

        //更新処理を行う
        update(savedCli);

    }

    private Client findOneInternal(int id) {
        return em.find(Client.class, id);
    }

    private void createInternal(ClientView cv) {

        em.getTransaction().begin();
        em.persist(ClientConverter.toModel(cv));
        em.getTransaction().commit();

    }

    private void updateInternal(ClientView cv) {

        em.getTransaction().begin();
        Client c = findOneInternal(cv.getId());
        ClientConverter.copyViewToModel(c, cv);
        em.getTransaction().commit();

    }

}

