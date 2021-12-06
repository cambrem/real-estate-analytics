public class Property{
    private String address;
    private String type;
    private int units;
    private double purchasePrice;

    // income
    private double rent;

    // monthly expenses
    private double propertyTax;
    private double insurance;
    private double vacancy;
    private double repairs;
    private double capX;
    private double mortgage;


    public Property(String address, String type, int units, double purchasePrice, double rent) {
        this.address = address;
        this.type = type;
        this.units = units;
        this.purchasePrice = purchasePrice;
        this.rent = rent;
        this.propertyTax = purchasePrice * 0.0186 / 12;
        this.insurance = purchasePrice * 0.01 / 12;
        this.vacancy = rent * 0.05;
        this.capX = 182.75;
        this.mortgage = 0.8*purchasePrice/(30*12);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public double getPropertyTax() {
        return propertyTax;
    }

    public void setPropertyTax(int propertyTax) {
        this.propertyTax = propertyTax;
    }

    public double getInsurance() {
        return insurance;
    }

    public void setInsurance(double insurance) {
        this.insurance = insurance;
    }

    public double getVacancy() {
        return vacancy;
    }

    public void setVacancy(double vacancy) {
        this.vacancy = vacancy;
    }

    public double getRepairs() {
        return repairs;
    }

    public void setRepairs(double repairs) {
        this.repairs = repairs;
    }

    public double getCapX() {
        return capX;
    }

    public void setCapX(double capX) {
        this.capX = capX;
    }

    public double getMortgage() {
        return mortgage;
    }

    public void setMortgage(double mortgage) {
        this.mortgage = mortgage;
    }

    public String toString(){
        return "Address: " + address + "\nType: " + type + "\nUnits: " + units + "\nPurchase Price: $" + purchasePrice + "\nRent: $" + rent;
    }
}
