import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class ProiectBD {
    private JPanel Main;
    private JTextField txtClient;
    private JTextField txtProd;
    private JTextField txtMag;
    private JButton button;
    private JButton modificaButton;
    private JButton stergeButton;
    private JButton COMENZIButton;
    private JLabel NRCOMANDA;
    private JTextField txtData;
    private JTextField txtCom;
    private JTextField txtPret;

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("ProiectBD");
        frame.setContentPane(new ProiectBD().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,800);
        frame.setLocationRelativeTo(null);
    }

    Connection con;
    PreparedStatement pst;

    public ProiectBD() throws SQLException {
        String url = "jdbc:oracle:thin:@bd-dc.cs.tuiasi.ro:1539:orcl";
        String user = "bd130";
        String password = "bd130";


        Connection con = DriverManager.getConnection(url, user, password);
        System.out.println("Succes");


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String NrComanda, Client, Magazin, Produse, Data, Pret;

                Client = txtMag.getText();
                Magazin = txtClient.getText();
                Produse = txtProd.getText();
                Data = txtData.getText();
                Pret = txtPret.getText();

                try {
                    pst = con.prepareStatement("insert into COMENZI(MAGAZIN_ID,CLIENT_ID,PRODUS_ID,COMANDA_DATE,PRET)values(?,?,?,?,?)");
                    pst.setString(1, Client);
                    pst.setString(2, Magazin);
                    pst.setString(3, Produse);
                    pst.setString(4, Data);
                    pst.setString(5, Pret);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Adaugare cu succes!");


                    txtMag.setText("");
                    txtClient.setText("");
                    txtProd.setText("");
                    txtData.setText("");
                    txtPret.setText("");


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String NrComanda, Client, Magazin, Produse, Data, Pret;

                Client = txtClient.getText();
                Magazin = txtMag.getText();
                Produse = txtProd.getText();
                Data = txtData.getText();
                Pret = txtPret.getText();
                NrComanda = txtCom.getText();

                try {
                    pst = con.prepareStatement("update COMENZI set MAGAZIN_ID = ?, PRODUS_ID = ?, COMANDA_DATE = ?, PRET = ?where COMANDA_ID = ?");

                    pst.setInt(1, Integer.parseInt(Magazin));
                    pst.setInt(2, Integer.parseInt(Produse));
                    pst.setString(3, Data);
                    pst.setInt(4, Integer.parseInt(Pret));
                    pst.setInt(5,Integer.parseInt(NrComanda));
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Modificare cu succes!");


                    txtMag.setText("");
                    txtClient.setText("");
                    txtProd.setText("");
                    txtData.setText("");
                    txtPret.setText("");

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


        COMENZIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String cv="SELECT COMANDA_ID, MAGAZIN_ID, CLIENT_ID, PRODUS_ID, COMANDA_DATE, PRET from COMENZI ";
                    Statement st= con.createStatement();

                    ResultSet res = st.executeQuery(cv);

                    String[] columns = {"COMANDA_ID", "MAGAZIN_ID", "CLIENT_ID", "PRODUS_ID", "COMANDA_DATE","PRET"};
                    String[][] data4 = new String[30][30];

                    int l = 0;
                    while (res.next()) {
                        int cmd = res.getInt("COMANDA_ID");
                        int mgz = res.getInt("MAGAZIN_ID");
                        int cliid = res.getInt("CLIENT_ID");
                        int prid = res.getInt("PRODUS_ID");
                        String data = res.getString("COMANDA_DATE");
                        int pret= res.getInt("PRET");
                        data4[l][0] = cmd+" ";
                        data4[l][1] = mgz+" ";
                        data4[l][2] = cliid+" ";
                        data4[l][3] = prid + " ";
                        data4[l][4] = data;
                        data4[l][5] = pret+ " ";
                        l++;
                    }

                    DefaultTableModel model = new DefaultTableModel(data4, columns);
                    JTable table = new JTable(model);
                    table.setShowGrid(true);
                    table.setShowVerticalLines(true);
                    JScrollPane pane = new JScrollPane(table);
                    JFrame f = new JFrame("COMENZI");
                    JPanel panel = new JPanel();
                    panel.add(pane);
                    f.add(panel);
                    f.setSize(500, 500);
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);






                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });


        stergeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String bid;

                bid = txtCom.getText();

                try {
                    pst = con.prepareStatement("delete from COMENZI where COMANDA_ID = ?");

                    pst.setString(1,bid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Stergere cu succes!");

                    txtMag.setText("");
                    txtClient.setText("");
                    txtProd.setText("");
                    txtData.setText("");
                    txtPret.setText("");
                    txtCom.setText(" ");


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
    }
}