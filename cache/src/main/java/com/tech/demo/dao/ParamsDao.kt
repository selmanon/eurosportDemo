package com.tech.demo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tech.demo.db.Constants
import com.tech.demo.entity.ParamsDataBaseEntity
import io.reactivex.Flowable

@Dao
abstract class ParamsDao {

    @Query(Constants.Params.QUERY_PARAMS)
    abstract fun getParams(): Flowable<ParamsDataBaseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveParams(params: ParamsDataBaseEntity)
}