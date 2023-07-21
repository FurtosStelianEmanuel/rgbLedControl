/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rgbledcontrol;

import communicationbridge.InitializingPort;
import communicationbridge.SerialPortReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author Manel
 */
public class RgbLedControl {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        InitializingPort initializingPort = new InitializingPort();

        for(int i=0;i<10;i++){
            initializingPort.write("ceva\n");
            Thread.sleep(1000);
        }
        
        
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
    }

}
