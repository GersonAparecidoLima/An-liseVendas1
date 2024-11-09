public class Sale {
    private Integer month;
    private Integer year;
    private String seller;
    private Integer quantity;
    private Double total;

    // Construtor
    public Sale(Integer month, Integer year, String seller, Integer quantity, Double total) {
        this.month = month;
        this.year = year;
        this.seller = seller;
        this.quantity = quantity;
        this.total = total;
    }

    // Getters
    public Integer getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    public String getSeller() {
        return seller;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getTotal() {
        return total;
    }

    // Cálculo do preço médio
    public Double averagePrice() {
        return total / quantity;
    }

    @Override
    public String toString() {
        return String.format("%d/%d, %s, %d, %.2f, pm = %.2f", 
                month, year, seller, quantity, total, averagePrice());
    }
}
