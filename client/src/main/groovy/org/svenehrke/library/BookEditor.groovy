package org.svenehrke.library
import groovyx.javafx.SceneGraphBuilder
import org.opendolphin.core.client.ClientDolphin
import org.opendolphin.core.client.ClientPresentationModel
import static org.opendolphin.binding.JFXBinder.bind

import static org.svenehrke.library.Constants.ATT.TITLE
import static org.svenehrke.library.Constants.PM_ID.SELECTED

@SuppressWarnings("GroovyAssignabilityCheck")
class BookEditor {

	ClientPresentationModel selectedBook
	private javafx.scene.Node view
	private ClientDolphin clientDolphin

	javafx.scene.Node cachedOrNewView(SceneGraphBuilder sgb) {
		if (! view) {
			view = newView sgb
			initializeBindings sgb
			setCurrentBook()
		}
		return view
	}

	private javafx.scene.Node newView(SceneGraphBuilder sgb) {
		sgb.with {
			javafx.scene.Node result = gridPane hgap:10, vgap:12, padding: 20, {
				columnConstraints     minWidth: 80, halignment: "right"
				label       "Title",    row: 0, column: 0
				titleLabel = label       "-",    row: 0, column: 1, id: 'titleLabel'
			}

			return result
		}

	}

	def initializeBindings(SceneGraphBuilder sgb) {
		println selectedBook
		println "initializeBindings: sgb.titleLabel: ${sgb.titleLabel}"
		sgb.with {
			bind TITLE of selectedBook to 'text' of sgb.titleLabel
		}
	}

	def void setCurrentBook() {
		def pm = clientDolphin.findPresentationModelById(SELECTED)
		pm['bookId'].value = selectedBook.id
	}
}
