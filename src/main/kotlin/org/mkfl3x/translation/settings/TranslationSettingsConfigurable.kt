package org.mkfl3x.translation.settings

import com.intellij.credentialStore.Credentials
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

class TranslationSettingsConfigurable : Configurable {

    private lateinit var settingsPanel: TranslationSettingsPanel

    override fun getDisplayName() = "Translation Settings"

    override fun createComponent(): JComponent {
        settingsPanel = TranslationSettingsPanel()
        return settingsPanel
    }

    override fun isModified(): Boolean {
        return ApplicationManager.getApplication().getService(TranslationSettingsState::class.java)!!.let {
            it.targetLanguage != settingsPanel.targetLanguage.selectedItem as String ||
            it.deeplToken != Credentials(null, settingsPanel.deeplToken.password)
        }
    }

    override fun apply() {
        ApplicationManager.getApplication().getService(TranslationSettingsState::class.java)!!.apply {
            this.targetLanguage = settingsPanel.targetLanguage.selectedItem as String
            this.deeplToken = Credentials(null, settingsPanel.deeplToken.password)
        }
    }
}