package com.example.onlineshop

object RemoveHtmlTag {
        fun html2text(html: String): String {
            return html.replace("<.*?>".toRegex(), "")
        }
}