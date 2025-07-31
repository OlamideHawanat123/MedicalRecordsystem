package africa.semicolon.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "Admins")
public class Admin extends User{
    @Field("admin_is_verified")
    private boolean isVerified = false;
}
