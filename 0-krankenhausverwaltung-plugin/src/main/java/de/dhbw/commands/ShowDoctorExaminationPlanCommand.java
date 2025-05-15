package de.dhbw.commands;

import de.dhbw.InputParser;
import de.dhbw.aggregates.examination.util.ExaminationWithPatientName;
import de.dhbw.commands.exceptions.WrongAmoutOfParameters;
import de.dhbw.storage.DoctorStorage;
import de.dhbw.storage.ExaminationStorage;
import de.dhbw.storage.PatientStorage;
import de.dhbw.use_cases.ShowDoctorExaminationPlan;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

public class ShowDoctorExaminationPlanCommand implements Command {
        private final String argument;

        public ShowDoctorExaminationPlanCommand(String argument) {
            this.argument = argument;
        }

        @Override
        public String execute() throws RuntimeException, FileNotFoundException {
            List<Object> arguments = InputParser.parseArguments(argument);
            if (arguments.size() != 2) {
                throw new WrongAmoutOfParameters("The wrong amount of parameters.");
            }

            UUID assignmentId = null;
            boolean sortByDate;
            try {
                assignmentId = UUID.fromString(arguments.getFirst().toString());

            } catch (Exception e) {
                throw new IllegalArgumentException("The UUID is invalid. Please use a valid UUID.");
            }

            try {
                sortByDate = Boolean.parseBoolean(arguments.get(1).toString());;
            } catch (Exception e) {
                throw new IllegalArgumentException("The sort by date input is invalid. Please use a valid input (true or false).");
            }




            ShowDoctorExaminationPlan showDoctorExaminationPlan = new ShowDoctorExaminationPlan(new DoctorStorage(System.getProperty("user.dir") + "/" + "doctors.json"),
                    new ExaminationStorage(System.getProperty("user.dir") + "/" + "examinations.json"),
                    new PatientStorage(System.getProperty("user.dir") + "/" + "patients.json"));

            List<ExaminationWithPatientName> examinationsOfDoctor = showDoctorExaminationPlan.execute(assignmentId, sortByDate);

            StringBuilder result = new StringBuilder();
            result.append("Examination plan for Doctor: ").append(assignmentId).append("\n");
            for (ExaminationWithPatientName examination : examinationsOfDoctor) {

                result.append(examination.getPatientName()).append(" - ").append(examination.getExamination()).append("\n");
            }

            return result.toString();
        }
}
