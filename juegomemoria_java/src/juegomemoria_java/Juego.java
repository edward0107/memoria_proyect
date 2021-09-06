
package juegomemoria_java;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Juego {
   
    //atributos
    JFrame ventana;
    //presentacion
    JPanel panelPresentacion;
    JLabel fondoPresentacion;
    JLabel BotonJugar;   
    //juego
    JPanel panelJuego;
    JLabel fondoJuego;
    JLabel matriz[][];
    int mat[][];
    int matAux[][];
    String jugador;
    Random aleatorio;
    JLabel nombreJugador;
    Timer tiempo;
    JLabel cronometro;
    int min;
    int seg;
    int contador;
    Timer tiempoEspera;
    int contSegEsp;
    Timer tiempoEspera1;
    int ban;
    int ban1;
    
    int antnum;
    int antx;
    int anty;
    int actualnum;
    int actualx;
    int actualy;
    
    //constructor
    public Juego(){
        
        //ventana
        ventana = new JFrame("Juego De Memoria");
        ventana.setSize(600, 600);
        ventana.setLayout(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        
        //Jpanel presentacion
        panelPresentacion = new JPanel();
        panelPresentacion.setSize(ventana.getWidth(), ventana.getHeight());
        panelPresentacion.setLocation(0, 0);
        panelPresentacion.setLayout(null);
        panelPresentacion.setVisible(true);
        
        
        //fondo de presentacion
        fondoPresentacion = new JLabel();
        fondoPresentacion.setSize(ventana.getWidth(), ventana.getHeight());
        fondoPresentacion.setLocation(0, 0);
        fondoPresentacion.setIcon(new ImageIcon("imagenes/portada.png"));
        fondoPresentacion.setVisible(true);
        panelPresentacion.add(fondoPresentacion,0);
        
        
        //boton de jugar
        BotonJugar = new JLabel();
        BotonJugar.setSize(101, 110);
        BotonJugar.setLocation(0, 0);
        BotonJugar.setIcon(new ImageIcon("imagenes/boton.png"));
        BotonJugar.setVisible(true);
        panelPresentacion.add(BotonJugar,0);
        
        //Jpanel juego
        panelJuego = new JPanel();
        panelJuego.setSize(ventana.getWidth(), ventana.getHeight());
        panelJuego.setLocation(0, 0);
        panelJuego.setLayout(null);
        panelJuego.setVisible(true);
        
        //fondo de juego
        fondoJuego = new JLabel();
        fondoJuego.setSize(ventana.getWidth(), ventana.getHeight());
        fondoJuego.setLocation(0, 0);
        fondoJuego.setIcon(new ImageIcon("imagenes/fondoJuego.png"));
        fondoJuego.setVisible(true);
        panelJuego.add(fondoJuego,0);
        
        //nombre del jugador
        nombreJugador = new JLabel();
        nombreJugador.setSize(150, 20);
        nombreJugador.setLocation(100, 10);
        nombreJugador.setForeground(Color.WHITE);
        nombreJugador.setVisible(true);
        panelJuego.add(nombreJugador,0);
        
        //nombre del jugador
        cronometro = new JLabel();
        cronometro.setSize(150, 20);
        cronometro.setLocation(ventana.getWidth()-200, 10);
        cronometro.setForeground(Color.WHITE);
        cronometro.setVisible(true);
        panelJuego.add(cronometro,0);
        
        //matriz logica
        mat = new int[4][5];
        matAux = new int[4][5];
        aleatorio = new Random();
        this.numerosAleatorios();
        
        //matriz de imagenes
        matriz = new JLabel[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = new JLabel();
                matriz[i][j].setBounds(155+(j*56), 175+(i*67), 56, 67);
                matriz[i][j].setIcon(new ImageIcon("imagenes/"+matAux[i][j]+".png"));
                matriz[i][j].setVisible(true);
                panelJuego.add(matriz[i][j],0);
            }
        } 
        
        //tiempo
        min = 0;
        seg = 0;
        
        tiempo = new Timer (1000, new ActionListener () 
        { 
            public void actionPerformed(ActionEvent e) 
            {
                seg++;
                if(seg == 60 ){
                    min++;
                    seg = 0;
                }               
                cronometro.setText("tiempo: "+min+":"+seg);          
        }});
        
        
        //tiempo de espera
        contSegEsp = 0;
        tiempoEspera = new Timer (1000, new ActionListener () 
        { 
            public void actionPerformed(ActionEvent e) 
            {
                 contSegEsp++;        
        }});
        tiempoEspera.start();
        tiempoEspera.stop();
        contSegEsp = 0;
        ban = 0;
        ban1 = 0;
        // evento de clic de las cartas
        
        contador = 0;
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed( MouseEvent e){
                        for (int k = 0; k < 4; k++) {
                            for (int l = 0; l < 5; l++) {
                                if(e.getSource() == matriz[k][l]){
                                    //System.out.println(k+" "+l);
                                    
                                    if( matAux[k][l] == 0 && contador !=2){
                                        
                                        matAux[k][l] = mat[k][l];
                                        matriz[k][l].setIcon(new ImageIcon("imagenes/"+matAux[k][l]+".png"));
                                        contador++;
                                        actualnum = mat[k][l];
                                        actualx = k;
                                        actualy = l;
                                        if(contador == 1){
                                            antnum = mat[k][l];
                                            antx = k;
                                            anty = l;
                                        }
                                                                                
                                        tiempoEspera1 = new Timer (500, new ActionListener () 
                                        { 
                                        public void actionPerformed(ActionEvent e) 
                                        {
                        
                                            
                                            if(contador == 2 && ban1 == 0 ){
                                                tiempoEspera.restart();
                                                ban1=1;
                                            }
                                            if( contador == 2 && contSegEsp == 2 ){
                                                tiempoEspera.stop();
                                                contSegEsp = 0;
                                                if( matAux[actualx][actualy] == matAux[antx][anty] ){
                                                    matAux[actualx][actualy] = -1;
                                                    matAux[antx][anty] = -1;
                                                    matriz[actualx][actualy].setIcon(new ImageIcon("imagenes/"+matAux[actualx][actualy]+".png"));
                                                    matriz[antx][anty].setIcon(new ImageIcon("imagenes/"+matAux[antx][anty]+".png"));
                                                    contador = 0;
                                                    //ganar
                                                    int acum = 0;
                                                    for (int m = 0; m < 4; m++) {
                                                        for (int n = 0; n < 5; n++) {
                                                            if(matAux[m][n] == -1)
                                                                acum++;
                                                        }
                                                    }
                                                    if(acum == 20 ){
                                                        tiempo.stop();
                                                        JOptionPane.showMessageDialog(ventana, "FELICITACIONES GANO con un Tiempo: "+min+":"+seg);
                                                        
                                                        for (int m = 0; m < 4; m++) {
                                                            for (int n = 0; n < 5; n++) {
                                                                mat[m][n] = 0;
                                                                matAux[m][n] = 0;
                                                                matriz[m][n].setIcon(new ImageIcon("imagenes/"+matAux[m][n]+".png"));
                                                            }  
                                                        }
                                                        numerosAleatorios();
                                                        min = 0;
                                                        seg = 0;
                                                        tiempo.restart();
                                                        
                                                    }
                                                }
                                                
                                                for (int m = 0; m < 4; m++) {
                                                    for (int n = 0; n < 5; n++) {
                                                        System.out.print(matAux[m][n]+" ");
                                                        if( matAux[m][n] != 0 && matAux[m][n]!=-1 ){
                                                            matAux[m][n] = 0;
                                                            matriz[m][n].setIcon(new ImageIcon("imagenes/"+matAux[m][n]+".png"));
                                                            System.out.println("hola");
                                                            contador = 0;
                                                        }
                                                    }
                                                    System.out.println("");
                                                }
                                                tiempoEspera1.stop();
                                                ban1=0;
                                            }
                                        }});
                                        if( ban == 0 ){
                                            tiempoEspera1.start();
                                            ban = 1;
                                        }    
                                        if(contador == 2)
                                            tiempoEspera1.restart();
                                          
                                    }
                                    
                                    
                                    
                                }
                                
                            }   
                        }
                    
                    }
                });
                
            }  
        }
        
        
        
        
        
        
        BotonJugar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                //System.out.println("presione el boton");
                
                jugador = JOptionPane.showInputDialog(ventana, "Nombre del jugador", "Escribe aqui" );     
                while(jugador == null || jugador.compareTo("Escribe aqui")==0 || jugador.compareTo("")==0){
                    jugador = JOptionPane.showInputDialog(ventana, "Debes ingresar usuario","Escribe aqui");
                }
                nombreJugador.setText("Jugador: "+jugador );
                tiempo.start();
                panelPresentacion.setVisible(false);
                ventana.add(panelJuego);
                panelJuego.setVisible(true);
     
            }});
        
        
        
        
        
        
        ventana.add(panelPresentacion);
        ventana.setVisible(true);
    }
    
    public void numerosAleatorios(){
        
        int acumulador = 0;
        for (int i = 0; i < 4; i++) 
            for (int j = 0; j < 5; j++){ 
                mat[i][j] = 0;
                matAux[i][j] = 0;
            }    
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                mat[i][j] = aleatorio.nextInt(10)+1;
                do{
                    acumulador = 0;
                    for (int k = 0; k < 4; k++) {
                        for (int l = 0; l < 5; l++) {
                            if( mat[i][j] == mat[k][l] ){
                                acumulador +=1;
                            } 
                        }
                    }
                    if(acumulador == 3){
                        mat[i][j] = aleatorio.nextInt(10)+1;
                    }
                }while( acumulador == 3 );    
            }
        }
        
       /* for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(mat[i][j]+"   ");
            
            }
            System.out.println("");
        }*/
    }
    
    
    
    
    
}
