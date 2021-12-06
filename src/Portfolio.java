import java.util.ArrayList;

public class Portfolio {
    ArrayList<Property> properties = new ArrayList<>();
    private double expenses;
    private double income;
    private double cashFlow;

    public Portfolio(Property... p) {
        for(Property prop : p) {
            //System.out.println(prop);
            properties.add(prop);
        }
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getCashFlow() {
        return cashFlow;
    }

    public void setCashFlow(double cashFlow) {
        this.cashFlow = cashFlow;
    }

    public String toString(){
        String props = "Real Estate Portfolio:\n----------------------\n";
        for(Property p : properties){
            props += p.toString();
        }
        return props;
    }

    //runSimulation(int years)

    public static void main(String [] args){
        Property NorthSt = new Property("304 North Street", "Multifamily", 4, 400_000, 5_000);
        Portfolio myPortfolio = new Portfolio(NorthSt);
        System.out.println(myPortfolio);
    }
}
