package com.pseudoankit.notesappkmm.domain.model

import com.pseudoankit.notesappkmm.presentation.Colors.BabyBlueHex
import com.pseudoankit.notesappkmm.presentation.Colors.LightGreenHex
import com.pseudoankit.notesappkmm.presentation.Colors.RedOrangeHex
import com.pseudoankit.notesappkmm.presentation.Colors.RedPinkHex
import com.pseudoankit.notesappkmm.presentation.Colors.VioletHex
import kotlinx.datetime.LocalDateTime

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val createdAt: LocalDateTime
) {

    companion object {
        private val colors =
            listOf(RedOrangeHex, RedPinkHex, BabyBlueHex, VioletHex, LightGreenHex)

        fun generateRandomColor() = colors.random()
    }
}
