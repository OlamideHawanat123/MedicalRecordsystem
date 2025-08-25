package africa.semicolon.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Users")
public class User {
    @Id
    @JsonIgnore
    private String id;
    private int age;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    @JsonIgnore
    private String password;
    @Indexed(unique=true)
    private String email;
    private UserGender gender;
    private UserRoles role;
    private boolean isVerified;
    private boolean isLicensedVerified;
    private String specialization;
    private String licenseId;



}
