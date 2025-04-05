package de.dhbw.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InputParser {

    public static List<Object> parseArguments(String argument) {
        List<Object> result = new ArrayList<>();
        String[] topLevelParameters = argument.split(",(?![^\\[]*\\])"); // Split by comma, ignoring commas inside brackets

        for (String param : topLevelParameters) {
            param = param.trim();
            if (param.startsWith("[") && param.endsWith("]")) {
                // Found an inner array
                String innerArrayContent = param.substring(1, param.length() - 1).trim();

                if (!innerArrayContent.isEmpty()) {
                    String[] innerArray = innerArrayContent.split(",");
                    for (int i = 0; i < innerArray.length; i++) {
                        innerArray[i] = innerArray[i].trim();
                    }
                    result.add(innerArray);
                } else {
                    result.add(new String[0]); // Empty inner array
                }
            } else {
                // Simple parameter
                result.add(param);
            }
        }
        return result;
    }

    public static List<UUID> parseUuidList(Object element) throws IllegalArgumentException {
        if (!(element instanceof String[])) {
            throw new IllegalArgumentException("Das Element ist kein String-Array und kann nicht als Liste von UUIDs geparst werden.");
        }

        String[] uuidStrings = (String[]) element;
        List<UUID> uuidList = new ArrayList<>();

        for (String uuidStr : uuidStrings) {
            try {
                UUID uuid = UUID.fromString(uuidStr.trim());
                uuidList.add(uuid);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Ungültiges UUID-Format im Array: '" + uuidStr.trim() + "'", e);
            }
        }

        return uuidList;
    }
}
