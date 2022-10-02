package bo.custom;

import bo.SuperBO;
import dto.ProgramDTO;
import dto.StudentDTO;

import java.io.IOException;
import java.util.List;

public interface StudentBO extends SuperBO {
    boolean addStudent(StudentDTO dto) throws IOException;

    boolean updateStudent(StudentDTO dto) throws IOException;

    boolean deleteStudent(String id) throws IOException;

    List<StudentDTO> getAllStudents() throws IOException;
}
