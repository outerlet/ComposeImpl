package jp.craftman1take.composeimpl.data

import androidx.annotation.DrawableRes
import jp.craftman1take.composeimpl.R

data class Picture(
    val id: Int,
    val title: String,
    @DrawableRes val resId: Int,
)

private val pictureTitles = listOf(
    "紅葉",
    "因幡の白兎",
    "桜",
    "秋の寺社",
    "夏の吊り橋",
    "春のツツジ",
    "山の案内熊",
    "山のトトロ",
    "秘境駅",
    "秋のススキ",
)

val pictureList = listOf(
    R.drawable.picture_01,
    R.drawable.picture_02,
    R.drawable.picture_03,
    R.drawable.picture_04,
    R.drawable.picture_05,
    R.drawable.picture_06,
    R.drawable.picture_07,
    R.drawable.picture_08,
    R.drawable.picture_09,
    R.drawable.picture_10,
).mapIndexed { index, resId -> Picture(index, pictureTitles[index], resId) }

val thumbnailPictureList = listOf(
    R.drawable.picture_01_tmb,
    R.drawable.picture_02_tmb,
    R.drawable.picture_03_tmb,
    R.drawable.picture_04_tmb,
    R.drawable.picture_05_tmb,
    R.drawable.picture_06_tmb,
    R.drawable.picture_07_tmb,
    R.drawable.picture_08_tmb,
    R.drawable.picture_09_tmb,
    R.drawable.picture_10_tmb,
).mapIndexed { index, resId -> Picture(index, pictureTitles[index], resId) }
