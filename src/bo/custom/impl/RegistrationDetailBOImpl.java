package bo.custom.impl;

import bo.custom.RegistrationDetailBO;
import dao.DAOFactory;
import dao.custom.ProgramDAO;
import dao.custom.RegistrationDetailsDAO;
import dto.RegistrationDetailsDTO;
import entity.Program;
import entity.RegisterDetails;
import entity.Student;

import java.io.IOException;
import java.util.List;

public class RegistrationDetailBOImpl implements RegistrationDetailBO {
    private final RegistrationDetailsDAO registrationDetailsDAO= (RegistrationDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTRATIONDETAIL);

    @Override
    public boolean addRegistration(RegistrationDetailsDTO dto) throws IOException {
        RegisterDetails registerDetails=new RegisterDetails();

        registerDetails.setDate(dto.getDate());
        registerDetails.setProgram(new Program(dto.getProgramId()));
        registerDetails.setStudent(new Student(dto.getStudentId()));

        registrationDetailsDAO.add(registerDetails);
        return true;
    }

    @Override
    public boolean updateRegistration(RegistrationDetailsDTO dto) throws IOException {
        return false;
    }

    @Override
    public boolean deleteRegistration(String id) throws IOException {
        return false;
    }

    @Override
    public List<RegistrationDetailsDTO> getAllRegistration() throws IOException {
        return null;
    }
}
