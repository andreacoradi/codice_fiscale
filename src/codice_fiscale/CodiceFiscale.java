/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package codice_fiscale;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.String;

/**
 *
 * @author Isabllix supervised by Figus
 */
public class CodiceFiscale {
    private String cognome; 
    private String nome; 
    private String anno; 
    private String mese; 
    private String giorno; 
    private String luogo;
    private String codice;
    
    public CodiceFiscale(String cognome, String nome, String sesso, String data, String luogo){
        cognome(cognome);
        nome(nome);
        String[] Data = data.split("-");
        anno(Data[0]);
        mese(Data[1]);
        giorno(Data[2], sesso);
        luogo(luogo);
        this.codice = this.cognome + this.nome + this.anno + this.mese + this.giorno + this.luogo;
        if(!isValid()){
            return;
        }else
            codiceControllo();
        //System.out.println("CODICE: " +  this.codice);
    }
    
    public String getCodice() throws CodiceFiscaleNotValid{
        if(isValid())
            return this.codice;
        throw new CodiceFiscaleNotValid();  
    }
    
    private boolean isValid(){
        return !(this.cognome == null || this.nome == null ||this.anno == null ||this.mese == null ||this.giorno == null ||this.luogo == null);
    }
    
    private void cognome (String cognome){
        ArrayList<Character> vocali = new ArrayList<>();
        ArrayList<Character> consonanti = new ArrayList<>();
        String x = "";
        cognome = correggi(cognome);
        //System.out.println("CORRETTO " + cognome);
        for (int i = 0; i < cognome.length(); i++){
            if (vocale(cognome.charAt(i)))
                vocali.add(cognome.charAt(i));
            else
                consonanti.add(cognome.charAt(i));
        }
        //System.out.println("CIAO " + consonanti + " CIAO");
        //System.out.println("" + 5 + CONS " + (consonanti.get(0))+consonanti.get(1)));
        if (consonanti.size() >=3)
            x +="" + consonanti.get(0)+ consonanti.get(1) + consonanti.get(2);
        else if (consonanti.size() == 2 && vocali.size() >=1)
            x+= ""+consonanti.get(0) + consonanti.get(1)+ vocali.get(0);
        else if (consonanti.size() == 2 && vocali.size() == 0)
            x+= ""+consonanti.get(0) + consonanti.get(1) + 'X';
        else if (consonanti.size() == 1 && vocali.size() >= 2)
            x+= ""+consonanti.get(0) + vocali.get(0) + vocali.get(1);
        else if (consonanti.size() == 1 && vocali.size() == 1)
            x+= ""+consonanti.get(0) + vocali.get(0) + 'X';
        else if (consonanti.size() == 1 && vocali.size() == 0)
            x+= ""+consonanti.get(0) + "XX";
        else if (consonanti.size() == 0 && vocali.size() >=3)
            x+= ""+vocali.get(0) + vocali.get(1) + vocali.get(2);
        else if (consonanti.size() == 0 && vocali.size() == 2)
            x+= ""+vocali.get(0) + vocali.get(1) + 'X';
        else if (consonanti.size() == 0 && vocali.size() == 1)
            x+= ""+vocali.get(0) + "XX";
        //System.out.println("COGNOME:" + x);
        this.cognome = x;
    }
    
    private void nome (String nome){
        ArrayList<Character> vocali = new ArrayList<>();
        ArrayList<Character> consonanti = new ArrayList<>();
        String x = "";
        nome = correggi(nome);
        for (int i = 0; i < nome.length(); i++){
            if (vocale(nome.charAt(i)))
                vocali.add(nome.charAt(i));
            else
                consonanti.add(nome.charAt(i));
        }
        
        
        if (consonanti.size() >=4)
            x+= ""+consonanti.get(0)+ consonanti.get(2) + consonanti.get(3);
        else if (consonanti.size() == 3)
            x+= ""+consonanti.get(0) + consonanti.get(1)+ consonanti.get(2);
        else if (consonanti.size() == 2 && vocali.size() >= 1)
            x+=""+ consonanti.get(0) + consonanti.get(1) + vocali.get(0);
        else if (consonanti.size() == 2 && vocali.size() == 0)
            x+=""+ consonanti.get(0) + consonanti.get(0) + 'X';
        else if (consonanti.size() == 1 && vocali.size() >= 2)
            x+=""+ consonanti.get(0) + vocali.get(0) + vocali.get(1);
        else if (consonanti.size() == 1 && vocali.size() == 1)
            x+=""+ consonanti.get(0) + vocali.get(0) + 'X';
        else if (consonanti.size() == 1 && vocali.size() == 0)
            x+= ""+consonanti.get(0) + "XX";
        else if (consonanti.size() == 0 && vocali.size() >=3)
            x+=""+ vocali.get(0) + vocali.get(1) + vocali.get(2);
        else if (consonanti.size() == 0 && vocali.size() == 2)
            x+=""+ vocali.get(0) + vocali.get(1) + 'X';
        else if (consonanti.size() == 0 && vocali.size() == 1)
            x+=""+ vocali.get(0) + "XX";
        //System.out.println("NOME:" + x);
        this.nome = x;
    }
    
    private void anno(String anno){
        this.anno = anno.substring(2);
        //System.out.println("ANNO " + this.anno);
    }
    
    private void mese(String mese){
        if (mese.equals("01"))
            this.mese = "A";
        else if (mese.equals("02"))
            this.mese = "B";  
        else if (mese.equals("03"))
            this.mese = "C";
        else if (mese.equals("04"))
            this.mese = "D";
        else if (mese.equals("05"))
            this.mese = "E";
        else if (mese.equals("06"))
            this.mese = "H";
        else if (mese.equals("07"))
            this.mese = "L";
        else if (mese.equals("08"))
            this.mese = "M";
        else if (mese.equals("09"))
            this.mese = "P";
        else if (mese.equals("10"))
            this.mese = "R";
        else if (mese.equals("11"))
            this.mese = "S";
        else if (mese.equals("12"))
            this.mese = "T";
    }
    
    private void giorno(String giorno, String sesso){
        int g = Integer.parseInt(giorno);
        if(sesso == "F")
            g+=40;
        if(g < 10)
            this.giorno = "0" + Integer.toString(g);
        else
            this.giorno = Integer.toString(g);
    }
    
    private void luogo(String luogo){
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File("comuni.csv")));
            String line;
            while((line = in.readLine()) != null){
                String temp[] = line.split(";");
                String t = correggi(luogo).trim();
                String t2 = correggi(temp[0]).trim();
                if(t.equals(t2)){
                    this.luogo = temp[1];
                    return;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CodiceFiscale.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CodiceFiscale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void codiceControllo(){
        int somma = codiceControlloDispari() + codiceControlloPari();
        somma %= 26; //Ci sono 26 lettere nell'alfabeto
        somma += 'A';
        this.codice += (char)somma;
    }
    
    private int codiceControlloPari(){
        String cod = this.codice;
        int somma = 0;
        for(int i=1; i<cod.length(); i+=2){
            char t = cod.charAt(i);
            if (t == 'A' || t == '0')
                somma += 0;
            else if (t == 'B' || t == '1')
                somma += 1;
            else if (t == 'C' || t == '2')
                somma += 2;
            else if (t == 'D' || t == '3')
                somma += 3;
            else if (t == 'E' || t == '4')
                somma += 4;
            else if (t == 'F' || t == '5')
                somma += 5;
            else if (t == 'G' || t == '6')
                somma += 6;
            else if (t == 'H' || t == '7')
                somma += 7;
            else if (t == 'I' || t == '8')
                somma += 8;
            else if (t == 'J' || t == '9')
                somma += 9;
            else if (t == 'K')
                somma += 10;
            else if (t == 'L')
                somma += 11;
            else if (t == 'M')
                somma += 12;
            else if (t == 'N')
                somma += 13;
            else if (t == 'O')
                somma += 14;
            else if (t == 'P')
                somma += 15;
            else if (t == 'Q')
                somma += 16;
            else if (t == 'R')
                somma += 17;
            else if (t == 'S')
                somma += 18;
            else if (t == 'T')
                somma += 19;
            else if (t == 'U')
                somma += 20;
            else if (t == 'V')
                somma += 21;
            else if (t == 'W')
                somma += 22;
            else if (t == 'X')
                somma += 23;
            else if (t == 'Y')
                somma += 24;
            else if (t == 'Z')
                somma += 25;
        }
        return somma;
    }
    
    private int codiceControlloDispari(){
        int somma = 0;
        String cod = this.codice;
        for(int i=0; i<cod.length(); i+= 2){
            char t = cod.charAt(i);
            //System.out.print(t);
            if (t == 'A' || t == '0')
                somma += 1;
            else if (t == 'B' || t == '1')
                somma += 0;
            else if (t == 'C' || t == '2')
                somma += 5;
            else if (t == 'D' || t == '3')
                somma += 7;
            else if (t == 'E' || t == '4')
                somma += 9;
            else if (t == 'F' || t == '5')
                somma += 13;
            else if (t == 'G' || t == '6')
                somma += 15;
            else if (t == 'H' || t == '7')
                somma += 17;
            else if (t == 'I' || t == '8')
                somma += 19;
            else if (t == 'J' || t == '9')
                somma += 21;
            else if (t == 'K')
                somma += 2;
            else if (t == 'L')
                somma += 4;
            else if (t == 'M')
                somma += 18;
            else if (t == 'N')
                somma += 20;
            else if (t == 'O')
                somma += 11;
            else if (t == 'P')
                somma += 3;
            else if (t == 'Q')
                somma += 6;
            else if (t == 'R')
                somma += 8;
            else if (t == 'S')
                somma += 12;
            else if (t == 'T')
                somma += 14;
            else if (t == 'U')
                somma += 16;
            else if (t == 'V')
                somma += 10;
            else if (t == 'W')
                somma += 22;
            else if (t == 'X')
                somma += 25;
            else if (t == 'Y')
                somma += 24;
            else if (t == 'Z')
                somma += 23;
        }
        return somma;
    }

    private String correggi (String a){
        String b = "";
        a = a.toLowerCase();
        for (int i = 0; i<a.length();i++){
            if (a.charAt(i)!=' ' && a.charAt(i)!='\'')
                b+=a.charAt(i);
        }
        b = b.replace('à', 'a');
        b = b.replace('è', 'e');
        b = b.replace('é', 'e');
        b = b.replace('ì', 'i');
        b = b.replace('ò', 'o');
        b = b.replace('ù', 'u');
        return b.toUpperCase();
    }

    private boolean vocale (char c){
        return (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U');
    }
}
