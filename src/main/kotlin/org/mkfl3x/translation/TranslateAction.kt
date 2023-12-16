package org.mkfl3x.translation

import com.deepl.api.AuthorizationException
import com.deepl.api.Translator
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ui.Messages.showMessageDialog
import com.intellij.openapi.ui.popup.Balloon
import com.intellij.openapi.ui.popup.JBPopupFactory
import org.mkfl3x.translation.settings.TranslationSettingsState

class TranslateAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getData(CommonDataKeys.EDITOR)!!
        if (editor.selectionModel.hasSelection())
            translate(editor.selectionModel.selectedText!!, editor)
    }
}

private fun translate(text: String, editor: Editor) {
    val settings = ApplicationManager.getApplication().getService(TranslationSettingsState::class.java)!!
    if (settings.deeplToken == null)
        showMessageDialog(null, "DeepL token is not defined", "DeepL token error", null)
    try {
        val translation = Translator(settings.deeplToken?.getPasswordAsString())
            .translateText(
                text,
                null,
                settings.supportedTargetLanguages.first { it.displayName == settings.targetLanguage }.shortName
            )
        if (text != translation.text)
            showTranslation(
                "${translation.text}\n\nSource language detected: '${translation.detectedSourceLanguage.uppercase()}']",
                editor
            )
        else
            showTranslation("Can't translate, sorry :(", editor)
    } catch (e: AuthorizationException) {
        showMessageDialog(null, "Wrong DeepL token", "DeepL token error", null)
    }
}

private fun showTranslation(message: String, editor: Editor) {
    JBPopupFactory.getInstance()
        .createHtmlTextBalloonBuilder(message, null, editor.colorsScheme.defaultBackground, null)
        .createBalloon()
        .show(JBPopupFactory.getInstance().guessBestPopupLocation(editor), Balloon.Position.atRight)
}