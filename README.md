# autosierra
Tool for personal use to automate batch processing in the Innovative Sierra software program. 

Function: Reads a plain text file containing barcodes of items to be processed in Sierra, opens an instance of Sierra or switches to a running Sierra process, navigates through basic Sierra menus, and executes a macro stored in Sierra's CTRL+F12 keystroke for each barcode.

Issues: Due to the nature of the program's simulation of human keystrokes, the program cannot detect input errors or any feedback from Sierra.  This program will not work properly if user mouse clicks or keyboard actions occur during execution, and is therefore not suitable for non-personal applications.

Todos: Add abort feature.
