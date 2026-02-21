package example;
//-*- mode:java; encoding:utf8n; coding:utf-8 -*-
// vim:set fileencoding=utf-8:
//@homepage@
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class SelectAll extends JPanel{
    private final JTextField field1 = new JTextField("aaaaaaaaa");
    private final JTextField field2 = new JTextField("bbbbbbbbb");
    public SelectAll() {
        super(new GridLayout(2,1));
        field1.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                ((JTextComponent)e.getSource()).selectAll();
            }
        });
        add(makeTitlePanel(field1, "focusGained: selectAll"));
        add(makeTitlePanel(field2, "default"));
        setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
        setPreferredSize(new Dimension(320, 200));
    }
    private JComponent makeTitlePanel(JComponent cmp, String title) {
        JPanel p = new JPanel(new java.awt.GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1.0;
        c.fill    = GridBagConstraints.HORIZONTAL;
        c.insets  = new Insets(5, 5, 5, 5);
        p.add(cmp, c);
        p.setBorder(BorderFactory.createTitledBorder(title));
        return p;
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                createAndShowGUI();
            }
        });
    }
    public static void createAndShowGUI() {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("@title@");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new SelectAll());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
