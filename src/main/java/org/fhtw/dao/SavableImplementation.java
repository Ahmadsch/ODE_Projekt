package org.fhtw.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.fhtw.entity.Message;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


public class SavableImplementation implements Savable {
    private static final String DIR_PATH = "resources/employees";
    private static final String EMPLOYEES_IDS_FILE = "ALL_EMPLOYEES_IDS";

    @Override
    public void writeDataIntoFile(Message message) throws IOException {
        final String employeeFilePath = getFilePath(message.getEmployeeId());
        final String allEmployeesIdsFilePath = getFilePath(EMPLOYEES_IDS_FILE);

        if (!findFileById(allEmployeesIdsFilePath)) {
            createDirectory(DIR_PATH);
            generateEmployeeIds(allEmployeesIdsFilePath);
        }
        if (!employeeExists(message.getEmployeeId(), allEmployeesIdsFilePath))
            throw new IllegalArgumentException("The Employee ID, which is given, was not found in our records. Try a valid ID. The ID given: " + message.getEmployeeId());

        String[] header = null;
        if (!findFileById(employeeFilePath))
            header = new String[]{"EmployeeID", "Name", "Task", "Date-from", "Date-to"};

        FileWriter fileWriter = new FileWriter(employeeFilePath, true);
        CSVWriter csvWriter = new CSVWriter(fileWriter);

        if (header != null)
            csvWriter.writeNext(header);
        csvWriter.writeNext(message.getAllData());
        System.out.println("Successfully wrote to the file.");
        csvWriter.close();
        fileWriter.close();

    }

    @Override
    public boolean findFileById(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path) && !Files.isDirectory(path);
    }

    private void createDirectory(String dirPath) {
        File dir = new File(dirPath);
        dir.mkdirs();
    }

    private String getFilePath(String employeeId) {
        String DATATYPE = ".csv";
        return DIR_PATH + "/" + employeeId + DATATYPE;
    }

    private boolean employeeExists(String employeeId, String fileName) throws IOException {
        String[] employeeIDs = loadAllEmployeesIDs(fileName);
        for (String employeeID : employeeIDs) {
            if (employeeID.equals(employeeId))
                return true;
        }
        return false;
    }
    private String[] loadAllEmployeesIDs(String fileName) throws IOException {
        final int ARRAY_SIZE = 15;
        int index = 0;
        String[] nextLine;
        CSVReader cswReader = new CSVReader(new FileReader(fileName));
        String[] employeeIDs = new String[ARRAY_SIZE];
        while ((nextLine = cswReader.readNext()) != null) {
            if (index == ARRAY_SIZE)
                break;
            employeeIDs[index++] = Arrays.toString(nextLine).replaceAll("[\\[\\](){}]","").trim();
        }
        return employeeIDs;
    }
    private void generateEmployeeIds(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName,true);
        final String[] employeeIDs = new String[]{
                "0000", "0001", "0002", "0003", "0004",
                "0005", "0006", "0007", "0008", "0009",
                "0010", "0011", "0012", "0013", "0014",
        };
        for (String employeeID : employeeIDs) {
            fileWriter.write(employeeID+"\n");
        }
        System.out.println("generated a new Employee List");
        fileWriter.close();
    }
}


/*

    private void deleteFile(String filePath) {
        File f = new File(filePath);
        boolean success = f.delete();
        if (success)
            System.out.println("deleted file");
        else
            System.out.println("could not delete file");
    }

    private void renameFile(String oldName, String newName) {
        File file = new File(oldName);
        boolean success = file.renameTo(new File(newName));
        if (success)
            System.out.println("renamed file");
        else
            System.out.println("could not rename file");
    }
 */