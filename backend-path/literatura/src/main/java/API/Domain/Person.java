package API.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Person {

    @JsonProperty("name")
    private String name;

    @JsonProperty("birth_year")
    private String birthYear; // Cambia "birth_year" por "birthYear"

    @JsonProperty("death_year")
    private String deathYear;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID
    @JsonProperty("id")
    private int id; // Agrega "id" para que Jackson pueda mapearlo correctamente
}
