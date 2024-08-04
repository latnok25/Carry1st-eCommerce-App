package com.latnok.carry1stecommerceapp.di

import android.content.Context
import androidx.room.Room
import com.latnok.data.api.ApiService
import com.latnok.data.database.AppDatabase
import com.latnok.data.database.CartItemDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.latnok.data.repository.ProductRepositoryImpl
import com.latnok.domain.repository.ProductRepository

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductRepository(apiService: ApiService): ProductRepository {
        return ProductRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).addMigrations(MIGRATION_1_2).build()
    }

    @Provides
    fun provideCartItemDAO(appDatabase: AppDatabase): CartItemDAO {
        return appDatabase.cartItemDAO()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Create a new table with the new schema
            database.execSQL("""
            CREATE TABLE cart_items_new (
                uid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                id INTEGER NOT NULL,
                name TEXT NOT NULL,
                quantity INTEGER NOT NULL,
                price REAL NOT NULL,
                imageLocation TEXT NOT NULL,
                currencyCode TEXT NOT NULL,
                currencySymbol TEXT NOT NULL,
                description TEXT NOT NULL,
                status TEXT NOT NULL
            )
        """)

            // Copy data from the old table to the new table
            database.execSQL("""
            INSERT INTO cart_items_new (id, name, quantity, price, imageLocation, currencyCode, currencySymbol, description, status)
            SELECT id, name, quantity, price, imageLocation, currencyCode, currencySymbol, description, status
            FROM cart_items
        """)

            // Remove the old table
            database.execSQL("DROP TABLE cart_items")

            // Rename the new table to the old table's name
            database.execSQL("ALTER TABLE cart_items_new RENAME TO cart_items")
        }
    }

}