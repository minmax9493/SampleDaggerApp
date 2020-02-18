package uz.minmax.sampledaggerapp.data.source.local.dao

import androidx.room.*

@Dao
interface BaseDao <T>{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t:T):Long

    @Update
    fun update(t:T)

    @Delete
    fun delete(t:T)
}