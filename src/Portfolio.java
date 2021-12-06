import java.util.ArrayList;
import java.util.Arrays;

public class Portfolio {
    ArrayList<Property> properties = new ArrayList<>();
    private double expenses;
    private double income;
    private double cashFlow;

    public Portfolio(Property... p) {
        properties.addAll(Arrays.asList(p));
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
        double monthlyPayment = 0;
        double propertyPayment = 0;
        for(Property p : properties){
            propertyPayment = p.getMortgage();
            for(int i = 0; i <= p.getMortgageLength(); i++){
                propertyPayment *= (1 + p.getMortgageInterestRate()/100);
            }
            monthlyPayment += propertyPayment / p.getMortgageLength();
        }
        return monthlyPayment;
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
        double initialInvestment = 0;
        for(Property p : properties){
            initialInvestment += p.getDownPayment();
        }
        double profit = 0;
        for(int i = 0; i <= years; i++){
            profit = (i*cashFlow*12) - calculateMortgagePayment(i*12) - initialInvestment;
            System.out.printf("\nYear " + i + "\n-----\nMonthly Cashflow: %.2f\nProfit: %.2f\n", cashFlow - calculateMortgagePayment(i*12)/12, profit);
            //System.out.println("Year " + i + ":" + profit);
        }
    }
    //breakEven()
    //

    public static void main(String [] args){
        Property NorthSt = new Property("304 North Street", "Multifamily", 4, 400_000, 5_000);
        NorthSt.setDownPayment(40_000);
        NorthSt.setMortgageInterestRate(1);
        NorthSt.setMortgageLength(10);
        Portfolio myPortfolio = new Portfolio(NorthSt);
        System.out.println(myPortfolio);
        myPortfolio.runSimulation(10);

    }
}
