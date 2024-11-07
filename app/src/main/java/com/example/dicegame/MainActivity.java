package com.example.dicegame;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button pszycisk_rzut;
    private Button pszycisk_reset;
    private TextView kosc1;
    private TextView kosc2;
    private TextView kosc3;
    private TextView kosc4;
    private TextView kosc5;
    private TextView podpis;
    private TextView wynik_losowania;
    private TextView wynik_gry;
    private TextView liczba_rzutow;
    private Random rand = new Random();
    int wynik_gry_int = 0;
    int licznik = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kosc1 = findViewById(R.id.kosc1);
        kosc2 = findViewById(R.id.kosc2);
        kosc3 = findViewById(R.id.kosc3);
        kosc4 = findViewById(R.id.kosc4);
        kosc5 = findViewById(R.id.kosc5);
        podpis = findViewById(R.id.podpis);
        wynik_gry = findViewById(R.id.wynik_gry);
        wynik_losowania = findViewById(R.id.wynik_losowania);
        liczba_rzutow = findViewById(R.id.liczba_rzutow);
        pszycisk_rzut = findViewById(R.id.pszycisk_rzut);
        pszycisk_reset = findViewById(R.id.pszycisk_reset);

        pszycisk_rzut.setOnClickListener( v -> {

            int rand1 = rand.nextInt(6) + 1;
            kosc1.setText(Integer.toString(rand1));
            int rand2 = rand.nextInt(6) + 1;
            kosc2.setText(Integer.toString(rand2));
            int rand3 = rand.nextInt(6) + 1;
            kosc3.setText(Integer.toString(rand3));
            int rand4 = rand.nextInt(6) + 1;
            kosc4.setText(Integer.toString(rand4));
            int rand5 = rand.nextInt(6) + 1;
            kosc5.setText(Integer.toString(rand5));


            int[] liczs = {rand1, rand2, rand3, rand4, rand5};
            int wynik = 0;


            int[] mnoznik = {0, 0, 0, 0, 0};
            int[] zapobiegawcza = {0, 0, 0, 0, 0};
            boolean spr = false;
            for(int i = 0; i<liczs.length;i++){
                for(int j = 0; j<liczs.length;j++){
                    for(int b = 0; b<zapobiegawcza.length;b++){
                        if(liczs[j]==zapobiegawcza[b]){
                            spr = false;
                            break;
                        }else{
                            spr = true;
                        }
                    }
                    if(spr){
                        if(liczs[i]==liczs[j]){
                            mnoznik[i] += 1;
                        }
                    }
                    spr = false;
                }
                if(mnoznik[i]>=2){
                    wynik += liczs[i] * mnoznik[i];
                }
                zapobiegawcza[i] = liczs[i];

            }

            wynik_losowania.setText("Wynik tego losowania: " + wynik);
            licznik++;
            liczba_rzutow.setText("Liczba rzutów: " + licznik);
            updateScore(wynik);


        });

        pszycisk_reset.setOnClickListener( v -> {
            kosc1.setText("?");
            kosc2.setText("?");
            kosc3.setText("?");
            kosc4.setText("?");
            kosc5.setText("?");
            wynik_gry_int = 0;
            licznik = 0;
            wynik_gry.setText("Wynik gry: " + wynik_gry_int);
            liczba_rzutow.setText("Liczba rzutów: " + licznik);

        });

    }

    private void updateScore(int wynik) {
        wynik_gry_int +=wynik;
        wynik_gry.setText("Wynik gry: " + wynik_gry_int);
    }
}