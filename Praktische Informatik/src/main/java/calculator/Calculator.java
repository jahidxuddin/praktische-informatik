package main.java.calculator;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Calculator {
    public static void main(String[] args) {
        new CalculatorFrame();
    }
}

class CalculatorFrame extends JFrame {
    public boolean hasFirstClicked = false;

    public CalculatorFrame() {
        setupFrame();
        centerWindow();
        getContentPane().add(new CalculatorPanel(hasFirstClicked));
        HashMap<String, Component> frameComponents = createComponentMap(new HashMap<String, Component>(),
                getContentPane().getComponents());
        HashMap<String, Component> panelComponents = createComponentMap(frameComponents,
                ((JPanel) frameComponents.get(null)).getComponents());
        HashMap<String, Component> buttonPanelComponents = createComponentMap(panelComponents,
                ((JPanel) panelComponents.get(null)).getComponents());
        JTextField textField = ((JTextField) buttonPanelComponents.get("textField"));
        addKeyListener(new ButtonKeyListener(buttonPanelComponents, textField));
        setVisible(true);
    }

    public void setupFrame() {
        setTitle("Taschenrechner");
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("./calculator/icon.png")).getImage());
        setSize(300, 400);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
    }

    public void centerWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }

    public HashMap<String, Component> createComponentMap(HashMap<String, Component> componentMap,
            Component[] components) {
        for (int i = 0; i < components.length; i++) {
            componentMap.put(components[i].getName(), components[i]);
        }
        return componentMap;
    }
}

class CalculatorPanel extends JPanel {
    JTextField textField;
    JPanel buttons;

    boolean hasFirstClicked;

    public CalculatorPanel(boolean hasFirstClicked) {
        this.hasFirstClicked = hasFirstClicked;
        setLayout(new BorderLayout());
        setupTextField();
        setupButtons();
        add(textField, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);
    }

    public void setupTextField() {
        textField = new JTextField("0");
        textField.setFocusable(false);
        textField.setName("textField");
        textField.setFont(new Font(textField.getName(), Font.PLAIN, 36));
        textField.setEditable(false);
        textField.setBackground(new Color(34, 34, 34));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setBorder(new EmptyBorder(10, 10, 10, 10));
        textField.setForeground(Color.WHITE);
    }

    public void setupButtons() {
        buttons = new JPanel();
        buttons.setLayout(new GridLayout(4, 4));
        buttons.setBorder(new EmptyBorder(3, 3, 3, 3));
        buttons.setBackground(new Color(34, 34, 34));

        String[] buttonLabels = {
                "7", "8", "9", "÷",
                "4", "5", "6", "×",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String buttonLabel : buttonLabels) {
            buttonFactory(buttonLabel);
        }
    }

    public void buttonFactory(String label) {
        final JButton button = new JButton(label);

        button.setFocusable(false);
        button.setName(label);

        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(new Color(34, 34, 34)));

        if (label.equals("+") || label.equals("-") || label.equals("×") || label.equals("÷") || label.equals("=")
                || label.equals(".")) {
            button.setBackground(new Color(18, 18, 18));
            button.setFont(new Font(button.getName(), Font.PLAIN, 28));
        } else {
            button.setBackground(Color.BLACK);
            button.setFont(new Font(button.getName(), Font.PLAIN, 20));
        }

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String btnText = ((JButton) e.getSource()).getText();

                if (!hasFirstClicked) {
                    hasFirstClicked = true;
                    textField.setText("");
                }

                if (btnText.equals("+") || btnText.equals("-") || btnText.equals("×") || btnText.equals("÷")) {
                    String[] splittedTextField = textField.getText().split(" ");
                    if (splittedTextField.length > 1) {
                        if (textField.getText().contains("+") || splittedTextField[1].equals("-")
                                || textField.getText().contains("×") || textField.getText().contains("÷")) {
                            try {
                                calculate(splittedTextField[0], splittedTextField[1], splittedTextField[2], btnText);
                            } catch (ArrayIndexOutOfBoundsException ignored) {

                            }
                        } else {
                            if (textField.getText().length() == 0) {
                                textField.setText(btnText);
                            } else {
                                if (textField.getText().charAt(textField.getText().length() - 1) == ' ') {
                                    textField.setText(textField.getText() + btnText + " ");
                                } else {
                                    textField.setText(textField.getText() + " " + btnText + " ");
                                }
                            }
                        }
                    } else {
                        if (textField.getText().contains("+") || textField.getText().contains("×")
                                || textField.getText().contains("÷")) {
                            try {
                                calculate(splittedTextField[0], splittedTextField[1], splittedTextField[2], btnText);
                            } catch (ArrayIndexOutOfBoundsException ignored) {

                            }
                        } else {
                            if (textField.getText().length() == 0) {
                                textField.setText(btnText);
                            } else {
                                if (textField.getText().charAt(textField.getText().length() - 1) == ' ') {
                                    textField.setText(textField.getText() + btnText + " ");
                                } else {
                                    textField.setText(textField.getText() + " " + btnText + " ");
                                }
                            }
                        }
                    }
                } else if (btnText.equals("=")) {
                    String[] splittedTextField = textField.getText().split(" ");
                    if (!textField.getText().isEmpty()) {
                        try {
                            calculate(splittedTextField[0], splittedTextField[1], splittedTextField[2], "");
                        } catch (ArrayIndexOutOfBoundsException ignored) {

                        }
                    }
                } else {
                    textField.setText(textField.getText() + btnText);
                }
            }
        });

        button.addMouseListener(new MouseAdapter() {
            Color buttonBgColor = button.getBackground();

            public void mouseEntered(MouseEvent evt) {
                button.setBackground(Color.DARK_GRAY);
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(buttonBgColor);
            }
        });

        buttons.add(button);
    }

    public void calculate(String number1, String operator, String number2, String currentOperator) {
        try {
            if (currentOperator.isEmpty()) {
                switch (operator) {
                    case "+":
                        textField.setText(String.valueOf(Double.parseDouble(number1) + Double.parseDouble(number2)));
                        break;
                    case "-":
                        textField.setText(String.valueOf(Double.parseDouble(number1) - Double.parseDouble(number2)));
                        break;
                    case "×":
                        textField.setText(String.valueOf(Double.parseDouble(number1) * Double.parseDouble(number2)));
                        break;
                    case "÷":
                        textField.setText(String.valueOf(Double.parseDouble(number1) / Double.parseDouble(number2)));
                        break;
                    default:
                        break;
                }
            } else {
                switch (operator) {
                    case "+":
                        textField.setText(String.valueOf(Double.parseDouble(number1) + Double.parseDouble(number2))
                                + " " + currentOperator + " ");
                        break;
                    case "-":
                        textField.setText(String.valueOf(Double.parseDouble(number1) - Double.parseDouble(number2))
                                + " " + currentOperator + " ");
                        break;
                    case "×":
                        textField.setText(String.valueOf(Double.parseDouble(number1) * Double.parseDouble(number2))
                                + " " + currentOperator + " ");
                        break;
                    case "÷":
                        textField.setText(String.valueOf(Double.parseDouble(number1) / Double.parseDouble(number2))
                                + " " + currentOperator + " ");
                        break;
                    default:
                        break;
                }
            }
        } catch (NumberFormatException ignored) {

        }
    }
}

class ButtonKeyListener implements KeyListener {
    HashMap<String, Component> componentMap;
    JTextField textField;

    public ButtonKeyListener(HashMap<String, Component> componentMap, JTextField textField) {
        this.componentMap = componentMap;
        this.textField = textField;
    }

    public Component getComponentByName(String name) {
        if (componentMap.containsKey(name)) {
            return componentMap.get(name);
        }
        return null;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            JButton button;
            String buttonName = String.valueOf(e.getKeyChar());

            if (e.getKeyCode() == 10) {
                button = ((JButton) getComponentByName("="));
                button.doClick();
            } else if (e.getKeyCode() == 8 || e.getKeyCode() == 110 || e.getKeyCode() == 127) {
                try {
                    if (textField.getText().charAt(textField.getText().length() - 1) == ' ') {
                        String removedLastTwoChars = textField.getText().substring(0, textField.getText().length() - 2);
                        textField.setText(removedLastTwoChars);
                    } else {
                        String removedLastChar = textField.getText().substring(0, textField.getText().length() - 1);
                        textField.setText(removedLastChar);
                    }
                } catch (StringIndexOutOfBoundsException ignored) {

                }
            } else {
                switch (buttonName) {
                    case "*":
                        button = ((JButton) getComponentByName("×"));
                        button.doClick();
                        break;
                    case "/":
                        button = ((JButton) getComponentByName("÷"));
                        button.doClick();
                        break;
                    default:
                        button = ((JButton) getComponentByName(buttonName));
                        button.doClick();
                        break;
                }
            }
        } catch (NullPointerException ignored) {

        }
    }
}