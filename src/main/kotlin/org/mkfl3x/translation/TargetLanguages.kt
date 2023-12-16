package org.mkfl3x.translation

enum class TargetLanguages(val shortName: String) {
    English_American("en-US"),
    English_British("en-GB"),
    Bulgarian("bg"),
    Czech("cs"),
    Chinese_Simplified("zh"),
    Danish("da"),
    German("de"),
    Greek("el"),
    Spanish("es"),
    Estonian("et"),
    Finnish("fi"),
    French("fr"),
    Hungarian("hu"),
    Indonesian("id"),
    Italian("it"),
    Japanese("ja"),
    Korean("ko"),
    Lithuanian("lt"),
    Latvian("lv"),
    Norwegian("nb"),
    Dutch("nl"),
    Polish("pl"),
    Portuguese_Brazilian("pt-BR"),
    Portuguese_European("pt-PT"),
    Romanian("ro"),
    Russian("ru"),
    Slovak("sk"),
    Slovenian("sl"),
    Swedish("sv"),
    Turkish("tr"),
    Ukrainian("uk")
}

data class Language(val displayName: String, val shortName: String)