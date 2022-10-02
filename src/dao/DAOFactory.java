package dao;

import dao.custom.impl.ProgramDAOImpl;
import dao.custom.impl.QueryDAOImpl;
import dao.custom.impl.RegistrationDetailsDAOImpl;
import dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }
    public static DAOFactory getDaoFactory(){
        if (daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case PROGRAM:
                return new ProgramDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case REGISTRATIONDETAIL:
                return new RegistrationDetailsDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        STUDENT, PROGRAM, REGISTRATIONDETAIL, QUERY
    }
}
