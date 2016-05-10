# autosierra
Tool to automate batch processing in the Innovative Sierra software program. 

Function: Reads a plain text file containing barcodes of items to be processed in Sierra, navigates through basic Sierra menus, and executes a macro stored in Sierra's CTRL+F12 keystroke for each barcode.

Issues: Due to the nature of the program's simulation of human keystrokes, the program currently cannot detect input errors or feedback from Sierra.  This program is therefore suitable for personal applications.

Todos: 
- Add abort feature using keylistener.
- Add exceptions for invalid barcode length and "31223" prefix (ex: 31223017531260)
