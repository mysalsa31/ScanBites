package com.example.scanbite_v1_camera_function.util



import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query



@Dao
interface AccountDao {

    //there is an upsert function which update an existing ,
    // but we need insert.
    @Insert
    suspend fun insertAccount(account: Account)

    @Delete
    suspend fun deleteContact(account: Account)

    @Query("SELECT * FROM account ORDER BY firstName ASC")
    fun getAccountOrderedByFirstName(): LiveData<List<Account>>

    @Query("SELECT * FROM account ORDER BY lastName ASC")
    fun getAccountOrderedByLastName(): LiveData<List<Account>>

    @Query("SELECT * FROM account ORDER BY phoneNumber ASC")
    fun getAccountOrderedByPhoneNumber(): LiveData<List<Account>>


}