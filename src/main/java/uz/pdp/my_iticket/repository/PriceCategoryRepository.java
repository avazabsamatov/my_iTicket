package uz.pdp.my_iticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.my_iticket.model.PriceCategory;

import java.util.UUID;

@RepositoryRestResource(path = "price-category",collectionResourceRel = "price-categories", itemResourceRel = "price-category")
public interface PriceCategoryRepository extends JpaRepository<PriceCategory, UUID> {
    PriceCategory findPriceCategoriesById(UUID id);
}
