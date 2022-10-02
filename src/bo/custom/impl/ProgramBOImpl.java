package bo.custom.impl;

import bo.custom.ProgramBO;
import dao.DAOFactory;
import dao.custom.ProgramDAO;
import dto.ProgramDTO;
import entity.Program;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {
    private final ProgramDAO programDAO= (ProgramDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public boolean addProgram(ProgramDTO dto) throws IOException {
        return programDAO.add(new Program(dto.getId(),
                dto.getName(),
                dto.getDuration(),
                dto.getFee()));
    }

    @Override
    public boolean updateProgram(ProgramDTO programDTO) throws IOException {
        return programDAO.update(new Program(
                programDTO.getId(),
                programDTO.getName(),
                programDTO.getDuration(),
                programDTO.getFee()
        ));
    }

    @Override
    public boolean deleteProgram(String id) throws IOException {
        return programDAO.delete(id);
    }

    @Override
    public List<ProgramDTO> getAllPrograms() throws IOException {
        List<ProgramDTO> allPrograms=new ArrayList<>();
        List<Program> all = programDAO.getAll();
        for (Program program:all){
            allPrograms.add(new ProgramDTO(program.getId(),program.getName(),program.getDuration(),program.getFee()));
        }
        return allPrograms;
    }


}
