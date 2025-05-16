package de.dhbw.commands;

import de.dhbw.commands.create.CreateAssignmentCommand;

public class HelpCommand implements Command {
    @Override
    public String execute() throws RuntimeException {
        return "A command ist build like this: <keyword> <type> => <parameter1>, <parameter2>\n" +
                "Available keywords are: 'create' and 'read'\n" +
                "Available types for create are: 'assignment', 'doctor', 'examination', 'patient', 'room'\n" +
                "Available types for read are: 'assignment', 'doctor', 'examination', 'patient', 'room', 'doctorExaminationPlan\n" +
                "Lists of parameters from the same type are entered in square brackets. Within these brackets the values are separated by comma.\n" +
                "Examples for all keywords and types with the required parameters can be found in the documentation in the chapter 'Musterbefehle'.";
    }
}
