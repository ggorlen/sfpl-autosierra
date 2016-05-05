// This program only works in Windows.  Tested in Windows 10.
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.*;

public class AutoSierra {

    public static void main(String[] args) throws AWTException,IOException {
        
        // Check for command line arg speciying filename
        if (args.length == 0) {
            System.out.println("Provide a command line argument with input filename.");
            System.exit(0);
        }

         // https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
        //final JFileChooser fc = new JFileChooser();
        //int returnVal = fc.showOpenDialog(aComponent);
        
        // Read text file from filename and convert to array
        String[] text = readTextFile(args[0]);

        // Launch Sierra as active window
        Runtime.getRuntime().exec("notepad");
        // ----or change windows with alt tab : http://stackoverflow.com/questions/2694365/can-i-move-another-programs-window-to-the-front-of-focus        
        
        // Make a robot object
        Robot robot = new Robot();

        // Enter text into window
        for(int i = 0; i < text.length; i++) {
            StringSelection stringSelection = new StringSelection(text[i]);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, stringSelection);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(500);
        }
    }
    
    private static String[] readTextFile(String filename) {
        String input = "";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-16"))) {
            String line;

            while ((line = br.readLine()) != null) {
                input += line + "\n ";
            }
        }
		catch(IOException ex) {
			System.out.println (ex.toString());
		}
        return input.split(" ");
    }
}
