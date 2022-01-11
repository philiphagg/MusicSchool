package soundgood.controller;


import soundgood.integration.SgDBException;
import soundgood.integration.SoundGoodDAO;
import soundgood.model.Instrument;
import soundgood.model.InstrumentException;
import soundgood.model.Lease;
import soundgood.model.LeaseException;

import java.sql.SQLException;
import java.util.List;

/**
 * responsible for deligate tasks through out the application
 */
public class Controller {
    private final SoundGoodDAO sgDAO;

    public Controller() throws SgDBException {
        sgDAO = new SoundGoodDAO();
    }

    /**
     * make a list of instruments
     *
     * @param instrType the type of instrument that shall be listed
     * @return the list of instruments
     * @throws InstrumentException
     */
    public List<Instrument> listInstruments(String instrType) throws InstrumentException {
        try{
            return sgDAO.listInstruments(instrType);
        }catch (Exception e) {
            throw new InstrumentException("Unable to list instruments", e);
        }
    }

    /**
     * Makes all necessary function calls to rent an instrument
     *
     * @param instrumentID  instrument to be rented
     * @param studentID     student that wants to rent the instrument
     * @throws SgDBException
     * @throws SQLException
     */
    public void rent(int instrumentID, int studentID) throws SgDBException, SQLException {
        Instrument instr = new Instrument(instrumentID, studentID);
        Instrument noOfInstruments = sgDAO.gatherInformationBeforeInstrumentRental(instr);
        instr = instr.correctQtyAndCheckEligability(noOfInstruments);
        sgDAO.rent(instr);
        sgDAO.updateInstrumentQty(instr);
        sgDAO.commit();
    }

    /**
     * Makes a list of all leases in the database
     * @return
     * @throws LeaseException
     */
    public List<Lease> listLease() throws LeaseException {
        try{
            return sgDAO.listLease();
        } catch (SgDBException | SQLException e) {
            throw new LeaseException("Unable to list leases", e);
        }

    }

    /**
     * terminates a lease
     *
     * @param leaseID lease that shall be terminated
     * @throws SgDBException
     * @throws SQLException
     */
    public void terminate(int leaseID) throws SgDBException, SQLException {
        String failureMsg = "Wasn't able to terminate " +leaseID;
        Lease lease = new Lease(leaseID);
        Instrument instr = sgDAO.gatherInformationBeforeTermination(lease);
        sgDAO.updateInstrumentQty(instr.updateQty(instr));
        sgDAO.terminateLease(lease);
        try {
            sgDAO.commit();
        } catch (SgDBException sge){
            throw new SgDBException(failureMsg, sge);
        }


    }
}

