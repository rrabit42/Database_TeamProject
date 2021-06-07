package gui.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.DBConnection;

@SuppressWarnings("serial")
public class Login extends CustomUI {

    private JFrame frame = new JFrame();
    private JPanel backgroundPanel;
    private JTextField txtNickname;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnJoin;
    private JLabel lbLogo, lbSearch;

    private static Connection conn;
    private static PreparedStatement pstmt;
    private static ResultSet rs;
    private static final String SQL = "SELECT * FROM DB2021_User WHERE NICKNAME = ? AND PASSWORD = ? AND DELETE_FG = 'N'";

    public Login() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();

        lbSearch.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "관리자에게 문의하세요.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        });

        txtPassword.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnLogin.doClick();
                }
            }
            public void keyPressed(KeyEvent e) {}
        });

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nickname = txtNickname.getText();
                String password = String.valueOf(txtPassword.getPassword());

                conn = DBConnection.getConnection();

                try {
                    pstmt = conn.prepareStatement(SQL);
                    pstmt.setString(1, nickname);
                    pstmt.setString(2, password);
                    rs = pstmt.executeQuery();

                    if (rs.next()) {
                        // Admin 기능을 구현하기 위해 넣었으나, 구현하지 못함. 추후에 기회가 된다면 구현할 것.
                        if(rs.getString("ADMIN_FG").equals("Y")) {
//                            new gui.admin.Main();
                            frame.dispose();
                        } else {
                            new Main(nickname);
                            frame.dispose();
                        }
                    } else {
                        conn.close();
                        JOptionPane.showMessageDialog(frame, "일치하는 사용자가 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "인증되지 않았습니다.");
                }
            }
        });

        btnJoin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Join();
                frame.dispose();
            }
        });

        frame.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {
                frame.requestFocus();
            }
            public void mouseExited(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseClicked(MouseEvent e) {}
        });

        frame.setSize(426, 779);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void init() {
        backgroundPanel = new JPanel();
        frame.setContentPane(backgroundPanel);
        frame.setTitle("DB2021Team03-영화 정보 프로그램");

        CustomUI custom = new CustomUI(backgroundPanel);
        custom.setPanel();

        lbLogo = custom.setLbImg("lbLogo", 0, 150, 150);
        txtNickname = custom.setTextField("txtNickname", "Nickname", 35, 290, 350, 45);
        txtPassword = custom.setPasswordField("txtPassword", "Password", 35, 345, 350, 45);

        btnLogin = custom.setBtnGreen("btnLogin", "로그인", 35, 425, 350, 40);
        btnJoin = custom.setBtnWhite("btnJoin", "회원가입", 35, 480);

        lbSearch = custom.setLb("lbSearch", "아이디 찾기 ｜ 비밀번호 찾기", 100, 535, 200, 40, "center", 15, "plain");
    }

    public static void main(String[] args) {
        new Login();
    }
}