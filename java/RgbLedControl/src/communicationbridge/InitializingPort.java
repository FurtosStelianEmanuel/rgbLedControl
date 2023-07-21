/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicationbridge;

import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 * @author Manel
 */
public class InitializingPort {

    private SerialPort serialPort;

    public InitializingPort() {
        serialPort = new SerialPort("COM4");
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            int mask = SerialPort.MASK_RXCHAR;
            serialPort.setEventsMask(mask);
            serialPort.addEventListener(new SerialPortReader(serialPort));
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    public void write(String dataToWrite) {
        try {
            serialPort.writeString(dataToWrite);
        } catch (SerialPortException ex) {
            Logger.getLogger(InitializingPort.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
