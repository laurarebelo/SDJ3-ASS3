package via.sdj3.ass2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import via.sdj3.ass2.model.Animal;
import via.sdj3.ass2.model.AnimalDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Integer> {
    @Query(
            value = "select * from slaughterhouse.animal where date(date_time) = ?1",
            nativeQuery = true)
    public List<Animal> findAnimalsByDate(LocalDate date);
    public List<Animal> findAnimalsByFarm(String farm);
    public List<Animal> findAnimalsByRegNumIn(Collection<Integer> regNum);
}
