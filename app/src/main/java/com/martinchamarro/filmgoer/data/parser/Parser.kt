package com.martinchamarro.filmgoer.data.parser

interface Parser<INPUT, OUTPUT> {

    fun parse(input: INPUT?): OUTPUT

}