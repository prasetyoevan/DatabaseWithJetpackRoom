package id.vanevaan.kelas.databasewithroom.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "member")
public class Member {
    @PrimaryKey(autoGenerate = true)
    int id;
    String nama;
    String tanggalLahir;
    String golonganDarah;
    String horoskop;
    String namaPanggilan;

    @Ignore
    public Member(String nama, String tanggalLahir, String golonganDarah, String horoskop, String namaPanggilan) {
        this.nama = nama;
        this.tanggalLahir = tanggalLahir;
        this.golonganDarah = golonganDarah;
        this.horoskop = horoskop;
        this.namaPanggilan = namaPanggilan;
    }

    public Member(int id, String nama, String tanggalLahir, String golonganDarah, String horoskop, String namaPanggilan) {
        this.id = id;
        this.nama = nama;
        this.tanggalLahir = tanggalLahir;
        this.golonganDarah = golonganDarah;
        this.horoskop = horoskop;
        this.namaPanggilan = namaPanggilan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getGolonganDarah() {
        return golonganDarah;
    }

    public void setGolonganDarah(String golonganDarah) {
        this.golonganDarah = golonganDarah;
    }

    public String getHoroskop() {
        return horoskop;
    }

    public void setHoroskop(String horoskop) {
        this.horoskop = horoskop;
    }

    public String getNamaPanggilan() {
        return namaPanggilan;
    }

    public void setNamaPanggilan(String namaPanggilan) {
        this.namaPanggilan = namaPanggilan;
    }
}
