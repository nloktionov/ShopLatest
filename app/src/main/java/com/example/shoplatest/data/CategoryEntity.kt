package com.example.shoplatest.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class CategoryEntity(

    @PrimaryKey
    var categoryName: String,

    @ColumnInfo
    var categoryImage: String
) {
}