package id.vanevaan.kelas.databasewithroom.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import id.vanevaan.kelas.databasewithroom.R;
import id.vanevaan.kelas.databasewithroom.constants.Constants;
import id.vanevaan.kelas.databasewithroom.database.AppDatabase;
import id.vanevaan.kelas.databasewithroom.database.AppExecutors;
import id.vanevaan.kelas.databasewithroom.model.Member;

public class EditActivity extends AppCompatActivity {
    EditText nama, tanggalLahir, golonganDarah, horoskop, namaPanggilan;
    Button button;
    int mMemberId;
    Intent intent;
    private AppDatabase mDb;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initViews();
        mDb = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.UPDATE_Member_Id)) {
            button.setText("Update");

            mMemberId = intent.getIntExtra(Constants.UPDATE_Member_Id, -1);

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    Member member = mDb.memberDao().loadMemberById(mMemberId);
                    populateUI(member);
                }
            });


        }

    }

    private void populateUI(Member member) {

        if (member == null) {
            return;
        }

        nama.setText(member.getNama());
        tanggalLahir.setText(member.getTanggalLahir());
        golonganDarah.setText(member.getGolonganDarah());
        horoskop.setText(member.getHoroskop());
        namaPanggilan.setText(member.getNamaPanggilan());
    }

    private void initViews() {
        nama = findViewById(R.id.edit_nama);
        tanggalLahir = findViewById(R.id.edit_tanggalLahir);
        golonganDarah = findViewById(R.id.edit_golonganDarah);
        horoskop = findViewById(R.id.edit_horoskop);
        namaPanggilan = findViewById(R.id.edit_namaPanggilan);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveButtonClicked();
            }
        });
    }

    public void onSaveButtonClicked() {
        final Member member = new Member(
                nama.getText().toString(),
                tanggalLahir.getText().toString(),
                golonganDarah.getText().toString(),
                horoskop.getText().toString(),
                namaPanggilan.getText().toString());

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (!intent.hasExtra(Constants.UPDATE_Member_Id)) {
                    mDb.memberDao().insertMember(member);
                } else {
                    member.setId(mMemberId);
                    mDb.memberDao().updateMember(member);
                }
                finish();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}