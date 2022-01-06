package soundgood.startup;


import soundgood.controller.Controller;
import soundgood.integration.SgDBException;
import soundgood.view.BlockingInterpreter;

public class Main {

    public static void main(String[] args) {
        try {
            new BlockingInterpreter(new Controller()).handleCmds();
        } catch(SgDBException sge) {
            System.out.println("Could not connect to Bank db.");
            sge.printStackTrace();
        }
    }
}
