package bo.custom;

import bo.SuperBO;
import dto.RegistrationDetailsDTO;

import java.io.IOException;
import java.util.List;

public interface RegistrationDetailBO extends SuperBO {
    boolean addRegistration(RegistrationDetailsDTO dto) throws IOException;

    boolean updateRegistration(RegistrationDetailsDTO dto) throws IOException;

    boolean deleteRegistration(String id) throws IOException;

    List<RegistrationDetailsDTO> getAllRegistration() throws IOException;
}
