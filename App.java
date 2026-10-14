import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username; 
    }
    
    public String getPassword() {
        return password; 
    }
}

public class App {
    private static ArrayList<User> userList = new ArrayList<>();

    public static void main(String[] args) {
        userList.add(new User("abangdat", "dat1318"));

        String appName = "Auth System - CardLayout";
        int width = 450;
        int height = 320;

        JFrame frame = new JFrame(appName);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); 

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        JPanel loginPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField loginUserField = new JTextField();
        JPasswordField loginPassField = new JPasswordField();
        JButton loginBtn = new JButton("Login");
        JButton goToRegisterBtn = new JButton("Ke Register");

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(loginUserField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(loginPassField);
        loginPanel.add(loginBtn);
        loginPanel.add(goToRegisterBtn);

        JPanel registerPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        registerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField regUserField = new JTextField();
        JPasswordField regPassField = new JPasswordField();
        JButton regBtn = new JButton("Register");
        JButton goToLoginBtn = new JButton("Ke Login");

        registerPanel.add(new JLabel("Reg Username:"));
        registerPanel.add(regUserField);
        registerPanel.add(new JLabel("Reg Password:"));
        registerPanel.add(regPassField);
        registerPanel.add(regBtn);
        registerPanel.add(goToLoginBtn);

        JPanel helloPanel = new JPanel(new BorderLayout(10, 10));
        helloPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel welcomeLabel = new JLabel("Hello, User!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        JButton logoutBtn = new JButton("Logout");

        helloPanel.add(welcomeLabel, BorderLayout.CENTER);
        helloPanel.add(logoutBtn, BorderLayout.SOUTH);

        mainPanel.add(loginPanel, "Login");
        mainPanel.add(registerPanel, "Register");
        mainPanel.add(helloPanel, "Hello");

        goToRegisterBtn.addActionListener(e -> cardLayout.show(mainPanel, "Register"));
        goToLoginBtn.addActionListener(e -> cardLayout.show(mainPanel, "Login"));

        regBtn.addActionListener(e -> {
            String user = regUserField.getText().trim();
            String pass = new String(regPassField.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Username & Password nggak boleh kosong!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            userList.add(new User(user, pass));
            JOptionPane.showMessageDialog(frame, "Register Berhasil! Silakan Login.", "Info", JOptionPane.INFORMATION_MESSAGE);

            regUserField.setText("");
            regPassField.setText("");
            cardLayout.show(mainPanel, "Login");
        });

        loginBtn.addActionListener(e -> {
            String user = loginUserField.getText().trim();
            String pass = new String(loginPassField.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Username & Password wajib diisi!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean userFound = false;
            boolean passwordMatch = false;

            for (User u : userList) {
                if (u.getUsername().equals(user)) {
                    userFound = true;
                    if (u.getPassword().equals(pass)) {
                        passwordMatch = true;
                    }
                    break;
                }
            }

            if (userFound && passwordMatch) {
                welcomeLabel.setText("Hello, " + user + "!");
                loginUserField.setText("");
                loginPassField.setText("");
                cardLayout.show(mainPanel, "Hello"); 
            } else if (!userFound) {
                JOptionPane.showMessageDialog(frame, "Username tidak terdafar!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Password salah!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        logoutBtn.addActionListener(e -> cardLayout.show(mainPanel, "Login"));

        frame.add(mainPanel);
        cardLayout.show(mainPanel, "Login");
        frame.setVisible(true);
    }
}