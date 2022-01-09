package soundgood.integration;


import soundgood.model.Instrument;
import soundgood.model.Lease;

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
    private PreparedStatement findAllLeasesStmt;
    private PreparedStatement findLeaseOnIDStmt;
    private PreparedStatement findSpecificLeasesStmt;
    private PreparedStatement terminateLease;

    private final String TABLE_INSTRUMENTS_FOR_RENT = "instrument_for_rent";
    private final String INSTRUMENT_FOR_RENT = "instrument_for_rent";
    private final String QUANTITY_IN_STOCK = "quantity_in_stock";
    private final String TABLE_FOR_LEASE = "lease";
    private final String TABLE_STUDENT_ID = "student_id";
    private final String START_DATE = "start_date";
    private final String END_DATE = "end_date";
    private final String INSTRUMENT_ID = "instrument_id";
    private final String STUDENT_ID = "student_id";
    private final String INSTRUMENT_TYPE = "instrument_type";
    private final String ID = "id";



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
    public List<Instrument> listInstruments(String instrType) throws SgDBException {
        String failureMsg = "Could not list instruments.";
        List<Instrument> instruments = new ArrayList<>();

        try {
            findAllInstrumentsStmt.setString(1,instrType);
            ResultSet result = findAllInstrumentsStmt.executeQuery();
            while(result.next()){
                instruments.add(new Instrument(
                        result.getInt("id"),
                        result.getString("instrument_type"),
                        result.getString("brand"),
                        result.getInt(QUANTITY_IN_STOCK)));
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

        try(ResultSet result = findAllLeasesStmt.executeQuery()) {
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
            if(qtyInStock.next()) {instrument.setQty(qtyInStock.getInt(QUANTITY_IN_STOCK));}
                return instrument;
        } catch (SQLException sqle){
            handleException(failureMsg, sqle);
        }
            return null;
        }

    public Instrument gatherInformationBeforeTermination(Lease lease) throws SgDBException {
        String failureMsg = "something went wrong with: " +lease.getId() ;
        try{
            findLeaseOnIDStmt.setInt(1,lease.getId());
            ResultSet resultSet = findLeaseOnIDStmt.executeQuery();
            if(resultSet.next()) {

                return new Instrument(
                        resultSet.getInt("quantity_in_stock"),
                        resultSet.getInt(INSTRUMENT_ID),
                        "test"
                );
            }



        }catch (SQLException sqle){
            handleException(failureMsg,sqle);
        }
        return null;
    }

    public void rent(Instrument instr) throws SgDBException {
        String failureMsg = "Could not rent instrument: " + instr.getInstrumentID();
        LocalDateTime startTime = LocalDateTime.now();
        try {
            addLeaseStmt.setTimestamp(1, Timestamp.valueOf(startTime));
            addLeaseStmt.setTimestamp(2, Timestamp.valueOf(startTime.plusMonths(6)));
            addLeaseStmt.setInt(3, instr.getInstrumentID());
            addLeaseStmt.setInt(4, instr.getStudent_id());
            addLeaseStmt.executeUpdate();
            connection.commit();
            updateInstrumentQty(instr);

        }catch (SQLException  sqle){
            handleException(failureMsg, sqle);
        }
    }

    public void updateInstrumentQty(Instrument instr) throws SQLException, SgDBException {
        String failMsg = "failed to update instrument" + instr.getInstrumentID();
        try {
            updateQuantityInStockStmt.setInt(1, instr.getQty());
            updateQuantityInStockStmt.setInt(2, instr.getInstrumentID());
            updateQuantityInStockStmt.executeUpdate();
            connection.commit();
        }catch (SQLException sqle){
            handleException(failMsg,sqle);
        }
    }

    public void terminateLease(Lease lease) throws SQLException, SgDBException {
        String failureMsg = "could not execute termination: " + lease.getId();
        try{
            terminateLease.setInt(1,lease.getId());
            terminateLease.executeUpdate();
            connection.commit();

        }catch (SQLException sqle){
            handleException(failureMsg, sqle);
        }
    }

    private void prepareStatements() throws SQLException{
        findAllInstrumentsStmt = connection.prepareStatement("SELECT * FROM " + INSTRUMENT_FOR_RENT + " WHERE " + QUANTITY_IN_STOCK + " > 0 and "+ INSTRUMENT_TYPE +"= ?");
        checkStudentEligableForRent = connection.prepareStatement("SELECT COUNT(*) FROM " + TABLE_FOR_LEASE + " WHERE " + TABLE_STUDENT_ID + " = ?");
        checkQuantityInStockOfInstrumentStmt = connection.prepareStatement("SELECT quantity_in_stock FROM " + TABLE_INSTRUMENTS_FOR_RENT + " WHERE id = ?");
        addLeaseStmt = connection.prepareStatement("INSERT INTO " + TABLE_FOR_LEASE + " (" + START_DATE + ", " + END_DATE + ", " + INSTRUMENT_ID + ", " + STUDENT_ID + ") VALUES (?,?,?,?)");
        updateQuantityInStockStmt = connection.prepareStatement("UPDATE " + INSTRUMENT_FOR_RENT + " SET " + QUANTITY_IN_STOCK + " = ? where id = ?");
        findAllLeasesStmt = connection.prepareStatement("SELECT * FROM "+TABLE_FOR_LEASE+ " WHERE "+END_DATE+" IS NOT NULL");
        findLeaseOnIDStmt = connection.prepareStatement("SELECT * FROM "+TABLE_FOR_LEASE+" JOIN "+TABLE_INSTRUMENTS_FOR_RENT+ " ifr ON lease."+INSTRUMENT_ID+" = ifr." +ID+" WHERE lease."+ID+ " = ?");
        findSpecificLeasesStmt = connection.prepareStatement("SELECT * FROM "+TABLE_FOR_LEASE+" WHERE ID = ?");
        terminateLease = connection.prepareStatement("UPDATE " +TABLE_FOR_LEASE+ " SET " +END_DATE+ " = null where id = ?");
    }



}
