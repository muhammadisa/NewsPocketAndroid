package com.xoxoer.newspocket.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xoxoer.newspocket.model.example.Example
import io.reactivex.Single

@Dao
interface ExampleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExample(example: Example)

    @Query("SELECT * FROM example WHERE id = :id_")
    fun getExample(id_: Int): Single<Example>

    @Query("DELETE FROM example")
    fun deleteExample()

}