package com.katana.jeu.Modes;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.katana.jeu.R;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.HashMap;

@SuppressLint("SetTextI18n")
public class Multijoueur_Activity extends AppCompatActivity {

    ImageView p1, p2, p3, p4, p5, p6, p7, p8, p9;
    TextView txt_rond, txt_croix, code;
    Dialog tourDialog, revengeDialog;
    CardView code_card, quitter;

    int rond = 0, croix = 0;

    String _1, _2, _3, _4, _5, _6, _7, _8, _9;
    String player, forme, room, tour, tour_after, croix_ready, rond_ready;
    String gameWinned = "false";
    String gagnant = "none";
    String leave = "none";

    private ClipData myClip;
    private ClipboardManager myClipboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);

        tourDialog = new Dialog(Multijoueur_Activity.this);
        tourDialog.setContentView(R.layout.dialog_tour);
        tourDialog.setCancelable(false);
        tourDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tourDialog.create();

        revengeDialog = new Dialog(Multijoueur_Activity.this);
        revengeDialog.setContentView(R.layout.dialog_multijoueur_attente);
        revengeDialog.setCancelable(false);
        revengeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        revengeDialog.create();

        p1=findViewById(R.id.btn_P1);
        p2=findViewById(R.id.btn_P2);
        p3=findViewById(R.id.btn_P3);
        p4=findViewById(R.id.btn_P4);
        p5=findViewById(R.id.btn_P5);
        p6=findViewById(R.id.btn_P6);
        p7=findViewById(R.id.btn_P7);
        p8=findViewById(R.id.btn_P8);
        p9=findViewById(R.id.btn_P9);
        code=findViewById(R.id.code);
        txt_rond=findViewById(R.id.txt_rond);
        txt_croix=findViewById(R.id.txt_croix);
        code_card=findViewById(R.id.cv_code);
        quitter=findViewById(R.id.cv_leave);

        PushDownAnim.setPushDownAnimTo(p1,p2,p3,p4,p5,p6,p7,p8,p9,code_card,quitter);

        Intent intent = getIntent();
        forme = intent.getStringExtra("forme");
        player = intent.getStringExtra("player");
        room = intent.getStringExtra("value");
        code.setText("Code : "+ room);

        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        code_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myClip = ClipData.newPlainText("text", room);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(Multijoueur_Activity.this, "Numéro de salle copié", Toast.LENGTH_SHORT).show();
            }
        });

        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_quitter();
            }
        });

        if (forme.equals("none")){
            code_card.setVisibility(View.GONE);
            dialog_choix();
        } else {
            if (forme.equals("croix")){
                tour_after="rond";
                txt_croix.setText("0 :Moi");
            } else if (forme.equals("rond")){
                tour_after="croix";
                txt_rond.setText("Moi: 0");
            }
        }

        p1.setOnClickListener(view -> {
            if (_1==null || _1.equals("null") || _1.equals("c1")){
                setEnabledFalse();
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("tour").setValue(tour_after);
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("1").setValue(forme);
            }
        });

        p2.setOnClickListener(view -> {
            if (_2==null || _2.equals("null") || _2.equals("c2")){
                setEnabledFalse();
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("tour").setValue(tour_after);
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("2").setValue(forme);
            }
        });

        p3.setOnClickListener(view -> {
            if (_3==null || _3.equals("null") || _3.equals("c3")){
                setEnabledFalse();
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("tour").setValue(tour_after);
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("3").setValue(forme);
            }
        });

        p4.setOnClickListener(view -> {
            if (_4==null || _4.equals("null") || _4.equals("c4")){
                setEnabledFalse();
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("tour").setValue(tour_after);
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("4").setValue(forme);
            }
        });

        p5.setOnClickListener(view -> {
            if (_5==null || _5.equals("null") || _5.equals("c5")){
                setEnabledFalse();
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("tour").setValue(tour_after);
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("5").setValue(forme);
            }
        });

        p6.setOnClickListener(view -> {
            if (_6==null || _6.equals("null") || _6.equals("c6")){
                setEnabledFalse();
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("tour").setValue(tour_after);
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("6").setValue(forme);
            }
        });

        p7.setOnClickListener(view -> {
            if (_7==null || _7.equals("null") || _7.equals("c7")){
                setEnabledFalse();
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("tour").setValue(tour_after);
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("7").setValue(forme);
            }
        });

        p8.setOnClickListener(view -> {
            if (_8==null || _8.equals("null") || _8.equals("c8")){
                setEnabledFalse();
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("tour").setValue(tour_after);
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("8").setValue(forme);
            }
        });

        p9.setOnClickListener(view -> {
            if (_9==null || _9.equals("null") || _9.equals("c9")){
                setEnabledFalse();
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("tour").setValue(tour_after);
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("9").setValue(forme);
            }
        });

        loadInRealTime();
    }

    private void setEnabledFalse() {
        p1.setEnabled(false);
        p2.setEnabled(false);
        p3.setEnabled(false);
        p4.setEnabled(false);
        p5.setEnabled(false);
        p6.setEnabled(false);
        p7.setEnabled(false);
        p8.setEnabled(false);
        p9.setEnabled(false);
    }
    private void setEnabledtrue() {
        p1.setEnabled(true);
        p2.setEnabled(true);
        p3.setEnabled(true);
        p4.setEnabled(true);
        p5.setEnabled(true);
        p6.setEnabled(true);
        p7.setEnabled(true);
        p8.setEnabled(true);
        p9.setEnabled(true);
    }

    private void loadInRealTime() {
        FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                _1 = String.valueOf(snapshot.child("1").getValue());
                _2 = String.valueOf(snapshot.child("2").getValue());
                _3 = String.valueOf(snapshot.child("3").getValue());
                _4 = String.valueOf(snapshot.child("4").getValue());
                _5 = String.valueOf(snapshot.child("5").getValue());
                _6 = String.valueOf(snapshot.child("6").getValue());
                _7 = String.valueOf(snapshot.child("7").getValue());
                _8 = String.valueOf(snapshot.child("8").getValue());
                _9 = String.valueOf(snapshot.child("9").getValue());
                tour = String.valueOf(snapshot.child("tour").getValue());
                croix_ready = String.valueOf(snapshot.child("croix_ready").getValue());
                rond_ready = String.valueOf(snapshot.child("rond_ready").getValue());
                String deconnexion = String.valueOf(snapshot.child("deconnexion").getValue());

                if (_1.equals("rond")){
                    p1.setImageResource((R.drawable.ic_jeu_rond));
                } else if (_1.equals("croix")){
                    p1.setImageResource((R.drawable.ic_jeu_croix));
                }

                if (_2.equals("rond")){
                    p2.setImageResource((R.drawable.ic_jeu_rond));
                } else if (_2.equals("croix")){
                    p2.setImageResource((R.drawable.ic_jeu_croix));
                }

                if (_3.equals("rond")){
                    p3.setImageResource((R.drawable.ic_jeu_rond));
                } else if (_3.equals("croix")){
                    p3.setImageResource((R.drawable.ic_jeu_croix));
                }

                if (_4.equals("rond")){
                    p4.setImageResource((R.drawable.ic_jeu_rond));
                } else if (_4.equals("croix")){
                    p4.setImageResource((R.drawable.ic_jeu_croix));
                }

                if (_5.equals("rond")){
                    p5.setImageResource((R.drawable.ic_jeu_rond));
                } else if (_5.equals("croix")){
                    p5.setImageResource((R.drawable.ic_jeu_croix));
                }

                if (_6.equals("rond")){
                    p6.setImageResource((R.drawable.ic_jeu_rond));
                } else if (_6.equals("croix")){
                    p6.setImageResource((R.drawable.ic_jeu_croix));
                }

                if (_7.equals("rond")){
                    p7.setImageResource((R.drawable.ic_jeu_rond));
                } else if (_7.equals("croix")){
                    p7.setImageResource((R.drawable.ic_jeu_croix));
                }

                if (_8.equals("rond")){
                    p8.setImageResource((R.drawable.ic_jeu_rond));
                } else if (_8.equals("croix")){
                    p8.setImageResource((R.drawable.ic_jeu_croix));
                }

                if (_9.equals("rond")){
                    p9.setImageResource((R.drawable.ic_jeu_rond));
                } else if (_9.equals("croix")){
                    p9.setImageResource((R.drawable.ic_jeu_croix));
                }

                if (gameWinned.equals("false")){
                    if (tour.equals(forme) || forme.equals("none")){
                        tourDialog.dismiss();
                        setEnabledtrue();
                    } else {
                        tourDialog.show();
                        setEnabledFalse();
                    }
                    checkWin();
                } else {
                    checkForRevenge();
                }

                if (!leave.equals("moi")){
                    if (deconnexion.equals("true")){
                        dialog_deconnexion();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void checkForRevenge() {
        if (croix_ready!=null && rond_ready!=null && croix_ready.equals("ok") && rond_ready.equals("ok")){
            revengeDialog.dismiss();

            if (gagnant.equals("moi")){
                FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("tour").setValue(forme);
                tourDialog.dismiss();
            } else if (gagnant.equals("pas_moi")){
                tourDialog.show();
            } else if (gagnant.equals(forme)){
                tourDialog.dismiss();
            } else {
                tourDialog.show();
            }

            reset();

        }
    }
    private void checkWin() {
        if (_1.equals(_2) && _2.equals(_3) || _1.equals(_4) && _4.equals(_7) || _2.equals(_5) && _5.equals(_8) || _3.equals(_6) && _6.equals(_9) || _4.equals(_5) && _5.equals(_6) || _7.equals(_8) && _8.equals(_9) || _1.equals(_5) && _5.equals(_9) || _3.equals(_5) && _5.equals(_7)) {
            if (tour.equals(forme)) {
                if (forme.equals("croix")){
                    rond=rond+1;
                    txt_rond.setText("Rond: "+rond);
                } else {
                    croix=croix+1;
                    txt_croix.setText(croix + " :Croix");
                }

                gameWinned="true";
                gagnant="pas_moi";
                dialog_defaite();

            } else {
                if (forme.equals("croix")){
                    croix=croix+1;
                    txt_croix.setText(croix + " :Moi");
                } else {
                    rond=rond+1;
                    txt_rond.setText("Moi: "+rond);
                }

                gameWinned="true";
                gagnant="moi";
                dialog_victoire();
            }

        }else{
            if (!_1.equals("c1") && !_2.equals("c2") && !_3.equals("c3")  && !_4.equals("c4") && !_5.equals("c5") && !_6.equals("c6") && !_7.equals("c7")  && !_8.equals("c8") && !_9.equals("c9")){
                gameWinned="true";
                gagnant=tour;

                dialog_match_nul();
            }
        }

    }
    private void reset() {
        p1.setImageResource((R.drawable.item_png));
        p2.setImageResource((R.drawable.item_png));
        p3.setImageResource((R.drawable.item_png));
        p4.setImageResource((R.drawable.item_png));
        p5.setImageResource((R.drawable.item_png));
        p6.setImageResource((R.drawable.item_png));
        p7.setImageResource((R.drawable.item_png));
        p8.setImageResource((R.drawable.item_png));
        p9.setImageResource((R.drawable.item_png));

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("1", "c1");
        hashMap.put("2", "c2");
        hashMap.put("3", "c3");
        hashMap.put("4", "c4");
        hashMap.put("5", "c5");
        hashMap.put("6", "c6");
        hashMap.put("7", "c7");
        hashMap.put("8", "c8");
        hashMap.put("9", "c9");
        hashMap.put("croix_ready", "none");
        hashMap.put("rond_ready", "none");
        FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                gameWinned="false";
            }
        });
    }

    private void dialog_quitter() {
        Dialog dialog = new Dialog(Multijoueur_Activity.this);
        dialog.setContentView(R.layout.dialog_multijoueur_confirmer);
        dialog.setCancelable(false);

        CardView ok = dialog.findViewById(R.id.btn_ok);
        CardView non = dialog.findViewById(R.id.btn_non);

        ok.setOnClickListener(view -> {
            dialog.dismiss();
            leave="moi";
            finish();
        });

        non.setOnClickListener(view -> {
            dialog.dismiss();
        });

        dialog.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    private void dialog_deconnexion() {
        Dialog dialog = new Dialog(Multijoueur_Activity.this);
        dialog.setContentView(R.layout.dialog_multijoueur_deconnexion);
        dialog.setCancelable(false);

        CardView ok = dialog.findViewById(R.id.btn_ok);

        ok.setOnClickListener(view -> {
            finish();
        });

        dialog.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    private void dialog_victoire() {
        Dialog dialog = new Dialog(Multijoueur_Activity.this);
        dialog.setContentView(R.layout.dialog_multijoueur_victoire);
        dialog.setCancelable(false);

        CardView ok = dialog.findViewById(R.id.btn_ok);

        ok.setOnClickListener(view -> {
            dialog.dismiss();
            FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child(forme+"_ready").setValue("ok");
            revengeDialog.show();
            tourDialog.dismiss();
        });

        dialog.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    private void dialog_match_nul() {
        Dialog dialog = new Dialog(Multijoueur_Activity.this);
        dialog.setContentView(R.layout.dialog_matchnul);
        dialog.setCancelable(false);

        CardView ok = dialog.findViewById(R.id.btn_ok);

        ok.setOnClickListener(view -> {
            dialog.dismiss();
            FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child(forme+"_ready").setValue("ok");
            revengeDialog.show();
            tourDialog.dismiss();
        });

        dialog.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }
    private void dialog_defaite() {
        Dialog dialog = new Dialog(Multijoueur_Activity.this);
        dialog.setContentView(R.layout.dialog_multijoueur_defaite);
        dialog.setCancelable(false);

        CardView ok = dialog.findViewById(R.id.btn_ok);

        ok.setOnClickListener(view -> {
            dialog.dismiss();
            FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child(forme+"_ready").setValue("ok");
            revengeDialog.show();
            tourDialog.dismiss();
        });

        dialog.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    private void dialog_choix(){
        Dialog dialog = new Dialog(Multijoueur_Activity.this);
        dialog.setContentView(R.layout.dialog_multijoueur_choose);
        dialog.setCancelable(false);

        CardView rond = dialog.findViewById(R.id.btn_rond);
        CardView croix = dialog.findViewById(R.id.btn_croix);

        rond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase.getInstance().getReference("Room").child(room).child("player1").setValue("rond").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        forme="rond";
                        tour_after="croix";
                        txt_rond.setText("Moi: 0");
                        code_card.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                        FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("tour").setValue("rond");
                    }
                });
            }
        });

        croix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase.getInstance().getReference("Room").child(room).child("player1").setValue("croix").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        forme="croix";
                        tour_after="rond";
                        txt_croix.setText("0 :Moi");
                        code_card.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                        FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("tour").setValue("croix");
                    }
                });
            }
        });

        dialog.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        leave="moi";
        FirebaseDatabase.getInstance().getReference("Room").child(room).child("gameplay").child("deconnexion").setValue("true");
    }
}