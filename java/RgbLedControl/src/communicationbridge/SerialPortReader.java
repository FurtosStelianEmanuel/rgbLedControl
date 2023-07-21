/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicationbridge;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author Manel
 */
public class SerialPortReader implements SerialPortEventListener {

    private final SerialPort serialPort;
//    private String 
    private final int BUFFER_SIZE = 12;
    private StringBuilder buffer = new StringBuilder();

    public SerialPortReader(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR()) {//If data is available
//            System.out.println(event.getEventValue());
            if (event.getEventValue() >= BUFFER_SIZE) {//Check bytes count in the input buffer
                //Read data, if 10 bytes available 
                try {
                    String s = serialPort.readString(BUFFER_SIZE);
                    buffer.append(s);

                    if (s.contains("\n") && s.contains("\r")) {
                        System.out.println(buffer.toString());

                        buffer.setLength(0);
                    }
                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}
