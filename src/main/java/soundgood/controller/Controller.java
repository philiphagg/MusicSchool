package soundgood.controller;


import soundgood.integration.SgDBException;
import soundgood.integration.SoundGoodDAO;
import soundgood.model.Instrument;
import soundgood.model.InstrumentException;
import soundgood.model.Lease;
import soundgood.model.LeaseException;

import java.sql.SQLException;
import java.util.List;

public class Controller {
    private final SoundGoodDAO sgDAO;

    public Controller() throws SgDBException {
        sgDAO = new SoundGoodDAO();
    }

    public List<Instrument> listInstruments() throws InstrumentException {
        try{
            return sgDAO.listInstruments();
        }catch (Exception e) {
            throw new InstrumentException("Unable to list instruments", e);
        }
    }


    public void rent(int instrumentID, int studentID) throws SgDBException, SQLException {
        Instrument instr = new Instrument(instrumentID, studentID);
        Instrument noOfInstruments = sgDAO.gatherInformationBeforeInstrumentRental(instr);
        instr = instr.rent(noOfInstruments);
        sgDAO.rent(instr);


    }

    public List<Lease> terminate() throws LeaseException {
        try{
            return sgDAO.listLease();
        } catch (SgDBException | SQLException e) {
            throw new LeaseException("Unable to list instruments", e);
        }

    }
}

