package rc.legostore.persistence;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import rc.legostore.model.DeliveryInfo;
import rc.legostore.model.LegoSet;
import rc.legostore.model.LegoSetDifficulty;
import rc.legostore.model.ProductReview;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

@Service
public class DbSeeder implements CommandLineRunner {
    private MongoTemplate mongoTemplate;

    public DbSeeder(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        /* Add Lego Sets */
        this.mongoTemplate.dropCollection(LegoSet.class);
        this.mongoTemplate.insertAll(getInitialLegosets());
    }

    private Collection<LegoSet> getInitialLegosets() {
        LegoSet milleniumFalcon = new LegoSet( "Millenium Falcon", "Star Wars", LegoSetDifficulty.HARD,
                new DeliveryInfo(LocalDate.now().plusDays(1), 30, true),
                Arrays.asList(new ProductReview("Dan", 7), new ProductReview("Tom", 5), new ProductReview("John", 10)));

        LegoSet skyPolice = new LegoSet( "Sky Police Air Base", "City", LegoSetDifficulty.EASY,
                new DeliveryInfo(LocalDate.now().plusDays(3), 80, true),
                Arrays.asList(new ProductReview("Victor", 10), new ProductReview("Stefan", 10), new ProductReview("Dan", 8)));

        LegoSet mcLarenSenna = new LegoSet( "McLarenSenna", "SpeedChampions", LegoSetDifficulty.MEDIUM,
                new DeliveryInfo(LocalDate.now().plusDays(2), 40, false),
                Arrays.asList(new ProductReview("Mihai", 7), new ProductReview("George", 3), new ProductReview("Dumitru", 3)));

        LegoSet mindstormEye = new LegoSet( "Mindstorms EV3", "Mindstorms", LegoSetDifficulty.HARD,
                new DeliveryInfo(LocalDate.now().plusDays(5), 120, false),
                Arrays.asList(new ProductReview("Cosmin", 9), new ProductReview("Jane", 8), new ProductReview("James", 6)));

        return Arrays.asList(milleniumFalcon, skyPolice, mcLarenSenna, mindstormEye);
    }
}
