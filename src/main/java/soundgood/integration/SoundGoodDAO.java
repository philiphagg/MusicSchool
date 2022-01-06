package soundgood.integration;


import soundgood.model.Instrument;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SoundGoodDAO {
    private Connection connection;
    private PreparedStatement findAllInstrumentsStmt;
    private final String instrument_for_rent = "instrument_for_rent";
    private final String quantity_in_stock = "quantity_in_stock";

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
        String failureMsg = "Could not list instruments.";
        List<Instrument> instruments = new ArrayList<>();

        try (ResultSet result = findAllInstrumentsStmt.executeQuery()){
            while(result.next()){
                instruments.add(new Instrument(result.getString("instrument_type"),
                                                result.getString("brand"),
                                                result.getInt("quantity_in_stock")));
            }
            connection.commit();
        }catch (SQLException sqle) {
            handleException(failureMsg, sqle);
        }



        return instruments;
    }
    private void prepareStatements() throws SQLException{
        findAllInstrumentsStmt = connection.prepareStatement("SELECT * FROM " + instrument_for_rent + " WHERE " + quantity_in_stock + " > 0;");
    }

}
