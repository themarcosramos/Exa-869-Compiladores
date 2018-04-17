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
		matrizTransicao['a'][0] = Estado.ID;  //<Inicial> ::= a<ID>
		matrizTransicao['a'][1] = Estado.ID;  //<ID> ::= a<ID>
		matrizTransicao['a'][6] = Estado.comp1;  //<comp1> ::= a<comp1>
		matrizTransicao['a'][9] = Estado.comp1;  //<comp2> ::= a<comp1>
		matrizTransicao['a'][12] = Estado.UEV;  //<nump1> ::= a<UEV>
		matrizTransicao['a'][13] = Estado.TMF;  //<nump2> ::= a<TMF>
		matrizTransicao['a'][21] = Estado.cadcp1;  //<cadcp1> ::= a<cadcp1>
		matrizTransicao['a'][22] = Estado.cadcp1;  //<cadcp2> ::= a<cadcp1>
		matrizTransicao['b'][0] = Estado.ID;  //<Inicial> ::= b<ID>
		matrizTransicao['b'][1] = Estado.ID;  //<ID> ::= b<ID>
		matrizTransicao['b'][6] = Estado.comp1;  //<comp1> ::= b<comp1>
		matrizTransicao['b'][9] = Estado.comp1;  //<comp2> ::= b<comp1>
		matrizTransicao['b'][12] = Estado.UEV;  //<nump1> ::= b<UEV>
		matrizTransicao['b'][13] = Estado.TMF;  //<nump2> ::= b<TMF>
		matrizTransicao['b'][21] = Estado.cadcp1;  //<cadcp1> ::= b<cadcp1>
		matrizTransicao['b'][22] = Estado.cadcp1;  //<cadcp2> ::= b<cadcp1>
		matrizTransicao['c'][0] = Estado.ID;  //<Inicial> ::= c<ID>
		matrizTransicao['c'][1] = Estado.ID;  //<ID> ::= c<ID>
		matrizTransicao['c'][6] = Estado.comp1;  //<comp1> ::= c<comp1>
		matrizTransicao['c'][9] = Estado.comp1;  //<comp2> ::= c<comp1>
		matrizTransicao['c'][12] = Estado.UEV;  //<nump1> ::= c<UEV>
		matrizTransicao['c'][13] = Estado.TMF;  //<nump2> ::= c<TMF>
		matrizTransicao['c'][21] = Estado.cadcp1;  //<cadcp1> ::= c<cadcp1>
		matrizTransicao['c'][22] = Estado.cadcp1;  //<cadcp2> ::= c<cadcp1>
		matrizTransicao['d'][0] = Estado.ID;  //<Inicial> ::= d<ID>
		matrizTransicao['d'][1] = Estado.ID;  //<ID> ::= d<ID>
		matrizTransicao['d'][6] = Estado.comp1;  //<comp1> ::= d<comp1>
		matrizTransicao['d'][9] = Estado.comp1;  //<comp2> ::= d<comp1>
		matrizTransicao['d'][12] = Estado.UEV;  //<nump1> ::= d<UEV>
		matrizTransicao['d'][13] = Estado.TMF;  //<nump2> ::= d<TMF>
		matrizTransicao['d'][21] = Estado.cadcp1;  //<cadcp1> ::= d<cadcp1>
		matrizTransicao['d'][22] = Estado.cadcp1;  //<cadcp2> ::= d<cadcp1>
		matrizTransicao['e'][0] = Estado.ID;  //<Inicial> ::= e<ID>
		matrizTransicao['e'][1] = Estado.ID;  //<ID> ::= e<ID>
		matrizTransicao['e'][6] = Estado.comp1;  //<comp1> ::= e<comp1>
		matrizTransicao['e'][9] = Estado.comp1;  //<comp2> ::= e<comp1>
		matrizTransicao['e'][12] = Estado.UEV;  //<nump1> ::= e<UEV>
		matrizTransicao['e'][13] = Estado.TMF;  //<nump2> ::= e<TMF>
		matrizTransicao['e'][21] = Estado.cadcp1;  //<cadcp1> ::= e<cadcp1>
		matrizTransicao['e'][22] = Estado.cadcp1;  //<cadcp2> ::= e<cadcp1>
		matrizTransicao['f'][0] = Estado.ID;  //<Inicial> ::= f<ID>
		matrizTransicao['f'][1] = Estado.ID;  //<ID> ::= f<ID>
		matrizTransicao['f'][6] = Estado.comp1;  //<comp1> ::= f<comp1>
		matrizTransicao['f'][9] = Estado.comp1;  //<comp2> ::= f<comp1>
		matrizTransicao['f'][12] = Estado.UEV;  //<nump1> ::= f<UEV>
		matrizTransicao['f'][13] = Estado.TMF;  //<nump2> ::= f<TMF>
		matrizTransicao['f'][21] = Estado.cadcp1;  //<cadcp1> ::= f<cadcp1>
		matrizTransicao['f'][22] = Estado.cadcp1;  //<cadcp2> ::= f<cadcp1>
		matrizTransicao['g'][0] = Estado.ID;  //<Inicial> ::= g<ID>
		matrizTransicao['g'][1] = Estado.ID;  //<ID> ::= g<ID>
		matrizTransicao['g'][6] = Estado.comp1;  //<comp1> ::= g<comp1>
		matrizTransicao['g'][9] = Estado.comp1;  //<comp2> ::= g<comp1>
		matrizTransicao['g'][12] = Estado.UEV;  //<nump1> ::= g<UEV>
		matrizTransicao['g'][13] = Estado.TMF;  //<nump2> ::= g<TMF>
		matrizTransicao['g'][21] = Estado.cadcp1;  //<cadcp1> ::= g<cadcp1>
		matrizTransicao['g'][22] = Estado.cadcp1;  //<cadcp2> ::= g<cadcp1>
		matrizTransicao['h'][0] = Estado.ID;  //<Inicial> ::= h<ID>
		matrizTransicao['h'][1] = Estado.ID;  //<ID> ::= h<ID>
		matrizTransicao['h'][6] = Estado.comp1;  //<comp1> ::= h<comp1>
		matrizTransicao['h'][9] = Estado.comp1;  //<comp2> ::= h<comp1>
		matrizTransicao['h'][12] = Estado.UEV;  //<nump1> ::= h<UEV>
		matrizTransicao['h'][13] = Estado.TMF;  //<nump2> ::= h<TMF>
		matrizTransicao['h'][21] = Estado.cadcp1;  //<cadcp1> ::= h<cadcp1>
		matrizTransicao['h'][22] = Estado.cadcp1;  //<cadcp2> ::= h<cadcp1>
		matrizTransicao['i'][0] = Estado.ID;  //<Inicial> ::= i<ID>
		matrizTransicao['i'][1] = Estado.ID;  //<ID> ::= i<ID>
		matrizTransicao['i'][6] = Estado.comp1;  //<comp1> ::= i<comp1>
		matrizTransicao['i'][9] = Estado.comp1;  //<comp2> ::= i<comp1>
		matrizTransicao['i'][12] = Estado.UEV;  //<nump1> ::= i<UEV>
		matrizTransicao['i'][13] = Estado.TMF;  //<nump2> ::= i<TMF>
		matrizTransicao['i'][21] = Estado.cadcp1;  //<cadcp1> ::= i<cadcp1>
		matrizTransicao['i'][22] = Estado.cadcp1;  //<cadcp2> ::= i<cadcp1>
		matrizTransicao['j'][0] = Estado.ID;  //<Inicial> ::= j<ID>
		matrizTransicao['j'][1] = Estado.ID;  //<ID> ::= j<ID>
		matrizTransicao['j'][6] = Estado.comp1;  //<comp1> ::= j<comp1>
		matrizTransicao['j'][9] = Estado.comp1;  //<comp2> ::= j<comp1>
		matrizTransicao['j'][12] = Estado.UEV;  //<nump1> ::= j<UEV>
		matrizTransicao['j'][13] = Estado.TMF;  //<nump2> ::= j<TMF>
		matrizTransicao['j'][21] = Estado.cadcp1;  //<cadcp1> ::= j<cadcp1>
		matrizTransicao['j'][22] = Estado.cadcp1;  //<cadcp2> ::= j<cadcp1>
		matrizTransicao['k'][0] = Estado.ID;  //<Inicial> ::= k<ID>
		matrizTransicao['k'][1] = Estado.ID;  //<ID> ::= k<ID>
		matrizTransicao['k'][6] = Estado.comp1;  //<comp1> ::= k<comp1>
		matrizTransicao['k'][9] = Estado.comp1;  //<comp2> ::= k<comp1>
		matrizTransicao['k'][12] = Estado.UEV;  //<nump1> ::= k<UEV>
		matrizTransicao['k'][13] = Estado.TMF;  //<nump2> ::= k<TMF>
		matrizTransicao['k'][21] = Estado.cadcp1;  //<cadcp1> ::= k<cadcp1>
		matrizTransicao['k'][22] = Estado.cadcp1;  //<cadcp2> ::= k<cadcp1>
		matrizTransicao['l'][0] = Estado.ID;  //<Inicial> ::= l<ID>
		matrizTransicao['l'][1] = Estado.ID;  //<ID> ::= l<ID>
		matrizTransicao['l'][6] = Estado.comp1;  //<comp1> ::= l<comp1>
		matrizTransicao['l'][9] = Estado.comp1;  //<comp2> ::= l<comp1>
		matrizTransicao['l'][12] = Estado.UEV;  //<nump1> ::= l<UEV>
		matrizTransicao['l'][13] = Estado.TMF;  //<nump2> ::= l<TMF>
		matrizTransicao['l'][21] = Estado.cadcp1;  //<cadcp1> ::= l<cadcp1>
		matrizTransicao['l'][22] = Estado.cadcp1;  //<cadcp2> ::= l<cadcp1>
		matrizTransicao['m'][0] = Estado.ID;  //<Inicial> ::= m<ID>
		matrizTransicao['m'][1] = Estado.ID;  //<ID> ::= m<ID>
		matrizTransicao['m'][6] = Estado.comp1;  //<comp1> ::= m<comp1>
		matrizTransicao['m'][9] = Estado.comp1;  //<comp2> ::= m<comp1>
		matrizTransicao['m'][12] = Estado.UEV;  //<nump1> ::= m<UEV>
		matrizTransicao['m'][13] = Estado.TMF;  //<nump2> ::= m<TMF>
		matrizTransicao['m'][21] = Estado.cadcp1;  //<cadcp1> ::= m<cadcp1>
		matrizTransicao['m'][22] = Estado.cadcp1;  //<cadcp2> ::= m<cadcp1>
		matrizTransicao['n'][0] = Estado.ID;  //<Inicial> ::= n<ID>
		matrizTransicao['n'][1] = Estado.ID;  //<ID> ::= n<ID>
		matrizTransicao['n'][6] = Estado.comp1;  //<comp1> ::= n<comp1>
		matrizTransicao['n'][9] = Estado.comp1;  //<comp2> ::= n<comp1>
		matrizTransicao['n'][12] = Estado.UEV;  //<nump1> ::= n<UEV>
		matrizTransicao['n'][13] = Estado.TMF;  //<nump2> ::= n<TMF>
		matrizTransicao['n'][21] = Estado.cadcp1;  //<cadcp1> ::= n<cadcp1>
		matrizTransicao['n'][22] = Estado.cadcp1;  //<cadcp2> ::= n<cadcp1>
		matrizTransicao['o'][0] = Estado.ID;  //<Inicial> ::= o<ID>
		matrizTransicao['o'][1] = Estado.ID;  //<ID> ::= o<ID>
		matrizTransicao['o'][6] = Estado.comp1;  //<comp1> ::= o<comp1>
		matrizTransicao['o'][9] = Estado.comp1;  //<comp2> ::= o<comp1>
		matrizTransicao['o'][12] = Estado.UEV;  //<nump1> ::= o<UEV>
		matrizTransicao['o'][13] = Estado.TMF;  //<nump2> ::= o<TMF>
		matrizTransicao['o'][21] = Estado.cadcp1;  //<cadcp1> ::= o<cadcp1>
		matrizTransicao['o'][22] = Estado.cadcp1;  //<cadcp2> ::= o<cadcp1>
		matrizTransicao['p'][0] = Estado.ID;  //<Inicial> ::= p<ID>
		matrizTransicao['p'][1] = Estado.ID;  //<ID> ::= p<ID>
		matrizTransicao['p'][6] = Estado.comp1;  //<comp1> ::= p<comp1>
		matrizTransicao['p'][9] = Estado.comp1;  //<comp2> ::= p<comp1>
		matrizTransicao['p'][12] = Estado.UEV;  //<nump1> ::= p<UEV>
		matrizTransicao['p'][13] = Estado.TMF;  //<nump2> ::= p<TMF>
		matrizTransicao['p'][21] = Estado.cadcp1;  //<cadcp1> ::= p<cadcp1>
		matrizTransicao['p'][22] = Estado.cadcp1;  //<cadcp2> ::= p<cadcp1>
		matrizTransicao['q'][0] = Estado.ID;  //<Inicial> ::= q<ID>
		matrizTransicao['q'][1] = Estado.ID;  //<ID> ::= q<ID>
		matrizTransicao['q'][6] = Estado.comp1;  //<comp1> ::= q<comp1>
		matrizTransicao['q'][9] = Estado.comp1;  //<comp2> ::= q<comp1>
		matrizTransicao['q'][12] = Estado.UEV;  //<nump1> ::= q<UEV>
		matrizTransicao['q'][13] = Estado.TMF;  //<nump2> ::= q<TMF>
		matrizTransicao['q'][21] = Estado.cadcp1;  //<cadcp1> ::= q<cadcp1>
		matrizTransicao['q'][22] = Estado.cadcp1;  //<cadcp2> ::= q<cadcp1>
		matrizTransicao['r'][0] = Estado.ID;  //<Inicial> ::= r<ID>
		matrizTransicao['r'][1] = Estado.ID;  //<ID> ::= r<ID>
		matrizTransicao['r'][6] = Estado.comp1;  //<comp1> ::= r<comp1>
		matrizTransicao['r'][9] = Estado.comp1;  //<comp2> ::= r<comp1>
		matrizTransicao['r'][12] = Estado.UEV;  //<nump1> ::= r<UEV>
		matrizTransicao['r'][13] = Estado.TMF;  //<nump2> ::= r<TMF>
		matrizTransicao['r'][21] = Estado.cadcp1;  //<cadcp1> ::= r<cadcp1>
		matrizTransicao['r'][22] = Estado.cadcp1;  //<cadcp2> ::= r<cadcp1>
		matrizTransicao['s'][0] = Estado.ID;  //<Inicial> ::= s<ID>
		matrizTransicao['s'][1] = Estado.ID;  //<ID> ::= s<ID>
		matrizTransicao['s'][6] = Estado.comp1;  //<comp1> ::= s<comp1>
		matrizTransicao['s'][9] = Estado.comp1;  //<comp2> ::= s<comp1>
		matrizTransicao['s'][12] = Estado.UEV;  //<nump1> ::= s<UEV>
		matrizTransicao['s'][13] = Estado.TMF;  //<nump2> ::= s<TMF>
		matrizTransicao['s'][21] = Estado.cadcp1;  //<cadcp1> ::= s<cadcp1>
		matrizTransicao['s'][22] = Estado.cadcp1;  //<cadcp2> ::= s<cadcp1>
		matrizTransicao['t'][0] = Estado.ID;  //<Inicial> ::= t<ID>
		matrizTransicao['t'][1] = Estado.ID;  //<ID> ::= t<ID>
		matrizTransicao['t'][6] = Estado.comp1;  //<comp1> ::= t<comp1>
		matrizTransicao['t'][9] = Estado.comp1;  //<comp2> ::= t<comp1>
		matrizTransicao['t'][12] = Estado.UEV;  //<nump1> ::= t<UEV>
		matrizTransicao['t'][13] = Estado.TMF;  //<nump2> ::= t<TMF>
		matrizTransicao['t'][21] = Estado.cadcp1;  //<cadcp1> ::= t<cadcp1>
		matrizTransicao['t'][22] = Estado.cadcp1;  //<cadcp2> ::= t<cadcp1>
		matrizTransicao['u'][0] = Estado.ID;  //<Inicial> ::= u<ID>
		matrizTransicao['u'][1] = Estado.ID;  //<ID> ::= u<ID>
		matrizTransicao['u'][6] = Estado.comp1;  //<comp1> ::= u<comp1>
		matrizTransicao['u'][9] = Estado.comp1;  //<comp2> ::= u<comp1>
		matrizTransicao['u'][12] = Estado.UEV;  //<nump1> ::= u<UEV>
		matrizTransicao['u'][13] = Estado.TMF;  //<nump2> ::= u<TMF>
		matrizTransicao['u'][21] = Estado.cadcp1;  //<cadcp1> ::= u<cadcp1>
		matrizTransicao['u'][22] = Estado.cadcp1;  //<cadcp2> ::= u<cadcp1>
		matrizTransicao['v'][0] = Estado.ID;  //<Inicial> ::= v<ID>
		matrizTransicao['v'][1] = Estado.ID;  //<ID> ::= v<ID>
		matrizTransicao['v'][6] = Estado.comp1;  //<comp1> ::= v<comp1>
		matrizTransicao['v'][9] = Estado.comp1;  //<comp2> ::= v<comp1>
		matrizTransicao['v'][12] = Estado.UEV;  //<nump1> ::= v<UEV>
		matrizTransicao['v'][13] = Estado.TMF;  //<nump2> ::= v<TMF>
		matrizTransicao['v'][21] = Estado.cadcp1;  //<cadcp1> ::= v<cadcp1>
		matrizTransicao['v'][22] = Estado.cadcp1;  //<cadcp2> ::= v<cadcp1>
		matrizTransicao['w'][0] = Estado.ID;  //<Inicial> ::= w<ID>
		matrizTransicao['w'][1] = Estado.ID;  //<ID> ::= w<ID>
		matrizTransicao['w'][6] = Estado.comp1;  //<comp1> ::= w<comp1>
		matrizTransicao['w'][9] = Estado.comp1;  //<comp2> ::= w<comp1>
		matrizTransicao['w'][12] = Estado.UEV;  //<nump1> ::= w<UEV>
		matrizTransicao['w'][13] = Estado.TMF;  //<nump2> ::= w<TMF>
		matrizTransicao['w'][21] = Estado.cadcp1;  //<cadcp1> ::= w<cadcp1>
		matrizTransicao['w'][22] = Estado.cadcp1;  //<cadcp2> ::= w<cadcp1>
		matrizTransicao['x'][0] = Estado.ID;  //<Inicial> ::= x<ID>
		matrizTransicao['x'][1] = Estado.ID;  //<ID> ::= x<ID>
		matrizTransicao['x'][6] = Estado.comp1;  //<comp1> ::= x<comp1>
		matrizTransicao['x'][9] = Estado.comp1;  //<comp2> ::= x<comp1>
		matrizTransicao['x'][12] = Estado.UEV;  //<nump1> ::= x<UEV>
		matrizTransicao['x'][13] = Estado.TMF;  //<nump2> ::= x<TMF>
		matrizTransicao['x'][21] = Estado.cadcp1;  //<cadcp1> ::= x<cadcp1>
		matrizTransicao['x'][22] = Estado.cadcp1;  //<cadcp2> ::= x<cadcp1>
		matrizTransicao['y'][0] = Estado.ID;  //<Inicial> ::= y<ID>
		matrizTransicao['y'][1] = Estado.ID;  //<ID> ::= y<ID>
		matrizTransicao['y'][6] = Estado.comp1;  //<comp1> ::= y<comp1>
		matrizTransicao['y'][9] = Estado.comp1;  //<comp2> ::= y<comp1>
		matrizTransicao['y'][12] = Estado.UEV;  //<nump1> ::= y<UEV>
		matrizTransicao['y'][13] = Estado.TMF;  //<nump2> ::= y<TMF>
		matrizTransicao['y'][21] = Estado.cadcp1;  //<cadcp1> ::= y<cadcp1>
		matrizTransicao['y'][22] = Estado.cadcp1;  //<cadcp2> ::= y<cadcp1>
		matrizTransicao['z'][0] = Estado.ID;  //<Inicial> ::= z<ID>
		matrizTransicao['z'][1] = Estado.ID;  //<ID> ::= z<ID>
		matrizTransicao['z'][6] = Estado.comp1;  //<comp1> ::= z<comp1>
		matrizTransicao['z'][9] = Estado.comp1;  //<comp2> ::= z<comp1>
		matrizTransicao['z'][12] = Estado.UEV;  //<nump1> ::= z<UEV>
		matrizTransicao['z'][13] = Estado.TMF;  //<nump2> ::= z<TMF>
		matrizTransicao['z'][21] = Estado.cadcp1;  //<cadcp1> ::= z<cadcp1>
		matrizTransicao['z'][22] = Estado.cadcp1;  //<cadcp2> ::= z<cadcp1>
		matrizTransicao['A'][0] = Estado.ID;  //<Inicial> ::= A<ID>
		matrizTransicao['A'][1] = Estado.ID;  //<ID> ::= A<ID>
		matrizTransicao['A'][6] = Estado.comp1;  //<comp1> ::= A<comp1>
		matrizTransicao['A'][9] = Estado.comp1;  //<comp2> ::= A<comp1>
		matrizTransicao['A'][12] = Estado.UEV;  //<nump1> ::= A<UEV>
		matrizTransicao['A'][13] = Estado.TMF;  //<nump2> ::= A<TMF>
		matrizTransicao['A'][21] = Estado.cadcp1;  //<cadcp1> ::= A<cadcp1>
		matrizTransicao['A'][22] = Estado.cadcp1;  //<cadcp2> ::= A<cadcp1>
		matrizTransicao['B'][0] = Estado.ID;  //<Inicial> ::= B<ID>
		matrizTransicao['B'][1] = Estado.ID;  //<ID> ::= B<ID>
		matrizTransicao['B'][6] = Estado.comp1;  //<comp1> ::= B<comp1>
		matrizTransicao['B'][9] = Estado.comp1;  //<comp2> ::= B<comp1>
		matrizTransicao['B'][12] = Estado.UEV;  //<nump1> ::= B<UEV>
		matrizTransicao['B'][13] = Estado.TMF;  //<nump2> ::= B<TMF>
		matrizTransicao['B'][21] = Estado.cadcp1;  //<cadcp1> ::= B<cadcp1>
		matrizTransicao['B'][22] = Estado.cadcp1;  //<cadcp2> ::= B<cadcp1>
		matrizTransicao['C'][0] = Estado.ID;  //<Inicial> ::= C<ID>
		matrizTransicao['C'][1] = Estado.ID;  //<ID> ::= C<ID>
		matrizTransicao['C'][6] = Estado.comp1;  //<comp1> ::= C<comp1>
		matrizTransicao['C'][9] = Estado.comp1;  //<comp2> ::= C<comp1>
		matrizTransicao['C'][12] = Estado.UEV;  //<nump1> ::= C<UEV>
		matrizTransicao['C'][13] = Estado.TMF;  //<nump2> ::= C<TMF>
		matrizTransicao['C'][21] = Estado.cadcp1;  //<cadcp1> ::= C<cadcp1>
		matrizTransicao['C'][22] = Estado.cadcp1;  //<cadcp2> ::= C<cadcp1>
		matrizTransicao['D'][0] = Estado.ID;  //<Inicial> ::= D<ID>
		matrizTransicao['D'][1] = Estado.ID;  //<ID> ::= D<ID>
		matrizTransicao['D'][6] = Estado.comp1;  //<comp1> ::= D<comp1>
		matrizTransicao['D'][9] = Estado.comp1;  //<comp2> ::= D<comp1>
		matrizTransicao['D'][12] = Estado.UEV;  //<nump1> ::= D<UEV>
		matrizTransicao['D'][13] = Estado.TMF;  //<nump2> ::= D<TMF>
		matrizTransicao['D'][21] = Estado.cadcp1;  //<cadcp1> ::= D<cadcp1>
		matrizTransicao['D'][22] = Estado.cadcp1;  //<cadcp2> ::= D<cadcp1>
		matrizTransicao['E'][0] = Estado.ID;  //<Inicial> ::= E<ID>
		matrizTransicao['E'][1] = Estado.ID;  //<ID> ::= E<ID>
		matrizTransicao['E'][6] = Estado.comp1;  //<comp1> ::= E<comp1>
		matrizTransicao['E'][9] = Estado.comp1;  //<comp2> ::= E<comp1>
		matrizTransicao['E'][12] = Estado.UEV;  //<nump1> ::= E<UEV>
		matrizTransicao['E'][13] = Estado.TMF;  //<nump2> ::= E<TMF>
		matrizTransicao['E'][21] = Estado.cadcp1;  //<cadcp1> ::= E<cadcp1>
		matrizTransicao['E'][22] = Estado.cadcp1;  //<cadcp2> ::= E<cadcp1>
		matrizTransicao['F'][0] = Estado.ID;  //<Inicial> ::= F<ID>
		matrizTransicao['F'][1] = Estado.ID;  //<ID> ::= F<ID>
		matrizTransicao['F'][6] = Estado.comp1;  //<comp1> ::= F<comp1>
		matrizTransicao['F'][9] = Estado.comp1;  //<comp2> ::= F<comp1>
		matrizTransicao['F'][12] = Estado.UEV;  //<nump1> ::= F<UEV>
		matrizTransicao['F'][13] = Estado.TMF;  //<nump2> ::= F<TMF>
		matrizTransicao['F'][21] = Estado.cadcp1;  //<cadcp1> ::= F<cadcp1>
		matrizTransicao['F'][22] = Estado.cadcp1;  //<cadcp2> ::= F<cadcp1>
		matrizTransicao['G'][0] = Estado.ID;  //<Inicial> ::= G<ID>
		matrizTransicao['G'][1] = Estado.ID;  //<ID> ::= G<ID>
		matrizTransicao['G'][6] = Estado.comp1;  //<comp1> ::= G<comp1>
		matrizTransicao['G'][9] = Estado.comp1;  //<comp2> ::= G<comp1>
		matrizTransicao['G'][12] = Estado.UEV;  //<nump1> ::= G<UEV>
		matrizTransicao['G'][13] = Estado.TMF;  //<nump2> ::= G<TMF>
		matrizTransicao['G'][21] = Estado.cadcp1;  //<cadcp1> ::= G<cadcp1>
		matrizTransicao['G'][22] = Estado.cadcp1;  //<cadcp2> ::= G<cadcp1>
		matrizTransicao['H'][0] = Estado.ID;  //<Inicial> ::= H<ID>
		matrizTransicao['H'][1] = Estado.ID;  //<ID> ::= H<ID>
		matrizTransicao['H'][6] = Estado.comp1;  //<comp1> ::= H<comp1>
		matrizTransicao['H'][9] = Estado.comp1;  //<comp2> ::= H<comp1>
		matrizTransicao['H'][12] = Estado.UEV;  //<nump1> ::= H<UEV>
		matrizTransicao['H'][13] = Estado.TMF;  //<nump2> ::= H<TMF>
		matrizTransicao['H'][21] = Estado.cadcp1;  //<cadcp1> ::= H<cadcp1>
		matrizTransicao['H'][22] = Estado.cadcp1;  //<cadcp2> ::= H<cadcp1>
		matrizTransicao['I'][0] = Estado.ID;  //<Inicial> ::= I<ID>
		matrizTransicao['I'][1] = Estado.ID;  //<ID> ::= I<ID>
		matrizTransicao['I'][6] = Estado.comp1;  //<comp1> ::= I<comp1>
		matrizTransicao['I'][9] = Estado.comp1;  //<comp2> ::= I<comp1>
		matrizTransicao['I'][12] = Estado.UEV;  //<nump1> ::= I<UEV>
		matrizTransicao['I'][13] = Estado.TMF;  //<nump2> ::= I<TMF>
		matrizTransicao['I'][21] = Estado.cadcp1;  //<cadcp1> ::= I<cadcp1>
		matrizTransicao['I'][22] = Estado.cadcp1;  //<cadcp2> ::= I<cadcp1>
		matrizTransicao['J'][0] = Estado.ID;  //<Inicial> ::= J<ID>
		matrizTransicao['J'][1] = Estado.ID;  //<ID> ::= J<ID>
		matrizTransicao['J'][6] = Estado.comp1;  //<comp1> ::= J<comp1>
		matrizTransicao['J'][9] = Estado.comp1;  //<comp2> ::= J<comp1>
		matrizTransicao['J'][12] = Estado.UEV;  //<nump1> ::= J<UEV>
		matrizTransicao['J'][13] = Estado.TMF;  //<nump2> ::= J<TMF>
		matrizTransicao['J'][21] = Estado.cadcp1;  //<cadcp1> ::= J<cadcp1>
		matrizTransicao['J'][22] = Estado.cadcp1;  //<cadcp2> ::= J<cadcp1>
		matrizTransicao['K'][0] = Estado.ID;  //<Inicial> ::= K<ID>
		matrizTransicao['K'][1] = Estado.ID;  //<ID> ::= K<ID>
		matrizTransicao['K'][6] = Estado.comp1;  //<comp1> ::= K<comp1>
		matrizTransicao['K'][9] = Estado.comp1;  //<comp2> ::= K<comp1>
		matrizTransicao['K'][12] = Estado.UEV;  //<nump1> ::= K<UEV>
		matrizTransicao['K'][13] = Estado.TMF;  //<nump2> ::= K<TMF>
		matrizTransicao['K'][21] = Estado.cadcp1;  //<cadcp1> ::= K<cadcp1>
		matrizTransicao['K'][22] = Estado.cadcp1;  //<cadcp2> ::= K<cadcp1>
		matrizTransicao['L'][0] = Estado.ID;  //<Inicial> ::= L<ID>
		matrizTransicao['L'][1] = Estado.ID;  //<ID> ::= L<ID>
		matrizTransicao['L'][6] = Estado.comp1;  //<comp1> ::= L<comp1>
		matrizTransicao['L'][9] = Estado.comp1;  //<comp2> ::= L<comp1>
		matrizTransicao['L'][12] = Estado.UEV;  //<nump1> ::= L<UEV>
		matrizTransicao['L'][13] = Estado.TMF;  //<nump2> ::= L<TMF>
		matrizTransicao['L'][21] = Estado.cadcp1;  //<cadcp1> ::= L<cadcp1>
		matrizTransicao['L'][22] = Estado.cadcp1;  //<cadcp2> ::= L<cadcp1>
		matrizTransicao['M'][0] = Estado.ID;  //<Inicial> ::= M<ID>
		matrizTransicao['M'][1] = Estado.ID;  //<ID> ::= M<ID>
		matrizTransicao['M'][6] = Estado.comp1;  //<comp1> ::= M<comp1>
		matrizTransicao['M'][9] = Estado.comp1;  //<comp2> ::= M<comp1>
		matrizTransicao['M'][12] = Estado.UEV;  //<nump1> ::= M<UEV>
		matrizTransicao['M'][13] = Estado.TMF;  //<nump2> ::= M<TMF>
		matrizTransicao['M'][21] = Estado.cadcp1;  //<cadcp1> ::= M<cadcp1>
		matrizTransicao['M'][22] = Estado.cadcp1;  //<cadcp2> ::= M<cadcp1>
		matrizTransicao['N'][0] = Estado.ID;  //<Inicial> ::= N<ID>
		matrizTransicao['N'][1] = Estado.ID;  //<ID> ::= N<ID>
		matrizTransicao['N'][6] = Estado.comp1;  //<comp1> ::= N<comp1>
		matrizTransicao['N'][9] = Estado.comp1;  //<comp2> ::= N<comp1>
		matrizTransicao['N'][12] = Estado.UEV;  //<nump1> ::= N<UEV>
		matrizTransicao['N'][13] = Estado.TMF;  //<nump2> ::= N<TMF>
		matrizTransicao['N'][21] = Estado.cadcp1;  //<cadcp1> ::= N<cadcp1>
		matrizTransicao['N'][22] = Estado.cadcp1;  //<cadcp2> ::= N<cadcp1>
		matrizTransicao['O'][0] = Estado.ID;  //<Inicial> ::= O<ID>
		matrizTransicao['O'][1] = Estado.ID;  //<ID> ::= O<ID>
		matrizTransicao['O'][6] = Estado.comp1;  //<comp1> ::= O<comp1>
		matrizTransicao['O'][9] = Estado.comp1;  //<comp2> ::= O<comp1>
		matrizTransicao['O'][12] = Estado.UEV;  //<nump1> ::= O<UEV>
		matrizTransicao['O'][13] = Estado.TMF;  //<nump2> ::= O<TMF>
		matrizTransicao['O'][21] = Estado.cadcp1;  //<cadcp1> ::= O<cadcp1>
		matrizTransicao['O'][22] = Estado.cadcp1;  //<cadcp2> ::= O<cadcp1>
		matrizTransicao['P'][0] = Estado.ID;  //<Inicial> ::= P<ID>
		matrizTransicao['P'][1] = Estado.ID;  //<ID> ::= P<ID>
		matrizTransicao['P'][6] = Estado.comp1;  //<comp1> ::= P<comp1>
		matrizTransicao['P'][9] = Estado.comp1;  //<comp2> ::= P<comp1>
		matrizTransicao['P'][12] = Estado.UEV;  //<nump1> ::= P<UEV>
		matrizTransicao['P'][13] = Estado.TMF;  //<nump2> ::= P<TMF>
		matrizTransicao['P'][21] = Estado.cadcp1;  //<cadcp1> ::= P<cadcp1>
		matrizTransicao['P'][22] = Estado.cadcp1;  //<cadcp2> ::= P<cadcp1>
		matrizTransicao['Q'][0] = Estado.ID;  //<Inicial> ::= Q<ID>
		matrizTransicao['Q'][1] = Estado.ID;  //<ID> ::= Q<ID>
		matrizTransicao['Q'][6] = Estado.comp1;  //<comp1> ::= Q<comp1>
		matrizTransicao['Q'][9] = Estado.comp1;  //<comp2> ::= Q<comp1>
		matrizTransicao['Q'][12] = Estado.UEV;  //<nump1> ::= Q<UEV>
		matrizTransicao['Q'][13] = Estado.TMF;  //<nump2> ::= Q<TMF>
		matrizTransicao['Q'][21] = Estado.cadcp1;  //<cadcp1> ::= Q<cadcp1>
		matrizTransicao['Q'][22] = Estado.cadcp1;  //<cadcp2> ::= Q<cadcp1>
		matrizTransicao['R'][0] = Estado.ID;  //<Inicial> ::= R<ID>
		matrizTransicao['R'][1] = Estado.ID;  //<ID> ::= R<ID>
		matrizTransicao['R'][6] = Estado.comp1;  //<comp1> ::= R<comp1>
		matrizTransicao['R'][9] = Estado.comp1;  //<comp2> ::= R<comp1>
		matrizTransicao['R'][12] = Estado.UEV;  //<nump1> ::= R<UEV>
		matrizTransicao['R'][13] = Estado.TMF;  //<nump2> ::= R<TMF>
		matrizTransicao['R'][21] = Estado.cadcp1;  //<cadcp1> ::= R<cadcp1>
		matrizTransicao['R'][22] = Estado.cadcp1;  //<cadcp2> ::= R<cadcp1>
		matrizTransicao['S'][0] = Estado.ID;  //<Inicial> ::= S<ID>
		matrizTransicao['S'][1] = Estado.ID;  //<ID> ::= S<ID>
		matrizTransicao['S'][6] = Estado.comp1;  //<comp1> ::= S<comp1>
		matrizTransicao['S'][9] = Estado.comp1;  //<comp2> ::= S<comp1>
		matrizTransicao['S'][12] = Estado.UEV;  //<nump1> ::= S<UEV>
		matrizTransicao['S'][13] = Estado.TMF;  //<nump2> ::= S<TMF>
		matrizTransicao['S'][21] = Estado.cadcp1;  //<cadcp1> ::= S<cadcp1>
		matrizTransicao['S'][22] = Estado.cadcp1;  //<cadcp2> ::= S<cadcp1>
		matrizTransicao['T'][0] = Estado.ID;  //<Inicial> ::= T<ID>
		matrizTransicao['T'][1] = Estado.ID;  //<ID> ::= T<ID>
		matrizTransicao['T'][6] = Estado.comp1;  //<comp1> ::= T<comp1>
		matrizTransicao['T'][9] = Estado.comp1;  //<comp2> ::= T<comp1>
		matrizTransicao['T'][12] = Estado.UEV;  //<nump1> ::= T<UEV>
		matrizTransicao['T'][13] = Estado.TMF;  //<nump2> ::= T<TMF>
		matrizTransicao['T'][21] = Estado.cadcp1;  //<cadcp1> ::= T<cadcp1>
		matrizTransicao['T'][22] = Estado.cadcp1;  //<cadcp2> ::= T<cadcp1>
		matrizTransicao['U'][0] = Estado.ID;  //<Inicial> ::= U<ID>
		matrizTransicao['U'][1] = Estado.ID;  //<ID> ::= U<ID>
		matrizTransicao['U'][6] = Estado.comp1;  //<comp1> ::= U<comp1>
		matrizTransicao['U'][9] = Estado.comp1;  //<comp2> ::= U<comp1>
		matrizTransicao['U'][12] = Estado.UEV;  //<nump1> ::= U<UEV>
		matrizTransicao['U'][13] = Estado.TMF;  //<nump2> ::= U<TMF>
		matrizTransicao['U'][21] = Estado.cadcp1;  //<cadcp1> ::= U<cadcp1>
		matrizTransicao['U'][22] = Estado.cadcp1;  //<cadcp2> ::= U<cadcp1>
		matrizTransicao['V'][0] = Estado.ID;  //<Inicial> ::= V<ID>
		matrizTransicao['V'][1] = Estado.ID;  //<ID> ::= V<ID>
		matrizTransicao['V'][6] = Estado.comp1;  //<comp1> ::= V<comp1>
		matrizTransicao['V'][9] = Estado.comp1;  //<comp2> ::= V<comp1>
		matrizTransicao['V'][12] = Estado.UEV;  //<nump1> ::= V<UEV>
		matrizTransicao['V'][13] = Estado.TMF;  //<nump2> ::= V<TMF>
		matrizTransicao['V'][21] = Estado.cadcp1;  //<cadcp1> ::= V<cadcp1>
		matrizTransicao['V'][22] = Estado.cadcp1;  //<cadcp2> ::= V<cadcp1>
		matrizTransicao['W'][0] = Estado.ID;  //<Inicial> ::= W<ID>
		matrizTransicao['W'][1] = Estado.ID;  //<ID> ::= W<ID>
		matrizTransicao['W'][6] = Estado.comp1;  //<comp1> ::= W<comp1>
		matrizTransicao['W'][9] = Estado.comp1;  //<comp2> ::= W<comp1>
		matrizTransicao['W'][12] = Estado.UEV;  //<nump1> ::= W<UEV>
		matrizTransicao['W'][13] = Estado.TMF;  //<nump2> ::= W<TMF>
		matrizTransicao['W'][21] = Estado.cadcp1;  //<cadcp1> ::= W<cadcp1>
		matrizTransicao['W'][22] = Estado.cadcp1;  //<cadcp2> ::= W<cadcp1>
		matrizTransicao['X'][0] = Estado.ID;  //<Inicial> ::= X<ID>
		matrizTransicao['X'][1] = Estado.ID;  //<ID> ::= X<ID>
		matrizTransicao['X'][6] = Estado.comp1;  //<comp1> ::= X<comp1>
		matrizTransicao['X'][9] = Estado.comp1;  //<comp2> ::= X<comp1>
		matrizTransicao['X'][12] = Estado.UEV;  //<nump1> ::= X<UEV>
		matrizTransicao['X'][13] = Estado.TMF;  //<nump2> ::= X<TMF>
		matrizTransicao['X'][21] = Estado.cadcp1;  //<cadcp1> ::= X<cadcp1>
		matrizTransicao['X'][22] = Estado.cadcp1;  //<cadcp2> ::= X<cadcp1>
		matrizTransicao['Y'][0] = Estado.ID;  //<Inicial> ::= Y<ID>
		matrizTransicao['Y'][1] = Estado.ID;  //<ID> ::= Y<ID>
		matrizTransicao['Y'][6] = Estado.comp1;  //<comp1> ::= Y<comp1>
		matrizTransicao['Y'][9] = Estado.comp1;  //<comp2> ::= Y<comp1>
		matrizTransicao['Y'][12] = Estado.UEV;  //<nump1> ::= Y<UEV>
		matrizTransicao['Y'][13] = Estado.TMF;  //<nump2> ::= Y<TMF>
		matrizTransicao['Y'][21] = Estado.cadcp1;  //<cadcp1> ::= Y<cadcp1>
		matrizTransicao['Y'][22] = Estado.cadcp1;  //<cadcp2> ::= Y<cadcp1>
		matrizTransicao['Z'][0] = Estado.ID;  //<Inicial> ::= Z<ID>
		matrizTransicao['Z'][1] = Estado.ID;  //<ID> ::= Z<ID>
		matrizTransicao['Z'][6] = Estado.comp1;  //<comp1> ::= Z<comp1>
		matrizTransicao['Z'][9] = Estado.comp1;  //<comp2> ::= Z<comp1>
		matrizTransicao['Z'][12] = Estado.UEV;  //<nump1> ::= Z<UEV>
		matrizTransicao['Z'][13] = Estado.TMF;  //<nump2> ::= Z<TMF>
		matrizTransicao['Z'][21] = Estado.cadcp1;  //<cadcp1> ::= Z<cadcp1>
		matrizTransicao['Z'][22] = Estado.cadcp1;  //<cadcp2> ::= Z<cadcp1>
		matrizTransicao[' '][0] = Estado.ID;  //<Inicial> ::=  <ID>
		matrizTransicao[' '][6] = Estado.comp1;  //<comp1> ::=  <comp1>
		matrizTransicao[' '][9] = Estado.comp1;  //<comp2> ::=  <comp1>
		matrizTransicao[' '][12] = Estado.nump1;  //<nump1> ::=  <nump1>
		matrizTransicao[' '][13] = Estado.TMF;  //<nump2> ::=  <TMF>
		matrizTransicao[' '][17] = Estado.nump1;  //<OPAR3> ::=  <nump1>
		matrizTransicao[' '][21] = Estado.cadcp1;  //<cadcp1> ::=  <cadcp1>
		matrizTransicao[' '][22] = Estado.cadcp1;  //<cadcp2> ::=  <cadcp1>
		matrizTransicao['0'][1] = Estado.ID;  //<ID> ::= 0<ID>
		matrizTransicao['1'][0] = Estado.NUM;  //<Inicial> ::= 1<NUM>
		matrizTransicao['1'][1] = Estado.ID;  //<ID> ::= 1<ID>
		matrizTransicao['1'][6] = Estado.comp1;  //<comp1> ::= 1<comp1>
		matrizTransicao['1'][9] = Estado.comp1;  //<comp2> ::= 1<comp1>
		matrizTransicao['1'][11] = Estado.NUM;  //<NUM> ::= 1<NUM>
		matrizTransicao['1'][12] = Estado.NUM;  //<nump1> ::= 1<NUM>
		matrizTransicao['1'][13] = Estado.NUM2;  //<nump2> ::= 1<NUM2>
		matrizTransicao['1'][14] = Estado.NUM2;  //<NUM2> ::= 1<NUM2>
		matrizTransicao['1'][17] = Estado.NUM;  //<OPAR3> ::= 1<NUM>
		matrizTransicao['1'][21] = Estado.cadcp1;  //<cadcp1> ::= 1<cadcp1>
		matrizTransicao['1'][22] = Estado.cadcp1;  //<cadcp2> ::= 1<cadcp1>
		matrizTransicao['2'][0] = Estado.NUM;  //<Inicial> ::= 2<NUM>
		matrizTransicao['2'][1] = Estado.ID;  //<ID> ::= 2<ID>
		matrizTransicao['2'][6] = Estado.comp1;  //<comp1> ::= 2<comp1>
		matrizTransicao['2'][9] = Estado.comp1;  //<comp2> ::= 2<comp1>
		matrizTransicao['2'][11] = Estado.NUM;  //<NUM> ::= 2<NUM>
		matrizTransicao['2'][12] = Estado.NUM;  //<nump1> ::= 2<NUM>
		matrizTransicao['2'][13] = Estado.NUM2;  //<nump2> ::= 2<NUM2>
		matrizTransicao['2'][14] = Estado.NUM2;  //<NUM2> ::= 2<NUM2>
		matrizTransicao['2'][17] = Estado.NUM;  //<OPAR3> ::= 2<NUM>
		matrizTransicao['2'][21] = Estado.cadcp1;  //<cadcp1> ::= 2<cadcp1>
		matrizTransicao['2'][22] = Estado.cadcp1;  //<cadcp2> ::= 2<cadcp1>
		matrizTransicao['3'][0] = Estado.NUM;  //<Inicial> ::= 3<NUM>
		matrizTransicao['3'][1] = Estado.ID;  //<ID> ::= 3<ID>
		matrizTransicao['3'][6] = Estado.comp1;  //<comp1> ::= 3<comp1>
		matrizTransicao['3'][9] = Estado.comp1;  //<comp2> ::= 3<comp1>
		matrizTransicao['3'][11] = Estado.NUM;  //<NUM> ::= 3<NUM>
		matrizTransicao['3'][12] = Estado.NUM;  //<nump1> ::= 3<NUM>
		matrizTransicao['3'][13] = Estado.NUM2;  //<nump2> ::= 3<NUM2>
		matrizTransicao['3'][14] = Estado.NUM2;  //<NUM2> ::= 3<NUM2>
		matrizTransicao['3'][17] = Estado.NUM;  //<OPAR3> ::= 3<NUM>
		matrizTransicao['3'][21] = Estado.cadcp1;  //<cadcp1> ::= 3<cadcp1>
		matrizTransicao['3'][22] = Estado.cadcp1;  //<cadcp2> ::= 3<cadcp1>
		matrizTransicao['4'][0] = Estado.NUM;  //<Inicial> ::= 4<NUM>
		matrizTransicao['4'][1] = Estado.ID;  //<ID> ::= 4<ID>
		matrizTransicao['4'][6] = Estado.comp1;  //<comp1> ::= 4<comp1>
		matrizTransicao['4'][9] = Estado.comp1;  //<comp2> ::= 4<comp1>
		matrizTransicao['4'][11] = Estado.NUM;  //<NUM> ::= 4<NUM>
		matrizTransicao['4'][12] = Estado.NUM;  //<nump1> ::= 4<NUM>
		matrizTransicao['4'][13] = Estado.NUM2;  //<nump2> ::= 4<NUM2>
		matrizTransicao['4'][14] = Estado.NUM2;  //<NUM2> ::= 4<NUM2>
		matrizTransicao['4'][17] = Estado.NUM;  //<OPAR3> ::= 4<NUM>
		matrizTransicao['4'][21] = Estado.cadcp1;  //<cadcp1> ::= 4<cadcp1>
		matrizTransicao['4'][22] = Estado.cadcp1;  //<cadcp2> ::= 4<cadcp1>
		matrizTransicao['5'][0] = Estado.NUM;  //<Inicial> ::= 5<NUM>
		matrizTransicao['5'][1] = Estado.ID;  //<ID> ::= 5<ID>
		matrizTransicao['5'][6] = Estado.comp1;  //<comp1> ::= 5<comp1>
		matrizTransicao['5'][9] = Estado.comp1;  //<comp2> ::= 5<comp1>
		matrizTransicao['5'][11] = Estado.NUM;  //<NUM> ::= 5<NUM>
		matrizTransicao['5'][12] = Estado.NUM;  //<nump1> ::= 5<NUM>
		matrizTransicao['5'][13] = Estado.NUM2;  //<nump2> ::= 5<NUM2>
		matrizTransicao['5'][14] = Estado.NUM2;  //<NUM2> ::= 5<NUM2>
		matrizTransicao['5'][17] = Estado.NUM;  //<OPAR3> ::= 5<NUM>
		matrizTransicao['5'][21] = Estado.cadcp1;  //<cadcp1> ::= 5<cadcp1>
		matrizTransicao['5'][22] = Estado.cadcp1;  //<cadcp2> ::= 5<cadcp1>
		matrizTransicao['6'][0] = Estado.NUM;  //<Inicial> ::= 6<NUM>
		matrizTransicao['6'][1] = Estado.ID;  //<ID> ::= 6<ID>
		matrizTransicao['6'][6] = Estado.comp1;  //<comp1> ::= 6<comp1>
		matrizTransicao['6'][9] = Estado.comp1;  //<comp2> ::= 6<comp1>
		matrizTransicao['6'][11] = Estado.NUM;  //<NUM> ::= 6<NUM>
		matrizTransicao['6'][12] = Estado.NUM;  //<nump1> ::= 6<NUM>
		matrizTransicao['6'][13] = Estado.NUM2;  //<nump2> ::= 6<NUM2>
		matrizTransicao['6'][14] = Estado.NUM2;  //<NUM2> ::= 6<NUM2>
		matrizTransicao['6'][17] = Estado.NUM;  //<OPAR3> ::= 6<NUM>
		matrizTransicao['6'][21] = Estado.cadcp1;  //<cadcp1> ::= 6<cadcp1>
		matrizTransicao['6'][22] = Estado.cadcp1;  //<cadcp2> ::= 6<cadcp1>
		matrizTransicao['7'][0] = Estado.NUM;  //<Inicial> ::= 7<NUM>
		matrizTransicao['7'][1] = Estado.ID;  //<ID> ::= 7<ID>
		matrizTransicao['7'][6] = Estado.comp1;  //<comp1> ::= 7<comp1>
		matrizTransicao['7'][9] = Estado.comp1;  //<comp2> ::= 7<comp1>
		matrizTransicao['7'][11] = Estado.NUM;  //<NUM> ::= 7<NUM>
		matrizTransicao['7'][12] = Estado.NUM;  //<nump1> ::= 7<NUM>
		matrizTransicao['7'][13] = Estado.NUM2;  //<nump2> ::= 7<NUM2>
		matrizTransicao['7'][14] = Estado.NUM2;  //<NUM2> ::= 7<NUM2>
		matrizTransicao['7'][17] = Estado.NUM;  //<OPAR3> ::= 7<NUM>
		matrizTransicao['7'][21] = Estado.cadcp1;  //<cadcp1> ::= 7<cadcp1>
		matrizTransicao['7'][22] = Estado.cadcp1;  //<cadcp2> ::= 7<cadcp1>
		matrizTransicao['8'][0] = Estado.NUM;  //<Inicial> ::= 8<NUM>
		matrizTransicao['8'][1] = Estado.ID;  //<ID> ::= 8<ID>
		matrizTransicao['8'][6] = Estado.comp1;  //<comp1> ::= 8<comp1>
		matrizTransicao['8'][9] = Estado.comp1;  //<comp2> ::= 8<comp1>
		matrizTransicao['8'][11] = Estado.NUM;  //<NUM> ::= 8<NUM>
		matrizTransicao['8'][12] = Estado.NUM;  //<nump1> ::= 8<NUM>
		matrizTransicao['8'][13] = Estado.NUM2;  //<nump2> ::= 8<NUM2>
		matrizTransicao['8'][14] = Estado.NUM2;  //<NUM2> ::= 8<NUM2>
		matrizTransicao['8'][17] = Estado.NUM;  //<OPAR3> ::= 8<NUM>
		matrizTransicao['8'][21] = Estado.cadcp1;  //<cadcp1> ::= 8<cadcp1>
		matrizTransicao['8'][22] = Estado.cadcp1;  //<cadcp2> ::= 8<cadcp1>
		matrizTransicao['9'][0] = Estado.NUM;  //<Inicial> ::= 9<NUM>
		matrizTransicao['9'][1] = Estado.ID;  //<ID> ::= 9<ID>
		matrizTransicao['9'][6] = Estado.comp1;  //<comp1> ::= 9<comp1>
		matrizTransicao['9'][9] = Estado.comp1;  //<comp2> ::= 9<comp1>
		matrizTransicao['9'][11] = Estado.NUM;  //<NUM> ::= 9<NUM>
		matrizTransicao['9'][12] = Estado.NUM;  //<nump1> ::= 9<NUM>
		matrizTransicao['9'][13] = Estado.NUM2;  //<nump2> ::= 9<NUM2>
		matrizTransicao['9'][14] = Estado.NUM2;  //<NUM2> ::= 9<NUM2>
		matrizTransicao['9'][17] = Estado.NUM;  //<OPAR3> ::= 9<NUM>
		matrizTransicao['9'][21] = Estado.cadcp1;  //<cadcp1> ::= 9<cadcp1>
		matrizTransicao['9'][22] = Estado.cadcp1;  //<cadcp2> ::= 9<cadcp1>
		matrizTransicao['_'][1] = Estado.ID;  //<ID> ::= _<ID>
		matrizTransicao['_'][6] = Estado.comp1;  //<comp1> ::= _<comp1>
		matrizTransicao['_'][9] = Estado.comp1;  //<comp2> ::= _<comp1>
		matrizTransicao['_'][12] = Estado.UEV;  //<nump1> ::= _<UEV>
		matrizTransicao['_'][13] = Estado.TMF;  //<nump2> ::= _<TMF>
		matrizTransicao['_'][21] = Estado.cadcp1;  //<cadcp1> ::= _<cadcp1>
		matrizTransicao['_'][22] = Estado.cadcp1;  //<cadcp2> ::= _<cadcp1>
		matrizTransicao[';'][0] = Estado.DEL;  //<Inicial> ::= ;<DEL>
		matrizTransicao[';'][6] = Estado.comp1;  //<comp1> ::= ;<comp1>
		matrizTransicao[';'][9] = Estado.comp1;  //<comp2> ::= ;<comp1>
		matrizTransicao[';'][12] = Estado.UEV;  //<nump1> ::= ;<UEV>
		matrizTransicao[';'][13] = Estado.TMF;  //<nump2> ::= ;<TMF>
		matrizTransicao[','][0] = Estado.DEL;  //<Inicial> ::= ,<DEL>
		matrizTransicao[','][6] = Estado.comp1;  //<comp1> ::= ,<comp1>
		matrizTransicao[','][9] = Estado.comp1;  //<comp2> ::= ,<comp1>
		matrizTransicao[','][12] = Estado.UEV;  //<nump1> ::= ,<UEV>
		matrizTransicao[','][13] = Estado.TMF;  //<nump2> ::= ,<TMF>
		matrizTransicao['('][0] = Estado.DEL;  //<Inicial> ::= (<DEL>
		matrizTransicao['('][6] = Estado.comp1;  //<comp1> ::= (<comp1>
		matrizTransicao['('][9] = Estado.comp1;  //<comp2> ::= (<comp1>
		matrizTransicao['('][12] = Estado.UEV;  //<nump1> ::= (<UEV>
		matrizTransicao['('][13] = Estado.TMF;  //<nump2> ::= (<TMF>
		matrizTransicao[')'][0] = Estado.DEL;  //<Inicial> ::= )<DEL>
		matrizTransicao[')'][6] = Estado.comp1;  //<comp1> ::= )<comp1>
		matrizTransicao[')'][9] = Estado.comp1;  //<comp2> ::= )<comp1>
		matrizTransicao[')'][12] = Estado.UEV;  //<nump1> ::= )<UEV>
		matrizTransicao[')'][13] = Estado.TMF;  //<nump2> ::= )<TMF>
		matrizTransicao['['][0] = Estado.DEL;  //<Inicial> ::= [<DEL>
		matrizTransicao['['][6] = Estado.comp1;  //<comp1> ::= [<comp1>
		matrizTransicao['['][9] = Estado.comp1;  //<comp2> ::= [<comp1>
		matrizTransicao['['][12] = Estado.UEV;  //<nump1> ::= [<UEV>
		matrizTransicao['['][13] = Estado.TMF;  //<nump2> ::= [<TMF>
		matrizTransicao[']'][0] = Estado.DEL;  //<Inicial> ::= ]<DEL>
		matrizTransicao[']'][6] = Estado.comp1;  //<comp1> ::= ]<comp1>
		matrizTransicao[']'][9] = Estado.comp1;  //<comp2> ::= ]<comp1>
		matrizTransicao[']'][12] = Estado.UEV;  //<nump1> ::= ]<UEV>
		matrizTransicao[']'][13] = Estado.TMF;  //<nump2> ::= ]<TMF>
		matrizTransicao['{'][0] = Estado.DEL;  //<Inicial> ::= {<DEL>
		matrizTransicao['{'][6] = Estado.comp1;  //<comp1> ::= {<comp1>
		matrizTransicao['{'][9] = Estado.comp1;  //<comp2> ::= {<comp1>
		matrizTransicao['{'][12] = Estado.UEV;  //<nump1> ::= {<UEV>
		matrizTransicao['{'][13] = Estado.TMF;  //<nump2> ::= {<TMF>
		matrizTransicao['}'][0] = Estado.DEL;  //<Inicial> ::= }<DEL>
		matrizTransicao['}'][6] = Estado.comp1;  //<comp1> ::= }<comp1>
		matrizTransicao['}'][9] = Estado.comp1;  //<comp2> ::= }<comp1>
		matrizTransicao['}'][12] = Estado.UEV;  //<nump1> ::= }<UEV>
		matrizTransicao['}'][13] = Estado.TMF;  //<nump2> ::= }<TMF>
		matrizTransicao['.'][0] = Estado.DEL;  //<Inicial> ::= .<DEL>
		matrizTransicao['.'][6] = Estado.comp1;  //<comp1> ::= .<comp1>
		matrizTransicao['.'][9] = Estado.comp1;  //<comp2> ::= .<comp1>
		matrizTransicao['.'][11] = Estado.nump2;  //<NUM> ::= .<nump2>
		matrizTransicao['.'][12] = Estado.UEV;  //<nump1> ::= .<UEV>
		matrizTransicao['.'][13] = Estado.TMF;  //<nump2> ::= .<TMF>
		matrizTransicao['+'][0] = Estado.OPAR;  //<Inicial> ::= +<OPAR>
		matrizTransicao['+'][6] = Estado.comp1;  //<comp1> ::= +<comp1>
		matrizTransicao['+'][7] = Estado.OPAR2;  //<OPAR> ::= +<OPAR2>
		matrizTransicao['+'][9] = Estado.comp1;  //<comp2> ::= +<comp1>
		matrizTransicao['+'][12] = Estado.UEV;  //<nump1> ::= +<UEV>
		matrizTransicao['+'][13] = Estado.TMF;  //<nump2> ::= +<TMF>
		matrizTransicao['+'][21] = Estado.cadcp1;  //<cadcp1> ::= +<cadcp1>
		matrizTransicao['+'][22] = Estado.cadcp1;  //<cadcp2> ::= +<cadcp1>
		matrizTransicao['*'][0] = Estado.OPAR;  //<Inicial> ::= *<OPAR>
		matrizTransicao['*'][6] = Estado.comp2;  //<comp1> ::= *<comp2>
		matrizTransicao['*'][7] = Estado.comp1;  //<OPAR> ::= *<comp1>
		matrizTransicao['*'][9] = Estado.comp1;  //<comp2> ::= *<comp1>
		matrizTransicao['*'][12] = Estado.UEV;  //<nump1> ::= *<UEV>
		matrizTransicao['*'][13] = Estado.TMF;  //<nump2> ::= *<TMF>
		matrizTransicao['*'][21] = Estado.cadcp1;  //<cadcp1> ::= *<cadcp1>
		matrizTransicao['*'][22] = Estado.cadcp1;  //<cadcp2> ::= *<cadcp1>
		matrizTransicao['/'][0] = Estado.OPAR;  //<Inicial> ::= /<OPAR>
		matrizTransicao['/'][6] = Estado.comp1;  //<comp1> ::= /<comp1>
		matrizTransicao['/'][7] = Estado.COML;  //<OPAR> ::= /<COML>
		matrizTransicao['/'][9] = Estado.COMB;  //<comp2> ::= /<COMB>
		matrizTransicao['/'][12] = Estado.UEV;  //<nump1> ::= /<UEV>
		matrizTransicao['/'][13] = Estado.TMF;  //<nump2> ::= /<TMF>
		matrizTransicao['/'][21] = Estado.cadcp1;  //<cadcp1> ::= /<cadcp1>
		matrizTransicao['/'][22] = Estado.cadcp1;  //<cadcp2> ::= /<cadcp1>
		matrizTransicao['!'][0] = Estado.OPL;  //<Inicial> ::= !<OPL>
		matrizTransicao['!'][6] = Estado.comp1;  //<comp1> ::= !<comp1>
		matrizTransicao['!'][9] = Estado.comp1;  //<comp2> ::= !<comp1>
		matrizTransicao['!'][12] = Estado.UEV;  //<nump1> ::= !<UEV>
		matrizTransicao['!'][13] = Estado.TMF;  //<nump2> ::= !<TMF>
		matrizTransicao['!'][21] = Estado.cadcp1;  //<cadcp1> ::= !<cadcp1>
		matrizTransicao['!'][22] = Estado.cadcp1;  //<cadcp2> ::= !<cadcp1>
		matrizTransicao['"'][0] = Estado.cadcp1;  //<Inicial> ::= "<cadcp1>
		matrizTransicao['"'][6] = Estado.comp1;  //<comp1> ::= "<comp1>
		matrizTransicao['"'][9] = Estado.comp1;  //<comp2> ::= "<comp1>
		matrizTransicao['"'][12] = Estado.UEV;  //<nump1> ::= "<UEV>
		matrizTransicao['"'][13] = Estado.TMF;  //<nump2> ::= "<TMF>
		matrizTransicao['"'][21] = Estado.CADC;  //<cadcp1> ::= "<CADC>
		matrizTransicao['"'][22] = Estado.cadcp1;  //<cadcp2> ::= "<cadcp1>
		matrizTransicao['#'][6] = Estado.comp1;  //<comp1> ::= #<comp1>
		matrizTransicao['#'][9] = Estado.comp1;  //<comp2> ::= #<comp1>
		matrizTransicao['#'][12] = Estado.UEV;  //<nump1> ::= #<UEV>
		matrizTransicao['#'][13] = Estado.TMF;  //<nump2> ::= #<TMF>
		matrizTransicao['#'][21] = Estado.cadcp1;  //<cadcp1> ::= #<cadcp1>
		matrizTransicao['#'][22] = Estado.cadcp1;  //<cadcp2> ::= #<cadcp1>
		matrizTransicao['$'][6] = Estado.comp1;  //<comp1> ::= $<comp1>
		matrizTransicao['$'][9] = Estado.comp1;  //<comp2> ::= $<comp1>
		matrizTransicao['$'][12] = Estado.UEV;  //<nump1> ::= $<UEV>
		matrizTransicao['$'][13] = Estado.TMF;  //<nump2> ::= $<TMF>
		matrizTransicao['$'][21] = Estado.cadcp1;  //<cadcp1> ::= $<cadcp1>
		matrizTransicao['$'][22] = Estado.cadcp1;  //<cadcp2> ::= $<cadcp1>
		matrizTransicao['%'][6] = Estado.comp1;  //<comp1> ::= %<comp1>
		matrizTransicao['%'][9] = Estado.comp1;  //<comp2> ::= %<comp1>
		matrizTransicao['%'][12] = Estado.UEV;  //<nump1> ::= %<UEV>
		matrizTransicao['%'][13] = Estado.TMF;  //<nump2> ::= %<TMF>
		matrizTransicao['%'][21] = Estado.cadcp1;  //<cadcp1> ::= %<cadcp1>
		matrizTransicao['%'][22] = Estado.cadcp1;  //<cadcp2> ::= %<cadcp1>
		matrizTransicao['&'][0] = Estado.oplp;  //<Inicial> ::= &<oplp>
		matrizTransicao['&'][6] = Estado.comp1;  //<comp1> ::= &<comp1>
		matrizTransicao['&'][9] = Estado.comp1;  //<comp2> ::= &<comp1>
		matrizTransicao['&'][12] = Estado.UEV;  //<nump1> ::= &<UEV>
		matrizTransicao['&'][13] = Estado.TMF;  //<nump2> ::= &<TMF>
		matrizTransicao['&'][20] = Estado.OPL2;  //<oplp> ::= &<OPL2>
		matrizTransicao['&'][21] = Estado.cadcp1;  //<cadcp1> ::= &<cadcp1>
		matrizTransicao['&'][22] = Estado.cadcp1;  //<cadcp2> ::= &<cadcp1>
		matrizTransicao['-'][0] = Estado.OPAR3;  //<Inicial> ::= -<OPAR3>
		matrizTransicao['-'][6] = Estado.comp1;  //<comp1> ::= -<comp1>
		matrizTransicao['-'][9] = Estado.comp1;  //<comp2> ::= -<comp1>
		matrizTransicao['-'][12] = Estado.UEV;  //<nump1> ::= -<UEV>
		matrizTransicao['-'][13] = Estado.TMF;  //<nump2> ::= -<TMF>
		matrizTransicao['-'][17] = Estado.OPAR2;  //<OPAR3> ::= -<OPAR2>
		matrizTransicao['-'][21] = Estado.cadcp1;  //<cadcp1> ::= -<cadcp1>
		matrizTransicao['-'][22] = Estado.cadcp1;  //<cadcp2> ::= -<cadcp1>
		matrizTransicao[':'][6] = Estado.comp1;  //<comp1> ::= :<comp1>
		matrizTransicao[':'][9] = Estado.comp1;  //<comp2> ::= :<comp1>
		matrizTransicao[':'][12] = Estado.UEV;  //<nump1> ::= :<UEV>
		matrizTransicao[':'][13] = Estado.TMF;  //<nump2> ::= :<TMF>
		matrizTransicao[':'][21] = Estado.cadcp1;  //<cadcp1> ::= :<cadcp1>
		matrizTransicao[':'][22] = Estado.cadcp1;  //<cadcp2> ::= :<cadcp1>
		matrizTransicao['<'][0] = Estado.OPRL;  //<Inicial> ::= <<OPRL>
		matrizTransicao['<'][6] = Estado.comp1;  //<comp1> ::= <<comp1>
		matrizTransicao['<'][9] = Estado.comp1;  //<comp2> ::= <<comp1>
		matrizTransicao['<'][12] = Estado.UEV;  //<nump1> ::= <<UEV>
		matrizTransicao['<'][13] = Estado.TMF;  //<nump2> ::= <<TMF>
		matrizTransicao['<'][21] = Estado.cadcp1;  //<cadcp1> ::= <<cadcp1>
		matrizTransicao['<'][22] = Estado.cadcp1;  //<cadcp2> ::= <<cadcp1>
		matrizTransicao['='][0] = Estado.OPRL;  //<Inicial> ::= =<OPRL>
		matrizTransicao['='][6] = Estado.comp1;  //<comp1> ::= =<comp1>
		matrizTransicao['='][9] = Estado.comp1;  //<comp2> ::= =<comp1>
		matrizTransicao['='][12] = Estado.UEV;  //<nump1> ::= =<UEV>
		matrizTransicao['='][13] = Estado.TMF;  //<nump2> ::= =<TMF>
		matrizTransicao['='][15] = Estado.OPRL2;  //<OPRL> ::= =<OPRL2>
		matrizTransicao['='][18] = Estado.OPRL2;  //<OPL> ::= =<OPRL2>
		matrizTransicao['='][21] = Estado.cadcp1;  //<cadcp1> ::= =<cadcp1>
		matrizTransicao['='][22] = Estado.cadcp1;  //<cadcp2> ::= =<cadcp1>
		matrizTransicao['>'][0] = Estado.OPRL;  //<Inicial> ::= ><OPRL>
		matrizTransicao['>'][6] = Estado.comp1;  //<comp1> ::= ><comp1>
		matrizTransicao['>'][9] = Estado.comp1;  //<comp2> ::= ><comp1>
		matrizTransicao['>'][12] = Estado.UEV;  //<nump1> ::= ><UEV>
		matrizTransicao['>'][13] = Estado.TMF;  //<nump2> ::= ><TMF>
		matrizTransicao['>'][21] = Estado.cadcp1;  //<cadcp1> ::= ><cadcp1>
		matrizTransicao['>'][22] = Estado.cadcp1;  //<cadcp2> ::= ><cadcp1>
		matrizTransicao['?'][6] = Estado.comp1;  //<comp1> ::= ?<comp1>
		matrizTransicao['?'][9] = Estado.comp1;  //<comp2> ::= ?<comp1>
		matrizTransicao['?'][12] = Estado.UEV;  //<nump1> ::= ?<UEV>
		matrizTransicao['?'][13] = Estado.TMF;  //<nump2> ::= ?<TMF>
		matrizTransicao['?'][21] = Estado.cadcp1;  //<cadcp1> ::= ?<cadcp1>
		matrizTransicao['?'][22] = Estado.cadcp1;  //<cadcp2> ::= ?<cadcp1>
		matrizTransicao['@'][6] = Estado.comp1;  //<comp1> ::= @<comp1>
		matrizTransicao['@'][9] = Estado.comp1;  //<comp2> ::= @<comp1>
		matrizTransicao['@'][12] = Estado.UEV;  //<nump1> ::= @<UEV>
		matrizTransicao['@'][13] = Estado.TMF;  //<nump2> ::= @<TMF>
		matrizTransicao['@'][21] = Estado.cadcp1;  //<cadcp1> ::= @<cadcp1>
		matrizTransicao['@'][22] = Estado.cadcp1;  //<cadcp2> ::= @<cadcp1>
		matrizTransicao['\\'][6] = Estado.comp1;  //<comp1> ::= \<comp1>
		matrizTransicao['\\'][9] = Estado.comp1;  //<comp2> ::= \<comp1>
		matrizTransicao['\\'][12] = Estado.UEV;  //<nump1> ::= \<UEV>
		matrizTransicao['\\'][13] = Estado.TMF;  //<nump2> ::= \<TMF>
		matrizTransicao['\\'][21] = Estado.cadcp2;  //<cadcp1> ::= \<cadcp2>
		matrizTransicao['\\'][22] = Estado.cadcp1;  //<cadcp2> ::= \<cadcp1>
		matrizTransicao['^'][6] = Estado.comp1;  //<comp1> ::= ^<comp1>
		matrizTransicao['^'][9] = Estado.comp1;  //<comp2> ::= ^<comp1>
		matrizTransicao['^'][12] = Estado.UEV;  //<nump1> ::= ^<UEV>
		matrizTransicao['^'][13] = Estado.TMF;  //<nump2> ::= ^<TMF>
		matrizTransicao['^'][21] = Estado.cadcp1;  //<cadcp1> ::= ^<cadcp1>
		matrizTransicao['^'][22] = Estado.cadcp1;  //<cadcp2> ::= ^<cadcp1>
		matrizTransicao['`'][6] = Estado.comp1;  //<comp1> ::= `<comp1>
		matrizTransicao['`'][9] = Estado.comp1;  //<comp2> ::= `<comp1>
		matrizTransicao['`'][12] = Estado.UEV;  //<nump1> ::= `<UEV>
		matrizTransicao['`'][13] = Estado.TMF;  //<nump2> ::= `<TMF>
		matrizTransicao['`'][21] = Estado.cadcp1;  //<cadcp1> ::= `<cadcp1>
		matrizTransicao['`'][22] = Estado.cadcp1;  //<cadcp2> ::= `<cadcp1>
		matrizTransicao['|'][0] = Estado.oplp;  //<Inicial> ::= |<oplp>
		matrizTransicao['|'][6] = Estado.comp1;  //<comp1> ::= |<comp1>
		matrizTransicao['|'][9] = Estado.comp1;  //<comp2> ::= |<comp1>
		matrizTransicao['|'][12] = Estado.UEV;  //<nump1> ::= |<UEV>
		matrizTransicao['|'][13] = Estado.TMF;  //<nump2> ::= |<TMF>
		matrizTransicao['|'][20] = Estado.OPL2;  //<oplp> ::= |<OPL2>
		matrizTransicao['|'][21] = Estado.cadcp1;  //<cadcp1> ::= |<cadcp1>
		matrizTransicao['|'][22] = Estado.cadcp1;  //<cadcp2> ::= |<cadcp1>
		matrizTransicao['~'][6] = Estado.comp1;  //<comp1> ::= ~<comp1>
		matrizTransicao['~'][9] = Estado.comp1;  //<comp2> ::= ~<comp1>
		matrizTransicao['~'][12] = Estado.UEV;  //<nump1> ::= ~<UEV>
		matrizTransicao['~'][13] = Estado.TMF;  //<nump2> ::= ~<TMF>
		matrizTransicao['~'][21] = Estado.cadcp1;  //<cadcp1> ::= ~<cadcp1>
		matrizTransicao['~'][22] = Estado.cadcp1;  //<cadcp2> ::= ~<cadcp1>
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