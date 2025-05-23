package de.dhbw.aggregates.examination.value_objects;

public enum ExaminationType {
    GeneralExamination ,
    PhysicalExamination ,
    BloodExamination,
    Biopsy,
    Ultrasound;

    public static String[] getAllExaminationTypes() {
        return new String[]{GeneralExamination.name(), PhysicalExamination.name(), BloodExamination.name(), Biopsy.name(), Ultrasound.name()};
    }
}
