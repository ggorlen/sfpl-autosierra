// This program works in Windows running Java 8.  Tested in Windows 10 and 7.

// TODO: add abort option with keylistener.  Add error log.  Improve user interface.

import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.*;
import javax.swing.JOptionPane; 

public class AutoSierra {

    private static final String ENCODING = "UTF-8";
    
    public static void main(String[] args) throws AWTException,IOException {

        // Get filename
        String filename = JOptionPane.showInputDialog(null, "Enter input filename with extension \n(Ex: file.txt):", "file.txt");

        // Debug:
        // String encoding = JOptionPane.showInputDialog(null, "Enter name of encoding for input \n(Ex: UTF-16):", "UTF-16");
        // String process = JOptionPane.showInputDialog(null, "Enter name of process with extension to launch \n(Ex: sierra.exe):", "sierra.exe");
        
        // Display beginning message and offer to abort
        int result = JOptionPane.showConfirmDialog(null, "Beginning batch processing; switch the active window to Sierra in the next 10 seconds, \n" +
                                                        "then do not use the mouse or keyboard until the completion dialog box appears.",
        "Alert", JOptionPane.OK_CANCEL_OPTION);
        
        if (result != 0) {
            System.exit(0);
        }
        
        // Read text file from filename and convert to String array
        String[] text = readTextFile(filename);
        
        // Launch Sierra as active window.  Problem: can't launch Sierra as easily as notepad?
        // Runtime.getRuntime().exec(process);

        // Give user time to begin Sierra as active process
        try {
            Thread.sleep(15000);   // 1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        
        // Make a robot object
        Robot robot = new Robot();

        // Direct program to Sierra's "Search / Holds" screen
        robot.keyPress(KeyEvent.VK_F3);
        robot.keyRelease(KeyEvent.VK_F3);
        robot.delay(5000);
        robot.keyPress(KeyEvent.VK_B);
        robot.keyRelease(KeyEvent.VK_B);
        robot.delay(500);
        
        // Enter text into window
        for(int i = 0; i < text.length; i++) {
            
            // Copy each barcode into Sierra
            StringSelection stringSelection = new StringSelection(text[i]);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, stringSelection);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            robot.delay(3000);

            // Execute Sierra Macro at CTRL+F12
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_F12);
            robot.keyRelease(KeyEvent.VK_F12);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            robot.delay(2000);
        }
        
        // Display end message:
        JOptionPane.showMessageDialog(null, "Done!");
    }
    
    private static String[] readTextFile(String filename) {
        String input = "";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), ENCODING))) {
            String line;
            while((line = br.readLine()) != null) {
                input += line + " ";
            }
        }
	catch(IOException ex) {
	    System.out.println (ex.toString());
	}
        return input.split(" ");
    }
}
