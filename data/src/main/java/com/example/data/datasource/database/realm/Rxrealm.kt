package com.example.data.datasource.database.realm

import io.reactivex.Maybe
import io.reactivex.MaybeOnSubscribe
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults

object Rxrealm {

    fun <T: RealmObject> getElement( query: (Realm) -> T? ) : Maybe<T> = Maybe.create(MaybeOnSubscribe {
        val realm = Realm.getDefaultInstance()
        val result = query(realm)
        if(result != null &&  result.isLoaded && result.isValid) { it.onSuccess(realm.copyFromRealm(result)) }
            else { it.onComplete() }
        it.setCancellable { realm.close() }
    })

    fun <T: RealmObject> getList( query: (Realm) -> RealmResults<T>? ) : Maybe<List<T>> = Maybe.create(MaybeOnSubscribe {
        val realm = Realm.getDefaultInstance()
        val result = query(realm)
        if(result != null &&  result.isLoaded && result.isValid) { it.onSuccess(realm.copyFromRealm(result)) }
            else { it.onComplete() }
        it.setCancellable{ realm.close() }
    })


}