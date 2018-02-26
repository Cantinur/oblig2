import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/* OBS!! Jeg har her satt main inn her sammen med en rekke metoder, deriblandt
den som leser fra fil. Dette er fordi de aller fleste metodene som er her er kun
for dette programmet. */
public class Hovedprogram{
  public static void main(String[] args){
    Hovedprogram h = new Hovedprogram();
    //Den hadde med .txt da jeg lastet den ned.
    h.lesFraFil("1.in.txt");
    h.meny();
  }

  private ArrayList<Bil> bilListe = new ArrayList<Bil>();
  public Scanner tast = new Scanner(System.in);
  /* For a gjore koden mindre har jeg flyttet deler inn i egene metoder.
  Dette har jeg gjort for at det skal bli lettere a lese koden*/
  public void lesFraFil(String filnavn){
    try{
      Scanner fil = new Scanner(new File(filnavn));

      while (fil.hasNextLine()){
        String linjeMedInformasjon = fil.nextLine();
        trimOverflodig(linjeMedInformasjon);
        String[] info = new String[4];
        info = linjeMedInformasjon.split(" ");
        String bilType = trimOverflodig(info[0]);

        if (bilType.equals("EL") || bilType.equals("PERSONBIL") || bilType.equals("LASTEBIL")){
          leggTilBil(info, bilType);
        } else {
          System.out.println("Det er en feil! Staar: "+linjeMedInformasjon);
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("Her er det feil.");
      System.exit(0);
    }
  }
  /* Hjelpemetode for a fjerne symbolene < > og trimmebort "tomme tastetrykk".
  I eksemplet gitt i oppgaven inneholdt de forskjellige tallene <> klammer
  rundt. Dette ma da fjernes da bilen blir registrert. Har ogsa lagt inn en
  sikkerhetsjekk for a passe pa at tallene har punktum istede for komma. */
  private String trimOverflodig(String tilTrim){
    return tilTrim.trim().replace("<", "").replace(">", "").replace(",", ".");
  }
  /* Forste del av koden ser etter om bilen er en Ebil, hvis ikke gar den til
  del to og legger til en personbil eller lastebil. Programmet vil riktig nok
  krasje dersom det er lagt inn feil informasjon pa feil plass. Tar det som en gitt
  at txt filen den leser fra er riktig strukturert. */
  private  void leggTilBil(String[] info, String typeBil){
    String reg = trimOverflodig(info[1]);
    /*sjekk for a passe pa at den samme bilen ikke blir registrert i systemet
    flere ganger.*/
    for (Bil b : bilListe){
      if (reg.equals(b.returnerReg())){
        System.out.println(reg+" er allerede registrert.");
        return;
      }
    }
    String utslippEllerBatteri = trimOverflodig(info[2]);
    if(typeBil.equals("EL")){
      double batteri = Double.parseDouble(utslippEllerBatteri);
      Elbil e = new Elbil(reg, batteri);
      bilListe.add(e);

    } else {
      double utslipp = Double.parseDouble(utslippEllerBatteri);
      String seterEllerVekt = trimOverflodig(info[3]);

      if (typeBil.equals("PERSONBIL")){
        int seter = Integer.parseInt(seterEllerVekt);
        Personbiler p = new Personbiler(reg, utslipp, seter);
        bilListe.add(p);

      } else if (typeBil.equals("LASTEBIL")){
        double nyttevekt = Double.parseDouble(seterEllerVekt);
        Lastebiler l = new Lastebiler(reg, utslipp, nyttevekt);
        bilListe.add(l);
      }
    }
  }
  /*Dette er kanskje den oppgave messig viktigste metoden i hovedprogram.*/
  private void skrivBiler(String typeBil){
    int count = 0;

    if (typeBil.equals("Biler")){
      count = bilListe.size();
    } else {
      for (Bil b : bilListe){
        if (b.returnType(typeBil)){
          b.type();
          count += 1;
        }
      }
    }
    System.out.println("Det er "+count+" "+typeBil.toLowerCase()+" i systemet.");
  }
  /*Hjelpemetode for a ga igjennom a se om det som har blitt tastet inn er lovlig.*/
  private boolean sjekkOmRiktig(String inputString, int antallIGittListe){
    for (int i = 0; i <= antallIGittListe; i++){
      if (inputString.equals(i + "")){
        return true;
      }
    }
    return false;
  }
  /*Her tilbyr programmet brukeren alternativer til filtrering. Bruker
  kan taste inn et tall mellom 1 og 5 og fa forskjellige filteringer*/
  private void filtrer(){
    boolean whileLokkeStopper = true;
    //Er inne i filter til bruker har tastet inn gyldig informasjon.
    while (whileLokkeStopper){
      System.out.println("Velg filter:");
      System.out.println("1 - Elbiler");
      System.out.println("2 - Fossilbiler");
      System.out.println("3 - Personbiler");
      System.out.println("4 - Lastebiler");
      System.out.println("5 - Vis alle");
      String inputString = trimOverflodig(tast.nextLine());
      //Skjekk for a unnga kjorefeil.
      if (sjekkOmRiktig(inputString, 5)){
        int inputTilTall = Integer.parseInt(inputString);
        if (inputTilTall == 1){
          inputString = "Elbiler";
        } else if (inputTilTall == 2){
          inputString = "Fossilbiler";
        } else if (inputTilTall == 3){
          inputString = "Personbiler";
        } else if (inputTilTall == 4){
          inputString = "Lastebiler";
        } else if (inputTilTall == 5){
          inputString = "Biler";
        }
        skrivBiler(inputString);
        whileLokkeStopper = false;
      } else {
        System.out.println("Vendligst tast inn et tall mellom 1 & 5");
      }
    }
  }
  //Laget en meny for a gjore pragrammet mer bruker vennlig.
  public void meny(){
    int inputTilTall = 0;
    while (inputTilTall < 2){
      System.out.println("Velg fra liste: ");
      System.out.println("1 - Filtrer ");
      System.out.println("2 - Avslutt");
      String inputString = trimOverflodig(tast.nextLine());
      if (sjekkOmRiktig(inputString, 2)){
        inputTilTall = Integer.parseInt(inputString);
        if (inputTilTall == 1){
          filtrer();
        } else if (inputTilTall == 2){
          System.out.println("Programmet avsluttes");
        }
      }  else {
        System.out.println("Vendlisgt skriv kun inn 1 eller 2.");
      }
    }
  }
}
