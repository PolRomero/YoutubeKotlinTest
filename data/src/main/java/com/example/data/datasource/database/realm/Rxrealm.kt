package com.example.data.datasource.database.realm

import io.reactivex.Maybe
import io.reactivex.MaybeEmitter
import io.reactivex.MaybeOnSubscribe
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmObject

object Rxrealm {

    fun <T: RealmObject> getElement( query: (Realm) -> T? ) : Maybe<T> = Maybe.create(MaybeOnSubscribe {
        val realm = Realm.getDefaultInstance()
        val result = query(realm)
        if(result != null &&  result.isLoaded && result.isValid) { it.onSuccess(realm.copyFromRealm(result)) }
            else { it.onComplete() }
        realm.close()
    })

}