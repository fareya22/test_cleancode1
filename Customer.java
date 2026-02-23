/**
 * Customer class - THIN / ANEMIC model.
 *
 * DESIGN ISSUES PRESENT:
 * - Information Hiding: all fields are public, no encapsulation
 * - Feature Envy victim: OrderManager accesses these fields directly instead
 *   of calling methods on Customer (Customer's own behavior lives elsewhere)
 * - Modularity: Customer has no behavior of its own - loyalty logic,
 *   greeting logic, and discount logic all live in OrderManager instead
 *
 * REFACTORING OPPORTUNITIES:
 * - "extract class"       → split into Customer + LoyaltyAccount
 * - "move method"        → move getGreeting(), addLoyaltyPoints(),
 *                          getSeniorDiscount() FROM OrderManager INTO here
 * - "parameterize variable" → loyalty thresholds (500, 1000) should be params
 */
public class Customer {

    // Information Hiding Issue: should all be private with getters/setters
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

    // -----------------------------------------------------------------------
    // These methods SHOULD exist but are MISSING - behavior that belongs to
    // Customer is currently living inside OrderManager (Feature Envy source).
    //
    // The methods below are stubs showing what SHOULD be here:
    // -----------------------------------------------------------------------

    /**
     * MOVE METHOD TARGET: This method should exist here but the logic
     * currently lives inside OrderManager.placeOrder() directly.
     * Refactoring: "move method" → bring loyalty point logic here.
     */
    public void addLoyaltyPoints(double orderTotal) {
        // Stub - actual logic scattered in OrderManager
        this.loyaltyPoints += orderTotal * 0.01;
    }

    /**
     * MOVE METHOD TARGET: Greeting logic lives in OrderManager.placeOrder()
     * but logically belongs to Customer.
     * Refactoring: "move method" → move greeting construction here.
     */
    public String getGreeting() {
        // Stub - actual logic scattered in OrderManager
        if (this.age >= 65) {
            return "Dear Senior Customer " + this.name;
        }
        return "Hello " + this.name;
    }

    /**
     * PARAMETERIZE VARIABLE TARGET: thresholds 500 and 1000 are magic numbers
     * scattered across OrderManager. They should be parameters or constants here.
     *
     * Refactoring: "parameterize variable" → pass threshold values as parameters
     *   e.g., updateTier(double silverThreshold, double goldThreshold)
     */
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
