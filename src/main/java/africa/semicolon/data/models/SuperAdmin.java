package africa.semicolon.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "SuperAdmin")
@EqualsAndHashCode(callSuper=true)

public class SuperAdmin extends User{
}
