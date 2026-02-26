
public class Customer {

    
    public String name;
    public String email;
    public String address;
    public int age;
    public double loyaltyPoints;
    public String membershipTier;  // "Standard", "Silver", "Gold" - computed in OrderManager!

    public Customer(String name, String email, String address, int age) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.age = age;
        this.loyaltyPoints = 0;
        this.membershipTier = "Standard";
    }

    
    public void addLoyaltyPoints(double orderTotal) {
        // Stub - actual logic scattered in OrderManager
        this.loyaltyPoints += orderTotal * 0.01;
    }

   
    public String getGreeting() {
        // Stub - actual logic scattered in OrderManager
        if (this.age >= 65) {
            return "Dear Senior Customer " + this.name;
        }
        return "Hello " + this.name;
    }

    
    public void updateMembershipTier() {
        // Magic numbers - should be parameterized
        if (this.loyaltyPoints > 1000) {
            this.membershipTier = "Gold";
        } else if (this.loyaltyPoints > 500) {
            this.membershipTier = "Silver";
        } else {
            this.membershipTier = "Standard";
        }
    }

    @Override
    public String toString() {
        return name + " <" + email + "> [" + membershipTier + "]";
    }
}
