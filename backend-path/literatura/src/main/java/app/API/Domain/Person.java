package app.API.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "person")  // Optional if the table name is the same as the class name
public class Person {

    @JsonProperty("name")
    private String name;

    @JsonProperty("birth_year")
    private String birthYear;

    @JsonProperty("death_year")
    private String deathYear;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generates the ID
    @JsonProperty("id")
    private int id;  // For Jackson to map it correctly
}
