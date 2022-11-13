package com.example.ssp.utils

class UFormatter {
    companion object {
        fun date(date: String): String {
            val year = date.subSequence(0, 4)
            val month = date.subSequence(4, 6)
            val day = date.subSequence(6, 8)

            return "$year-$month-$day"
        }

        fun time(time: String): String {
            println(time.length)
            val hh = time.subSequence(0, 2)
            val mm = time.subSequence(2, 4)

            return "$hh:$mm"
        }
    }
}