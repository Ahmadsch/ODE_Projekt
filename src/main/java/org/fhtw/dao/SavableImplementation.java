package org.fhtw.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.fhtw.entity.Message;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class SavableImplementation implements Savable {
    private static final String DIR_PATH = "resources/employees";

    @Override
    public void writeDataIntoFile(Message message) {
        final String FILE_PATH = getFilePath(message.getEmployeeId());
        try {
            if (!validateData(message))
                throw new IOException();
            if (findFileById(FILE_PATH)){
                String tmpFileName = "tmp.csv";
                CSVReader cswReader = new CSVReader(new FileReader(FILE_PATH));
                CSVWriter csvWriter = new CSVWriter(new FileWriter(tmpFileName));
                String [] nextLine;
                List<String[]> LinesAsList = new ArrayList<String[]>();
                while ((nextLine = cswReader.readNext()) != null) {
                    LinesAsList.add(nextLine);
                }
                LinesAsList.add(message.getAllData());
                csvWriter.writeAll(LinesAsList);
                System.out.println("Successfully wrote to the file.");
                csvWriter.close();
                cswReader.close();
                deleteFile(FILE_PATH);
                renameFile(tmpFileName,FILE_PATH);
            }else {
                createDirectory(DIR_PATH);
                FileWriter fileWriter = new FileWriter(FILE_PATH);
                CSVWriter csvWriter = new CSVWriter(fileWriter);
                // adding header to csv
                String[] header = {"EmployeeID", "Name", "Task", "Date-from", "Date-to"};
                csvWriter.writeNext(header);
                csvWriter.writeNext(message.getAllData());
                System.out.println("Successfully wrote to the file. ");
                csvWriter.close();
                fileWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    @Override
    public boolean findFileById(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path) && !Files.isDirectory(path);
    }

    public void createDirectory(String dirPath){
        File dir = new File(dirPath);
        dir.mkdirs();
    }

    private void deleteFile(String filePath){
        File f = new File(filePath);
        boolean success = f.delete();
        if (success)
            System.out.println("deleted file");
        else
            System.out.println("could not delete file");
    }
    private void renameFile(String oldName, String newName){
        File file = new File(oldName);
        boolean success = file.renameTo(new File(newName));
        if (success)
            System.out.println("renamed file");
        else
            System.out.println("could not rename file");
    }

    public String getFilePath(int employeeId) {
        String DATATYPE = ".csv";
        return DIR_PATH +"/"+employeeId + DATATYPE;
    }

    private boolean validateData(Message message) {
        if (!String.valueOf(message.getEmployeeId()).matches("[0-9]+"))
            return false;

        return isValidDate(message.getDateFrom()) && isValidDate(message.getDateTo());
    }
    private boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:ms");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException pe) {
            pe.printStackTrace();
            return false;
        }
        return true;
    }

}
