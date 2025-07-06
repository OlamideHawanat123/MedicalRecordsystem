package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Users")
public class User {
    @Id
    private String id;
    private int age;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String password;
    @Indexed(unique=true)
    private String email;
    private UserGender gender;
    private UserRoles role;
    private boolean isVerified;


}
