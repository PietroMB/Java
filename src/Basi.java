import java.util.ArrayList;

/**
 * Created by pietro on 22/03/17.
 */
class Contatore{
    private int conta;
    public Contatore(){conta=0;}
    public synchronized void incr(){conta++;}
    public synchronized void decr(){conta--;}
    public int getConta(){return conta;}
}

public class Basi extends Thread{
    private String stringaprivata;
    private int interoprivato;

    public Basi(String s){
        stringaprivata=s;
    /*this.stringaprivata=s*/
    }

    public void stampaPrivata(){
        System.out.println(stringaprivata+"\n");
    }



    public static void main(String[] args) {
    /*1 hello world*/
        System.out.println("ciao\n");

        int i=0;
    /*2 condizionni*/
        if (i==0) {
            i=2;
        }
        switch(i){
            case 0:
                System.out.println("c'è stato un errore\n");
                break;
            case 2:
                System.out.println("tutto ok\n");
        }

    /*3 itezazioni e stringhe*/
        String a="Marco";
        String b=a;

        int[] v = new int[20];
        v[5]=7;
        System.out.println(v[5]+" "+v.length);

        for (i=0;i<a.length();i++) {
            System.out.println(a.charAt(i));
        }
        if (a.equals(b)){
            b="a";
            i=(int)4.2;
            try {
                i=Integer.parseInt(b);
            }catch (NumberFormatException e){}
        }
    /*4 Arraylist*/
        ArrayList <Integer> al = new ArrayList<Integer>();
        al.add(5);
        al.add(54);
        al.add(2);
        al.add(115);
        al.set(2,115);
        for (int x:al) {
            System.out.println(x);
        }

        /*thread*/
        Contatore c = new Contatore();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j=1;j<10000;j++)
                    c.incr();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j=1;j<10000;j++)
                    c.decr();
            }
        });
        t1.start();
        t2.start();
        System.out.println("\n\n"+c.getConta());
    }
}
