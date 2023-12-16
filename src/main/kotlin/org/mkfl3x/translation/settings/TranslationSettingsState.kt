package org.mkfl3x.translation.settings

import com.intellij.credentialStore.CredentialAttributes
import com.intellij.credentialStore.Credentials
import com.intellij.ide.passwordSafe.PasswordSafe
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import org.mkfl3x.translation.Language
import org.mkfl3x.translation.TargetLanguages

@State(name = "TranslationSettingsState", storages = [Storage("translationSettings.xml")])
class TranslationSettingsState : PersistentStateComponent<TranslationSettingsState> {

    val supportedTargetLanguages = TargetLanguages.values().map { Language(it.name, it.shortName) }
    var targetLanguage: String = supportedTargetLanguages.first().displayName
    var deeplToken: Credentials?
        set(value) = PasswordSafe.instance.set(DEEPL_PASSWORD, value)
        get() = PasswordSafe.instance.get(DEEPL_PASSWORD)

    override fun getState(): TranslationSettingsState = this

    override fun loadState(state: TranslationSettingsState) {
        targetLanguage = state.targetLanguage
        deeplToken = state.deeplToken
    }

    companion object {
        private val DEEPL_PASSWORD = CredentialAttributes("DeepL password")
    }
}