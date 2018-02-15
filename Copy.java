package Copy;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Alvin on 15/02/2018.
 */
public class Copy extends JFrame{

    private JButton copyUser, copyPass, nextAcc, inputFile;
    private JTextArea userText, passText, loadedText;
    private File file;
    private BufferedReader reader;

    public static void main(String[] args) {
        Copy cp = new Copy();
    }

    private Copy(){
        copyUser = new JButton("copy");
        copyPass = new JButton("copy");
        nextAcc = new JButton("next");
        inputFile = new JButton("input file");

        userText = new JTextArea(1,20);
        passText = new JTextArea(1,20);
        loadedText = new JTextArea("file loaded");

        JPanel jp = new JPanel(new FlowLayout());
        jp.add(userText);
        jp.add(copyUser);
        jp.add(passText);
        jp.add(copyPass);
        jp.add(inputFile);
        jp.add(nextAcc);
        jp.add(loadedText); loadedText.setVisible(false);

        add(jp);

        buttonHandler bh = new buttonHandler(this);
        copyUser.addActionListener(bh);
        copyPass.addActionListener(bh);
        nextAcc.addActionListener(bh);
        inputFile.addActionListener(bh);

        setSize(350,140);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
    }

    private void readFile(){
        try {
            String currentLine = reader.readLine();
            if(currentLine != null){
                String user = currentLine.substring(0, currentLine.lastIndexOf(":"));
                String pass = currentLine.substring(currentLine.lastIndexOf(":")+1, currentLine.length());
                userText.setText(user);
                passText.setText(pass);
            }else{
                loadedText.setText("end of file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyToClipboard(String text){
        StringSelection selectedText = new StringSelection(text);
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        cb.setContents(selectedText, null);
    }

    class buttonHandler implements ActionListener{
        Copy c;

        buttonHandler(Copy c){
            this.c = c;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(copyUser)){
                copyToClipboard(userText.getText());

            }else if (e.getSource().equals(copyPass)){
                copyToClipboard(passText.getText());

            }else if (e.getSource().equals(nextAcc)){
                if(file != null){ readFile(); }

            }else if (e.getSource().equals(inputFile)){
                final JFileChooser fc = new JFileChooser();

                int responseVal = fc.showOpenDialog(Copy.this);
                if(responseVal == JFileChooser.APPROVE_OPTION){
                    file = fc.getSelectedFile();
                    try {
                        reader = new BufferedReader(new FileReader(file));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }

                    loadedText.setVisible(true);
                }else{
                    System.out.println("no file chosen");
                }
            }
        }
    }
}