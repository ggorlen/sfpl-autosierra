# autosierra
Tool to automate batch processing in the Innovative Sierra software program. 

Function: Reads a plain text file containing barcodes of items to be processed in Sierra, navigates through basic Sierra menus, and executes a macro stored in Sierra's CTRL+F12 keystroke for each barcode.

Issues: Due to the nature of the program's simulation of human keystrokes, the program currently cannot detect input errors or feedback from Sierra.  This program is therefore suitable for personal applications.  Tested in Windows 10 and 7 running JRE 8.

Todos: 
- Add abort feature using https://github.com/kwhat/jnativehook
- Consider adding additional macro choices in drop-down list; but this would require a form or streamlining would be sacrificed
- Allow user to select encoding, also requiring a form or more buttons
- Consider creating a UI using Swing, SpringForm or similar
- [done] Add exceptions for invalid barcode length and "31223" prefix (ex: 31223017531260).  Replaces error log.
- [done] Add longer keystroke delays for magazines
- [done] Add elapsed time
- [done] Improved navigation in basic Sierra windows
