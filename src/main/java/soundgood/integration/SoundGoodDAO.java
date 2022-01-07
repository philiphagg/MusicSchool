package soundgood.integration;


import soundgood.model.Instrument;
import soundgood.model.Lease;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SoundGoodDAO {
    private Connection connection;
    private PreparedStatement findAllInstrumentsStmt;
    private PreparedStatement checkStudentEligableForRent;
    private PreparedStatement checkQuantityInStockOfInstrumentStmt;
    private PreparedStatement addLeaseStmt;
    private PreparedStatement updateQuantityInStockStmt;
    private PreparedStatement findLeasesOnIDStmt;

    private final String TABLE_INSTRUMENTS_FOR_RENT = "instrument_for_rent";
    private final String instrument_for_rent = "instrument_for_rent";
    private final String quantity_in_stock = "quantity_in_stock";
    private final String TABLE_FOR_LEASE = "lease";
    private final String TABLE_STUDENT_ID = "student_id";
    private final String lease = "lease";
    private final String start_date = "start_date";
    private final String end_date = "start_date";
    private final String instrument_id = "instrument_id";
    private final String student_id = "student_id";


    public SoundGoodDAO() throws SgDBException{
        try{
            connectToSoundGoodDB();
            prepareStatements();
        } catch (ClassNotFoundException | SQLException exception){
            throw new SgDBException("could not connect to datasource", exception);
        }
    }

    public void commit() throws SgDBException {
        try {
            connection.commit();
        } catch (SQLException e) {
            handleException("Failed to commit", e);
        }
    }

    private void connectToSoundGoodDB() throws ClassNotFoundException, SQLException{
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/soundgoodmusic",
                "postgres", "hej123");
        connection.setAutoCommit(false);
    }

    private void handleException(String failureMsg, Exception cause) throws SgDBException {
        String completeFailureMsg = failureMsg;
        try {
            connection.rollback();
        } catch (SQLException rollbackExc) {
            completeFailureMsg = completeFailureMsg +
                    ". Also failed to rollback transaction because of: " + rollbackExc.getMessage();
        }

        if (cause != null) {
            throw new SgDBException(failureMsg, cause);
        } else {
            throw new SgDBException(failureMsg);
        }
    }
    public List<Instrument> listInstruments() throws SgDBException{
        //TODO lägg in så man kan söka efter specifikt instrument
        String failureMsg = "Could not list instruments.";
        List<Instrument> instruments = new ArrayList<>();

        try (ResultSet result = findAllInstrumentsStmt.executeQuery()){
            while(result.next()){
                instruments.add(new Instrument(
                        result.getInt("id"),
                        result.getString("instrument_type"),
                        result.getString("brand"),
                        result.getInt("quantity_in_stock")));
            }
            connection.commit();
        }catch (SQLException sqle) {
            handleException(failureMsg, sqle);
        }

        return instruments;
    }
    public List<Lease> listLease() throws SgDBException, SQLException {
        String failureMsg = "could not list leases.";
        List<Lease> leaseList = new ArrayList<>();

        try(ResultSet result = findLeasesOnIDStmt.executeQuery()) {
            while(result.next()){
                leaseList.add(new Lease(
                        result.getInt("id"),
                        result.getTimestamp("start_date"),
                        result.getTimestamp("end_date"),
                        result.getInt("instrument_id"),
                        result.getInt("student_id")
                ));
            }
            return leaseList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Instrument gatherInformationBeforeInstrumentRental(Instrument instrument) throws SgDBException {
        String failureMsg = "something went wrong with instrument: " + instrument.getInstrumentID();
        ResultSet noInstr = null;
        ResultSet qtyInStock = null;
        try {
            checkStudentEligableForRent.setInt(1, instrument.getStudent_id());
            noInstr = checkStudentEligableForRent.executeQuery();

            checkQuantityInStockOfInstrumentStmt.setInt(1, instrument.getInstrumentID());
            qtyInStock = checkQuantityInStockOfInstrumentStmt.executeQuery();

            if(noInstr.next()) {instrument.setNumberOfInstrumentsRented(noInstr.getInt("count"));}
            if(qtyInStock.next()) {instrument.setQty(qtyInStock.getInt(quantity_in_stock));}
                return instrument;
        } catch (SQLException sqle){
            handleException(failureMsg, sqle);
        }
            return null;
        }

    public void rent(Instrument instr) throws SgDBException {
        String failureMsg = "Could not rent instrument: " + instrument_id;
        LocalDateTime startTime = LocalDateTime.now();
        try {
            addLeaseStmt.setTimestamp(1, Timestamp.valueOf(startTime));
            addLeaseStmt.setTimestamp(2, Timestamp.valueOf(startTime.plusMonths(6)));
            addLeaseStmt.setInt(3, instr.getInstrumentID());
            addLeaseStmt.setInt(4, instr.getStudent_id());
            addLeaseStmt.executeUpdate();
            connection.commit();
            updateQuantityInStockStmt.setInt(1, instr.getQty());
            updateQuantityInStockStmt.setInt(2, instr.getInstrumentID());
            updateQuantityInStockStmt.executeUpdate();
            connection.commit();

        }catch (SQLException  sqle){
            handleException(failureMsg, sqle);
        }
    }


    private void prepareStatements() throws SQLException{
        findAllInstrumentsStmt = connection.prepareStatement("SELECT * FROM " + instrument_for_rent + " WHERE " + quantity_in_stock + " > 0");
        checkStudentEligableForRent = connection.prepareStatement("SELECT COUNT(*) FROM " + TABLE_FOR_LEASE + " WHERE " + TABLE_STUDENT_ID + " = ?");
        checkQuantityInStockOfInstrumentStmt = connection.prepareStatement("SELECT quantity_in_stock FROM " + TABLE_INSTRUMENTS_FOR_RENT + " WHERE id = ?");
        addLeaseStmt = connection.prepareStatement("INSERT INTO " + lease + " (" + start_date + ", " + end_date + ", " + instrument_id + ", " + student_id + ") VALUES (?,?,?,?)");
        updateQuantityInStockStmt = connection.prepareStatement("UPDATE " +instrument_for_rent+ " SET " +quantity_in_stock+ " = ? where id = ?");
        findLeasesOnIDStmt = connection.prepareStatement("SELECT * FROM "+lease);
    }
}
