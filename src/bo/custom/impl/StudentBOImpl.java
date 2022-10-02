package bo.custom.impl;

import bo.custom.StudentBO;
import dao.DAOFactory;
import dao.custom.StudentDAO;
import dto.StudentDTO;
import entity.Student;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    private final StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);


    @Override
    public boolean addStudent(StudentDTO dto) throws IOException {
        return studentDAO.add(new Student(dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getContactNo()));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws IOException {
        return studentDAO.update(new Student(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getContactNo()
        ));
    }

    @Override
    public boolean deleteStudent(String id) throws IOException {
        return studentDAO.delete(id);
    }

    @Override
    public List<StudentDTO> getAllStudents() throws IOException {
        List<StudentDTO> allStudents=new ArrayList<>();
        List<Student> all = studentDAO.getAll();
        for (Student student:all){
            allStudents.add(new StudentDTO(student.getId(),student.getName(),student.getAddress(),student.getContactNo()));
        }
        return allStudents;
    }
}
