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

    public double calculateAmortization(){
        double mortgagePayment = 0;
        double principalPayment;
        for(Property p : properties){
            if(p.getMortgage() > 0.001){
                mortgagePayment += p.getMonthlyPayment();
                principalPayment = p.getMonthlyPayment() - (p.getMortgage() * (p.getInterestRate()/12));
                p.payDownMortgage(principalPayment);
            }
        }
        return mortgagePayment;
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

    public void setCashFlow(double income, double expenses, double mortgagePayment) {
        this.cashFlow = income - expenses - mortgagePayment;
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
        double mortgagePayment;
        double profit = -investment;
        System.out.printf("\nInitial investment: $%.2f\n", investment);
        for(int i = 1; i <= years; i++){
            System.out.println("\nYear " + i + "\n-----");
            for(int j = 1; j <= 12; j++) {
                mortgagePayment = calculateAmortization();
                setCashFlow(this.income, this.expenses, mortgagePayment);
                profit += cashFlow;
                System.out.printf("Month " + (12*(i-1)+j) + ":  Cash flow: $%.2f   Profit on original investment: $%.2f", cashFlow, profit);
                for(Property p : properties){
                    System.out.printf("   Mortgage Payment: $%.2f   Equity: $%.2f   Mortgage remaining: $%.2f", mortgagePayment, p.getEquity(), p.getMortgage());
                    System.out.println();
                }
            }
        }
    }

    public static void main(String [] args){
        Property NorthSt = new Property("304 North Street", "Multifamily", 4, 400_000, 5_000);
        NorthSt.setLoanDetails(40_000, 15, 0.07);
        Portfolio myPortfolio = new Portfolio(NorthSt);
        //System.out.println(myPortfolio);
        myPortfolio.runSimulation(30);

    }
}
