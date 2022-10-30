package org.example.service;

import org.example.dto.Terminal;
import org.example.enums.GeneralStatus;
import org.example.repository.TerminalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalService {
    private final TerminalRepository terminalRepository;

    public TerminalService(TerminalRepository terminalRepository) {
        this.terminalRepository = terminalRepository;
    }

    public void create(Terminal terminal) {

        Terminal terminal1 = terminalRepository.getTerminal(terminal.getCode());

        if (terminal1 != null) {
            System.out.println("Bunday kodli terminal mavjud");
            return;
        }


        int i = terminalRepository.addTerminalToDb(terminal);
        if (i != 0) {
            System.out.println("Terminal Created");
        } else {
            System.out.println("Failed");
        }


    }


    public void get_terminal_list() {
        List<Terminal> terminal_list_fromDb = terminalRepository.get_terminal_list_fromDb();
        for (Terminal terminal : terminal_list_fromDb) {
            System.out.println(terminal);
        }

    }

    public void updateTerminal_address(String code, String address) {

        int i = terminalRepository.updateTerminal_address_fromDB(code, address);
        if (i == 0) {
            System.out.println("Failed");
        } else {
            System.out.println("Successfully");
        }
    }

    public void changeTerminal_status(String code) {
        Terminal terminal = terminalRepository.getTerminal(code);
        if (terminal == null) {
            System.out.println("Bunday terminal mavjud emas");
            return;
        }

        String status = GeneralStatus.BLOCK.name();

        if (terminal.getStatus().equals(GeneralStatus.BLOCK)) {
            status = GeneralStatus.ACTIVE.name();

        }

        int i = terminalRepository.changeTerminal_status_fromDB(code, status);

        if (i == 0) {
            System.out.println("Failed");
        } else {
            System.out.println("Terminal status changed to " + status + " successfully");
        }
    }

    public void deleteTerminal(String code) {
        int i = terminalRepository.deleteTerminal_fromDb(code);
        if (i == 0) {
            System.out.println("Failed");
        } else {
            System.out.println("Deleted");
        }
    }
}
