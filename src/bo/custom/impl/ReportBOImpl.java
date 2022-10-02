package bo.custom.impl;

import bo.custom.ReportBO;
import dao.DAOFactory;
import dao.custom.QueryDAO;
import dao.custom.StudentDAO;
import entity.CustomEntity;

import java.io.IOException;
import java.util.List;

public class ReportBOImpl implements ReportBO {
    private final QueryDAO queryDAO= (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public List<CustomEntity> getAllDetails(String id) throws Exception {
        return queryDAO.getRegistrationDetails(id);
    }
}
