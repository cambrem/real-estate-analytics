import java.util.ArrayList;
import java.util.Arrays;

public class Portfolio {
    ArrayList<Property> properties = new ArrayList<>();
    private double expenses;
    private double income;
    private double cashFlow;
    private double investment;

    public Portfolio(Property... p) {
        properties.addAll(Arrays.asList(p));
        investment = 0;
        for(Property prop : properties){
            investment += prop.getDownPayment();
        }
    }

    public void calculateMonthlyExpenses(){
        for(Property p : properties){
            expenses += p.getPropertyTax();
            expenses += p.getInsurance();
            expenses += p.getVacancy();
            expenses += p.getRepairs();
            expenses += p.getCapX();
        }
    }

    public void calculateMonthlyIncome(){
        for(Property p : properties){
            income += p.getRent();
        }
    }

    public double calculateMortgagePayment(int month){
        double annualPayment = 0;
        double propertyPayment = 0;
        for(Property p : properties){
            if(p.getMortgageRemaining()>0){
                propertyPayment = p.getMortgage();
                System.out.println("Property payment: " + propertyPayment);
                for(int i = 1; i <= p.getMortgageLength(); i++){
                    propertyPayment *= (1 + p.getMortgageInterestRate()/100);
                    System.out.println("Property payment: " + propertyPayment);

                }
                annualPayment += propertyPayment / p.getMortgageLength();
                p.payDownMortgage(annualPayment);
                p.increaseEquity(annualPayment);
            }
        }
        return annualPayment;
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

    public void runSimulation(int years){
        calculateMonthlyExpenses();
        calculateMonthlyIncome();
        cashFlow = this.income - this.expenses;
        System.out.println(cashFlow);
        double profit = 0;
        double mortgagePayment = calculateMortgagePayment(12);
        System.out.println(mortgagePayment);
        System.out.printf("\nInitial investment: $%.2f\n", investment);
        for(int i = 1; i <= years; i++){
            profit = (i*cashFlow*12) - i*mortgagePayment - investment;
            System.out.printf("\nYear " + i + "\n-----\nMonthly Cashflow: $%.2f\nProfit at year end: $%.2f\n", cashFlow - calculateMortgagePayment(i*12)/12, profit);
        }
    }
    //breakEven()
    //

    public static void main(String [] args){
        Property NorthSt = new Property("304 North Street", "Multifamily", 4, 400_000, 5_000);
        NorthSt.setDownPayment(40_000);
        NorthSt.setMortgageInterestRate(1);
        NorthSt.setMortgageLength(30);
        Portfolio myPortfolio = new Portfolio(NorthSt);
        //System.out.println(myPortfolio);
        myPortfolio.runSimulation(10);

    }
}
