import java.util.ArrayList;
import java.util.List;


public class OrderManager {

   
    public String customerName;
    public String customerEmail;
    public String customerAddress;
    public int customerAge;
    public double customerLoyaltyPoints;

    public List<String> productNames = new ArrayList<>();
    public List<Double> productPrices = new ArrayList<>();
    public List<Integer> productQuantities = new ArrayList<>();
    public List<Integer> productStockLevels = new ArrayList<>();

    public List<String> orderHistory = new ArrayList<>();
    public double totalRevenue = 0;
    public int totalOrdersProcessed = 0;

    
    public double calculateFinalPrice(int productIndex, int qty) {
        double basePrice = productPrices.get(productIndex);
        double total = basePrice * qty;
        double discount = 0;

        // Magic number issue - should be parameterized
        if (qty > 10) {
            if (qty > 50) {
                if (qty > 100) {
                    discount = 0.30; // bulk discount - magic number
                } else {
                    discount = 0.20;
                }
            } else {
                discount = 0.10;
            }
        }

        
        if (customerLoyaltyPoints > 1000) {
            discount += 0.05;
        } else if (customerLoyaltyPoints > 500) {
            discount += 0.02;
        }

        
        if (customerAge >= 65) {
            discount += 0.15;
        } else if (customerAge <= 18) {
            discount += 0.10;
        }

        double finalPrice = total - (total * discount);

        // Inline Variable Issue: unnecessary intermediate variable
        double tax = finalPrice * 0.08;
        double priceWithTax = finalPrice + tax;
        return priceWithTax;
    }

    
    public void placeOrder(int productIndex, int qty) {
        String productName = productNames.get(productIndex);
        int stock = productStockLevels.get(productIndex);

        if (stock < qty) {
            System.out.println("Insufficient stock for: " + productName);
            return;
        }

        double price = calculateFinalPrice(productIndex, qty);

        // Feature Envy: manipulating customer's loyalty points directly here
        double pointsEarned = price * 0.01;
        customerLoyaltyPoints += pointsEarned;

        // Feature Envy: formatting customer greeting using customer fields
        String greeting;
        if (customerAge >= 65) {
            greeting = "Dear Senior Customer " + customerName;
        } else {
            greeting = "Hello " + customerName;
        }

        // Update stock
        productStockLevels.set(productIndex, stock - qty);

        // Update order history using customer fields directly
        String record = customerName + " | " + customerEmail + " | "
                + productName + " x" + qty + " | $" + String.format("%.2f", price)
                + " | Ship to: " + customerAddress;
        orderHistory.add(record);

        
        sendEmailConfirmation(greeting, productName, qty, price);

        totalRevenue += price;
        totalOrdersProcessed++;
        System.out.println("Order placed for " + customerName + ": " + productName + " x" + qty);
    }


    public void sendEmailConfirmation(String greeting, String product, int qty, double price) {
        System.out.println("--- EMAIL ---");
        System.out.println("To: " + customerEmail);
        System.out.println(greeting + ",");
        System.out.println("Your order: " + product + " x" + qty + " = $" + String.format("%.2f", price));
        System.out.println("Loyalty points balance: " + customerLoyaltyPoints);
        System.out.println("Shipping to: " + customerAddress);
        System.out.println("--- END EMAIL ---");
    }

    
    public String getCustomerStatus() {
        return customerLoyaltyPoints > 1000 ? "Gold" : "Standard";
    }

    
    public void generateReport() {
        int totalOrders = totalOrdersProcessed;           
        double avgRevenue = totalRevenue / totalOrders;   
        String status = getCustomerStatus();              

        System.out.println("=== REPORT ===");
        System.out.println("Customer: " + customerName + " [" + status + "]");
        System.out.println("Total Orders: " + totalOrders);
        System.out.println("Total Revenue: $" + String.format("%.2f", totalRevenue));
        System.out.println("Avg Order Value: $" + String.format("%.2f", avgRevenue));
        System.out.println("Order History:");
        for (String record : orderHistory) {
            System.out.println("  " + record);
        }
    }

    
    
    public void addProduct(String name, double price, int stock) {
        productNames.add(name);
        productPrices.add(price);
        productStockLevels.add(stock);
        productQuantities.add(0);
    }

    public boolean checkStock(int productIndex, int qty) {
        // Inline Variable Issue: stockAvailable is used immediately and once
        int stockAvailable = productStockLevels.get(productIndex);
        return stockAvailable >= qty;
    }
}
