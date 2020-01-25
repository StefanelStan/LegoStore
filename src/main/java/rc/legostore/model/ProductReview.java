package rc.legostore.model;

public class ProductReview {
    private String userName;
    private int rating;

    public ProductReview(final String userName, final int rating) {
        this.userName = userName;
        this.rating = rating;
    }

    public String getUserName() {
        return userName;
    }

    public int getRating() {
        return rating;
    }
}
