package Course.request;

import lombok.Data;
@Data
public class CreateStudentRequest extends Object{
    private String name;
    private String surname;
    private CreateStudentInfoRequest studentInfo;

    @Override
    public String toString() {
        return "CreateStudentRequest{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", studentInfo=" + studentInfo +
                '}';
    }
}
