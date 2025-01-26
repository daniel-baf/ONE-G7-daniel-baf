package app.API.Controller;

import app.API.Domain.Person;
import app.API.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonController {

    private final PersonRepository personRepository;

    // Constructor injection of the PersonRepository
    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void savePerson(Person person) {
        this.personRepository.save(person);
    }

    public Person getPersonById(Integer id) {
        return this.personRepository.findById(id).orElse(null); // Handle Optional
    }
}
