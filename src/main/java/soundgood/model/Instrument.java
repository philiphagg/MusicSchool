package soundgood.model;

public class Instrument {
    public String type;
    public String brand;
    public int qty;
    public int student_id;
    public int instrumentID;
    public int numberOfInstrumentsRented;

    public Instrument() {
        this.type = null;
        this.brand = null;
    }
    public Instrument(int instrumentID, int student_id){
        this.instrumentID = instrumentID;
        this.student_id = student_id;
    }

    public Instrument(String instrumentType, String brand, int qtyInStock) {
        this.type = instrumentType;
        this.brand = brand;
        this.qty = qtyInStock;
    }



    public Instrument(int instrumentID, String type, String brand, int qty) {
        this.type = type;
        this.brand = brand;
        this.qty = qty;
        this.instrumentID = instrumentID;
    }

    public Instrument rent(Instrument instr){
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

    public int getStudent_id(){return student_id;}

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
}
