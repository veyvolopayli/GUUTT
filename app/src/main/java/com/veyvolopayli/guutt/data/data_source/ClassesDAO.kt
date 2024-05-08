package com.veyvolopayli.guutt.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.veyvolopayli.guutt.domain.model.ClassDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassesDAO {
    @Query("SELECT * FROM classdbo WHERE `group`=:group ORDER BY start")
    fun getGroupClasses(group: String): Flow<List<ClassDBO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClasses(classes: List<ClassDBO>)
}