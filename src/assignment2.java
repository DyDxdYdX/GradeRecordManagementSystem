import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;

public class assignment2 extends JPanel implements ActionListener{
    private JMenuBar menuBar;
    private JLabel nameTItle;
    private JLabel registerTitle;
    private JLabel idTitle;
    private JLabel numOfSemTitle;
    private JLabel gpaTitle;
    private JTextField name;
    private JTextField id;
    private JLabel numSem;
    private JTextField gpa;
    private JTextArea studentList;
    private JButton minusButton;
    private JButton plusButton;
    private JComboBox sem;
    private JButton setGPAButton;
    private JButton addStdButton;
    private JButton showButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton saveButton;
    private JList stdMatricList;
    DefaultListModel<String> model = new DefaultListModel<>();
    Vector comboBoxItems = new Vector();
    DefaultComboBoxModel semester = new DefaultComboBoxModel(comboBoxItems);
    DefaultListModel<Double> stdGpa;
    public static ArrayList<student> stdItemList = new ArrayList<>();
    JMenu fileMenu = new JMenu ("File");
    JMenuItem openItem = new JMenuItem ("Open");


    public assignment2() throws FileNotFoundException {
        //construct preComponents
        fileMenu.add (openItem);

        //construct components
        registerTitle = new JLabel("Regestered List:");
        menuBar = new JMenuBar();
        menuBar.add (fileMenu);
        nameTItle = new JLabel ("Name");
        idTitle = new JLabel ("Matric Number");
        numOfSemTitle = new JLabel ("Number of Sem");
        gpaTitle = new JLabel ("GPA");
        name = new JTextField ();
        id = new JTextField ();
        numSem = new JLabel ("0");
        gpa = new JTextField ("0.0");
        studentList = new JTextArea ();
        minusButton = new JButton ("-");
        plusButton = new JButton ("+");
        sem = new JComboBox (semester);
        setGPAButton = new JButton ("Set GPA");
        addStdButton = new JButton ("Add");
        showButton = new JButton ("Show");
        updateButton = new JButton ("Update");
        deleteButton = new JButton ("Delete");
        saveButton = new JButton ("Save");
        stdMatricList = new JList<String> (model);
        stdGpa = new DefaultListModel<>();

        //adjust size and set layout
        setPreferredSize (new Dimension (784, 651));
        setLayout (null);

        //add components
        add (registerTitle);
        add (menuBar);
        add (nameTItle);
        add (idTitle);
        add (numOfSemTitle);
        add (gpaTitle);
        add (name);
        add (id);
        add (numSem);
        add (gpa);
        add (studentList);
        add (minusButton);
        add (plusButton);
        add (sem);
        add (setGPAButton);
        add (addStdButton);
        add (showButton);
        add (updateButton);
        add (deleteButton);
        add (saveButton);
        add (stdMatricList);

        //set component bounds (only needed by Absolute Positioning)
        registerTitle.setBounds(470, 35,200,25);
        menuBar.setBounds (0, 0, 784, 30);
        nameTItle.setBounds (5, 35, 45, 25);
        idTitle.setBounds (5, 65, 90, 25);
        numOfSemTitle.setBounds (5, 95, 95, 25);
        gpaTitle.setBounds (5, 125, 35, 25);
        name.setBounds (105, 35, 360, 25);
        id.setBounds (105, 65, 360, 25);
        numSem.setBounds (105, 95, 45, 25);
        gpa.setBounds (180, 125, 110, 25);
        gpa.setEditable(false);
        studentList.setBounds (5, 245, 775, 370);
        studentList.setEditable(false);
        minusButton.setBounds (155, 95, 45, 25);
        plusButton.setBounds (205, 95, 45, 25);
        sem.setBounds (105, 125, 70, 25);
        setGPAButton.setBounds (295, 125, 100, 25);
        addStdButton.setBounds (365, 215, 100, 25);
        showButton.setBounds (470, 215, 100, 25);
        updateButton.setBounds (575, 215, 100, 25);
        deleteButton.setBounds (680, 215, 100, 25);
        saveButton.setBounds (680, 620, 100, 25);
        stdMatricList.setBounds (470, 65, 310, 145);

        //add gui function
        minusButton.addActionListener(this);
        plusButton.addActionListener(this);
        setGPAButton.addActionListener(this);
        addStdButton.addActionListener(this);
        showButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        saveButton.addActionListener(this);
        sem.addActionListener(this);
        openItem.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae){
        String semester = numSem.getText();
        int tempSemNum = Integer.parseInt(semester);
        if(ae.getSource()==plusButton){
            tempSemNum++;
            gpa.setEditable(true);
            comboBoxItems.add(tempSemNum);
            numSem.setText(String.valueOf(tempSemNum));
            stdGpa.addElement(0.0);
        }
        if(ae.getSource()==minusButton){
            if(tempSemNum==0) tempSemNum=0;
            else{
                tempSemNum--;
                if(tempSemNum==0) gpa.setEditable(false);
                comboBoxItems.remove(tempSemNum);
                numSem.setText(String.valueOf(tempSemNum));
                stdGpa.removeElementAt(tempSemNum);
            }
        }
        stdGpa.setSize(tempSemNum);
        if(ae.getSource()==sem){
            gpa.setText(String.valueOf(stdGpa.elementAt(sem.getSelectedIndex())));
        }
        if(ae.getSource()==setGPAButton){
            try{
                if(Double.parseDouble(gpa.getText())>4){
                    JOptionPane.showMessageDialog(null,"GPA MUST BE LESS THAN 4");
                }
                else if(Double.parseDouble(gpa.getText())<0){
                    JOptionPane.showMessageDialog(null,"GPA CANNOT BE NEGATIVE");
                }
                else stdGpa.add(sem.getSelectedIndex(), Double.parseDouble(gpa.getText()));
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Please Enter a Number");
            }
            try {
                gpa.setText(String.valueOf(stdGpa.elementAt(sem.getSelectedIndex())));
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"GPA is EMPTY!");
            }
        }
        if(ae.getSource()==addStdButton){
            boolean duplicate = false;
            for (int i = 0; i<model.getSize();i++){
                if (id.getText().equals(model.elementAt(i))) duplicate = true;
            }
            if (name.getText().isEmpty() || id.getText().isEmpty() || tempSemNum == 0 || duplicate){
                if(name.getText().isEmpty()) JOptionPane.showMessageDialog(null,"Name is EMPTY!");
                if(id.getText().isEmpty()) JOptionPane.showMessageDialog(null,"Matric Number is EMPTY!");
                if(tempSemNum == 0) JOptionPane.showMessageDialog(null,"Please Add Semester!");
                if(duplicate) JOptionPane.showMessageDialog(null,"Duplicate Matric Number FOUNDED");
            } else {
                student tempStd = new student(name.getText(), id.getText(), Integer.parseInt(numSem.getText()));
                model.addElement(id.getText());
                for (int i = 0; i < tempSemNum; i++) {
                    tempStd.setGpa(stdGpa.elementAt(i));
                }
                stdItemList.add(tempStd);
                studentList.selectAll();
                studentList.replaceSelection("");
                studentList.append("Name: " + name.getText() + "\n");
                studentList.append("Matric Number: " + id.getText() + "\n");
                studentList.append("Number of Semester: " + numSem.getText() + "\n\n");
                for (int i = 0; i < tempSemNum; i++) {
                    studentList.append("Sem " + (i + 1) + ": " + String.valueOf(stdGpa.elementAt(i)) + "\n");
                }
                name.setText("");
                id.setText("");
                numSem.setText("0");
                comboBoxItems.removeAllElements();
                stdGpa.removeAllElements();
                gpa.setText("");
            }
        }
        if(ae.getSource()==showButton){
            gpa.setEditable(true);
            try {
                int i = stdMatricList.getSelectedIndex();
                name.setText(stdItemList.get(i).getName());
                id.setText(stdItemList.get(i).getId());
                numSem.setText(String.valueOf(stdItemList.get(i).getSem()));
                stdGpa.removeAllElements();
                comboBoxItems.removeAllElements();
                for (int j = 0; j < Integer.parseInt(numSem.getText()); j++) {
                    comboBoxItems.add(j + 1);
                    stdGpa.addElement(stdItemList.get(i).getGpa().get(j));
                }
                studentList.selectAll();
                studentList.replaceSelection("");
                studentList.append("Name: " + name.getText() + "\n");
                studentList.append("Matric Number: " + id.getText() + "\n");
                studentList.append("Number of Semester: " + numSem.getText() + "\n\n");
                for (int j = 0; j < Integer.parseInt(numSem.getText()); j++) {
                    studentList.append("Sem " + (j + 1) + ": " + String.valueOf(stdGpa.elementAt(j)) + "\n");
                }
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null,"PLEASE SELECT A DATA!");
            }
        }
        if(ae.getSource()==updateButton){
            gpa.setEditable(true);
            boolean duplicate = false;
            for (int i = 0; i<model.getSize();i++){
                if (id.getText().equals(model.elementAt(i))) duplicate = true;
            }
            if (name.getText().isEmpty() || id.getText().isEmpty() || tempSemNum == 0 || duplicate) {
                if (name.getText().isEmpty()) JOptionPane.showMessageDialog(null, "Name is EMPTY!");
                if (id.getText().isEmpty()) JOptionPane.showMessageDialog(null, "Matric Number is EMPTY!");
                if (tempSemNum == 0) JOptionPane.showMessageDialog(null, "Please Add Semester!");
                if (duplicate) JOptionPane.showMessageDialog(null, "Duplicate Matric Number FOUNDED");
            }else {
                try {
                    int i = stdMatricList.getSelectedIndex();
                    stdItemList.get(i).setName(name.getText());
                    stdItemList.get(i).setId(id.getText());
                    stdItemList.get(i).setSem(Integer.parseInt(numSem.getText()));
                    stdItemList.get(i).initialize();
                    for (int j = 0; j < Integer.parseInt(numSem.getText()); j++) {
                        stdItemList.get(i).setGpa(stdGpa.elementAt(j));
                    }
                    model.setElementAt(id.getText(), i);
                    studentList.selectAll();
                    studentList.replaceSelection("");
                    studentList.append("Name: " + name.getText() + "\n");
                    studentList.append("Matric Number: " + id.getText() + "\n");
                    studentList.append("Number of Semester: " + numSem.getText() + "\n\n");
                    for (int j = 0; j < Integer.parseInt(numSem.getText()); j++) {
                        studentList.append("Sem " + (j + 1) + ": " + String.valueOf(stdGpa.elementAt(j)) + "\n");
                    }
                    stdMatricList.clearSelection();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "PLEASE SELECT A DATA!");
                }
            }
        }
        if (ae.getSource()==deleteButton){
            try {
                int i = stdMatricList.getSelectedIndex();
                model.removeElementAt(i);
                stdMatricList.clearSelection();
                studentList.selectAll();
                studentList.replaceSelection("");
                name.setText("");
                id.setText("");
                numSem.setText("0");
                comboBoxItems.removeAllElements();
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null,"PLEASE SELECT A DATA!");
            }
        }
        if (ae.getSource()==saveButton){
            if(studentList.getText().isEmpty()) JOptionPane.showMessageDialog(null,"THERE IS NOTHING TO BE SAVED!");
            else {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Student Data.txt"))) {
                    for (int i = 0; i < stdItemList.size(); i++) {
                        writer.write(stdItemList.get(i).getName() + System.lineSeparator());
                        writer.write(stdItemList.get(i).getId() + System.lineSeparator());
                        writer.write(stdItemList.get(i).getSem() + System.lineSeparator());
                        for (int j = 0; j < stdItemList.get(i).gpa.size(); j++) {
                            writer.write(String.valueOf(stdItemList.get(i).gpa.get(j)) + System.lineSeparator());
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Save");
                    setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error");
                }
            }
        }
        if (ae.getSource()==openItem){

                File selectedFile = new File("Student Data.txt");
                try {
                    Scanner reader = new Scanner(selectedFile);
                    while(reader.hasNextLine()){
                        String name = reader.nextLine();
                        String id = reader.nextLine();
                        int sem = Integer.parseInt(reader.nextLine());
                        student std = new student(name,id,sem);
                        for (int i=0; i<sem; i++){
                            std.setGpa(Double.parseDouble(reader.nextLine()));
                        }
                        stdItemList.add(std);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"File not found!");
                }

            for (student std:stdItemList){
                model.addElement(std.getId());
            }
        }
    }

    public static void main (String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame ("GRADE RECORD MANAGEMENT SYSTEM");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new assignment2());
        frame.pack();
        frame.setVisible (true);
        frame.setResizable(false);
    }
}
