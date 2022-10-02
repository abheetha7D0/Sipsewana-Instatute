package bo.custom;

import bo.SuperBO;
import dto.ProgramDTO;
import entity.Program;

import java.io.IOException;
import java.util.List;

public interface ProgramBO extends SuperBO {
    boolean addProgram(ProgramDTO dto) throws IOException;

    boolean updateProgram(ProgramDTO programDTO) throws IOException;

    boolean deleteProgram(String id) throws IOException;

    List<ProgramDTO> getAllPrograms() throws IOException;




}
