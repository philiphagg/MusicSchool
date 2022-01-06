package soundgood.controller;


import soundgood.integration.SgDBException;
import soundgood.integration.SoundGoodDAO;
import soundgood.model.Instrument;
import soundgood.model.InstrumentException;

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


}

