package rc.legostore.persistence;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import rc.legostore.model.LegoSet;
import rc.legostore.model.LegoSetDifficulty;

import java.util.Collection;

@Repository
public interface LegoSetRepository extends MongoRepository<LegoSet, String> {

    Collection<LegoSet> findAllByThemeContains(String theme, Sort sortByTheme);

    Collection<LegoSet> findAllByNameStartingWithAndDifficultyEquals(String name, LegoSetDifficulty difficulty);

    @Query("{'delivery.deliveryFee' : {$lt : ?0}}")
    Collection<LegoSet> findAllByDeliveryFeeLessThan(int price, Sort sortByTheme);

    @Query("{'reviews.rating' : {$eq : ?0}}")
    Collection<LegoSet> findAllByReviewsRatingEqualTo(int rating);

    Collection<LegoSet> findAllByThemeIsNotContaining(String theme);

    @Query("{'delivery.inStock' : {$eq : ?0}}")
    Collection<LegoSet> findAllByDeliveryInfoInStock(boolean inStock);
}
