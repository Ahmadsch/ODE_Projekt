package org.fhtw.dao;

import org.fhtw.entity.Message;

import java.io.IOException;

public interface Savable {
    void writeDataIntoFile(Message message) throws IOException;
    boolean findFileById(String employeeId);

}
