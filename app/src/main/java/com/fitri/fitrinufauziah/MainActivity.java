package com.fitri.fitrinufauziah;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //jika "tulisan berwarna merah".
    //klik tulisan

    EditText etNama, etAlamat;
    Spinner spProdi;
    CheckBox cbValid;
    RadioGroup rgLulusan;
    Button btSimpan, btHapus, btToast, btNotifikasi, bt_exit, bt_snack_bar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //definisi dan sambungkan objek yg ada pada XML
        etNama = (EditText) findViewById(R.id.et_nama);
        etAlamat = (EditText) findViewById(R.id.et_alamat);
        spProdi = (Spinner) findViewById(R.id.sp_prodi);
        rgLulusan = (RadioGroup) findViewById(R.id.rg_lulusan);
        cbValid = (CheckBox) findViewById(R.id.cb_valid);
        btSimpan = (Button) findViewById(R.id.bt_simpan);
        btHapus = (Button) findViewById(R.id.bt_hapus);
        btToast = (Button) findViewById(R.id.bt_toast);
        btNotifikasi = (Button) findViewById(R.id.bt_notifikasi);
        bt_exit =(Button) findViewById(R.id.bt_exit);
        bt_snack_bar = (Button) findViewById(R.id.bt_snack_bar);




        final RadioButton rbSMA = (RadioButton) findViewById(R.id.rb_SMA);
        rbSMA.setChecked(true);

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama, alamat, prodi, lulusan;
                boolean valid;

                nama = etNama.getText().toString();
                alamat = etAlamat.getText().toString();
                prodi = spProdi.getSelectedItem().toString();

                //ambil nilai radiogrup
                int selectedId = rgLulusan.getCheckedRadioButtonId();
                final RadioButton rbLulus = (RadioButton) findViewById(selectedId);

                lulusan = rbLulus.getText().toString();

                //ambil nilai dari checkbox
                valid = cbValid.isChecked();

                if (!valid){
                    Toast.makeText(
                            getApplicationContext(),
                            "Formulir belum terkonfirmasi",
                            Toast.LENGTH_SHORT)
                            .show();
                    return;
                }

                if (nama.length() == 0 || alamat.length() == 0 ) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Lengkapi Formulir",
                            Toast.LENGTH_SHORT)
                            .show();

                    return;
                }

                // UNTUK MELEMPAR/MENYELIPKAN DATA YANG SUDAH DI INPUT KE HALAMAN SELANJUTNYA
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("i_nama", nama);
                i.putExtra("i_alamat", alamat);
                i.putExtra("i_prodi", prodi);
                i.putExtra("i_lulusan", lulusan);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(i);

            }
        });

        //EVENT UNTUK TOMBOL TOAST
        btToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        getApplicationContext(),
                        "Hai",
                        Toast.LENGTH_SHORT)
                        .show();

                int selectedId = rgLulusan.getCheckedRadioButtonId();
                final RadioButton rbLulus = (RadioButton) findViewById(selectedId);

                Log.d("test",
                        etAlamat.getText().toString() + " "
                                + spProdi.getSelectedItem().toString() + " "
                                + rbLulus.getText().toString());


            }
        });

        btNotifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getApplicationContext(), "notify_001");
                Intent ii = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, ii, 0);

                NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
                bigText.setBigContentTitle("Aplikasi buatanku");
                bigText.setSummaryText("Ini adalah notifikasi dariku");

                mBuilder.setContentIntent(pendingIntent);
                mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
                mBuilder.setContentTitle("Aplikasi buatanku");
                mBuilder.setContentText("Notifikasi ini menggunakan versi terbaru");
                mBuilder.setPriority(Notification.PRIORITY_MAX);
                mBuilder.setStyle(bigText);
                mBuilder.setDefaults(Notification.DEFAULT_SOUND); //suara
                mBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000, 1000}); //getar

                NotificationManager mNotificationManager =
                        (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("notify_001",
                            "Channel human readable title",
                            NotificationManager.IMPORTANCE_DEFAULT);
                    mNotificationManager.createNotificationChannel(channel);
                }

                mNotificationManager.notify(0, mBuilder.build());
            }
        });

        btHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.drawable.ic_launcher)
                        .setTitle("Hapus Data Ini")
                        .setMessage("Ini dialog Box")
                        .setCancelable(true)
                        .setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(
                                                getApplicationContext(),
                                                "OK ditekan",
                                                Toast.LENGTH_SHORT)
                                                .show();

                                    }
                                }
                        )
                        .setNegativeButton(
                                "Batal",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(
                                                getApplicationContext(),
                                                "Batal Di Tekan",
                                                Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                }
                        )
                        .show();
            }
        });

        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_snack_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view =findViewById(R.id.main_layout_id);
                String pesan ="Hai... Aku snackbar";
                int durasi = Snackbar.LENGTH_SHORT;
                Snackbar.make(view, pesan, durasi).show();
            }
        });
    }
    //oncreate menu nya alt+insert -> overide methods -> oncreateoptionmenu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //memanggil layout menunya
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_nggu, menu);

        //definisi searchview agar dapat diolah textnya
        MenuItem menuItem = menu.findItem(R.id.mn_cari);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getApplicationContext(),
                    newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    // menu perlu diberikan aksi alt+insert -> overide methods -> onoptionitemselected


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_alert:

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.drawable.ic_launcher)
                        .setTitle("Hapus Data Ini")
                        .setMessage("Ini dialog Box")
                        .setCancelable(true)
                        .setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(
                                                getApplicationContext(),
                                                "OK ditekan",
                                                Toast.LENGTH_SHORT)
                                                .show();

                                    }
                                });

            case R.id.mn_toast:
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


    }
