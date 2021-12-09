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
        double monthlyPayment = 0;
        double totalPropertyPayment;
        double propertyPayment = 0;
        for(Property p : properties){
            if(p.getMortgageRemaining()>0){
                totalPropertyPayment = p.getMortgage();
                /*
                for(int i = 1; i <= p.getMortgageLength(); i++){
                    totalPropertyPayment*= (1 + p.getMortgageInterestRate()/100);
                    //System.out.println("Property payment: " + propertyPayment);
                }
                */
                propertyPayment = totalPropertyPayment / (p.getMortgageLength()*12);
                monthlyPayment += propertyPayment;
            }
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
        double profit = -investment;
        double mortgagePayment;
        System.out.printf("\nInitial investment: $%.2f\n", investment);
        for(int i = 1; i <= years; i++){
            System.out.println("\nYear " + i + "\n-----");
            for(int j = 1; j <= 12; j++) {
                mortgagePayment = calculateMortgagePayment(12);
                setCashFlow(this.income, this.expenses, mortgagePayment);
                profit += cashFlow;
                for(Property p : properties){
                    p.payDownMortgage(mortgagePayment);
                }
                System.out.printf("Month " + (12*(i-1)+j) + ":  Cash flow: $%.2f   Profit on original investment: $%.2f", cashFlow, profit);
                for(Property p : properties){
                    System.out.printf("   Mortgage Payment: $%.2f   Equity: $%.2f   Mortgage remaining: $%.2f", mortgagePayment, p.getEquity(), p.getMortgageRemaining());
                    System.out.println();
                }
            }
        }
    }

    public static void main(String [] args){
        Property NorthSt = new Property("304 North Street", "Multifamily", 4, 400_000, 5_000);
        NorthSt.setDownPayment(40_000);
        NorthSt.setMortgageInterestRate(1);
        NorthSt.setMortgageLength(15);
        Portfolio myPortfolio = new Portfolio(NorthSt);
        //System.out.println(myPortfolio);
        myPortfolio.runSimulation(30);

    }
}
