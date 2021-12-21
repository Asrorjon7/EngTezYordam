package info.texnoman.engtezyordamasosiy.utils

import io.paperdb.Paper

object OrderIdSave {
    const val  notification ="oeifhef"
    fun saveId(id: Int){
        Paper.book().write(notification,id)
    }

    fun  getId(): Int {
        return Paper.book().read(notification,0)
    }
}