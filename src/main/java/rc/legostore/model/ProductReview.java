package rc.legostore.model;

public class ProductReview {
    private String username;
    private int rating;

    public ProductReview(final String username, final int rating) {
        this.username = username;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public int getRating() {
        return rating;
    }
}
