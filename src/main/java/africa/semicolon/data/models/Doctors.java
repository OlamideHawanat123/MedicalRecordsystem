package africa.semicolon.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "Doctors")
public class Doctors extends User{
    private boolean isAvailable = true;


}
