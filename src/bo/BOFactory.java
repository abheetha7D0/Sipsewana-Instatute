package bo;

import bo.custom.impl.ProgramBOImpl;
import bo.custom.impl.RegistrationDetailBOImpl;
import bo.custom.impl.ReportBOImpl;
import bo.custom.impl.StudentBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }
    public static BOFactory getBoFactory(){
        if (boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }
    public enum BOTypes{
        PROGRAM,STUDENT , REGISTRATIONDETAIL,REPORT
    }
    public SuperBO getBo(BOTypes boTypes){
        switch (boTypes) {
            case PROGRAM:
                return new ProgramBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case REGISTRATIONDETAIL:
                return new RegistrationDetailBOImpl();
            case REPORT:
                return new ReportBOImpl();

            default:
                return null;
        }
    }
}
