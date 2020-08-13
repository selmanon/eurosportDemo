package com.tech.demo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tech.demo.db.Constants

@Entity(tableName = Constants.Params.TABLE_NAME)
data  class ParamsDataBaseEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = -1,
    var lastCacheTime: Long)