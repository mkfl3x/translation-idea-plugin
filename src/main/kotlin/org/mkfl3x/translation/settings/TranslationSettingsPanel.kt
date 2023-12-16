package org.mkfl3x.translation.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.util.ui.FormBuilder
import java.awt.GridLayout
import javax.swing.JComboBox
import javax.swing.JPanel
import javax.swing.JPasswordField

class TranslationSettingsPanel : JPanel(GridLayout(1, 1)) {

    // привет!
    val targetLanguage = JComboBox<String>()
    val deeplToken = JPasswordField()

    init {
        ApplicationManager.getApplication().getService(TranslationSettingsState::class.java)!!.apply {
            this.supportedTargetLanguages.forEach {
                this@TranslationSettingsPanel.targetLanguage.addItem(it.displayName)
            }
            this@TranslationSettingsPanel.deeplToken.text = deeplToken?.getPasswordAsString() ?: ""
        }

        add(
            FormBuilder.createFormBuilder()
                .addLabeledComponent("Target language:", targetLanguage)
                .addLabeledComponent("DeepL token:", deeplToken)
                .addComponentFillVertically(JPanel(), 0)
                .panel
        )
    }
}