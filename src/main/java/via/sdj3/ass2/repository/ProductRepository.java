package via.sdj3.ass2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import via.sdj3.ass2.model.Animal;
import via.sdj3.ass2.model.Product;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query(
            value = "SELECT distinct product_id from product join part_in_product pip on product.product_id = pip.product_product_id join part p on pip.parts_part_id = p.part_id join animal a on a.reg_num = p.og_animal_reg_num where reg_num = ?1",
            nativeQuery = true)
    public List<Integer> findProductsIdsByAnimalRegIdInvolved(int animalRegId);

    public List<Product> findProductsByProductIdIn(Collection<Integer> productId);

    @Query(
            value = "Select distinct reg_num from animal join part p on animal.reg_num = p.og_animal_reg_num join part_in_product pip on p.part_id = pip.parts_part_id join product p2 on p2.product_id = pip.product_product_id where p2.product_id = ?1",
            nativeQuery = true)
    public List<Integer> findAnimalRegIdsInvolvedInProductByProductId(int productId);
}
