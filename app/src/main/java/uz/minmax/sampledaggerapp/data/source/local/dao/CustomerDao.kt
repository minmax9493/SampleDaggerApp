package uz.minmax.sampledaggerapp.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.minmax.sampledaggerapp.data.models.Customer

@Dao
interface CustomerDao:
    BaseDao<Customer> {

    @Query("select * from customer_table order by name ASC")
    fun getCustomers(): LiveData<List<Customer>>

    @Insert
    fun insertAll(customers:List<Customer>)

}