package com.clean.architecture.features.common

/**
 * Created by Rehan Sarwar on 07/06/2022.
 */
class DomainMapperException(message: String, throwable: Throwable? = null) :
    Exception(message, throwable)

class DatabaseMapperException(message: String, throwable: Throwable? = null) :
    Exception(message, throwable)

abstract class DataToDomainMapper<INPUT : Any, OUTPUT : Any> {
    fun toDomain(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DomainMapperException("Could not map ${input::class.simpleName} to Domain", throwable)
    }

    protected abstract fun map(input: INPUT): OUTPUT
}

abstract class DataToDatabaseMapper<INPUT : Any, OUTPUT : Any> {

    fun toDatabase(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DatabaseMapperException(
            "Could not map ${input::class.simpleName} to Database",
            throwable
        )
    }

    protected abstract fun map(input: INPUT): OUTPUT
}

abstract class DatabaseToDomainMapper<INPUT : Any, OUTPUT : Any> {

    fun toDomain(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DomainMapperException(
            "Could not map ${input::class.simpleName} to Domain",
            throwable
        )
    }

    protected abstract fun map(input: INPUT): OUTPUT
}
