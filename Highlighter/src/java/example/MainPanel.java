// -*- mode:java; encoding:utf-8 -*-
// vim:set fileencoding=utf-8:
// @homepage@

package example;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.text.JTextComponent;

public final class MainPanel extends JPanel {
  private static final String PATTERN = "Swing";
  private static final String[] INIT_TXT = {
    "Trail: Creating a GUI with JFC/Swing",
    "Lesson: Learning Swing by Example",
    "This lesson explains the concepts you need to",
    " use Swing components in building a user interface.",
    " First we examine the simplest Swing application you can write.",
    " Then we present several progressively complicated examples of creating",
    " user interfaces using components in the javax.swing package.",
    " We cover several Swing components, such as buttons, labels, and text areas.",
    " The handling of events is also discussed,",
    " as are layout management and accessibility.",
    " This lesson ends with a set of questions and exercises",
    " so you can test yourself on what you've learned.",
    "https://docs.oracle.com/javase/tutorial/uiswing/learn/index.html"
  };
  private static final HighlightPainter HIGHLIGHT = new DefaultHighlightPainter(Color.YELLOW);

  private MainPanel() {
    super(new BorderLayout());

    JTextArea jta = new JTextArea();
    jta.setLineWrap(true);
    jta.setText(String.join("\n", INIT_TXT));

    JButton highlight = new JButton("highlight: " + PATTERN);
    highlight.addActionListener(e -> {
      jta.setEditable(false);
      setHighlight(jta, PATTERN);
    });

    JButton clear = new JButton("clear");
    clear.addActionListener(e -> {
      jta.setEditable(true);
      jta.getHighlighter().removeAllHighlights();
    });

    Box box = Box.createHorizontalBox();
    box.add(Box.createHorizontalGlue());
    box.add(highlight);
    box.add(clear);
    add(new JScrollPane(jta));
    add(box, BorderLayout.SOUTH);
    setPreferredSize(new Dimension(320, 240));
  }

  protected static void setHighlight(JTextComponent jtc, String pattern) {
    jtc.getHighlighter().removeAllHighlights();
    try {
      Highlighter highlighter = jtc.getHighlighter();
      Document doc = jtc.getDocument();
      String text = doc.getText(0, doc.getLength());
      Matcher matcher = Pattern.compile(pattern).matcher(text);
      int pos = 0;
      while (matcher.find(pos) && !matcher.group().isEmpty()) {
        pos = matcher.end();
        highlighter.addHighlight(matcher.start(), pos, HIGHLIGHT);
      }
      // int pos = text.indexOf(pattern);
      // while (pos >= 0) {
      //   int nextp = pos + pattern.length();
      //   jtc.getHighlighter().addHighlight(pos, nextp, HIGHLIGHT);
      //   pos = text.indexOf(pattern, nextp);
      // }
    } catch (BadLocationException | PatternSyntaxException ex) {
      UIManager.getLookAndFeel().provideErrorFeedback(jtc);
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
