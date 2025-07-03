package africa.semicolon.data.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Patients")
public class Patient extends User{
}
