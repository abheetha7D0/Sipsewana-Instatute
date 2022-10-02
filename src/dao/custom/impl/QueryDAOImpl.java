package dao.custom.impl;

import dao.custom.QueryDAO;
import entity.CustomEntity;
import dao.custom.ProgramDAO;
import entity.Program;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<CustomEntity> getRegistrationDetails(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        String hql="SELECT s.id,s.name,r.date FROM Student s INNER JOIN RegisterDetails r ON s.id = r.student where r.program=:program";
        Query query = session.createQuery(hql);
        query.setParameter("program",session.load(Program.class,id));
        List<Object[]>list = query.list();
        List<CustomEntity>all=new ArrayList<>();
        for(Object[]objects:list){

            all.add(new CustomEntity(objects[0].toString(),objects[1].toString(),objects[2].toString()));
        }
        transaction.commit();
        session.close();
        return all;
    }
}
