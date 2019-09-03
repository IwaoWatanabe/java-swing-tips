// -*- mode:java; encoding:utf-8 -*-
// vim:set fileencoding=utf-8:
// @homepage@

package example;

import com.sun.java.swing.plaf.windows.WindowsSliderUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Objects;
import javax.swing.*;
import javax.swing.plaf.metal.MetalSliderUI;

public final class MainPanel extends JPanel {
  public static final int MAXI = 80;
  public static final int MINI = 40;

  private MainPanel() {
    super(new GridLayout(2, 1, 5, 5));

    JSlider slider1 = makeSlider("ChangeListener");
    JSlider slider2 = makeSlider("TrackListener");
    if (slider2.getUI() instanceof WindowsSliderUI) {
      slider2.setUI(new WindowsDragLimitedSliderUI(slider2));
    } else {
      slider2.setUI(new MetalDragLimitedSliderUI());
    }
    add(slider1);
    add(slider2);
    setPreferredSize(new Dimension(320, 240));
  }

  private static JSlider makeSlider(String title) {
    JSlider slider = new JSlider(0, 100, 40);
    slider.setBorder(BorderFactory.createTitledBorder(title));
    slider.setMajorTickSpacing(10);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    Dictionary<?, ?> dictionary = slider.getLabelTable();
    if (Objects.nonNull(dictionary)) {
      Enumeration<?> elements = dictionary.elements();
      while (elements.hasMoreElements()) {
        JLabel label = (JLabel) elements.nextElement();
        int v = Integer.parseInt(label.getText());
        if (v > MAXI || v < MINI) {
          label.setForeground(Color.RED);
        }
      }
    }
    slider.getModel().addChangeListener(e -> {
      BoundedRangeModel m = (BoundedRangeModel) e.getSource();
      if (m.getValue() > MAXI) {
        m.setValue(MAXI);
      } else if (m.getValue() < MINI) {
        m.setValue(MINI);
      }
    });
    return slider;
  }

  private static class WindowsDragLimitedSliderUI extends WindowsSliderUI {
    protected WindowsDragLimitedSliderUI(JSlider slider) {
      super(slider);
    }

    @Override protected TrackListener createTrackListener(JSlider slider) {
      return new TrackListener() {
        @Override public void mouseDragged(MouseEvent e) {
          // case HORIZONTAL:
          int halfThumbWidth = thumbRect.width / 2;
          int thumbLeft = e.getX() - offset;
          int maxPos = xPositionForValue(MAXI) - halfThumbWidth;
          int minPos = xPositionForValue(MINI) - halfThumbWidth;
          if (thumbLeft > maxPos) {
            e.translatePoint(maxPos + offset - e.getX(), 0);
          } else if (thumbLeft < minPos) {
            e.translatePoint(minPos + offset - e.getX(), 0);
          }
          super.mouseDragged(e);
        }
      };
    }
  }

  private static class MetalDragLimitedSliderUI extends MetalSliderUI {
    @Override protected TrackListener createTrackListener(JSlider slider) {
      return new TrackListener() {
        @Override public void mouseDragged(MouseEvent e) {
          // case HORIZONTAL:
          int halfThumbWidth = thumbRect.width / 2;
          int thumbLeft = e.getX() - offset;
          int maxPos = xPositionForValue(MAXI) - halfThumbWidth;
          int minPos = xPositionForValue(MINI) - halfThumbWidth;
          if (thumbLeft > maxPos) {
            e.translatePoint(maxPos + offset - e.getX(), 0);
          } else if (thumbLeft < minPos) {
            e.translatePoint(minPos + offset - e.getX(), 0);
          }
          super.mouseDragged(e);
        }
      };
    }
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
