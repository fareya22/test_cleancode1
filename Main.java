
public class Main {

    public static void main(String[] args) {

        // ===== SETUP: God Class takes on all responsibilities =====
        OrderManager manager = new OrderManager();

        manager.customerName = "Alice Johnson";
        manager.customerEmail = "alice@example.com";
        manager.customerAddress = "123 Elm Street, Dhaka";
        manager.customerAge = 67;                  // senior customer
        manager.customerLoyaltyPoints = 850.0;     // silver-tier loyalty

        manager.addProduct("Laptop", 999.99, 50);
        manager.addProduct("Mouse", 29.99, 200);
        manager.addProduct("Keyboard", 59.99, 150);

        System.out.println("===== ORDER PROCESSING DEMO =====\n");

        
        int laptopIndex = 0;
        manager.placeOrder(laptopIndex, 2);

        System.out.println();

        
        int mouseIndex = 1;
        int bulkQty = 15;
        manager.placeOrder(mouseIndex, bulkQty);

        System.out.println();

        
        manager.generateReport();

        System.out.println("\n===== CUSTOMER OBJECT DEMO =====\n");

        // Customer class demo - showing the thin anemic model
        Customer customer = new Customer("Bob Smith", "bob@example.com", "456 Oak Ave", 22);
        customer.addLoyaltyPoints(250.0);
        customer.updateMembershipTier();

        // Information Hiding Issue: accessing public fields directly
        System.out.println("Name: " + customer.name);
        System.out.println("Email: " + customer.email);
        System.out.println("Tier: " + customer.membershipTier);
        System.out.println("Greeting: " + customer.getGreeting());

        System.out.println("\n===== STOCK CHECK DEMO =====\n");

        
        boolean available = manager.checkStock(2, 10);
        if (available) {
            System.out.println("Keyboards in stock - placing order.");
            manager.placeOrder(2, 10);
        }
    }
}
