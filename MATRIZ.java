import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MATRIZ extends JFrame
{
	private JButton btnVerificar = new JButton();
	private JLabel jLabel1 = new JLabel();
	private JTextField txfSentenca = new JTextField();
	private String[] alfabeto = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", " ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "_", ";", ",", "(", ")", "[", "]", "{", "}", ".", "+", "*", "/", "!", "\"", "#", "$", "%", "&", "-", ":", "<", "=", ">", "?", "@", "\\", "^", "`", "|", "~"};
	private String[] estados = {"Inicial", "ID", "UEV", "TMF", "DEL", "COML", "comp1", "OPAR", "OPAR2", "comp2", "COMB", "NUM", "nump1", "nump2", "NUM2", "OPRL", "OPRL2", "OPAR3", "OPL", "OPL2", "oplp", "cadcp1", "cadcp2", "CADC"};
	private String[] estadosReconhecedores = {"OPL2", "CADC", "NUM", "UEV", "NUM2", "OPRL2", "DEL", "OPL", "TMF", "COML", "OPAR", "ID", "OPAR2", "COMB", "OPRL", "OPAR3"};
	private String[][] matrizTransicao = new String[94][24];
	private String estadoInicial = "Inicial";
	private String estadoAtual;

	private final int TRANSICAO_ENCONTRADA  = 0;
	private final int ENTRADA_INEXISTENTE   = 1;
	private final int TRANSICAO_INEXISTENTE = 2;

	public MATRIZ()
	{
		try
		{
			iniciaPrograma();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void iniciaPrograma() throws Exception
	{
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(400, 155));
		this.setTitle("Programa reconhecedor");
		this.setResizable(false);
		btnVerificar.setText("Verificar");
		btnVerificar.setBounds(new Rectangle(145, 80, 105, 35));
		btnVerificar.setMnemonic('V');
		btnVerificar.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				btnVerificar_actionPerformed(e);
				}
			});
		jLabel1.setText("Senten�a");
		jLabel1.setBounds(new Rectangle(15, 20, 195, 15));
		txfSentenca.setBounds(new Rectangle(15, 35, 365, 20));
		this.getContentPane().add(txfSentenca, null);
		this.getContentPane().add(jLabel1, null);
		this.getContentPane().add(btnVerificar, null);
		inicializaAutomato();
	}

	private void inicializaAutomato()
	{
		for (int i=0; i<alfabeto.length; i++)
			for (int j=0; j<estados.length; j++)
				matrizTransicao[i][j] = null;
		matrizTransicao[0][0] = "ID";  //<Inicial> ::= a<ID>
		matrizTransicao[0][1] = "ID";  //<ID> ::= a<ID>
		matrizTransicao[0][6] = "comp1";  //<comp1> ::= a<comp1>
		matrizTransicao[0][9] = "comp1";  //<comp2> ::= a<comp1>
		matrizTransicao[0][12] = "UEV";  //<nump1> ::= a<UEV>
		matrizTransicao[0][13] = "TMF";  //<nump2> ::= a<TMF>
		matrizTransicao[0][21] = "cadcp1";  //<cadcp1> ::= a<cadcp1>
		matrizTransicao[0][22] = "cadcp1";  //<cadcp2> ::= a<cadcp1>
		matrizTransicao[1][0] = "ID";  //<Inicial> ::= b<ID>
		matrizTransicao[1][1] = "ID";  //<ID> ::= b<ID>
		matrizTransicao[1][6] = "comp1";  //<comp1> ::= b<comp1>
		matrizTransicao[1][9] = "comp1";  //<comp2> ::= b<comp1>
		matrizTransicao[1][12] = "UEV";  //<nump1> ::= b<UEV>
		matrizTransicao[1][13] = "TMF";  //<nump2> ::= b<TMF>
		matrizTransicao[1][21] = "cadcp1";  //<cadcp1> ::= b<cadcp1>
		matrizTransicao[1][22] = "cadcp1";  //<cadcp2> ::= b<cadcp1>
		matrizTransicao[2][0] = "ID";  //<Inicial> ::= c<ID>
		matrizTransicao[2][1] = "ID";  //<ID> ::= c<ID>
		matrizTransicao[2][6] = "comp1";  //<comp1> ::= c<comp1>
		matrizTransicao[2][9] = "comp1";  //<comp2> ::= c<comp1>
		matrizTransicao[2][12] = "UEV";  //<nump1> ::= c<UEV>
		matrizTransicao[2][13] = "TMF";  //<nump2> ::= c<TMF>
		matrizTransicao[2][21] = "cadcp1";  //<cadcp1> ::= c<cadcp1>
		matrizTransicao[2][22] = "cadcp1";  //<cadcp2> ::= c<cadcp1>
		matrizTransicao[3][0] = "ID";  //<Inicial> ::= d<ID>
		matrizTransicao[3][1] = "ID";  //<ID> ::= d<ID>
		matrizTransicao[3][6] = "comp1";  //<comp1> ::= d<comp1>
		matrizTransicao[3][9] = "comp1";  //<comp2> ::= d<comp1>
		matrizTransicao[3][12] = "UEV";  //<nump1> ::= d<UEV>
		matrizTransicao[3][13] = "TMF";  //<nump2> ::= d<TMF>
		matrizTransicao[3][21] = "cadcp1";  //<cadcp1> ::= d<cadcp1>
		matrizTransicao[3][22] = "cadcp1";  //<cadcp2> ::= d<cadcp1>
		matrizTransicao[4][0] = "ID";  //<Inicial> ::= e<ID>
		matrizTransicao[4][1] = "ID";  //<ID> ::= e<ID>
		matrizTransicao[4][6] = "comp1";  //<comp1> ::= e<comp1>
		matrizTransicao[4][9] = "comp1";  //<comp2> ::= e<comp1>
		matrizTransicao[4][12] = "UEV";  //<nump1> ::= e<UEV>
		matrizTransicao[4][13] = "TMF";  //<nump2> ::= e<TMF>
		matrizTransicao[4][21] = "cadcp1";  //<cadcp1> ::= e<cadcp1>
		matrizTransicao[4][22] = "cadcp1";  //<cadcp2> ::= e<cadcp1>
		matrizTransicao[5][0] = "ID";  //<Inicial> ::= f<ID>
		matrizTransicao[5][1] = "ID";  //<ID> ::= f<ID>
		matrizTransicao[5][6] = "comp1";  //<comp1> ::= f<comp1>
		matrizTransicao[5][9] = "comp1";  //<comp2> ::= f<comp1>
		matrizTransicao[5][12] = "UEV";  //<nump1> ::= f<UEV>
		matrizTransicao[5][13] = "TMF";  //<nump2> ::= f<TMF>
		matrizTransicao[5][21] = "cadcp1";  //<cadcp1> ::= f<cadcp1>
		matrizTransicao[5][22] = "cadcp1";  //<cadcp2> ::= f<cadcp1>
		matrizTransicao[6][0] = "ID";  //<Inicial> ::= g<ID>
		matrizTransicao[6][1] = "ID";  //<ID> ::= g<ID>
		matrizTransicao[6][6] = "comp1";  //<comp1> ::= g<comp1>
		matrizTransicao[6][9] = "comp1";  //<comp2> ::= g<comp1>
		matrizTransicao[6][12] = "UEV";  //<nump1> ::= g<UEV>
		matrizTransicao[6][13] = "TMF";  //<nump2> ::= g<TMF>
		matrizTransicao[6][21] = "cadcp1";  //<cadcp1> ::= g<cadcp1>
		matrizTransicao[6][22] = "cadcp1";  //<cadcp2> ::= g<cadcp1>
		matrizTransicao[7][0] = "ID";  //<Inicial> ::= h<ID>
		matrizTransicao[7][1] = "ID";  //<ID> ::= h<ID>
		matrizTransicao[7][6] = "comp1";  //<comp1> ::= h<comp1>
		matrizTransicao[7][9] = "comp1";  //<comp2> ::= h<comp1>
		matrizTransicao[7][12] = "UEV";  //<nump1> ::= h<UEV>
		matrizTransicao[7][13] = "TMF";  //<nump2> ::= h<TMF>
		matrizTransicao[7][21] = "cadcp1";  //<cadcp1> ::= h<cadcp1>
		matrizTransicao[7][22] = "cadcp1";  //<cadcp2> ::= h<cadcp1>
		matrizTransicao[8][0] = "ID";  //<Inicial> ::= i<ID>
		matrizTransicao[8][1] = "ID";  //<ID> ::= i<ID>
		matrizTransicao[8][6] = "comp1";  //<comp1> ::= i<comp1>
		matrizTransicao[8][9] = "comp1";  //<comp2> ::= i<comp1>
		matrizTransicao[8][12] = "UEV";  //<nump1> ::= i<UEV>
		matrizTransicao[8][13] = "TMF";  //<nump2> ::= i<TMF>
		matrizTransicao[8][21] = "cadcp1";  //<cadcp1> ::= i<cadcp1>
		matrizTransicao[8][22] = "cadcp1";  //<cadcp2> ::= i<cadcp1>
		matrizTransicao[9][0] = "ID";  //<Inicial> ::= j<ID>
		matrizTransicao[9][1] = "ID";  //<ID> ::= j<ID>
		matrizTransicao[9][6] = "comp1";  //<comp1> ::= j<comp1>
		matrizTransicao[9][9] = "comp1";  //<comp2> ::= j<comp1>
		matrizTransicao[9][12] = "UEV";  //<nump1> ::= j<UEV>
		matrizTransicao[9][13] = "TMF";  //<nump2> ::= j<TMF>
		matrizTransicao[9][21] = "cadcp1";  //<cadcp1> ::= j<cadcp1>
		matrizTransicao[9][22] = "cadcp1";  //<cadcp2> ::= j<cadcp1>
		matrizTransicao[10][0] = "ID";  //<Inicial> ::= k<ID>
		matrizTransicao[10][1] = "ID";  //<ID> ::= k<ID>
		matrizTransicao[10][6] = "comp1";  //<comp1> ::= k<comp1>
		matrizTransicao[10][9] = "comp1";  //<comp2> ::= k<comp1>
		matrizTransicao[10][12] = "UEV";  //<nump1> ::= k<UEV>
		matrizTransicao[10][13] = "TMF";  //<nump2> ::= k<TMF>
		matrizTransicao[10][21] = "cadcp1";  //<cadcp1> ::= k<cadcp1>
		matrizTransicao[10][22] = "cadcp1";  //<cadcp2> ::= k<cadcp1>
		matrizTransicao[11][0] = "ID";  //<Inicial> ::= l<ID>
		matrizTransicao[11][1] = "ID";  //<ID> ::= l<ID>
		matrizTransicao[11][6] = "comp1";  //<comp1> ::= l<comp1>
		matrizTransicao[11][9] = "comp1";  //<comp2> ::= l<comp1>
		matrizTransicao[11][12] = "UEV";  //<nump1> ::= l<UEV>
		matrizTransicao[11][13] = "TMF";  //<nump2> ::= l<TMF>
		matrizTransicao[11][21] = "cadcp1";  //<cadcp1> ::= l<cadcp1>
		matrizTransicao[11][22] = "cadcp1";  //<cadcp2> ::= l<cadcp1>
		matrizTransicao[12][0] = "ID";  //<Inicial> ::= m<ID>
		matrizTransicao[12][1] = "ID";  //<ID> ::= m<ID>
		matrizTransicao[12][6] = "comp1";  //<comp1> ::= m<comp1>
		matrizTransicao[12][9] = "comp1";  //<comp2> ::= m<comp1>
		matrizTransicao[12][12] = "UEV";  //<nump1> ::= m<UEV>
		matrizTransicao[12][13] = "TMF";  //<nump2> ::= m<TMF>
		matrizTransicao[12][21] = "cadcp1";  //<cadcp1> ::= m<cadcp1>
		matrizTransicao[12][22] = "cadcp1";  //<cadcp2> ::= m<cadcp1>
		matrizTransicao[13][0] = "ID";  //<Inicial> ::= n<ID>
		matrizTransicao[13][1] = "ID";  //<ID> ::= n<ID>
		matrizTransicao[13][6] = "comp1";  //<comp1> ::= n<comp1>
		matrizTransicao[13][9] = "comp1";  //<comp2> ::= n<comp1>
		matrizTransicao[13][12] = "UEV";  //<nump1> ::= n<UEV>
		matrizTransicao[13][13] = "TMF";  //<nump2> ::= n<TMF>
		matrizTransicao[13][21] = "cadcp1";  //<cadcp1> ::= n<cadcp1>
		matrizTransicao[13][22] = "cadcp1";  //<cadcp2> ::= n<cadcp1>
		matrizTransicao[14][0] = "ID";  //<Inicial> ::= o<ID>
		matrizTransicao[14][1] = "ID";  //<ID> ::= o<ID>
		matrizTransicao[14][6] = "comp1";  //<comp1> ::= o<comp1>
		matrizTransicao[14][9] = "comp1";  //<comp2> ::= o<comp1>
		matrizTransicao[14][12] = "UEV";  //<nump1> ::= o<UEV>
		matrizTransicao[14][13] = "TMF";  //<nump2> ::= o<TMF>
		matrizTransicao[14][21] = "cadcp1";  //<cadcp1> ::= o<cadcp1>
		matrizTransicao[14][22] = "cadcp1";  //<cadcp2> ::= o<cadcp1>
		matrizTransicao[15][0] = "ID";  //<Inicial> ::= p<ID>
		matrizTransicao[15][1] = "ID";  //<ID> ::= p<ID>
		matrizTransicao[15][6] = "comp1";  //<comp1> ::= p<comp1>
		matrizTransicao[15][9] = "comp1";  //<comp2> ::= p<comp1>
		matrizTransicao[15][12] = "UEV";  //<nump1> ::= p<UEV>
		matrizTransicao[15][13] = "TMF";  //<nump2> ::= p<TMF>
		matrizTransicao[15][21] = "cadcp1";  //<cadcp1> ::= p<cadcp1>
		matrizTransicao[15][22] = "cadcp1";  //<cadcp2> ::= p<cadcp1>
		matrizTransicao[16][0] = "ID";  //<Inicial> ::= q<ID>
		matrizTransicao[16][1] = "ID";  //<ID> ::= q<ID>
		matrizTransicao[16][6] = "comp1";  //<comp1> ::= q<comp1>
		matrizTransicao[16][9] = "comp1";  //<comp2> ::= q<comp1>
		matrizTransicao[16][12] = "UEV";  //<nump1> ::= q<UEV>
		matrizTransicao[16][13] = "TMF";  //<nump2> ::= q<TMF>
		matrizTransicao[16][21] = "cadcp1";  //<cadcp1> ::= q<cadcp1>
		matrizTransicao[16][22] = "cadcp1";  //<cadcp2> ::= q<cadcp1>
		matrizTransicao[17][0] = "ID";  //<Inicial> ::= r<ID>
		matrizTransicao[17][1] = "ID";  //<ID> ::= r<ID>
		matrizTransicao[17][6] = "comp1";  //<comp1> ::= r<comp1>
		matrizTransicao[17][9] = "comp1";  //<comp2> ::= r<comp1>
		matrizTransicao[17][12] = "UEV";  //<nump1> ::= r<UEV>
		matrizTransicao[17][13] = "TMF";  //<nump2> ::= r<TMF>
		matrizTransicao[17][21] = "cadcp1";  //<cadcp1> ::= r<cadcp1>
		matrizTransicao[17][22] = "cadcp1";  //<cadcp2> ::= r<cadcp1>
		matrizTransicao[18][0] = "ID";  //<Inicial> ::= s<ID>
		matrizTransicao[18][1] = "ID";  //<ID> ::= s<ID>
		matrizTransicao[18][6] = "comp1";  //<comp1> ::= s<comp1>
		matrizTransicao[18][9] = "comp1";  //<comp2> ::= s<comp1>
		matrizTransicao[18][12] = "UEV";  //<nump1> ::= s<UEV>
		matrizTransicao[18][13] = "TMF";  //<nump2> ::= s<TMF>
		matrizTransicao[18][21] = "cadcp1";  //<cadcp1> ::= s<cadcp1>
		matrizTransicao[18][22] = "cadcp1";  //<cadcp2> ::= s<cadcp1>
		matrizTransicao[19][0] = "ID";  //<Inicial> ::= t<ID>
		matrizTransicao[19][1] = "ID";  //<ID> ::= t<ID>
		matrizTransicao[19][6] = "comp1";  //<comp1> ::= t<comp1>
		matrizTransicao[19][9] = "comp1";  //<comp2> ::= t<comp1>
		matrizTransicao[19][12] = "UEV";  //<nump1> ::= t<UEV>
		matrizTransicao[19][13] = "TMF";  //<nump2> ::= t<TMF>
		matrizTransicao[19][21] = "cadcp1";  //<cadcp1> ::= t<cadcp1>
		matrizTransicao[19][22] = "cadcp1";  //<cadcp2> ::= t<cadcp1>
		matrizTransicao[20][0] = "ID";  //<Inicial> ::= u<ID>
		matrizTransicao[20][1] = "ID";  //<ID> ::= u<ID>
		matrizTransicao[20][6] = "comp1";  //<comp1> ::= u<comp1>
		matrizTransicao[20][9] = "comp1";  //<comp2> ::= u<comp1>
		matrizTransicao[20][12] = "UEV";  //<nump1> ::= u<UEV>
		matrizTransicao[20][13] = "TMF";  //<nump2> ::= u<TMF>
		matrizTransicao[20][21] = "cadcp1";  //<cadcp1> ::= u<cadcp1>
		matrizTransicao[20][22] = "cadcp1";  //<cadcp2> ::= u<cadcp1>
		matrizTransicao[21][0] = "ID";  //<Inicial> ::= v<ID>
		matrizTransicao[21][1] = "ID";  //<ID> ::= v<ID>
		matrizTransicao[21][6] = "comp1";  //<comp1> ::= v<comp1>
		matrizTransicao[21][9] = "comp1";  //<comp2> ::= v<comp1>
		matrizTransicao[21][12] = "UEV";  //<nump1> ::= v<UEV>
		matrizTransicao[21][13] = "TMF";  //<nump2> ::= v<TMF>
		matrizTransicao[21][21] = "cadcp1";  //<cadcp1> ::= v<cadcp1>
		matrizTransicao[21][22] = "cadcp1";  //<cadcp2> ::= v<cadcp1>
		matrizTransicao[22][0] = "ID";  //<Inicial> ::= w<ID>
		matrizTransicao[22][1] = "ID";  //<ID> ::= w<ID>
		matrizTransicao[22][6] = "comp1";  //<comp1> ::= w<comp1>
		matrizTransicao[22][9] = "comp1";  //<comp2> ::= w<comp1>
		matrizTransicao[22][12] = "UEV";  //<nump1> ::= w<UEV>
		matrizTransicao[22][13] = "TMF";  //<nump2> ::= w<TMF>
		matrizTransicao[22][21] = "cadcp1";  //<cadcp1> ::= w<cadcp1>
		matrizTransicao[22][22] = "cadcp1";  //<cadcp2> ::= w<cadcp1>
		matrizTransicao[23][0] = "ID";  //<Inicial> ::= x<ID>
		matrizTransicao[23][1] = "ID";  //<ID> ::= x<ID>
		matrizTransicao[23][6] = "comp1";  //<comp1> ::= x<comp1>
		matrizTransicao[23][9] = "comp1";  //<comp2> ::= x<comp1>
		matrizTransicao[23][12] = "UEV";  //<nump1> ::= x<UEV>
		matrizTransicao[23][13] = "TMF";  //<nump2> ::= x<TMF>
		matrizTransicao[23][21] = "cadcp1";  //<cadcp1> ::= x<cadcp1>
		matrizTransicao[23][22] = "cadcp1";  //<cadcp2> ::= x<cadcp1>
		matrizTransicao[24][0] = "ID";  //<Inicial> ::= y<ID>
		matrizTransicao[24][1] = "ID";  //<ID> ::= y<ID>
		matrizTransicao[24][6] = "comp1";  //<comp1> ::= y<comp1>
		matrizTransicao[24][9] = "comp1";  //<comp2> ::= y<comp1>
		matrizTransicao[24][12] = "UEV";  //<nump1> ::= y<UEV>
		matrizTransicao[24][13] = "TMF";  //<nump2> ::= y<TMF>
		matrizTransicao[24][21] = "cadcp1";  //<cadcp1> ::= y<cadcp1>
		matrizTransicao[24][22] = "cadcp1";  //<cadcp2> ::= y<cadcp1>
		matrizTransicao[25][0] = "ID";  //<Inicial> ::= z<ID>
		matrizTransicao[25][1] = "ID";  //<ID> ::= z<ID>
		matrizTransicao[25][6] = "comp1";  //<comp1> ::= z<comp1>
		matrizTransicao[25][9] = "comp1";  //<comp2> ::= z<comp1>
		matrizTransicao[25][12] = "UEV";  //<nump1> ::= z<UEV>
		matrizTransicao[25][13] = "TMF";  //<nump2> ::= z<TMF>
		matrizTransicao[25][21] = "cadcp1";  //<cadcp1> ::= z<cadcp1>
		matrizTransicao[25][22] = "cadcp1";  //<cadcp2> ::= z<cadcp1>
		matrizTransicao[26][0] = "ID";  //<Inicial> ::= A<ID>
		matrizTransicao[26][1] = "ID";  //<ID> ::= A<ID>
		matrizTransicao[26][6] = "comp1";  //<comp1> ::= A<comp1>
		matrizTransicao[26][9] = "comp1";  //<comp2> ::= A<comp1>
		matrizTransicao[26][12] = "UEV";  //<nump1> ::= A<UEV>
		matrizTransicao[26][13] = "TMF";  //<nump2> ::= A<TMF>
		matrizTransicao[26][21] = "cadcp1";  //<cadcp1> ::= A<cadcp1>
		matrizTransicao[26][22] = "cadcp1";  //<cadcp2> ::= A<cadcp1>
		matrizTransicao[27][0] = "ID";  //<Inicial> ::= B<ID>
		matrizTransicao[27][1] = "ID";  //<ID> ::= B<ID>
		matrizTransicao[27][6] = "comp1";  //<comp1> ::= B<comp1>
		matrizTransicao[27][9] = "comp1";  //<comp2> ::= B<comp1>
		matrizTransicao[27][12] = "UEV";  //<nump1> ::= B<UEV>
		matrizTransicao[27][13] = "TMF";  //<nump2> ::= B<TMF>
		matrizTransicao[27][21] = "cadcp1";  //<cadcp1> ::= B<cadcp1>
		matrizTransicao[27][22] = "cadcp1";  //<cadcp2> ::= B<cadcp1>
		matrizTransicao[28][0] = "ID";  //<Inicial> ::= C<ID>
		matrizTransicao[28][1] = "ID";  //<ID> ::= C<ID>
		matrizTransicao[28][6] = "comp1";  //<comp1> ::= C<comp1>
		matrizTransicao[28][9] = "comp1";  //<comp2> ::= C<comp1>
		matrizTransicao[28][12] = "UEV";  //<nump1> ::= C<UEV>
		matrizTransicao[28][13] = "TMF";  //<nump2> ::= C<TMF>
		matrizTransicao[28][21] = "cadcp1";  //<cadcp1> ::= C<cadcp1>
		matrizTransicao[28][22] = "cadcp1";  //<cadcp2> ::= C<cadcp1>
		matrizTransicao[29][0] = "ID";  //<Inicial> ::= D<ID>
		matrizTransicao[29][1] = "ID";  //<ID> ::= D<ID>
		matrizTransicao[29][6] = "comp1";  //<comp1> ::= D<comp1>
		matrizTransicao[29][9] = "comp1";  //<comp2> ::= D<comp1>
		matrizTransicao[29][12] = "UEV";  //<nump1> ::= D<UEV>
		matrizTransicao[29][13] = "TMF";  //<nump2> ::= D<TMF>
		matrizTransicao[29][21] = "cadcp1";  //<cadcp1> ::= D<cadcp1>
		matrizTransicao[29][22] = "cadcp1";  //<cadcp2> ::= D<cadcp1>
		matrizTransicao[30][0] = "ID";  //<Inicial> ::= E<ID>
		matrizTransicao[30][1] = "ID";  //<ID> ::= E<ID>
		matrizTransicao[30][6] = "comp1";  //<comp1> ::= E<comp1>
		matrizTransicao[30][9] = "comp1";  //<comp2> ::= E<comp1>
		matrizTransicao[30][12] = "UEV";  //<nump1> ::= E<UEV>
		matrizTransicao[30][13] = "TMF";  //<nump2> ::= E<TMF>
		matrizTransicao[30][21] = "cadcp1";  //<cadcp1> ::= E<cadcp1>
		matrizTransicao[30][22] = "cadcp1";  //<cadcp2> ::= E<cadcp1>
		matrizTransicao[31][0] = "ID";  //<Inicial> ::= F<ID>
		matrizTransicao[31][1] = "ID";  //<ID> ::= F<ID>
		matrizTransicao[31][6] = "comp1";  //<comp1> ::= F<comp1>
		matrizTransicao[31][9] = "comp1";  //<comp2> ::= F<comp1>
		matrizTransicao[31][12] = "UEV";  //<nump1> ::= F<UEV>
		matrizTransicao[31][13] = "TMF";  //<nump2> ::= F<TMF>
		matrizTransicao[31][21] = "cadcp1";  //<cadcp1> ::= F<cadcp1>
		matrizTransicao[31][22] = "cadcp1";  //<cadcp2> ::= F<cadcp1>
		matrizTransicao[32][0] = "ID";  //<Inicial> ::= G<ID>
		matrizTransicao[32][1] = "ID";  //<ID> ::= G<ID>
		matrizTransicao[32][6] = "comp1";  //<comp1> ::= G<comp1>
		matrizTransicao[32][9] = "comp1";  //<comp2> ::= G<comp1>
		matrizTransicao[32][12] = "UEV";  //<nump1> ::= G<UEV>
		matrizTransicao[32][13] = "TMF";  //<nump2> ::= G<TMF>
		matrizTransicao[32][21] = "cadcp1";  //<cadcp1> ::= G<cadcp1>
		matrizTransicao[32][22] = "cadcp1";  //<cadcp2> ::= G<cadcp1>
		matrizTransicao[33][0] = "ID";  //<Inicial> ::= H<ID>
		matrizTransicao[33][1] = "ID";  //<ID> ::= H<ID>
		matrizTransicao[33][6] = "comp1";  //<comp1> ::= H<comp1>
		matrizTransicao[33][9] = "comp1";  //<comp2> ::= H<comp1>
		matrizTransicao[33][12] = "UEV";  //<nump1> ::= H<UEV>
		matrizTransicao[33][13] = "TMF";  //<nump2> ::= H<TMF>
		matrizTransicao[33][21] = "cadcp1";  //<cadcp1> ::= H<cadcp1>
		matrizTransicao[33][22] = "cadcp1";  //<cadcp2> ::= H<cadcp1>
		matrizTransicao[34][0] = "ID";  //<Inicial> ::= I<ID>
		matrizTransicao[34][1] = "ID";  //<ID> ::= I<ID>
		matrizTransicao[34][6] = "comp1";  //<comp1> ::= I<comp1>
		matrizTransicao[34][9] = "comp1";  //<comp2> ::= I<comp1>
		matrizTransicao[34][12] = "UEV";  //<nump1> ::= I<UEV>
		matrizTransicao[34][13] = "TMF";  //<nump2> ::= I<TMF>
		matrizTransicao[34][21] = "cadcp1";  //<cadcp1> ::= I<cadcp1>
		matrizTransicao[34][22] = "cadcp1";  //<cadcp2> ::= I<cadcp1>
		matrizTransicao[35][0] = "ID";  //<Inicial> ::= J<ID>
		matrizTransicao[35][1] = "ID";  //<ID> ::= J<ID>
		matrizTransicao[35][6] = "comp1";  //<comp1> ::= J<comp1>
		matrizTransicao[35][9] = "comp1";  //<comp2> ::= J<comp1>
		matrizTransicao[35][12] = "UEV";  //<nump1> ::= J<UEV>
		matrizTransicao[35][13] = "TMF";  //<nump2> ::= J<TMF>
		matrizTransicao[35][21] = "cadcp1";  //<cadcp1> ::= J<cadcp1>
		matrizTransicao[35][22] = "cadcp1";  //<cadcp2> ::= J<cadcp1>
		matrizTransicao[36][0] = "ID";  //<Inicial> ::= K<ID>
		matrizTransicao[36][1] = "ID";  //<ID> ::= K<ID>
		matrizTransicao[36][6] = "comp1";  //<comp1> ::= K<comp1>
		matrizTransicao[36][9] = "comp1";  //<comp2> ::= K<comp1>
		matrizTransicao[36][12] = "UEV";  //<nump1> ::= K<UEV>
		matrizTransicao[36][13] = "TMF";  //<nump2> ::= K<TMF>
		matrizTransicao[36][21] = "cadcp1";  //<cadcp1> ::= K<cadcp1>
		matrizTransicao[36][22] = "cadcp1";  //<cadcp2> ::= K<cadcp1>
		matrizTransicao[37][0] = "ID";  //<Inicial> ::= L<ID>
		matrizTransicao[37][1] = "ID";  //<ID> ::= L<ID>
		matrizTransicao[37][6] = "comp1";  //<comp1> ::= L<comp1>
		matrizTransicao[37][9] = "comp1";  //<comp2> ::= L<comp1>
		matrizTransicao[37][12] = "UEV";  //<nump1> ::= L<UEV>
		matrizTransicao[37][13] = "TMF";  //<nump2> ::= L<TMF>
		matrizTransicao[37][21] = "cadcp1";  //<cadcp1> ::= L<cadcp1>
		matrizTransicao[37][22] = "cadcp1";  //<cadcp2> ::= L<cadcp1>
		matrizTransicao[38][0] = "ID";  //<Inicial> ::= M<ID>
		matrizTransicao[38][1] = "ID";  //<ID> ::= M<ID>
		matrizTransicao[38][6] = "comp1";  //<comp1> ::= M<comp1>
		matrizTransicao[38][9] = "comp1";  //<comp2> ::= M<comp1>
		matrizTransicao[38][12] = "UEV";  //<nump1> ::= M<UEV>
		matrizTransicao[38][13] = "TMF";  //<nump2> ::= M<TMF>
		matrizTransicao[38][21] = "cadcp1";  //<cadcp1> ::= M<cadcp1>
		matrizTransicao[38][22] = "cadcp1";  //<cadcp2> ::= M<cadcp1>
		matrizTransicao[39][0] = "ID";  //<Inicial> ::= N<ID>
		matrizTransicao[39][1] = "ID";  //<ID> ::= N<ID>
		matrizTransicao[39][6] = "comp1";  //<comp1> ::= N<comp1>
		matrizTransicao[39][9] = "comp1";  //<comp2> ::= N<comp1>
		matrizTransicao[39][12] = "UEV";  //<nump1> ::= N<UEV>
		matrizTransicao[39][13] = "TMF";  //<nump2> ::= N<TMF>
		matrizTransicao[39][21] = "cadcp1";  //<cadcp1> ::= N<cadcp1>
		matrizTransicao[39][22] = "cadcp1";  //<cadcp2> ::= N<cadcp1>
		matrizTransicao[40][0] = "ID";  //<Inicial> ::= O<ID>
		matrizTransicao[40][1] = "ID";  //<ID> ::= O<ID>
		matrizTransicao[40][6] = "comp1";  //<comp1> ::= O<comp1>
		matrizTransicao[40][9] = "comp1";  //<comp2> ::= O<comp1>
		matrizTransicao[40][12] = "UEV";  //<nump1> ::= O<UEV>
		matrizTransicao[40][13] = "TMF";  //<nump2> ::= O<TMF>
		matrizTransicao[40][21] = "cadcp1";  //<cadcp1> ::= O<cadcp1>
		matrizTransicao[40][22] = "cadcp1";  //<cadcp2> ::= O<cadcp1>
		matrizTransicao[41][0] = "ID";  //<Inicial> ::= P<ID>
		matrizTransicao[41][1] = "ID";  //<ID> ::= P<ID>
		matrizTransicao[41][6] = "comp1";  //<comp1> ::= P<comp1>
		matrizTransicao[41][9] = "comp1";  //<comp2> ::= P<comp1>
		matrizTransicao[41][12] = "UEV";  //<nump1> ::= P<UEV>
		matrizTransicao[41][13] = "TMF";  //<nump2> ::= P<TMF>
		matrizTransicao[41][21] = "cadcp1";  //<cadcp1> ::= P<cadcp1>
		matrizTransicao[41][22] = "cadcp1";  //<cadcp2> ::= P<cadcp1>
		matrizTransicao[42][0] = "ID";  //<Inicial> ::= Q<ID>
		matrizTransicao[42][1] = "ID";  //<ID> ::= Q<ID>
		matrizTransicao[42][6] = "comp1";  //<comp1> ::= Q<comp1>
		matrizTransicao[42][9] = "comp1";  //<comp2> ::= Q<comp1>
		matrizTransicao[42][12] = "UEV";  //<nump1> ::= Q<UEV>
		matrizTransicao[42][13] = "TMF";  //<nump2> ::= Q<TMF>
		matrizTransicao[42][21] = "cadcp1";  //<cadcp1> ::= Q<cadcp1>
		matrizTransicao[42][22] = "cadcp1";  //<cadcp2> ::= Q<cadcp1>
		matrizTransicao[43][0] = "ID";  //<Inicial> ::= R<ID>
		matrizTransicao[43][1] = "ID";  //<ID> ::= R<ID>
		matrizTransicao[43][6] = "comp1";  //<comp1> ::= R<comp1>
		matrizTransicao[43][9] = "comp1";  //<comp2> ::= R<comp1>
		matrizTransicao[43][12] = "UEV";  //<nump1> ::= R<UEV>
		matrizTransicao[43][13] = "TMF";  //<nump2> ::= R<TMF>
		matrizTransicao[43][21] = "cadcp1";  //<cadcp1> ::= R<cadcp1>
		matrizTransicao[43][22] = "cadcp1";  //<cadcp2> ::= R<cadcp1>
		matrizTransicao[44][0] = "ID";  //<Inicial> ::= S<ID>
		matrizTransicao[44][1] = "ID";  //<ID> ::= S<ID>
		matrizTransicao[44][6] = "comp1";  //<comp1> ::= S<comp1>
		matrizTransicao[44][9] = "comp1";  //<comp2> ::= S<comp1>
		matrizTransicao[44][12] = "UEV";  //<nump1> ::= S<UEV>
		matrizTransicao[44][13] = "TMF";  //<nump2> ::= S<TMF>
		matrizTransicao[44][21] = "cadcp1";  //<cadcp1> ::= S<cadcp1>
		matrizTransicao[44][22] = "cadcp1";  //<cadcp2> ::= S<cadcp1>
		matrizTransicao[45][0] = "ID";  //<Inicial> ::= T<ID>
		matrizTransicao[45][1] = "ID";  //<ID> ::= T<ID>
		matrizTransicao[45][6] = "comp1";  //<comp1> ::= T<comp1>
		matrizTransicao[45][9] = "comp1";  //<comp2> ::= T<comp1>
		matrizTransicao[45][12] = "UEV";  //<nump1> ::= T<UEV>
		matrizTransicao[45][13] = "TMF";  //<nump2> ::= T<TMF>
		matrizTransicao[45][21] = "cadcp1";  //<cadcp1> ::= T<cadcp1>
		matrizTransicao[45][22] = "cadcp1";  //<cadcp2> ::= T<cadcp1>
		matrizTransicao[46][0] = "ID";  //<Inicial> ::= U<ID>
		matrizTransicao[46][1] = "ID";  //<ID> ::= U<ID>
		matrizTransicao[46][6] = "comp1";  //<comp1> ::= U<comp1>
		matrizTransicao[46][9] = "comp1";  //<comp2> ::= U<comp1>
		matrizTransicao[46][12] = "UEV";  //<nump1> ::= U<UEV>
		matrizTransicao[46][13] = "TMF";  //<nump2> ::= U<TMF>
		matrizTransicao[46][21] = "cadcp1";  //<cadcp1> ::= U<cadcp1>
		matrizTransicao[46][22] = "cadcp1";  //<cadcp2> ::= U<cadcp1>
		matrizTransicao[47][0] = "ID";  //<Inicial> ::= V<ID>
		matrizTransicao[47][1] = "ID";  //<ID> ::= V<ID>
		matrizTransicao[47][6] = "comp1";  //<comp1> ::= V<comp1>
		matrizTransicao[47][9] = "comp1";  //<comp2> ::= V<comp1>
		matrizTransicao[47][12] = "UEV";  //<nump1> ::= V<UEV>
		matrizTransicao[47][13] = "TMF";  //<nump2> ::= V<TMF>
		matrizTransicao[47][21] = "cadcp1";  //<cadcp1> ::= V<cadcp1>
		matrizTransicao[47][22] = "cadcp1";  //<cadcp2> ::= V<cadcp1>
		matrizTransicao[48][0] = "ID";  //<Inicial> ::= W<ID>
		matrizTransicao[48][1] = "ID";  //<ID> ::= W<ID>
		matrizTransicao[48][6] = "comp1";  //<comp1> ::= W<comp1>
		matrizTransicao[48][9] = "comp1";  //<comp2> ::= W<comp1>
		matrizTransicao[48][12] = "UEV";  //<nump1> ::= W<UEV>
		matrizTransicao[48][13] = "TMF";  //<nump2> ::= W<TMF>
		matrizTransicao[48][21] = "cadcp1";  //<cadcp1> ::= W<cadcp1>
		matrizTransicao[48][22] = "cadcp1";  //<cadcp2> ::= W<cadcp1>
		matrizTransicao[49][0] = "ID";  //<Inicial> ::= X<ID>
		matrizTransicao[49][1] = "ID";  //<ID> ::= X<ID>
		matrizTransicao[49][6] = "comp1";  //<comp1> ::= X<comp1>
		matrizTransicao[49][9] = "comp1";  //<comp2> ::= X<comp1>
		matrizTransicao[49][12] = "UEV";  //<nump1> ::= X<UEV>
		matrizTransicao[49][13] = "TMF";  //<nump2> ::= X<TMF>
		matrizTransicao[49][21] = "cadcp1";  //<cadcp1> ::= X<cadcp1>
		matrizTransicao[49][22] = "cadcp1";  //<cadcp2> ::= X<cadcp1>
		matrizTransicao[50][0] = "ID";  //<Inicial> ::= Y<ID>
		matrizTransicao[50][1] = "ID";  //<ID> ::= Y<ID>
		matrizTransicao[50][6] = "comp1";  //<comp1> ::= Y<comp1>
		matrizTransicao[50][9] = "comp1";  //<comp2> ::= Y<comp1>
		matrizTransicao[50][12] = "UEV";  //<nump1> ::= Y<UEV>
		matrizTransicao[50][13] = "TMF";  //<nump2> ::= Y<TMF>
		matrizTransicao[50][21] = "cadcp1";  //<cadcp1> ::= Y<cadcp1>
		matrizTransicao[50][22] = "cadcp1";  //<cadcp2> ::= Y<cadcp1>
		matrizTransicao[51][0] = "ID";  //<Inicial> ::= Z<ID>
		matrizTransicao[51][1] = "ID";  //<ID> ::= Z<ID>
		matrizTransicao[51][6] = "comp1";  //<comp1> ::= Z<comp1>
		matrizTransicao[51][9] = "comp1";  //<comp2> ::= Z<comp1>
		matrizTransicao[51][12] = "UEV";  //<nump1> ::= Z<UEV>
		matrizTransicao[51][13] = "TMF";  //<nump2> ::= Z<TMF>
		matrizTransicao[51][21] = "cadcp1";  //<cadcp1> ::= Z<cadcp1>
		matrizTransicao[51][22] = "cadcp1";  //<cadcp2> ::= Z<cadcp1>
		matrizTransicao[52][0] = "ID";  //<Inicial> ::=  <ID>
		matrizTransicao[52][6] = "comp1";  //<comp1> ::=  <comp1>
		matrizTransicao[52][9] = "comp1";  //<comp2> ::=  <comp1>
		matrizTransicao[52][12] = "nump1";  //<nump1> ::=  <nump1>
		matrizTransicao[52][13] = "TMF";  //<nump2> ::=  <TMF>
		matrizTransicao[52][17] = "nump1";  //<OPAR3> ::=  <nump1>
		matrizTransicao[52][21] = "cadcp1";  //<cadcp1> ::=  <cadcp1>
		matrizTransicao[52][22] = "cadcp1";  //<cadcp2> ::=  <cadcp1>
		matrizTransicao[53][1] = "ID";  //<ID> ::= 0<ID>
		matrizTransicao[54][0] = "NUM";  //<Inicial> ::= 1<NUM>
		matrizTransicao[54][1] = "ID";  //<ID> ::= 1<ID>
		matrizTransicao[54][6] = "comp1";  //<comp1> ::= 1<comp1>
		matrizTransicao[54][9] = "comp1";  //<comp2> ::= 1<comp1>
		matrizTransicao[54][11] = "NUM";  //<NUM> ::= 1<NUM>
		matrizTransicao[54][12] = "NUM";  //<nump1> ::= 1<NUM>
		matrizTransicao[54][13] = "NUM2";  //<nump2> ::= 1<NUM2>
		matrizTransicao[54][14] = "NUM2";  //<NUM2> ::= 1<NUM2>
		matrizTransicao[54][17] = "NUM";  //<OPAR3> ::= 1<NUM>
		matrizTransicao[54][21] = "cadcp1";  //<cadcp1> ::= 1<cadcp1>
		matrizTransicao[54][22] = "cadcp1";  //<cadcp2> ::= 1<cadcp1>
		matrizTransicao[55][0] = "NUM";  //<Inicial> ::= 2<NUM>
		matrizTransicao[55][1] = "ID";  //<ID> ::= 2<ID>
		matrizTransicao[55][6] = "comp1";  //<comp1> ::= 2<comp1>
		matrizTransicao[55][9] = "comp1";  //<comp2> ::= 2<comp1>
		matrizTransicao[55][11] = "NUM";  //<NUM> ::= 2<NUM>
		matrizTransicao[55][12] = "NUM";  //<nump1> ::= 2<NUM>
		matrizTransicao[55][13] = "NUM2";  //<nump2> ::= 2<NUM2>
		matrizTransicao[55][14] = "NUM2";  //<NUM2> ::= 2<NUM2>
		matrizTransicao[55][17] = "NUM";  //<OPAR3> ::= 2<NUM>
		matrizTransicao[55][21] = "cadcp1";  //<cadcp1> ::= 2<cadcp1>
		matrizTransicao[55][22] = "cadcp1";  //<cadcp2> ::= 2<cadcp1>
		matrizTransicao[56][0] = "NUM";  //<Inicial> ::= 3<NUM>
		matrizTransicao[56][1] = "ID";  //<ID> ::= 3<ID>
		matrizTransicao[56][6] = "comp1";  //<comp1> ::= 3<comp1>
		matrizTransicao[56][9] = "comp1";  //<comp2> ::= 3<comp1>
		matrizTransicao[56][11] = "NUM";  //<NUM> ::= 3<NUM>
		matrizTransicao[56][12] = "NUM";  //<nump1> ::= 3<NUM>
		matrizTransicao[56][13] = "NUM2";  //<nump2> ::= 3<NUM2>
		matrizTransicao[56][14] = "NUM2";  //<NUM2> ::= 3<NUM2>
		matrizTransicao[56][17] = "NUM";  //<OPAR3> ::= 3<NUM>
		matrizTransicao[56][21] = "cadcp1";  //<cadcp1> ::= 3<cadcp1>
		matrizTransicao[56][22] = "cadcp1";  //<cadcp2> ::= 3<cadcp1>
		matrizTransicao[57][0] = "NUM";  //<Inicial> ::= 4<NUM>
		matrizTransicao[57][1] = "ID";  //<ID> ::= 4<ID>
		matrizTransicao[57][6] = "comp1";  //<comp1> ::= 4<comp1>
		matrizTransicao[57][9] = "comp1";  //<comp2> ::= 4<comp1>
		matrizTransicao[57][11] = "NUM";  //<NUM> ::= 4<NUM>
		matrizTransicao[57][12] = "NUM";  //<nump1> ::= 4<NUM>
		matrizTransicao[57][13] = "NUM2";  //<nump2> ::= 4<NUM2>
		matrizTransicao[57][14] = "NUM2";  //<NUM2> ::= 4<NUM2>
		matrizTransicao[57][17] = "NUM";  //<OPAR3> ::= 4<NUM>
		matrizTransicao[57][21] = "cadcp1";  //<cadcp1> ::= 4<cadcp1>
		matrizTransicao[57][22] = "cadcp1";  //<cadcp2> ::= 4<cadcp1>
		matrizTransicao[58][0] = "NUM";  //<Inicial> ::= 5<NUM>
		matrizTransicao[58][1] = "ID";  //<ID> ::= 5<ID>
		matrizTransicao[58][6] = "comp1";  //<comp1> ::= 5<comp1>
		matrizTransicao[58][9] = "comp1";  //<comp2> ::= 5<comp1>
		matrizTransicao[58][11] = "NUM";  //<NUM> ::= 5<NUM>
		matrizTransicao[58][12] = "NUM";  //<nump1> ::= 5<NUM>
		matrizTransicao[58][13] = "NUM2";  //<nump2> ::= 5<NUM2>
		matrizTransicao[58][14] = "NUM2";  //<NUM2> ::= 5<NUM2>
		matrizTransicao[58][17] = "NUM";  //<OPAR3> ::= 5<NUM>
		matrizTransicao[58][21] = "cadcp1";  //<cadcp1> ::= 5<cadcp1>
		matrizTransicao[58][22] = "cadcp1";  //<cadcp2> ::= 5<cadcp1>
		matrizTransicao[59][0] = "NUM";  //<Inicial> ::= 6<NUM>
		matrizTransicao[59][1] = "ID";  //<ID> ::= 6<ID>
		matrizTransicao[59][6] = "comp1";  //<comp1> ::= 6<comp1>
		matrizTransicao[59][9] = "comp1";  //<comp2> ::= 6<comp1>
		matrizTransicao[59][11] = "NUM";  //<NUM> ::= 6<NUM>
		matrizTransicao[59][12] = "NUM";  //<nump1> ::= 6<NUM>
		matrizTransicao[59][13] = "NUM2";  //<nump2> ::= 6<NUM2>
		matrizTransicao[59][14] = "NUM2";  //<NUM2> ::= 6<NUM2>
		matrizTransicao[59][17] = "NUM";  //<OPAR3> ::= 6<NUM>
		matrizTransicao[59][21] = "cadcp1";  //<cadcp1> ::= 6<cadcp1>
		matrizTransicao[59][22] = "cadcp1";  //<cadcp2> ::= 6<cadcp1>
		matrizTransicao[60][0] = "NUM";  //<Inicial> ::= 7<NUM>
		matrizTransicao[60][1] = "ID";  //<ID> ::= 7<ID>
		matrizTransicao[60][6] = "comp1";  //<comp1> ::= 7<comp1>
		matrizTransicao[60][9] = "comp1";  //<comp2> ::= 7<comp1>
		matrizTransicao[60][11] = "NUM";  //<NUM> ::= 7<NUM>
		matrizTransicao[60][12] = "NUM";  //<nump1> ::= 7<NUM>
		matrizTransicao[60][13] = "NUM2";  //<nump2> ::= 7<NUM2>
		matrizTransicao[60][14] = "NUM2";  //<NUM2> ::= 7<NUM2>
		matrizTransicao[60][17] = "NUM";  //<OPAR3> ::= 7<NUM>
		matrizTransicao[60][21] = "cadcp1";  //<cadcp1> ::= 7<cadcp1>
		matrizTransicao[60][22] = "cadcp1";  //<cadcp2> ::= 7<cadcp1>
		matrizTransicao[61][0] = "NUM";  //<Inicial> ::= 8<NUM>
		matrizTransicao[61][1] = "ID";  //<ID> ::= 8<ID>
		matrizTransicao[61][6] = "comp1";  //<comp1> ::= 8<comp1>
		matrizTransicao[61][9] = "comp1";  //<comp2> ::= 8<comp1>
		matrizTransicao[61][11] = "NUM";  //<NUM> ::= 8<NUM>
		matrizTransicao[61][12] = "NUM";  //<nump1> ::= 8<NUM>
		matrizTransicao[61][13] = "NUM2";  //<nump2> ::= 8<NUM2>
		matrizTransicao[61][14] = "NUM2";  //<NUM2> ::= 8<NUM2>
		matrizTransicao[61][17] = "NUM";  //<OPAR3> ::= 8<NUM>
		matrizTransicao[61][21] = "cadcp1";  //<cadcp1> ::= 8<cadcp1>
		matrizTransicao[61][22] = "cadcp1";  //<cadcp2> ::= 8<cadcp1>
		matrizTransicao[62][0] = "NUM";  //<Inicial> ::= 9<NUM>
		matrizTransicao[62][1] = "ID";  //<ID> ::= 9<ID>
		matrizTransicao[62][6] = "comp1";  //<comp1> ::= 9<comp1>
		matrizTransicao[62][9] = "comp1";  //<comp2> ::= 9<comp1>
		matrizTransicao[62][11] = "NUM";  //<NUM> ::= 9<NUM>
		matrizTransicao[62][12] = "NUM";  //<nump1> ::= 9<NUM>
		matrizTransicao[62][13] = "NUM2";  //<nump2> ::= 9<NUM2>
		matrizTransicao[62][14] = "NUM2";  //<NUM2> ::= 9<NUM2>
		matrizTransicao[62][17] = "NUM";  //<OPAR3> ::= 9<NUM>
		matrizTransicao[62][21] = "cadcp1";  //<cadcp1> ::= 9<cadcp1>
		matrizTransicao[62][22] = "cadcp1";  //<cadcp2> ::= 9<cadcp1>
		matrizTransicao[63][1] = "ID";  //<ID> ::= _<ID>
		matrizTransicao[63][6] = "comp1";  //<comp1> ::= _<comp1>
		matrizTransicao[63][9] = "comp1";  //<comp2> ::= _<comp1>
		matrizTransicao[63][12] = "UEV";  //<nump1> ::= _<UEV>
		matrizTransicao[63][13] = "TMF";  //<nump2> ::= _<TMF>
		matrizTransicao[63][21] = "cadcp1";  //<cadcp1> ::= _<cadcp1>
		matrizTransicao[63][22] = "cadcp1";  //<cadcp2> ::= _<cadcp1>
		matrizTransicao[64][0] = "DEL";  //<Inicial> ::= ;<DEL>
		matrizTransicao[64][6] = "comp1";  //<comp1> ::= ;<comp1>
		matrizTransicao[64][9] = "comp1";  //<comp2> ::= ;<comp1>
		matrizTransicao[64][12] = "UEV";  //<nump1> ::= ;<UEV>
		matrizTransicao[64][13] = "TMF";  //<nump2> ::= ;<TMF>
		matrizTransicao[65][0] = "DEL";  //<Inicial> ::= ,<DEL>
		matrizTransicao[65][6] = "comp1";  //<comp1> ::= ,<comp1>
		matrizTransicao[65][9] = "comp1";  //<comp2> ::= ,<comp1>
		matrizTransicao[65][12] = "UEV";  //<nump1> ::= ,<UEV>
		matrizTransicao[65][13] = "TMF";  //<nump2> ::= ,<TMF>
		matrizTransicao[66][0] = "DEL";  //<Inicial> ::= (<DEL>
		matrizTransicao[66][6] = "comp1";  //<comp1> ::= (<comp1>
		matrizTransicao[66][9] = "comp1";  //<comp2> ::= (<comp1>
		matrizTransicao[66][12] = "UEV";  //<nump1> ::= (<UEV>
		matrizTransicao[66][13] = "TMF";  //<nump2> ::= (<TMF>
		matrizTransicao[67][0] = "DEL";  //<Inicial> ::= )<DEL>
		matrizTransicao[67][6] = "comp1";  //<comp1> ::= )<comp1>
		matrizTransicao[67][9] = "comp1";  //<comp2> ::= )<comp1>
		matrizTransicao[67][12] = "UEV";  //<nump1> ::= )<UEV>
		matrizTransicao[67][13] = "TMF";  //<nump2> ::= )<TMF>
		matrizTransicao[68][0] = "DEL";  //<Inicial> ::= [<DEL>
		matrizTransicao[68][6] = "comp1";  //<comp1> ::= [<comp1>
		matrizTransicao[68][9] = "comp1";  //<comp2> ::= [<comp1>
		matrizTransicao[68][12] = "UEV";  //<nump1> ::= [<UEV>
		matrizTransicao[68][13] = "TMF";  //<nump2> ::= [<TMF>
		matrizTransicao[69][0] = "DEL";  //<Inicial> ::= ]<DEL>
		matrizTransicao[69][6] = "comp1";  //<comp1> ::= ]<comp1>
		matrizTransicao[69][9] = "comp1";  //<comp2> ::= ]<comp1>
		matrizTransicao[69][12] = "UEV";  //<nump1> ::= ]<UEV>
		matrizTransicao[69][13] = "TMF";  //<nump2> ::= ]<TMF>
		matrizTransicao[70][0] = "DEL";  //<Inicial> ::= {<DEL>
		matrizTransicao[70][6] = "comp1";  //<comp1> ::= {<comp1>
		matrizTransicao[70][9] = "comp1";  //<comp2> ::= {<comp1>
		matrizTransicao[70][12] = "UEV";  //<nump1> ::= {<UEV>
		matrizTransicao[70][13] = "TMF";  //<nump2> ::= {<TMF>
		matrizTransicao[71][0] = "DEL";  //<Inicial> ::= }<DEL>
		matrizTransicao[71][6] = "comp1";  //<comp1> ::= }<comp1>
		matrizTransicao[71][9] = "comp1";  //<comp2> ::= }<comp1>
		matrizTransicao[71][12] = "UEV";  //<nump1> ::= }<UEV>
		matrizTransicao[71][13] = "TMF";  //<nump2> ::= }<TMF>
		matrizTransicao[72][0] = "DEL";  //<Inicial> ::= .<DEL>
		matrizTransicao[72][6] = "comp1";  //<comp1> ::= .<comp1>
		matrizTransicao[72][9] = "comp1";  //<comp2> ::= .<comp1>
		matrizTransicao[72][11] = "nump2";  //<NUM> ::= .<nump2>
		matrizTransicao[72][12] = "UEV";  //<nump1> ::= .<UEV>
		matrizTransicao[72][13] = "TMF";  //<nump2> ::= .<TMF>
		matrizTransicao[73][0] = "OPAR";  //<Inicial> ::= +<OPAR>
		matrizTransicao[73][6] = "comp1";  //<comp1> ::= +<comp1>
		matrizTransicao[73][7] = "OPAR2";  //<OPAR> ::= +<OPAR2>
		matrizTransicao[73][9] = "comp1";  //<comp2> ::= +<comp1>
		matrizTransicao[73][12] = "UEV";  //<nump1> ::= +<UEV>
		matrizTransicao[73][13] = "TMF";  //<nump2> ::= +<TMF>
		matrizTransicao[73][21] = "cadcp1";  //<cadcp1> ::= +<cadcp1>
		matrizTransicao[73][22] = "cadcp1";  //<cadcp2> ::= +<cadcp1>
		matrizTransicao[74][0] = "OPAR";  //<Inicial> ::= *<OPAR>
		matrizTransicao[74][6] = "comp2";  //<comp1> ::= *<comp2>
		matrizTransicao[74][7] = "comp1";  //<OPAR> ::= *<comp1>
		matrizTransicao[74][9] = "comp1";  //<comp2> ::= *<comp1>
		matrizTransicao[74][12] = "UEV";  //<nump1> ::= *<UEV>
		matrizTransicao[74][13] = "TMF";  //<nump2> ::= *<TMF>
		matrizTransicao[74][21] = "cadcp1";  //<cadcp1> ::= *<cadcp1>
		matrizTransicao[74][22] = "cadcp1";  //<cadcp2> ::= *<cadcp1>
		matrizTransicao[75][0] = "OPAR";  //<Inicial> ::= /<OPAR>
		matrizTransicao[75][6] = "comp1";  //<comp1> ::= /<comp1>
		matrizTransicao[75][7] = "COML";  //<OPAR> ::= /<COML>
		matrizTransicao[75][9] = "COMB";  //<comp2> ::= /<COMB>
		matrizTransicao[75][12] = "UEV";  //<nump1> ::= /<UEV>
		matrizTransicao[75][13] = "TMF";  //<nump2> ::= /<TMF>
		matrizTransicao[75][21] = "cadcp1";  //<cadcp1> ::= /<cadcp1>
		matrizTransicao[75][22] = "cadcp1";  //<cadcp2> ::= /<cadcp1>
		matrizTransicao[76][0] = "OPL";  //<Inicial> ::= !<OPL>
		matrizTransicao[76][6] = "comp1";  //<comp1> ::= !<comp1>
		matrizTransicao[76][9] = "comp1";  //<comp2> ::= !<comp1>
		matrizTransicao[76][12] = "UEV";  //<nump1> ::= !<UEV>
		matrizTransicao[76][13] = "TMF";  //<nump2> ::= !<TMF>
		matrizTransicao[76][21] = "cadcp1";  //<cadcp1> ::= !<cadcp1>
		matrizTransicao[76][22] = "cadcp1";  //<cadcp2> ::= !<cadcp1>
		matrizTransicao[77][0] = "cadcp1";  //<Inicial> ::= "<cadcp1>
		matrizTransicao[77][6] = "comp1";  //<comp1> ::= "<comp1>
		matrizTransicao[77][9] = "comp1";  //<comp2> ::= "<comp1>
		matrizTransicao[77][12] = "UEV";  //<nump1> ::= "<UEV>
		matrizTransicao[77][13] = "TMF";  //<nump2> ::= "<TMF>
		matrizTransicao[77][21] = "CADC";  //<cadcp1> ::= "<CADC>
		matrizTransicao[77][22] = "cadcp1";  //<cadcp2> ::= "<cadcp1>
		matrizTransicao[78][6] = "comp1";  //<comp1> ::= #<comp1>
		matrizTransicao[78][9] = "comp1";  //<comp2> ::= #<comp1>
		matrizTransicao[78][12] = "UEV";  //<nump1> ::= #<UEV>
		matrizTransicao[78][13] = "TMF";  //<nump2> ::= #<TMF>
		matrizTransicao[78][21] = "cadcp1";  //<cadcp1> ::= #<cadcp1>
		matrizTransicao[78][22] = "cadcp1";  //<cadcp2> ::= #<cadcp1>
		matrizTransicao[79][6] = "comp1";  //<comp1> ::= $<comp1>
		matrizTransicao[79][9] = "comp1";  //<comp2> ::= $<comp1>
		matrizTransicao[79][12] = "UEV";  //<nump1> ::= $<UEV>
		matrizTransicao[79][13] = "TMF";  //<nump2> ::= $<TMF>
		matrizTransicao[79][21] = "cadcp1";  //<cadcp1> ::= $<cadcp1>
		matrizTransicao[79][22] = "cadcp1";  //<cadcp2> ::= $<cadcp1>
		matrizTransicao[80][6] = "comp1";  //<comp1> ::= %<comp1>
		matrizTransicao[80][9] = "comp1";  //<comp2> ::= %<comp1>
		matrizTransicao[80][12] = "UEV";  //<nump1> ::= %<UEV>
		matrizTransicao[80][13] = "TMF";  //<nump2> ::= %<TMF>
		matrizTransicao[80][21] = "cadcp1";  //<cadcp1> ::= %<cadcp1>
		matrizTransicao[80][22] = "cadcp1";  //<cadcp2> ::= %<cadcp1>
		matrizTransicao[81][0] = "oplp";  //<Inicial> ::= &<oplp>
		matrizTransicao[81][6] = "comp1";  //<comp1> ::= &<comp1>
		matrizTransicao[81][9] = "comp1";  //<comp2> ::= &<comp1>
		matrizTransicao[81][12] = "UEV";  //<nump1> ::= &<UEV>
		matrizTransicao[81][13] = "TMF";  //<nump2> ::= &<TMF>
		matrizTransicao[81][20] = "OPL2";  //<oplp> ::= &<OPL2>
		matrizTransicao[81][21] = "cadcp1";  //<cadcp1> ::= &<cadcp1>
		matrizTransicao[81][22] = "cadcp1";  //<cadcp2> ::= &<cadcp1>
		matrizTransicao[82][0] = "OPAR3";  //<Inicial> ::= -<OPAR3>
		matrizTransicao[82][6] = "comp1";  //<comp1> ::= -<comp1>
		matrizTransicao[82][9] = "comp1";  //<comp2> ::= -<comp1>
		matrizTransicao[82][12] = "UEV";  //<nump1> ::= -<UEV>
		matrizTransicao[82][13] = "TMF";  //<nump2> ::= -<TMF>
		matrizTransicao[82][17] = "OPAR2";  //<OPAR3> ::= -<OPAR2>
		matrizTransicao[82][21] = "cadcp1";  //<cadcp1> ::= -<cadcp1>
		matrizTransicao[82][22] = "cadcp1";  //<cadcp2> ::= -<cadcp1>
		matrizTransicao[83][6] = "comp1";  //<comp1> ::= :<comp1>
		matrizTransicao[83][9] = "comp1";  //<comp2> ::= :<comp1>
		matrizTransicao[83][12] = "UEV";  //<nump1> ::= :<UEV>
		matrizTransicao[83][13] = "TMF";  //<nump2> ::= :<TMF>
		matrizTransicao[83][21] = "cadcp1";  //<cadcp1> ::= :<cadcp1>
		matrizTransicao[83][22] = "cadcp1";  //<cadcp2> ::= :<cadcp1>
		matrizTransicao[84][0] = "OPRL";  //<Inicial> ::= <<OPRL>
		matrizTransicao[84][6] = "comp1";  //<comp1> ::= <<comp1>
		matrizTransicao[84][9] = "comp1";  //<comp2> ::= <<comp1>
		matrizTransicao[84][12] = "UEV";  //<nump1> ::= <<UEV>
		matrizTransicao[84][13] = "TMF";  //<nump2> ::= <<TMF>
		matrizTransicao[84][21] = "cadcp1";  //<cadcp1> ::= <<cadcp1>
		matrizTransicao[84][22] = "cadcp1";  //<cadcp2> ::= <<cadcp1>
		matrizTransicao[85][0] = "OPRL";  //<Inicial> ::= =<OPRL>
		matrizTransicao[85][6] = "comp1";  //<comp1> ::= =<comp1>
		matrizTransicao[85][9] = "comp1";  //<comp2> ::= =<comp1>
		matrizTransicao[85][12] = "UEV";  //<nump1> ::= =<UEV>
		matrizTransicao[85][13] = "TMF";  //<nump2> ::= =<TMF>
		matrizTransicao[85][15] = "OPRL2";  //<OPRL> ::= =<OPRL2>
		matrizTransicao[85][18] = "OPRL2";  //<OPL> ::= =<OPRL2>
		matrizTransicao[85][21] = "cadcp1";  //<cadcp1> ::= =<cadcp1>
		matrizTransicao[85][22] = "cadcp1";  //<cadcp2> ::= =<cadcp1>
		matrizTransicao[86][0] = "OPRL";  //<Inicial> ::= ><OPRL>
		matrizTransicao[86][6] = "comp1";  //<comp1> ::= ><comp1>
		matrizTransicao[86][9] = "comp1";  //<comp2> ::= ><comp1>
		matrizTransicao[86][12] = "UEV";  //<nump1> ::= ><UEV>
		matrizTransicao[86][13] = "TMF";  //<nump2> ::= ><TMF>
		matrizTransicao[86][21] = "cadcp1";  //<cadcp1> ::= ><cadcp1>
		matrizTransicao[86][22] = "cadcp1";  //<cadcp2> ::= ><cadcp1>
		matrizTransicao[87][6] = "comp1";  //<comp1> ::= ?<comp1>
		matrizTransicao[87][9] = "comp1";  //<comp2> ::= ?<comp1>
		matrizTransicao[87][12] = "UEV";  //<nump1> ::= ?<UEV>
		matrizTransicao[87][13] = "TMF";  //<nump2> ::= ?<TMF>
		matrizTransicao[87][21] = "cadcp1";  //<cadcp1> ::= ?<cadcp1>
		matrizTransicao[87][22] = "cadcp1";  //<cadcp2> ::= ?<cadcp1>
		matrizTransicao[88][6] = "comp1";  //<comp1> ::= @<comp1>
		matrizTransicao[88][9] = "comp1";  //<comp2> ::= @<comp1>
		matrizTransicao[88][12] = "UEV";  //<nump1> ::= @<UEV>
		matrizTransicao[88][13] = "TMF";  //<nump2> ::= @<TMF>
		matrizTransicao[88][21] = "cadcp1";  //<cadcp1> ::= @<cadcp1>
		matrizTransicao[88][22] = "cadcp1";  //<cadcp2> ::= @<cadcp1>
		matrizTransicao[89][6] = "comp1";  //<comp1> ::= \<comp1>
		matrizTransicao[89][9] = "comp1";  //<comp2> ::= \<comp1>
		matrizTransicao[89][12] = "UEV";  //<nump1> ::= \<UEV>
		matrizTransicao[89][13] = "TMF";  //<nump2> ::= \<TMF>
		matrizTransicao[89][21] = "cadcp2";  //<cadcp1> ::= \<cadcp2>
		matrizTransicao[89][22] = "cadcp1";  //<cadcp2> ::= \<cadcp1>
		matrizTransicao[90][6] = "comp1";  //<comp1> ::= ^<comp1>
		matrizTransicao[90][9] = "comp1";  //<comp2> ::= ^<comp1>
		matrizTransicao[90][12] = "UEV";  //<nump1> ::= ^<UEV>
		matrizTransicao[90][13] = "TMF";  //<nump2> ::= ^<TMF>
		matrizTransicao[90][21] = "cadcp1";  //<cadcp1> ::= ^<cadcp1>
		matrizTransicao[90][22] = "cadcp1";  //<cadcp2> ::= ^<cadcp1>
		matrizTransicao[91][6] = "comp1";  //<comp1> ::= `<comp1>
		matrizTransicao[91][9] = "comp1";  //<comp2> ::= `<comp1>
		matrizTransicao[91][12] = "UEV";  //<nump1> ::= `<UEV>
		matrizTransicao[91][13] = "TMF";  //<nump2> ::= `<TMF>
		matrizTransicao[91][21] = "cadcp1";  //<cadcp1> ::= `<cadcp1>
		matrizTransicao[91][22] = "cadcp1";  //<cadcp2> ::= `<cadcp1>
		matrizTransicao[92][0] = "oplp";  //<Inicial> ::= |<oplp>
		matrizTransicao[92][6] = "comp1";  //<comp1> ::= |<comp1>
		matrizTransicao[92][9] = "comp1";  //<comp2> ::= |<comp1>
		matrizTransicao[92][12] = "UEV";  //<nump1> ::= |<UEV>
		matrizTransicao[92][13] = "TMF";  //<nump2> ::= |<TMF>
		matrizTransicao[92][20] = "OPL2";  //<oplp> ::= |<OPL2>
		matrizTransicao[92][21] = "cadcp1";  //<cadcp1> ::= |<cadcp1>
		matrizTransicao[92][22] = "cadcp1";  //<cadcp2> ::= |<cadcp1>
		matrizTransicao[93][6] = "comp1";  //<comp1> ::= ~<comp1>
		matrizTransicao[93][9] = "comp1";  //<comp2> ::= ~<comp1>
		matrizTransicao[93][12] = "UEV";  //<nump1> ::= ~<UEV>
		matrizTransicao[93][13] = "TMF";  //<nump2> ::= ~<TMF>
		matrizTransicao[93][21] = "cadcp1";  //<cadcp1> ::= ~<cadcp1>
		matrizTransicao[93][22] = "cadcp1";  //<cadcp2> ::= ~<cadcp1>
	}

	private int executaTransicao(String entrada)
	{
		int resultado = TRANSICAO_INEXISTENTE;
		int indEntrada, indEstado;
		indEntrada = getIndiceEntrada(entrada);
		if (indEntrada==-1)
			return ENTRADA_INEXISTENTE;
		indEstado = getIndiceEstado(estadoAtual);
		String estadoSaida = matrizTransicao[indEntrada][indEstado];
		if (estadoSaida!=null)
		{
			estadoAtual = estadoSaida;
			resultado = TRANSICAO_ENCONTRADA;
		}

		return resultado;
	}
	private int getIndiceEntrada(String entrada)
	{
		for (int i=0; i<alfabeto.length; i++)
			if (alfabeto[i].equals(entrada))
			return i;

		return -1;
	}

	private int getIndiceEstado(String estado)
	{
		for (int i=0; i<estados.length; i++)
			if (estados[i].equals(estado))
				return i;

		return -1;
	}

	private void btnVerificar_actionPerformed(ActionEvent e)
	{
		String valorEntrada = txfSentenca.getText();
		String entradaAtual = null;
		int resultado = TRANSICAO_ENCONTRADA;
		int i=0;
		estadoAtual = estadoInicial;
		while ( (i<valorEntrada.length()) && (resultado==TRANSICAO_ENCONTRADA) )
		{
			entradaAtual = valorEntrada.charAt(i) + "";
			resultado = executaTransicao(entradaAtual);
			i++;
		}
		switch(resultado)
		{
			case TRANSICAO_INEXISTENTE : JOptionPane.showMessageDialog(null, "N�o foi poss�vel encontrar uma transi��o com a entrada \"" + entradaAtual + "\".\nSenten�a n�o reconhecida!", "Resultado", JOptionPane.ERROR_MESSAGE);
										break;
			case ENTRADA_INEXISTENTE :   JOptionPane.showMessageDialog(null, "A entrada \"" + entradaAtual + "\" n�o pertence ao alfabeto.\nSenten�a n�o reconhecida!", "Resultado", JOptionPane.ERROR_MESSAGE);
										break;
			default : if ( isReconhecedor(estadoAtual) )
						JOptionPane.showMessageDialog(null, "Senten�a reconhecida!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Senten�a n�o reconhecida!", "Resultado", JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean isReconhecedor(String estado)
	{
		for (int i=0; i<estadosReconhecedores.length; i++)
		{
			if (estadosReconhecedores[i].equals(estado))
				return true;
		}
		return false;
	}

	public static void main(String[] args)
	{
		MATRIZ f1 = new MATRIZ();
		f1.addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			});
		f1.show();
	}
}