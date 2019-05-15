package com.martinchamarro.filmgoer.data.entity

import com.google.gson.annotations.SerializedName

data class MovieEntity(
        @SerializedName("Title") val title: String,
        @SerializedName("Year") val year: String,
        @SerializedName("Rated") val rated: String,
        @SerializedName("Released") val released: String,
        @SerializedName("Runtime") val runtime: String,
        @SerializedName("Genre") val genre: String,
        @SerializedName("Director") val director: String,
        @SerializedName("Writer") val writer: String,
        @SerializedName("Actors") val actors: String,
        @SerializedName("Plot") val plot: String,
        @SerializedName("Language") val language: String,
        @SerializedName("Country") val countries: String,
        @SerializedName("Awards") val awards: String,
        @SerializedName("Poster") val posterUrl: String,
        @SerializedName("Metascore") val metascore: String,
        @SerializedName("imdbRating") val imdbRating: String,
        @SerializedName("imdbVotes") val imdbVotes: String,
        @SerializedName("imdbID") val imdbId: String,
        @SerializedName("Type") val type: String,
        @SerializedName("Production") val production: String,
        @SerializedName("Website") val website: String) {

    override fun toString(): String {
        return "$imdbRating - $title ($year)"
    }

}