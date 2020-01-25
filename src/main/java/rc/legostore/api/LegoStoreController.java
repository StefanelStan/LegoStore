package rc.legostore.api;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rc.legostore.model.LegoSet;
import rc.legostore.model.LegoSetDifficulty;
import rc.legostore.persistence.LegoSetRepository;

import java.util.Collection;

@RestController
@RequestMapping("legostore/api")
public class LegoStoreController {
    // private MongoTemplate mongoTemplate;
    private LegoSetRepository legoSetRepository;
    public LegoStoreController(LegoSetRepository legoSetRepository) {
        // this.mongoTemplate = mongoTemplate;
        this.legoSetRepository =  legoSetRepository;
    }

    @PostMapping
    public void insert(@RequestBody LegoSet legoSet) {
        this.legoSetRepository.insert(legoSet);
    }

    @GetMapping("/all")
    public Collection<LegoSet> getAll(){
        final Sort sortByTheme = Sort.by("theme").ascending();
        return this.legoSetRepository.findAll(sortByTheme);
    }

    @PutMapping
    public void update(@RequestBody LegoSet legoSet) {
        this.legoSetRepository.save(legoSet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.legoSetRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public LegoSet findById(@PathVariable String id){
        return this.legoSetRepository.findById(id).orElse(null);
    }

    @GetMapping("/byTheme/{theme}")
    public Collection<LegoSet> byTheme(@PathVariable String theme) {
        final Sort sortByTheme = Sort.by("theme").ascending();
        return this.legoSetRepository.findAllByThemeContains(theme, sortByTheme);
    }

    @GetMapping("/byNameAndDifficulty/{name}/{difficulty}")
    public Collection<LegoSet> getByNameAndDifficulty(@PathVariable String name, @PathVariable String difficulty) {
        return this.legoSetRepository.findAllByNameStartingWithAndDifficultyEquals(name, LegoSetDifficulty.valueOf(difficulty));
    }

    @GetMapping("/byDelivertFeeLessThan/{price}")
    public Collection<LegoSet> getByDelivertFeeLessThan(@PathVariable int price){
        final Sort sortByTheme = Sort.by("theme").ascending();
        return this.legoSetRepository.findAllByDeliveryFeeLessThan(price, sortByTheme);
    }

    @GetMapping("/byReviewRatingEqualTo/{rating}")
    public Collection<LegoSet> findAllByReviewsRatingEqualTo(@PathVariable int rating) {
        return this.legoSetRepository.findAllByReviewsRatingEqualTo(rating);
    }

    @GetMapping("/byThemeNotContaining/{theme}")
    public Collection<LegoSet> findAllByThemeNotContaining(@PathVariable String theme) {
        return this.legoSetRepository.findAllByThemeIsNotContaining(theme);
    }

    @GetMapping("/byDeliveryInStock/{inStock}")
    public Collection<LegoSet> findAllByDeliveryInfoInStock(@PathVariable String inStock) {
        return this.legoSetRepository.findAllByDeliveryInfoInStock(Boolean.valueOf(inStock));
    }
}
