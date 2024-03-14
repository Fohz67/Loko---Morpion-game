package com.katana.jeu.Modes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.katana.jeu.R;

@SuppressLint("SetTextI18n")
public class Solo_Activity extends AppCompatActivity {

    ImageView p1, p2, p3, p4, p5, p6, p7, p8, p9;
    TextView txt_rond, txt_croix;
    CardView reset;

    int tour;
    int croix = 0 , rond = 0;

    String c1 = "c1", c2 = "c2", c3 = "c3", c4 = "c4", c5 = "c5", c6 = "c6", c7 = "c7", c8 = "c8", c9= "c9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1=findViewById(R.id.btn_P1);
        p2=findViewById(R.id.btn_P2);
        p3=findViewById(R.id.btn_P3);
        p4=findViewById(R.id.btn_P4);
        p5=findViewById(R.id.btn_P5);
        p6=findViewById(R.id.btn_P6);
        p7=findViewById(R.id.btn_P7);
        p8=findViewById(R.id.btn_P8);
        p9=findViewById(R.id.btn_P9);
        reset=findViewById(R.id.reset);

        txt_rond=findViewById(R.id.txt_rond);
        txt_croix=findViewById(R.id.txt_croix);

        dialog_choix();

        reset.setOnClickListener(view -> reset());

        p1.setOnClickListener(view -> {
            if (tour==0){
                p1.setImageResource((R.drawable.ic_jeu_rond));
                tour=1;
                c1="rond";
            } else {
                p1.setImageResource((R.drawable.ic_jeu_croix));
                tour=0;
                c1="croix";
            }
            p1.setEnabled(false);
            checkWin();
        });

        p2.setOnClickListener(view -> {
            if (tour==0){
                p2.setImageResource((R.drawable.ic_jeu_rond));
                tour=1;
                c2="rond";
            } else {
                p2.setImageResource((R.drawable.ic_jeu_croix));
                tour=0;
                c2="croix";
            }
            p2.setEnabled(false);
            checkWin();
        });

        p3.setOnClickListener(view -> {
            if (tour==0){
                p3.setImageResource((R.drawable.ic_jeu_rond));
                tour=1;
                c3="rond";
            } else {
                p3.setImageResource((R.drawable.ic_jeu_croix));
                tour=0;
                c3="croix";
            }
            p3.setEnabled(false);
            checkWin();
        });

        p4.setOnClickListener(view -> {
            if (tour==0){
                p4.setImageResource((R.drawable.ic_jeu_rond));
                tour=1;
                c4="rond";
            } else {
                p4.setImageResource((R.drawable.ic_jeu_croix));
                tour=0;
                c4="croix";
            }
            p4.setEnabled(false);
            checkWin();
        });

        p5.setOnClickListener(view -> {
            if (tour==0){
                p5.setImageResource((R.drawable.ic_jeu_rond));
                tour=1;
                c5="rond";
            } else {
                p5.setImageResource((R.drawable.ic_jeu_croix));
                tour=0;
                c5="croix";
            }
            p5.setEnabled(false);
            checkWin();
        });

        p6.setOnClickListener(view -> {
            if (tour==0){
                p6.setImageResource((R.drawable.ic_jeu_rond));
                tour=1;
                c6="rond";
            } else {
                p6.setImageResource((R.drawable.ic_jeu_croix));
                tour=0;
                c6="croix";
            }
            p6.setEnabled(false);
            checkWin();
        });

        p7.setOnClickListener(view -> {
            if (tour==0){
                p7.setImageResource((R.drawable.ic_jeu_rond));
                tour=1;
                c7="rond";

            } else {
                p7.setImageResource((R.drawable.ic_jeu_croix));
                tour=0;
                c7="croix";
            }
            p7.setEnabled(false);
            checkWin();
        });

        p8.setOnClickListener(view -> {
            if (tour==0){
                p8.setImageResource((R.drawable.ic_jeu_rond));
                tour=1;
                c8="rond";
            } else {
                p8.setImageResource((R.drawable.ic_jeu_croix));
                tour=0;
                c8="croix";
            }
            p8.setEnabled(false);
            checkWin();
        });

        p9.setOnClickListener(view -> {
            if (tour==0){
                p9.setImageResource((R.drawable.ic_jeu_rond));
                c9="rond";
                tour=1;
            } else {
                p9.setImageResource((R.drawable.ic_jeu_croix));
                tour=0;
                c9="croix";
            }
            p9.setEnabled(false);
            checkWin();
        });

    }

    private void checkWin() {

        if (c1.equals(c2) && c2.equals(c3) || c1.equals(c4) && c4.equals(c7) || c2.equals(c5) && c5.equals(c8) || c3.equals(c6) && c6.equals(c9) || c4.equals(c5) && c5.equals(c6) || c7.equals(c8) && c8.equals(c9) || c1.equals(c5) && c5.equals(c9) || c3.equals(c5) && c5.equals(c7)) {
            if (tour == 1) {
                dialog_victoire_rond();
                rond=rond+1;
                txt_rond.setText("Rond: "+rond);

            } else {
                dialog_victoire_croix();
                croix=croix+1;
                txt_croix.setText(croix + " :Croix");
            }

        }else{
            if (!c1.equals("c1") && !c2.equals("c2") && !c3.equals("c3")  && !c4.equals("c4") && !c5.equals("c5") && !c6.equals("c6") && !c7.equals("c7")  && !c8.equals("c8") && !c9.equals("c9")){
                dialog_match_nul();
            }
        }
    }

    private void dialog_choix(){
        Dialog dialog = new Dialog(Solo_Activity.this);
        dialog.setContentView(R.layout.dialog_choose);
        dialog.setCancelable(false);

        CardView android = dialog.findViewById(R.id.btn_rond);
        CardView ios = dialog.findViewById(R.id.btn_croix);

        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tour=0;
                dialog.dismiss();
            }
        });

        ios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tour=1;
                dialog.dismiss();
            }
        });

        dialog.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    private void dialog_match_nul() {
        Dialog dialog = new Dialog(Solo_Activity.this);
        dialog.setContentView(R.layout.dialog_matchnul);
        dialog.setCancelable(false);

        CardView ok = dialog.findViewById(R.id.btn_ok);

        ok.setOnClickListener(view -> {
            dialog.dismiss();
            semi_reset();
        });

        dialog.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    private void dialog_victoire_croix() {
        Dialog dialog = new Dialog(Solo_Activity.this);
        dialog.setContentView(R.layout.dialog_victoire_croix);
        dialog.setCancelable(false);

        CardView ok = dialog.findViewById(R.id.btn_ok);

        ok.setOnClickListener(view -> {
            dialog.dismiss();
            semi_reset();
        });

        dialog.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    private void dialog_victoire_rond() {
        Dialog dialog = new Dialog(Solo_Activity.this);
        dialog.setContentView(R.layout.dialog_victoire_rond);
        dialog.setCancelable(false);

        CardView ok = dialog.findViewById(R.id.btn_ok);

        ok.setOnClickListener(view -> {
            dialog.dismiss();
            semi_reset();
        });

        dialog.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void semi_reset() {
        c1 = "c1";
        c2 = "c2";
        c3 = "c3";
        c4 = "c4";
        c5 = "c5";
        c6 = "c6";
        c7 = "c7";
        c8 = "c8";
        c9 = "c9";

        p1.setImageResource((R.drawable.item_png));
        p2.setImageResource((R.drawable.item_png));
        p3.setImageResource((R.drawable.item_png));
        p4.setImageResource((R.drawable.item_png));
        p5.setImageResource((R.drawable.item_png));
        p6.setImageResource((R.drawable.item_png));
        p7.setImageResource((R.drawable.item_png));
        p8.setImageResource((R.drawable.item_png));
        p9.setImageResource((R.drawable.item_png));

        p1.setEnabled(true);
        p2.setEnabled(true);
        p3.setEnabled(true);
        p4.setEnabled(true);
        p5.setEnabled(true);
        p6.setEnabled(true);
        p7.setEnabled(true);
        p8.setEnabled(true);
        p9.setEnabled(true);

        dialog_choix();
    }
    private void reset(){
        c1 = "c1";
        c2 = "c2";
        c3 = "c3";
        c4 = "c4";
        c5 = "c5";
        c6 = "c6";
        c7 = "c7";
        c8 = "c8";
        c9 = "c9";

        p1.setImageResource((R.drawable.item_png));
        p2.setImageResource((R.drawable.item_png));
        p3.setImageResource((R.drawable.item_png));
        p4.setImageResource((R.drawable.item_png));
        p5.setImageResource((R.drawable.item_png));
        p6.setImageResource((R.drawable.item_png));
        p7.setImageResource((R.drawable.item_png));
        p8.setImageResource((R.drawable.item_png));
        p9.setImageResource((R.drawable.item_png));

        p1.setEnabled(true);
        p2.setEnabled(true);
        p3.setEnabled(true);
        p4.setEnabled(true);
        p5.setEnabled(true);
        p6.setEnabled(true);
        p7.setEnabled(true);
        p8.setEnabled(true);
        p9.setEnabled(true);

        croix = 0;
        rond = 0;

        txt_rond.setText("Rond: 0");
        txt_croix.setText("0 :Croix");

        dialog_choix();
    }
}