// -*- mode:java; encoding:utf-8 -*-
// vim:set fileencoding=utf-8:
// @homepage@

package example;

import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

public final class MainPanel extends JPanel {
  private MainPanel() {
    super(new GridLayout(1, 2));
    add(makeTitledPanel("Default", new JScrollPane(new JList<>(makeModel()))));
    add(makeTitledPanel("clearSelection", new JScrollPane(makeList(makeModel()))));
    setPreferredSize(new Dimension(320, 240));
  }

  private static ListModel<String> makeModel() {
    DefaultListModel<String> model = new DefaultListModel<>();
    model.addElement("aaaaaaa");
    model.addElement("bbbbbbbbbbbbb");
    model.addElement("cccccccccc");
    model.addElement("ddddddddd");
    model.addElement("eeeeeeeeee");
    return model;
  }

  private static JList<String> makeList(ListModel<String> model) {
    return new JList<String>(model) {
      private transient MouseInputListener listener;

      @Override public void updateUI() {
        removeMouseListener(listener);
        removeMouseMotionListener(listener);
        setForeground(null);
        setBackground(null);
        setSelectionForeground(null);
        setSelectionBackground(null);
        super.updateUI();
        listener = new ClearSelectionListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
      }
    };
    // list.putClientProperty("List.isFileList", Boolean.TRUE);
    // list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    // list.setFixedCellWidth(64);
    // list.setFixedCellHeight(64);
    // list.setVisibleRowCount(0);
    // return list;
  }

  private static Component makeTitledPanel(String title, Component c) {
    JPanel p = new JPanel(new BorderLayout());
    p.setBorder(BorderFactory.createTitledBorder(title));
    p.add(c);
    return p;
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(MainPanel::createAndShowGui);
  }

  private static void createAndShowGui() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
      ex.printStackTrace();
      Toolkit.getDefaultToolkit().beep();
    }
    JFrame frame = new JFrame("@title@");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.getContentPane().add(new MainPanel());
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}

class ClearSelectionListener extends MouseInputAdapter {
  private boolean startOutside;

  private static <E> void clearSelectionAndFocus(JList<E> list) {
    list.clearSelection();
    list.getSelectionModel().setAnchorSelectionIndex(-1);
    list.getSelectionModel().setLeadSelectionIndex(-1);
  }

  private static <E> boolean contains(JList<E> list, Point pt) {
    for (int i = 0; i < list.getModel().getSize(); i++) {
      if (list.getCellBounds(i, i).contains(pt)) {
        return true;
      }
    }
    return false;
  }

  @Override public void mousePressed(MouseEvent e) {
    JList<?> list = (JList<?>) e.getComponent();
    startOutside = !contains(list, e.getPoint());
    if (startOutside) {
      clearSelectionAndFocus(list);
    }
  }

  @Override public void mouseReleased(MouseEvent e) {
    startOutside = false;
  }

  @Override public void mouseDragged(MouseEvent e) {
    JList<?> list = (JList<?>) e.getComponent();
    if (contains(list, e.getPoint())) {
      startOutside = false;
    } else if (startOutside) {
      clearSelectionAndFocus(list);
    }
  }
}
