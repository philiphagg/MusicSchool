package soundgood.integration;


import soundgood.model.Instrument;
import soundgood.model.Lease;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Class responsible for communication with the database. Only
 * accessed from the controller
 */
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


    /**
     * Constructor instantiating the DAO object
     *
     * @throws SgDBException
     */
    public SoundGoodDAO() throws SgDBException{
        try{
            connectToSoundGoodDB();
            prepareStatements();
        } catch (ClassNotFoundException | SQLException exception){
            throw new SgDBException("could not connect to datasource", exception);
        }
    }

    /**
     * Method that can be accessed from the controller
     * for committing multi task transactions
     *
     * @throws SgDBException
     */
    public void commit() throws SgDBException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throwExceptionAndRollback("Failed to commit", e);
        }
    }


    /**
     * Method establish connection with the database
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void connectToSoundGoodDB() throws ClassNotFoundException, SQLException{
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/soundgoodmusic",
                "postgres", "hej123");
        connection.setAutoCommit(false);
    }

    /**
     * If there's any issues with the commits, this method
     * is responsible for rollback the transactions
     *
     * @param failureMsg message that describes the error
     * @param cause      error message that was catched
     * @throws SgDBException
     */
    private void throwExceptionAndRollback(String failureMsg, Exception cause) throws SgDBException {
        String completeFailureMsg = failureMsg;
        try {
            connection.rollback();
        } catch (SQLException rollbackExc) {
            completeFailureMsg = completeFailureMsg +
                    ". Also failed to rollback transaction because of: " + rollbackExc.getMessage();
        }

        if (cause != null) {
            throw new SgDBException(completeFailureMsg, cause);
        } else {
            throw new SgDBException(completeFailureMsg);
        }
    }

    /**
     * Responsible to gather information about instruments from database
     * and store them in a list
     *
     * @param instrType the type of instrument to gather information about
     * @return  list with information about instruments
     * @throws SgDBException
     */
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
            throwExceptionAndRollback(failureMsg, sqle);
        }

        return instruments;
    }

    /**
     * Responsible to gather information about leases from database
     * and store them in a list
     *
     * @return the list of leases
     * @throws SgDBException
     * @throws SQLException
     */
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
            throwExceptionAndRollback(failureMsg,throwables);
        }
        return null;
    }

    /**
     * Gathers information before a rental can be made
     *
     * @param instrument    instrument containing <code>studentID</code> and <code>instrument ID</code>
     * @return              an instrument containing information
     * @throws SgDBException
     */
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
            throwExceptionAndRollback(failureMsg, sqle);
        }
        return null;
    }

    /**
     * Gathers information before termination can be done
     *
     * @param lease information regarding the lease that should be found
     * @return      a lease containing the information
     * @throws SgDBException
     */
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
            throwExceptionAndRollback(failureMsg,sqle);
        }
        return null;
    }

    /**
     * function rents an instrument
     *
     * @param instr the instrument that shall be rented
     * @throws SgDBException
     */
    public void rent(Instrument instr) throws SgDBException {
        String failureMsg = "Could not rent instrument: " + instr.getInstrumentID();
        LocalDateTime startTime = LocalDateTime.now();
        try {
            addLeaseStmt.setTimestamp(1, Timestamp.valueOf(startTime));
            addLeaseStmt.setTimestamp(2, Timestamp.valueOf(startTime.plusMonths(6)));
            addLeaseStmt.setInt(3, instr.getInstrumentID());
            addLeaseStmt.setInt(4, instr.getStudent_id());
            addLeaseStmt.executeUpdate();

        }catch (SQLException  sqle){
            throwExceptionAndRollback(failureMsg, sqle);
        }
    }

    /**
     * update the quantity of the instrument
     *
     * @param instr
     * @throws SgDBException
     */
    public void updateInstrumentQty(Instrument instr) throws  SgDBException {
        String failMsg = "failed to update instrument " + instr.getInstrumentID();
        try {
            updateQuantityInStockStmt.setInt(1, instr.getQty());
            updateQuantityInStockStmt.setInt(2, instr.getInstrumentID());
            updateQuantityInStockStmt.executeUpdate();
        }catch (SQLException sqle){
            throwExceptionAndRollback(failMsg,sqle);
        }
    }

    /**
     * terminate a lease
     *
     * @param lease the lease that shall be terminated
     * @throws SgDBException
     */
    public void terminateLease(Lease lease) throws  SgDBException {
        String failureMsg = "could not execute termination: " + lease.getId();
        try{
            terminateLease.setInt(1,lease.getId());
            terminateLease.executeUpdate();
            connection.commit();

        }catch (SQLException sqle){
            throwExceptionAndRollback(failureMsg, sqle);
        }
    }

    /**
     * Includes all the prepared statements, that are pre loaded in
     * the fields when the dao is created.
     *
     * @throws SQLException
     */
    private void prepareStatements() throws SQLException{
        findAllInstrumentsStmt = connection.prepareStatement("SELECT * FROM " + INSTRUMENT_FOR_RENT + " WHERE " + QUANTITY_IN_STOCK + " > 0 and "+ INSTRUMENT_TYPE +"= ?");
        checkStudentEligableForRent = connection.prepareStatement("SELECT COUNT(*) FROM " + TABLE_FOR_LEASE + " WHERE " + TABLE_STUDENT_ID + " = ? AND "+END_DATE+" IS NOT NULL");
        checkQuantityInStockOfInstrumentStmt = connection.prepareStatement("SELECT quantity_in_stock FROM " + TABLE_INSTRUMENTS_FOR_RENT + " WHERE id = ? FOR UPDATE");
        addLeaseStmt = connection.prepareStatement("INSERT INTO " + TABLE_FOR_LEASE + " (" + START_DATE + ", " + END_DATE + ", " + INSTRUMENT_ID + ", " + STUDENT_ID + ") VALUES (?,?,?,?)");
        updateQuantityInStockStmt = connection.prepareStatement("UPDATE " + INSTRUMENT_FOR_RENT + " SET " + QUANTITY_IN_STOCK + " = ? where id = ? ");
        findAllLeasesStmt = connection.prepareStatement("SELECT * FROM "+TABLE_FOR_LEASE+ " WHERE "+END_DATE+" IS NOT NULL");
        findLeaseOnIDStmt = connection.prepareStatement("SELECT * FROM "+TABLE_FOR_LEASE+" JOIN "+TABLE_INSTRUMENTS_FOR_RENT+ " ifr ON lease."+INSTRUMENT_ID+" = ifr." +ID+" WHERE lease."+ID+ " = ? FOR UPDATE");
        findSpecificLeasesStmt = connection.prepareStatement("SELECT * FROM "+TABLE_FOR_LEASE+" WHERE ID = ?");
        terminateLease = connection.prepareStatement("UPDATE " +TABLE_FOR_LEASE+ " SET " +END_DATE+ " = null where id = ?");
    }



}
