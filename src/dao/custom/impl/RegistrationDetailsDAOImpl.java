package dao.custom.impl;

import dao.custom.RegistrationDetailsDAO;
import entity.Program;
import entity.RegisterDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.IOException;
import java.util.List;

public class RegistrationDetailsDAOImpl implements RegistrationDetailsDAO {
    @Override
    public boolean add(RegisterDetails registerDetails) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(registerDetails);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(RegisterDetails registerDetails) throws IOException {
        return false;
    }

    @Override
    public boolean delete(String s) throws IOException {
        return false;
    }

    @Override
    public List<RegisterDetails> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from RegisterDetails ");
        List <RegisterDetails> list = query.list();

        transaction.commit();
        session.close();
        return list;
    }
}
