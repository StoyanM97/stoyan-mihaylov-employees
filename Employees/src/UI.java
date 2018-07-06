import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

class UI extends JFrame implements ActionListener {
    private File file;
    private String[][] employeesInfo;

    UI() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.getCurrentDirectory();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.file = fileChooser.getSelectedFile();
        } else {
            System.out.println("No Selection ");
            System.exit(0);
        }

        this.setSize(800, 800);

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Dimension dimension = toolkit.getScreenSize();

        int xPosition = dimension.width / 2 - this.getWidth() / 2;
        int yPosition = dimension.height / 2 - this.getHeight() / 2;

        this.setLocation(xPosition, yPosition);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Employees");


    }

    File getFile() {
        return this.file;
    }

    public String[][] getEmployeesInfo() {
        return employeesInfo;
    }

    void setEmployeesInfo(String[][] employeesInfo) {
        this.employeesInfo = new String[employeesInfo.length][employeesInfo[0].length];
        for (int i = 0; i < employeesInfo.length; i++) {
            for (int j = 0; j < employeesInfo[0].length; j++) {
                this.employeesInfo[i][j] = employeesInfo[i][j];
            }
        }
    }

    void addTable(String[][] employeesInfo) {
        String[] columnNames = {"Employee1ID", "Employee2ID", "Project IDs", "Days Worked"};
        JTable table = new JTable(employeesInfo, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        this.add(scrollPane);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
