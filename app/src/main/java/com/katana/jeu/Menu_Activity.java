package com.katana.jeu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.katana.jeu.Modes.Multijoueur_Activity;
import com.katana.jeu.Modes.Solo_Activity;

import java.util.HashMap;
import java.util.Random;

public class Menu_Activity extends AppCompatActivity {

    String room;
    String a, b, c, d ="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        dialogMenu();
    }


    private void dialogMenu() {
        Dialog dialog = new Dialog(Menu_Activity.this);
        dialog.setContentView(R.layout.dialog_menu);
        dialog.setCancelable(false);

        CardView solo = dialog.findViewById(R.id.btn_solo);
        CardView multi = dialog.findViewById(R.id.btn_multi);

        multi.setOnClickListener(view -> dialogChoose());
        solo.setOnClickListener(view -> startActivity(new Intent(Menu_Activity.this, Solo_Activity.class)));

        dialog.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    private void dialogChoose() {
        Dialog dialog = new Dialog(Menu_Activity.this);
        dialog.setContentView(R.layout.dialog_room);
        dialog.setCancelable(false);

        CardView join = dialog.findViewById(R.id.btn_join);
        CardView create = dialog.findViewById(R.id.btn_create);

        create.setOnClickListener(view -> {

            Random random = new Random();
            int value = random.nextInt(1 + 9999) + 1;

            if (value<10){
                room =value+"000";
            } else if (value<100){
                room =value+"00";
            } if (value<1000){
                room =value+"0";
            } else {
                room = value+"";
            }

            HashMap<Object, String> hashMap = new HashMap<>();
            hashMap.put("room", room);
            hashMap.put("enabled", "true");
            FirebaseDatabase.getInstance().getReference("Room").child(room).setValue(hashMap);

            HashMap<Object, String> hashMap2 = new HashMap<>();
            hashMap2.put("1", "c1");
            hashMap2.put("2", "c2");
            hashMap2.put("3", "c3");
            hashMap2.put("4", "c4");
            hashMap2.put("5", "c5");
            hashMap2.put("6", "c6");
            hashMap2.put("7", "c7");
            hashMap2.put("8", "c8");
            hashMap2.put("9", "c9");
            FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").setValue(hashMap2);

            Intent intent = new Intent(getApplicationContext(), Multijoueur_Activity.class);
            intent.putExtra("value", room);
            intent.putExtra("forme", "none");
            intent.putExtra("player", "player1");
            startActivity(intent);

            dialog.dismiss();

        });

        join.setOnClickListener(view -> {
            dialogJoin();
            dialog.dismiss();
        });

        dialog.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    private void dialogJoin() {
        Dialog dialog = new Dialog(Menu_Activity.this);
        dialog.setContentView(R.layout.dialog_room_imput);
        dialog.setCancelable(false);

        EditText et1 = dialog.findViewById(R.id.et1);
        EditText et2 = dialog.findViewById(R.id.et2);
        EditText et3 = dialog.findViewById(R.id.et3);
        EditText et4 = dialog.findViewById(R.id.et4);

        TextView txt = dialog.findViewById(R.id.txt_btn);
        ProgressBar pb = dialog.findViewById(R.id.pb_btn);
        CardView ok = dialog.findViewById(R.id.btn_ok);

        et1.requestFocus();
        showKeyboard();

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (et1.length()==1){

                    et2.requestFocus();
                    a=et1.getText().toString();
                    et1.setSelection(1);

                } else if (et1.length()==0){

                    et1.requestFocus();
                    et1.setSelection(0);

                } else {

                    int x = et1.getText().toString().indexOf(a);

                    if (x==0){
                        et1.setText(et1.getText().toString().subSequence(1,2));
                    } else {
                        et1.setText(et1.getText().toString().subSequence(0,1));
                    }
                    et1.setSelection(1);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (et2.length()==1){

                    et3.requestFocus();
                    b=et2.getText().toString();
                    et2.setSelection(1);

                } else if (et2.length()==0){

                    et1.requestFocus();
                    et2.setSelection(0);

                } else {

                    int x = et2.getText().toString().indexOf(b);

                    if (x==0){
                        et2.setText(et2.getText().toString().subSequence(1,2));
                    } else {
                        et2.setText(et2.getText().toString().subSequence(0,1));
                    }
                    et2.setSelection(1);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (et3.length()==1){

                    c=et3.getText().toString();
                    et4.requestFocus();
                    et3.setSelection(1);

                } else if (et3.length()==0){

                    et2.requestFocus();
                    et3.setSelection(0);

                } else {

                    int x = et3.getText().toString().indexOf(c);

                    if (x==0){
                        et3.setText(et3.getText().toString().subSequence(1,2));
                    } else {
                        et3.setText(et3.getText().toString().subSequence(0,1));
                    }
                    et3.setSelection(1);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (et4.length()==1){

                    d=et4.getText().toString();
                    et4.requestFocus();
                    et4.setSelection(1);

                } else if (et4.length()==0){

                    et3.requestFocus();
                    et4.setSelection(0);

                } else {

                    int x = et4.getText().toString().indexOf(d);

                    if (x==0){
                        et4.setText(et4.getText().toString().subSequence(1,2));
                    } else {
                        et4.setText(et4.getText().toString().subSequence(0,1));
                    }
                    et4.setSelection(1);

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ok.setOnClickListener(view -> {
            txt.setVisibility(View.GONE);
            pb.setVisibility(View.VISIBLE);

            String final_code = et1.getText().toString()+et2.getText().toString()+et3.getText().toString()+et4.getText().toString();
            Toast.makeText(getApplicationContext(), ""+final_code, Toast.LENGTH_SHORT).show();

            FirebaseDatabase.getInstance().getReference("Room").child(final_code).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){

                        String enabled = String.valueOf(snapshot.child("enabled").getValue());
                        String player1 = String.valueOf(snapshot.child("player1").getValue());
                        String player2;

                        if (enabled.equals("true")){

                            HashMap<String, Object> hashMap = new HashMap<>();
                            if (player1.equals("croix")){
                                player2="rond";
                            } else {
                                player2="croix";
                            }

                            hashMap.put("player2", player2);
                            hashMap.put("enabled", "false");
                            FirebaseDatabase.getInstance().getReference("Room").child(final_code).updateChildren(hashMap);

                            Intent intent = new Intent(getApplicationContext(), Multijoueur_Activity.class);
                            intent.putExtra("value", final_code);
                            intent.putExtra("forme", player2);
                            intent.putExtra("player", "player2");
                            startActivity(intent);

                            closeKeyboard();
                            dialog.dismiss();

                        } else {
                            dialog_deja_plein();
                            pb.setVisibility(View.GONE);
                            txt.setVisibility(View.VISIBLE);
                            closeKeyboard();
                        }


                    } else {
                        dialog_erreur();
                        pb.setVisibility(View.GONE);
                        txt.setVisibility(View.VISIBLE);
                        closeKeyboard();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        });

        dialog.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void dialog_deja_plein() {
        Dialog dialog = new Dialog(Menu_Activity.this);
        dialog.setContentView(R.layout.dialog_erreur_partie_pleine);
        dialog.setCancelable(false);

        CardView ok = dialog.findViewById(R.id.btn_ok);

        ok.setOnClickListener(view -> dialog.dismiss());

        dialog.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    private void dialog_erreur() {
        Dialog dialog = new Dialog(Menu_Activity.this);
        dialog.setContentView(R.layout.dialog_erreur_partie);
        dialog.setCancelable(false);

        CardView ok = dialog.findViewById(R.id.btn_ok);

        ok.setOnClickListener(view -> dialog.dismiss());

        dialog.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void showKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
    public void closeKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}