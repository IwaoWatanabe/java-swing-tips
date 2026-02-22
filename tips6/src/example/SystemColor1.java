package example;
//-*- mode:java; encoding:utf8n; coding:utf-8 -*-
// vim:set fileencoding=utf-8:
//@homepage@
import java.awt.*;
import javax.swing.*;

public class SystemColor1 extends JPanel{
    public SystemColor1() {
        super(new BorderLayout());
        Box box = Box.createVerticalBox();
        box.add(makeSystemColorPanel(java.awt.SystemColor.desktop, "desktop"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.activeCaption, "activeCaption"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.inactiveCaption, "inactiveCaption"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.activeCaptionText, "activeCaptionText"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.inactiveCaptionText, "inactiveCaptionText"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.activeCaptionBorder, "activeCaptionBorder"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.inactiveCaptionBorder, "inactiveCaptionBorder"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.window, "window"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.windowText, "windowText"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.menu, "menu"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.menuText, "menuText"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.text, "text"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.textHighlight, "textHighlight"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.textText, "textText"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.textHighlightText, "textHighlightText"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.control, "control"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.controlLtHighlight, "controlLtHighlight"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.controlHighlight, "controlHighlight"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.controlShadow, "controlShadow"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.controlDkShadow, "controlDkShadow"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.controlText, "controlText"));
//        box.add(makeSystemColorPanel(java.awt.SystemColor.inactiveCaptionControlText, "inactiveControlText"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.control, "control"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.scrollbar, "scrollbar"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.info, "info"));
        box.add(makeSystemColorPanel(java.awt.SystemColor.infoText, "infoText"));
        box.add(Box.createRigidArea(new Dimension(320, 0)));

    //    box.add(Box.createVerticalStrut(10));
    //    box.add(makeSystemColorPanel(new Color(0xFF004E98), "test"));

        add(new JScrollPane(box));
    }

    private static JPanel makeSystemColorPanel(Color color, String text) {
        JPanel p = new JPanel(new BorderLayout());
        JTextField jtext = new JTextField(text+": 0x"+Integer.toHexString(color.getRGB()).toUpperCase());
        jtext.setEditable(false);
        p.add(jtext);
        JLabel l = new JLabel();
        l.setPreferredSize(new Dimension(32,0));
        l.setOpaque(true);
        l.setBackground(color);
        p.add(l, BorderLayout.EAST);
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
        frame.getContentPane().add(new SystemColor1());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
