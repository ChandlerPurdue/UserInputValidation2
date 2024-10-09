package com.example.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInputFrame extends JFrame {
    private JTextField ageField;
    private JTextField emailField;
    private JTextArea resultArea;

    public UserInputFrame() {
        setTitle("User Input Validation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(null);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(10, 10, 80, 25);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(100, 10, 160, 25);
        add(ageField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 40, 80, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(100, 40, 160, 25);
        add(emailField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(10, 80, 100, 25);
        add(submitButton);

        resultArea = new JTextArea();
        resultArea.setBounds(10, 120, 360, 120);
        resultArea.setEditable(false);
        add(resultArea);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateInputs();
            }
        });
    }

    // Improved method for validating inputs
    private void validateInputs() {
        StringBuilder result = new StringBuilder();
        
        // Sanitize and validate age input
        String sanitizedAge = sanitizeInput(ageField.getText());
        if (!isValidAge(sanitizedAge)) {
            result.append("Invalid age. Please enter a number between 5 and 18.\n");
        } else {
            result.append("Age is valid.\n");
        }

        // Sanitize and validate email input
        String sanitizedEmail = sanitizeInput(emailField.getText());
        if (!isValidEmail(sanitizedEmail)) {
            result.append("Invalid email format.\n");
        } else {
            result.append("Email is valid.\n");
        }

        resultArea.setText(result.toString());
    }

    // Method to validate age
    private boolean isValidAge(String ageInput) {
        try {
            int age = Integer.parseInt(ageInput);
            return age >= 5 && age <= 18;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Method to validate email using regex
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    // Method to sanitize input by removing any harmful characters
    private String sanitizeInput(String input) {
        return input.replaceAll("[^A-Za-z0-9@.]", ""); // Allow only alphanumeric characters, '@' and '.'
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserInputFrame().setVisible(true);
            }
        });
    }
}

