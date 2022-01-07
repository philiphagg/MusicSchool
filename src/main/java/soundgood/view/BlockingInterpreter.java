package soundgood.view;


import soundgood.controller.Controller;

import soundgood.model.Instrument;
import soundgood.model.InstrumentException;
import soundgood.model.Lease;
import soundgood.model.LeaseException;

import java.util.List;
import java.util.Scanner;

public class BlockingInterpreter {
    private static final String PROMPT = ">> ";
    private final Scanner console = new Scanner(System.in);
    private Controller ctrl;
    private boolean keepReceivingCmds = false;

    public BlockingInterpreter(Controller ctrl) {
        this.ctrl = ctrl;
    }



    public void stop() {
        keepReceivingCmds = false;
    }

    public void handleCmds() {
        keepReceivingCmds = true;
        while (keepReceivingCmds) {
            try {
                CmdLine cmdLine = new CmdLine(readNextLine());
                switch (cmdLine.getCmd()) {
                    case HELP:
                        for (Command command : Command.values()) {
                            if (command == Command.ILLEGAL_COMMAND) {
                                continue;
                            }
                            System.out.println(command.toString().toLowerCase());
                        }
                        break;
                    case QUIT:
                        keepReceivingCmds = false;
                        break;
                    case LIST:

                        List<Instrument> instruments = ctrl.listInstruments();
                        for(Instrument instrument : instruments){
                            System.out.println(
                                            "id: " +instrument.getInstrumentID()+ ", "+
                                            "instrument type: " +instrument.getType() + ", "
                                            + "brand: " + instrument.getBrand() + ", "
                                            + "Available for rent: " + instrument.getQty());
                        }
                        break;
                    case RENT:

                        ctrl.rent(
                                Integer.parseInt(cmdLine.getParameter(0)),
                                Integer.parseInt(cmdLine.getParameter(1))
                        );
                        break;

                    case TERMINATE:
                        List<Lease> leaseList = ctrl.terminate();
                        for(Lease lease : leaseList){
                            System.out.println(
                                    "id: " +lease.getId()+","+
                                    "start date: "+lease.getStartDate()+", "+
                                    "end date: "+lease.getEndDate()+", "+
                                    "instrument id: "+lease.getInstrumentID()+", "+
                                    "student id: " +lease.getStudent_id()
                            );
                        }
                        System.out.println("which lease would you like to terminate?");

                        Scanner sc = new Scanner(System.in);
                        int test = sc.nextInt();
                        System.out.println(test);

                        break;

                    default:
                        System.out.println("illegal command");
                }
            } catch (Exception | InstrumentException | LeaseException e) {
                System.out.println("Operation failed");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private String readNextLine() {
        System.out.print(PROMPT);
        return console.nextLine();
    }

}
