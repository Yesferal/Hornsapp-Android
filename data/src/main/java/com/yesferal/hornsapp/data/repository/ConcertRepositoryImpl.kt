package com.yesferal.hornsapp.data.repository

import com.yesferal.hornsapp.domain.abstraction.ConcertRepository
import com.yesferal.hornsapp.data.abstraction.ApiDataSource
import com.yesferal.hornsapp.data.abstraction.OrmDataSource
import com.yesferal.hornsapp.domain.common.Result
import com.yesferal.hornsapp.domain.entity.Concert

class ConcertRepositoryImpl(
    private val ormDataSource: OrmDataSource,
    private val apiDataSource: ApiDataSource
) : ConcertRepository {

    override suspend fun getConcerts() = apiDataSource.getConcerts()

    override suspend fun getConcert(
        id: String
    ): Result<Concert> = apiDataSource.getConcert(id)

    override suspend fun getFavoriteConcert(): List<Concert> {
        return ormDataSource.getFavoriteConcerts()
    }

    override suspend fun insertFavoriteConcert(
        concert: Concert
    ) {
        ormDataSource.insertFavoriteConcert(concert)
    }

    override suspend fun removeFavoriteConcert(
        concert: Concert
    ) {
        ormDataSource.removeFavoriteConcert(concert)
    }
}