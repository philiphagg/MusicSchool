package soundgood.model;


/**
 * represents a instrument. this class will also
 * transfer data between layers
 */
public class Instrument {
    public String type;
    public String brand;
    public int qty;
    public int student_id;
    public int instrumentID;
    public int numberOfInstrumentsRented;


    public Instrument(int instrumentID, int student_id){
        this.instrumentID = instrumentID;
        this.student_id = student_id;
    }

    public Instrument(String instrumentType, String brand, int qtyInStock) {
        this.type = instrumentType;
        this.brand = brand;
        this.qty = qtyInStock;
    }
    public Instrument(int qty, int instrumentID, String type){
        this.qty = qty;
        this.instrumentID = instrumentID;
    }

    public Instrument(int instrumentID, String type, String brand, int qty) {
        this.type = type;
        this.brand = brand;
        this.qty = qty;
        this.instrumentID = instrumentID;
    }

    public Instrument(int qtyInStock) {
        this.qty = qtyInStock;
    }

    /**
     * method changes quantity for the rent, if it's possible
     *
     * @param instr instrument that shall change quantity
     * @return  the instrument or null
     */
    public Instrument correctQtyAndCheckEligability(Instrument instr){
        if(rentalIsPossible(instr)){
            instr.setQty(instr.getQty() - 1);
            return instr;
        }
        else{
            informUserReasons(instr);
        }
        return null;

    }


    private void informUserReasons(Instrument instr) {
        if(instr.getQty() == 0)
            System.out.println("The instrument is not available");
        if(instr.getNumberOfInstrumentsRented() > 1)
            System.out.println("student cannot rent anymore instruments");
    }

    private boolean rentalIsPossible(Instrument noOfInstruments) {
        return noOfInstruments.getQty() > 0 && noOfInstruments.getNumberOfInstrumentsRented() < 2;
    }

    public int getStudent_id(){
        return student_id;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public int getQty() {
        return qty;
    }

    public int getInstrumentID() {
        return instrumentID;
    }

    public int getNumberOfInstrumentsRented() {
        return numberOfInstrumentsRented;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public void setInstrumentID(int instrumentID) {
        this.instrumentID = instrumentID;
    }

    public void setNumberOfInstrumentsRented(int numberOfInstrumentsRented) {
        this.numberOfInstrumentsRented = numberOfInstrumentsRented;
    }

    /**
     * updates the quantity of the instrument
     *
     * @param instr instrument that should be updated
     * @return the instrument
     */
    public Instrument updateQty(Instrument instr) {
        instr.setQty(this.qty + 1);
        return instr;
    }
}
