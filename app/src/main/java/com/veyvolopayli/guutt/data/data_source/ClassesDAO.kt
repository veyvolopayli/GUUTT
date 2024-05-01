package com.veyvolopayli.guutt.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.veyvolopayli.guutt.domain.model.ClassObjectWithNote
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassesDAO {
    @Query("SELECT * FROM classobjectwithnote WHERE `group`=:group")
    fun getGroupClasses(group: String): Flow<List<ClassObjectWithNote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClasses(classes: List<ClassObjectWithNote>)
}