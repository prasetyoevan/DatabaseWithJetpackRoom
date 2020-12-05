package id.vanevaan.kelas.databasewithroom.database;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import id.vanevaan.kelas.databasewithroom.model.Member;

import java.util.List;

@Dao
public interface MemberDao {

    @Query("SELECT * FROM MEMBER ORDER BY ID")
    List<Member> loadAllMembers();

    @Insert
    void insertMember(Member member);

    @Update
    void updateMember(Member member);

    @Delete
    void delete(Member member);

    @Query("SELECT * FROM MEMBER WHERE id = :id")
    Member loadMemberById(int id);
}
