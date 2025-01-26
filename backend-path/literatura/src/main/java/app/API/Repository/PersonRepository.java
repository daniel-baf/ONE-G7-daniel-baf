package app.API.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import app.API.Domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
