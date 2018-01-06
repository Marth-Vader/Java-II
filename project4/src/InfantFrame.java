import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
//import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Graphical user interface for interacting with Infant data
 * 
 * @author CS2334, modified by Lucas Black, Martha Nguyen
 * @version 11/13/17
 *
 */
public class InfantFrame extends JFrame
{
    /** Panel for selecting the station, statistic, and Year */
    private SelectionPanel selectionPanel;
    /** Panel for displaying statistic */
    private DataPanel dataPanel;
    /** Width of column 1 text fields in the data display */
    private final static int COLUMN_FIELD_WIDTH = 10;
    /** Infant that is currently loaded.  */
    private Infant infant;
 

    ///////////////////////////////////////////////////////////////////
    /**
     * 
     * @author CS2334, modified by Lucas Black, Martha Nguyen
     * @version 2017-10-20
     * 
     * Menu bar that provides file loading and program exit capabilities.
     *
     */
    private class FileMenuBar extends JMenuBar 
    {
        /** Menu on the menu bar */
        private JMenu menu;
        /** Exit menu option */
        private JMenuItem menuExit;
        /** Open menu option.  */
        private JMenuItem menuOpen;
        /** Reference to a file chooser pop-up */
        private JFileChooser fileChooser;

        /**
         * Constructor: fully assemble the menu bar and attach the 
         * necessary action listeners.
         */
        public FileMenuBar() {
            // Create the menu and add it to the menu bar
            menu = new JMenu("File");
            add(menu);

            
            // TODO: create and add menu items
            menuOpen = new JMenuItem("Open Configuration File");
            menuExit = new JMenuItem("Exit");
            
            menu.add(menuOpen);
            menu.add(menuExit);
            

            // TODO: Action listener for exit
            menuExit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    System.exit(0);
                }
            });
            

            // Filter for the file chooser: we only want files ending in '.dat'
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Configuration Files", "dat");

            // Create the file chooser: the default is to use the data directory
            fileChooser = new JFileChooser(new File("./data"));
            fileChooser.setFileFilter(filter);

            // Action listener for file open
            menuOpen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Ask for a file
                    int returnVal = fileChooser.showOpenDialog(menuOpen);
                    // Was a file specified?
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        // Yes
                        // Extract the file that was selected
                        File file = fileChooser.getSelectedFile();
                        try {
                            // TODO: Set to a "busy" cursor
                            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                            // Save the components of the selected file
                            String directory = file.getParent();
                            String fname = file.getName();
                            
                            
                            // TODO: Do the loading work: the first two characters are the Infant ID

                            
                            
                            loadData(directory, fname.substring(0, 2));
                            
                            //Return the cursor to standard form
                            InfantFrame.this.setCursor(null); 

                            // TODO: If we did not load any data then open a message dialog box to indicate an error
                            //if infant has no trials, we loaded no data
                            if(infant.getSize() == 0)
                            {
                                // Catch IO errors
                                JOptionPane.showMessageDialog(fileChooser, 
                                        "No data loaded");
                                // Return the cursor to standard form
                                InfantFrame.this.setCursor(null);
                            }
                            
                            
                            // TODO: Update the frame
                            InfantFrame.this.update();
                            
                        }
                        catch (IOException e2)
                        {
                            // Catch IO errors
                            JOptionPane.showMessageDialog(fileChooser, 
                                    "File load error");
                            // Return the cursor to standard form
                            InfantFrame.this.setCursor(null);
                        }

                    }
                    
                }

            });

        }
    }


    ///////////////////////////////////////////////////////////////////
    /**
     * 
     * @author CS2334, modified by Lucas Black, Martha Nguyen
     * @version 2016-11-01
     * 
     * Selection panel: contains JLists for the list of trials, the list of fieldNames and the 
     * list of subfieldNames.  Note that the displayed subfieldNames is dependent on which 
     * field has been selected
     *
     */
    private class SelectionPanel extends JPanel
    {
        /** Selection of available trials/weeks.  */
        private JList<String> trialList;
        /** Selection of the field.  */
        private JList<String> fieldList;
        /** Selection of the subfield.  */
        private JList<String> subfieldList;

        /** List model for the trial list.  */
        private DefaultListModel<String> trialListModel;
        /** List model for the field list.  */
        private DefaultListModel<String> fieldListModel;
        /** List model for the subfield list.  */
        private DefaultListModel<String> subfieldListModel;

        /** Scroll pane: trial list  */
        private JScrollPane trialScroller;
        /** Scroll pane: field list  */
        private JScrollPane fieldScroller;
        /** Scroll pane: subfield list  */
        private JScrollPane subfieldScroller;

        /**  Trial selection label */
        private JLabel trialLabel;
        /** Field selection label */
        private JLabel fieldLabel;
        /** Subfield selection label */
        private JLabel subfieldLabel;

        /** FieldMapper for one of the trials.  */
        private FieldMapper fieldMapper;

        /**
         * Constructor
         * 
         * Creates a 3x2 grid of components with labels down the left column and Jlist down the 2nd column
         * @throws IOException If there is an error finding or loading the data file.
         */
        private SelectionPanel() throws IOException
        {
            ////////////////////////////
            // Create the JList for trial selection
            // List model contains the data to be displayed.
            trialListModel = new DefaultListModel<String>();
            // JList for trials
            trialList = new JList<String>(trialListModel);
            // Multiple items can be selected at once
            trialList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            // Vertically organized list with an arbitrary number of rows
            trialList.setVisibleRowCount(-1);
            trialList.setLayoutOrientation(JList.VERTICAL);
            // Scroll pane goes around the JList
            trialScroller = new JScrollPane(trialList);
            trialScroller.setPreferredSize(new Dimension(300, 100));
            //trialList.setSelectedIndex(0);


            /////////////////////////////////////
            // JList for field selection
            // Model is of Strings
            // TODO: complete implementation    
            
            //field selection follows the same implementation as the trial list
            fieldListModel = new DefaultListModel<String>();
            fieldList = new JList<String>(fieldListModel);
            fieldList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            fieldList.setVisibleRowCount(-1);
            fieldList.setLayoutOrientation(JList.VERTICAL);
            fieldScroller = new JScrollPane(fieldList);
            fieldScroller.setPreferredSize(new Dimension(300,100));
            //fieldList.setSelectedIndex(0);


            //subfield selection follows the same implementation as the trial list
            subfieldListModel = new DefaultListModel<String>();
            subfieldList = new JList<String>(subfieldListModel);
            subfieldList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            subfieldList.setVisibleRowCount(-1);
            subfieldList.setLayoutOrientation(JList.VERTICAL);
            subfieldScroller = new JScrollPane(subfieldList);
            subfieldScroller.setPreferredSize(new Dimension(300,100));
            //subfieldList.setSelectedIndex(0);
//            

            

            ////////////////
            // Selection Listeners
            // TODO: complete implementation of all three listeners
            //********************* DO THIS
            
            //add action listener for trialList
            trialList.addListSelectionListener (new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent e)
                {

                    //update the InfantFrame if trial(s) are selected
                    InfantFrame.this.update();
                    
                }
                
            });
            
            
            //fieldList action listener
            fieldList.addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent e)
                {
                    //update the subfields when a field is selected
                    updateSubfieldSelections();
                    
                }
                
            });
            
            
            //subfieldList action listener
            subfieldList.addListSelectionListener (new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent e)
                {
                    //update the DataPanel in the InfantFrame
                    InfantFrame.this.update();
                    
                }
                
            });
            
            

            

            
            

            // Labels
            trialLabel = new JLabel("Trials");
            fieldLabel = new JLabel("Fields");
            subfieldLabel = new JLabel("Subfields");


            ///////////////
            // Layout
            this.setLayout(new GridBagLayout());
            GridBagConstraints layoutConst = new GridBagConstraints();


            // TODO: complete the layout
            layoutConst.fill = GridBagConstraints.VERTICAL;
            layoutConst.anchor = GridBagConstraints.CENTER;  
            layoutConst.insets = new Insets(20,20,20,20);
            
             
            //Adding the trial label
            layoutConst.weighty = .5;
            layoutConst.gridx = 0;
            layoutConst.gridy = 0;
            add(trialLabel, layoutConst);
            
            //adding field label
            layoutConst.fill = GridBagConstraints.VERTICAL;
            layoutConst.weighty = .5;
            layoutConst.gridx = 0;
            layoutConst.gridy = 1;
            add(fieldLabel, layoutConst);
            
            //adding subfield label
            layoutConst.fill = GridBagConstraints.VERTICAL;
            layoutConst.weighty = .5;
            layoutConst.gridx = 0;
            layoutConst.gridy = 2;
            add(subfieldLabel, layoutConst);
            
            //adding trial scroller
            layoutConst.gridx = 1;
            layoutConst.gridy = 0;
            add(trialScroller, layoutConst);
            
            //adding field scroller
            layoutConst.gridx = 1;
            layoutConst.gridy = 1;
            add(fieldScroller, layoutConst);
            
            //adding subfield scroller
            layoutConst.gridx = 1;
            layoutConst.gridy = 2;
            add(subfieldScroller, layoutConst);
            
            
            // TODO: Background color of the panel
            this.setBackground(new Color(170, 210, 160));

            // ///////////////////////
            // Set the names of the key objects: don't change these
            this.trialLabel.setName("TrialList");
            this.fieldLabel.setName("FieldList");
            this.subfieldLabel.setName("SubfieldList");
            // ///////////////////////
            setVisible(true);
        }

        /**
         * Update the entire selection panel based on the currently-loaded infant.
         * 
         * This method is generally called when a new infant is loaded
         * 
         */
        private void updateSelections()
        {
            ////////////////
            // Trial list

            // Clear all elements
            this.trialListModel.clear();


            //add trials to the infant object and update the trial list
            for (int i = 0; i < infant.getSize(); i++) {            

                trialListModel.addElement(infant.getItem(i).toString());

            }
                
            


            //****************************DO THIS
            /////////////////////
            // Field list

            // TODO: complete implementation (the fieldMapper will be
            //   useful here, if one exists)
            
            this.fieldListModel.clear();

            //if the infant contains trials
            if (infant.getSize() > 0) {
                
                //create a fieldMapper for that infant
                this.fieldMapper = infant.getItem(0).getFieldMapper();
                //create an iterator
                Iterator<String> fieldMapIt = fieldMapper.iterator();
                
                //while the iterator has elements
                while (fieldMapIt.hasNext()) {            
     
                    //add fields to the list
                    fieldListModel.addElement(fieldMapIt.next());

                }
                
            }

 
            // Update the subfields
           //this.updateSubfieldSelections();
        }

        
        
        /**
         * Update the subfield selection list based on the subfields available for the currently selected field
         * 
         * 
         * This method is called any time there is a change to the selected field.
         */
        private void updateSubfieldSelections()
        {
            // TODO: complete implementation
            
            //Field subfields = new subfieldListModel.clear();
            
            //Iterator<String> subfieldIt = fieldMapper.iterator();
            
            if (fieldMapper.size() > 0) {
                 
                //fieldMapper object 
                this.fieldMapper = infant.getItem(fieldList.getSelectedIndex()).getFieldMapper();

                //subfield iterator
                Iterator<String> subfieldIt = fieldMapper.getField(fieldList.getSelectedValue()).iterator();
                
                while(subfieldIt.hasNext()) {
                    
                    //populate list with subfields if that subfield doesn't exist
                    //if (!(subfieldListModel.contains(subfieldIt.next().toString()))) {
                        
                    subfieldListModel.addElement(subfieldIt.next().toString());
                    
                    //} 
                    
                }
              
            }
          
          

            //**************************DO THIS
            // Tell the rest of the frame that it needs to update
            InfantFrame.this.update();
        }

    }

    
    
    ///////////////////////////////////////////////////////////////////
    /**
     * DataPanel: display selection information and statistics
     * 
     * @author CS2334, modified by Lucas Black, Martha Nguyen
     * @version 2017-10-20
     * 
     * 
     *
     */

    private class DataPanel extends JPanel
    {
        // Labels
        private JLabel infantIDLabel = new JLabel("Infant ID:");
        private JLabel fieldNameLabel = new JLabel("Field:");
        private JLabel subfieldNameLabel = new JLabel("Subfield:");
        private JLabel maxLabel = new JLabel("Max:");
        private JLabel maxWeekLabel = new JLabel("on");
        private JLabel maxTimeLabel = new JLabel("at");
        private JLabel minLabel = new JLabel("Min");
        private JLabel minWeekLabel = new JLabel("on");
        private JLabel minTimeLabel = new JLabel("at");
        private JLabel averageLabel = new JLabel("Average:");

        // TODO: complete implementation
        //creates the JTextFields corresponding to the Label Names
        private JTextField infantIDField = new JTextField();
        private JTextField fieldNameField = new JTextField();
        private JTextField subfieldNameField = new JTextField();
        private JTextField maxValueField = new JTextField();
        private JTextField maxWeekField = new JTextField();
        private JTextField maxTimeField = new JTextField();
        private JTextField minValueField = new JTextField();
        private JTextField minWeekField= new JTextField();
        private JTextField minTimeField = new JTextField();
        private JTextField averageValueField = new JTextField();
        

        


        /** 
         * Constructor
         * 
         * Create and lay out the data display panel
         */
        private DataPanel()
        {

            // Background color of the panel
            this.setBackground(new Color(200, 200, 230));

            // Set the text fields to be non-editable
            infantIDField.setEditable(false);
            fieldNameField.setEditable(false);
            subfieldNameField.setEditable(false);
            maxValueField.setEditable(false);
            maxWeekField.setEditable(false);
            maxTimeField.setEditable(false);
            minValueField.setEditable(false);
            minWeekField.setEditable(false);
            minTimeField.setEditable(false);
            averageValueField.setEditable(false);
            
            //resize the text fields
            infantIDField.setPreferredSize(new Dimension(100, 20));
            fieldNameField.setPreferredSize(new Dimension(100, 20));
            subfieldNameField.setPreferredSize(new Dimension(100, 20));
            maxValueField.setPreferredSize(new Dimension(100, 20));
            maxWeekField.setPreferredSize(new Dimension(100, 20));
            maxTimeField.setPreferredSize(new Dimension(100, 20));
            minValueField.setPreferredSize(new Dimension(100, 20));
            minWeekField.setPreferredSize(new Dimension(100, 20));
            minTimeField.setPreferredSize(new Dimension(100, 20));
            averageValueField.setPreferredSize(new Dimension(100, 20));

            //////////////
            // TODO: Layout
            Insets insets = new Insets(15, 15, 15, 15);
            this.setLayout(new GridBagLayout());

            // TODO: complete the layout

            
            GridBagConstraints layoutConst = new GridBagConstraints();
            layoutConst.insets = insets;

            //InfantID 
            layoutConst.fill = GridBagConstraints.CENTER;
            layoutConst.weightx = 0.5;
            layoutConst.gridx = 0;
            layoutConst.gridy = 0;
            add(infantIDLabel, layoutConst);     

            layoutConst.fill = GridBagConstraints.HORIZONTAL;
            layoutConst.weightx = 1;
            layoutConst.gridx = 1;
            layoutConst.gridy = 0;
            add(infantIDField, layoutConst);
            
            
            //fieldname 
            layoutConst.fill = GridBagConstraints.CENTER;
            layoutConst.weightx = 0.5;
            layoutConst.gridx = 0;
            layoutConst.gridy = 1;
            add(fieldNameLabel, layoutConst);           

            layoutConst.fill = GridBagConstraints.HORIZONTAL;
            layoutConst.weightx = 0.5;
            layoutConst.gridx = 1;
            layoutConst.gridy = 1;
            add(fieldNameField, layoutConst);

            

            //subfield name
            layoutConst.fill = GridBagConstraints.CENTER;
            layoutConst.weightx = 0.5;
            layoutConst.gridx = 0;
            layoutConst.gridy = 2;
            add(subfieldNameLabel, layoutConst);           

            layoutConst.fill = GridBagConstraints.HORIZONTAL;
            layoutConst.weightx = 0.5;
            layoutConst.gridx = 1;
            layoutConst.gridy = 2;
            add(subfieldNameField, layoutConst);
            
            
            //max fields
            layoutConst.fill = GridBagConstraints.CENTER;
            layoutConst.weightx = 0.5;
            layoutConst.gridx = 0;
            layoutConst.gridy = 3;
            add(maxLabel, layoutConst);     

            layoutConst.fill = GridBagConstraints.HORIZONTAL;
            layoutConst.weightx = 1;
            layoutConst.gridx = 1;
            layoutConst.gridy = 3;
            add(maxValueField, layoutConst);
            
            layoutConst.fill = GridBagConstraints.CENTER;
            layoutConst.weightx = 0.5;
            layoutConst.gridx = 2;
            layoutConst.gridy = 3;
            add(maxWeekLabel, layoutConst);     

            layoutConst.fill = GridBagConstraints.HORIZONTAL;
            layoutConst.weightx = 1;
            layoutConst.gridx = 3;
            layoutConst.gridy = 3;
            add(maxWeekField, layoutConst);
            
            layoutConst.fill = GridBagConstraints.CENTER;
            layoutConst.weightx = 0.5;
            layoutConst.gridx = 4;
            layoutConst.gridy = 3;
            add(maxTimeLabel, layoutConst);     

            layoutConst.fill = GridBagConstraints.HORIZONTAL;
            layoutConst.weightx = 1;
            layoutConst.gridx = 5;
            layoutConst.gridy = 3;
            add(maxTimeField, layoutConst);

            
            
            //avg field
            layoutConst.fill = GridBagConstraints.CENTER;
            layoutConst.weightx = 0.5;
            layoutConst.gridx = 0;
            layoutConst.gridy = 4;
            add(averageLabel, layoutConst);     

            layoutConst.fill = GridBagConstraints.HORIZONTAL;
            layoutConst.weightx = 1;
            layoutConst.gridx = 1;
            layoutConst.gridy = 4;
            add(averageValueField, layoutConst);
            
            
            
            //min fields
            layoutConst.fill = GridBagConstraints.CENTER;
            layoutConst.weightx = 0.5;
            layoutConst.gridx = 0;
            layoutConst.gridy = 5;
            add(minLabel, layoutConst);     

            layoutConst.fill = GridBagConstraints.HORIZONTAL;
            layoutConst.weightx = 1;
            layoutConst.gridx = 1;
            layoutConst.gridy = 5;
            add(minValueField, layoutConst);
            
            layoutConst.fill = GridBagConstraints.CENTER;
            layoutConst.weightx = 0.5;
            layoutConst.gridx = 2;
            layoutConst.gridy = 5;
            add(minWeekLabel, layoutConst);     

            layoutConst.fill = GridBagConstraints.HORIZONTAL;
            layoutConst.weightx = 1;
            layoutConst.gridx = 3;
            layoutConst.gridy = 5;
            add(minWeekField, layoutConst);
            
            layoutConst.fill = GridBagConstraints.CENTER;
            layoutConst.weightx = 0.5;
            layoutConst.gridx = 4;
            layoutConst.gridy = 5;
            add(minTimeLabel, layoutConst);     

            layoutConst.fill = GridBagConstraints.HORIZONTAL;
            layoutConst.weightx = 1;
            layoutConst.gridx = 5;
            layoutConst.gridy = 5;
            add(minTimeField, layoutConst);

            
            
            /////////////////////////////////////
            // Component names: DO NOT CHANGE THIS CODE
            
            infantIDField.setName("infantIDField");
            fieldNameField.setName("fieldNameField");
            subfieldNameField.setName("subfieldNameField");
            maxValueField.setName("maxValueField");
            maxWeekField.setName("maxWeekField");
            maxTimeField.setName("maxTimeField");
            minValueField.setName("minValueField");
            minWeekField.setName("minWeekField");
            minTimeField.setName("minTimeField");
            averageValueField.setName("averageValueField");
            /////////////////////////////////////
            
            
        }

        /**
         * Update the data display panel with new Strings
         * 
         * @param infantID Infant ID
         * @param fieldName Field name 
         * @param subfieldName Subfield name
         * @param maxState Max value String
         * @param maxStateWeek Max value Week (Trial.toString())
         * @param maxStateTime Max value time
         * @param minState Min value String
         * @param minStateWeek Min value Week
         * @param minStateTime Min value time
         * @param average Average value
         */
        private void update(String infantID, String fieldName, String subfieldName,
                String maxState, String maxStateWeek, String maxStateTime,
                String minState, String minStateWeek, String minStateTime,
                String average)
        {
            // TODO: Set each of the text fields
            infantIDField.setText(infantID);
            fieldNameField.setText(fieldName);
            subfieldNameField.setText(subfieldName);
            maxValueField.setText(maxState);
            maxWeekField.setText(maxStateWeek);
            maxTimeField.setText(maxStateTime);
            minValueField.setText(minState);
            minWeekField.setText(minStateWeek);
            minTimeField.setText(minStateTime);
            averageValueField.setText(average);
        }
    }

    ///////////////////////////////////////////////////////////////////
    // InfantFrame definition

    /**
     * InfantFrame constructor
     * 
     * Two frames are side-by-side: SelectionPanel on the left and DataPanel on the right.
     * @throws IOException If there is an error finding or loading the data file.
     */
    public InfantFrame() throws IOException
    {
        super("Infant Explorer");
        
        /** Menu bar */
        FileMenuBar fileMenuBar;
        // Configure the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);

        // Menu bar
        fileMenuBar = new FileMenuBar();
        this.setJMenuBar(fileMenuBar);

        // GridBagLayout for the contents
        this.setLayout(new GridBagLayout());

        GridBagConstraints layoutConst = new GridBagConstraints();
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        layoutConst.insets = new Insets(10, 10, 10, 10);
        
        // Selection panel
        this.selectionPanel = new SelectionPanel();
        this.add(this.selectionPanel, layoutConst);


        
        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        
        this.dataPanel = new DataPanel();
        this.add(this.dataPanel, layoutConst);

        
        // TODO: Display panel
        selectionPanel.setVisible(true);
        dataPanel.setVisible(true);
        
        
        update();
        // Make the frame visible
        this.setVisible(true);
        
        // Compress the size of all objects
        this.pack();
        
    }

    /**
     * Load a new Infant.
     * 
     * This method is declared as "synchronized" to ensure that only one thread is allowed to call it
     * and other synchronized methods at once.
     * 
     * @param directory Directory in which the infant data files (csv) are located 
     * @param infantID ID of the infant to be loaded
     * @throws IOException Thrown if an error occurs while reading the file
     */
    public synchronized void loadData(String directory, String infantID) throws IOException
    {
        //creates the new infant object
        infant = new Infant(directory, infantID);
        
        //updates the Trial,Field, and Subfields options of the selection panel
        selectionPanel.updateSelections();

    }

    /**
     * Translate the selections made in the SelectionPanel into a set of Strings to
     * be displayed in the DataPanel.
     * 
     * This method is declared as "synchronized" to ensure that only one thread is allowed to call it
     * and other synchronized methods at once.
     * 
     * One of the challenges in implementing this method is that it can be called at any time.  In 
     * particular, the structures that it is referencing may be in the process of being updated.  So,
     * we cannot assume that things like "selected values" are not actually set to something interesting
     * or useful.
     * 
     */
    public synchronized void update()
    {
        // Which weeks have been selected?
        int[] indices = selectionPanel.trialList.getSelectedIndices();

        // Default values for the displayed Strings
        String infantID = "n/a";
        String maxStateString = "n/a";
        String maxStateWeekString = "n/a";
        String maxStateTimeString = "n/a";

        String minStateString = "n/a";
        String minStateWeekString = "n/a";
        String minStateTimeString = "n/a";

        String averageString = "n/a";

        String fieldName = "n/a";
        String subfieldName = "n/a";

        // Does the infant object exist and does it have at least one week?
        if (infant != null && infant.getSize() > 0)
        {
            // Create an Infant object with only the selected weeks
            Infant subInfant = new Infant(this.infant, indices);
            
            // Extract the infant ID
            infantID = infant.getInfantID();

            // Which field has been selected?
            fieldName = selectionPanel.fieldList.getSelectedValue();

            // Does this field exist and is it not empty
            if (fieldName != null && !fieldName.equals(""))
            {
                // Which subfield has been selected?
                subfieldName = selectionPanel.subfieldList.getSelectedValue();
                
                // TODO: complete the setting of the defined Strings
                //sets the maxState string of the subInfant
                //the strings associated with the max values
                maxStateString = subInfant.getMaxState(fieldName, subfieldName).getValue(fieldName, subfieldName).toString();
                maxStateWeekString = subInfant.getMaxState(fieldName, subfieldName).getTrial().toString();
                maxStateTimeString = subInfant.getMaxState(fieldName, subfieldName).getValue("time", "").toString();
                
                
                //the strings associated with the min values
                minStateString = subInfant.getMinState(fieldName, subfieldName).getValue(fieldName, subfieldName).toString();
                minStateWeekString = subInfant.getMinState(fieldName, subfieldName).getTrial().toString();
                minStateTimeString = subInfant.getMinState(fieldName, subfieldName).getValue("time", "").toString();
                
                //the string of the average
                averageString = subInfant.getAverageValue(fieldName, subfieldName).toString(); 
                
//                

                
                
            }
            else
            {
                // Indicate no meaningful fieldName
                fieldName = "n/a";
            }
        }

        // Tell the data panel to update
        this.dataPanel.update(infantID, fieldName, subfieldName,
                maxStateString, maxStateWeekString, maxStateTimeString,
                minStateString, minStateWeekString, minStateTimeString,
                averageString);
        
        
        //use this line to test displays
        //this.dataPanel.update("k1", "left", "y", "1", "2", "3:00", "0", "2", "2:00", "6");

    }

}
