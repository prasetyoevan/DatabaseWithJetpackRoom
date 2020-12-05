package id.vanevaan.kelas.databasewithroom.adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import id.vanevaan.kelas.databasewithroom.R;
import id.vanevaan.kelas.databasewithroom.activities.EditActivity;
import id.vanevaan.kelas.databasewithroom.constants.Constants;
import id.vanevaan.kelas.databasewithroom.database.AppDatabase;
import id.vanevaan.kelas.databasewithroom.database.AppExecutors;
import id.vanevaan.kelas.databasewithroom.model.Member;

import java.util.List;

public class MemberAdaptor extends RecyclerView.Adapter<MemberAdaptor.MyViewHolder> {
    private Context context;
    private List<Member> mMemberList;

    public MemberAdaptor(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.member_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberAdaptor.MyViewHolder myViewHolder, int i) {
        myViewHolder.nama.setText(mMemberList.get(i).getNama());
        myViewHolder.tanggalLahir.setText(mMemberList.get(i).getTanggalLahir());
        myViewHolder.golonganDarah.setText(mMemberList.get(i).getGolonganDarah());
        myViewHolder.horoskop.setText(mMemberList.get(i).getHoroskop());
        myViewHolder.namaPanggilan.setText(mMemberList.get(i).getNamaPanggilan());
    }

    @Override
    public int getItemCount() {
        if (mMemberList == null) {
            return 0;
        }
        return mMemberList.size();

    }

    public void setTasks(List<Member> memberList) {
        mMemberList = memberList;
        notifyDataSetChanged();
    }

    public List<Member> getTasks() {

        return mMemberList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nama, tanggalLahir, golonganDarah, horoskop, namaPanggilan;
        ImageView editImage;
        AppDatabase mDb;

        MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            mDb = AppDatabase.getInstance(context);
            nama = itemView.findViewById(R.id.member_nama);
            tanggalLahir = itemView.findViewById(R.id.member_tanggalLahir);
            golonganDarah = itemView.findViewById(R.id.member_golonganDarah);
            horoskop = itemView.findViewById(R.id.member_horoskop);
            namaPanggilan = itemView.findViewById(R.id.member_namaPanggilan);
            editImage = itemView.findViewById(R.id.edit_Image);
            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int elementId = mMemberList.get(getAdapterPosition()).getId();
                    Intent i = new Intent(context, EditActivity.class);
                    i.putExtra(Constants.UPDATE_Member_Id, elementId);
                    context.startActivity(i);
                }
            });
        }
    }
}
