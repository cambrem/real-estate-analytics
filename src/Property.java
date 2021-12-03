public class Property{
    private String address;
    private String type;
    private int units;
    private int purchasePrice;
    private int monthlyRent;
    private int propertyTax;

    public Property(String address, String type, int units, int purchasePrice, int monthlyRent, int propertyTax) {
        this.address = address;
        this.type = type;
        this.units = units;
        this.purchasePrice = purchasePrice;
        this.monthlyRent = monthlyRent;
        this.propertyTax = propertyTax;
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

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(int monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public int getPropertyTax() {
        return propertyTax;
    }

    public void setPropertyTax(int propertyTax) {
        this.propertyTax = propertyTax;
    }

    public String toString(){
        return address + " " + type + " " + units + " " + purchasePrice + " " + monthlyRent + " " + propertyTax;
    }

    public static void main(String [] args){
        Property p1 = new Property("304 North Street", "Multifamily", 4, 400_000, 6_000, 7_440);
        System.out.println(p1);
    }
}
