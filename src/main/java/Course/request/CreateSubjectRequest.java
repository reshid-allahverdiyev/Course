package Course.request;

import lombok.Data;

@Data
public class CreateSubjectRequest {

    private String name;


    @Override
    public String toString() {
        return "CreateSubjectRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
